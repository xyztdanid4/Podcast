package szakdolgozat.podcast.gui.hbox;

import java.io.Serializable;

import javafx.scene.layout.HBox;

public class SerialHBox extends HBox implements Serializable {

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SerialHBox []";
	}

	private static final long serialVersionUID = -4392847936046722203L;

	public SerialHBox(final int padding) {
		super(padding);
	}
}