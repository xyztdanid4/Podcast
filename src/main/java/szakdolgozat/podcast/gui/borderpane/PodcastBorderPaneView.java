package szakdolgozat.podcast.gui.borderpane;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.ListView;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import szakdolgozat.podcast.builder.HBoxBuilder;
import szakdolgozat.podcast.builder.VBoxBuilder;
import szakdolgozat.podcast.data.podcast.Podcast;
import szakdolgozat.podcast.data.podcast.PodcastEpisode;
import szakdolgozat.podcast.gui.decorator.PodcastBPDecorator;
import szakdolgozat.podcast.gui.samples.ButtonSample;

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
	// private List<Podcast> podcastsFromDBList;
	// private ObservableList<HBox> podcastsContainer;
	private ListView<HBox> podcastListView;
	// private ObservableList<HBox> episodesContainer;
	private ListView<HBox> episodeListView;
	PodcastBoderPaneController podcastBoderPaneController;

	public PodcastBorderPaneView() {
		podcastBoderPaneController = new PodcastBoderPaneController();
		podcastBoderPaneController.readfromDB();
		PodcastBPDecorator.decorateFactory(this);
		setPadding();
		showPodcastEmptyInformation();
		showSubscribedPodcasts();
		showEmptyEpisodesList();
		setPodcastListInvalidationListener();
	}

	private void setPadding() {
		setPadding(new Insets(PodcastBPDecorator.PADDING, PodcastBPDecorator.PADDING, PodcastBPDecorator.PADDING,
				PodcastBPDecorator.PADDING));
	}

	private void showPodcastEmptyInformation() {
		setTop(HBoxBuilder.create().bigText(GETINFO).size(PodcastBPDecorator.PODCASTINFORMATIONCONTAINERWIDTH,
				PodcastBPDecorator.PODCASTINFORMATIONCONTAINERHEIGHT).build());
	}

	private void showSubscribedPodcasts() {
		// readfromDB();
		podcastBoderPaneController.readfromDB();
		if (!podcastBoderPaneController.getPodcastsFromDBList().isEmpty()) {
			ObservableList<HBox> podcastsContainer = FXCollections.observableArrayList();
			for (Podcast podcastIterator : podcastBoderPaneController.getPodcastsFromDBList()) {
				podcastsContainer.add(HBoxBuilder.create().smallRectangle(podcastIterator.getArtworkUrl60())
						.artist(podcastIterator.getArtistName()).button(new ButtonSample() {
							{
								setOnAction(new EventHandler<ActionEvent>() {
									@Override
									public void handle(ActionEvent event) {
										podcastBoderPaneController.removefromDB(podcastIterator.getArtistName());
										showPodcastEmptyInformation();
										showSubscribedPodcasts();
										showEmptyEpisodesList();
										setPodcastListInvalidationListener();
									}
								});
								setText(UNSUBSCRIBE);
								setTooltip(new Tooltip(CLICKFORUNSUBSCRIBE));
							}
						}).build());
			}
			// actionok miatt meg kell tartani a listviewt
			podcastListView = PodcastBPDecorator.decorateListViewFactory(new ListView<HBox>(podcastsContainer),
					PodcastBPDecorator.PODCASTLISTVIEWWIDTH, PodcastBPDecorator.PODCASTLISTVIEWHEIGHT);
			// setmargin miatt meg kell tartani a podcastlistvboxot is
			VBox podcastListVBox = new VBox(PodcastBPDecorator.VBOXPADDING,
					PodcastBPDecorator.decorateTextFactory(new Text(SUBSCRIBEDPODCAST), PodcastBPDecorator.BIGTEXTSIZE),
					podcastListView);
			setMargin(podcastListVBox, new Insets(PodcastBPDecorator.PADDING));
			// setAlignment(podcastListVBox, Pos.CENTER_LEFT);
			setLeft(podcastListVBox);
		} else {
			// szintén a setmargin miatt kel a podcastVbox is
			VBox podcastVBox = new VBox(PodcastBPDecorator.VBOXPADDING,
					PodcastBPDecorator.decorateTextFactory(new Text(SUBSCRIBEDPODCAST), PodcastBPDecorator.BIGTEXTSIZE),
					PodcastBPDecorator.decorateHBoxFactory(
							new HBox(PodcastBPDecorator.HBOXPADDING,
									PodcastBPDecorator.decorateTextFactory(new Text(NOSUBSCRIPTIONS),
											PodcastBPDecorator.SMALLTEXTSIZE)),
							PodcastBPDecorator.PODCASTLISTVIEWWIDTH, PodcastBPDecorator.NOSUBSCRIPTIONHBOXHEIGHT,
							PodcastBPDecorator.PODCASTLISTVIEWWIDTH, PodcastBPDecorator.NOSUBSCRIPTIONHBOXHEIGHT));

			setMargin(podcastVBox, new Insets(PodcastBPDecorator.PADDING));
			// setAlignment(podcastListVBox, Pos.CENTER_LEFT);
			setLeft(podcastVBox);
		}
	}

	private void setPodcastListInvalidationListener() {
		podcastListView.getSelectionModel().selectedIndexProperty().addListener((Observable o) -> {
			showPodcastInformation();
			showpodcastEpisodes();
		});
	}

	private void showPodcastInformation() {
		podcastBoderPaneController.readfromDB();
		int selectedindex = podcastListView.getSelectionModel().getSelectedIndex();
		setTop(HBoxBuilder.create()
				.size(PodcastBPDecorator.PODCASTINFORMATIONCONTAINERWIDTH,
						PodcastBPDecorator.PODCASTINFORMATIONCONTAINERHEIGHT)
				.vbox(VBoxBuilder.create()
						.bigRectangle(podcastBoderPaneController.getPodcastsFromDBList().get(selectedindex)
								.getArtworkUrl100())
						.build())
				.vbox(VBoxBuilder.create()
						.smallText(ARTISTLABEL
								+ podcastBoderPaneController.getPodcastsFromDBList().get(selectedindex).getArtistName())
						.smallText(COLLECTIONNAMELABEL + podcastBoderPaneController.getPodcastsFromDBList()
								.get(selectedindex).getCollectionName())
						.smallText(COUNTRYLABEL
								+ podcastBoderPaneController.getPodcastsFromDBList().get(selectedindex).getCountry())
						.build())
				.vbox(VBoxBuilder.create()
						.smallText(FEEDURLLABEL
								+ podcastBoderPaneController.getPodcastsFromDBList().get(selectedindex).getFeedUrl())
						.smallText(GENRELABEL + podcastBoderPaneController.getPodcastsFromDBList().get(selectedindex)
								.getPrimaryGenreName())
						.smallText(LASTRELEASEDATELABEL + podcastBoderPaneController.getPodcastsFromDBList()
								.get(selectedindex).getReleaseDate())
						.build())
				.build());
	}

	private void showEmptyEpisodesList() {
		VBox episodeVBox = VBoxBuilder.create().bigText(SUBSCRIBEDEPISODES).topLeftAlignment()
				.noHBox(HBoxBuilder.create().smallText(NOPODCASTSELECTED)
						.size(PodcastBPDecorator.EMPTYEPISODESLISTWIDTH, PodcastBPDecorator.EMPTYEPISODESLISTHEIGHT)
						.build())
				.build();
		setMargin(episodeVBox, new Insets(PodcastBPDecorator.PADDING));
		setCenter(episodeVBox);
	}

	private void showpodcastEpisodes() {
		podcastBoderPaneController.readfromDB();
		ObservableList<HBox> episodesContainer = FXCollections.observableArrayList();
		for (PodcastEpisode podcastEpisode : podcastBoderPaneController.getPodcastsFromDBList()
				.get(podcastListView.getSelectionModel().getSelectedIndex()).getPodcastEpisode()) {
			Image image = new Image(podcastEpisode.getImage());
			if (image.isError()) {
				// megtartjuk az objektumot, hiszen az actiont még hozzá kell
				// füzni
				HBox itemHbox = PodcastBPDecorator
						.decorateHBoxFactory(
								new HBox(PodcastBPDecorator.HBOXPADDING,
										PodcastBPDecorator
												.decorateImageViewFactory(new ImageView(image),
														PodcastBPDecorator.EPISODESIMAGEVIEWHEIGHT,
														PodcastBPDecorator.EPISODESIMAGEVIEWWIDTH),
										PodcastBPDecorator
												.decorateTextFactory(
														new Text(
																podcastEpisode.getTitle().length() > 40
																		? new String(new StringBuilder(podcastEpisode
																				.getTitle().substring(0, 40))
																						.append("..."))
																		: podcastEpisode.getTitle()),
														PodcastBPDecorator.SMALLTEXTSIZE),
										PodcastBPDecorator.decorateTextFactory(new Text(podcastEpisode.getPubdate()),
												PodcastBPDecorator.SMALLTEXTSIZE)));
				episodesContainer.add(itemHbox);
			} else {
				// megtartjuk az objektumot, hiszen az actiont még hozzá kell
				// füzni
				HBox itemHbox = new HBox(PodcastBPDecorator.HBOXPADDING,
						PodcastBPDecorator
								.decorateHelperVBoxFactory(
										new VBox(PodcastBPDecorator.decorateRectangleFactory(new Rectangle(),
												PodcastBPDecorator.EPISODESIMAGEVIEWHEIGHT,
												PodcastBPDecorator.EPISODESIMAGEVIEWWIDTH,
												podcastEpisode
														.getImage()))),
						PodcastBPDecorator.decorateTextFactory(new Text(podcastEpisode.getTitle().length() > 40
								? new String(
										new StringBuilder(podcastEpisode.getTitle().substring(0, 40)).append("..."))
								: podcastEpisode.getTitle()), PodcastBPDecorator.SMALLTEXTSIZE),
						PodcastBPDecorator.decorateTextFactory(new Text(podcastEpisode.getPubdate()),
								PodcastBPDecorator.SMALLTEXTSIZE));
				PodcastBPDecorator.decorateHBoxFactory(itemHbox);
				episodesContainer.add(itemHbox);
			}
		}
		episodeListView = PodcastBPDecorator.decorateListViewFactory(new ListView<HBox>(episodesContainer),
				PodcastBPDecorator.EPISODESLISTWIDTH, PodcastBPDecorator.EPISODESLISTHEIGHT);
		// marad mer setmargin megint
		VBox episodeVBox = new VBox(PodcastBPDecorator.VBOXPADDING,
				PodcastBPDecorator.decorateTextFactory(new Text(SUBSCRIBEDEPISODES), PodcastBPDecorator.BIGTEXTSIZE),
				episodeListView);
		setMargin(episodeVBox, new Insets(PodcastBPDecorator.PADDING));
		setCenter(episodeVBox);
	}

	private void setSubscribeButtonAction(ButtonSample button, Podcast podcast) {
		button.setOnAction((ActionEvent event) -> {
			podcastBoderPaneController.removefromDB(podcast.getArtistName());
			showPodcastEmptyInformation();
			showSubscribedPodcasts();
			showEmptyEpisodesList();
			setPodcastListInvalidationListener();
		});
	}
}
