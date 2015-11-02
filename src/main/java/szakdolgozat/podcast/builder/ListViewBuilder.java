package szakdolgozat.podcast.builder;

import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import szakdolgozat.podcast.gui.decorator.Decorator;

public class ListViewBuilder {
	static ListViewBuilder instance = new ListViewBuilder();
	private static ListView<HBox> listView;

	private ListViewBuilder() {

	}

	public static ListViewBuilder getInstance() {
		return instance;
	}

	public static ListViewBuilder create() {
		listView = Decorator.decorateListViewFactory(new ListView<HBox>());
		return getInstance();
	}

	public ListView<HBox> build() {
		return listView;
	}

}
