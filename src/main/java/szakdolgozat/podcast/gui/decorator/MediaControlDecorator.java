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
 * The Class MediaControlDecorator.
 * 
 * @author Daniel Toth
 * @version 0.0.1
 * @since 0.0.1
 */
public class MediaControlDecorator extends Decorator {

	/** The timeslider. */
	public static int TIMESLIDER = 600;

	/** The Constant PADDING. */
	public static final int PADDING = 5;

	/** The Constant RECTANGLESIZE. */
	public static final int RECTANGLESIZE = 3;

	/** The Constant ARCHEIGHT. */
	public static final int ARCHEIGHT = 3;

	/** The Constant ARCHWIDTH. */
	public static final int ARCHWIDTH = 3;

	/** The Constant BUTTONSIZE. */
	public static final int BUTTONSIZE = 50;

	/**
	 * Instantiates a new media control decorator.
	 */
	public MediaControlDecorator() {

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
				new CornerRadii(BORDERPANEBORDERRADIUS),
				new BorderWidths(BORDERSIZE, BORDERSIZE, BORDERSIZE, BORDERSIZE))));
		return pane;
	}
}
