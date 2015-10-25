package szakdolgozat.podcast.gui.borderpane;

import java.util.List;

import org.mongodb.morphia.query.Query;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import szakdolgozat.podcast.data.podcast.Podcast;
import szakdolgozat.podcast.data.podcast.PodcastEpisode;
import szakdolgozat.podcast.gui.decorator.PodcastBPDecorator;
import szakdolgozat.podcast.gui.samples.ButtonSample;
import szakdolgozat.podcast.gui.samples.ListViewSample;
import szakdolgozat.podcast.morphia.MorphiaConnector;

public class PodcastBorderPane extends BorderPane {
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
	private VBox podcastListVBox;
	private ObservableList<HBox> podcastsContainer;
	private ListViewSample podcastListView;
	private ObservableList<HBox> episodesContainer;
	private ListView<HBox> episodeListView;

	public PodcastBorderPane() {
		readfromDB();
		PodcastBPDecorator.decorate(this);
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
		Text infoText = new Text(GETINFO);
		PodcastBPDecorator.decorateText(infoText, PodcastBPDecorator.BIGTEXTSIZE);
		HBox podcastInformationContainer = new HBox(PodcastBPDecorator.HBOXPADDING, infoText);
		PodcastBPDecorator.decorateHBox(podcastInformationContainer,
				PodcastBPDecorator.PODCASTINFORMATIONCONTAINERWIDTH,
				PodcastBPDecorator.PODCASTINFORMATIONCONTAINERHEIGHT,
				PodcastBPDecorator.PODCASTINFORMATIONCONTAINERMAXWIDTH,
				PodcastBPDecorator.PODCASTINFORMATIONCONTAINERMAXHEIGHT);
		setTop(podcastInformationContainer);
	}

	private void showSubscribedPodcasts() {
		readfromDB();
		Text podcastText = new Text(SUBSCRIBEDPODCAST);
		PodcastBPDecorator.decorateText(podcastText, PodcastBPDecorator.BIGTEXTSIZE);
		podcastsContainer = FXCollections.observableArrayList();
		podcastListView = new ListViewSample();
		PodcastBPDecorator.decorateListView(podcastListView, PodcastBPDecorator.PODCASTLISTVIEWWIDTH,
				PodcastBPDecorator.PODCASTLISTVIEWHEIGHT);
		for (Podcast podcastIterator : podcastsFromDBList) {
			Rectangle imageRectangle = new Rectangle();
			PodcastBPDecorator.decorateRectangle(imageRectangle, PodcastBPDecorator.SMALLRECTANGLEHEIGHT,
					PodcastBPDecorator.SMALLRECTANGLEWIDTH, podcastIterator.getArtworkUrl60());
			Text name = new Text(new String(podcastIterator.getArtistName()));
			PodcastBPDecorator.decorateText(name, PodcastBPDecorator.SMALLTEXTSIZE);
			ButtonSample subscribeButton = new ButtonSample(UNSUBSCRIBE, CLICKFORUNSUBSCRIBE);
			PodcastBPDecorator.decorateButton(subscribeButton);
			setSubscribeButtonAction(subscribeButton, podcastIterator);
			HBox itemHbox = new HBox(PodcastBPDecorator.HBOXPADDING, imageRectangle, name, subscribeButton);
			PodcastBPDecorator.decorateHBox(itemHbox);
			podcastsContainer.add(itemHbox);
			podcastListView.setItems(podcastsContainer);
			podcastListVBox = new VBox(PodcastBPDecorator.DEFAULTVBOXPADDING, podcastText, podcastListView);
			setMargin(podcastListVBox, new Insets(PodcastBPDecorator.PADDING));
			// setAlignment(podcastListVBox, Pos.CENTER_LEFT);
			setLeft(podcastListVBox);
		}
		if (!isEmptySubscriptions()) {
			podcastListView.setDisable(false);
		} else {
			Text nosubscriptionText = new Text(NOSUBSCRIPTIONS);
			PodcastBPDecorator.decorateText(nosubscriptionText, PodcastBPDecorator.SMALLTEXTSIZE);
			HBox itemHbox = new HBox(PodcastBPDecorator.HBOXPADDING, nosubscriptionText);
			PodcastBPDecorator.decorateHBox(itemHbox, PodcastBPDecorator.PODCASTLISTVIEWWIDTH,
					PodcastBPDecorator.NOSUBSCRIPTIONHBOXHEIGHT, PodcastBPDecorator.PODCASTLISTVIEWWIDTH,
					PodcastBPDecorator.NOSUBSCRIPTIONHBOXHEIGHT);
			VBox podcastHBox = new VBox(PodcastBPDecorator.DEFAULTVBOXPADDING, podcastText, itemHbox);
			setMargin(podcastHBox, new Insets(PodcastBPDecorator.PADDING));
			// setAlignment(podcastListVBox, Pos.CENTER_LEFT);
			setLeft(podcastHBox);
		}
	}

