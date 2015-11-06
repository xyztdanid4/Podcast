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

public class PodcastBPDecorator extends Decorator {
	// podcastinformationcontainer
	public static final int PODCASTINFORMATIONCONTAINERWIDTH = 800;
	public static final int PODCASTINFORMATIONCONTAINERHEIGHT = 100;
	public static final int PODCASTINFORMATIONCONTAINERMAXWIDTH = 800;
	public static final int PODCASTINFORMATIONCONTAINERMAXHEIGHT = 100;
	public static final int HELPERVBOXPADDING = 5;
	// podcastlist
	public static final int NOSUBSCRIPTIONHBOXHEIGHT = 50;
	public static final int PODCASTLISTVIEWWIDTH = 300;
	public static final int PODCASTLISTVIEWHEIGHT = 300;
	// episodeslist
	public static final int EPISODESLISTWIDTH = 550;
	public static final int EPISODESLISTHEIGHT = 300;
	public static final int EMPTYEPISODESLISTWIDTH = 550;
	public static final int EMPTYEPISODESLISTHEIGHT = 50;
	public static final int EPISODESIMAGEVIEWHEIGHT = 30;
	public static final int EPISODESIMAGEVIEWWIDTH = 30;

	public PodcastBPDecorator() {

	}

	public static Pane decorateFactory(final Pane pane) {
		pane.setBackground(new Background(
				new BackgroundFill(Color.web(BACKGROUNDCOLOR), new CornerRadii(BORDERPANEBORDERRADIUS), Insets.EMPTY)));
		pane.setBorder(new Border(new BorderStroke(Color.web(BORDERCOLOR), BorderStrokeStyle.SOLID,
				new CornerRadii(BORDERPANEBORDERRADIUS), new BorderWidths(BORDERSIZE, BORDERSIZE, 0, BORDERSIZE))));
		return pane;
	}

}
