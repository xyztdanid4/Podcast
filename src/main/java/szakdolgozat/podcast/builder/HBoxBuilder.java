package szakdolgozat.podcast.builder;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import szakdolgozat.podcast.gui.decorator.Decorator;

public class HBoxBuilder {
	static HBoxBuilder instance = new HBoxBuilder();
	private static HBox hbox;

	private HBoxBuilder() {

	}

	public static HBoxBuilder create() {
		hbox = Decorator.decorateHBoxFactory(new HBox(Decorator.HBOXPADDING));
		return getInstance();
	}

	public HBoxBuilder noHBox(final HBox hbox) {
		hbox.getChildren().add(hbox);
		return getInstance();
	}

	public HBoxBuilder effectOn() {
		hbox = Decorator.decorateEffect(hbox);
		return getInstance();
	}

	public static HBoxBuilder noCreate() {
		hbox = new HBox(Decorator.HBOXPADDING);
		return getInstance();
	}

	public HBoxBuilder image(final String image) {
		hbox.getChildren().add(Decorator.decorateImageViewFactory(new ImageView(image), Decorator.SMALLRECTANGLEHEIGHT,
				Decorator.SMALLRECTANGLEWIDTH));
		return getInstance();
	}

	public HBoxBuilder smallRectangle(final String image) {
		hbox.getChildren().add(Decorator.decorateRectangleFactory(new Rectangle(), Decorator.SMALLRECTANGLEHEIGHT,
				Decorator.SMALLRECTANGLEWIDTH, image));
		return getInstance();
	}

	public HBoxBuilder bigRectangle(final String image) {
		hbox.getChildren().add(Decorator.decorateRectangleFactory(new Rectangle(), Decorator.BIGRECTANGLEHEIGHT,
				Decorator.BIGRECTANGLEWIDTH, image));
		return getInstance();
	}

	public HBoxBuilder checkBox(final CheckBox checkbox) {
		hbox.getChildren().add(Decorator.decorateCheckBoxFactory(checkbox));
		return getInstance();
	}

	public HBoxBuilder artist(final String artist) {
		hbox.getChildren()
				.add(Decorator.decorateTextFactory(
						new Text(artist.length() > 20
								? new String(new StringBuilder(artist.substring(0, 20)).append("...")) : artist),
				Decorator.SMALLTEXTSIZE));
		return getInstance();
	}

	public HBoxBuilder bigText(final String text) {
		hbox.getChildren().add(Decorator.decorateTextFactory(new Text(text), Decorator.BIGTEXTSIZE));
		return getInstance();
	}

	public HBoxBuilder smallText(final String text) {
		hbox.getChildren().add(Decorator.decorateTextFactory(new Text(text), Decorator.SMALLTEXTSIZE));
		return getInstance();
	}

	public HBoxBuilder title(final String artist) {
		hbox.getChildren()
				.add(Decorator.decorateTextFactory(
						new Text(artist.length() > 40
								? new String(new StringBuilder(artist.substring(0, 40)).append("...")) : artist),
				Decorator.SMALLTEXTSIZE));
		return getInstance();
	}

	public HBoxBuilder button(final Button button) {
		hbox.getChildren().add(Decorator.decorateButtonFactory(button));
		return getInstance();
	}

	public HBoxBuilder noButton(final Button button) {
		hbox.getChildren().add(button);
		return getInstance();
	}

	public HBoxBuilder noText(final Text text) {
		hbox.getChildren().add(text);
		return getInstance();
	}

	public HBoxBuilder noTextField(final TextField textField) {
		hbox.getChildren().add(textField);
		return getInstance();
	}

	public HBoxBuilder textField(final TextField textField, final int width, final int height) {
		hbox.getChildren().add(Decorator.decorateTextFieldFactory(textField, width, height));
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

	public HBoxBuilder noVBox(final VBox vbox) {
		vbox.setAlignment(Pos.CENTER_LEFT);
		hbox.getChildren().add(vbox);
		return getInstance();
	}

	private static HBoxBuilder getInstance() {
		return instance;
	}

}
