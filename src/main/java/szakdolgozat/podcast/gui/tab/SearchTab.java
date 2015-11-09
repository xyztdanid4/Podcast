package szakdolgozat.podcast.gui.tab;

import szakdolgozat.podcast.gui.borderpane.SearchBorderPaneView;

public class SearchTab extends ApplicationTab {
	private static final String SEARCHTAB_IMAGEPATH = "appbar.section.expand.png";
	private static final String SEARCHTAB_TITLE = "Click here to search and subscribe for podcasts!";
	private static SearchTab instance = null;

	private SearchTab() {
		super(SEARCHTAB_IMAGEPATH, SEARCHTAB_TITLE);
		adaptOnSelection();

	}

	@Override
	protected void onselection() {
		setContent(new SearchBorderPaneView());
	}

	public static SearchTab getInstance() {
		if (instance == null) {
			instance = new SearchTab();
		}
		return instance;
	}

}
