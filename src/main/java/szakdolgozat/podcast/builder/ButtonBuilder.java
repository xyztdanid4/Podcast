package szakdolgozat.podcast.builder;

import javafx.scene.control.Button;
import szakdolgozat.podcast.gui.decorator.Decorator;

/**
 * The Class ButtonBuilder.
 * 
 * @author Daniel Toth
 * @version 0.0.1
 * @since 0.0.1
 * 
 *        This class is used for assemble a new Button object. Basically it is a
 *        wrapper class that makes easier to make instance of objects.
 */
public class ButtonBuilder implements Builder {

	/** The instance of the builder object. */
	static ButtonBuilder instance = new ButtonBuilder();

	/**
	 * The button that we would like to assemble. This is the object that we
	 * return after the construction.
	 */
	private static Button button;

	/**
	 * Instantiates a new button builder.
	 */
	private ButtonBuilder() {

	}

	/**
	 * Gets the single instance of ButtonBuilder.
	 *
	 * @return single instance of ButtonBuilder
	 */
	private static ButtonBuilder getInstance() {
		return instance;
	}

	/**
	 * Creates the button object that we would like to assemble. The
	 * instantiation progress including the decoration. The decoration progress
	 * will create the new instance of our needed variable.
	 *
	 * @return the button builder
	 */
	public static ButtonBuilder create() {
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
	 * This method decides the default disability property of the button depends
	 * on the parameter.
	 *
	 * @param bool,
	 *            the button default disable value.
	 * 
	 * @return the button builder
	 */
	public ButtonBuilder disable(final boolean bool) {
		button.setDisable(bool);
		return getInstance();
	}

	/**
	 * This method will text the button.
	 *
	 * @param text
	 *            which will be on the button.
	 * 
	 * @return the button builder
	 */
	public ButtonBuilder text(final String text) {
		button.setText(text);
		return getInstance();
	}

}
