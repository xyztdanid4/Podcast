package szakdolgozat.podcast.gui.samples;

import javafx.scene.control.TextField;

public class TextFieldSample extends TextField {
	public TextFieldSample(final String promptText, final String textFieldTooltip) {
		setTooltip(new TooltipSample(textFieldTooltip));
		setPromptText(promptText);
	}
}
