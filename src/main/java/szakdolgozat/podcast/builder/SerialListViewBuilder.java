package szakdolgozat.podcast.builder;

import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import szakdolgozat.podcast.data.podcast.PodcastEpisode;
import szakdolgozat.podcast.gui.decorator.Decorator;

public class SerialListViewBuilder implements Builder {
	static SerialListViewBuilder instance = new SerialListViewBuilder();
	private static ListView<PodcastEpisode> listView;

	private SerialListViewBuilder() {

	}

	private static SerialListViewBuilder getInstance() {
		return instance;
	}

	public static SerialListViewBuilder create() {
		listView = Decorator.decoratePlayListViewFactory(new ListView<PodcastEpisode>());
		return getInstance();
	}

	@Override
	public ListView<PodcastEpisode> build() {
		return listView;
	}

	public SerialListViewBuilder size(final int width, final int height) {
		listView.setPrefSize(width, height);
		listView.setMaxSize(width, height);
		return getInstance();
	}

	public SerialListViewBuilder items(final ObservableList<PodcastEpisode> list) {
		listView.setItems(list);
		return getInstance();
	}

}
