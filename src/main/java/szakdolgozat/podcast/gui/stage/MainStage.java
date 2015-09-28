package szakdolgozat.podcast.gui.stage;

import javafx.scene.Scene;
import szakdolgozat.podcast.gui.borderpane.MainBorderPane;
import szakdolgozat.podcast.gui.samples.StageSample;

public class MainStage extends StageSample {
	private static final String TITLE = "PodcastApp";
	private static MainStage instance = new MainStage(TITLE);
	private static MainBorderPane mainBorderPane;
	private static Scene mainScene;
	private static final int DEFAULTHSIZE = 1300;
	private static final int DEFAULTVSIZE = 600;

	private MainStage(String title) {
		super(title);
		mainBorderPane = new MainBorderPane();
		mainScene = new Scene(mainBorderPane, DEFAULTHSIZE, DEFAULTVSIZE);
		setScene(mainScene);
	}

	public static MainStage getInstance() {
		return instance;
	}
}
