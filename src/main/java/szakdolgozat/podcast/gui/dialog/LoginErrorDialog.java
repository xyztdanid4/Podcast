package szakdolgozat.podcast.gui.dialog;

import javafx.event.ActionEvent;
import szakdolgozat.podcast.gui.samples.ButtonSample;
import szakdolgozat.podcast.gui.samples.GridPaneSample;
import szakdolgozat.podcast.gui.samples.LabelSample;
import szakdolgozat.podcast.gui.stage.LoginErrorDialogStage;

public class LoginErrorDialog extends GridPaneSample {
	private LabelSample messageLabelSample;
	private final String MESSAGE_TOOLTIP = "Some kind of error!";
	// private final String MESSAGETEXT =
	// "You must use only letters which is in english abc!";
	private ButtonSample okButtonSample;
	private final String OKBUTTONSAMPLE_TOOLTIP = "Press this to try again!";
	private final String OKBUTTONSAMPLETEXT = "Ok";

	public LoginErrorDialog(String messageText) {
		messageLabelSample = new LabelSample(messageText, MESSAGE_TOOLTIP);
		okButtonSample = new ButtonSample(OKBUTTONSAMPLETEXT,
				OKBUTTONSAMPLE_TOOLTIP);
		add(messageLabelSample, 1, 1);
		add(okButtonSample, 2, 2);
		setButtonFunctionality();
	}

	private void setButtonFunctionality() {
		okButtonSample.setOnAction((ActionEvent event) -> {
			LoginErrorDialogStage.getInstance().hide();
		});
	}

	public LabelSample getMessageLabelSample() {
		return messageLabelSample;
	}
}
