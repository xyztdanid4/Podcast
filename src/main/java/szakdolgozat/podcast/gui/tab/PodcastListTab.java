package szakdolgozat.podcast.gui.tab;

import szakdolgozat.podcast.gui.borderpane.PodcastBorderPaneView;

/**
 * The Class PodcastListTab.
 * 
 * * @author Daniel Toth
 * 
 * @version 0.0.1
 * @since 0.0.1
 */
public class PodcastListTab extends ApplicationTab {

	/** The Constant PODCASTLISTTAB_IMAGEPATH. */
	private static final String PODCASTLISTTAB_IMAGEPATH = "appbar.list.png";

	/** The Constant PODCASTLISTTAB_TITLE. */
	private static final String PODCASTLISTTAB_TITLE = "Click here to manage your podcasts!";

	/**
	 * Instantiates a new podcast list tab.
	 */
	public PodcastListTab() {
		super(PODCASTLISTTAB_IMAGEPATH, PODCASTLISTTAB_TITLE);
		adaptOnSelection();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see szakdolgozat.podcast.gui.tab.ApplicationTab#onselection()
	 */
	@Override
	protected void onselection() {
		setContent(new PodcastBorderPaneView());
	}

}
