package szakdolgozat.podcast.gui.stage;

import javafx.scene.Scene;
import szakdolgozat.podcast.controller.MainStageController;
import szakdolgozat.podcast.gui.borderpane.MainBorderPaneView;
import szakdolgozat.podcast.gui.samples.StageSample;

public class MainStage extends StageSample {
	private static final String TITLE = "PodcastApp";
	private static MainStage instance = null;
	private static final int DEFAULTHSIZE = 1300;
	private static final int DEFAULTVSIZE = 700;

	private MainStage(final String title) {
		super(title);
		final Scene mainScene = new Scene(MainBorderPaneView.getInstance(), DEFAULTHSIZE, DEFAULTVSIZE);
		mainScene.getStylesheets().add("listcell.css");
		setScene(mainScene);
		MainStageController.create();
	}

	public static MainStage getInstance() {
		if (instance == null) {
			instance = new MainStage(TITLE);
		}
		return instance;
	}

}
