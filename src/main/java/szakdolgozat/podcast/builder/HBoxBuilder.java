package szakdolgozat.podcast.builder;

import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import szakdolgozat.podcast.gui.decorator.Decorator;
import szakdolgozat.podcast.gui.decorator.PodcastBPDecorator;
import szakdolgozat.podcast.gui.samples.ButtonSample;

public class HBoxBuilder {
	static HBoxBuilder instance = new HBoxBuilder();
	private static HBox hbox;

	private HBoxBuilder() {

	}

	public static HBoxBuilder create() {
		hbox = Decorator.decorateHBoxFactory(new HBox(Decorator.HBOXPADDING));
		return getInstance();
	}

	public HBoxBuilder image(String image) {
		hbox.getChildren().add(Decorator.decorateRectangleFactory(new Rectangle(), Decorator.SMALLRECTANGLEHEIGHT,
				Decorator.SMALLRECTANGLEWIDTH, image));
		return getInstance();
	}

	public HBoxBuilder artist(String artist) {
		hbox.getChildren()
				.add(Decorator.decorateTextFactory(
						new Text(artist.length() > 20
								? new String(new StringBuilder(artist.substring(0, 20)).append("...")) : artist),
				Decorator.SMALLTEXTSIZE));
		return getInstance();
	}

	public HBoxBuilder button(ButtonSample button) {
		hbox.getChildren().add(PodcastBPDecorator.decorateButtonSampleFactory(button));
		return getInstance();
	}

	public HBox build() {
		return hbox;
	}

	public static HBoxBuilder getInstance() {
		return instance;
	}

	/*
	 * public static HBox build(final String image, String artist, ButtonSample
	 * button) { return Decorator.decorateHBoxFactory(new
	 * HBox(Decorator.HBOXPADDING, Decorator.decorateRectangleFactory(new
	 * Rectangle(), Decorator.SMALLRECTANGLEHEIGHT,
	 * Decorator.SMALLRECTANGLEWIDTH, image), Decorator.decorateTextFactory( new
	 * Text(artist.length() > 20 ? new String(new
	 * StringBuilder(artist.substring(0, 20)).append("...")) : artist),
	 * Decorator.SMALLTEXTSIZE),
	 * Decorator.decorateButtonSampleFactory(button))); }
	 */
	/*
	 * public HBox build(final String image, String artist) { return
	 * Decorator.decorateHBoxFactory(new HBox(Decorator.HBOXPADDING,
	 * Decorator.decorateRectangleFactory(new Rectangle(),
	 * Decorator.SMALLRECTANGLEHEIGHT, Decorator.SMALLRECTANGLEWIDTH, image),
	 * Decorator.decorateTextFactory( new Text(artist.length() > 20 ? new
	 * String(new StringBuilder(artist.substring(0, 20)).append("...")) :
	 * artist), Decorator.SMALLTEXTSIZE))); }
	 */

	/*
	 * public HBox build(String artist) { return
	 * Decorator.decorateHBoxFactory(new HBox(Decorator.PADDING,
	 * Decorator.decorateTextFactory( new Text(artist.length() > 20 ? new
	 * String(new StringBuilder(artist.substring(0, 20)).append("...")) :
	 * artist), Decorator.SMALLTEXTSIZE))); }
	 */
}
