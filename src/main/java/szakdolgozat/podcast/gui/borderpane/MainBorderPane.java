package szakdolgozat.podcast.gui.borderpane;

import javafx.scene.layout.BorderPane;
import szakdolgozat.podcast.gui.mediaplayer.MediaControlDani;
import szakdolgozat.podcast.gui.vbox.RecommendVBoxView;
import szakdolgozat.podcast.tabpane.ApplicationTabPane;

public class MainBorderPane extends BorderPane {
	public MainBorderPane() {
		buildCenter();
		buildRight();
		buildBottom();
	}

	private void buildCenter() {
		setCenter(new ApplicationTabPane());
	}

	private void buildRight() {
		setRight(new RecommendVBoxView());
	}

	private void buildBottom() {
		setBottom(new MediaControlDani());
	}

}
