package szakdolgozat.podcast.gui.tab;

import szakdolgozat.podcast.gui.borderpane.PodcastBorderPaneView;

public class PodcastListTab extends ApplicationTab {
	private static final String PODCASTLISTTAB_IMAGEPATH = "appbar.list.png";
	private static final String PODCASTLISTTAB_TITLE = "Click here to manage your podcasts!";

	public PodcastListTab() {
		super(PODCASTLISTTAB_IMAGEPATH, PODCASTLISTTAB_TITLE);
		adaptOnSelection();
	}

	@Override
	protected void onselection() {
		setContent(new PodcastBorderPaneView());
	}

}
