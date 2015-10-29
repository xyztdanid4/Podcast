package szakdolgozat.podcast.builder;

import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import szakdolgozat.podcast.gui.decorator.Decorator;

public class HBoxBuilder {
	static HBoxBuilder instance = new HBoxBuilder();

	private HBoxBuilder() {

	}

	public static HBoxBuilder getInstance() {
		return instance;
	}

	public HBox build(final String image, String artist) {
		// ez egy jo megoldás------------------------------------------
		// Rectangle imageRectangle = Decorator.decorateRectangleFactory(new
		// Rectangle(), Decorator.SMALLRECTANGLEHEIGHT,
		// Decorator.SMALLRECTANGLEWIDTH, image);
		// Text artistName = Decorator
		// .decorateTextFactory(
		// new Text(artist.length() > 20
		// ? new String(new StringBuilder(artist.substring(0,
		// 20)).append("...")) : artist),
		// Decorator.SMALLTEXTSIZE);
		// HBox itemHBox = Decorator.decorateHBoxFactory(new
		// HBox(Decorator.PADDING, imageRectangle, artistName));
		// return itemHBox;
		// jo megoldás
		// vége------------------------------------------------------

		// de itt van egy sorban : D
		return Decorator.decorateHBoxFactory(new HBox(10,
				Decorator.decorateRectangleFactory(new Rectangle(), Decorator.SMALLRECTANGLEHEIGHT,
						Decorator.SMALLRECTANGLEWIDTH, image),
				Decorator.decorateTextFactory(
						new Text(artist.length() > 20
								? new String(new StringBuilder(artist.substring(0, 20)).append("...")) : artist),
						Decorator.SMALLTEXTSIZE)));
	}

	public HBox build(String artist) {
		// jo megoldas--------------------------------
		// Text artistName = new Text(
		// artist.length() > 20 ? new String(new
		// StringBuilder(artist.substring(0, 20)).append("...")) : artist);
		// Decorator.decorateTextFactory(artistName, Decorator.SMALLTEXTSIZE);
		// HBox itemHBox = new HBox(Decorator.PADDING, artistName);
		// Decorator.decorateHBoxFactory(itemHBox);
		// return itemHBox;
		// jo megoldas vege-------------------------------
		return Decorator.decorateHBoxFactory(new HBox(Decorator.PADDING,
				Decorator.decorateTextFactory(
						new Text(artist.length() > 20
								? new String(new StringBuilder(artist.substring(0, 20)).append("...")) : artist),
				Decorator.SMALLTEXTSIZE)));
	}

}
