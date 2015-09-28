package szakdolgozat.podcast.gui.tab;

import szakdolgozat.podcast.gui.borderpane.SearchBorderPane;
import szakdolgozat.podcast.gui.samples.TabSample;

public class SearchTab extends TabSample {
	private SearchBorderPane searchBorderPane;
	private final String SEARCHTAB_IMAGEPATH = "appbar.section.expand.png";
	private final String SEARCHTAB_TITLE = "Click here to search and subscribe for podcasts!";

	public SearchTab() {
		searchBorderPane = new SearchBorderPane();
		super.makeTab((TabSample) this, SEARCHTAB_IMAGEPATH, SEARCHTAB_TITLE);
		setContent(searchBorderPane);
	}
}
