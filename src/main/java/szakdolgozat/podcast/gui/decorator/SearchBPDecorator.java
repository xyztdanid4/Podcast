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
 * The Class SearchBPDecorator.
 * 
 * @author Daniel Toth
 * @version 0.0.1
 * @since 0.0.1
 */
public class SearchBPDecorator extends Decorator {

	/** The Constant SEARCHTEXTFIELDWIDTH. */
	public static final int SEARCHTEXTFIELDWIDTH = 600;

	/** The Constant SEARCHTEXTFIELDHEIGHT. */
	public static final int SEARCHTEXTFIELDHEIGHT = 30;

	/** The Constant EPISODESLISTWIDTH. */
	public static final int EPISODESLISTWIDTH = 400;

	/** The Constant EPISODESLISTHEIGHT. */
	public static final int EPISODESLISTHEIGHT = 400;

	/** The Constant PODCASTLISTWIDTH. */
	public static final int PODCASTLISTWIDTH = 400;

	/** The Constant PODCASTLISTHEIGHT. */
	public static final int PODCASTLISTHEIGHT = 400;

	/** The Constant IMAGEWIDTH. */
	public static final int IMAGEWIDTH = 25;

	/** The Constant IMAGEHEIGHT. */
	public static final int IMAGEHEIGHT = 25;

	/**
	 * Instantiates a new search bp decorator.
	 */
	public SearchBPDecorator() {

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
