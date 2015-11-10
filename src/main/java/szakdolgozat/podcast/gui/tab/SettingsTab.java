package szakdolgozat.podcast.gui.tab;

import szakdolgozat.podcast.gui.borderpane.SettingsBorderPane;

public class SettingsTab extends ApplicationTab {
	private static final String SETTINGSTAB_IMAGEPATH = "appbar.settings.png";
	private static final String SETTINGSTAB_TITLE = "Click here for downloads!";
	private static SettingsTab instance = null;

	public SettingsTab() {
		super(SETTINGSTAB_IMAGEPATH, SETTINGSTAB_TITLE);
		setContent(new SettingsBorderPane());
	}

	@Override
	protected void onselection() {
		setContent(new SettingsBorderPane());
	}

	public static SettingsTab getInstance() {
		if (instance == null) {
			instance = new SettingsTab();
		}
		return instance;
	}
}
