package szakdolgozat.podcast.controller;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import szakdolgozat.podcast.data.podcast.Podcast;
import szakdolgozat.podcast.data.podcast.PodcastEpisode;
import szakdolgozat.podcast.gui.hbox.SerialHBox;
import szakdolgozat.podcast.morphia.MorphiaConnector;

public class PlayListController {

	private static PlayListController instance = null;
	private static ObservableList<PodcastEpisode> playList;
	private static ObservableList<SerialHBox> realPlayList;
	private static ObservableList<PodcastEpisode> episodeList;
	private static ObservableList<SerialHBox> realEpisodeList;

	private PlayListController() {
		realPlayList = FXCollections.<SerialHBox> observableArrayList();
		playList = FXCollections.<PodcastEpisode> observableArrayList();
	}

	/**
	 * @return the playList
	 */
	public ObservableList<PodcastEpisode> getPlayList() {
		return playList;
	}

	/**
	 * @param playList
	 *            the playList to set
	 */
	public void setPlayList(final ObservableList<PodcastEpisode> playList) {
		PlayListController.playList = playList;
	}

	/**
	 * @return the realPlayList
	 */
	public ObservableList<SerialHBox> getRealPlayList() {
		return realPlayList;
	}

	/**
	 * @param realPlayList
	 *            the realPlayList to set
	 */
	public void setRealPlayList(final ObservableList<SerialHBox> realPlayList) {
		PlayListController.realPlayList = realPlayList;
	}

	/**
	 * @return the episodeList
	 */
	public ObservableList<PodcastEpisode> getEpisodeList() {
		return episodeList;
	}

	/**
	 * @param episodeList
	 *            the episodeList to set
	 */
	public void setEpisodeList(final ObservableList<PodcastEpisode> episodeList) {
		PlayListController.episodeList = episodeList;
	}

	/**
	 * @return the realEpisodeList
	 */
	public ObservableList<SerialHBox> getRealEpisodeList() {
		return realEpisodeList;
	}

	/**
	 * @param realEpisodeList
	 *            the realEpisodeList to set
	 */
	public void setRealEpisodeList(final ObservableList<SerialHBox> realEpisodeList) {
		PlayListController.realEpisodeList = realEpisodeList;
	}

	/**
	 * @param instance
	 *            the instance to set
	 */
	public static void setInstance(final PlayListController instance) {
		PlayListController.instance = instance;
	}

	public static PlayListController getInstance() {
		if (instance == null) {
			instance = new PlayListController();
		}
		return instance;
	}

	public List<Podcast> readfromDB() {
		return MorphiaConnector.getDataStore().createQuery(Podcast.class).asList();
	}

}
