package szakdolgozat.podcast.builder;

import javafx.scene.control.TextField;
import szakdolgozat.podcast.gui.decorator.Decorator;

/**
 * The Class TextFieldBuilder.
 * 
 * @author Daniel Toth
 * @version 0.0.1
 * @since 0.0.1
 * 
 *        This class is used for assemble a new textField object. Basically it
 *        is a wrapper class that makes easier to make instance of objects.
 */
public class TextFieldBuilder implements Builder {

	/** The instance of the builder object. */
	static TextFieldBuilder instance = new TextFieldBuilder();

	/**
	 * The textField that we would like to assemble. This is the object that we
	 * return after the construction.
	 */
	private static TextField textField;

	/**
	 * Instantiates a new text field builder.
	 */
	private TextFieldBuilder() {

	}

	/**
	 * Gets the single instance of TextFieldBuilder.
	 *
	 * @return single instance of TextFieldBuilder.
	 */
	private static TextFieldBuilder getInstance() {
		return instance;
	}

	/**
	 * Creates the instance of our needed object through decoration progress.
	 *
	 * @return the text field builder object.
	 */
	public static TextFieldBuilder create() {
		textField = Decorator.decorateTextFieldFactory(new TextField());
		return getInstance();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see szakdolgozat.podcast.builder.Builder#build()
	 */
	@Override
	public TextField build() {
		return textField;
	}

	/**
	 * This method will set the size of our textField object.
	 *
	 * @param width
	 *            the width of our textfield.
	 * @param height
	 *            the height of our textfield.
	 * 
	 * @return the text field builder object.
	 */
	public TextFieldBuilder size(final int width, final int height) {
		textField.setPrefSize(width, height);
		textField.setMaxSize(width, height);
		return getInstance();
	}

	/**
	 * This method is used for setting as given by parameter string as promptext
	 * for our textfield.
	 *
	 * @param string
	 *            we would like see as textfield.
	 * 
	 * @return the text field builder object.
	 */
	public TextFieldBuilder promptText(final String string) {
		textField.setPromptText(string);
		return getInstance();
	}
}
