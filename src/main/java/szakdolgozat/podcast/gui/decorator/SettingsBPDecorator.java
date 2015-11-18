package szakdolgozat.podcast.gui.decorator;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 * The Class SettingsBPDecorator.
 * 
 * @author Daniel Toth
 * @version 0.0.1
 * @since 0.0.1
 */
public class SettingsBPDecorator extends Decorator {

	/** The textfieldwidth. */
	public static int TEXTFIELDWIDTH = 80;

	/** The textfieldheight. */
	public static int TEXTFIELDHEIGHT = 30;

	/** The insets. */
	public static int INSETS = 20;

	/**
	 * Instantiates a new settings bp decorator.
	 */
	public SettingsBPDecorator() {

	}

	/**
	 * Decorate factory.
	 *
	 * @param pane
	 *            the pane
	 * @return the pane
	 */
	public static Pane decorateFactory(final Pane pane) {
		pane.setBackground(new Background(
				new BackgroundFill(Color.web(BACKGROUNDCOLOR), new CornerRadii(BORDERPANEBORDERRADIUS), Insets.EMPTY)));
		pane.setBorder(new Border(new BorderStroke(Color.web(BORDERCOLOR), BorderStrokeStyle.SOLID,
				new CornerRadii(BORDERPANEBORDERRADIUS), new BorderWidths(BORDERSIZE, BORDERSIZE, 0, BORDERSIZE))));
		return pane;
	}
}