	boolean isEmptySubscriptions() {
		return podcastsContainer.isEmpty();
	}

	private void setPodcastListInvalidationListener() {
		podcastListView.getSelectionModel().selectedIndexProperty().addListener((Observable o) -> {
			showPodcastInformation();
			showpodcastEpisodes();
		});
	}

	private void showPodcastInformation() {
		readfromDB();
		Text artistLabel = new Text(new String(ARTISTLABEL
				+ podcastsFromDBList.get(podcastListView.getSelectionModel().getSelectedIndex()).getArtistName()));
		PodcastBPDecorator.decorateText(artistLabel, PodcastBPDecorator.SMALLTEXTSIZE);
		Text collectionNameLabel = new Text(new String(COLLECTIONNAMELABEL
				+ podcastsFromDBList.get(podcastListView.getSelectionModel().getSelectedIndex()).getCollectionName()));
		PodcastBPDecorator.decorateText(collectionNameLabel, PodcastBPDecorator.SMALLTEXTSIZE);
		Text countryLabel = new Text(new String(COUNTRYLABEL
				+ podcastsFromDBList.get(podcastListView.getSelectionModel().getSelectedIndex()).getCountry()));
		PodcastBPDecorator.decorateText(countryLabel, PodcastBPDecorator.SMALLTEXTSIZE);
		Text feedUrlLabel = new Text(new String(FEEDURLLABEL
				+ podcastsFromDBList.get(podcastListView.getSelectionModel().getSelectedIndex()).getFeedUrl()));
		PodcastBPDecorator.decorateText(feedUrlLabel, PodcastBPDecorator.SMALLTEXTSIZE);
		Text genreLabel = new Text(new String(GENRELABEL + podcastsFromDBList
				.get(podcastListView.getSelectionModel().getSelectedIndex()).getPrimaryGenreName()));
		PodcastBPDecorator.decorateText(genreLabel, PodcastBPDecorator.SMALLTEXTSIZE);
		Text lastReleaseDateLabel = new Text(new String(LASTRELEASEDATELABEL
				+ podcastsFromDBList.get(podcastListView.getSelectionModel().getSelectedIndex()).getReleaseDate()));
		PodcastBPDecorator.decorateText(lastReleaseDateLabel, PodcastBPDecorator.SMALLTEXTSIZE);
		Text trackCountLabel = new Text(new String(TRACKCOUNTLABEL
				+ podcastsFromDBList.get(podcastListView.getSelectionModel().getSelectedIndex()).getTrackCount()));
		PodcastBPDecorator.decorateText(trackCountLabel, PodcastBPDecorator.SMALLTEXTSIZE);
		Rectangle imageRectangle = new Rectangle();
		PodcastBPDecorator.decorateRectangle(imageRectangle, PodcastBPDecorator.BIGRECTANGLEHEIGHT,
				PodcastBPDecorator.BIGRECTANGLEWIDTH,
				podcastsFromDBList.get(podcastListView.getSelectionModel().getSelectedIndex()).getArtworkUrl100());
		VBox helperVBox3 = new VBox(imageRectangle);
		PodcastBPDecorator.decorateHelperVBox(helperVBox3);
		VBox helperVBox1 = new VBox(PodcastBPDecorator.HELPERVBOXPADDING, artistLabel, collectionNameLabel,
				countryLabel);
		PodcastBPDecorator.decorateHelperVBox(helperVBox1);
		VBox helperVBox2 = new VBox(PodcastBPDecorator.HELPERVBOXPADDING, feedUrlLabel, genreLabel,
				lastReleaseDateLabel, trackCountLabel);
		PodcastBPDecorator.decorateHelperVBox(helperVBox2);
		HBox podcastInformationContainer = new HBox(PodcastBPDecorator.HBOXPADDING, helperVBox3, helperVBox1,
				helperVBox2);
		PodcastBPDecorator.decorateHBox(podcastInformationContainer,
				PodcastBPDecorator.PODCASTINFORMATIONCONTAINERWIDTH,
				PodcastBPDecorator.PODCASTINFORMATIONCONTAINERHEIGHT,
				PodcastBPDecorator.PODCASTINFORMATIONCONTAINERMAXWIDTH,
				PodcastBPDecorator.PODCASTINFORMATIONCONTAINERMAXHEIGHT);
		setTop(podcastInformationContainer);
	}

