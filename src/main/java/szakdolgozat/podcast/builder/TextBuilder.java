package szakdolgozat.podcast.builder;

import javafx.scene.text.Text;
import szakdolgozat.podcast.gui.decorator.Decorator;

public class TextBuilder {
	static TextBuilder instance = new TextBuilder();
	private static Text text;

	public TextBuilder() {

	}

	public static TextBuilder create() {
		text = new Text();
		return getInstance();
	}

	private static TextBuilder getInstance() {
		return instance;
	}

	public TextBuilder bigText(final String string) {
		text = Decorator.decorateTextFactory(new Text(new String(string)), Decorator.BIGTEXTSIZE);
		return getInstance();
	}

	public TextBuilder smallText(final String string) {
		text = Decorator.decorateTextFactory(new Text(new String(string)), Decorator.SMALLTEXTSIZE);
		return getInstance();
	}

	public Text build() {
		return text;
	}
}
