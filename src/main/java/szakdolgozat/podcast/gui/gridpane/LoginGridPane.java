package szakdolgozat.podcast.gui.gridpane;

import java.util.List;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import szakdolgozat.podcast.basicinformation.InformationContainer;
import szakdolgozat.podcast.gui.dialog.ErrorDialog;
import szakdolgozat.podcast.gui.samples.ButtonSample;
import szakdolgozat.podcast.gui.samples.GridPaneSample;
import szakdolgozat.podcast.gui.samples.LabelSample;
import szakdolgozat.podcast.gui.samples.TextFieldSample;
import szakdolgozat.podcast.gui.stage.LoginStage;
import szakdolgozat.podcast.gui.stage.MainStage;
import szakdolgozat.podcast.morphia.MorphiaLoginConnector;
import szakdolgozat.podcast.user.User;

public class LoginGridPane extends GridPaneSample {
	private TextFieldSample nameTextFieldSample;
	private TextFieldSample passwordTextFieldSample;
	private LabelSample nameLabelSample;
	private LabelSample passwordLabelSample;
	private static final String NAMELABELSAMPLE_TOOLTIP = "Interact with name textfield!";
	private static final String PASSWORDLABELSAMPLE_TOOLTIP = "Interact with password textfield!";
	private static final String NAMETEXTFIELDSAMPLE_TOOLTP = "Enter your name!";
	private static final String PASSWORDTEXTFIELDSAMPLE_TOOLTIP = "Enter your password!";
	private static final String NAMETEXTFIELDSAMPLE_PROMPTEXT = "Name";
	private static final String PASSWORDTEXTFIELDSAMPLE_PROMPTEXT = "Password";
	private static final String NAMELABELSAMPLETEXT = "Name";
	private static final String PASSWORDLABELSAMPLETEXT = "Password";
	private static final String OKBUTTONSAMPLETEXT = "Login";
	private static final String CANCELBUTTONTEXT = "Cancel (exit)";
	private static final String OKBUTTONSMAPLE_TOOLTIP = "Press this to login!";
	private static final String CANCELBUTTONSAMPLE_TOOLTIP = "Press this to cancel, and exit!";
	private ButtonSample okButtonSample;
	private ButtonSample cancelButtonSample;
	private LabelSample messageLabelSample;
	private static final String MESSAGELABELSAMPLETEXT = "Welcome, please Login!";
	private static final String MESSAGELABELSAMPLE_TOOLTIP = "Hey Buddy welcome!";
	private static final String ERRORMESSAGE = "Name or Password is incorrect!";
	private static final String MATCHERROR = "You should use only englush abc letters!";

	public LoginGridPane() {
		nameLabelSample = new LabelSample(NAMELABELSAMPLETEXT, NAMELABELSAMPLE_TOOLTIP);
		passwordLabelSample = new LabelSample(PASSWORDLABELSAMPLETEXT, PASSWORDLABELSAMPLE_TOOLTIP);
		nameTextFieldSample = new TextFieldSample(NAMETEXTFIELDSAMPLE_PROMPTEXT, NAMETEXTFIELDSAMPLE_TOOLTP);
		passwordTextFieldSample = new TextFieldSample(PASSWORDTEXTFIELDSAMPLE_PROMPTEXT,
				PASSWORDTEXTFIELDSAMPLE_TOOLTIP);
		okButtonSample = new ButtonSample(OKBUTTONSAMPLETEXT, OKBUTTONSMAPLE_TOOLTIP);
		cancelButtonSample = new ButtonSample(CANCELBUTTONTEXT, CANCELBUTTONSAMPLE_TOOLTIP);
		messageLabelSample = new LabelSample(MESSAGELABELSAMPLETEXT, MESSAGELABELSAMPLE_TOOLTIP);
		add(messageLabelSample, 1, 1);
		add(nameLabelSample, 1, 2);
		add(passwordLabelSample, 1, 3);
		add(nameTextFieldSample, 2, 2);
		add(passwordTextFieldSample, 2, 3);
		add(okButtonSample, 2, 4);
		add(cancelButtonSample, 2, 5);
		setOkButtonSampleFunctionality();
		setCancelButtonSampleFunctinality();
		setButtonDisability();
		setPasswordTextSampleFieldKeyEvent();
	}

	private void setCancelButtonSampleFunctinality() {
		cancelButtonSample.setOnAction((ActionEvent event) -> {
			Platform.exit();
		});
	}

	private void setOkButtonSampleFunctionality() {
		okButtonSample.setOnAction((ActionEvent event) -> {
			if (!(nameTextFieldSample.getText().matches("[a-zA-Z]+"))) {
				ErrorDialog errorDialog = new ErrorDialog(MATCHERROR);
			} else {
				if (checkUserAndPassword()) {
					MainStage.getInstance().show();
					InformationContainer.getInstance().setOwner(nameTextFieldSample.getText());
					LoginStage.getInstance().hide();
				} else {
					ErrorDialog errorDialog = new ErrorDialog(ERRORMESSAGE);
				}
			}
		});
	}

	private void setButtonDisability() {
		okButtonSample.disableProperty().bind(Bindings.isEmpty(nameTextFieldSample.textProperty()));
		okButtonSample.disableProperty().bind(Bindings.isEmpty(passwordTextFieldSample.textProperty()));
	}

	private boolean checkUserAndPassword() {
		return !(MorphiaLoginConnector.getDataStore().createQuery(User.class)
				.filter("name = ", nameTextFieldSample.getText())
				.filter("password = ", passwordTextFieldSample.getText()).asList().isEmpty());
	}

	private void setPasswordTextSampleFieldKeyEvent() {
		passwordTextFieldSample.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.ENTER) {
					okButtonSample.fire();
				}
			}
		});
	}

	private void selectAll() {
		List<User> query = MorphiaLoginConnector.getDataStore().createQuery(User.class)
				.filter("name = ", nameTextFieldSample.getText())
				.filter("password = ", passwordTextFieldSample.getText()).asList();
		for (int i = 0; i < query.size(); i++) {
			System.out.println(query.get(i).getName());
			System.out.println(query.get(i).getPassword());
			System.out.println(query.get(i).getEmail());
		}
	}
}