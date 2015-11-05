package szakdolgozat.podcast.builder;

import javafx.geometry.Pos;
import javafx.scene.control.ListView;
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

	public static VBoxBuilder getInstance() {
		return instance;
	}

	public VBox build() {
		return vbox;
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

	public VBoxBuilder bigText(final String text) {
		vbox.getChildren().add(Decorator.decorateTextFactory(new Text(text), Decorator.BIGTEXTSIZE));
		return getInstance();
	}

	public VBoxBuilder smallText(final String text) {
		vbox.getChildren().add(Decorator.decorateTextFactory(new Text(text), Decorator.SMALLTEXTSIZE));
		return getInstance();
	}

	public VBoxBuilder noHBox(HBox hbox) {
		vbox.getChildren().add(hbox);
		return getInstance();
	}

	public VBoxBuilder bigRectangle(String image) {
		vbox.getChildren().add(Decorator.decorateRectangleFactory(new Rectangle(), Decorator.BIGRECTANGLEHEIGHT,
				Decorator.BIGRECTANGLEWIDTH, image));
		return getInstance();
	}

	public VBoxBuilder listView(ListView listView) {

		return getInstance();
	}

}
