package szakdolgozat.podcast.builder;

import javafx.scene.control.TextField;
import szakdolgozat.podcast.gui.decorator.Decorator;

public class TextFieldBuilder {
	static TextFieldBuilder instance = new TextFieldBuilder();
	private static TextField textField;

	private TextFieldBuilder() {

	}

	private static TextFieldBuilder getInstance() {
		return instance;
	}

	public static TextFieldBuilder create() {
		textField = Decorator.decorateTextFieldFactory(new TextField());
		return getInstance();
	}

	public TextField build() {
		return textField;
	}

	public TextFieldBuilder size(final int width, final int height) {
		textField.setPrefSize(width, height);
		textField.setMaxSize(width, height);
		return getInstance();
	}

	public TextFieldBuilder promptText(final String text) {
		textField.setPromptText(text);
		return getInstance();
	}
}
