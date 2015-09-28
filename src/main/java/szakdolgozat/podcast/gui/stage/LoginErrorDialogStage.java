package szakdolgozat.podcast.gui.stage;

import javafx.scene.Scene;
import szakdolgozat.podcast.gui.dialog.LoginErrorDialog;
import szakdolgozat.podcast.gui.samples.StageSample;

public class LoginErrorDialogStage extends StageSample {
	private static final String TITLE = "Login error!";
	private static LoginErrorDialogStage instance = new LoginErrorDialogStage(
			TITLE);
	private static LoginErrorDialog loginErrorDialog;
	private static Scene loginErrorScene;
	private static final int DEFAULTHSIZE = 400;
	private static final int DEFAULTVSIZE = 100;

	public LoginErrorDialogStage(final String title) {
		super(title);
		loginErrorDialog = new LoginErrorDialog(
				"You must use strings which contain only englist letters!");
		loginErrorScene = new Scene(loginErrorDialog, DEFAULTHSIZE,
				DEFAULTVSIZE);
		setScene(loginErrorScene);
	}

	public static LoginErrorDialogStage getInstance() {
		return instance;
	}

	public static LoginErrorDialog getLoginErrorDialog() {
		return loginErrorDialog;
	}
}
