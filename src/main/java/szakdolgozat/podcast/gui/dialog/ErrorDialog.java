package szakdolgozat.podcast.gui.dialog;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import szakdolgozat.podcast.builder.ButtonBuilder;
import szakdolgozat.podcast.builder.StageBuilder;
import szakdolgozat.podcast.builder.TextBuilder;
import szakdolgozat.podcast.gui.decorator.Decorator;
import szakdolgozat.podcast.gui.decorator.ErrorDialogDecorator;
import szakdolgozat.podcast.gui.samples.StageSample;

public abstract class ErrorDialog extends GridPane {
	private static final String OKBUTTONTEXT = "Ok";
	private static final String WRONG = "Something went wrong!";
	protected StageSample stage;
	protected Button okButton;

	public ErrorDialog(final String message) {
		//-.-off
		this.okButton = ButtonBuilder.create()
									.text(OKBUTTONTEXT)
									.build();
		setOkButtonEnterEvent();
		
		final Text text = TextBuilder.create()
									.smallText(message)
									.build();
		
		setMargin(text, new Insets(Decorator.PADDING));
		add(text, ErrorDialogDecorator.labelX, ErrorDialogDecorator.labelY);
		add(this.okButton, ErrorDialogDecorator.buttonX, ErrorDialogDecorator.buttonY);

		this.stage = StageBuilder.create(WRONG)
								.scene(this, ErrorDialogDecorator.SCENE_WIDTH, ErrorDialogDecorator.SCENE_HIGHT)
								.build();
		//-.-on
		// this.stage.setScene(new Scene(this, ErrorDialogDecorator.SCENE_WIDTH,
		// ErrorDialogDecorator.SCENE_HIGHT));
		ErrorDialogDecorator.decorate(this);

		this.stage.show();
	}

	protected abstract void setOkButtonFunction();

	public void setOkButtonEnterEvent() {
		this.okButton.defaultButtonProperty().bind(this.okButton.focusedProperty());
	}

}
