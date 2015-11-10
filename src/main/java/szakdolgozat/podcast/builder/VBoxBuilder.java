package szakdolgozat.podcast.builder;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import szakdolgozat.podcast.gui.decorator.Decorator;

public class VBoxBuilder {
	static VBoxBuilder instance = new VBoxBuilder();
	private static VBox vbox;

	private VBoxBuilder() {

	}

	private static VBoxBuilder getInstance() {
		return instance;
	}

	public VBox build() {
		return vbox;
	}

	public VBoxBuilder checkBox(final CheckBox checkbox) {
		vbox.getChildren().add(Decorator.decorateCheckBoxFactory(checkbox));
		return getInstance();
	}

	public static VBoxBuilder create() {
		vbox = Decorator.decorateVBoxFactory(new VBox(Decorator.VBOXPADDING));
		vbox.setAlignment(Pos.CENTER_LEFT);
		return getInstance();
	}

	public VBoxBuilder topLeftAlignment() {
		vbox.setAlignment(Pos.TOP_LEFT);
		return getInstance();
	}

	public VBoxBuilder Button(final Button button) {
		vbox.getChildren().add(Decorator.decorateButtonFactory(button));
		return getInstance();
	}

	public VBoxBuilder noButton(final Button button) {
		vbox.getChildren().add(Decorator.decorateButtonFactory(button));
		return getInstance();
	}

	public VBoxBuilder centerLeftAlignment() {
		vbox.setAlignment(Pos.CENTER_LEFT);
		return getInstance();
	}

	public VBoxBuilder bigText(final String text) {
		vbox.getChildren().add(Decorator.decorateTextFactory(new Text(text), Decorator.BIGTEXTSIZE));
		return getInstance();
	}

	public VBoxBuilder noTextField(final TextField textField) {
		vbox.getChildren().add(textField);
		return getInstance();
	}

	public VBoxBuilder smallText(final String text) {
		vbox.getChildren().add(Decorator.decorateTextFactory(new Text(text), Decorator.SMALLTEXTSIZE));
		return getInstance();
	}

	public VBoxBuilder noHBox(final HBox hbox) {
		vbox.getChildren().add(hbox);
		return getInstance();
	}

	public VBoxBuilder noListView(final ListView<HBox> listVIew) {
		vbox.getChildren().add(listVIew);
		return getInstance();
	}

	public VBoxBuilder bigRectangle(final String image) {
		vbox.getChildren().add(Decorator.decorateRectangleFactory(new Rectangle(), Decorator.BIGRECTANGLEHEIGHT,
				Decorator.BIGRECTANGLEWIDTH, image));
		return getInstance();
	}

	public VBoxBuilder size(final int width, final int height) {
		vbox.setPrefSize(width, height);
		vbox.setMaxSize(width, height);
		return getInstance();
	}

}
