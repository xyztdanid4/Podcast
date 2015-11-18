package szakdolgozat.podcast.gui.tab;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import szakdolgozat.podcast.controller.NotificationBorderPaneController;
import szakdolgozat.podcast.gui.borderpane.NotificationBorderPaneView;

/**
 * The Class NotificationTab.
 * 
 * * @author Daniel Toth
 * 
 * @version 0.0.1
 * @since 0.0.1
 */
public class NotificationTab extends ApplicationTab {

	/** The Constant PODCASTLISTTAB_IMAGEPATH. */
	private static final String PODCASTLISTTAB_IMAGEPATH = "appbar.page.0.png";

	/** The Constant PODCASTLISTTAB_TITLE. */
	private static final String PODCASTLISTTAB_TITLE = "Click here to manage your notifications!";

	/** The counter. */
	private static int counter;

	/**
	 * Instantiates a new notification tab.
	 */
	public NotificationTab() {
		super(PODCASTLISTTAB_IMAGEPATH, PODCASTLISTTAB_TITLE);
		adaptOnSelection();

		NotificationBorderPaneController.getInstance().getNotificationContainer()
				.addListener((InvalidationListener) observable -> {
					counter++;
					Platform.runLater(() -> {
						if (counter < 10) {
							setGraphic(new ImageView(new Image(getClass().getClassLoader()
									.getResource("appbar.page." + counter + ".png").toExternalForm())));
						} else {
							setGraphic(new ImageView(new Image(
									getClass().getClassLoader().getResource("appbar.page.new.png").toExternalForm())));
						}
					});
				});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see szakdolgozat.podcast.gui.tab.ApplicationTab#adaptOnSelection()
	 */
	@Override
	protected void adaptOnSelection() {
		selectedProperty().addListener((InvalidationListener) observable -> {
			if (isSelected()) {
				onselection();

			}
		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see szakdolgozat.podcast.gui.tab.ApplicationTab#onselection()
	 */
	@Override
	protected void onselection() {
		setContent(new NotificationBorderPaneView());
		final ImageView imageView = new ImageView(
				new Image(getClass().getClassLoader().getResource("appbar.page.0.png").toExternalForm()));
		imageView.setRotate(90);
		Platform.runLater(() -> setGraphic(imageView));
		counter = 0;
	}
}
