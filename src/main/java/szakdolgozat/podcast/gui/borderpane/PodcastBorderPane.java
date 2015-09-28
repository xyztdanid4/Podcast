package szakdolgozat.podcast.gui.borderpane;

import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class PodcastBorderPane extends BorderPane {
	private VBox podcastVBox;

	public PodcastBorderPane() {
		podcastVBox = new VBox(10);
		Text podcastText = new Text("User's podcast");
		podcastText.setFont(Font.font("Arial", FontWeight.BOLD, 16));
		podcastVBox.getChildren().add(podcastText);
		setMargin(podcastVBox, new Insets(20));
		setLeft(podcastVBox);
	}

	private void showPodcastList() {

	}
}
