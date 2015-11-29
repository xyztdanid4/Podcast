package szakdolgozat.podcast.gui.tab;

import szakdolgozat.podcast.gui.borderpane.SettingsBorderPaneView;

/**
 * The Class SettingsTab.
 * 
 * * @author Daniel Toth
 * 
 * @version 0.0.1
 * @since 0.0.1
 */
public class SettingsTab extends ApplicationTab {

	/** The Constant SETTINGSTAB_IMAGEPATH. */
	private static final String SETTINGSTAB_IMAGEPATH = "appbar.settings.png";

	/** The Constant SETTINGSTAB_TITLE. */
	private static final String SETTINGSTAB_TITLE = "Click here for downloads!";

	/** The instance. */
	private static SettingsTab instance = null;

	/**
	 * Instantiates a new settings tab.
	 */
	public SettingsTab() {
		super(SETTINGSTAB_IMAGEPATH, SETTINGSTAB_TITLE);
		setContent(new SettingsBorderPaneView());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see szakdolgozat.podcast.gui.tab.ApplicationTab#onselection()
	 */
	@Override
	protected void onselection() {
		setContent(new SettingsBorderPaneView());
	}

	/**
	 * Gets the single instance of SettingsTab.
	 *
	 * @return single instance of SettingsTab
	 */
	public static SettingsTab getInstance() {
		if (instance == null) {
			instance = new SettingsTab();
		}
		return instance;
	}
}
