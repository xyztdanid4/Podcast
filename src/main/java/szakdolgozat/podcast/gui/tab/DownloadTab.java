package szakdolgozat.podcast.gui.tab;

import szakdolgozat.podcast.gui.borderpane.DownloadBorderPane;

public class DownloadTab extends ApplicationTab {
	private static final String DOWNLOADTAB_IMAGEPATH = "appbar.disk.download.png";
	private static final String DOWNLOADTAB_TITLE = "Click here for downloads!";

	public DownloadTab() {
		super(DOWNLOADTAB_IMAGEPATH, DOWNLOADTAB_TITLE);
		setContent(new DownloadBorderPane());
	}
}
