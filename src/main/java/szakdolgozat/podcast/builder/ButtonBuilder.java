package szakdolgozat.podcast.builder;

import javafx.scene.control.Button;
import szakdolgozat.podcast.gui.decorator.Decorator;

public class ButtonBuilder {
	static ButtonBuilder instance = new ButtonBuilder();
	private static Button button;

	private ButtonBuilder() {

	}

	private static ButtonBuilder getInstance() {
		return instance;
	}

	public static ButtonBuilder create() {
		button = Decorator.decorateButtonFactory(new Button());
		return getInstance();
	}

	public Button build() {
		return button;
	}

	public ButtonBuilder disable(final boolean bool) {
		button.setDisable(bool);
		return getInstance();
	}

	public ButtonBuilder text(final String text) {
		button.setText(text);
		return getInstance();
	}

}
