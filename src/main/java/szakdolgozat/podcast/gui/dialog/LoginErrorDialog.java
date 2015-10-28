package szakdolgozat.podcast.gui.dialog;

import javafx.event.ActionEvent;
import szakdolgozat.podcast.gui.stage.LoginStage;

public class LoginErrorDialog extends ErrorDialog {

	public LoginErrorDialog(final String message) {
		super(message);
		setOkButtonFunctinolity();
	}

	@Override
	protected void setOkButtonFunctinolity() {
		okButton.setOnAction((ActionEvent event) -> {
			dialog.close();
			LoginStage.getInstance().show();
		});
	}

}
