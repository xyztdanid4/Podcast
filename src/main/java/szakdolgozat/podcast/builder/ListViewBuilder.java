package szakdolgozat.podcast.builder;

import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import szakdolgozat.podcast.gui.decorator.Decorator;

public class ListViewBuilder {
	static ListViewBuilder instance = new ListViewBuilder();
	private static ListView<HBox> listView;

	private ListViewBuilder() {

	}

	private static ListViewBuilder getInstance() {
		return instance;
	}

	public static ListViewBuilder create() {
		listView = Decorator.decorateListViewFactory(new ListView<HBox>());
		return getInstance();
	}

	public ListView<HBox> build() {
		return listView;
	}

	public ListViewBuilder size(final int width, final int height) {
		listView.setPrefSize(width, height);
		listView.setMaxSize(width, height);
		return getInstance();
	}

	public ListViewBuilder items(final ObservableList<HBox> list) {
		listView.setItems(list);
		return getInstance();
	}
}
