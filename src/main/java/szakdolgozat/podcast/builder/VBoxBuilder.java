package szakdolgozat.podcast.builder;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import szakdolgozat.podcast.gui.decorator.Decorator;

/**
 * The Class VBoxBuilder.
 * 
 * 
 * @author Daniel Toth
 * @version 0.0.1
 * @since 0.0.1
 * 
 *        This class is used for assemble a new VBox object. Basically it is a
 *        wrapper class that makes easier to make instance of objects.
 */
public class VBoxBuilder implements Builder {

	/** The instance of the builder object. */
	static VBoxBuilder instance = new VBoxBuilder();

	/**
	 * The vbox that we would like to assemble. This is the object that we
	 * return after the construction.
	 */
	private static VBox vbox;

	/**
	 * Instantiates a new vbox builder.
	 */
	private VBoxBuilder() {

	}

	/**
	 * Gets the single instance of VBoxBuilder.
	 *
	 * @return single instance of VBoxBuilder
	 */
	private static VBoxBuilder getInstance() {
		return instance;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see szakdolgozat.podcast.builder.Builder#build()
	 */
	@Override
	public VBox build() {
		return vbox;
	}

	/**
	 * This method will add a checkbox to our vbox. Notice that it will make no
	 * instance of checkbox, just decorate it.
	 *
	 * @param checkbox
	 *            we would like to add.
	 * 
	 * @return the vbox builder
	 */
	public VBoxBuilder checkBox(final CheckBox checkbox) {
		vbox.getChildren().add(checkbox);
		return getInstance();
	}

	/**
	 * Creates the instance of our needed object through decoration progress.
	 *
	 * @return the vbox builder object.
	 */
	public static VBoxBuilder create() {
		vbox = Decorator.decorateVBoxFactory(new VBox(Decorator.VBOXPADDING));
		vbox.setAlignment(Pos.CENTER_LEFT);
		return getInstance();
	}

	/**
	 * This method is set the alignment top left.
	 *
	 * @return the vbox builder object.
	 */
	public VBoxBuilder topLeftAlignment() {
		vbox.setAlignment(Pos.TOP_LEFT);
		return getInstance();
	}

	/**
	 *
	 * This method is used for adding the given button by parameter which will
	 * be decorated.
	 *
	 * @param button
	 *            we would like to add.
	 * 
	 * @return the vbox builder object.
	 */
	public VBoxBuilder Button(final Button button) {
		vbox.getChildren().add(Decorator.decorateButtonFactory(button));
		return getInstance();
	}

	/**
	 * This method will add a created button object to our vbox without
	 * decoration.
	 *
	 * @param button
	 *            we would like to add.
	 * 
	 * @return the vbox builder object.
	 */
	public VBoxBuilder noButton(final Button button) {
		vbox.getChildren().add(button);
		return getInstance();
	}

	/**
	 * This method is used for set the alignment center left.
	 *
	 * @return the vbox builder object.
	 */
	public VBoxBuilder centerLeftAlignment() {
		vbox.setAlignment(Pos.CENTER_LEFT);
		return getInstance();
	}

	/**
	 * This method will create and add a text object to our vbox. In the
	 * application there are two sizes of texts. That is why we need two kind of
	 * text generating method. As parameter we only get the srting, the text
	 * object's instance is maked by the decorator object. This method is
	 * responsible for creating and adding the bigger sized text.
	 *
	 * @param text
	 *            that we would like to add.
	 * 
	 * @return the vbox builder object.
	 */
	public VBoxBuilder bigText(final String text) {
		vbox.getChildren().add(Decorator.decorateTextFactory(new Text(text), Decorator.BIGTEXTSIZE));
		return getInstance();
	}

	/**
	 * This method will add a created textfield object to our vbox without
	 * decoration.
	 *
	 * @param textField
	 *            we would like to add.
	 * 
	 * @return the vbox builder object.
	 */
	public VBoxBuilder noTextField(final TextField textField) {
		vbox.getChildren().add(textField);
		return getInstance();
	}

	/**
	 * This method will create and add a text object to our vbox. In the
	 * application there are two sizes of texts. That is why we need two kind of
	 * text generating method. As parameter we only get the srting, the text
	 * object's instance is maked by the decorator object. This method is
	 * responsible for creating and adding the smaller sized text.
	 *
	 * @param text
	 *            which is in string in the parameter.
	 * 
	 * @return the vbox builder object.
	 */
	public VBoxBuilder smallText(final String text) {
		vbox.getChildren().add(Decorator.decorateTextFactory(new Text(text), Decorator.SMALLTEXTSIZE));
		return getInstance();
	}

	/**
	 * 
	 * This method will get an hbox instance, and the parameter will be added to
	 * our builded vbox. As we see in the method name the 'no' is indicates that
	 * there is no decoration progress as we use this method.
	 *
	 * @param hbox
	 *            that we would like to add.
	 * 
	 * @return the vbox builder object.
	 */
	public VBoxBuilder noHBox(final HBox hbox) {
		vbox.getChildren().add(hbox);
		return getInstance();
	}

	/**
	 * This method is used for adding a listview without decoration to our vbox.
	 *
	 * @param listView
	 *            the list view we would like to add.
	 * @return the vbox builder object.
	 */
	public VBoxBuilder noListView(final ListView<HBox> listView) {
		vbox.getChildren().add(listView);
		return getInstance();
	}

	/**
	 * This method will create and add a rectangle object to our vbox. This
	 * rectangle is needed cause of two reason. One, is that in this application
	 * we have two kind image, one is smaller, the other is bigger. So we have
	 * to make a difference. Second, is that we need to decorate all the images
	 * in the program, and in java fx there are no possibility to do that of an
	 * image, that why we use a rectangle around of our images. The parameter
	 * will contain the url of the image, and inside the decoration process the
	 * rectangle and image will be constructed.
	 *
	 * @param image
	 *            we would like to add.
	 * 
	 * @return the vbox builder object.
	 */
	public VBoxBuilder bigRectangle(final String image) {
		vbox.getChildren().add(Decorator.decorateRectangleFactory(new Rectangle(), Decorator.BIGRECTANGLEHEIGHT,
				Decorator.BIGRECTANGLEWIDTH, image));
		return getInstance();
	}

	/**
	 * This method will set the size of our vbox object.
	 *
	 * @param width
	 *            the width of our vbox.
	 * 
	 * @param height
	 *            the height of our vbox
	 * 
	 * @return the vbox builder object.
	 */
	public VBoxBuilder size(final int width, final int height) {
		vbox.setPrefSize(width, height);
		vbox.setMaxSize(width, height);
		return getInstance();
	}

}
