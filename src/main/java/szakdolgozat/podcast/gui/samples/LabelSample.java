package szakdolgozat.podcast.gui.samples;

import javafx.scene.control.Label;

public class LabelSample extends Label {
	private TooltipSample labelTooltip;

	public LabelSample(final String text, final String labeltooltip) {
		setText(text);
		setTooltip(labelTooltip);
	}
}
