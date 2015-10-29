package szakdolgozat.podcast.gui.borderpane;

import java.util.ArrayList;

import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.ListView;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import szakdolgozat.podcast.data.podcast.Podcast;
import szakdolgozat.podcast.data.podcast.PodcastContainer;
import szakdolgozat.podcast.data.podcast.PodcastEpisode;
import szakdolgozat.podcast.gui.decorator.SearchBPDecorator;
import szakdolgozat.podcast.gui.samples.ButtonSample;
import szakdolgozat.podcast.gui.samples.TextFieldSample;
import szakdolgozat.podcast.jsonparser.PodcastJsonParser;
import szakdolgozat.podcast.morphia.MorphiaConnector;
import szakdolgozat.podcast.xmlparser.XmlParser;

public class SearchBorderPane extends BorderPane {
	private static final String SEARCHTEXTFIELD_PROMPTTEXT = "SEARCH ME!";
	private static final String SEARCHTEXTFIELD_TOOLTIP = "Type something, you would like to podcast!";
	private static final String SEARCHBUTTON_TOOLTIP = "Click me for search!";
	private static final String SEARCHBUTTON_TEXT = "Search!";
	private static final String EPISODESTEXT = "Episodes";
	private static final String PODCASTTEXT = "Podcasts";
	private static final String SUBSCRIBED_TEXT = "Subscribe!";
	private static final String ALREADYSUBSCRIBED_TEXT = "Already subscribed!";
	private static final String SUBSCRIBED_TOOLTIP = "Click for subscribe!";
	private static final String ALREADYSUBSCRIBED_TOOLTIP = "Choose from others to subscribe!";
	private static final String NORESULT = "No result found!";
	private ButtonSample searchButton;
	private HBox searchItemsContainerHbox;
	private TextFieldSample searchTextField;
	private PodcastContainer searchPodcastContainer;
	private ObservableList<HBox> searchResultList;
	private ListView searchResultListView;
	private ObservableList<HBox> episodesList;
	private ListView episodesListView;
	private VBox podcastListVBox;
	private VBox episodesListVBox;

	public SearchBorderPane() {
		searchTextField = SearchBPDecorator.decorateTextFieldSampleFactory(
				new TextFieldSample(SEARCHTEXTFIELD_PROMPTTEXT, SEARCHTEXTFIELD_TOOLTIP),
				SearchBPDecorator.SEARCHTEXTFIELDWIDTH, SearchBPDecorator.SEARCHTEXTFIELDHEIGHT);
		searchButton = SearchBPDecorator
				.decorateButtonSampleFactory(new ButtonSample(SEARCHBUTTON_TEXT, SEARCHBUTTON_TOOLTIP));
		setTop(new HBox(SearchBPDecorator.HBOXPADDING, searchTextField, searchButton));
		setButtonDisability();
		setSearchButtonFunctionality();
		setSearchTextFieldKeyEvent();
		setPadding();
		showPodcastList();
		showEpisodesList();
		SearchBPDecorator.decorateFactory(this);
	}

	private void setPadding() {
		setPadding(new Insets(SearchBPDecorator.PADDING, SearchBPDecorator.PADDING, SearchBPDecorator.PADDING,
				SearchBPDecorator.PADDING));
	}

