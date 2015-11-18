package szakdolgozat.podcast.builder;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import szakdolgozat.podcast.gui.decorator.Decorator;

/**
 * The Class HBoxBuilder.
 * 
 * @author Daniel Toth
 * @version 0.0.1
 * @since 0.0.1
 * 
 *        This class is used for assemble a new HBox object. Basically it is a
 *        wrapper class that makes easier to make instance of objects.
 */
public class HBoxBuilder implements Builder {

	/** The instance of the builder object. */
	static HBoxBuilder instance = new HBoxBuilder();

	/**
	 * The hbox that we would like to assemble. This is the object that we
	 * return after the construction.
	 */
	private static HBox hbox;

	/**
	 * Instantiates a new hbox builder instance.
	 */
	private HBoxBuilder() {

	}

	/**
	 * Creates the instance of our needed object through decoration progress.
	 *
	 * @return the hbox builder instance.
	 */
	public static HBoxBuilder create() {
		hbox = Decorator.decorateHBoxFactory(new HBox(Decorator.HBOXPADDING));
		return getInstance();
	}

	/**
	 * This method will get an hbox instance, and the parameter will be added to
	 * our builded hbox. As we see in the method name the 'no' is indicates that
	 * there is no decoration progress as we use this method.
	 *
	 * @param hbox
	 *            that we would like to add.
	 * 
	 * @return the hbox builder instance.
	 */
	public HBoxBuilder noHBox(final HBox hbox) {
		hbox.getChildren().add(hbox);
		return getInstance();
	}

	/**
	 * This method will set a default effect for our button. If we use this the
	 * decoration object will set the effect on. Default is not to use this, if
	 * a button has an action we should use this method to indicate that it has
	 * an action.
	 *
	 * @return the hbox builder instance.
	 */
	public HBoxBuilder effectOn() {
		hbox = Decorator.decorateEffect(hbox);
		return getInstance();
	}

	/**
	 * This method is used for create a new hbox instance, and add to our hbox,
	 * but this time there is no decoration progress.
	 *
	 * @return the hbox builder instance.
	 */
	public static HBoxBuilder noCreate() {
		hbox = new HBox(Decorator.HBOXPADDING);
		return getInstance();
	}

	/**
	 * This method is used for adding a decorated image to our hbox. The
	 * parameter is only containing the string of the image. Inside the method,
	 * the decorator will decorate and make an instance of it.
	 *
	 * @param image
	 *            we would like to add.
	 * 
	 * @return the hbox builder instance.
	 */

	public HBoxBuilder image(final String image) {
		hbox.getChildren().add(Decorator.decorateImageViewFactory(new ImageView(image), Decorator.SMALLRECTANGLEHEIGHT,
				Decorator.SMALLRECTANGLEWIDTH));
		return getInstance();
	}

	/*
	 * public HBoxBuilder image(final String image) { hbox.getChildren()
	 * .add(Decorator.decorateImageViewFactory(new ImageView(new
	 * Image(getClass().getResourceAsStream(image))),
	 * Decorator.SMALLRECTANGLEHEIGHT, Decorator.SMALLRECTANGLEWIDTH)); return
	 * getInstance(); }
	 */
	/**
	 * This method will create and add a slider to our hbox.
	 *
	 * @param slider
	 *            we would like to add.
	 * 
	 * @return the hbox builder instance.
	 */
	public HBoxBuilder slider(final Slider slider) {
		hbox.getChildren().add(slider);
		return getInstance();
	}

	/**
	 * This method will create and add a rectangle object to our hbox. This
	 * rectangle is needed cause of two reason. One, is that in this application
	 * we have two kind image, one is smaller, the other is bigger. So we have
	 * to make a difference. Second, is that we need to decorate all the images
	 * in the program, and in java fx there are no possibility to do that of an
	 * image, that why we use a rectangle around of our images. The parameter
	 * will contain the url of the image, and inside the decoration process the
	 * rectangle and image will be constructed.
	 * 
	 * @param image
	 *            we would like to add
	 * 
	 * 
	 * @return the hbox builder instance
	 */
	public HBoxBuilder smallRectangle(final String image) {
		hbox.getChildren().add(Decorator.decorateRectangleFactory(new Rectangle(), Decorator.SMALLRECTANGLEHEIGHT,
				Decorator.SMALLRECTANGLEWIDTH, image));
		return getInstance();
	}

	/**
	 * This method will create and add a rectangle object to our hbox. This
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
	 * 
	 * @return the hbox builder instance
	 */
	public HBoxBuilder bigRectangle(final String image) {
		hbox.getChildren().add(Decorator.decorateRectangleFactory(new Rectangle(), Decorator.BIGRECTANGLEHEIGHT,
				Decorator.BIGRECTANGLEWIDTH, image));
		return getInstance();
	}

	/**
	 * This method will add a checkbox to our hbox. Notice that it will make no
	 * instance of checkbox, just decorate it.
	 *
	 * @param checkbox
	 *            we would like to add.
	 * 
	 * @return the hbox builder instance
	 */
	public HBoxBuilder checkBox(final CheckBox checkbox) {
		hbox.getChildren().add(Decorator.decorateCheckBoxFactory(checkbox));
		return getInstance();
	}

