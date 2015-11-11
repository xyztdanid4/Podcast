package szakdolgozat.podcast.gui.samples;

import javafx.scene.control.ProgressBar;

public class ProgressBarSample {
	public static ProgressBarSample instance = new ProgressBarSample();
	public static ProgressBar progressBar;

	private ProgressBarSample() {
		super();
		progressBar = new ProgressBar(0);
	}

	public static ProgressBar getProgressBar() {
		return progressBar;
	}

}
