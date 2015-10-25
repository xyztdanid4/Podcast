package szakdolgozat.podcast.gui.dialog;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import szakdolgozat.podcast.gui.decorator.ErrorDialogDecorator;
import szakdolgozat.podcast.gui.samples.ButtonSample;
import szakdolgozat.podcast.gui.samples.GridPaneSample;
import szakdolgozat.podcast.gui.samples.LabelSample;
import szakdolgozat.podcast.gui.samples.StageSample;
import szakdolgozat.podcast.gui.stage.LoginStage;

public class ErrorDialog extends GridPaneSample {
	private static final String OKBUTTONTEXT = "Ok";
	private static final String OKBUTTONTOOLTIP = "Press this to try again!";
	private static final String MESSAGELABELSAMPLE_TOOLTIP = "Interact with ok button!";
	private static final String WRONG = "Something went wrong!";
	private StageSample dialog;
	private ButtonSample okButton;

	public ErrorDialog(final String message) {
		dialog = new StageSample(WRONG);
		okButton = new ButtonSample(OKBUTTONTEXT, OKBUTTONTOOLTIP);
		ErrorDialogDecorator.decorateButton(okButton);
		setOkButtonFunctinolity();
		LabelSample label = new LabelSample(message, MESSAGELABELSAMPLE_TOOLTIP);
		ErrorDialogDecorator.decorateLabel(label);
		add(label, ErrorDialogDecorator.labelX, ErrorDialogDecorator.labelY);
		add(okButton, ErrorDialogDecorator.buttonX, ErrorDialogDecorator.buttonY);
		dialog.setScene(new Scene(this, ErrorDialogDecorator.SCENE_WIDTH, ErrorDialogDecorator.SCENE_HIGHT));
		ErrorDialogDecorator.decorate(this);
		setOkButtonEnterEvent();
		dialog.show();
	}

	private void setOkButtonEnterEvent() {
		okButton.defaultButtonProperty().bind(okButton.focusedProperty());
	}

	private void setOkButtonFunctinolity() {
		okButton.setOnAction((ActionEvent event) -> {
			dialog.close();
			LoginStage.getInstance().show();
		});
	}

}
