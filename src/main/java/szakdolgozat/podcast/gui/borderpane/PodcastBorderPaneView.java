package szakdolgozat.podcast.gui.borderpane;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import szakdolgozat.podcast.builder.HBoxBuilder;
import szakdolgozat.podcast.builder.ListViewBuilder;
import szakdolgozat.podcast.builder.VBoxBuilder;
import szakdolgozat.podcast.data.podcast.Podcast;
import szakdolgozat.podcast.data.podcast.PodcastEpisode;
import szakdolgozat.podcast.gui.decorator.Decorator;
import szakdolgozat.podcast.gui.decorator.PodcastBPDecorator;

public class PodcastBorderPaneView extends BorderPane {
	public static final String NOSUBSCRIPTIONS = "You have no subscriptions yet!";
	public static final String GETINFO = "Choose from subscribed podcast to get information!";
	public static final String SUBSCRIBEDPODCAST = "Subscribed Podcasts";
	public static final String UNSUBSCRIBE = "Unsubscribe!";
	public static final String CLICKFORUNSUBSCRIBE = "Click for unsubscribe!";
	public static final String NOPODCASTSELECTED = "Choose from subscribed podcasts!";
	public static final String SUBSCRIBEDEPISODES = "Subscribed Episodes";
	public static final String ARTISTLABEL = "Artist name: ";
	public static final String COLLECTIONNAMELABEL = "Title: ";
	public static final String COUNTRYLABEL = "Country: ";
	public static final String FEEDURLLABEL = "Feed url: ";
	public static final String GENRELABEL = "Genre: ";
	public static final String LASTRELEASEDATELABEL = "Last Updated: ";
	public static final String TRACKCOUNTLABEL = "Number of episodes: ";
	private ListView<HBox> podcastListView;
	private ListView<HBox> episodeListView;
	PodcastBoderPaneController podcastBoderPaneController;

	public PodcastBorderPaneView() {
		this.podcastBoderPaneController = new PodcastBoderPaneController();
		this.podcastBoderPaneController.readfromDB();
		PodcastBPDecorator.decorateFactory(this);
		setPadding();
		showPodcastEmptyInformation();
		showSubscribedPodcasts();
		showEmptyEpisodesList();
		if (!this.podcastBoderPaneController.getPodcastsFromDBList().isEmpty()) {
			setPodcastListInvalidationListener();
		}

	}

	private void setPadding() {
		setPadding(new Insets(Decorator.PADDING, Decorator.PADDING, Decorator.PADDING, Decorator.PADDING));
	}

	private void showPodcastEmptyInformation() {
		//-.-off
		setTop(HBoxBuilder.create()
							.bigText(GETINFO)
							.size(PodcastBPDecorator.PODCASTINFORMATIONCONTAINERWIDTH,PodcastBPDecorator.PODCASTINFORMATIONCONTAINERHEIGHT)
							.build());
		//-.-on
	}

	private void showSubscribedPodcasts() {
		this.podcastBoderPaneController.readfromDB();
		if (!this.podcastBoderPaneController.getPodcastsFromDBList().isEmpty()) {
			final ObservableList<HBox> podcastsContainer = FXCollections.observableArrayList();
			for (final Podcast podcastIterator : this.podcastBoderPaneController.getPodcastsFromDBList()) {
				//-.-off
				podcastsContainer.add(HBoxBuilder.create()
												.smallRectangle(podcastIterator.getArtworkUrl60())
												.artist(podcastIterator.getArtistName())
												.effectOn()
												.button(new Button() {
												{
													setOnAction(event -> {
														PodcastBorderPaneView.this.podcastBoderPaneController
															.removefromDB(podcastIterator.getArtistName());
														showPodcastEmptyInformation();
														showSubscribedPodcasts();
														showEmptyEpisodesList();
														setPodcastListInvalidationListener();
														PodcastBorderPaneView.this.podcastBoderPaneController.notice(podcastIterator);
													});
												setText(UNSUBSCRIBE);
												}
												}).build());
			}
			//-.-on
			//-.-off
			this.podcastListView = ListViewBuilder.create()
													.items(podcastsContainer)
													.size(PodcastBPDecorator.PODCASTLISTVIEWWIDTH, PodcastBPDecorator.PODCASTLISTVIEWHEIGHT)
													.build();
			//-.-on
			//-.-off
			final VBox podcastListVBox = VBoxBuilder.create()
													.bigText(SUBSCRIBEDPODCAST)
													.noListView(this.podcastListView)
													.topLeftAlignment()
													.build();
			//-.-on
			setMargin(podcastListVBox, new Insets(Decorator.PADDING));
			setLeft(podcastListVBox);
		} else {
			//-.-off
			final VBox podcastVBox = VBoxBuilder.create()
												.bigText(SUBSCRIBEDPODCAST)
												.noHBox(HBoxBuilder.create()
																	.smallText(NOSUBSCRIPTIONS)
																	.size(PodcastBPDecorator.PODCASTLISTVIEWWIDTH, PodcastBPDecorator.NOSUBSCRIPTIONHBOXHEIGHT)
																	.build())
												.topLeftAlignment()
												.build();
			//-.-on
			setMargin(podcastVBox, new Insets(Decorator.PADDING));
			setLeft(podcastVBox);
		}
	}

