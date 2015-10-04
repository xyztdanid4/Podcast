package szakdolgozat.podcast.gui.dialog;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import szakdolgozat.podcast.gui.samples.ButtonSample;
import szakdolgozat.podcast.gui.samples.GridPaneSample;
import szakdolgozat.podcast.gui.samples.LabelSample;
import szakdolgozat.podcast.gui.stage.LoginStage;

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
		decorateButton(okButtonSample);
		setOkButtonFunctinolity();
		LabelSample label = new LabelSample(message, MESSAGELABELSAMPLE_TOOLTIP);
		decorateLabel(label);
		add(label, 1, 1);
		add(okButtonSample, 2, 2);
		dialog.setTitle("Something went wrong!");
		dialog.setScene(new Scene(this, SCENE_WIDTH, SCENE_HIGHT));
		dialog.setResizable(false);
		decorate();
		setOkButtonSampleEnterEvent();
		dialog.show();
	}

	private void setOkButtonSampleEnterEvent() {
		okButtonSample.defaultButtonProperty().bind(okButtonSample.focusedProperty());
	}

	private void setOkButtonFunctinolity() {
		okButtonSample.setOnAction((ActionEvent event) -> {
			dialog.close();
			LoginStage.getInstance().show();
		});
	}

	private void decorateLabel(Label label) {
		label.setTextFill(Color.web("#FFFFFF"));
	}

	private void decorate() {
		setBackground(new Background(new BackgroundFill(Color.web("#191919"), new CornerRadii(0), Insets.EMPTY)));
		setBorder(new Border(new BorderStroke(Color.web("#006666"), BorderStrokeStyle.SOLID, new CornerRadii(0),
				new BorderWidths(3))));
	}

	private void decorateButton(Button button) {
		button.setBackground(
				new Background(new BackgroundFill(Color.web("#808080"), new CornerRadii(10), Insets.EMPTY)));
		button.setBorder(new Border(new BorderStroke(Color.web("#006666"), BorderStrokeStyle.SOLID, new CornerRadii(3),
				new BorderWidths(3))));
		button.setTextFill(Color.web("#FFFFFF"));
		setMouseEnteredEventButton(button);
		setMouseExitedEventButton(button);
	}

	private void setMouseEnteredEventButton(Button button) {
		button.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				button.setBackground(
						new Background(new BackgroundFill(Color.web("#191919"), new CornerRadii(10), Insets.EMPTY)));
			}
		});
	}

	private void setMouseExitedEventButton(Button button) {
		button.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				button.setBackground(
						new Background(new BackgroundFill(Color.web("#808080"), new CornerRadii(10), Insets.EMPTY)));
			}
		});
	}
}
