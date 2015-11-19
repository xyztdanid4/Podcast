package szakdolgozat.podcast.gui.builder;

import javafx.scene.control.Label;
import szakdolgozat.podcast.gui.decorator.Decorator;

/**
 * The Class LabelBuilder.
 * 
 * @author Daniel Toth
 * @version 0.0.1
 * @since 0.0.1
 * 
 *        This class is used for assemble a new Label object. Basically it is a
 *        wrapper class that makes easier to make instance of objects.
 */
public class LabelBuilder implements Builder {

	/** The instance of the builder object. */
	static LabelBuilder instance = new LabelBuilder();

	/**
	 * The label that we would like to assemble. This is the object that we
	 * return after the construction.
	 */
	private static Label label;

	/**
	 * Instantiates a new label builder.
	 */
	private LabelBuilder() {

	}

	/**
	 * Gets the single instance of LabelBuilder.
	 *
	 * @return single instance of LabelBuilder
	 */
	private static LabelBuilder getInstance() {
		return instance;
	}

	/**
	 * Creates the instance of our needed object through decoration progress.
	 *
	 * @return the label builder.
	 */
	public static LabelBuilder create() {
		label = Decorator.decorateLabelFactory(new Label());
		return getInstance();
	}

	/**
	 * This method will add a text to our hbox. We only get the string of the
	 * text as parameter, the instantiation is inside the decoration progress.
	 *
	 * @param text
	 *            that we would like to add.
	 * @return the label builder.
	 */
	public LabelBuilder text(final String text) {
		label.setText(text);
		return getInstance();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see szakdolgozat.podcast.builder.Builder#build()
	 */
	@Override
	public Label build() {
		return label;
	}
}
