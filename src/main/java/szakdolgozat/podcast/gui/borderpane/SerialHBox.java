package szakdolgozat.podcast.gui.borderpane;

import java.io.Serializable;

import javafx.scene.layout.HBox;

public class SerialHBox extends HBox implements Serializable {
	public SerialHBox(final int padding) {
		super(padding);
	}
}
