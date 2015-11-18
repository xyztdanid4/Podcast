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
 * The Class PodcastBPDecorator.
 * 
 * @author Daniel Toth
 * @version 0.0.1
 * @since 0.0.1
 */
public class PodcastBPDecorator extends Decorator {

	/** The Constant PODCASTINFORMATIONCONTAINERWIDTH. */
	public static final int PODCASTINFORMATIONCONTAINERWIDTH = 800;

	/** The Constant PODCASTINFORMATIONCONTAINERHEIGHT. */
	public static final int PODCASTINFORMATIONCONTAINERHEIGHT = 100;

	/** The Constant PODCASTINFORMATIONCONTAINERMAXWIDTH. */
	public static final int PODCASTINFORMATIONCONTAINERMAXWIDTH = 800;

	/** The Constant PODCASTINFORMATIONCONTAINERMAXHEIGHT. */
	public static final int PODCASTINFORMATIONCONTAINERMAXHEIGHT = 100;

	/** The Constant HELPERVBOXPADDING. */
	public static final int HELPERVBOXPADDING = 5;

	/** The Constant NOSUBSCRIPTIONHBOXHEIGHT. */
	public static final int NOSUBSCRIPTIONHBOXHEIGHT = 50;

	/** The Constant PODCASTLISTVIEWWIDTH. */
	public static final int PODCASTLISTVIEWWIDTH = 300;

	/** The Constant PODCASTLISTVIEWHEIGHT. */
	public static final int PODCASTLISTVIEWHEIGHT = 300;

	/** The Constant EPISODESLISTWIDTH. */
	public static final int EPISODESLISTWIDTH = 550;

	/** The Constant EPISODESLISTHEIGHT. */
	public static final int EPISODESLISTHEIGHT = 300;

	/** The Constant EMPTYEPISODESLISTWIDTH. */
	public static final int EMPTYEPISODESLISTWIDTH = 550;

	/** The Constant EMPTYEPISODESLISTHEIGHT. */
	public static final int EMPTYEPISODESLISTHEIGHT = 50;

	/** The Constant EPISODESIMAGEVIEWHEIGHT. */
	public static final int EPISODESIMAGEVIEWHEIGHT = 30;

	/** The Constant EPISODESIMAGEVIEWWIDTH. */
	public static final int EPISODESIMAGEVIEWWIDTH = 30;

	/**
	 * Instantiates a new podcast bp decorator.
	 */
	public PodcastBPDecorator() {

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
