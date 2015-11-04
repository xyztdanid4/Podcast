package szakdolgozat.podcast.gui.tab;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class ApplicationTab extends Tab {
	public ApplicationTab(final String imagePath, final String tabTitle) {
		setGraphic(new ImageView(new Image(getClass().getClassLoader().getResource(imagePath).toExternalForm())));
		setClosable(false);
	}

	protected void adaptOnSelection() {
		selectedProperty().addListener(new InvalidationListener() {
			@Override
			public void invalidated(Observable observable) {
				if (isSelected()) {
					onselection();
				}
			}
		});
	}

	protected abstract void onselection();

}
