package szakdolgozat.podcast.gui.borderpane;

import javafx.scene.layout.BorderPane;
import szakdolgozat.podcast.data.podcast.PodcastEpisode;
import szakdolgozat.podcast.gui.mediaplayer.MediaControlPodcast;
import szakdolgozat.podcast.gui.tabpane.ApplicationTabPane;
import szakdolgozat.podcast.gui.vbox.RecommendVBoxView;

public class MainBorderPaneView extends BorderPane {
	private static MainBorderPaneView instance = null;

	public static MainBorderPaneView getInstance() {
		if (instance == null) {
			instance = new MainBorderPaneView();
		}
		return instance;
	}

	private MainBorderPaneView() {
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
			buildMedia(podcastEpisode);
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param podcastEpisode
	 */
	private void buildMedia(final PodcastEpisode podcastEpisode) {
		setBottom(MediaControlPodcast.getInstance().create(podcastEpisode));
	}

	private void buildBottom() {
		setBottom(new MediaControlPodcast());
	}

}
