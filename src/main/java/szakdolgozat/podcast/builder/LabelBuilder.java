package szakdolgozat.podcast.builder;

import javafx.scene.control.Label;
import szakdolgozat.podcast.gui.decorator.Decorator;

public class LabelBuilder {
	static LabelBuilder instance = new LabelBuilder();
	private static Label label;

	private LabelBuilder() {

	}

	private static LabelBuilder getInstance() {
		return instance;
	}

	public static LabelBuilder create() {
		label = Decorator.decorateLabelFactory(new Label());
		return getInstance();
	}

	public LabelBuilder text(final String text) {
		label.setText(text);
		return getInstance();
	}

	public Label build() {
		return label;
	}
}
