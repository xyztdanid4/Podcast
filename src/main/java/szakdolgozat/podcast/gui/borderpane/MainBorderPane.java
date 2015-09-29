package szakdolgozat.podcast.gui.borderpane;

import javafx.scene.control.Label;
import szakdolgozat.podcast.gui.samples.BorderPaneSample;
import szakdolgozat.podcast.gui.samples.TabPaneSample;

public class MainBorderPane extends BorderPaneSample {
	private TabPaneSample tabPaneSample;
	private Label rightLabel;

	public MainBorderPane() {
		tabPaneSample = new TabPaneSample();
		setCenter(tabPaneSample);

		rightLabel = new Label("RIGHTSIDE");
		setRight(rightLabel);

	}
}
