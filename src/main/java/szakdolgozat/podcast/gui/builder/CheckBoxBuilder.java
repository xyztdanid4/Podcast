package szakdolgozat.podcast.gui.builder;

import javafx.scene.control.CheckBox;
import szakdolgozat.podcast.gui.decorator.Decorator;

/**
 * The Class CheckBoxBuilder.
 * 
 * @author Daniel Toth
 * @version 0.0.1
 * @since 0.0.1
 * 
 *        This class is used for assemble a new CheckBox object. Basically it is
 *        a wrapper class that makes easier to make instance of objects.
 */
public class CheckBoxBuilder implements Builder {

	/** The instance of the builder object. */
	static CheckBoxBuilder instance = new CheckBoxBuilder();

	/**
	 * The checkBox that we would like to assemble. This is the object that we
	 * return after the construction.
	 */
	private static CheckBox checkBox;

	/**
	 * Instantiates a new check box builder.
	 */
	private CheckBoxBuilder() {

	}

	/**
	 * Gets the single instance of CheckBoxBuilder.
	 *
	 * @return single instance of CheckBoxBuilder
	 */
	private static CheckBoxBuilder getInstance() {
		return instance;
	}

	/**
	 * Creates the checkBox object that we would like to return after the
	 * construction. The creation progress is contains decoration. The instance
	 * will be created by the decorator method.
	 *
	 * @return the check box builder
	 */
	public static CheckBoxBuilder create() {
		checkBox = Decorator.decorateCheckBoxFactory(new CheckBox());
		return getInstance();
	}

	/**
	 * This method will give the text attribute of the checkBox
	 *
	 * @param text,
	 *            the text which is near the checkBox.
	 * 
	 * @return the check box builder
	 */
	public CheckBoxBuilder text(final String text) {
		checkBox.setText(text);
		return getInstance();
	}

	/**
	 * This method will set the default selected property of the checkBox.
	 *
	 * @param bool,
	 *            boolean variable which is represent the state of the selected
	 *            property.
	 * 
	 * @return the check box builder
	 */
	public CheckBoxBuilder setDefaultValue(final boolean bool) {
		checkBox.setSelected(bool);
		return getInstance();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see szakdolgozat.podcast.builder.Builder#build()
	 */
	@Override
	public CheckBox build() {
		return checkBox;
	}

}
