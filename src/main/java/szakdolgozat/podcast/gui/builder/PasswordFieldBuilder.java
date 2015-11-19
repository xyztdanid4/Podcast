package szakdolgozat.podcast.gui.builder;

import javafx.scene.control.PasswordField;
import szakdolgozat.podcast.gui.decorator.Decorator;

/**
 * The Class PasswordFieldBuilder.
 * 
 * @author Daniel Toth
 * @version 0.0.1
 * @since 0.0.1
 * 
 *        This class is used for assemble a new PasswordField object. Basically
 *        it is a wrapper class that makes easier to make instance of objects.
 * 
 */
public class PasswordFieldBuilder implements Builder {

	/** The instance of the builder object. */
	static PasswordFieldBuilder instance = new PasswordFieldBuilder();

	/**
	 * The passwordfield that we would like to assemble. This is the object that
	 * we return after the construction.
	 */
	private static PasswordField passwordField;

	/**
	 * Instantiates a new password field builder.
	 */
	private PasswordFieldBuilder() {

	}

	/**
	 * Gets the single instance of PasswordFieldBuilder.
	 *
	 * @return single instance of PasswordFieldBuilder
	 */
	private static PasswordFieldBuilder getInstance() {
		return instance;
	}

	/**
	 * Creates the instance of our needed object through decoration progress.
	 *
	 * @return the media button builder instance.
	 */
	public static PasswordFieldBuilder create() {
		passwordField = Decorator.decoratePasswordFieldFactory(new PasswordField());
		return getInstance();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see szakdolgozat.podcast.builder.Builder#build()
	 */
	@Override
	public PasswordField build() {
		return passwordField;
	}

	/**
	 * This method is used for setting as given by parameter string as promptext
	 * for our passwordfield.
	 *
	 * @param text
	 *            we would like see as promptext.
	 * @return the media button builder instance.
	 */
	public PasswordFieldBuilder promptText(final String text) {
		passwordField.setPromptText(text);
		return getInstance();
	}
}
