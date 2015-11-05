package szakdolgozat.podcast.builder;

import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import szakdolgozat.podcast.gui.decorator.Decorator;
import szakdolgozat.podcast.gui.decorator.PodcastBPDecorator;
import szakdolgozat.podcast.gui.samples.ButtonSample;
import szakdolgozat.podcast.gui.samples.TextFieldSample;

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

	public HBoxBuilder smallRectangle(String image) {
		hbox.getChildren().add(Decorator.decorateRectangleFactory(new Rectangle(), Decorator.SMALLRECTANGLEHEIGHT,
				Decorator.SMALLRECTANGLEWIDTH, image));
		return getInstance();
	}

	public HBoxBuilder bigRectangle(String image) {
		hbox.getChildren().add(Decorator.decorateRectangleFactory(new Rectangle(), Decorator.BIGRECTANGLEHEIGHT,
				Decorator.BIGRECTANGLEWIDTH, image));
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

	public HBoxBuilder bigText(String text) {
		hbox.getChildren().add(Decorator.decorateTextFactory(new Text(text), Decorator.BIGTEXTSIZE));
		return getInstance();
	}

	public HBoxBuilder title(String artist) {
		hbox.getChildren()
				.add(Decorator.decorateTextFactory(
						new Text(artist.length() > 40
								? new String(new StringBuilder(artist.substring(0, 40)).append("...")) : artist),
				Decorator.SMALLTEXTSIZE));
		return getInstance();
	}

	public HBoxBuilder button(ButtonSample button) {
		hbox.getChildren().add(PodcastBPDecorator.decorateButtonSampleFactory(button));
		return getInstance();
	}

	public HBoxBuilder noDecoratebutton(ButtonSample button) {
		hbox.getChildren().add(button);
		return getInstance();
	}

	public HBoxBuilder textField(TextFieldSample textField, final int width, final int height) {
		hbox.getChildren().add(PodcastBPDecorator.decorateTextFieldSampleFactory(textField, width, height));
		return getInstance();
	}

	public HBoxBuilder size(final int width, final int height) {
		hbox.setPrefSize(width, height);
		hbox.setMaxSize(width, height);
		return getInstance();
	}

	public HBox build() {
		return hbox;
	}

	public static HBoxBuilder getInstance() {
		return instance;
	}

}
