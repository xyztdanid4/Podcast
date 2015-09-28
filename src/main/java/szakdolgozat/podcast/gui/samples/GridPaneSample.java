package szakdolgozat.podcast.gui.samples;

import javafx.scene.layout.GridPane;

public class GridPaneSample extends GridPane {
	static final private int DEFAULTHSPACING = 10;
	static final private int DEFAULTVSPACING = 10;

	public GridPaneSample() {
		setHgap(DEFAULTHSPACING);
		setVgap(DEFAULTVSPACING);
	}
}
