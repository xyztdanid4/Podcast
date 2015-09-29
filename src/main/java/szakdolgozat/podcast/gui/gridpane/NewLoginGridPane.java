package szakdolgozat.podcast.gui.gridpane;

import java.util.List;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import szakdolgozat.podcast.basicinformation.InformationContainer;
import szakdolgozat.podcast.gui.dialog.ErrorDialog;
import szakdolgozat.podcast.gui.samples.ButtonSample;
import szakdolgozat.podcast.gui.samples.GridPaneSample;
import szakdolgozat.podcast.gui.samples.LabelSample;
import szakdolgozat.podcast.gui.samples.TextFieldSample;
import szakdolgozat.podcast.gui.stage.LoginErrorDialogStage;
import szakdolgozat.podcast.gui.stage.LoginStage;
import szakdolgozat.podcast.gui.stage.MainStage;
import szakdolgozat.podcast.morphia.MorphiaLoginConnector;
import szakdolgozat.podcast.user.User;

public class NewLoginGridPane extends GridPaneSample {
	private TextFieldSample nameTextFieldSample;
	private TextFieldSample passwordTextFieldSample;
	private LabelSample nameLabelSample;
	private LabelSample passwordLabelSample;
	private final String NAMELABELSAMPLE_TOOLTIP = "Interact with name textfield!";
	private final String PASSWORDLABELSAMPLE_TOOLTIP = "Interact with password textfield!";
	private final String NAMETEXTFIELDSAMPLE_TOOLTP = "Enter your name!";
	private final String PASSWORDTEXTFIELDSAMPLE_TOOLTIP = "Enter your password!";
	private final String NAMETEXTFIELDSAMPLE_PROMPTEXT = "Name";
	private final String PASSWORDTEXTFIELDSAMPLE_PROMPTEXT = "Password";
	private final String NAMELABELSAMPLETEXT = "Name";
	private final String PASSWORDLABELSAMPLETEXT = "Password";
	private final String OKBUTTONSAMPLETEXT = "Login";
	private final String CANCELBUTTONTEXT = "Cancel (exit)";
	private final String OKBUTTONSMAPLE_TOOLTIP = "Press this to login!";
	private final String CANCELBUTTONSAMPLE_TOOLTIP = "Press this to cancel, and exit!";
	private ButtonSample okButtonSample;
	private ButtonSample cancelButtonSample;
	private LabelSample messageLabelSample;
	private final String MESSAGELABELSAMPLETEXT = "Welcome, please Login!";
	private final String MESSAGELABELSAMPLE_TOOLTIP = "Hey Buddy welcome!";
	private LabelSample emailLabelSample;
	private final String EMAILLABELSAMPLE_TOOLTIP = "Interact with e-mail field!";
	private final String EMAILLABELSAMPLETEXT = "E-mail";
	private TextFieldSample passwordAgainTextFieldSample;
	private final String PASSWORDAGAINTEXTFIELDSAMPLEPROMPTTEXT = "Password again";
	private final String PASSWORDAGAINTEXTFIELDSAMPLE_TOOLTIP = "Enter your password again!";
	private LabelSample passwordAgainLabelSample;
	private final String PASSWORDAGAINLABELSAMPLETEXT = "Password again";
	private final String PASSWORDAGAINLABELSAMPLE_TOOLTIP = "Interact with password again field!";
	private TextFieldSample emailTextFieldSample;
	private final String EMAILTEXTFIELDSAMPLEPROMTTEXT = "E-mail";
	private final String EMAILTTEXTFIELDSAMPLE_TOOLTIP = "Enter your mail addres!";
	private final String ERRORMESSAGE = "This name or e-mail address is in use!";
	private final String ERRORPASSWORD = "The two password is different";
	private final String ERRORMAILFORMAT = "Wrong e-mail format!";

