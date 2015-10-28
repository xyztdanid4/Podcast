package szakdolgozat.podcast.gui.borderpane;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import szakdolgozat.podcast.gui.mediaplayer.MediaControlDani;
import szakdolgozat.podcast.gui.vbox.RecommendVBox;
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
		setRight(new RecommendVBox());
	}

	private void buildBottom() {
		setBottom(new MediaControlDani());
	}

}
