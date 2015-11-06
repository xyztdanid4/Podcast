package szakdolgozat.podcast.builder;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import szakdolgozat.podcast.gui.samples.StageSample;

public class StageBuilder {
	static StageBuilder instance = new StageBuilder();
	private static StageSample stage;

	public StageBuilder() {

	}

	public static StageBuilder create(final String title) {
		stage = new StageSample(title);
		return getInstance();
	}

	private static StageBuilder getInstance() {
		return instance;
	}

	public StageBuilder scene(final Pane pane, final int width, final int height) {
		stage.setScene(new Scene(pane, width, height));
		return getInstance();
	}

	public StageSample build() {
		return stage;
	}
}
