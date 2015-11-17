package szakdolgozat.podcast.builder;

import javafx.scene.control.Slider;

/**
 * The Class SliderBuilder.
 * 
 * @author Daniel Toth
 * @version 0.0.1
 * @since 0.0.1
 * 
 *        This class is used for assemble a new Slider object. Basically it is a
 *        wrapper class that makes easier to make instance of objects.
 */
public class SliderBuilder implements Builder {

	/** The instance of the builder object. */
	static SliderBuilder instance = new SliderBuilder();

	/**
	 * The slider that we would like to assemble. This is the object that we
	 * return after the construction.
	 */
	private static Slider slider;

	/**
	 * Instantiates a new slider builder.
	 */
	private SliderBuilder() {

	}

	/**
	 * Gets the single instance of SliderBuilder.
	 *
	 * @return single instance of SliderBuilder
	 */
	private static SliderBuilder getInstance() {
		return instance;
	}

	/**
	 * Creates the instance of our needed object through decoration progress.
	 *
	 * @return the slider builder object.
	 */
	public static SliderBuilder create() {
		slider = new Slider();
		return getInstance();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see szakdolgozat.podcast.builder.Builder#build()
	 */
	@Override
	public Slider build() {
		return slider;
	}
}
