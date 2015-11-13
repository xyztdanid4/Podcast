package szakdolgozat.podcast.builder;

import szakdolgozat.podcast.gui.borderpane.SerialHBox;
import szakdolgozat.podcast.gui.decorator.Decorator;

public class SerialHboxBuilder implements Builder {
	static SerialHboxBuilder instance = new SerialHboxBuilder();
	private static SerialHBox hbox;

	public static SerialHboxBuilder create() {
		hbox = Decorator.decorateSerialHBoxFactory(new SerialHBox(Decorator.HBOXPADDING));
		return getInstance();
	}

	@Override
	public SerialHBox build() {
		return hbox;
	}

	private static SerialHboxBuilder getInstance() {
		return instance;
	}

}
