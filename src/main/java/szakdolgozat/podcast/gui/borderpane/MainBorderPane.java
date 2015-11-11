package szakdolgozat.podcast.gui.borderpane;

import javafx.scene.layout.BorderPane;
import szakdolgozat.podcast.data.podcast.PodcastEpisode;
import szakdolgozat.podcast.gui.mediaplayer.MediaControlPodcast;
import szakdolgozat.podcast.gui.tabpane.ApplicationTabPane;
import szakdolgozat.podcast.gui.vbox.RecommendVBoxView;

public class MainBorderPane extends BorderPane {
	private static MainBorderPane instance = null;

	public static MainBorderPane getInstance() {
		if (instance == null) {
			instance = new MainBorderPane();
		}
		return instance;
	}

	private MainBorderPane() {
		buildCenter();
		buildRight();
		buildBottom();
	}

	private void buildCenter() {
		setCenter(ApplicationTabPane.getInstance());
	}

	public void buildRight() {
		setRight(new RecommendVBoxView());
	}

	public void buildBottom(final PodcastEpisode podcastEpisode) {
		try {
			setBottom(MediaControlPodcast.getInstance().create(podcastEpisode));
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	private void buildBottom() {
		setBottom(new MediaControlPodcast());
	}

}
