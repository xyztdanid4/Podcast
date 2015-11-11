package szakdolgozat.podcast.gui.borderpane;

import java.util.ArrayList;

import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import szakdolgozat.podcast.builder.ButtonBuilder;
import szakdolgozat.podcast.builder.HBoxBuilder;
import szakdolgozat.podcast.builder.ListViewBuilder;
import szakdolgozat.podcast.builder.TextBuilder;
import szakdolgozat.podcast.builder.TextFieldBuilder;
import szakdolgozat.podcast.builder.VBoxBuilder;
import szakdolgozat.podcast.data.podcast.Podcast;
import szakdolgozat.podcast.data.podcast.PodcastEpisode;
import szakdolgozat.podcast.gui.decorator.Decorator;
import szakdolgozat.podcast.gui.decorator.SearchBPDecorator;
import szakdolgozat.podcast.gui.mediaplayer.MediaControlPodcast;
import szakdolgozat.podcast.xmlparser.XmlParser;

public class SearchBorderPaneView extends BorderPane {
	private static final String SEARCHTEXTFIELD_PROMPTTEXT = "SEARCH ME!";
	private static final String SEARCHBUTTON_TEXT = "Search!";
	private static final String EPISODESTEXT = "Episodes";
	private static final String PODCASTTEXT = "Podcasts";
	private static final String SUBSCRIBED_TEXT = "Subscribe!";
	private static final String ALREADYSUBSCRIBED_TEXT = "Already subscribed!";
	private static final String NORESULT = "No result found!";
	private final Button searchButton;
	private final TextField searchTextField;
	private ObservableList<HBox> searchResultList;
	private ListView<HBox> searchResultListView;
	private ObservableList<HBox> episodesList;
	private ListView<HBox> episodesListView;
	private VBox podcastListVBox;
	private VBox episodesListVBox;
	private final SearchBorderPaneController searchBorderPaneController;

	public SearchBorderPaneView() {
		this.searchBorderPaneController = new SearchBorderPaneController();
		//-.-off
		this.searchTextField = TextFieldBuilder.create()
												.promptText(SEARCHTEXTFIELD_PROMPTTEXT)
												.size(SearchBPDecorator.SEARCHTEXTFIELDWIDTH, SearchBPDecorator.SEARCHTEXTFIELDHEIGHT)
												.build();
		
		setSearchTextFieldKeyEvent();
		
		this.searchButton = ButtonBuilder.create()
									.text(SEARCHBUTTON_TEXT)
									.build();
		setSearchButtonFunction();
		setButtonDisability();
		setTop(HBoxBuilder.noCreate()
							.noTextField(this.searchTextField)
							.noButton(this.searchButton)
							.build());
		//-.-on
		setPadding();
		showPodcastList();
		showEpisodesList();
		SearchBPDecorator.decorateFactory(this);
	}

	public SearchBorderPaneView(final String searchText) {
		this.searchBorderPaneController = new SearchBorderPaneController();
		//-.-off
		this.searchTextField = TextFieldBuilder.create()
												.promptText(SEARCHTEXTFIELD_PROMPTTEXT)
												.size(SearchBPDecorator.SEARCHTEXTFIELDWIDTH, SearchBPDecorator.SEARCHTEXTFIELDHEIGHT)
												.build();
		
		setSearchTextFieldKeyEvent();
		
		this.searchButton = ButtonBuilder.create()
									.text(SEARCHBUTTON_TEXT)
									.build();
		setSearchButtonFunction();
		setButtonDisability();
		setTop(HBoxBuilder.noCreate()
							.noTextField(this.searchTextField)
							.noButton(this.searchButton)
							.build());
		//-.-on
		setPadding();
		showPodcastList();
		showEpisodesList();
		SearchBPDecorator.decorateFactory(this);

		this.searchTextField.setText(searchText);
		this.searchButton.fire();
	}

	private void setPadding() {
		setPadding(new Insets(Decorator.PADDING, Decorator.PADDING, Decorator.PADDING, Decorator.PADDING));
	}

