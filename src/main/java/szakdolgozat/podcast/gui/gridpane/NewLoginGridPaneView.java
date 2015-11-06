package szakdolgozat.podcast.gui.gridpane;

import javafx.application.Platform;
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
import szakdolgozat.podcast.gui.decorator.NewLoginGridPaneDecorator;
import szakdolgozat.podcast.gui.dialog.LoginErrorDialog;
import szakdolgozat.podcast.gui.samples.GridPaneSample;
import szakdolgozat.podcast.gui.stage.LoginStage;
import szakdolgozat.podcast.gui.stage.MainStage;

public class NewLoginGridPaneView extends GridPaneSample {
	private static final String MATCHERROR = "You should use only englush abc letters!";
	private static final String NAMETEXTFIELD_PROMPTEXT = "Name";
	private static final String PASSWORDTEXTFIELD_PROMPTEXT = "Password";
	private static final String NAMELABEL_TEXT = "Name";
	private static final String PASSWORDLABEL_TEXT = "Password";
	private static final String OKBUTTON_TEXT = "Login";
	private static final String CANCELBUTTON_TEXT = "Cancel (exit)";
	private static final String MESSAGELABEL_TEXT = "Welcome, please Login!";
	private static final String EMAILLABEL_TEXT = "E-mail";
	private static final String PASSWORDAGAINTEXTFIELD_PROMPTTEXT = "Password again";
	private static final String PASSWORDAGAINLABEL_TEXT = "Password again";
	private static final String EMAILTEXTFIELD_PROMTTEXT = "E-mail";
	private static final String ERRORMESSAGE = "This name or e-mail address is in use!";
	private static final String ERRORPASSWORD = "The two password is different!";
	private static final String ERRORMAILFORMAT = "Wrong e-mail format!";
	private static Button okButton;
	private static Button cancelButton;
	private static PasswordField passwordAgainTextField;
	private static TextField emailTextField;
	private static TextField nameTextField;
	private static PasswordField passwordField;
	final NewLoginGridPaneController newLoginGridPaneController;

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

	private void setCancelButtonFunctinality() {
		cancelButton.setOnAction((final ActionEvent event) -> {
			Platform.exit();
		});
	}

	private void setOkButtonFunctionality() {
		okButton.setOnAction((final ActionEvent event) -> {
			if (!(nameTextField.getText().matches("[a-zA-Z]+"))) {
				LoginStage.getInstance().hide();
				final LoginErrorDialog errorDialog1 = new LoginErrorDialog(MATCHERROR);
			} else if (!(passwordField.getText().equals(passwordAgainTextField.getText()))) {
				LoginStage.getInstance().hide();
				final LoginErrorDialog errorDialog2 = new LoginErrorDialog(ERRORPASSWORD);
			} else if (!(emailTextField.getText().matches("[a-zA-Z0-9]+[@][a-zA-Z]+[.][a-zA-Z]+"))) {
				LoginStage.getInstance().hide();
				final LoginErrorDialog errorDialog3 = new LoginErrorDialog(ERRORMAILFORMAT);
			} else {
				if (NewLoginGridPaneView.this.newLoginGridPaneController.checkValuesInDB(nameTextField.getText(),
						emailTextField.getText())) {
					NewLoginGridPaneView.this.newLoginGridPaneController.saveUser(nameTextField.getText(),
							passwordAgainTextField.getText(), emailTextField.getText());
					NewLoginGridPaneView.this.newLoginGridPaneController.setDBowner(nameTextField.getText());
					LoginStage.getInstance().hide();
					MainStage.getInstance().show();
				} else {
					LoginStage.getInstance().hide();
					final LoginErrorDialog errorDialog4 = new LoginErrorDialog(ERRORMESSAGE);
				}
			}
		});
	}

	private void setButtonDisability() {
		okButton.disableProperty().bind(Bindings.isEmpty(nameTextField.textProperty()));
		okButton.disableProperty().bind(Bindings.isEmpty(passwordField.textProperty()));
		okButton.disableProperty().bind(Bindings.isEmpty(passwordAgainTextField.textProperty()));
		okButton.disableProperty().bind(Bindings.isEmpty(emailTextField.textProperty()));
	}

	private void setPasswordFieldKeyEvent() {
		emailTextField.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER) {
				okButton.fire();
			}
		});
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