	/**
	 * This method will add a special text to our hbox. We only get the string
	 * of the text as parameter, the instantiation is inside the decoration
	 * progress. It is special cause we store only the first 20 letters of the
	 * string.
	 *
	 * @param artist
	 *            text we would like to add.
	 * 
	 * @return the hbox builder instance.
	 */
	public HBoxBuilder artist(final String artist) {
		hbox.getChildren()
				.add(Decorator.decorateTextFactory(
						new Text(artist.length() > 20
								? new String(new StringBuilder(artist.substring(0, 20)).append("...")) : artist),
				Decorator.SMALLTEXTSIZE));
		return getInstance();
	}

	/**
	 * This method will create and add a text object to our hbox. In the
	 * application there are two sizes of texts. That is why we need two kind of
	 * text generating method. As parameter we only get the srting, the text
	 * object's instance is maked by the decorator object. This method is
	 * responsible for creating and adding the bigger sized text.
	 *
	 * @param text
	 *            that we would like to add.
	 * 
	 * @return the hbox builder instance.
	 */
	public HBoxBuilder bigText(final String text) {
		hbox.getChildren().add(Decorator.decorateTextFactory(new Text(text), Decorator.BIGTEXTSIZE));
		return getInstance();
	}

	/**
	 * This method will create and add a text object to our hbox. In the
	 * application there are two sizes of texts. That is why we need two kind of
	 * text generating method. As parameter we only get the srting, the text
	 * object's instance is maked by the decorator object. This method is
	 * responsible for creating and adding the smaller sized text.
	 *
	 * @param text
	 *            which is in string in the parameter.
	 * 
	 * @return the hbox builder instance.
	 */
	public HBoxBuilder smallText(final String text) {
		hbox.getChildren().add(Decorator.decorateTextFactory(new Text(text), Decorator.SMALLTEXTSIZE));
		return getInstance();
	}

	/**
	 * This method will add a special text to our hbox. We only get the string
	 * of the text as parameter, the instantiation is inside the decoration
	 * progress. It is special cause we store only the first 60 letters of the
	 * string.
	 *
	 * @param artist
	 *            which is in string in the parameter.
	 * @return the hbox builder instancer
	 */
	public HBoxBuilder title(final String artist) {
		hbox.getChildren()
				.add(Decorator.decorateTextFactory(
						new Text(artist.length() > 60
								? new String(new StringBuilder(artist.substring(0, 60)).append("...")) : artist),
				Decorator.SMALLTEXTSIZE));
		return getInstance();
	}

	/**
	 * This method is used for adding the given button by parameter which will
	 * be decorated.
	 *
	 * @param button
	 *            we would like to add.
	 * 
	 * @return the hbox builder instance.
	 */
	public HBoxBuilder button(final Button button) {
		hbox.getChildren().add(Decorator.decorateButtonFactory(button));
		return getInstance();
	}

	/**
	 * This method will add a created button object to our hbox without
	 * decoration.
	 *
	 * @param button
	 *            we would like to add.
	 * 
	 * @return the hbox builder instance.
	 */
	public HBoxBuilder noButton(final Button button) {
		hbox.getChildren().add(button);
		return getInstance();
	}

	/**
	 * This method will add a created label object to our hbox without
	 * decoration. We get the label as parameter.
	 *
	 * @param label
	 *            we would like to add.
	 * 
	 * @return the hbox builder instance.
	 */
	public HBoxBuilder noLabel(final Label label) {
		hbox.getChildren().add(label);
		return getInstance();
	}

	/**
	 * This method will add a created text object to our hbox without
	 * decoration. We get the text as parameter.
	 *
	 * @param text
	 *            we would like to add.
	 * 
	 * @return the hbox builder instance.
	 */
	public HBoxBuilder noText(final Text text) {
		hbox.getChildren().add(text);
		return getInstance();
	}

	/**
	 * This method will add a created textfield object to our hbox without
	 * decoration.
	 *
	 * @param textField
	 *            we would like to add.
	 * 
	 * @return the hbox builder instance.
	 */
	public HBoxBuilder noTextField(final TextField textField) {
		hbox.getChildren().add(textField);
		return getInstance();
	}

	/**
	 * This method will add a fully created textfield to our hbox through
	 * decoration method.
	 *
	 * @param textField
	 *            we would like to add.
	 * 
	 * @param width
	 *            the width of the textfield.
	 * 
	 * @param heightthe
	 *            height of the textfield.
	 * 
	 * @return the hbox builder instance.
	 */
	public HBoxBuilder textField(final TextField textField, final int width, final int height) {
		hbox.getChildren().add(Decorator.decorateTextFieldFactory(textField, width, height));
		return getInstance();
	}

	/**
	 * This method will set the size of our hbox object.
	 *
	 * @param width
	 *            the width of our hbox.
	 * 
	 * @param height
	 *            the height of our hbox.
	 * 
	 * @return the h box builder instance.
	 */
	public HBoxBuilder size(final int width, final int height) {
		hbox.setPrefSize(width, height);
		hbox.setMaxSize(width, height);
		return getInstance();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see szakdolgozat.podcast.builder.Builder#build()
	 */
	@Override
	public HBox build() {
		return hbox;
	}

	/**
	 * This method will give a vbox to our hbox, without decoration. The
	 * parameter is containing the created vbox, so there will be no creation of
	 * the vbox.
	 *
	 * @param vbox
	 * 
	 * @return the h box builder
	 */
	public HBoxBuilder noVBox(final VBox vbox) {
		vbox.setAlignment(Pos.CENTER_LEFT);
		hbox.getChildren().add(vbox);
		return getInstance();
	}

	/**
	 * Gets the single instance of HBoxBuilder.
	 *
	 * @return single the instance of HBoxBuilder.
	 */
	private static HBoxBuilder getInstance() {
		return instance;
	}

}
