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
 * The Class ErrorDialogDecorator.
 * 
 * @author Daniel Toth
 * @version 0.0.1
 * @since 0.0.1
 */
public class ErrorDialogDecorator extends Decorator {

	/** The Constant labelX. */
	public static final int labelX = 1;

	/** The Constant labelY. */
	public static final int labelY = 1;

	/** The Constant buttonX. */
	public static final int buttonX = 2;

	/** The Constant buttonY. */
	public static final int buttonY = 2;

	/** The Constant SCENE_WIDTH. */
	public static final int SCENE_WIDTH = 300;

	/** The Constant SCENE_HIGHT. */
	public static final int SCENE_HIGHT = 100;

	/**
	 * Instantiates a new error dialog decorator.
	 */
	public ErrorDialogDecorator() {

	}

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
				new CornerRadii(BORDERPANEBORDERRADIUS),
				new BorderWidths(BORDERSIZE, BORDERSIZE, BORDERSIZE, BORDERSIZE))));
		return pane;
	}
}
