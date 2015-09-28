package szakdolgozat.podcast.gui.samples;

import javafx.scene.control.TextField;

public class TextFieldSample extends TextField {
	private TooltipSample textFieldTooltip;

	public TextFieldSample(final String promptText,
			final String textFieldTooltip) {
		this.textFieldTooltip = new TooltipSample(textFieldTooltip);
		setTooltip(this.textFieldTooltip);
		setPromptText(promptText);
		// setPadding(new Insets(30, 30, 30, 30));
	}
}
