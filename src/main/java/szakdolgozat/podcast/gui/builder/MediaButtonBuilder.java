package szakdolgozat.podcast.gui.builder;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import szakdolgozat.podcast.gui.decorator.Decorator;
import szakdolgozat.podcast.gui.decorator.MediaControlDecorator;

/**
 * The Class MediaButtonBuilder.
 * 
 * @author Daniel Toth
 * @version 0.0.1
 * @since 0.0.1
 * 
 *        This class is used for assemble a new mediabutton object. Basically it
 *        is a wrapper class that makes easier to make instance of objects.
 */
public class MediaButtonBuilder implements Builder {

	/** The instance of the builder object. */
	static MediaButtonBuilder instance = new MediaButtonBuilder();

	/**
	 * The button that we would like to assemble. This is the object that we
	 * return after the construction.
	 */
	private static Button button;

	/**
	 * Instantiates a new media button builder.
	 */
	private MediaButtonBuilder() {

	}

	/**
	 * Gets the single instance of MediaButtonBuilder.
	 *
	 * @return single instance of MediaButtonBuilder
	 */
	private static MediaButtonBuilder getInstance() {
		return instance;
	}

	/**
	 * Creates the instance of our needed object through decoration progress.
	 *
	 * @return the media button builder instance.
	 */
	public static MediaButtonBuilder create() {
		button = Decorator.decorateButtonFactory(new Button());
		return getInstance();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see szakdolgozat.podcast.builder.Builder#build()
	 */
	@Override
	public Button build() {
		return button;
	}

	/**
	 * This method is used for adding a decorated image to our button. The
	 * parameter is only containing the string of the image. Inside the method,
	 * the decorator will decorate and make an instance of it.
	 *
	 * @param ImageURL
	 *            the imageUrl is the Url for the image.
	 * 
	 * @return the media button builder instance.
	 */
	public MediaButtonBuilder image(final String ImageURL) {
		final Rectangle rectangleButtonShape = new Rectangle(MediaControlDecorator.RECTANGLESIZE,
				MediaControlDecorator.RECTANGLESIZE);
		rectangleButtonShape.setArcHeight(MediaControlDecorator.ARCHEIGHT);
		rectangleButtonShape.setArcWidth(MediaControlDecorator.ARCHWIDTH);
		button.setShape(rectangleButtonShape);
		final ImageView buttonImageView = new ImageView(
				new Image(getClass().getClassLoader().getResource(ImageURL).toExternalForm()));
		button.setGraphic(buttonImageView);
		button.setMinSize(MediaControlDecorator.BUTTONSIZE, MediaControlDecorator.BUTTONSIZE);
		button.setMaxSize(MediaControlDecorator.BUTTONSIZE, MediaControlDecorator.BUTTONSIZE);
		return getInstance();
	}

	/**
	 * This method is used for setting the default disability property of the
	 * button.
	 *
	 * @param bool
	 *            the boolean variable that makes the property.
	 * 
	 * @return the media button builder instance.
	 */
	public MediaButtonBuilder disable(final boolean bool) {
		button.setDisable(bool);
		return getInstance();
	}

	/**
	 * This method will add a text to our button. We only get the string of the
	 * text as parameter, the instantiation is inside the decoration progress.
	 *
	 * @param text
	 *            that we would like to add.
	 * 
	 * @return the media button builder instance.
	 */
	public MediaButtonBuilder text(final String text) {
		button.setText(text);
		return getInstance();
	}

}
