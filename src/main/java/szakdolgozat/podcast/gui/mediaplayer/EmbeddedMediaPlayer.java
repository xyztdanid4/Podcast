package szakdolgozat.podcast.gui.mediaplayer;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class EmbeddedMediaPlayer extends Application {

	private static final String MEDIA_URL = "http://podcasts.spinninrecords.nl/oliverheldens/Heldeep015.mp3";

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Embedded Media Player");
		Group root = new Group();
		Scene scene = new Scene(root, 540, 241);

		// create media player
		Media media = new Media(MEDIA_URL);
		MediaPlayer mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setAutoPlay(true);
		MediaControl mediaControl = new MediaControl(mediaPlayer);
		scene.setRoot(mediaControl);

		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
