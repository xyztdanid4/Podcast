package szakdolgozat.podcast.gui.dialog;

import javafx.event.ActionEvent;
import szakdolgozat.podcast.gui.stage.MainStage;

public class OtherErrorDialog extends ErrorDialog {
	public OtherErrorDialog(final String message) {
		super(message);
		setOkButtonFunctinolity();
	}

	@Override
	protected void setOkButtonFunctinolity() {
		okButton.setOnAction((ActionEvent event) -> {
			dialog.close();
			MainStage.getInstance().show();
		});
	}
}
