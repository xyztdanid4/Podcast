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
import szakdolgozat.podcast.data.podcast.PodcastEpisode;
import szakdolgozat.podcast.gui.decorator.Decorator;

public class SerialVBoxBuilder implements Builder {
	static SerialVBoxBuilder instance = new SerialVBoxBuilder();
	private static VBox vbox;

	private SerialVBoxBuilder() {

	}

	private static SerialVBoxBuilder getInstance() {
		return instance;
	}

	@Override
	public VBox build() {
		return vbox;
	}

	public SerialVBoxBuilder checkBox(final CheckBox checkbox) {
		// vbox.getChildren().add(Decorator.decorateCheckBoxFactory(checkbox));
		vbox.getChildren().add(checkbox);
		return getInstance();
	}

	public static SerialVBoxBuilder create() {
		vbox = Decorator.decorateVBoxFactory(new VBox(Decorator.VBOXPADDING));
		vbox.setAlignment(Pos.CENTER_LEFT);
		return getInstance();
	}

	public SerialVBoxBuilder topLeftAlignment() {
		vbox.setAlignment(Pos.TOP_LEFT);
		return getInstance();
	}

	public SerialVBoxBuilder Button(final Button button) {
		vbox.getChildren().add(Decorator.decorateButtonFactory(button));
		return getInstance();
	}

	public SerialVBoxBuilder noButton(final Button button) {
		vbox.getChildren().add(Decorator.decorateButtonFactory(button));
		return getInstance();
	}

	public SerialVBoxBuilder centerLeftAlignment() {
		vbox.setAlignment(Pos.CENTER_LEFT);
		return getInstance();
	}

	public SerialVBoxBuilder bigText(final String text) {
		vbox.getChildren().add(Decorator.decorateTextFactory(new Text(text), Decorator.BIGTEXTSIZE));
		return getInstance();
	}

	public SerialVBoxBuilder noTextField(final TextField textField) {
		vbox.getChildren().add(textField);
		return getInstance();
	}

	public SerialVBoxBuilder smallText(final String text) {
		vbox.getChildren().add(Decorator.decorateTextFactory(new Text(text), Decorator.SMALLTEXTSIZE));
		return getInstance();
	}

	public SerialVBoxBuilder noHBox(final HBox hbox) {
		vbox.getChildren().add(hbox);
		return getInstance();
	}

	public SerialVBoxBuilder noListView(final ListView<PodcastEpisode> listView) {
		vbox.getChildren().add(listView);
		return getInstance();
	}

	public SerialVBoxBuilder bigRectangle(final String image) {
		vbox.getChildren().add(Decorator.decorateRectangleFactory(new Rectangle(), Decorator.BIGRECTANGLEHEIGHT,
				Decorator.BIGRECTANGLEWIDTH, image));
		return getInstance();
	}

	public SerialVBoxBuilder size(final int width, final int height) {
		vbox.setPrefSize(width, height);
		vbox.setMaxSize(width, height);
		return getInstance();
	}
}
