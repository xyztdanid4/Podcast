package szakdolgozat.podcast.gui.tab;

import szakdolgozat.podcast.gui.borderpane.SearchBorderPane;

public class SearchTab extends ApplicationTab {
	private static final String SEARCHTAB_IMAGEPATH = "appbar.section.expand.png";
	private static final String SEARCHTAB_TITLE = "Click here to search and subscribe for podcasts!";

	public SearchTab() {
		super(SEARCHTAB_IMAGEPATH, SEARCHTAB_TITLE);
		setContent(new SearchBorderPane());
	}
}
