package szakdolgozat.podcast.gui.gridpane;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import szakdolgozat.podcast.basicinformation.InformationContainer;
import szakdolgozat.podcast.gui.dialog.ErrorDialog;
import szakdolgozat.podcast.gui.samples.ButtonSample;
import szakdolgozat.podcast.gui.samples.GridPaneSample;
import szakdolgozat.podcast.gui.samples.LabelSample;
import szakdolgozat.podcast.gui.samples.PasswordFieldSample;
import szakdolgozat.podcast.gui.samples.TextFieldSample;
import szakdolgozat.podcast.gui.stage.LoginStage;
import szakdolgozat.podcast.gui.stage.MainStage;
import szakdolgozat.podcast.morphia.MorphiaLoginConnector;
import szakdolgozat.podcast.user.User;

public class LoginGridPane extends GridPaneSample {
	private static final String MESSAGELABEL_TEXT = "Welcome, please Login!";
	private static final String MESSAGELABEL_TOOLTIP = "Hey Buddy welcome!";
	private static final String ERRORMESSAGE = "Name or Password is incorrect!";
	private static final String MATCHERROR = "You should use only englush abc letters!";
	private static final String NAMELABEL_TOOLTIP = "Interact with name textfield!";
	private static final String PASSWORDLABEL_TOOLTIP = "Interact with password textfield!";
	private static final String NAMETEXTFIELD_TOOLTP = "Enter your name!";
	private static final String PASSWORDTEXTFIELD_TOOLTIP = "Enter your password!";
	private static final String NAMETEXTFIELD_PROMPTEXT = "Name";
	private static final String PASSWORDTEXTFIELD_PROMPTEXT = "Password";
	private static final String NAMELABEL_TEXT = "Name";
	private static final String PASSWORDLABEL_TEXT = "Password";
	private static final String OKBUTTON_TEXT = "Login";
	private static final String CANCELBUTTON_TEXT = "Cancel (exit)";
	private static final String OKBUTTONSMAPLE_TOOLTIP = "Press this to login!";
	private static final String CANCELBUTTONS_TOOLTIP = "Press this to cancel, and exit!";
	private static ButtonSample okButton;
	private static ButtonSample cancelButton;
	private static TextFieldSample nameTextField;
	private static PasswordFieldSample passwordPasswordField;
	private static LabelSample messageLabel;
	private static LabelSample nameLabel;
	private static LabelSample passwordLabel;

	public LoginGridPane() {
		nameTextField = new TextFieldSample(NAMETEXTFIELD_PROMPTEXT, NAMETEXTFIELD_TOOLTP);
		decorateTextField(nameTextField);
		passwordPasswordField = new PasswordFieldSample(PASSWORDTEXTFIELD_PROMPTEXT, PASSWORDTEXTFIELD_TOOLTIP);
		decorateTextField(passwordPasswordField);
		okButton = new ButtonSample(OKBUTTON_TEXT, OKBUTTONSMAPLE_TOOLTIP);
		decorateButton(okButton);
		cancelButton = new ButtonSample(CANCELBUTTON_TEXT, CANCELBUTTONS_TOOLTIP);
		decorateButton(cancelButton);
		messageLabel = new LabelSample(MESSAGELABEL_TEXT, MESSAGELABEL_TOOLTIP);
		decorateLabel(messageLabel);
		nameLabel = new LabelSample(NAMELABEL_TEXT, NAMELABEL_TOOLTIP);
		decorateLabel(nameLabel);
		passwordLabel = new LabelSample(PASSWORDLABEL_TEXT, PASSWORDLABEL_TOOLTIP);
		decorateLabel(passwordLabel);
		add(messageLabel, 1, 1);
		add(nameLabel, 1, 2);
		add(passwordLabel, 1, 3);
		add(nameTextField, 2, 2);
		add(passwordPasswordField, 2, 3);
		add(okButton, 2, 4);
		add(cancelButton, 2, 5);
		setOkButtonFunctionality();
		setCancelButtonFunctinality();
		setButtonDisability();
		setPasswordTextFieldKeyEvent();
		decorate();
	}

	private void setCancelButtonFunctinality() {
		cancelButton.setOnAction((ActionEvent event) -> {
			Platform.exit();
		});
	}

	private void setOkButtonFunctionality() {
		okButton.setOnAction((ActionEvent event) -> {
			if (!(nameTextField.getText().matches("[a-zA-Z]+"))) {
				LoginStage.getInstance().hide();
				ErrorDialog errorDialog = new ErrorDialog(MATCHERROR);
			} else {
				if (checkUserAndPassword()) {
					MainStage.getInstance().show();
					InformationContainer.getInstance().setOwner(nameTextField.getText());
					LoginStage.getInstance().hide();
				} else {
					LoginStage.getInstance().hide();
					ErrorDialog errorDialog = new ErrorDialog(ERRORMESSAGE);
				}
			}
		});
	}

	private void setButtonDisability() {
		okButton.disableProperty().bind(Bindings.isEmpty(nameTextField.textProperty()));
		okButton.disableProperty().bind(Bindings.isEmpty(passwordPasswordField.textProperty()));
	}

	private boolean checkUserAndPassword() {
		return !(MorphiaLoginConnector.getDataStore().createQuery(User.class).filter("name = ", nameTextField.getText())
				.filter("password = ", passwordPasswordField.getText()).asList().isEmpty());
	}

	private void setPasswordTextFieldKeyEvent() {
		passwordPasswordField.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.ENTER) {
					okButton.fire();
				}
			}
		});
	}

	private void decorateTextField(TextField textField) {
		textField.setBackground(
				new Background(new BackgroundFill(Color.web("#808080"), new CornerRadii(10), Insets.EMPTY)));
		textField.setBorder(new Border(new BorderStroke(Color.web("#006666"), BorderStrokeStyle.SOLID,
				new CornerRadii(3), new BorderWidths(3))));
		textField.setStyle("-fx-text-inner-color: white;");
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

	private void decorateLabel(Label label) {
		label.setTextFill(Color.web("#FFFFFF"));
	}

	private void decorate() {
		setBackground(new Background(new BackgroundFill(Color.web("#191919"), new CornerRadii(0), Insets.EMPTY)));
		setBorder(new Border(new BorderStroke(Color.web("#006666"), BorderStrokeStyle.SOLID, new CornerRadii(0),
				new BorderWidths(3))));
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