	private void setPodcastListInvalidationListener() {
		this.podcastListView.getSelectionModel().selectedIndexProperty().addListener((final Observable o) -> {
			showPodcastInformation();
			showpodcastEpisodes();
		});

	}

	private void showPodcastInformation() {
		this.podcastBoderPaneController.readfromDB();
		final int selectedindex = this.podcastListView.getSelectionModel().getSelectedIndex();
		//-.-off
		setTop(HBoxBuilder.create()
							.size(PodcastBPDecorator.PODCASTINFORMATIONCONTAINERWIDTH, PodcastBPDecorator.PODCASTINFORMATIONCONTAINERHEIGHT)
							.noVBox(VBoxBuilder.create()
												.bigRectangle(this.podcastBoderPaneController.getPodcastsFromDBList().get(selectedindex).getArtworkUrl100())
												.build())
							.noVBox(VBoxBuilder.create()
												.smallText(ARTISTLABEL + this.podcastBoderPaneController.getPodcastsFromDBList().get(selectedindex).getArtistName())
												.smallText(COLLECTIONNAMELABEL + this.podcastBoderPaneController.getPodcastsFromDBList().get(selectedindex).getCollectionName())
												.smallText(COUNTRYLABEL + this.podcastBoderPaneController.getPodcastsFromDBList().get(selectedindex).getCountry())
												.build())
							.noVBox(VBoxBuilder.create()
												.smallText(FEEDURLLABEL + this.podcastBoderPaneController.getPodcastsFromDBList().get(selectedindex).getFeedUrl())
												.smallText(GENRELABEL + this.podcastBoderPaneController.getPodcastsFromDBList().get(selectedindex).getPrimaryGenreName())
												.smallText(LASTRELEASEDATELABEL + this.podcastBoderPaneController.getPodcastsFromDBList().get(selectedindex).getReleaseDate())
												.build())
							.build());
		//-.-on
	}

	private void showEmptyEpisodesList() {
		//-.-off
		final VBox episodeVBox = VBoxBuilder.create()
											.bigText(SUBSCRIBEDEPISODES)
											.topLeftAlignment()
											.noHBox(HBoxBuilder.create()
																.smallText(NOPODCASTSELECTED)
																.size(PodcastBPDecorator.EMPTYEPISODESLISTWIDTH, PodcastBPDecorator.EMPTYEPISODESLISTHEIGHT)
																.build())
											.build();
		//-.-on
		setMargin(episodeVBox, new Insets(Decorator.PADDING));
		setCenter(episodeVBox);
	}

	private void showpodcastEpisodes() {
		this.podcastBoderPaneController.readfromDB();
		final int selected = this.podcastListView.getSelectionModel().getSelectedIndex();
		final ObservableList<HBox> episodesContainer = FXCollections.observableArrayList();
		for (final PodcastEpisode podcastEpisode : this.podcastBoderPaneController.getPodcastsFromDBList().get(selected)
				.getPodcastEpisode()) {
			final Image image = new Image(podcastEpisode.getImage());
			HBox itemHbox = null;
			if (image.isError()) {
				//-.-off
				itemHbox = HBoxBuilder.create()
										.image(podcastEpisode.getImage())
										.title(podcastEpisode.getTitle())
										.effectOn()
										.build();
				//-.-on
			} else {
				//-.-off
				itemHbox = HBoxBuilder.create()
										.smallRectangle(podcastEpisode.getImage())
										.title(podcastEpisode.getTitle())
										.effectOn()
										.build();
				//-.-on
			}
			episodesContainer.add(itemHbox);
		}
		//-.-off
		this.episodeListView = ListViewBuilder.create()
												.items(episodesContainer)
												.size(PodcastBPDecorator.EPISODESLISTWIDTH, PodcastBPDecorator.EPISODESLISTHEIGHT)
												.build();
		//-.-on
		//-.-off
		final VBox episodeVBox = VBoxBuilder.create()
											.bigText(SUBSCRIBEDEPISODES)
											.noListView(this.episodeListView)
											.topLeftAlignment()
											.build();
		//-.-on
		setMargin(episodeVBox, new Insets(Decorator.PADDING));
		setCenter(episodeVBox);
	}
}
