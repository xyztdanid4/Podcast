package szakdolgozat.podcast.controller;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import szakdolgozat.podcast.data.podcast.Podcast;
import szakdolgozat.podcast.data.podcast.PodcastEpisode;
import szakdolgozat.podcast.gui.hbox.SerialHBox;
import szakdolgozat.podcast.morphia.MorphiaConnector;

/**
 * The Class InformationContainer.
 *
 * @author Daniel Toth
 * @version 0.0.1
 * @since 0.0.1
 * 
 *        This class is responsible for controlling the playlist of the
 *        application.
 * 
 */
public class PlayListController {
	/**
	 * instance, represent the only instance of the class.
	 */
	private static PlayListController instance = null;
	/**
	 * playList, the source for the drag and drop action.
	 */
	private static ObservableList<PodcastEpisode> playList;
	/**
	 * realPlayList, the source for the playlistview.
	 */
	private static ObservableList<SerialHBox> realPlayList;
	/**
	 * episodeList, the source for the drag and drop acitons.
	 */
	private static ObservableList<PodcastEpisode> episodeList;
	/**
	 * realEpisodeList, the source for the episodeslistview.
	 */
	private static ObservableList<SerialHBox> realEpisodeList;

	/**
	 * The constructor main function is that make the instance of the lists.
	 */
	private PlayListController() {
		realPlayList = FXCollections.<SerialHBox> observableArrayList();
		playList = FXCollections.<PodcastEpisode> observableArrayList();
	}

	/**
	 * @return the playList.
	 */
	public ObservableList<PodcastEpisode> getPlayList() {
		return playList;
	}

	/**
	 * @param playList
	 *            the playList to set.
	 */
	public void setPlayList(final ObservableList<PodcastEpisode> playList) {
		PlayListController.playList = playList;
	}

	/**
	 * @return the realPlayList.
	 */
	public ObservableList<SerialHBox> getRealPlayList() {
		return realPlayList;
	}

	/**
	 * @param realPlayList
	 *            the realPlayList to set.
	 */
	public void setRealPlayList(final ObservableList<SerialHBox> realPlayList) {
		PlayListController.realPlayList = realPlayList;
	}

	/**
	 * @return the episodeList.
	 */
	public ObservableList<PodcastEpisode> getEpisodeList() {
		return episodeList;
	}

	/**
	 * @param episodeList
	 *            the episodeList to set.
	 */
	public void setEpisodeList(final ObservableList<PodcastEpisode> episodeList) {
		PlayListController.episodeList = episodeList;
	}

	/**
	 * @return the realEpisodeList.
	 */
	public ObservableList<SerialHBox> getRealEpisodeList() {
		return realEpisodeList;
	}

	/**
	 * @param realEpisodeList
	 *            the realEpisodeList to set.
	 */
	public void setRealEpisodeList(final ObservableList<SerialHBox> realEpisodeList) {
		PlayListController.realEpisodeList = realEpisodeList;
	}

	/**
	 * @param instance
	 *            the instance to set.
	 */
	public static void setInstance(final PlayListController instance) {
		PlayListController.instance = instance;
	}

	/**
	 * 
	 * @return instance.
	 */

	public static PlayListController getInstance() {
		if (instance == null) {
			instance = new PlayListController();
		}
		return instance;
	}

	/**
	 * 
	 * @return the list of the database objects.
	 */
	public List<Podcast> readfromDB() {
		return MorphiaConnector.getInstance().getDataStore().createQuery(Podcast.class).asList();
	}

}
