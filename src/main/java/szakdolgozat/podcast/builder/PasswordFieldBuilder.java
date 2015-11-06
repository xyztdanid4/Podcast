package szakdolgozat.podcast.builder;

import javafx.scene.control.PasswordField;
import szakdolgozat.podcast.gui.decorator.Decorator;

public class PasswordFieldBuilder {
	static PasswordFieldBuilder instance = new PasswordFieldBuilder();
	private static PasswordField passwordField;

	private PasswordFieldBuilder() {

	}

	private static PasswordFieldBuilder getInstance() {
		return instance;
	}

	public static PasswordFieldBuilder create() {
		passwordField = Decorator.decoratePasswordFieldFactory(new PasswordField());
		return getInstance();
	}

	public PasswordField build() {
		return passwordField;
	}

	public PasswordFieldBuilder promptText(final String text) {
		passwordField.setPromptText(text);
		return getInstance();
	}
}
