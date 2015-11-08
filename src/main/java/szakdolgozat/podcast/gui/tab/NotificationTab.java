package szakdolgozat.podcast.gui.tab;

import szakdolgozat.podcast.gui.borderpane.NotificationBorderPaneView;

public class NotificationTab extends ApplicationTab {
	private static final String PODCASTLISTTAB_IMAGEPATH = "appbar.layer.down.png";
	private static final String PODCASTLISTTAB_TITLE = "Click here to manage your notifications!";

	public NotificationTab() {
		super(PODCASTLISTTAB_IMAGEPATH, PODCASTLISTTAB_TITLE);
		adaptOnSelection();
	}

	@Override
	protected void onselection() {
		setContent(new NotificationBorderPaneView());
	}
}
