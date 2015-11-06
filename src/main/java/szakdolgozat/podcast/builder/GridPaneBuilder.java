package szakdolgozat.podcast.builder;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import szakdolgozat.podcast.gui.samples.ButtonSample;

public class GridPaneBuilder {
	static GridPaneBuilder insance = new GridPaneBuilder();
	private static GridPane gridPane;

	private static GridPaneBuilder getInsance() {
		return insance;
	}

	public static GridPaneBuilder create() {
		gridPane = new GridPane();
		return getInsance();
	}

	public GridPaneBuilder button(final Button button) {

		return getInsance();
	}

	public GridPaneBuilder buttonSample(final ButtonSample buttonSample) {
		return getInsance();
	}

	public GridPaneBuilder label(final Label label) {
		return getInsance();
	}

	public GridPaneBuilder textField(final TextField textField) {
		return getInsance();
	}

	public GridPaneBuilder textFieldSample() {
		return getInsance();
	}

}
