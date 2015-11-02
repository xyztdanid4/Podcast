package szakdolgozat.podcast.builder;

import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
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
		return getInstance();
	}

	public VBoxBuilder info(String text) {
		vbox.getChildren().add(Decorator.decorateTextFactory(
				new Text(
						text.length() > 20 ? new String(new StringBuilder(text.substring(0, 20)).append("...")) : text),
				Decorator.SMALLTEXTSIZE));
		return getInstance();
	}

	public VBoxBuilder listView(ListView listView) {

		return getInstance();
	}

}
