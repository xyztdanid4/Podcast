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
	private static PasswordFieldSample PasswordField;

	public LoginGridPane() {
		LoginGridPaneController loginGridPaneController = new LoginGridPaneController();
		nameTextField = LoginGridPaneDecorator
				.decorateTextFieldSampleFactory(new TextFieldSample(NAMETEXTFIELD_PROMPTEXT, NAMETEXTFIELD_TOOLTP));

		PasswordField = LoginGridPaneDecorator.decoratePasswordFieldSampleFactory(
				new PasswordFieldSample(PASSWORDTEXTFIELD_PROMPTEXT, PASSWORDTEXTFIELD_TOOLTIP) {
					{
						setOnKeyPressed(new EventHandler<KeyEvent>() {
							@Override
							public void handle(KeyEvent event) {
								if (event.getCode() == KeyCode.ENTER) {
									okButton.fire();
								}
							}
						});
					}
				});
		okButton = LoginGridPaneDecorator
				.decorateButtonSampleFactory(new ButtonSample(OKBUTTON_TEXT, OKBUTTONSMAPLE_TOOLTIP) {
					{
						setOnAction(new EventHandler<ActionEvent>() {
							@Override
							public void handle(ActionEvent event) {
								if (!(nameTextField.getText().matches("[a-zA-Z]+"))) {
									LoginStage.getInstance().hide();
									LoginErrorDialog errorDialog = new LoginErrorDialog(MATCHERROR);
								} else {
									if (loginGridPaneController.checkUserAndPassword(nameTextField.getText(),
											PasswordField.getText())) {
										InformationContainer.getInstance().setOwner(nameTextField.getText());
										MainStage.getInstance().show();
										LoginStage.getInstance().hide();
									} else {
										LoginStage.getInstance().hide();
										LoginErrorDialog errorDialog = new LoginErrorDialog(ERRORMESSAGE);
									}
								}
							}
						});
						disableProperty().bind(Bindings.isEmpty(nameTextField.textProperty()));
						disableProperty().bind(Bindings.isEmpty(PasswordField.textProperty()));
					}
				});

		cancelButton = LoginGridPaneDecorator
				.decorateButtonSampleFactory(new ButtonSample(CANCELBUTTON_TEXT, CANCELBUTTONS_TOOLTIP) {
					{
						setOnAction(new EventHandler<ActionEvent>() {
							@Override
							public void handle(ActionEvent event) {
								Platform.exit();
							}
						});
					}
				});
		/*
		 * LabelSample messageLabel = LoginGridPaneDecorator
		 * .decorateLabelSampleFactory(new LabelSample(MESSAGELABEL_TEXT,
		 * MESSAGELABEL_TOOLTIP));
		 * 
		 * LabelSample nameLabel = LoginGridPaneDecorator
		 * .decorateLabelSampleFactory(new LabelSample(NAMELABEL_TEXT,
		 * NAMELABEL_TOOLTIP));
		 * 
		 * LabelSample passwordLabel = LoginGridPaneDecorator
		 * .decorateLabelSampleFactory(new LabelSample(PASSWORDLABEL_TEXT,
		 * PASSWORDLABEL_TOOLTIP));
		 */
		add(LoginGridPaneDecorator.decorateLabelSampleFactory(new LabelSample(MESSAGELABEL_TEXT, MESSAGELABEL_TOOLTIP)),
				LoginGridPaneDecorator.MESSAGELABELX, LoginGridPaneDecorator.MESSAGLABELY);
		add(LoginGridPaneDecorator.decorateLabelSampleFactory(new LabelSample(NAMELABEL_TEXT, NAMELABEL_TOOLTIP)),
				LoginGridPaneDecorator.NAMELABELX, LoginGridPaneDecorator.NAMELABELY);
		add(LoginGridPaneDecorator
				.decorateLabelSampleFactory(new LabelSample(PASSWORDLABEL_TEXT, PASSWORDLABEL_TOOLTIP)),
				LoginGridPaneDecorator.PASSWORDLABELX, LoginGridPaneDecorator.PASSWORDLABELY);
		add(nameTextField, LoginGridPaneDecorator.NAMETEXTFIELDX, LoginGridPaneDecorator.NAMETEXTFIELDY);
		add(PasswordField, LoginGridPaneDecorator.PASSWORDFIELDX, LoginGridPaneDecorator.PASSWORDFIELDY);
		add(okButton, LoginGridPaneDecorator.OKBUTTONX, LoginGridPaneDecorator.OKBUTTONY);
		add(cancelButton, LoginGridPaneDecorator.CANCELBUTTONX, LoginGridPaneDecorator.CANCELBUTTONY);
		// setOkButtonFunctionality();
		// setCancelButtonFunctinality();
		// setButtonDisability();
		// setPasswordTextFieldKeyEvent();
		LoginGridPaneDecorator.decorateFactory(this);
	}

	/*
	 * private void setCancelButtonFunctinality() {
	 * cancelButton.setOnAction((ActionEvent event) -> { Platform.exit(); }); }
	 */
	/*
	 * private void setOkButtonFunctionality() {
	 * okButton.setOnAction((ActionEvent event) -> { if
	 * (!(nameTextField.getText().matches("[a-zA-Z]+"))) {
	 * LoginStage.getInstance().hide(); LoginErrorDialog errorDialog = new
	 * LoginErrorDialog(MATCHERROR); } else { if (checkUserAndPassword()) {
	 * InformationContainer.getInstance().setOwner(nameTextField.getText());
	 * MainStage.getInstance().show(); LoginStage.getInstance().hide(); } else {
	 * LoginStage.getInstance().hide(); LoginErrorDialog errorDialog = new
	 * LoginErrorDialog(ERRORMESSAGE); } } }); }
	 */
	/*
	 * private void setButtonDisability() {
	 * okButton.disableProperty().bind(Bindings.isEmpty(nameTextField.
	 * textProperty()));
	 * okButton.disableProperty().bind(Bindings.isEmpty(PasswordField.
	 * textProperty())); }
	 */

	/*
	 * private boolean checkUserAndPassword() { return
	 * !(MorphiaLoginConnector.getDataStore().createQuery(User.class).filter(
	 * "name = ", nameTextField.getText()) .filter("password = ",
	 * PasswordField.getText()).asList().isEmpty()); }
	 */

	/*
	 * private void setPasswordTextFieldKeyEvent() {
	 * PasswordField.setOnKeyPressed(new EventHandler<KeyEvent>() {
	 * 
	 * @Override public void handle(KeyEvent event) { if (event.getCode() ==
	 * KeyCode.ENTER) { okButton.fire(); } } }); }
	 */
}