package szakdolgozat.podcast.gui.borderpane;

import javafx.beans.InvalidationListener;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import szakdolgozat.podcast.basicinformation.InformationContainer;
import szakdolgozat.podcast.builder.TextBuilder;
import szakdolgozat.podcast.builder.TextFieldBuilder;
import szakdolgozat.podcast.builder.VBoxBuilder;
import szakdolgozat.podcast.gui.decorator.Decorator;
import szakdolgozat.podcast.gui.decorator.SettingsBPDecorator;
import szakdolgozat.podcast.threads.PodcastListener;

public class SettingsBorderPane extends BorderPane {
	private static final String EMAIL = "Email, turn on if you would like to get email updates!";
	private static final String SETTINGS = "Settings";
	private final CheckBox emailCheckBox;
	private final TextField frequencyTextField;
	private final Button updateButton;

	public SettingsBorderPane() {
		this.emailCheckBox = new CheckBox(EMAIL);
		//-.-off
		this.frequencyTextField = TextFieldBuilder.create()
												.size(80, 30)
												.promptText("Frequency")
												.build();
		
		this.updateButton = new Button("Update Frequency");
		setButtonBehaviour();
		
		final VBox settingsVBox = VBoxBuilder.create()
											.checkBox(this.emailCheckBox)
											.smallText("Subscribed Podcast Refresh Frequency in hours:")
											.noTextField(this.frequencyTextField)
											.noButton(this.updateButton)
											.topLeftAlignment()
											.build();
		//-.-on

		this.emailCheckBox.selectedProperty().addListener((InvalidationListener) observable -> {
			InformationContainer.getInstance().setEmailRequired(this.emailCheckBox.isSelected() ? true : false);
		});

		SettingsBPDecorator.decorateFactory(this);
		setPadding();
		setMargin(settingsVBox, new Insets(20));
		setTop(TextBuilder.create().bigText(SETTINGS).build());
		setLeft(settingsVBox);
	}

	private void setButtonBehaviour() {
		this.updateButton.setDisable(true);
		this.updateButton.setOnAction(event -> {
			PodcastListener.getInstance()
					.setFrequency(Integer.parseInt(SettingsBorderPane.this.frequencyTextField.getText()));
			PodcastListener.getInstance().getTimer().cancel();
			PodcastListener.getInstance().startListeningToSubscribedPodcasts();
		});

		this.frequencyTextField.textProperty().addListener((InvalidationListener) observable -> {
			this.updateButton.setDisable((this.frequencyTextField.getText().matches("[1-9]+[0-9]*") ? false : true));
		});

	}

	private void setPadding() {
		setPadding(new Insets(Decorator.PADDING, Decorator.PADDING, Decorator.PADDING, Decorator.PADDING));
	}
}