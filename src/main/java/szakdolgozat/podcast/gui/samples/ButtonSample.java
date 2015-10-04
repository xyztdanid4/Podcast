package szakdolgozat.podcast.gui.samples;

import javafx.scene.control.Button;

public class ButtonSample extends Button {
	public ButtonSample(final String text, final String tooltip) {
		super(text);
		new TooltipSample(tooltip);
		setTooltip(new TooltipSample(tooltip));
	}
}
