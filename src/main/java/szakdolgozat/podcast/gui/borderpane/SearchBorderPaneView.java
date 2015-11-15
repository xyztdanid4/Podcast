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
import szakdolgozat.podcast.controller.SearchBorderPaneController;
import szakdolgozat.podcast.data.podcast.Podcast;
import szakdolgozat.podcast.data.podcast.PodcastEpisode;
import szakdolgozat.podcast.gui.decorator.Decorator;
import szakdolgozat.podcast.gui.decorator.SearchBPDecorator;
import szakdolgozat.podcast.xmlparser.XmlParser;

/**
 * The Class SearchBorderPaneView. Contains the view of the SearchBorderpane.
 * Use the class to represent a search function for the program
 * 
 * @author Tóth Dániel YQZQMJ
 * @since 2015-09-01
 * @version 1.0
 * @see BorderPane
 * 
 */
public class SearchBorderPaneView extends BorderPane {

	/**
	 * The Constant SEARCHTEXTFIELD_PROMPTTEXT. Use this constant as prompttext
	 * for our searchTextField.
	 * 
	 * @see TextField
	 * @see String
	 */
	private static final String SEARCHTEXTFIELD_PROMPTTEXT = "SEARCH ME!";

	/**
	 * The Constant SEARCHBUTTON_TEXT. Use this constant as Button text.
	 * 
	 * @see Button
	 * @see String
	 */
	private static final String SEARCHBUTTON_TEXT = "Search!";

	/**
	 * The Constant EPISODESTEXT. Use this constant for indicate the episodes
	 * which are contained by ListView.
	 * 
	 * @see ListView
	 * @see String
	 */
	private static final String EPISODESTEXT = "Episodes";

	/**
	 * The Constant PODCASTTEXT.
	 * 
	 */
	private static final String PODCASTTEXT = "Podcasts";

	/** The Constant SUBSCRIBED_TEXT. */
	private static final String SUBSCRIBED_TEXT = "Subscribe!";

	/** The Constant ALREADYSUBSCRIBED_TEXT. */
	private static final String ALREADYSUBSCRIBED_TEXT = "Already subscribed!";

	/** The Constant NORESULT. */
	private static final String NORESULT = "No result found!";

	/** The search button. */
	private final Button searchButton;

	/** The search text field. */
	private final TextField searchTextField;

	/** The search result list. */
	private ObservableList<HBox> searchResultList;

	/** The search result list view. */
	private ListView<HBox> searchResultListView;

	/** The episodes list. */
	private ObservableList<HBox> episodesList;

	/** The episodes list view. */
	private ListView<HBox> episodesListView;

	/** The podcast list v box. */
	private VBox podcastListVBox;

	/** The episodes list v box. */
	private VBox episodesListVBox;

	/** The search border pane controller. */
	private final SearchBorderPaneController searchBorderPaneController;

	/**
	 * Instantiates a new search border pane view.
	 */
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

	/**
	 * Instantiates a new search border pane view.
	 *
	 * @param searchText
	 *            the search text
	 */
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

	/**
	 * Sets the padding.
	 */
	private void setPadding() {
		setPadding(new Insets(Decorator.PADDING, Decorator.PADDING, Decorator.PADDING, Decorator.PADDING));
	}

	/**
	 * Sets the search text field key event.
	 */
	private void setSearchTextFieldKeyEvent() {
		this.searchTextField.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER) {
				SearchBorderPaneView.this.searchButton.fire();
			}
		});
	}

	/**
	 * Sets the button disability.
	 */
	private void setButtonDisability() {
		this.searchButton.disableProperty().bind(Bindings.isEmpty(this.searchTextField.textProperty()));
	}

	/**
	 * Sets the search button function.
	 */
	private void setSearchButtonFunction() {
		this.searchButton.setOnAction((final ActionEvent event) -> {
			this.searchBorderPaneController.startSearchPodcast(SearchBorderPaneView.this.searchTextField.getText());
			showSearchResult();
		});
	}

	/**
	 * Show search result.
	 */
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
					this.searchBorderPaneController.startDownload(podcast);
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

	/**
	 * Sets the list view invalidation listener.
	 */
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
									if (event.getButton().equals(MouseButton.PRIMARY)) {
										if (event.getClickCount() == 2) {
											this.searchBorderPaneController.startNewMediaPlayer(podcastEpisode);
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
								if (event.getButton().equals(MouseButton.PRIMARY)) {
									if (event.getClickCount() == 2) {
										this.searchBorderPaneController.startNewMediaPlayer(podcastEpisode);
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

	/**
	 * Show episodes list.
	 */
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

	/**
	 * Show podcast list.
	 */
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

	/**
	 * Show no result found.
	 */
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

	/**
	 * Enable lists. Use the method for enable our lists
	 * 
	 */
	private void enableLists() {
		this.searchResultListView.setDisable(false);
		this.episodesListView.setDisable(false);
	}

	/**
	 * Clear lists. Use the method for clearing our lists
	 *
	 */
	private void clearLists() {
		this.searchResultList.clear();
		this.episodesList.clear();
	}
}
