package szakdolgozat.podcast.gui.borderpane;

import javafx.scene.layout.BorderPane;
import szakdolgozat.podcast.gui.mediaplayer.MediaControlDani;
import szakdolgozat.podcast.gui.tabpane.ApplicationTabPane;
import szakdolgozat.podcast.gui.vbox.RecommendVBoxView;

public class MainBorderPane extends BorderPane {
	private static MainBorderPane instance = null;

	public static MainBorderPane getInstance() {
		if (instance == null) {
			instance = new MainBorderPane();
		}
		return instance;
	}

	private MainBorderPane() {
		buildCenter();
		buildRight();
		buildBottom();
	}

	private void buildCenter() {
		setCenter(ApplicationTabPane.getInstance());
	}

	public void buildRight() {
		setRight(new RecommendVBoxView());
	}

	private void buildBottom() {
		setBottom(new MediaControlDani());
	}

}