	private void showEmptyEpisodesList() {
		Text noPodcastSelectedText = new Text(NOPODCASTSELECTED);
		PodcastBPDecorator.decorateText(noPodcastSelectedText, PodcastBPDecorator.SMALLTEXTSIZE);
		Text episodeListText = new Text(SUBSCRIBEDEPISODES);
		PodcastBPDecorator.decorateText(episodeListText, PodcastBPDecorator.BIGTEXTSIZE);
		HBox itemHbox = new HBox(PodcastBPDecorator.HBOXPADDING, noPodcastSelectedText);
		PodcastBPDecorator.decorateHBox(itemHbox, PodcastBPDecorator.EMPTYEPISODESLISTWIDTH,
				PodcastBPDecorator.EMPTYEPISODESLISTHEIGHT, PodcastBPDecorator.EMPTYEPISODESLISTWIDTH,
				PodcastBPDecorator.EMPTYEPISODESLISTHEIGHT);
		VBox episodeVBox = new VBox(PodcastBPDecorator.DEFAULTVBOXPADDING, episodeListText, itemHbox);
		setMargin(episodeVBox, new Insets(PodcastBPDecorator.PADDING));
		setCenter(episodeVBox);
	}

	private void showpodcastEpisodes() {
		readfromDB();
		Text episodeListText = new Text(SUBSCRIBEDEPISODES);
		PodcastBPDecorator.decorateText(episodeListText, PodcastBPDecorator.BIGTEXTSIZE);
		episodesContainer = FXCollections.observableArrayList();
		for (PodcastEpisode podcastEpisode : podcastsFromDBList
				.get(podcastListView.getSelectionModel().getSelectedIndex()).getPodcastEpisode()) {
			Text pubDateText = new Text(podcastEpisode.getPubdate());
			PodcastBPDecorator.decorateText(pubDateText, PodcastBPDecorator.SMALLTEXTSIZE);
			Text titleText = new Text(podcastEpisode.getTitle());
			PodcastBPDecorator.decorateText(titleText, PodcastBPDecorator.SMALLTEXTSIZE);
			Image image = new Image(podcastEpisode.getImage());
			if (image.isError()) {
				ImageView imageView = new ImageView(image);
				PodcastBPDecorator.decorateImageView(imageView, PodcastBPDecorator.EPISODESIMAGEVIEWHEIGHT,
						PodcastBPDecorator.EPISODESIMAGEVIEWWIDTH);
				HBox itemHbox = new HBox(PodcastBPDecorator.HBOXPADDING, imageView, titleText, pubDateText);
				PodcastBPDecorator.decorateHBox(itemHbox);
				episodesContainer.add(itemHbox);
			} else {
				Rectangle imageRectangle = new Rectangle();
				PodcastBPDecorator.decorateRectangle(imageRectangle, PodcastBPDecorator.EPISODESIMAGEVIEWHEIGHT,
						PodcastBPDecorator.EPISODESIMAGEVIEWWIDTH, podcastEpisode.getImage());
				VBox imageVbox = new VBox(imageRectangle);
				PodcastBPDecorator.decorateHelperVBox(imageVbox);
				HBox itemHbox = new HBox(PodcastBPDecorator.HBOXPADDING, imageVbox, titleText, pubDateText);
				PodcastBPDecorator.decorateHBox(itemHbox);
				episodesContainer.add(itemHbox);
			}
		}
		episodeListView = new ListView<HBox>(episodesContainer);
		PodcastBPDecorator.decorateListView(episodeListView, PodcastBPDecorator.EPISODESLISTWIDTH,
				PodcastBPDecorator.EPISODESLISTHEIGHT);
		VBox episodeVBox = new VBox(PodcastBPDecorator.DEFAULTVBOXPADDING, episodeListText, episodeListView);
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
