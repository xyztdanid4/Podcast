package szakdolgozat.podcast.gui.borderpane;

import javafx.scene.control.Label;
import szakdolgozat.podcast.gui.samples.BorderPaneSample;
import szakdolgozat.podcast.tabpane.ApplicationTabPane;

public class MainBorderPane extends BorderPaneSample {
	private ApplicationTabPane tabPaneSample;
	private Label rightLabel;

	public MainBorderPane() {
		tabPaneSample = new ApplicationTabPane();
		setCenter(tabPaneSample);

		rightLabel = new Label("RIGHTSIDE");
		setRight(rightLabel);

	}
}
