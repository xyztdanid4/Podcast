package szakdolgozat.podcast.gui.borderpane;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import szakdolgozat.podcast.data.podcast.Podcast;
import szakdolgozat.podcast.gui.samples.ButtonSample;
import szakdolgozat.podcast.gui.samples.HBoxSample;
import szakdolgozat.podcast.gui.samples.ListViewSample;
import szakdolgozat.podcast.morphia.MorphiaConnector;

public class PodcastBorderPane extends BorderPane {
	private List<Podcast> podcastsFromDBList;
	private VBox podcastListVBox;
	private VBox episodesListVBox;
	private ObservableList<HBoxSample> podcastsContainer;
	private ListViewSample podcastListView;
	private ObservableList<HBoxSample> episodesContainer;
	private ListViewSample episodeListView;

	public PodcastBorderPane() {
		/*
		 * podcastVBox = new VBox(10); Text podcastText = new Text(
		 * "User's podcast"); podcastText.setFont(Font.font("Arial",
		 * FontWeight.BOLD, 16)); podcastVBox.getChildren().add(podcastText);
		 * setMargin(podcastVBox, new Insets(20)); setLeft(podcastVBox);
		 */
		// readfromDB();
		showSubsribedPodcasts();
	}

	private void setMarginForElements() {
		setPadding(new Insets(20, 20, 20, 20));
	}

	private void readfromDB() {
		podcastsFromDBList = MorphiaConnector.getDataStore().createQuery(Podcast.class).asList();
		// System.out.println(podcastList);
	}

	private void showSubsribedPodcasts() {
		readfromDB();
		podcastListVBox = new VBox(10);
		Text podcastText = new Text("Subscribed Podcasts");
		podcastText.setFont(Font.font("Arial", FontWeight.BOLD, 16));
		podcastsContainer = FXCollections.observableArrayList();
		podcastListView = new ListViewSample();
		// podcastsContainer.add(new HBoxSample(podcastText));
		for (int i = 0; i < podcastsFromDBList.size(); i++) {
			ImageView imageView = new ImageView();
			Image image = new Image(podcastsFromDBList.get(i).getArtworkUrl60());
			imageView.setImage(image);
			Text name = new Text(new String(podcastsFromDBList.get(i).getArtistName()));
			name.setFont(Font.font("Arial", FontWeight.BOLD, 13));
			ButtonSample subscribeButton = new ButtonSample("Unsubscribe", "Click for unsubscribe!");
			podcastsContainer.add(new HBoxSample(imageView, name));
		}
		setMargin(podcastListVBox, new Insets(20));
		podcastListView.setItems(podcastsContainer);
		podcastListVBox.getChildren().add(podcastText);
		podcastListVBox.getChildren().add(podcastListView);
		setAlignment(podcastListVBox, Pos.CENTER_LEFT);
		podcastListVBox.setPrefSize(300, 300);
		podcastListVBox.setMaxSize(800, 800);
		setLeft(podcastListVBox);
	}
}
