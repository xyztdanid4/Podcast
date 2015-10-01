package szakdolgozat.podcast.gui.tab;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import szakdolgozat.podcast.gui.borderpane.PodcastBorderPane;
import szakdolgozat.podcast.gui.samples.TabSample;

public class PodcastListTab extends TabSample {
	private static final String PODCASTLISTTAB_IMAGEPATH = "appbar.list.png";
	private static final String PODCASTLISTTAB_TITLE = "Click here to manage your podcasts!";

	public PodcastListTab() {
		super(PODCASTLISTTAB_IMAGEPATH, PODCASTLISTTAB_TITLE);
		adaptOnSelection();
		// setContent(podcastBorderPane);
	}

	// ENNEK KELL LEFUTNI MINDIG MIKOR RALEPUNK ERRE A TABRA
	private void adaptOnSelection() {
		selectedProperty().addListener(new InvalidationListener() {
			@Override
			public void invalidated(Observable observable) {
				if (isSelected()) {
					onselection();
				}
			}
		});
	}

	private void onselection() {
		setContent(new PodcastBorderPane());
	}

}
