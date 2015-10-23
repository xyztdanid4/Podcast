package szakdolgozat.podcast.gui.borderpane;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import szakdolgozat.podcast.gui.mediaplayer.MediaControlDani;
import szakdolgozat.podcast.tabpane.ApplicationTabPane;

public class MainBorderPane extends BorderPane {
	private Label rightLabel;

	public MainBorderPane() {
		buildCenter();
		buildRight();
		buildBottom();
	}

	private void buildCenter() {
		setCenter(new ApplicationTabPane());
	}

	private void buildRight() {
		rightLabel = new Label("RIGHTSIDE");
		setRight(rightLabel);
	}

	private void buildBottom() {
		setBottom(new MediaControlDani());
	}

}
