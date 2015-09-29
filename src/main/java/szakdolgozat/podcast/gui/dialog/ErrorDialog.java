package szakdolgozat.podcast.gui.dialog;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import szakdolgozat.podcast.gui.samples.ButtonSample;
import szakdolgozat.podcast.gui.samples.GridPaneSample;
import szakdolgozat.podcast.gui.samples.LabelSample;

public class ErrorDialog extends GridPaneSample {
	private Stage dialog;
	private Scene scene;
	private final String OKBUTTONTEXT = "Ok";
	private final String OKBUTTONTOOLTIP = "Press this to try again!";
	private ButtonSample okButtonSample;
	private LabelSample messageLabelSample;
	private final String MESSAGELABELSAMPLE_TOOLTIP = "Interact with ok button!";

	public ErrorDialog(final String message) {
		dialog = new Stage();
		scene = new Scene(this, 300, 100);
		okButtonSample = new ButtonSample(OKBUTTONTEXT, OKBUTTONTOOLTIP);
		messageLabelSample = new LabelSample(message, MESSAGELABELSAMPLE_TOOLTIP);

		okButtonSample.setOnAction((ActionEvent event) -> {
			dialog.close();
		});
		add(messageLabelSample, 1, 1);
		add(okButtonSample, 2, 2);
		dialog.setTitle("Something went wrong");
		dialog.setScene(scene);
		dialog.setResizable(false);
		dialog.show();
	}
}
