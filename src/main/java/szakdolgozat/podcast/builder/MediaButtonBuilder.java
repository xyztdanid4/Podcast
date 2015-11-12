package szakdolgozat.podcast.builder;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import szakdolgozat.podcast.gui.decorator.Decorator;

public class MediaButtonBuilder implements Builder {
	static MediaButtonBuilder instance = new MediaButtonBuilder();
	private static Button button;

	private MediaButtonBuilder() {

	}

	private static MediaButtonBuilder getInstance() {
		return instance;
	}

	public static MediaButtonBuilder create() {
		button = Decorator.decorateButtonFactory(new Button());
		return getInstance();
	}

	@Override
	public Button build() {
		return button;
	}

	public MediaButtonBuilder image(final String ImageURL) {
		final Rectangle rectangleButtonShape = new Rectangle(5, 5);
		rectangleButtonShape.setArcHeight(3);
		rectangleButtonShape.setArcWidth(3);
		button.setShape(rectangleButtonShape);
		final ImageView buttonImageView = new ImageView(
				new Image(getClass().getClassLoader().getResource(ImageURL).toExternalForm()));
		button.setGraphic(buttonImageView);
		button.setMinSize(50, 50);
		button.setMaxSize(50, 50);
		return getInstance();
	}

	public MediaButtonBuilder disable(final boolean bool) {
		button.setDisable(bool);
		return getInstance();
	}

	public MediaButtonBuilder text(final String text) {
		button.setText(text);
		return getInstance();
	}

}
