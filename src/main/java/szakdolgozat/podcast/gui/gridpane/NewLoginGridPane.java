package szakdolgozat.podcast.gui.gridpane;

import java.util.List;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import szakdolgozat.podcast.basicinformation.InformationContainer;
import szakdolgozat.podcast.gui.decorator.NewLoginGridPaneDecorator;
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

public class NewLoginGridPane extends GridPaneSample {
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
	private static final String OKBUTTON_TOOLTIP = "Press this to login!";
	private static final String CANCELBUTTON_TOOLTIP = "Press this to cancel, and exit!";
	private static final String MESSAGELABEL_TEXT = "Welcome, please Login!";
	private static final String MESSAGELABEL_TOOLTIP = "Hey Buddy welcome!";
	private static final String EMAILLABEL_TOOLTIP = "Interact with e-mail field!";
	private static final String EMAILLABEL_TEXT = "E-mail";
	private static final String PASSWORDAGAINTEXTFIELD_PROMPTTEXT = "Password again";
	private static final String PASSWORDAGAINTEXTFIELD_TOOLTIP = "Enter your password again!";
	private static final String PASSWORDAGAINLABEL_TEXT = "Password again";
	private static final String PASSWORDAGAINLABEL_TOOLTIP = "Interact with password again field!";
	private static final String EMAILTEXTFIELD_PROMTTEXT = "E-mail";
	private static final String EMAILTTEXTFIELD_TOOLTIP = "Enter your mail addres!";
	private static final String ERRORMESSAGE = "This name or e-mail address is in use!";
	private static final String ERRORPASSWORD = "The two password is different!";
	private static final String ERRORMAILFORMAT = "Wrong e-mail format!";
	private static ButtonSample okButton;
	private static ButtonSample cancelButton;
	private static PasswordFieldSample passwordAgainTextField;
	private static TextFieldSample emailTextField;
	private static TextFieldSample nameTextField;
	private static PasswordFieldSample passwordTextField;
	private static LabelSample messageLabel;
	private static LabelSample nameLabel;
	private static LabelSample passwordLabel;
	private static LabelSample passwordAgainLabel;
	private static LabelSample emailLabel;