	public NewLoginGridPane() {
		nameLabelSample = new LabelSample(NAMELABELSAMPLETEXT, NAMELABELSAMPLE_TOOLTIP);
		passwordLabelSample = new LabelSample(PASSWORDLABELSAMPLETEXT, PASSWORDLABELSAMPLE_TOOLTIP);
		nameTextFieldSample = new TextFieldSample(NAMETEXTFIELDSAMPLE_PROMPTEXT, NAMETEXTFIELDSAMPLE_TOOLTP);
		passwordTextFieldSample = new TextFieldSample(PASSWORDTEXTFIELDSAMPLE_PROMPTEXT,
				PASSWORDTEXTFIELDSAMPLE_TOOLTIP);
		okButtonSample = new ButtonSample(OKBUTTONSAMPLETEXT, OKBUTTONSMAPLE_TOOLTIP);
		cancelButtonSample = new ButtonSample(CANCELBUTTONTEXT, CANCELBUTTONSAMPLE_TOOLTIP);
		messageLabelSample = new LabelSample(MESSAGELABELSAMPLETEXT, MESSAGELABELSAMPLE_TOOLTIP);
		passwordAgainTextFieldSample = new TextFieldSample(PASSWORDAGAINTEXTFIELDSAMPLEPROMPTTEXT,
				PASSWORDAGAINTEXTFIELDSAMPLE_TOOLTIP);
		passwordAgainLabelSample = new LabelSample(PASSWORDAGAINLABELSAMPLETEXT, PASSWORDAGAINLABELSAMPLE_TOOLTIP);
		emailLabelSample = new LabelSample(EMAILLABELSAMPLETEXT, EMAILLABELSAMPLE_TOOLTIP);
		passwordAgainTextFieldSample = new TextFieldSample(PASSWORDAGAINTEXTFIELDSAMPLEPROMPTTEXT,
				PASSWORDAGAINTEXTFIELDSAMPLE_TOOLTIP);
		passwordAgainTextFieldSample = new TextFieldSample(PASSWORDAGAINTEXTFIELDSAMPLEPROMPTTEXT,
				PASSWORDAGAINTEXTFIELDSAMPLE_TOOLTIP);
		emailTextFieldSample = new TextFieldSample(EMAILTEXTFIELDSAMPLEPROMTTEXT, EMAILTTEXTFIELDSAMPLE_TOOLTIP);
		add(messageLabelSample, 1, 1);
		add(nameLabelSample, 1, 2);
		add(passwordLabelSample, 1, 3);
		add(nameTextFieldSample, 2, 2);
		add(passwordTextFieldSample, 2, 3);
		add(passwordAgainTextFieldSample, 2, 4);
		add(emailTextFieldSample, 2, 5);
		add(passwordAgainLabelSample, 1, 4);
		add(emailLabelSample, 1, 5);
		add(okButtonSample, 2, 6);
		add(cancelButtonSample, 2, 7);
		setOkButtonSampleFunctionality();
		setCancelButtonSampleFunctinality();
		setButtonDisability();
	}

	private void setCancelButtonSampleFunctinality() {
		cancelButtonSample.setOnAction((ActionEvent event) -> {
			Platform.exit();
		});
	}

	private void setOkButtonSampleFunctionality() {
		okButtonSample.setOnAction((ActionEvent event) -> {
			if (!(nameTextFieldSample.getText().matches("[a-zA-Z]+"))) {
				LoginErrorDialogStage.getInstance().show();
			} else if (!(passwordTextFieldSample.getText().equals(passwordAgainTextFieldSample.getText()))) {
				ErrorDialog errorDialog = new ErrorDialog(ERRORPASSWORD);
			} else if (!(emailTextFieldSample.getText().matches("[a-zA-Z0-9]+[@][a-zA-Z]+[.][a-zA-Z]+"))) {
				ErrorDialog errorDialog = new ErrorDialog(ERRORMAILFORMAT);
			} else {
				if (checkValuesInDB()) {
					MorphiaLoginConnector.save(new User(nameTextFieldSample.getText(),
							passwordAgainTextFieldSample.getText(), emailTextFieldSample.getText()));
					InformationContainer.getInstance().setOwner(nameTextFieldSample.getText());
					System.out.println(InformationContainer.getOwner());
					LoginStage.getInstance().hide();
					MainStage.getInstance().show();
				} else {
					ErrorDialog errorDialog = new ErrorDialog(ERRORMESSAGE);
				}
			}
		});
	}

	private void setButtonDisability() {
		okButtonSample.disableProperty().bind(Bindings.isEmpty(nameTextFieldSample.textProperty()));
		okButtonSample.disableProperty().bind(Bindings.isEmpty(passwordTextFieldSample.textProperty()));
		okButtonSample.disableProperty().bind(Bindings.isEmpty(passwordAgainTextFieldSample.textProperty()));
		okButtonSample.disableProperty().bind(Bindings.isEmpty(emailTextFieldSample.textProperty()));
	}

	private boolean checkValuesInDB() {
		return MorphiaLoginConnector.getDataStore().createQuery(User.class)
				.filter("name = ", nameTextFieldSample.getText()).asList().isEmpty()
				&& MorphiaLoginConnector.getDataStore().createQuery(User.class)
						.filter("email = ", emailTextFieldSample.getText()).asList().isEmpty();
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
