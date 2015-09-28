package szakdolgozat.podcast.gui.samples;

import javafx.scene.control.Button;

public class ButtonSample extends Button {
	private TooltipSample buttonSampleTooltip;

	public ButtonSample(final String text, final String tooltip) {
		super(text);
		buttonSampleTooltip = new TooltipSample(tooltip);
		setTooltip(buttonSampleTooltip);
	}
}
