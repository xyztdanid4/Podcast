package szakdolgozat.podcast.gui.tab;

import szakdolgozat.podcast.gui.borderpane.PlayListBorderPaneView;

/**
 * The Class PlayListTab.
 * 
 * * @author Daniel Toth
 * 
 * @version 0.0.1
 * @since 0.0.1
 */
public class PlayListTab extends ApplicationTab {

	/** The Constant PLAYLISTTAB_IMAGEPATH. */
	private static final String PLAYLISTTAB_IMAGEPATH = "appbar.control.play.png";

	/** The Constant PLAYLISTTAB_TITLE. */
	private static final String PLAYLISTTAB_TITLE = "Click here for your playlists!";

	/**
	 * Instantiates a new play list tab.
	 */
	public PlayListTab() {
		super(PLAYLISTTAB_IMAGEPATH, PLAYLISTTAB_TITLE);
		adaptOnSelection();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see szakdolgozat.podcast.gui.tab.ApplicationTab#onselection()
	 */
	@Override
	protected void onselection() {
		setContent(new PlayListBorderPaneView());
	}
}
