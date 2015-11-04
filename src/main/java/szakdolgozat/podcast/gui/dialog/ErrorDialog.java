package szakdolgozat.podcast.gui.dialog;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import szakdolgozat.podcast.gui.decorator.ErrorDialogDecorator;
import szakdolgozat.podcast.gui.samples.ButtonSample;
import szakdolgozat.podcast.gui.samples.StageSample;

public abstract class ErrorDialog extends GridPane {
	private static final String OKBUTTONTEXT = "Ok";
	private static final String OKBUTTONTOOLTIP = "Press this to try again!";
	private static final String WRONG = "Something went wrong!";
	protected StageSample dialog;
	protected ButtonSample okButton;

	public ErrorDialog(final String message) {
		dialog = new StageSample(WRONG);
		okButton = ErrorDialogDecorator.decorateButtonSampleFactory(new ButtonSample(OKBUTTONTEXT, OKBUTTONTOOLTIP));
		Text text = ErrorDialogDecorator.decorateTextFactory(new Text(new String(message)),
				ErrorDialogDecorator.SMALLTEXTSIZE);
		setMargin(text, new Insets(ErrorDialogDecorator.PADDING));
		add(text, ErrorDialogDecorator.labelX, ErrorDialogDecorator.labelY);
		add(okButton, ErrorDialogDecorator.buttonX, ErrorDialogDecorator.buttonY);
		dialog.setScene(new Scene(this, ErrorDialogDecorator.SCENE_WIDTH, ErrorDialogDecorator.SCENE_HIGHT));
		ErrorDialogDecorator.decorate(this);
		setOkButtonEnterEvent();
		dialog.show();
	}

	protected abstract void setOkButtonFunctinolity();

	public void setOkButtonEnterEvent() {
		okButton.defaultButtonProperty().bind(okButton.focusedProperty());
	}

}
