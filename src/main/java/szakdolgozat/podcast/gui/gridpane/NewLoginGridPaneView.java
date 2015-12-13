package szakdolgozat.podcast.gui.gridpane;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import szakdolgozat.podcast.controller.NewLoginGridPaneController;
import szakdolgozat.podcast.gui.builder.ButtonBuilder;
import szakdolgozat.podcast.gui.builder.LabelBuilder;
import szakdolgozat.podcast.gui.builder.PasswordFieldBuilder;
import szakdolgozat.podcast.gui.builder.TextFieldBuilder;
import szakdolgozat.podcast.gui.decorator.Decorator;
import szakdolgozat.podcast.gui.decorator.NewLoginGridPaneDecorator;
import szakdolgozat.podcast.gui.dialog.LoginErrorDialog;

/**
 * The Class NewLoginGridPaneView.
 * 
 * @author Daniel Toth
 * @version 0.0.1
 * @since 0.0.1
 */
public class NewLoginGridPaneView extends BaseLoginView {

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

	/** The Constant MESSAGELABEL_TEXT. */
	private static final String MESSAGELABEL_TEXT = "Welcome, please Login!";

	/** The Constant EMAILLABEL_TEXT. */
	private static final String EMAILLABEL_TEXT = "E-mail";

	/** The Constant PASSWORDAGAINTEXTFIELD_PROMPTTEXT. */
	private static final String PASSWORDAGAINTEXTFIELD_PROMPTTEXT = "Password again";

	/** The Constant PASSWORDAGAINLABEL_TEXT. */
	private static final String PASSWORDAGAINLABEL_TEXT = "Password again";

	/** The Constant EMAILTEXTFIELD_PROMTTEXT. */
	private static final String EMAILTEXTFIELD_PROMTTEXT = "E-mail";

	/** The Constant ERRORMESSAGE. */
	private static final String ERRORMESSAGE = "This name or e-mail address is in use!";

	/** The Constant ERRORPASSWORD. */
	private static final String ERRORPASSWORD = "Passwords mismatch!";

	/** The Constant ERRORMAILFORMAT. */
	private static final String ERRORMAILFORMAT = "Wrong e-mail format!";

	/** The ok button. */
	private static Button okButton;

	/** The cancel button. */
	private static Button cancelButton;

	/** The password again text field. */
	private static PasswordField passwordAgainTextField;

	/** The email text field. */
	private static TextField emailTextField;

	/** The name text field. */
	private static TextField nameTextField;

	/** The password field. */
	private static PasswordField passwordField;

	/** The new login grid pane controller. */
	final NewLoginGridPaneController newLoginGridPaneController;

