package szakdolgozat.podcast.gui.builder;

import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import szakdolgozat.podcast.gui.decorator.Decorator;
import szakdolgozat.podcast.gui.hbox.SerialHBox;

/**
 * The Class SerialListViewBuilder.
 * 
 * @author Daniel Toth
 * @version 0.0.1
 * @since 0.0.1
 * 
 *        This class is used for assemble a new serial ListView object.
 *        Basically it is a wrapper class that makes easier to make instance of
 *        objects.
 */
public class SerialListViewBuilder implements Builder {

	/** The instance of the builder object. */
	static SerialListViewBuilder instance = new SerialListViewBuilder();

	/**
	 * The seriallistView that we would like to assemble. This is the object
	 * that we return after the construction.
	 */
	private static ListView<SerialHBox> listView;

	/**
	 * Instantiates a new serial list view builder.
	 */
	private SerialListViewBuilder() {

	}

	/**
	 * Gets the single instance of SerialListViewBuilder.
	 *
	 * @return single instance of SerialListViewBuilder
	 */
	private static SerialListViewBuilder getInstance() {
		return instance;
	}

	/**
	 * Creates the instance of our needed object through decoration progress.
	 *
	 * @return the serial list view builder
	 */
	public static SerialListViewBuilder create() {
		listView = Decorator.decoratePlayListViewFactory(new ListView<SerialHBox>());
		return getInstance();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see szakdolgozat.podcast.builder.Builder#build()
	 */
	@Override
	public ListView<SerialHBox> build() {
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
	 * @return the serial list view builder.
	 */
	public SerialListViewBuilder size(final int width, final int height) {
		listView.setPrefSize(width, height);
		listView.setMaxSize(width, height);
		return getInstance();
	}

	/**
	 * * This method is used for set the items for our listview.
	 *
	 * @param list
	 *            we would like to add.
	 * @return the serial list view builder
	 */
	public SerialListViewBuilder items(final ObservableList<SerialHBox> list) {
		listView.setItems(list);
		return getInstance();
	}

}
