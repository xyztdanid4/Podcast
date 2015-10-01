package szakdolgozat.podcast.gui.stage;

import javafx.scene.Scene;
import szakdolgozat.podcast.gui.borderpane.MainBorderPane;
import szakdolgozat.podcast.gui.samples.StageSample;

public class MainStage extends StageSample {
	private static final String TITLE = "PodcastApp";
	private static MainStage instance = new MainStage(TITLE);
	private static final int DEFAULTHSIZE = 1300;
	private static final int DEFAULTVSIZE = 600;

	private MainStage(final String title) {
		super(title);
		setScene(new Scene(new MainBorderPane(), DEFAULTHSIZE, DEFAULTVSIZE));
	}

	public static MainStage getInstance() {
		return instance;
	}
}