	/**
	 * Instantiates a new new login grid pane view.
	 */
	public NewLoginGridPaneView() {
		this.newLoginGridPaneController = new NewLoginGridPaneController();
		//-.-off
		nameTextField = TextFieldBuilder.create()
										.promptText(NAMETEXTFIELD_PROMPTEXT)
										.build();
	
		passwordField = PasswordFieldBuilder.create()
											.promptText(PASSWORDTEXTFIELD_PROMPTEXT)
											.build();
		
		cancelButton = ButtonBuilder.create()
									.text(CANCELBUTTON_TEXT)
									.build();
		setCancelButtonFunctinality();
		
		passwordAgainTextField = PasswordFieldBuilder.create()
													.promptText(PASSWORDAGAINTEXTFIELD_PROMPTTEXT)
													.build();
		
		emailTextField = TextFieldBuilder.create()
										.promptText(EMAILTEXTFIELD_PROMTTEXT)
										.build();
		
		okButton = ButtonBuilder.create().text(OKBUTTON_TEXT).build();
		setButtonDisability();
		setOkButtonFunctionality();
		setPasswordFieldKeyEvent();
		
		add(LabelBuilder.create()
						.text(MESSAGELABEL_TEXT)
						.build()
						,NewLoginGridPaneDecorator.MESSAGELABELX, NewLoginGridPaneDecorator.MESSAGLABELY);
		
		add(LabelBuilder.create()
				.text(NAMELABEL_TEXT)
				.build()
				,NewLoginGridPaneDecorator.NAMELABELX, NewLoginGridPaneDecorator.NAMELABELY);
		
		add(LabelBuilder.create()
				.text(PASSWORDLABEL_TEXT)
				.build()
				,NewLoginGridPaneDecorator.PASSWORDLABELX, NewLoginGridPaneDecorator.PASSWORDLABELY);
		
		add(nameTextField, NewLoginGridPaneDecorator.NAMETEXTFIELDX, NewLoginGridPaneDecorator.NAMETEXTFIELDY);
		
		add(passwordField, NewLoginGridPaneDecorator.PASSWORDFIELDX, NewLoginGridPaneDecorator.PASSWORDFIELDY);
		
		add(passwordAgainTextField, NewLoginGridPaneDecorator.PASSWORDAGAINTEXTFIELDX,
				NewLoginGridPaneDecorator.PASSWORDAGAINTEXTFIELDY);
		
		add(emailTextField, NewLoginGridPaneDecorator.EMAILTEXTFIELDX, NewLoginGridPaneDecorator.EMAILTEXTFIELDY);
		
		add(LabelBuilder.create()
						.text(PASSWORDAGAINLABEL_TEXT)
						.build()
						, NewLoginGridPaneDecorator.PASSWORDAGAINLABELX, NewLoginGridPaneDecorator.PASSWORDAGAINLABELY);
		
		add(LabelBuilder.create()
						.text(EMAILLABEL_TEXT)
						.build()
						, NewLoginGridPaneDecorator.EMAILLABELX, NewLoginGridPaneDecorator.EMAILLABELY);
		
		add(okButton, NewLoginGridPaneDecorator.OKBUTTONX, NewLoginGridPaneDecorator.OKBUTTONY);
		
		add(cancelButton, NewLoginGridPaneDecorator.CANCELBUTTONX, NewLoginGridPaneDecorator.CANCELBUTTONY);
		
		//-.-on
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
	 * Sets the ok button functionality.
	 */
	private void setOkButtonFunctionality() {
		okButton.setOnAction((final ActionEvent event) -> {
			if (!(nameTextField.getText().matches("[a-zA-Z]+"))) {
				hideLoginStage();
				final LoginErrorDialog errorDialog1 = new LoginErrorDialog(MATCHERROR);
			} else if (!(passwordField.getText().equals(passwordAgainTextField.getText()))) {
				hideLoginStage();
				final LoginErrorDialog errorDialog2 = new LoginErrorDialog(ERRORPASSWORD);
			} else if (!(emailTextField.getText().matches("[a-zA-Z0-9]+[@][a-zA-Z]+[.][a-zA-Z]+"))) {
				hideLoginStage();
				final LoginErrorDialog errorDialog3 = new LoginErrorDialog(ERRORMAILFORMAT);
			} else {
				if (this.newLoginGridPaneController.checkValuesInDB(nameTextField.getText(),
						emailTextField.getText())) {
					this.newLoginGridPaneController.saveUser(nameTextField.getText(), passwordAgainTextField.getText(),
							emailTextField.getText());
					this.newLoginGridPaneController.setDBowner(nameTextField.getText());
					this.newLoginGridPaneController.setEmail(emailTextField.getText());
					hideLoginStage();
					showMainStage();
				} else {
					hideLoginStage();
					final LoginErrorDialog errorDialog4 = new LoginErrorDialog(ERRORMESSAGE);
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
		okButton.disableProperty().bind(Bindings.isEmpty(passwordAgainTextField.textProperty()));
		okButton.disableProperty().bind(Bindings.isEmpty(emailTextField.textProperty()));
	}

	/**
	 * Sets the password field key event.
	 */
	private void setPasswordFieldKeyEvent() {
		emailTextField.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER) {
				activateOkButton();
			}
		});
	}

	/**
	 * Activate ok button.
	 */
	private void activateOkButton() {
		okButton.fire();
	}

	/*
	 * private void selectAll() { List<User> query =
	 * MorphiaLoginConnector.getDataStore().createQuery(User.class).asList();
	 * for (int i = 0; i < query.size(); i++) {
	 * System.out.println(query.get(i).getName());
	 * System.out.println(query.get(i).getPassword());
	 * System.out.println(query.get(i).getEmail()); } }
	 */
}
