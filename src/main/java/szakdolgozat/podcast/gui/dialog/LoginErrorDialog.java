package szakdolgozat.podcast.gui.dialog;

import javafx.event.ActionEvent;
import szakdolgozat.podcast.gui.stage.LoginStage;

public class LoginErrorDialog extends ErrorDialog {
	public LoginErrorDialog(final String message) {
		super(message);
		setOkButtonFunction();
	}

	@Override
	protected void setOkButtonFunction() {
		okButton.setOnAction((ActionEvent event) -> {
			stage.close();
			LoginStage.getInstance().show();
		});
	}

}
