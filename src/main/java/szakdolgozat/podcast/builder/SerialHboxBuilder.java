package szakdolgozat.podcast.builder;

import javafx.scene.text.Text;
import szakdolgozat.podcast.gui.decorator.Decorator;
import szakdolgozat.podcast.gui.hbox.SerialHBox;

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

	public SerialHboxBuilder artist(final String artist) {
		hbox.getChildren()
				.add(Decorator.decorateTextFactory(
						new Text(artist.length() > 20
								? new String(new StringBuilder(artist.substring(0, 20)).append("...")) : artist),
				Decorator.SMALLTEXTSIZE));
		return getInstance();
	}

	public SerialHboxBuilder smallText(final String text) {
		hbox.getChildren().add(Decorator.decorateTextFactory(new Text(text), Decorator.SMALLTEXTSIZE));
		return getInstance();
	}

	public SerialHboxBuilder effectOn() {
		hbox = Decorator.decorateSerialEffect(hbox);
		return getInstance();
	}

	public SerialHboxBuilder title(final String artist) {
		hbox.getChildren()
				.add(Decorator.decorateTextFactory(
						new Text(artist.length() > 40
								? new String(new StringBuilder(artist.substring(0, 40)).append("...")) : artist),
				Decorator.SMALLTEXTSIZE));
		return getInstance();
	}

}
