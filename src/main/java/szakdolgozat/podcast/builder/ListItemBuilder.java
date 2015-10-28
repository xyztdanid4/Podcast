package szakdolgozat.podcast.builder;

import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import szakdolgozat.podcast.gui.decorator.RecommendListDecorator;

public class ListItemBuilder {
	static ListItemBuilder instance = new ListItemBuilder();

	private ListItemBuilder() {

	}

	public static ListItemBuilder getInstance() {
		return instance;
	}

	public HBox build(final String image, final String artist) {
		Rectangle imageRectangle = new Rectangle();
		RecommendListDecorator.decorateRectangle(imageRectangle, RecommendListDecorator.SMALLRECTANGLEHEIGHT,
				RecommendListDecorator.SMALLRECTANGLEWIDTH, image);
		Text artistName = new Text(
				artist.length() > 20 ? new String(new StringBuilder(artist.substring(0, 20)).append("...")) : artist);
		RecommendListDecorator.decorateText(artistName, RecommendListDecorator.SMALLTEXTSIZE);
		HBox itemHBox = new HBox(RecommendListDecorator.PADDING, imageRectangle, artistName);
		RecommendListDecorator.decorateHBox(itemHBox);
		return itemHBox;
	}

	public HBox build(final String artist) {
		Text artistName = new Text(
				artist.length() > 20 ? new String(new StringBuilder(artist.substring(0, 20)).append("...")) : artist);
		RecommendListDecorator.decorateText(artistName, RecommendListDecorator.SMALLTEXTSIZE);
		HBox itemHBox = new HBox(RecommendListDecorator.PADDING, artistName);
		RecommendListDecorator.decorateHBox(itemHBox);
		return itemHBox;
	}

}
