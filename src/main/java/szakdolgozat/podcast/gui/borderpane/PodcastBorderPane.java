package szakdolgozat.podcast.gui.borderpane;

import java.util.List;

import org.mongodb.morphia.query.Query;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import szakdolgozat.podcast.data.podcast.Podcast;
import szakdolgozat.podcast.data.podcast.PodcastEpisode;
import szakdolgozat.podcast.gui.samples.ButtonSample;
import szakdolgozat.podcast.gui.samples.ListViewSample;
import szakdolgozat.podcast.morphia.MorphiaConnector;

public class PodcastBorderPane extends BorderPane {
	private static final String NOSUBSCRIPTIONS = "You have no subscriptions yet!";
	private static final String GETINFO = "Choose from subscribed podcast to get information!";
	private static final String SUBSCRIBEDPODCAST = "Subscribed Podcasts";
	private static final String UNSUBSCRIBE = "Unsubscribe!";
	private static final String CLICKFORUNSUBSCRIBE = "Click for unsubscribe!";
	private static final String NOPODCASTSELECTED = "Choose from subscribed podcasts!";
	private static final String SUBSCRIBEDEPISODES = "Subscribed Episodes";
	private static final int PODCASTINFORMATIONCONTAINERWIDTH = 800;
	private static final int PODCASTINFORMATIONCONTAINERHEIGHT = 100;
	private static final int PODCASTINFORMATIONCONTAINERMAXWIDTH = 800;
	private static final int PODCASTINFORMATIONCONTAINERMAXHEIGHT = 100;
	private static final int BIGTEXTSIZE = 16;
	private static final int SMALLTEXTSIZE = 13;
	private static final int DEFAULTPADDING = 20;
	private static final int DEFAULTHBOXPADDING = 10;
	private static final int DEFAULTBORDERSIZE = 3;
	private static final int DEFAULTPODCASTLISTVIEWWIDTH = 300;
	private static final int DEFAULTPODCASTLISTVIEHEIGHT = 300;
	private static final int RECTANGLEARCHWIDHT = 10;
	private static final int RECTANGLEARCHHEIGHT = 10;
	private static final int SMALLRECTANGLEHEIGHT = 40;
	private static final int SMALLRECTANGLEWIDTH = 40;
	private static final int BIGRECTANGLEHEIGHT = 90;
	private static final int BIGRECTANGLEWIDTH = 90;
	private static final int DEFAULTCORNERRADIUS = 3;
	private static final int LISTVIEWINSETS = 0;
	private static final int BORDERPANEBORDERRADIUS = 0;
	private static final String TEXTCOLOR = "#FFFFFF";
	private static final String BORDERCOLOR = "#006666";
	private static final String ITEMBACKGROUNDCOLOR = "#808080";
	private static final String BACKGROUNDCOLOR = "#191919";
	private static final int DEFAULTVBOXPADDING = 10;
	private static final int DEFAULTNOSUBSCRIPTIONHBOXHEIGHT = 50;
	private static final int DEFAULTMARGINFORELEMENTS = 20;
	private static final String ARTISTLABEL = "Artist name: ";
	private static final String COLLECTIONNAMELABEL = "Title: ";
	private static final String COUNTRYLABEL = "Country: ";
	private static final String FEEDURLLABEL = "Feed url: ";
	private static final String GENRELABEL = "Genre: ";
	private static final String LASTRELEASEDATELABEL = "Last Updated: ";
	private static final String TRACKCOUNTLABEL = "Number of episodes: ";
	private static final int HELPERVBOXPADDING = 5;
	private static final int EPISODESLISTWIDTH = 500;
	private static final int EPISODESLISTHEIGHT = 300;
	private static final int EMPTYEPISODESLISTWIDTH = 500;
	private static final int EMPTYEPISODESLISTHEIGHT = 50;
	private static final int EPISODESIMAGEVIEWHEIGHT = 30;
	private static final int EPISODESIMAGEVIEWWIDTH = 30;

	private List<Podcast> podcastsFromDBList;
	private VBox podcastListVBox;
	private ObservableList<HBox> podcastsContainer;
	private ListViewSample podcastListView;
	private ObservableList<HBox> episodesContainer;
	private ListView<HBox> episodeListView;

	public PodcastBorderPane() {
		readfromDB();
		decorate();
		setPadding();
		showPodcastEmptyInformation();
		showSubscribedPodcasts();
		showEmptyEpisodesList();
		setPodcastListInvalidationListener();
	}

	private void setPadding() {
		setPadding(new Insets(DEFAULTPADDING, DEFAULTPADDING, DEFAULTPADDING, DEFAULTPADDING));
	}

