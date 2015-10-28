package szakdolgozat.podcast.gui.gridpane;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import szakdolgozat.podcast.basicinformation.InformationContainer;
import szakdolgozat.podcast.gui.decorator.LoginGridPaneDecorator;
import szakdolgozat.podcast.gui.dialog.LoginErrorDialog;
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
		LoginGridPaneDecorator.decorateTextField(nameTextField);
		passwordPasswordField = new PasswordFieldSample(PASSWORDTEXTFIELD_PROMPTEXT, PASSWORDTEXTFIELD_TOOLTIP);
		LoginGridPaneDecorator.decorateTextField(passwordPasswordField);
		okButton = new ButtonSample(OKBUTTON_TEXT, OKBUTTONSMAPLE_TOOLTIP);
		LoginGridPaneDecorator.decorateButton(okButton);
		cancelButton = new ButtonSample(CANCELBUTTON_TEXT, CANCELBUTTONS_TOOLTIP);
		LoginGridPaneDecorator.decorateButton(cancelButton);
		messageLabel = new LabelSample(MESSAGELABEL_TEXT, MESSAGELABEL_TOOLTIP);
		LoginGridPaneDecorator.decorateLabel(messageLabel);
		nameLabel = new LabelSample(NAMELABEL_TEXT, NAMELABEL_TOOLTIP);
		LoginGridPaneDecorator.decorateLabel(nameLabel);
		passwordLabel = new LabelSample(PASSWORDLABEL_TEXT, PASSWORDLABEL_TOOLTIP);
		LoginGridPaneDecorator.decorateLabel(passwordLabel);
		add(messageLabel, LoginGridPaneDecorator.MESSAGELABELX, LoginGridPaneDecorator.MESSAGLABELY);
		add(nameLabel, LoginGridPaneDecorator.NAMELABELX, LoginGridPaneDecorator.NAMELABELY);
		add(passwordLabel, LoginGridPaneDecorator.PASSWORDLABELX, LoginGridPaneDecorator.PASSWORDLABELY);
		add(nameTextField, LoginGridPaneDecorator.NAMETEXTFIELDX, LoginGridPaneDecorator.NAMETEXTFIELDY);
		add(passwordPasswordField, LoginGridPaneDecorator.PASSWORDFIELDX, LoginGridPaneDecorator.PASSWORDFIELDY);
		add(okButton, LoginGridPaneDecorator.OKBUTTONX, LoginGridPaneDecorator.OKBUTTONY);
		add(cancelButton, LoginGridPaneDecorator.CANCELBUTTONX, LoginGridPaneDecorator.CANCELBUTTONY);
		setOkButtonFunctionality();
		setCancelButtonFunctinality();
		setButtonDisability();
		setPasswordTextFieldKeyEvent();
		LoginGridPaneDecorator.decorate(this);
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
				LoginErrorDialog errorDialog = new LoginErrorDialog(MATCHERROR);
			} else {
				if (checkUserAndPassword()) {
					InformationContainer.getInstance().setOwner(nameTextField.getText());
					MainStage.getInstance().show();
					LoginStage.getInstance().hide();
				} else {
					LoginStage.getInstance().hide();
					LoginErrorDialog errorDialog = new LoginErrorDialog(ERRORMESSAGE);
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

}