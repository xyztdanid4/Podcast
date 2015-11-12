package szakdolgozat.podcast.builder;

import javafx.scene.control.CheckBox;
import szakdolgozat.podcast.gui.decorator.Decorator;

public class CheckBoxBuilder implements Builder {
	static CheckBoxBuilder instance = new CheckBoxBuilder();
	private static CheckBox checkBox;

	private CheckBoxBuilder() {

	}

	private static CheckBoxBuilder getInstance() {
		return instance;
	}

	public static CheckBoxBuilder create() {
		// checkBox = new CheckBox();
		checkBox = Decorator.decorateCheckBoxFactory(new CheckBox());
		return getInstance();
	}

	public CheckBoxBuilder text(final String text) {
		checkBox.setText(text);
		return getInstance();
	}

	public CheckBoxBuilder setDefaultValue(final boolean bool) {
		checkBox.setSelected(bool);
		return getInstance();
	}

	@Override
	public CheckBox build() {
		return checkBox;
	}

}
