package szakdolgozat.podcast.gui.tab;

import szakdolgozat.podcast.gui.borderpane.DownloadBorderPane;
import szakdolgozat.podcast.gui.samples.TabSample;

public class DownloadTab extends TabSample {
	private DownloadBorderPane downloadBorderPane;
	private final String DOWNLOADTAB_IMAGEPATH = "appbar.disk.download.png";
	private final String DOWNLOADTAB_TITLE = "Click here for downloads!";

	public DownloadTab() {
		downloadBorderPane = new DownloadBorderPane();
		super.makeTab((TabSample) this, DOWNLOADTAB_IMAGEPATH,
				DOWNLOADTAB_TITLE);
		setContent(downloadBorderPane);
	}
}
