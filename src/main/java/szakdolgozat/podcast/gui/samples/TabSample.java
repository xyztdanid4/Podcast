package szakdolgozat.podcast.gui.samples;

import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TabSample extends Tab {
	public TabSample(final String imagePath, final String tabTitle) {
		setGraphic(new ImageView(new Image(getClass().getClassLoader().getResource(imagePath).toExternalForm())));
		setClosable(false);
	}
	/*
	 * public void makeTab(TabSample tabSample, final String imagePath, final
	 * String tabTitle) { tabSample.setGraphic( new ImageView(new
	 * Image(getClass().getClassLoader().getResource(imagePath).toExternalForm()
	 * ))); setTooltip(new Tooltip(tabTitle)); }
	 */

}
