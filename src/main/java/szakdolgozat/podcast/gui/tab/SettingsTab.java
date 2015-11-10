package szakdolgozat.podcast.gui.tab;

import szakdolgozat.podcast.gui.borderpane.DownloadBorderPane;

public class SettingsTab extends ApplicationTab {
	private static final String SETTINGSTAB_IMAGEPATH = "appbar.disk.download.png";
	private static final String DOWNLOADTAB_TITLE = "Click here for downloads!";

	public SettingsTab() {
		super(SETTINGSTAB_IMAGEPATH, DOWNLOADTAB_TITLE);
		setContent(new DownloadBorderPane());
	}

	@Override
	protected void onselection() {

	}
}
