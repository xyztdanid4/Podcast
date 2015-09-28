package szakdolgozat.podcast.gui.tab;

import szakdolgozat.podcast.gui.samples.TabSample;

public class PlayListTab extends TabSample {
	private final String PLAYLISTTAB_IMAGEPATH = "appbar.control.play.png";
	private final String PLAYLISTTAB_TITLE = "Click here for your playlists!";

	public PlayListTab() {

		super.makeTab((TabSample) this, PLAYLISTTAB_IMAGEPATH,
				PLAYLISTTAB_TITLE);

	}
}
