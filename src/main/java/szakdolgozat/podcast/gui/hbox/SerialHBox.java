package szakdolgozat.podcast.gui.hbox;

import java.io.Serializable;

import javafx.scene.layout.HBox;

/**
 * The Class SerialHBox.
 * 
 * @author Daniel Toth
 * @version 0.0.1
 * @since 0.0.1
 */
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

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4392847936046722203L;

	/**
	 * Instantiates a new serial h box.
	 *
	 * @param padding
	 *            the padding
	 */
	public SerialHBox(final int padding) {
		super(padding);
	}
}
