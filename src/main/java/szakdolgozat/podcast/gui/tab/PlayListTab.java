package szakdolgozat.podcast.gui.tab;

import szakdolgozat.podcast.gui.borderpane.PlayListBorderPaneView;

public class PlayListTab extends ApplicationTab {
	private static final String PLAYLISTTAB_IMAGEPATH = "appbar.control.play.png";
	private static final String PLAYLISTTAB_TITLE = "Click here for your playlists!";

	public PlayListTab() {
		super(PLAYLISTTAB_IMAGEPATH, PLAYLISTTAB_TITLE);
		adaptOnSelection();
	}

	@Override
	protected void onselection() {
		setContent(new PlayListBorderPaneView());
	}
}
