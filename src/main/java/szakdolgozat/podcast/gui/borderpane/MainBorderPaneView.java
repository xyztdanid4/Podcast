package szakdolgozat.podcast.gui.borderpane;

import javafx.scene.layout.BorderPane;
import szakdolgozat.podcast.data.podcast.PodcastEpisode;
import szakdolgozat.podcast.gui.mediaplayer.MediaControlPodcast;
import szakdolgozat.podcast.gui.tabpane.ApplicationTabPane;
import szakdolgozat.podcast.gui.vbox.RecommendVBoxView;

/**
 * The Class MainBorderPaneView.
 * 
 * @author Daniel Toth
 * @version 0.0.1
 * @since 0.0.1
 * 
 *        This class is the root of the view of the application.
 */
public class MainBorderPaneView extends BorderPane {
	private static MainBorderPaneView instance = null;

	/**
	 * Gets the single instance of MainBorderPaneView.
	 *
	 * @return single instance of MainBorderPaneView
	 */
	public static MainBorderPaneView getInstance() {
		if (instance == null) {
			instance = new MainBorderPaneView();
		}
		return instance;
	}

	/**
	 * Instantiates a new main border pane view. And set our parts.
	 */
	private MainBorderPaneView() {
		buildCenter();
		buildRight();
		buildBottom();
	}

	/**
	 * Builds the center of the application. This has the tabpane which is the
	 * soul of our application. This is the place which is changing while the
	 * other areas such like right bottom and top remains all the time.
	 */
	private void buildCenter() {
		setCenter(ApplicationTabPane.getInstance());
	}

	/**
	 * Builds the right. This is the place for the recommendations list.
	 */
	public void buildRight() {
		setRight(new RecommendVBoxView());
	}

	/**
	 * Builds the bottom, where the mediaplayer takes place.
	 *
	 * @param podcastEpisode
	 *            the podcast episode
	 */
	public void buildBottom(final PodcastEpisode podcastEpisode) {
		try {
			buildMedia(podcastEpisode);
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param podcastEpisode
	 *            is the parameter which will we use for creating the media
	 *            player.
	 */
	private void buildMedia(final PodcastEpisode podcastEpisode) {
		setBottom(MediaControlPodcast.getInstance().create(podcastEpisode));
	}

	/**
	 * Builds the bottom.
	 */
	private void buildBottom() {
		setBottom(new MediaControlPodcast());
	}

}