	private void setSearchTextFieldKeyEvent() {
		searchTextField.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.ENTER) {
					searchButton.fire();
				}
			}
		});
	}

	private void setButtonDisability() {
		searchButton.disableProperty().bind(Bindings.isEmpty(searchTextField.textProperty()));
	}

	private void setSearchButtonFunctionality() {
		searchButton.setOnAction((ActionEvent event) -> {
			startSearchPodcast();
		});
	}

	private void startSearchPodcast() {
		PodcastJsonParser jsonParser = new PodcastJsonParser(new String("https://itunes.apple.com/search?term="
				+ searchTextField.getText() + "&entity=podcast&media=podcast&limit=5"));
		searchPodcastContainer = jsonParser.getSearchResult();
		showSearchResult();
	}

	private void showSearchResult() {
		clearLists();
		for (Podcast podcast : searchPodcastContainer.getResults()) {
			ButtonSample subscribeButton;
			if (isPodcastSubscribed(podcast.getArtistName())) {
				subscribeButton = SearchBPDecorator.decorateButtonSampleFactory(
						new ButtonSample(ALREADYSUBSCRIBED_TEXT, ALREADYSUBSCRIBED_TOOLTIP));
				subscribeButton.setDisable(true);
			} else {
				subscribeButton = SearchBPDecorator
						.decorateButtonSampleFactory(new ButtonSample(SUBSCRIBED_TEXT, SUBSCRIBED_TOOLTIP));
				subscribeButton.setDisable(false);
				subscribeButton.setOnAction((ActionEvent event) -> {
					XmlParser xmlParser = new XmlParser(podcast.getFeedUrl());
					podcast.setPodcastEpisode(new ArrayList<PodcastEpisode>(xmlParser.readFeed()));
					subscribe(podcast);
					subscribeButton.setDisable(true);
					subscribeButton.setText(ALREADYSUBSCRIBED_TEXT);
					subscribeButton.setTooltip(new Tooltip(ALREADYSUBSCRIBED_TOOLTIP));
				});
			}
			searchResultList.add(SearchBPDecorator.decorateHBoxFactory(new HBox(SearchBPDecorator.PADDING,
					SearchBPDecorator.decorateRectangleFactory(new Rectangle(), SearchBPDecorator.SMALLRECTANGLEHEIGHT,
							SearchBPDecorator.SMALLRECTANGLEWIDTH, podcast.getArtworkUrl60()),
					SearchBPDecorator.decorateTextFactory(new Text(podcast.getArtistName().length() > 20
							? new String(new StringBuilder(podcast.getArtistName().substring(0, 20)).append("..."))
							: podcast.getArtistName()), SearchBPDecorator.SMALLTEXTSIZE),
					subscribeButton)));
		}
		if (!searchResultList.isEmpty()) {
			enableLists();
		} else {
			showNoResultFound();
		}
		searchResultListView.setItems(searchResultList);
	}

	private void setListViewInvalidationListener() {
		searchResultListView.getSelectionModel().selectedIndexProperty().addListener((Observable o) -> {
			if (!searchResultListView.getSelectionModel().isEmpty()) {
				episodesList.clear();
				int selected = searchResultListView.getSelectionModel().getSelectedIndex();
				XmlParser xmlParser = new XmlParser(searchPodcastContainer.getResults().get(selected).getFeedUrl());
				searchPodcastContainer.getResults().get(selected)
						.setPodcastEpisode(new ArrayList<PodcastEpisode>(xmlParser.readFeed()));
				for (PodcastEpisode podcastEpisode : searchPodcastContainer.getResults().get(selected)
						.getPodcastEpisode()) {
					HBox itemHbox = null;
					Image image = new Image(podcastEpisode.getImage());
					if (image.isError()) {
						try {
							// action miatt meg kell hagyni
							itemHbox = SearchBPDecorator
									.decorateHBoxFactory(
											new HBox(SearchBPDecorator.PADDING,
													SearchBPDecorator.decorateImageViewFactory(
															new ImageView(new Image(
																	podcastEpisode.getImage())),
													SearchBPDecorator.IMAGEHEIGHT,
													SearchBPDecorator.IMAGEWIDTH), SearchBPDecorator
															.decorateTextFactory(
																	new Text(podcastEpisode.getTitle().length() > 40
																			? new String(new StringBuilder(
																					podcastEpisode.getTitle()
																							.substring(0, 40))
																									.append("..."))
																			: podcastEpisode.getTitle()),
																	SearchBPDecorator.SMALLTEXTSIZE)));
						} catch (Exception e) {
							e.printStackTrace();
							System.out.print("IMAGEHIBA SEARCHBORDERPANE");
						}
					} else {
						itemHbox = SearchBPDecorator.decorateHBoxFactory(new HBox(SearchBPDecorator.PADDING,
								SearchBPDecorator.decorateRectangleFactory(new Rectangle(),
										SearchBPDecorator.SMALLRECTANGLEHEIGHT, SearchBPDecorator.SMALLRECTANGLEWIDTH,
										podcastEpisode
												.getImage()),
								SearchBPDecorator
										.decorateTextFactory(
												new Text(
														podcastEpisode.getTitle().length() > 40
																? new String(new StringBuilder(
																		podcastEpisode.getTitle().substring(0, 40))
																				.append("..."))
																: podcastEpisode.getTitle()),
												SearchBPDecorator.SMALLTEXTSIZE)));
					}
					episodesList.add(itemHbox);
				}
				episodesListView.setItems(episodesList);
			}
		});
	}

	private void showEpisodesList() {
		episodesList = FXCollections.observableArrayList();
		episodesListView = SearchBPDecorator.decorateListViewFactory(new ListView<HBox>(episodesList),
				SearchBPDecorator.EPISODESLISTWIDTH, SearchBPDecorator.EPISODESLISTHEIGHT);
		episodesListVBox = new VBox(SearchBPDecorator.PADDING,
				SearchBPDecorator.decorateTextFactory(new Text(EPISODESTEXT), SearchBPDecorator.BIGTEXTSIZE),
				episodesListView);
		setMargin(episodesListVBox, new Insets(SearchBPDecorator.PADDING));
		setCenter(episodesListVBox);
	}

	private void showPodcastList() {
		searchResultList = FXCollections.observableArrayList();
		searchResultListView = SearchBPDecorator.decorateListViewFactory(new ListView<HBox>(searchResultList),
				SearchBPDecorator.PODCASTLISTWIDTH, SearchBPDecorator.PODCASTLISTHEIGHT);
		podcastListVBox = new VBox(SearchBPDecorator.PADDING,
				SearchBPDecorator.decorateTextFactory(new Text(PODCASTTEXT), SearchBPDecorator.BIGTEXTSIZE),
				searchResultListView);
		// setAlignment(podcastListVBox, Pos.CENTER_LEFT);
		setMargin(podcastListVBox, new Insets(SearchBPDecorator.PADDING));
		setListViewInvalidationListener();
		setLeft(podcastListVBox);
	}

	private boolean isPodcastSubscribed(final String name) {
		return !(MorphiaConnector.getDataStore().createQuery(Podcast.class).filter("artistName = ", name).asList()
				.isEmpty());
	}

	private void showNoResultFound() {
		Text text = new Text(new String(NORESULT));
		SearchBPDecorator.decorateTextFactory(text, SearchBPDecorator.BIGTEXTSIZE);
		HBox itemHbox = new HBox(SearchBPDecorator.PADDING, text);
		SearchBPDecorator.decorateHBoxFactory(itemHbox);
		searchResultList.add(itemHbox);
		searchResultListView.setDisable(true);
		episodesListView.setDisable(true);
	}

	private void enableLists() {
		searchResultListView.setDisable(false);
		episodesListView.setDisable(false);
	}

	private void clearLists() {
		searchResultList.clear();
		episodesList.clear();
	}

	private void subscribe(Podcast podcast) {
		MorphiaConnector.getInstance().getDataStore().save(podcast);
	}
}
