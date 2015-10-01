package szakdolgozat.podcast.gui.borderpane;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import szakdolgozat.podcast.tabpane.ApplicationTabPane;

public class MainBorderPane extends BorderPane {
	private ApplicationTabPane tabPaneSample;
	private Label rightLabel;

	public MainBorderPane() {
		tabPaneSample = new ApplicationTabPane();
		setCenter(tabPaneSample);

		rightLabel = new Label("RIGHTSIDE");
		setRight(rightLabel);

	}
}
