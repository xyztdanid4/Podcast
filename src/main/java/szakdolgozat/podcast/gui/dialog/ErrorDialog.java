package szakdolgozat.podcast.gui.dialog;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import szakdolgozat.podcast.gui.samples.ButtonSample;
import szakdolgozat.podcast.gui.samples.GridPaneSample;
import szakdolgozat.podcast.gui.samples.LabelSample;

public class ErrorDialog extends GridPaneSample {
	private static final String OKBUTTONTEXT = "Ok";
	private static final String OKBUTTONTOOLTIP = "Press this to try again!";
	private static final String MESSAGELABELSAMPLE_TOOLTIP = "Interact with ok button!";
	private static final int SCENE_WIDTH = 300;
	private static final int SCENE_HIGHT = 100;
	private Stage dialog;
	private ButtonSample okButtonSample;

	public ErrorDialog(final String message) {
		dialog = new Stage();
		okButtonSample = new ButtonSample(OKBUTTONTEXT, OKBUTTONTOOLTIP);
		setOkButtonFunctinolity();
		add(new LabelSample(message, MESSAGELABELSAMPLE_TOOLTIP), 1, 1);
		add(okButtonSample, 2, 2);
		dialog.setTitle("Something went wrong!");
		dialog.setScene(new Scene(this, SCENE_WIDTH, SCENE_HIGHT));
		dialog.setResizable(false);
		setOkButtonSampleEnterEvent();
		dialog.show();
	}

	private void setOkButtonSampleEnterEvent() {
		okButtonSample.defaultButtonProperty().bind(okButtonSample.focusedProperty());
	}

	private void setOkButtonFunctinolity() {
		okButtonSample.setOnAction((ActionEvent event) -> {
			dialog.close();
		});
	}
}
