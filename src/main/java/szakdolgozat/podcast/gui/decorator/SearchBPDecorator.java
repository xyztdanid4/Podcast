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

public class SearchBPDecorator extends Decorator {
	public static final int SEARCHTEXTFIELDWIDTH = 600;
	public static final int SEARCHTEXTFIELDHEIGHT = 30;
	// public static final int SEARCHTEXTFIELDMAXWIDTH = 600;
	// public static final int SEARCHTEXTFIELDMAXHEIGHT = 40;
	public static final int EPISODESLISTWIDTH = 400;
	public static final int EPISODESLISTHEIGHT = 400;
	// public static final int EPISODESLISTWIDTH
	// public static final int EPISODESLISTWIDTH
	public static final int PODCASTLISTWIDTH = 400;
	public static final int PODCASTLISTHEIGHT = 400;
	public static final int IMAGEWIDTH = 25;
	public static final int IMAGEHEIGHT = 25;

	public SearchBPDecorator() {

	}

	public static Pane decorateFactory(final Pane pane) {
		pane.setBackground(new Background(
				new BackgroundFill(Color.web(BACKGROUNDCOLOR), new CornerRadii(BORDERPANEBORDERRADIUS), Insets.EMPTY)));
		pane.setBorder(new Border(new BorderStroke(Color.web(BORDERCOLOR), BorderStrokeStyle.SOLID,
				new CornerRadii(BORDERPANEBORDERRADIUS), new BorderWidths(BORDERSIZE, BORDERSIZE, 0, BORDERSIZE))));
		return pane;
	}

}
