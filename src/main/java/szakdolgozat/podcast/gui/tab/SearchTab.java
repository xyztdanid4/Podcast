package szakdolgozat.podcast.gui.tab;

import szakdolgozat.podcast.gui.borderpane.SearchBorderPaneView;

/**
 * The Class SearchTab.
 * 
 * * @author Daniel Toth
 * 
 * @version 0.0.1
 * @since 0.0.1
 */
public class SearchTab extends ApplicationTab {

	/** The Constant SEARCHTAB_IMAGEPATH. */
	private static final String SEARCHTAB_IMAGEPATH = "appbar.section.expand.png";

	/** The Constant SEARCHTAB_TITLE. */
	private static final String SEARCHTAB_TITLE = "Click here to search and subscribe for podcasts!";

	/** The instance. */
	private static SearchTab instance = null;

	/**
	 * Instantiates a new search tab.
	 */
	private SearchTab() {
		super(SEARCHTAB_IMAGEPATH, SEARCHTAB_TITLE);
		adaptOnSelection();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see szakdolgozat.podcast.gui.tab.ApplicationTab#onselection()
	 */
	@Override
	protected void onselection() {
		setContent(new SearchBorderPaneView());
	}

	/**
	 * Gets the single instance of SearchTab.
	 *
	 * @return single instance of SearchTab
	 */
	public static SearchTab getInstance() {
		if (instance == null) {
			instance = new SearchTab();
		}
		return instance;
	}

}
