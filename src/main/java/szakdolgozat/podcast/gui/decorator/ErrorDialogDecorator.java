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

public class ErrorDialogDecorator extends Decorator {
	public static final int labelX = 1;
	public static final int labelY = 1;
	public static final int buttonX = 2;
	public static final int buttonY = 2;
	public static final int SCENE_WIDTH = 300;
	public static final int SCENE_HIGHT = 100;

	public ErrorDialogDecorator() {

	}

	public static void decorate(Pane pane) {
		pane.setBackground(new Background(
				new BackgroundFill(Color.web(BACKGROUNDCOLOR), new CornerRadii(BORDERPANEBORDERRADIUS), Insets.EMPTY)));
		pane.setBorder(new Border(new BorderStroke(Color.web(BORDERCOLOR), BorderStrokeStyle.SOLID,
				new CornerRadii(BORDERPANEBORDERRADIUS),
				new BorderWidths(BORDERSIZE, BORDERSIZE, BORDERSIZE, BORDERSIZE))));
	}
}
