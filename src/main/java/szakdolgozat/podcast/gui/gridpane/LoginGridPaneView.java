package szakdolgozat.podcast.gui.gridpane;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import szakdolgozat.podcast.builder.ButtonBuilder;
import szakdolgozat.podcast.builder.LabelBuilder;
import szakdolgozat.podcast.builder.PasswordFieldBuilder;
import szakdolgozat.podcast.builder.TextFieldBuilder;
import szakdolgozat.podcast.gui.decorator.Decorator;
import szakdolgozat.podcast.gui.decorator.LoginGridPaneDecorator;
import szakdolgozat.podcast.gui.dialog.LoginErrorDialog;

public class LoginGridPaneView extends BaseLoginView {
	private static final String MESSAGELABEL_TEXT = "Welcome, please Login!";
	private static final String ERRORMESSAGE = "Name or Password is incorrect!";
	private static final String MATCHERROR = "You should use only englush abc letters!";
	private static final String NAMETEXTFIELD_PROMPTEXT = "Name";
	private static final String PASSWORDTEXTFIELD_PROMPTEXT = "Password";
	private static final String NAMELABEL_TEXT = "Name";
	private static final String PASSWORDLABEL_TEXT = "Password";
	private static final String OKBUTTON_TEXT = "Login";
	private static final String CANCELBUTTON_TEXT = "Cancel (exit)";
	private static Button okButton;
	private static Button cancelButton;
	private static TextField nameTextField;
	private static PasswordField passwordField;
	final LoginGridPaneController loginGridPaneController;

	public LoginGridPaneView() {
		this.loginGridPaneController = new LoginGridPaneController();
		//-.-off
		nameTextField = TextFieldBuilder.create()
										.promptText(NAMETEXTFIELD_PROMPTEXT)
										.build();
		
		passwordField = PasswordFieldBuilder.create()
											.promptText(PASSWORDTEXTFIELD_PROMPTEXT)
											.build();
		
		setPasswordFieldKeyEvent();
		
		okButton = ButtonBuilder.create()
								.text(OKBUTTON_TEXT)
								.build();
		
		setOkButtonFunction();
		
		cancelButton = ButtonBuilder.create()
									.text(CANCELBUTTON_TEXT)
									.build();
		
		setCancelButtonFunctinality();
		
		add(LabelBuilder.create()
						.text(MESSAGELABEL_TEXT)
						.build()
						,LoginGridPaneDecorator.MESSAGELABELX, LoginGridPaneDecorator.MESSAGLABELY);

		add(LabelBuilder.create()
						.text(NAMELABEL_TEXT)
						.build()
						,LoginGridPaneDecorator.NAMELABELX, LoginGridPaneDecorator.NAMELABELY);

		add(LabelBuilder.create()
						.text(PASSWORDLABEL_TEXT)
						.build()
						,LoginGridPaneDecorator.PASSWORDLABELX, LoginGridPaneDecorator.PASSWORDLABELY);
		
		add(nameTextField, LoginGridPaneDecorator.NAMETEXTFIELDX, LoginGridPaneDecorator.NAMETEXTFIELDY);
		add(passwordField, LoginGridPaneDecorator.PASSWORDFIELDX, LoginGridPaneDecorator.PASSWORDFIELDY);
		add(okButton, LoginGridPaneDecorator.OKBUTTONX, LoginGridPaneDecorator.OKBUTTONY);
		add(cancelButton, LoginGridPaneDecorator.CANCELBUTTONX, LoginGridPaneDecorator.CANCELBUTTONY);
		//-.-on
		setButtonDisability();
		Decorator.decorateFactory(this);
	}

	private void setCancelButtonFunctinality() {
		cancelButton.setOnAction((final ActionEvent event) -> {
			exit();
		});
	}

	private void setOkButtonFunction() {
		okButton.setOnAction((final ActionEvent event) -> {
			if (!(nameTextField.getText().matches("[a-zA-Z]+"))) {
				hideLoginStage();
				final LoginErrorDialog errorDialog = new LoginErrorDialog(MATCHERROR);
			} else {
				if (this.loginGridPaneController.checkUserAndPassword(nameTextField.getText(),
						passwordField.getText())) {
					this.loginGridPaneController.setDBowner(nameTextField.getText());
					this.loginGridPaneController.setEmail(
							this.loginGridPaneController.getEmailFromDB(LoginGridPaneView.nameTextField.getText()));
					showMainStage();
					hideLoginStage();
				} else {
					hideLoginStage();
					final LoginErrorDialog errorDialog = new LoginErrorDialog(ERRORMESSAGE);
				}
			}
		});
	}

	private void setButtonDisability() {
		okButton.disableProperty().bind(Bindings.isEmpty(nameTextField.textProperty()));
		okButton.disableProperty().bind(Bindings.isEmpty(passwordField.textProperty()));
	}

	private void setPasswordFieldKeyEvent() {
		passwordField.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER) {
				ActivateOkButton();
			}
		});
	}

	private void ActivateOkButton() {
		okButton.fire();
	}

}