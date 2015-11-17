package szakdolgozat.podcast.builder;

import javafx.scene.text.Text;
import szakdolgozat.podcast.gui.decorator.Decorator;

/**
 * The Class TextBuilder.
 * 
 * @author Daniel Toth
 * @version 0.0.1
 * @since 0.0.1
 * 
 *        This class is used for assemble a new Text object. Basically it is a
 *        wrapper class that makes easier to make instance of objects.
 */

public class TextBuilder implements Builder {

	/** The instance of the builder object. */
	static TextBuilder instance = new TextBuilder();

	/**
	 * The text that we would like to assemble. This is the object that we
	 * return after the construction.
	 */
	private static Text text;

	/**
	 * Instantiates a new text builder.
	 */
	public TextBuilder() {

	}

	/**
	 * Creates the instance of our needed object through decoration progress.
	 *
	 * @return the text builder
	 */
	public static TextBuilder create() {
		text = new Text();
		return getInstance();
	}

	/**
	 * Gets the single instance of TextBuilder object.
	 *
	 * @return single instance of TextBuilder object.
	 */
	private static TextBuilder getInstance() {
		return instance;
	}

	/**
	 * This method will create and add a text object to our text. In the
	 * application there are two sizes of texts. That is why we need two kind of
	 * text generating method. As parameter we only get the srting, the text
	 * object's instance is maked by the decorator object. This method is
	 * responsible for creating and adding the bigger sized text.
	 *
	 * @param string
	 *            that we would like to add.
	 * @return the text builder
	 */
	public TextBuilder bigText(final String string) {
		text = Decorator.decorateTextFactory(new Text(new String(string)), Decorator.BIGTEXTSIZE);
		return getInstance();
	}

	/**
	 * This method will create and add a text object to our text. In the
	 * application there are two sizes of texts. That is why we need two kind of
	 * text generating method. As parameter we only get the srting, the text
	 * object's instance is maked by the decorator object. This method is
	 * responsible for creating and adding the smaller sized text.
	 *
	 * @param string
	 *            which is in string in the parameter.
	 * 
	 * @return the text builder object.
	 */
	public TextBuilder smallText(final String string) {
		text = Decorator.decorateTextFactory(new Text(new String(string)), Decorator.SMALLTEXTSIZE);
		return getInstance();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see szakdolgozat.podcast.builder.Builder#build()
	 */
	@Override
	public Text build() {
		return text;
	}
}
