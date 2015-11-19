package szakdolgozat.podcast.gui.builder;

import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import szakdolgozat.podcast.gui.decorator.Decorator;

/**
 * The Class ListViewBuilder.
 *
 * @author Daniel Toth
 * @version 0.0.1
 * @since 0.0.1
 * 
 *        This class is used for assemble a new ListView object. Basically it is
 *        a wrapper class that makes easier to make instance of objects.
 */
public class ListViewBuilder implements Builder {

	/** The instance of the builder object. */
	static ListViewBuilder instance = new ListViewBuilder();

	/**
	 * The listView that we would like to assemble. This is the object that we
	 * return after the construction.
	 */
	private static ListView<HBox> listView;

	/**
	 * Instantiates a new list view builder.
	 */
	private ListViewBuilder() {

	}

	/**
	 * Gets the single instance of ListViewBuilder.
	 *
	 * @return single instance of ListViewBuilder
	 */
	private static ListViewBuilder getInstance() {
		return instance;
	}

	/**
	 * Creates the instance of our needed object through decoration progress.
	 *
	 * @return the list view builder.
	 */
	public static ListViewBuilder create() {
		listView = Decorator.decorateListViewFactory(new ListView<HBox>());
		return getInstance();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see szakdolgozat.podcast.builder.Builder#build()
	 */
	@Override
	public ListView<HBox> build() {
		return listView;
	}

	/**
	 * This method will set the size of our listView object.
	 *
	 * @param width
	 *            the width of our listView.
	 * 
	 * @param height
	 *            the height of our listView.
	 * 
	 * @return the list view builder.
	 */
	public ListViewBuilder size(final int width, final int height) {
		listView.setPrefSize(width, height);
		listView.setMaxSize(width, height);
		return getInstance();
	}

	/**
	 * This method is used for set the items for our listview.
	 *
	 * @param list
	 *            we would like to add.
	 * @return the list view builder.
	 */
	public ListViewBuilder items(final ObservableList<HBox> list) {
		listView.setItems(list);
		return getInstance();
	}

}
