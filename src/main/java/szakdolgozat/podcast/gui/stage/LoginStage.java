package szakdolgozat.podcast.gui.stage;

import javafx.scene.Scene;
import szakdolgozat.podcast.gui.samples.StageSample;
import szakdolgozat.podcast.tabpane.LoginTabPane;

public class LoginStage extends StageSample {
	private static final String LOGINSTAGETEXT = "Login!";
	private static LoginStage instance = new LoginStage(LOGINSTAGETEXT);
	private static LoginTabPane loginTabPane;
	private static Scene loginScene;
	private static final int DEFAULTHSIZE = 400;
	private static final int DEFAULTVSIZE = 300;

	private LoginStage(final String title) {
		super(title);
		loginTabPane = new LoginTabPane();
		loginScene = new Scene(loginTabPane, DEFAULTHSIZE, DEFAULTVSIZE);
		setScene(loginScene);
	}

	public static LoginStage getInstance() {
		return instance;
	}

}