	public NewLoginGridPane() {
		nameTextField = new TextFieldSample(NAMETEXTFIELD_PROMPTEXT, NAMETEXTFIELD_TOOLTP);
		NewLoginGridPaneDecorator.decorateTextField(nameTextField);
		passwordTextField = new PasswordFieldSample(PASSWORDTEXTFIELD_PROMPTEXT, PASSWORDTEXTFIELD_TOOLTIP);
		NewLoginGridPaneDecorator.decorateTextField(passwordTextField);
		okButton = new ButtonSample(OKBUTTON_TEXT, OKBUTTON_TOOLTIP);
		NewLoginGridPaneDecorator.decorateButton(okButton);
		cancelButton = new ButtonSample(CANCELBUTTON_TEXT, CANCELBUTTON_TOOLTIP);
		NewLoginGridPaneDecorator.decorateButton(cancelButton);
		passwordAgainTextField = new PasswordFieldSample(PASSWORDAGAINTEXTFIELD_PROMPTTEXT,
				PASSWORDAGAINTEXTFIELD_TOOLTIP);
		NewLoginGridPaneDecorator.decorateTextField(passwordAgainTextField);
		emailTextField = new TextFieldSample(EMAILTEXTFIELD_PROMTTEXT, EMAILTTEXTFIELD_TOOLTIP);
		NewLoginGridPaneDecorator.decorateTextField(emailTextField);
		messageLabel = new LabelSample(MESSAGELABEL_TEXT, MESSAGELABEL_TOOLTIP);
		NewLoginGridPaneDecorator.decorateLabel(messageLabel);
		nameLabel = new LabelSample(NAMELABEL_TEXT, NAMELABEL_TOOLTIP);
		NewLoginGridPaneDecorator.decorateLabel(nameLabel);
		passwordLabel = new LabelSample(PASSWORDLABEL_TEXT, PASSWORDLABEL_TOOLTIP);
		NewLoginGridPaneDecorator.decorateLabel(passwordLabel);
		passwordAgainLabel = new LabelSample(PASSWORDAGAINLABEL_TEXT, PASSWORDAGAINLABEL_TOOLTIP);
		NewLoginGridPaneDecorator.decorateLabel(passwordAgainLabel);
		emailLabel = new LabelSample(EMAILLABEL_TEXT, EMAILLABEL_TOOLTIP);
		NewLoginGridPaneDecorator.decorateLabel(emailLabel);
		add(messageLabel, NewLoginGridPaneDecorator.MESSAGELABELX, NewLoginGridPaneDecorator.MESSAGLABELY);
		add(nameLabel, NewLoginGridPaneDecorator.NAMELABELX, NewLoginGridPaneDecorator.NAMELABELY);
		add(passwordLabel, NewLoginGridPaneDecorator.PASSWORDLABELX, NewLoginGridPaneDecorator.PASSWORDLABELY);
		add(nameTextField, NewLoginGridPaneDecorator.NAMETEXTFIELDX, NewLoginGridPaneDecorator.NAMETEXTFIELDY);
		add(passwordTextField, NewLoginGridPaneDecorator.PASSWORDFIELDX, NewLoginGridPaneDecorator.PASSWORDFIELDY);
		add(passwordAgainTextField, NewLoginGridPaneDecorator.PASSWORDAGAINTEXTFIELDX,
				NewLoginGridPaneDecorator.PASSWORDAGAINTEXTFIELDY);
		add(emailTextField, NewLoginGridPaneDecorator.EMAILTEXTFIELDX, NewLoginGridPaneDecorator.EMAILTEXTFIELDY);
		add(passwordAgainLabel, NewLoginGridPaneDecorator.PASSWORDAGAINLABELX,
				NewLoginGridPaneDecorator.PASSWORDAGAINLABELY);
		add(emailLabel, NewLoginGridPaneDecorator.EMAILLABELX, NewLoginGridPaneDecorator.EMAILLABELY);
		add(okButton, NewLoginGridPaneDecorator.OKBUTTONX, NewLoginGridPaneDecorator.OKBUTTONY);
		add(cancelButton, NewLoginGridPaneDecorator.CANCELBUTTONX, NewLoginGridPaneDecorator.CANCELBUTTONY);
		setOkButtonFunctionality();
		setCancelButtonFunctinality();
		setButtonDisability();
		setPasswordTextFieldKeyEvent();
		NewLoginGridPaneDecorator.decorate(this);
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
			} else if (!(passwordTextField.getText().equals(passwordAgainTextField.getText()))) {
				LoginStage.getInstance().hide();
				ErrorDialog errorDialog = new ErrorDialog(ERRORPASSWORD);
			} else if (!(emailTextField.getText().matches("[a-zA-Z0-9]+[@][a-zA-Z]+[.][a-zA-Z]+"))) {
				LoginStage.getInstance().hide();
				ErrorDialog errorDialog = new ErrorDialog(ERRORMAILFORMAT);
			} else {
				if (checkValuesInDB()) {
					saveUser();
					setDBowner();
					LoginStage.getInstance().hide();
					MainStage.getInstance().show();
				} else {
					LoginStage.getInstance().hide();
					ErrorDialog errorDialog = new ErrorDialog(ERRORMESSAGE);
				}
			}
		});
	}

	private void saveUser() {
		MorphiaLoginConnector
				.save(new User(nameTextField.getText(), passwordAgainTextField.getText(), emailTextField.getText()));
	}

	private void setDBowner() {
		InformationContainer.getInstance().setOwner(nameTextField.getText());
	}

	private void setButtonDisability() {
		okButton.disableProperty().bind(Bindings.isEmpty(nameTextField.textProperty()));
		okButton.disableProperty().bind(Bindings.isEmpty(passwordTextField.textProperty()));
		okButton.disableProperty().bind(Bindings.isEmpty(passwordAgainTextField.textProperty()));
		okButton.disableProperty().bind(Bindings.isEmpty(emailTextField.textProperty()));
	}

	private boolean checkValuesInDB() {
		return MorphiaLoginConnector.getDataStore().createQuery(User.class).filter("name = ", nameTextField.getText())
				.asList().isEmpty()
				&& MorphiaLoginConnector.getDataStore().createQuery(User.class)
						.filter("email = ", emailTextField.getText()).asList().isEmpty();
	}

	private void setPasswordTextFieldKeyEvent() {
		emailTextField.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.ENTER) {
					okButton.fire();
				}
			}
		});
	}

	private void selectAll() {
		List<User> query = MorphiaLoginConnector.getDataStore().createQuery(User.class).asList();
		for (int i = 0; i < query.size(); i++) {
			System.out.println(query.get(i).getName());
			System.out.println(query.get(i).getPassword());
			System.out.println(query.get(i).getEmail());
		}
	}
}
