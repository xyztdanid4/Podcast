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
	private List<Podcast> podcastsFromDBList;
	private VBox podcastListVBox;
	// private VBox episodesListVBox;
	private ObservableList<HBox> podcastsContainer;
	private ListViewSample podcastListView;
	private ObservableList<HBox> episodesContainer;
	private ListView episodeListView;
	private HBox podcastInformationContainer;
	// private ListViewSample podcastInformationListView;
	// private ObservableList<HBox> podcastInformationHbox;
	private List<PodcastEpisode> selectedPodcastEpisodes;

	public PodcastBorderPane() {
		readfromDB();
		setBackground(new Background(new BackgroundFill(Color.web("#191919"), new CornerRadii(3), Insets.EMPTY)));
		setBorder(new Border(new BorderStroke(Color.web("#006666"), BorderStrokeStyle.SOLID, new CornerRadii(3),
				new BorderWidths(3))));
		setPaddingForElements();
		showPodcastEmptyInformation();
		showSubscribedPodcasts();
		showEmptyEpisodesList();
		setPodcastListInvalidationListener();
	}

	private void setPaddingForElements() {
		setPadding(new Insets(20, 20, 20, 20));
	}

	private void readfromDB() {
		podcastsFromDBList = MorphiaConnector.getDataStore().createQuery(Podcast.class).asList();
	}

	private void showPodcastEmptyInformation() {
		Text text = new Text(GETINFO);
		text.setFont(Font.font("Arial", FontWeight.BOLD, 15));
		text.setFill(Color.web("#FFFFFF"));
		podcastInformationContainer = new HBox(10, text);
		podcastInformationContainer.setPrefSize(800, 100);
		podcastInformationContainer.setMaxSize(800, 100);
		podcastInformationContainer.setBackground(
				new Background(new BackgroundFill(Color.web("#808080"), new CornerRadii(10), Insets.EMPTY)));
		podcastInformationContainer.setBorder(new Border(new BorderStroke(Color.web("#006666"), BorderStrokeStyle.SOLID,
				new CornerRadii(10), new BorderWidths(3))));
		podcastInformationContainer.setAlignment(Pos.CENTER_LEFT);
		setTop(podcastInformationContainer);
	}

	private void showSubscribedPodcasts() {
		readfromDB();
		Text podcastText = new Text(SUBSCRIBEDPODCAST);
		podcastText.setFill(Color.web("#FFFFFF"));
		podcastText.setFont(Font.font("Arial", FontWeight.BOLD, 16));
		podcastsContainer = FXCollections.observableArrayList();
		podcastListView = new ListViewSample();
		podcastListView.setPrefSize(300, 300);
		podcastListView.setBorder(new Border(new BorderStroke(Color.web("#006666"), BorderStrokeStyle.SOLID,
				new CornerRadii(10), new BorderWidths(3))));
		podcastListView.setBackground(
				new Background(new BackgroundFill(Color.web("#808080"), new CornerRadii(10), Insets.EMPTY)));
		for (Podcast podcastIterator : podcastsFromDBList) {
			// ImageView imageView = new ImageView(new
			// Image(podcastIterator.getArtworkUrl60()));
			Rectangle imageRectangle = new Rectangle();
			imageRectangle.setArcHeight(10);
			imageRectangle.setArcWidth(10);
			imageRectangle.setHeight(40);
			imageRectangle.setWidth(40);
			imageRectangle.setFill(new ImagePattern(new Image(podcastIterator.getArtworkUrl60())));
			Text name = new Text(new String(podcastIterator.getArtistName()));
			name.setFont(Font.font("Arial", FontWeight.BOLD, 13));
			name.setFill(Color.web("#FFFFFF"));
			ButtonSample subscribeButton = new ButtonSample(UNSUBSCRIBE, CLICKFORUNSUBSCRIBE);
			subscribeButton.setOnAction((ActionEvent event) -> {
				removefromDB(podcastIterator.getArtistName());
				showPodcastEmptyInformation();
				showSubscribedPodcasts();
				showEmptyEpisodesList();
				setPodcastListInvalidationListener();
			});
			HBox itemHbox = new HBox(10, imageRectangle, name, subscribeButton);
			itemHbox.setAlignment(Pos.CENTER_LEFT);
			itemHbox.setBackground(
					new Background(new BackgroundFill(Color.web("#808080"), new CornerRadii(10), Insets.EMPTY)));
			itemHbox.setBorder(new Border(new BorderStroke(Color.web("#006666"), BorderStrokeStyle.SOLID,
					new CornerRadii(3), new BorderWidths(3))));
			setMouseExitedEventHBox(itemHbox);
			setMouseEnteredEventHBox(itemHbox);
			podcastsContainer.add(itemHbox);
			podcastListView.setItems(podcastsContainer);
			podcastListVBox = new VBox(10, podcastText, podcastListView);
			setMargin(podcastListVBox, new Insets(20));
			setAlignment(podcastListVBox, Pos.CENTER_LEFT);
			setLeft(podcastListVBox);
		}
		if (!isEmptySubscriptions()) {
			podcastListView.setDisable(false);
		} else {
			Text nosubscriptionText = new Text(NOSUBSCRIPTIONS);
			nosubscriptionText.setFill(Color.web("#FFFFFF"));
			nosubscriptionText.setFont(Font.font("Arial", FontWeight.BOLD, 13));
			HBox itemHbox = new HBox(10, nosubscriptionText);
			itemHbox.setBackground(
					new Background(new BackgroundFill(Color.web("#808080"), new CornerRadii(10), Insets.EMPTY)));
			itemHbox.setBorder(new Border(new BorderStroke(Color.web("#006666"), BorderStrokeStyle.SOLID,
					new CornerRadii(3), new BorderWidths(3))));
			itemHbox.setPrefSize(300, 50);
			itemHbox.setAlignment(Pos.CENTER_LEFT);
			VBox podcastHBox = new VBox(10, podcastText, itemHbox);
			setMargin(podcastHBox, new Insets(20));
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
		Text artistLabel = new Text(new String("Artist name: "
				+ podcastsFromDBList.get(podcastListView.getSelectionModel().getSelectedIndex()).getArtistName()));
		Text collectionNameLabel = new Text(new String("Title: "
				+ podcastsFromDBList.get(podcastListView.getSelectionModel().getSelectedIndex()).getCollectionName()));
		Text countryLabel = new Text(new String("Country: "
				+ podcastsFromDBList.get(podcastListView.getSelectionModel().getSelectedIndex()).getCountry()));
		Text feedUrlLabel = new Text(new String("Feed url: "
				+ podcastsFromDBList.get(podcastListView.getSelectionModel().getSelectedIndex()).getFeedUrl()));
		Text genreLabel = new Text(new String("Genre: " + podcastsFromDBList
				.get(podcastListView.getSelectionModel().getSelectedIndex()).getPrimaryGenreName()));
		Text lastReleaseDateLabel = new Text(new String("Last Updated: "
				+ podcastsFromDBList.get(podcastListView.getSelectionModel().getSelectedIndex()).getReleaseDate()));
		Text trackCountLabel = new Text(new String("Number of episodes: "
				+ podcastsFromDBList.get(podcastListView.getSelectionModel().getSelectedIndex()).getTrackCount()));
		artistLabel.setFont(Font.font("Arial", FontWeight.BOLD, 12));
		artistLabel.setFill(Color.web("#FFFFFF"));
		collectionNameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 12));
		collectionNameLabel.setFill(Color.web("#FFFFFF"));
		countryLabel.setFont(Font.font("Arial", FontWeight.BOLD, 12));
		countryLabel.setFill(Color.web("#FFFFFF"));
		genreLabel.setFont(Font.font("Arial", FontWeight.BOLD, 12));
		genreLabel.setFill(Color.web("#FFFFFF"));
		feedUrlLabel.setFont(Font.font("Arial", FontWeight.BOLD, 12));
		feedUrlLabel.setFill(Color.web("#FFFFFF"));
		lastReleaseDateLabel.setFont(Font.font("Arial", FontWeight.BOLD, 12));
		lastReleaseDateLabel.setFill(Color.web("#FFFFFF"));
		trackCountLabel.setFont(Font.font("Arial", FontWeight.BOLD, 12));
		trackCountLabel.setFill(Color.web("#FFFFFF"));
		Rectangle imageRectangle = new Rectangle();
		imageRectangle.setArcHeight(10);
		imageRectangle.setArcWidth(10);
		imageRectangle.setHeight(90);
		imageRectangle.setWidth(90);
		imageRectangle.setFill(new ImagePattern(new Image(
				podcastsFromDBList.get(podcastListView.getSelectionModel().getSelectedIndex()).getArtworkUrl100())));
		VBox helperVBox3 = new VBox(imageRectangle);
		helperVBox3.setAlignment(Pos.CENTER_LEFT);
		VBox helperVBox1 = new VBox(5, artistLabel, collectionNameLabel, countryLabel);
		helperVBox1.setAlignment(Pos.CENTER_LEFT);
		VBox helperVBox2 = new VBox(5, feedUrlLabel, genreLabel, lastReleaseDateLabel, trackCountLabel);
		helperVBox2.setAlignment(Pos.CENTER_LEFT);
		podcastInformationContainer = new HBox(10, helperVBox3, helperVBox1, helperVBox2);
		podcastInformationContainer.setPrefSize(800, 100);
		podcastInformationContainer.setMaxSize(800, 100);
		podcastInformationContainer.setBackground(
				new Background(new BackgroundFill(Color.web("#808080"), new CornerRadii(10), Insets.EMPTY)));
		podcastInformationContainer.setBorder(new Border(new BorderStroke(Color.web("#006666"), BorderStrokeStyle.SOLID,
				new CornerRadii(10), new BorderWidths(3))));
		setMouseEnteredEventHBox(podcastInformationContainer);
		setMouseExitedEventHBox(podcastInformationContainer);
		setTop(podcastInformationContainer);
	}

	private void showEmptyEpisodesList() {
		/*
		 * Text noPodcastSelectedText = new Text(NOPODCASTSELECTED);
		 * noPodcastSelectedText.setFont(Font.font("Arial", FontWeight.BOLD,
		 * 13)); noPodcastSelectedText.setFill(Color.web("#FFFFFF")); Text
		 * episodeListText = new Text(SUBSCRIBEDEPISODES);
		 * episodeListText.setFill(Color.web("#FFFFFF"));
		 * episodeListText.setFont(Font.font("Arial", FontWeight.BOLD, 16));
		 * HBox itemHbox = new HBox(10, noPodcastSelectedText);
		 * itemHbox.setAlignment(Pos.CENTER_LEFT); itemHbox.setBackground( new
		 * Background(new BackgroundFill(Color.web("#808080"), new
		 * CornerRadii(10), Insets.EMPTY))); itemHbox.setBorder(new Border(new
		 * BorderStroke(Color.web("#006666"), BorderStrokeStyle.SOLID, new
		 * CornerRadii(3), new BorderWidths(3))));
		 * setMouseEnteredEventHBox(itemHbox);
		 * setMouseExitedEventHBox(itemHbox); episodeListView = new
		 * ListView(FXCollections.observableArrayList(itemHbox));
		 * episodeListView.setPrefSize(500, 300);
		 * episodeListView.setMaxSize(500, 300); episodeListView.setBackground(
		 * new Background(new BackgroundFill(Color.web("#808080"), new
		 * CornerRadii(10), Insets.EMPTY))); episodeListView.setBorder(new
		 * Border(new BorderStroke(Color.web("#006666"),
		 * BorderStrokeStyle.SOLID, new CornerRadii(3), new BorderWidths(3))));
		 * VBox episodeVBox = new VBox(10, episodeListText, episodeListView);
		 * setMargin(episodeVBox, new Insets(20)); setCenter(episodeVBox);
		 */
		Text noPodcastSelectedText = new Text(NOPODCASTSELECTED);
		noPodcastSelectedText.setFont(Font.font("Arial", FontWeight.BOLD, 13));
		noPodcastSelectedText.setFill(Color.web("#FFFFFF"));
		Text episodeListText = new Text(SUBSCRIBEDEPISODES);
		episodeListText.setFill(Color.web("#FFFFFF"));
		episodeListText.setFont(Font.font("Arial", FontWeight.BOLD, 16));
		HBox itemHbox = new HBox(10, noPodcastSelectedText);
		itemHbox.setAlignment(Pos.CENTER_LEFT);
		itemHbox.setBackground(
				new Background(new BackgroundFill(Color.web("#808080"), new CornerRadii(10), Insets.EMPTY)));
		itemHbox.setBorder(new Border(new BorderStroke(Color.web("#006666"), BorderStrokeStyle.SOLID,
				new CornerRadii(3), new BorderWidths(3))));
		itemHbox.setPrefSize(500, 50);
		itemHbox.setMaxSize(500, 50);
		VBox episodeVBox = new VBox(10, episodeListText, itemHbox);
		setMargin(episodeVBox, new Insets(20));
		setCenter(episodeVBox);
	}

	private void showpodcastEpisodes() {
		readfromDB();
		Text episodeListText = new Text(SUBSCRIBEDEPISODES);
		episodeListText.setFill(Color.web("#FFFFFF"));
		episodeListText.setFont(Font.font("Arial", FontWeight.BOLD, 16));
		selectedPodcastEpisodes = podcastsFromDBList.get(podcastListView.getSelectionModel().getSelectedIndex())
				.getPodcastEpisode();
		episodesContainer = FXCollections.observableArrayList();
		for (PodcastEpisode podcastEpisode : selectedPodcastEpisodes) {
			Text pubDateText = new Text(podcastEpisode.getPubdate());
			pubDateText.setFill(Color.web("#FFFFFF"));
			pubDateText.setFont(Font.font("Arial", FontWeight.BOLD, 12));
			Text titleText = new Text(podcastEpisode.getTitle());
			titleText.setFill(Color.web("#FFFFFF"));
			titleText.setFont(Font.font("Arial", FontWeight.BOLD, 12));
			Image image = new Image(podcastEpisode.getImage());
			if (image.isError()) {
				ImageView imageView = new ImageView(new Image(podcastEpisode.getImage()));
				imageView.setFitWidth(30);
				imageView.setFitHeight(30);
				HBox itemHbox = new HBox(10, imageView, titleText, pubDateText);
				itemHbox.setAlignment(Pos.CENTER_LEFT);
				itemHbox.setBackground(
						new Background(new BackgroundFill(Color.web("#808080"), new CornerRadii(10), Insets.EMPTY)));
				itemHbox.setBorder(new Border(new BorderStroke(Color.web("#006666"), BorderStrokeStyle.SOLID,
						new CornerRadii(3), new BorderWidths(3))));
				setStyle("-fx-background-color: #808080;");
				setMouseEnteredEventHBox(itemHbox);
				setMouseExitedEventHBox(itemHbox);
				episodesContainer.add(itemHbox);
			} else {
				Rectangle imageRectangle = new Rectangle();
				imageRectangle.setArcHeight(10);
				imageRectangle.setArcWidth(10);
				imageRectangle.setHeight(30);
				imageRectangle.setWidth(30);
				imageRectangle.setFill(new ImagePattern(image));
				VBox imageVbox = new VBox(imageRectangle);
				imageVbox.setAlignment(Pos.CENTER_LEFT);
				HBox itemHbox = new HBox(10, imageVbox, titleText, pubDateText);
				itemHbox.setAlignment(Pos.CENTER_LEFT);
				itemHbox.setBackground(
						new Background(new BackgroundFill(Color.web("#808080"), new CornerRadii(10), Insets.EMPTY)));
				itemHbox.setBorder(new Border(new BorderStroke(Color.web("#006666"), BorderStrokeStyle.SOLID,
						new CornerRadii(3), new BorderWidths(3))));
				setMouseEnteredEventHBox(itemHbox);
				setMouseExitedEventHBox(itemHbox);
				episodesContainer.add(itemHbox);
			}
		}
		episodeListView = new ListView<HBox>(episodesContainer);
		episodeListView.setPrefSize(500, 300);
		episodeListView.setMaxSize(500, 300);
		episodeListView.setBackground(
				new Background(new BackgroundFill(Color.web("#808080"), new CornerRadii(10), Insets.EMPTY)));
		episodeListView.setBorder(new Border(new BorderStroke(Color.web("#006666"), BorderStrokeStyle.SOLID,
				new CornerRadii(3), new BorderWidths(3))));
		VBox episodeVBox = new VBox(10, episodeListText, episodeListView);
		setMargin(episodeVBox, new Insets(20));
		setCenter(episodeVBox);
	}

	private void setMouseEnteredEventHBox(HBox itemHbox) {
		itemHbox.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				itemHbox.setBackground(
						new Background(new BackgroundFill(Color.web("#191919"), new CornerRadii(10), Insets.EMPTY)));
			}
		});
	}

	private void setMouseExitedEventHBox(HBox itemHbox) {
		itemHbox.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				itemHbox.setBackground(
						new Background(new BackgroundFill(Color.web("#808080"), new CornerRadii(10), Insets.EMPTY)));
			}
		});
	}

	private void removefromDB(final String s) {
		final Query<Podcast> deletePodcast = MorphiaConnector.getDataStore().createQuery(Podcast.class)
				.filter("artistName =", s);
		MorphiaConnector.getDataStore().delete(deletePodcast);
	}
}
