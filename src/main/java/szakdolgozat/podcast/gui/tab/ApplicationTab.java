package szakdolgozat.podcast.gui.tab;

import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ApplicationTab extends Tab {
	public ApplicationTab(final String imagePath, final String tabTitle) {
		setGraphic(new ImageView(new Image(getClass().getClassLoader().getResource(imagePath).toExternalForm())));
		setClosable(false);
	}
	
}
