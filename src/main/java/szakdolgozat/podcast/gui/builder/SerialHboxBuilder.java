package szakdolgozat.podcast.gui.builder;

import javafx.scene.text.Text;
import szakdolgozat.podcast.gui.decorator.Decorator;
import szakdolgozat.podcast.gui.hbox.SerialHBox;

// TODO: Auto-generated Javadoc
/**
 * The Class SerialHboxBuilder.
 * 
 * @author Daniel Toth
 * @version 0.0.1
 * @since 0.0.1
 * 
 *        This class is used for assemble a new serial HBox object. Basically it
 *        is a wrapper class that makes easier to make instance of objects.
 */
public class SerialHboxBuilder implements Builder {

	/** The instance of the builder object. */
	static SerialHboxBuilder instance = new SerialHboxBuilder();

	/**
	 * The hbox that we would like to assemble. This is the object that we
	 * return after the construction.
	 */
	private static SerialHBox hbox;

	/**
	 * Creates the instance of our needed object through decoration progress.
	 *
	 * @return the serial hbox builder instance.
	 */
	public static SerialHboxBuilder create() {
		hbox = Decorator.decorateSerialHBoxFactory(new SerialHBox(Decorator.HBOXPADDING));
		return getInstance();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see szakdolgozat.podcast.builder.Builder#build()
	 */
	@Override
	public SerialHBox build() {
		return hbox;
	}

	/**
	 * Gets the single instance of SerialHboxBuilder.
	 *
	 * @return single instance of SerialHboxBuilder
	 */
	private static SerialHboxBuilder getInstance() {
		return instance;
	}

	/**
	 * This method will add a special text to our hbox. We only get the string
	 * of the text as parameter, the instantiation is inside the decoration
	 * progress. It is special cause we store only the first 20 letters of the
	 * string.
	 *
	 * @param artist
	 *            text we would like to add.
	 * @return the serial hbox builder instance.
	 */
	public SerialHboxBuilder artist(final String artist) {
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
	 * responsible for creating and adding the smaller sized text.
	 *
	 * @param text
	 *            which is in string in the parameter.
	 * @return the serial hbox builder instance.
	 */
	public SerialHboxBuilder smallText(final String text) {
		hbox.getChildren().add(Decorator.decorateTextFactory(new Text(text), Decorator.SMALLTEXTSIZE));
		return getInstance();
	}

	/**
	 * This method will set a default effect for our button. If we use this the
	 * decoration object will set the effect on. Default is not to use this, if
	 * a button has an action we should use this method to indicate that it has
	 * an action.
	 *
	 * @return the serial hbox builder instance.
	 */
	public SerialHboxBuilder effectOn() {
		hbox = Decorator.decorateSerialEffect(hbox);
		return getInstance();
	}

	/**
	 * This method will add a special text to our hbox. We only get the string
	 * of the text as parameter, the instantiation is inside the decoration
	 * progress. It is special cause we store only the first 20 letters of the
	 * string.
	 *
	 * @param artist
	 *            the artist we would like to add.
	 * @return the serial hbox builder instance.
	 */
	public SerialHboxBuilder title(final String artist) {
		hbox.getChildren()
				.add(Decorator.decorateTextFactory(
						new Text(artist.length() > 40
								? new String(new StringBuilder(artist.substring(0, 40)).append("...")) : artist),
				Decorator.SMALLTEXTSIZE));
		return getInstance();
	}

}
