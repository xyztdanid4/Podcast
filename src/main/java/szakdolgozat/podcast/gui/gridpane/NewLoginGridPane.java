package szakdolgozat.podcast.gui.gridpane;

import java.util.List;

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
		decorateTextField(nameTextField);
		passwordTextField = new PasswordFieldSample(PASSWORDTEXTFIELD_PROMPTEXT, PASSWORDTEXTFIELD_TOOLTIP);
		decorateTextField(passwordTextField);
		okButton = new ButtonSample(OKBUTTON_TEXT, OKBUTTON_TOOLTIP);
		decorateButton(okButton);
		cancelButton = new ButtonSample(CANCELBUTTON_TEXT, CANCELBUTTON_TOOLTIP);
		decorateButton(cancelButton);
		passwordAgainTextField = new PasswordFieldSample(PASSWORDAGAINTEXTFIELD_PROMPTTEXT,
				PASSWORDAGAINTEXTFIELD_TOOLTIP);
		decorateTextField(passwordAgainTextField);
		emailTextField = new TextFieldSample(EMAILTEXTFIELD_PROMTTEXT, EMAILTTEXTFIELD_TOOLTIP);
		decorateTextField(emailTextField);
		messageLabel = new LabelSample(MESSAGELABEL_TEXT, MESSAGELABEL_TOOLTIP);
		decorateLabel(messageLabel);
		nameLabel = new LabelSample(NAMELABEL_TEXT, NAMELABEL_TOOLTIP);
		decorateLabel(nameLabel);
		passwordLabel = new LabelSample(PASSWORDLABEL_TEXT, PASSWORDLABEL_TOOLTIP);
		decorateLabel(passwordLabel);
		passwordAgainLabel = new LabelSample(PASSWORDAGAINLABEL_TEXT, PASSWORDAGAINLABEL_TOOLTIP);
		decorateLabel(passwordAgainLabel);
		emailLabel = new LabelSample(EMAILLABEL_TEXT, EMAILLABEL_TOOLTIP);
		decorateLabel(emailLabel);
		add(messageLabel, 1, 1);
		add(nameLabel, 1, 2);
		add(passwordLabel, 1, 3);
		add(nameTextField, 2, 2);
		add(passwordTextField, 2, 3);
		add(passwordAgainTextField, 2, 4);
		add(emailTextField, 2, 5);
		add(passwordAgainLabel, 1, 4);
		add(emailLabel, 1, 5);
		add(okButton, 2, 6);
		add(cancelButton, 2, 7);
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

	private void selectAll() {
		List<User> query = MorphiaLoginConnector.getDataStore().createQuery(User.class).asList();
		for (int i = 0; i < query.size(); i++) {
			System.out.println(query.get(i).getName());
			System.out.println(query.get(i).getPassword());
			System.out.println(query.get(i).getEmail());
		}
	}
}
