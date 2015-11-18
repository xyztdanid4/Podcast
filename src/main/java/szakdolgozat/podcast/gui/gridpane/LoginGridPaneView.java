package szakdolgozat.podcast.gui.gridpane;

import org.apache.log4j.Logger;

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
import szakdolgozat.podcast.controller.LoginGridPaneController;
import szakdolgozat.podcast.gui.decorator.Decorator;
import szakdolgozat.podcast.gui.decorator.LoginGridPaneDecorator;
import szakdolgozat.podcast.gui.dialog.LoginErrorDialog;

/**
 * The Class LoginGridPaneView.
 * 
 * @author Daniel Toth
 * @version 0.0.1
 * @since 0.0.1
 */
public class LoginGridPaneView extends BaseLoginView {

	final static Logger logger = Logger.getLogger(LoginGridPaneView.class);

	/** The Constant MESSAGELABEL_TEXT. */
	private static final String MESSAGELABEL_TEXT = "Welcome, please Login!";

	/** The Constant ERRORMESSAGE. */
	private static final String ERRORMESSAGE = "Name or Password is incorrect!";

	/** The Constant MATCHERROR. */
	private static final String MATCHERROR = "You should use only englush abc letters!";

	/** The Constant NAMETEXTFIELD_PROMPTEXT. */
	private static final String NAMETEXTFIELD_PROMPTEXT = "Name";

	/** The Constant PASSWORDTEXTFIELD_PROMPTEXT. */
	private static final String PASSWORDTEXTFIELD_PROMPTEXT = "Password";

	/** The Constant NAMELABEL_TEXT. */
	private static final String NAMELABEL_TEXT = "Name";

	/** The Constant PASSWORDLABEL_TEXT. */
	private static final String PASSWORDLABEL_TEXT = "Password";

	/** The Constant OKBUTTON_TEXT. */
	private static final String OKBUTTON_TEXT = "Login";

	/** The Constant CANCELBUTTON_TEXT. */
	private static final String CANCELBUTTON_TEXT = "Cancel (exit)";

	/** The ok button. */
	private static Button okButton;

	/** The cancel button. */
	private static Button cancelButton;

	/** The name text field. */
	private static TextField nameTextField;

	/** The password field. */
	private static PasswordField passwordField;

	/** The login grid pane controller. */
	final LoginGridPaneController loginGridPaneController;

	/**
	 * Instantiates a new login grid pane view.
	 */
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
		
		/*
		okButton.setOnAction((final ActionEvent event) -> {
			try {
				problem.setText(this.loginGridPaneController.getEmailFromDB(LoginGridPaneView.nameTextField.getText()));
			} catch (final Exception e) {
				e.printStackTrace();
				logger.error(e);
			}
		});
		*/
		
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

	/**
	 * Sets the cancel button functinality.
	 */
	private void setCancelButtonFunctinality() {
		cancelButton.setOnAction((final ActionEvent event) -> {
			exit();
		});
	}

	/**
	 * Sets the ok button function.
	 */
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

	/**
	 * Sets the button disability.
	 */
	private void setButtonDisability() {
		okButton.disableProperty().bind(Bindings.isEmpty(nameTextField.textProperty()));
		okButton.disableProperty().bind(Bindings.isEmpty(passwordField.textProperty()));
	}

	/**
	 * Sets the password field key event.
	 */
	private void setPasswordFieldKeyEvent() {
		passwordField.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER) {
				ActivateOkButton();
			}
		});
	}

	/**
	 * Activate ok button.
	 */
	private void ActivateOkButton() {
		okButton.fire();
	}

}