	private void setSearchTextFieldKeyEvent() {
		this.searchTextField.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER) {
				SearchBorderPaneView.this.searchButton.fire();
			}
		});
	}

	private void setButtonDisability() {
		this.searchButton.disableProperty().bind(Bindings.isEmpty(this.searchTextField.textProperty()));
	}

	private void setSearchButtonFunction() {
		this.searchButton.setOnAction((final ActionEvent event) -> {
			this.searchBorderPaneController.startSearchPodcast(SearchBorderPaneView.this.searchTextField.getText());
			showSearchResult();
		});
	}

	private void showSearchResult() {
		clearLists();
		for (final Podcast podcast : this.searchBorderPaneController.getSearchPodcastContainer().getResults()) {
			//-.-off
			Button subscribeButton;
			if (this.searchBorderPaneController.isPodcastSubscribed(podcast.getArtistName())) {
				subscribeButton = ButtonBuilder.create()
												.text(ALREADYSUBSCRIBED_TEXT)
												.disable(true)
												.build();
			} else {
				subscribeButton = ButtonBuilder.create()
												.text(SUBSCRIBED_TEXT)
												.disable(false)
												.build();
				subscribeButton.setOnAction((final ActionEvent event) -> {
					this.searchBorderPaneController.subscribe(podcast);
					subscribeButton.setDisable(true);
					subscribeButton.setText(ALREADYSUBSCRIBED_TEXT);
					this.searchBorderPaneController.notice(podcast);
				});
			}
			this.searchResultList.add(HBoxBuilder.create()
					.smallRectangle(podcast.getArtworkUrl60())
					.artist(podcast.getArtistName())
					.noButton(subscribeButton)
					.effectOn()
					.build());
		}
		//-.-on
		if (!this.searchResultList.isEmpty()) {
			enableLists();
		} else {
			showNoResultFound();
		}
		this.searchResultListView.setItems(this.searchResultList);
	}

	private void setListViewInvalidationListener() {
		this.searchResultListView.getSelectionModel().selectedIndexProperty().addListener((final Observable o) -> {
			if (!this.searchResultListView.getSelectionModel().isEmpty()) {
				this.episodesList.clear();
				final int selected = this.searchResultListView.getSelectionModel().getSelectedIndex();
				final XmlParser xmlParser = new XmlParser(this.searchBorderPaneController.getSearchPodcastContainer()
						.getResults().get(selected).getFeedUrl());
				this.searchBorderPaneController.getSearchPodcastContainer().getResults().get(selected)
						.setPodcastEpisode(new ArrayList<PodcastEpisode>(xmlParser.readFeed()));
				for (final PodcastEpisode podcastEpisode : this.searchBorderPaneController.getSearchPodcastContainer()
						.getResults().get(selected).getPodcastEpisode()) {
					HBox itemHbox = null;
					final Image image = new Image(podcastEpisode.getImage());
					if (image.isError()) {
						try {
							//-.-off
							itemHbox = HBoxBuilder.create()
													.image(podcastEpisode.getImage())
													.title(podcastEpisode.getTitle())
													.effectOn()
													.build();
							//-.-on
							itemHbox.setOnMouseClicked(event -> {
								if (event.getButton().equals(MouseButton.PRIMARY)) {
									if (event.getClickCount() == 2) {
										try {
											MediaControlPodcast.getInstance().stop();
											MainBorderPane.getInstance().buildBottom(podcastEpisode);
										} catch (final Exception e) {
											e.printStackTrace();
										}
									}
								}
							});
						} catch (final Exception e) {
							e.printStackTrace();
						}
					} else {
						//-.-off
						itemHbox = HBoxBuilder.create()
												.smallRectangle(podcastEpisode.getImage())
												.title(podcastEpisode.getTitle())
												.effectOn()
												.build();
						//-.-on
						itemHbox.setOnMouseClicked(event -> {
							if (event.getButton().equals(MouseButton.PRIMARY)) {
								if (event.getClickCount() == 2) {
									try {
										MediaControlPodcast.getInstance().stop();
										MainBorderPane.getInstance().buildBottom(podcastEpisode);
									} catch (final Exception e) {
										e.printStackTrace();
									}
								}
							}
						});
					}
					this.episodesList.add(itemHbox);
				}
				this.episodesListView.setItems(this.episodesList);
			}
		});
	}

	private void showEpisodesList() {
		this.episodesList = FXCollections.observableArrayList();
		//-.-off
		this.episodesListView = ListViewBuilder.create()
												.items(this.episodesList)
												.size(SearchBPDecorator.EPISODESLISTWIDTH, SearchBPDecorator.EPISODESLISTHEIGHT)
												.build();
		this.episodesListVBox = VBoxBuilder.create()
											.bigText(EPISODESTEXT)
											.noListView(this.episodesListView)
											.topLeftAlignment()
											.build();
		//-.-on
		setMargin(this.episodesListVBox, new Insets(Decorator.PADDING));
		setCenter(this.episodesListVBox);
	}

	private void showPodcastList() {
		this.searchResultList = FXCollections.observableArrayList();
		//-.-off
		this.searchResultListView = ListViewBuilder.create()
													.items(this.searchResultList)
													.size(SearchBPDecorator.PODCASTLISTWIDTH, SearchBPDecorator.PODCASTLISTHEIGHT)
													.build();

		
		this.podcastListVBox = VBoxBuilder.create()
											.bigText(PODCASTTEXT)
											.noListView(this.searchResultListView)
											.topLeftAlignment()
											.build();
		//-.-on
		setMargin(this.podcastListVBox, new Insets(Decorator.PADDING));
		setListViewInvalidationListener();
		setLeft(this.podcastListVBox);
	}

	private void showNoResultFound() {
		//-.-off
		this.searchResultList.add(HBoxBuilder.create()
												.noText(TextBuilder.create()
																	.bigText(NORESULT)
																	.build())
												.build());
		//-.-on
		this.searchResultListView.setDisable(true);
		this.episodesListView.setDisable(true);
	}

	private void enableLists() {
		this.searchResultListView.setDisable(false);
		this.episodesListView.setDisable(false);
	}

	private void clearLists() {
		this.searchResultList.clear();
		this.episodesList.clear();
	}
}
