package szakdolgozat.podcast.gui.dialog;

import javafx.event.ActionEvent;
import szakdolgozat.podcast.gui.stage.MainStage;

/**
 * The Class OtherErrorDialog.
 * 
 * @author Daniel Toth
 * @version 0.0.1
 * @since 0.0.1
 * 
 *        This class basically is represent a login error dialog overriding the
 *        button function. If we got a fail during program is running, we have
 *        to show again the applicationstage.
 */
public class OtherErrorDialog extends ErrorDialog {
	public OtherErrorDialog(final String message) {
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
			MainStage.getInstance().show();
		});
	}
}
