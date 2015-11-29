package szakdolgozat.podcast.gui.borderpane;

import javafx.beans.InvalidationListener;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import szakdolgozat.podcast.controller.SettingsBorderPaneController;
import szakdolgozat.podcast.data.basicinformation.InformationContainer;
import szakdolgozat.podcast.gui.builder.ButtonBuilder;
import szakdolgozat.podcast.gui.builder.CheckBoxBuilder;
import szakdolgozat.podcast.gui.builder.TextBuilder;
import szakdolgozat.podcast.gui.builder.TextFieldBuilder;
import szakdolgozat.podcast.gui.builder.VBoxBuilder;
import szakdolgozat.podcast.gui.decorator.Decorator;
import szakdolgozat.podcast.gui.decorator.SettingsBPDecorator;

public class SettingsBorderPaneView extends BorderPane {
	private static final String SUBSCRIBED_PODCAST_REFRESH_FREQUENCY_IN_HOURS = "Subscribed Podcast Refresh Frequency in hours:";
	private static final String UPDATE_FREQUENCY = "Update Frequency";
	private static final String FREQUENCY = "Frequency";
	private static final String EMAIL = "Email, turn on if you would like to get email updates!";
	private static final String SETTINGS = "Settings";
	private final CheckBox emailCheckBox;
	private final TextField frequencyTextField;
	private final Button updateButton;
	private final SettingsBorderPaneController settingsBorderPaneController;
	private final VBox settingsVBox;

	public SettingsBorderPaneView() {
		this.settingsBorderPaneController = new SettingsBorderPaneController();
		//-.-off
		this.emailCheckBox = CheckBoxBuilder.create()
											.text(EMAIL)
											.setDefaultValue(InformationContainer.getInstance().isEmailRequired())
											.build();
		
		this.frequencyTextField = TextFieldBuilder.create()
												.size(SettingsBPDecorator.TEXTFIELDWIDTH, SettingsBPDecorator.TEXTFIELDHEIGHT)
												.promptText(FREQUENCY)
												.build();
		
		this.updateButton = ButtonBuilder.create().text(UPDATE_FREQUENCY).build();
		setUpdateButtonBehaviour();
		
		this.settingsVBox = VBoxBuilder.create()
											.checkBox(this.emailCheckBox)
											.smallText(SUBSCRIBED_PODCAST_REFRESH_FREQUENCY_IN_HOURS)
											.noTextField(this.frequencyTextField)
											.noButton(this.updateButton)
											.topLeftAlignment()
											.build();
		//-.-on

		setEmailCheckBoxSelectingAction();
		decorate();
		setTop(TextBuilder.create().bigText(SETTINGS).build());
		setLeft(this.settingsVBox);
	}

	private void decorate() {
		SettingsBPDecorator.decorateFactory(this);
		setPadding();
		setMargin(this.settingsVBox, new Insets(SettingsBPDecorator.INSETS));
	}

	private void setEmailCheckBoxSelectingAction() {
		this.emailCheckBox.selectedProperty().addListener((InvalidationListener) observable -> {
			this.settingsBorderPaneController.setEmailRequired(this.emailCheckBox.isSelected() ? true : false);
		});
	}

	private void setUpdateButtonBehaviour() {
		this.updateButton.setDisable(true);
		setUpdateButtonAction();
		setUpdateButtonSelectingAction();

	}

	private void setUpdateButtonSelectingAction() {
		this.frequencyTextField.textProperty().addListener((InvalidationListener) observable -> {
			this.updateButton.setDisable((this.frequencyTextField.getText().matches("[1-9]+[0-9]*") ? false : true));
		});
	}

	private void setUpdateButtonAction() {
		this.updateButton.setOnAction(event -> {
			this.settingsBorderPaneController.setFrequency(SettingsBorderPaneView.this.frequencyTextField.getText());
		});
	}

	private void setPadding() {
		setPadding(new Insets(Decorator.PADDING, Decorator.PADDING, Decorator.PADDING, Decorator.PADDING));
	}
}
