package szakdolgozat.podcast.gui.tab;

import javafx.beans.InvalidationListener;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * The Class ApplicationTab.
 * 
 * @author Daniel Toth
 * @version 0.0.1
 * @since 0.0.1
 */
public abstract class ApplicationTab extends Tab {

	/**
	 * Instantiates a new application tab.
	 *
	 * @param imagePath
	 *            the image path
	 * @param tabTitle
	 *            the tab title
	 */
	public ApplicationTab(final String imagePath, final String tabTitle) {
		setGraphic(new ImageView(new Image(getClass().getClassLoader().getResource(imagePath).toExternalForm())));
		setClosable(false);
	}

	/**
	 * Adapt on selection.
	 */
	protected void adaptOnSelection() {
		selectedProperty().addListener((InvalidationListener) observable -> {
			if (isSelected()) {
				onselection();
			}
		});
	}

	/**
	 * Onselection.
	 */
	protected abstract void onselection();

}
