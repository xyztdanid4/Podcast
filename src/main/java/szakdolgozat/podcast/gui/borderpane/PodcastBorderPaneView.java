package szakdolgozat.podcast.gui.borderpane;

import java.util.List;

import org.mongodb.morphia.query.Query;

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
import szakdolgozat.podcast.data.podcast.Podcast;
import szakdolgozat.podcast.data.podcast.PodcastEpisode;
import szakdolgozat.podcast.gui.decorator.PodcastBPDecorator;
import szakdolgozat.podcast.gui.samples.ButtonSample;
import szakdolgozat.podcast.morphia.MorphiaConnector;

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
	private List<Podcast> podcastsFromDBList;
	// private ObservableList<HBox> podcastsContainer;
	private ListView<HBox> podcastListView;
	// private ObservableList<HBox> episodesContainer;
	private ListView<HBox> episodeListView;

	public PodcastBorderPaneView() {
		readfromDB();
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

	private void readfromDB() {
		podcastsFromDBList = MorphiaConnector.getDataStore().createQuery(Podcast.class).asList();
	}

	private void showPodcastEmptyInformation() {
		setTop(PodcastBPDecorator
				.decorateHBoxFactory(
						new HBox(PodcastBPDecorator.HBOXPADDING,
								PodcastBPDecorator.decorateTextFactory(new Text(GETINFO),
										PodcastBPDecorator.BIGTEXTSIZE)),
				PodcastBPDecorator.PODCASTINFORMATIONCONTAINERWIDTH,
				PodcastBPDecorator.PODCASTINFORMATIONCONTAINERHEIGHT,
				PodcastBPDecorator.PODCASTINFORMATIONCONTAINERMAXWIDTH,
				PodcastBPDecorator.PODCASTINFORMATIONCONTAINERMAXHEIGHT));
	}

	private void showSubscribedPodcasts() {
		readfromDB();
		if (!podcastsFromDBList.isEmpty()) {
			ObservableList<HBox> podcastsContainer = FXCollections.observableArrayList();
			for (Podcast podcastIterator : podcastsFromDBList) {
				podcastsContainer.add(HBoxBuilder.create().image(podcastIterator.getArtworkUrl60())
						.artist(podcastIterator.getArtistName()).button(new ButtonSample() {
							{
								setOnAction(new EventHandler<ActionEvent>() {
									@Override
									public void handle(ActionEvent event) {
										removefromDB(podcastIterator.getArtistName());
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
		readfromDB();
		int selectedindex = podcastListView.getSelectionModel().getSelectedIndex();
		setTop(PodcastBPDecorator.decorateHBoxFactory(
				new HBox(PodcastBPDecorator.HBOXPADDING, PodcastBPDecorator
						.decorateHelperVBoxFactory(new VBox(PodcastBPDecorator.decorateRectangleFactory(new Rectangle(),
								PodcastBPDecorator.BIGRECTANGLEHEIGHT, PodcastBPDecorator.BIGRECTANGLEWIDTH,
								podcastsFromDBList.get(selectedindex).getArtworkUrl100()))),
						PodcastBPDecorator
								.decorateHelperVBoxFactory(
										new VBox(PodcastBPDecorator.HELPERVBOXPADDING,
												PodcastBPDecorator.decorateTextFactory(
														new Text(new String(ARTISTLABEL + podcastsFromDBList
																.get(selectedindex).getArtistName())),
														PodcastBPDecorator.SMALLTEXTSIZE),
												PodcastBPDecorator.decorateTextFactory(
														new Text(new String(COLLECTIONNAMELABEL + podcastsFromDBList
																.get(selectedindex).getCollectionName())),
														PodcastBPDecorator.SMALLTEXTSIZE),
												PodcastBPDecorator
														.decorateTextFactory(
																new Text(new String(COUNTRYLABEL + podcastsFromDBList
																		.get(selectedindex).getCountry())),
														PodcastBPDecorator.SMALLTEXTSIZE))),
				PodcastBPDecorator
						.decorateHelperVBoxFactory(
								new VBox(PodcastBPDecorator.HELPERVBOXPADDING,
										PodcastBPDecorator.decorateTextFactory(
												new Text(new String(FEEDURLLABEL
														+ podcastsFromDBList.get(selectedindex).getFeedUrl())),
								PodcastBPDecorator.SMALLTEXTSIZE), PodcastBPDecorator.decorateTextFactory(
										new Text(new String(GENRELABEL
												+ podcastsFromDBList.get(selectedindex).getPrimaryGenreName())),
								PodcastBPDecorator.SMALLTEXTSIZE), PodcastBPDecorator.decorateTextFactory(
										new Text(new String(LASTRELEASEDATELABEL
												+ podcastsFromDBList.get(selectedindex).getReleaseDate())),
								PodcastBPDecorator.SMALLTEXTSIZE), PodcastBPDecorator.decorateTextFactory(
										new Text(new String(TRACKCOUNTLABEL
												+ podcastsFromDBList.get(selectedindex).getTrackCount())),
										PodcastBPDecorator.SMALLTEXTSIZE)))),
				PodcastBPDecorator.PODCASTINFORMATIONCONTAINERWIDTH,
				PodcastBPDecorator.PODCASTINFORMATIONCONTAINERHEIGHT,
				PodcastBPDecorator.PODCASTINFORMATIONCONTAINERMAXWIDTH,
				PodcastBPDecorator.PODCASTINFORMATIONCONTAINERMAXHEIGHT));
	}

	private void showEmptyEpisodesList() {
		// setmargin miatt kell az epoisodesVBox
		VBox episodeVBox = new VBox(PodcastBPDecorator.VBOXPADDING,
				PodcastBPDecorator.decorateTextFactory(new Text(SUBSCRIBEDEPISODES), PodcastBPDecorator.BIGTEXTSIZE),
				PodcastBPDecorator.decorateHBoxFactory(
						new HBox(PodcastBPDecorator.HBOXPADDING,
								PodcastBPDecorator.decorateTextFactory(new Text(NOPODCASTSELECTED),
										PodcastBPDecorator.SMALLTEXTSIZE)),
						PodcastBPDecorator.EMPTYEPISODESLISTWIDTH, PodcastBPDecorator.EMPTYEPISODESLISTHEIGHT,
						PodcastBPDecorator.EMPTYEPISODESLISTWIDTH, PodcastBPDecorator.EMPTYEPISODESLISTHEIGHT));
		setMargin(episodeVBox, new Insets(PodcastBPDecorator.PADDING));
		setCenter(episodeVBox);
	}

	private void showpodcastEpisodes() {
		readfromDB();
		ObservableList<HBox> episodesContainer = FXCollections.observableArrayList();
		for (PodcastEpisode podcastEpisode : podcastsFromDBList
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

	private void removefromDB(final String name) {
		final Query<Podcast> deletePodcast = MorphiaConnector.getDataStore().createQuery(Podcast.class)
				.filter("artistName =", name);
		MorphiaConnector.getDataStore().delete(deletePodcast);
	}

	private void setSubscribeButtonAction(ButtonSample button, Podcast podcast) {
		button.setOnAction((ActionEvent event) -> {
			removefromDB(podcast.getArtistName());
			showPodcastEmptyInformation();
			showSubscribedPodcasts();
			showEmptyEpisodesList();
			setPodcastListInvalidationListener();
		});
	}
}
