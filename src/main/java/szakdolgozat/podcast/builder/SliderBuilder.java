package szakdolgozat.podcast.builder;

import javafx.scene.control.Slider;

public class SliderBuilder implements Builder {
	static SliderBuilder instance = new SliderBuilder();
	private static Slider slider;

	private SliderBuilder() {

	}

	private static SliderBuilder getInstance() {
		return instance;
	}

	public static SliderBuilder create() {
		// checkBox = new CheckBox();
		slider = new Slider();
		return getInstance();
	}

	@Override
	public Slider build() {
		return slider;
	}
}