	private void readfromDB() {
		podcastsFromDBList = MorphiaConnector.getDataStore().createQuery(Podcast.class).asList();
	}

	private void showPodcastEmptyInformation() {
		Text infoText = new Text(GETINFO);
		decorateText(infoText, BIGTEXTSIZE);
		HBox podcastInformationContainer = new HBox(DEFAULTHBOXPADDING, infoText);
		decorateHBox(podcastInformationContainer, PODCASTINFORMATIONCONTAINERWIDTH, PODCASTINFORMATIONCONTAINERHEIGHT,
				PODCASTINFORMATIONCONTAINERMAXWIDTH, PODCASTINFORMATIONCONTAINERMAXHEIGHT);
		setTop(podcastInformationContainer);
	}

	private void showSubscribedPodcasts() {
		readfromDB();
		Text podcastText = new Text(SUBSCRIBEDPODCAST);
		decorateText(podcastText, BIGTEXTSIZE);
		podcastsContainer = FXCollections.observableArrayList();
		podcastListView = new ListViewSample();
		decorateListView(podcastListView, DEFAULTPODCASTLISTVIEWWIDTH, DEFAULTPODCASTLISTVIEHEIGHT);
		for (Podcast podcastIterator : podcastsFromDBList) {
			Rectangle imageRectangle = new Rectangle();
			decorateRectangle(imageRectangle, SMALLRECTANGLEHEIGHT, SMALLRECTANGLEWIDTH,
					podcastIterator.getArtworkUrl60());
			Text name = new Text(new String(podcastIterator.getArtistName()));
			decorateText(name, SMALLTEXTSIZE);
			ButtonSample subscribeButton = new ButtonSample(UNSUBSCRIBE, CLICKFORUNSUBSCRIBE);
			setSubscribeButtonAction(subscribeButton, podcastIterator);
			HBox itemHbox = new HBox(DEFAULTHBOXPADDING, imageRectangle, name, subscribeButton);
			decorateHBox(itemHbox);
			podcastsContainer.add(itemHbox);
			podcastListView.setItems(podcastsContainer);
			podcastListVBox = new VBox(DEFAULTVBOXPADDING, podcastText, podcastListView);
			setMargin(podcastListVBox);
			// setAlignment(podcastListVBox);
			setLeft(podcastListVBox);
		}
		if (!isEmptySubscriptions()) {
			podcastListView.setDisable(false);
		} else {
			Text nosubscriptionText = new Text(NOSUBSCRIPTIONS);
			decorateText(nosubscriptionText, SMALLTEXTSIZE);
			HBox itemHbox = new HBox(DEFAULTHBOXPADDING, nosubscriptionText);
			decorateHBox(itemHbox, DEFAULTPODCASTLISTVIEWWIDTH, DEFAULTNOSUBSCRIPTIONHBOXHEIGHT,
					DEFAULTPODCASTLISTVIEWWIDTH, DEFAULTNOSUBSCRIPTIONHBOXHEIGHT);
			VBox podcastHBox = new VBox(DEFAULTVBOXPADDING, podcastText, itemHbox);
			setMargin(podcastHBox);
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
		decorateText(artistLabel, SMALLTEXTSIZE);
		Text collectionNameLabel = new Text(new String(COLLECTIONNAMELABEL
				+ podcastsFromDBList.get(podcastListView.getSelectionModel().getSelectedIndex()).getCollectionName()));
		decorateText(collectionNameLabel, SMALLTEXTSIZE);
		Text countryLabel = new Text(new String(COUNTRYLABEL
				+ podcastsFromDBList.get(podcastListView.getSelectionModel().getSelectedIndex()).getCountry()));
		decorateText(countryLabel, SMALLTEXTSIZE);
		Text feedUrlLabel = new Text(new String(FEEDURLLABEL
				+ podcastsFromDBList.get(podcastListView.getSelectionModel().getSelectedIndex()).getFeedUrl()));
		decorateText(feedUrlLabel, SMALLTEXTSIZE);
		Text genreLabel = new Text(new String(GENRELABEL + podcastsFromDBList
				.get(podcastListView.getSelectionModel().getSelectedIndex()).getPrimaryGenreName()));
		decorateText(genreLabel, SMALLTEXTSIZE);
		Text lastReleaseDateLabel = new Text(new String(LASTRELEASEDATELABEL
				+ podcastsFromDBList.get(podcastListView.getSelectionModel().getSelectedIndex()).getReleaseDate()));
		decorateText(lastReleaseDateLabel, SMALLTEXTSIZE);
		Text trackCountLabel = new Text(new String(TRACKCOUNTLABEL
				+ podcastsFromDBList.get(podcastListView.getSelectionModel().getSelectedIndex()).getTrackCount()));
		decorateText(trackCountLabel, SMALLTEXTSIZE);
		Rectangle imageRectangle = new Rectangle();
		decorateRectangle(imageRectangle, BIGRECTANGLEHEIGHT, BIGRECTANGLEWIDTH,
				podcastsFromDBList.get(podcastListView.getSelectionModel().getSelectedIndex()).getArtworkUrl100());
		VBox helperVBox3 = new VBox(imageRectangle);
		decorateHelperVBox(helperVBox3);
		VBox helperVBox1 = new VBox(HELPERVBOXPADDING, artistLabel, collectionNameLabel, countryLabel);
		decorateHelperVBox(helperVBox1);
		VBox helperVBox2 = new VBox(HELPERVBOXPADDING, feedUrlLabel, genreLabel, lastReleaseDateLabel, trackCountLabel);
		decorateHelperVBox(helperVBox2);
		HBox podcastInformationContainer = new HBox(DEFAULTHBOXPADDING, helperVBox3, helperVBox1, helperVBox2);
		decorateHBox(podcastInformationContainer, PODCASTINFORMATIONCONTAINERWIDTH, PODCASTINFORMATIONCONTAINERHEIGHT,
				PODCASTINFORMATIONCONTAINERMAXWIDTH, PODCASTINFORMATIONCONTAINERMAXHEIGHT);
		setTop(podcastInformationContainer);
	}

	private void showEmptyEpisodesList() {
		Text noPodcastSelectedText = new Text(NOPODCASTSELECTED);
		decorateText(noPodcastSelectedText, SMALLTEXTSIZE);
		Text episodeListText = new Text(SUBSCRIBEDEPISODES);
		decorateText(episodeListText, BIGTEXTSIZE);
		HBox itemHbox = new HBox(DEFAULTHBOXPADDING, noPodcastSelectedText);
		decorateHBox(itemHbox, EMPTYEPISODESLISTWIDTH, EMPTYEPISODESLISTHEIGHT, EMPTYEPISODESLISTWIDTH,
				EMPTYEPISODESLISTHEIGHT);
		VBox episodeVBox = new VBox(DEFAULTVBOXPADDING, episodeListText, itemHbox);
		setMargin(episodeVBox);
		setCenter(episodeVBox);
	}

	private void showpodcastEpisodes() {
		readfromDB();
		Text episodeListText = new Text(SUBSCRIBEDEPISODES);
		decorateText(episodeListText, BIGTEXTSIZE);
		episodesContainer = FXCollections.observableArrayList();
		for (PodcastEpisode podcastEpisode : podcastsFromDBList
				.get(podcastListView.getSelectionModel().getSelectedIndex()).getPodcastEpisode()) {
			Text pubDateText = new Text(podcastEpisode.getPubdate());
			decorateText(pubDateText, SMALLTEXTSIZE);
			Text titleText = new Text(podcastEpisode.getTitle());
			decorateText(titleText, SMALLTEXTSIZE);
			Image image = new Image(podcastEpisode.getImage());
			if (image.isError()) {
				ImageView imageView = new ImageView(image);
				decorateImageView(imageView, EPISODESIMAGEVIEWHEIGHT, EPISODESIMAGEVIEWWIDTH);
				HBox itemHbox = new HBox(DEFAULTHBOXPADDING, imageView, titleText, pubDateText);
				decorateHBox(itemHbox);
				episodesContainer.add(itemHbox);
			} else {
				Rectangle imageRectangle = new Rectangle();
				decorateRectangle(imageRectangle, EPISODESIMAGEVIEWHEIGHT, EPISODESIMAGEVIEWWIDTH,
						podcastEpisode.getImage());
				VBox imageVbox = new VBox(imageRectangle);
				imageVbox.setAlignment(Pos.CENTER_LEFT);
				HBox itemHbox = new HBox(DEFAULTHBOXPADDING, imageVbox, titleText, pubDateText);
				decorateHBox(itemHbox);
				episodesContainer.add(itemHbox);
			}
		}
		episodeListView = new ListView<HBox>(episodesContainer);
		decorateListView(episodeListView, EPISODESLISTWIDTH, EPISODESLISTHEIGHT);
		VBox episodeVBox = new VBox(DEFAULTVBOXPADDING, episodeListText, episodeListView);
		setMargin(episodeVBox, new Insets(20));
		setCenter(episodeVBox);
	}

	private void setMouseEnteredEventHBox(HBox itemHbox) {
		itemHbox.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				itemHbox.setBackground(new Background(new BackgroundFill(Color.web(BACKGROUNDCOLOR),
						new CornerRadii(DEFAULTCORNERRADIUS), Insets.EMPTY)));
			}
		});
	}

	private void setMouseExitedEventHBox(HBox itemHbox) {
		itemHbox.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				itemHbox.setBackground(new Background(new BackgroundFill(Color.web(ITEMBACKGROUNDCOLOR),
						new CornerRadii(DEFAULTCORNERRADIUS), Insets.EMPTY)));
			}
		});
	}

	private void removefromDB(final String name) {
		final Query<Podcast> deletePodcast = MorphiaConnector.getDataStore().createQuery(Podcast.class)
				.filter("artistName =", name);
		MorphiaConnector.getDataStore().delete(deletePodcast);
	}

	private void decorate() {
		setBackground(new Background(
				new BackgroundFill(Color.web(BACKGROUNDCOLOR), new CornerRadii(BORDERPANEBORDERRADIUS), Insets.EMPTY)));
		setBorder(new Border(new BorderStroke(Color.web(BORDERCOLOR), BorderStrokeStyle.SOLID,
				new CornerRadii(BORDERPANEBORDERRADIUS),
				new BorderWidths(DEFAULTBORDERSIZE, DEFAULTBORDERSIZE, 0, DEFAULTBORDERSIZE))));
	}

	private void decorateText(Text text, final int size) {
		text.setFill(Color.web(TEXTCOLOR));
		text.setFont(Font.font("Arial", FontWeight.BOLD, size));
	}

	private void decorateHBox(HBox hbox, final int prefwidth, final int prefheight, final int maxwidht,
			final int maxheight) {
		hbox.setPrefSize(prefwidth, prefheight);
		hbox.setMaxSize(maxwidht, maxheight);
		hbox.setBackground(new Background(new BackgroundFill(Color.web(ITEMBACKGROUNDCOLOR),
				new CornerRadii(DEFAULTCORNERRADIUS), Insets.EMPTY)));
		hbox.setBorder(new Border(new BorderStroke(Color.web(BORDERCOLOR), BorderStrokeStyle.SOLID,
				new CornerRadii(DEFAULTCORNERRADIUS), new BorderWidths(DEFAULTBORDERSIZE))));
		hbox.setAlignment(Pos.CENTER_LEFT);
	}

	private void decorateHBox(HBox hbox) {
		hbox.setBackground(new Background(new BackgroundFill(Color.web(ITEMBACKGROUNDCOLOR),
				new CornerRadii(DEFAULTCORNERRADIUS), Insets.EMPTY)));
		hbox.setBorder(new Border(new BorderStroke(Color.web(BORDERCOLOR), BorderStrokeStyle.SOLID,
				new CornerRadii(DEFAULTCORNERRADIUS), new BorderWidths(DEFAULTBORDERSIZE))));
		hbox.setAlignment(Pos.CENTER_LEFT);
		setMouseEnteredEventHBox(hbox);
		setMouseExitedEventHBox(hbox);
	}

	private void decorateListView(ListView listView, final int width, final int height) {
		listView.setPrefSize(width, height);
		listView.setMaxSize(width, height);
		listView.setBorder(new Border(new BorderStroke(Color.web(BORDERCOLOR), BorderStrokeStyle.SOLID,
				new CornerRadii(DEFAULTCORNERRADIUS), new BorderWidths(DEFAULTBORDERSIZE))));
		listView.setBackground(new Background(new BackgroundFill(Color.web(ITEMBACKGROUNDCOLOR),
				new CornerRadii(DEFAULTCORNERRADIUS), Insets.EMPTY)));
		listView.setPadding(new Insets(LISTVIEWINSETS));
	}

	private void decorateRectangle(Rectangle rectangle, final int height, final int width, String imageURL) {
		rectangle.setArcHeight(RECTANGLEARCHHEIGHT);
		rectangle.setArcWidth(RECTANGLEARCHWIDHT);
		rectangle.setHeight(height);
		rectangle.setWidth(width);
		rectangle.setFill(new ImagePattern(new Image(imageURL)));
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

	private void setMargin(Node node) {
		setMargin(node, new Insets(DEFAULTMARGINFORELEMENTS)); // ezzel tudom
		// beállítani a
		// távolságot az elmeek
		// közt
	}

	private void setAlignment(Node node) {
		setAlignment(node, Pos.CENTER_LEFT); // igazodás elemen belül
	}

	private void decorateHelperVBox(VBox vBox) {
		vBox.setAlignment(Pos.CENTER_LEFT);
	}

	private void decorateImageView(ImageView imageView, final int height, final int width) {
		imageView.setFitWidth(width);
		imageView.setFitHeight(height);
	}
}
