package szakdolgozat.podcast.gui.samples;

import javafx.scene.control.PasswordField;

public class PasswordFieldSample extends PasswordField {
	public PasswordFieldSample(final String promptText, final String textFieldTooltip) {
		setTooltip(new TooltipSample(textFieldTooltip));
		setPromptText(promptText);
	}
}
