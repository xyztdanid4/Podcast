package szakdolgozat.podcast.gui.samples;

import java.net.URL;

import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TabSample extends Tab {
	private TooltipSample tabSampleTooltip;

	public TabSample() {
		setClosable(false);
	}

	public void makeTab(TabSample tabSample, final String imagePath,
			final String tabTitle) {
		URL imageUrl = getClass().getClassLoader().getResource(imagePath);
		Image image = new Image(imageUrl.toExternalForm());
		ImageView icon = new ImageView(image);
		tabSample.setGraphic(icon);
		tabSampleTooltip = new TooltipSample(tabTitle);
		setTooltip(tabSampleTooltip);
	}
}
