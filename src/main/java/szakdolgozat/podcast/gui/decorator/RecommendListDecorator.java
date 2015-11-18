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
 * The Class RecommendListDecorator.
 * 
 * @author Daniel Toth
 * @version 0.0.1
 * @since 0.0.1
 */
public class RecommendListDecorator extends Decorator {

	/** The Constant LISTWIDTH. */
	public static final int LISTWIDTH = 250;

	/** The Constant LISTHEIGHT. */
	public static final int LISTHEIGHT = 400;

	/** The Constant PADDING. */
	public static final int PADDING = 10;

	/**
	 * Decorate.
	 *
	 * @param pane
	 *            the pane
	 * @return the pane
	 */
	public static Pane decorate(final Pane pane) {
		pane.setBackground(new Background(
				new BackgroundFill(Color.web(BACKGROUNDCOLOR), new CornerRadii(BORDERPANEBORDERRADIUS), Insets.EMPTY)));
		pane.setBorder(new Border(new BorderStroke(Color.web(BORDERCOLOR), BorderStrokeStyle.SOLID,
				new CornerRadii(BORDERPANEBORDERRADIUS), new BorderWidths(BORDERSIZE, BORDERSIZE, 0, 0))));
		return pane;
	}
}
