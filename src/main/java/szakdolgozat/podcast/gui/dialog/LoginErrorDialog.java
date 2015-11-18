package szakdolgozat.podcast.gui.dialog;

import javafx.event.ActionEvent;
import szakdolgozat.podcast.gui.stage.LoginStage;

/**
 * The Class LoginErrorDialog.
 * 
 * @author Daniel Toth
 * @version 0.0.1
 * @since 0.0.1
 * 
 *        This class basically is represent a login error dialog overriding the
 *        button function. If we got a fail during program is running, we have
 *        to show again the loginstage.
 */
public class LoginErrorDialog extends ErrorDialog {
	public LoginErrorDialog(final String message) {
		super(message);
		setOkButtonFunction();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see szakdolgozat.podcast.gui.dialog.ErrorDialog#setOkButtonFunction()
	 */
	@Override
	protected void setOkButtonFunction() {
		okButton.setOnAction((final ActionEvent event) -> {
			stage.close();
			LoginStage.getInstance().show();
		});
	}

}
