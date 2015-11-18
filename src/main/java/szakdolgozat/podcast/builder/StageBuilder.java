package szakdolgozat.podcast.builder;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import szakdolgozat.podcast.gui.stage.BaseStage;

/**
 * The Class StageBuilder.
 * 
 * @author Daniel Toth
 * @version 0.0.1
 * @since 0.0.1
 * 
 *        This class is used for assemble a new Stage object. Basically it is a
 *        wrapper class that makes easier to make instance of objects.
 */
public class StageBuilder implements Builder {

	/** The instance of the builder object. */
	static StageBuilder instance = new StageBuilder();

	/**
	 * The stage that we would like to assemble. This is the object that we
	 * return after the construction.
	 */
	private static BaseStage stage;

	/**
	 * Instantiates a new stage builder.
	 */
	public StageBuilder() {

	}

	/**
	 * Creates the instance of our needed object through decoration progress.
	 *
	 * @param title
	 *            that we would like to give to our stage.
	 * 
	 * @return the stage builder object.
	 */
	public static StageBuilder create(final String title) {
		stage = new BaseStage(title);
		return getInstance();
	}

	/**
	 * Gets the single instance of StageBuilder.
	 *
	 * @return single instance of StageBuilder object.
	 */
	private static StageBuilder getInstance() {
		return instance;
	}

	/**
	 * This method is used for settings the scene containing the folowwing
	 * attributes.
	 *
	 * @param pane
	 *            that we would like to state as root.
	 * @param width
	 *            the width size.
	 * @param height
	 *            the height size.
	 * 
	 * @return the stage builder object.
	 */
	public StageBuilder scene(final Pane pane, final int width, final int height) {
		stage.setScene(new Scene(pane, width, height));
		return getInstance();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see szakdolgozat.podcast.builder.Builder#build()
	 */
	@Override
	public BaseStage build() {
		return stage;
	}
}
