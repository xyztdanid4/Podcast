package szakdolgozat.podcast.gui.tab;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import szakdolgozat.podcast.gui.borderpane.SearchBorderPane;

public class SearchTab extends ApplicationTab {
	private static final String SEARCHTAB_IMAGEPATH = "appbar.section.expand.png";
	private static final String SEARCHTAB_TITLE = "Click here to search and subscribe for podcasts!";

	public SearchTab() {
		super(SEARCHTAB_IMAGEPATH, SEARCHTAB_TITLE);
		adaptOnSelection();
	}

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
		setContent(new SearchBorderPane());
	}
}
