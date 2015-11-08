package szakdolgozat.podcast.gui.samples;

import javafx.application.Platform;
import javafx.stage.Stage;

public class StageSample extends Stage {
	public StageSample(final String title) {
		setTitle(title);
		setResizable(false);
		setOnCloseAction();
	}

	private void setOnCloseAction() {
		setOnCloseRequest(event -> {
			System.out.println("exiting");
			Platform.exit();
			System.exit(0);
		});
	}

}
