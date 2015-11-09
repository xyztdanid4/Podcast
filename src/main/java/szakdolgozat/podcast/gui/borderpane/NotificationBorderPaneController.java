package szakdolgozat.podcast.gui.borderpane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.HBox;

public class NotificationBorderPaneController {
	private static NotificationBorderPaneController instance = null;
	private static ObservableList<HBox> notificationContainer;

	private NotificationBorderPaneController() {
		notificationContainer = FXCollections.observableArrayList();

	}

	public static NotificationBorderPaneController getInstance() {
		if (instance == null) {
			instance = new NotificationBorderPaneController();
		}
		return instance;
	}

	public ObservableList<HBox> getNotificationContainer() {
		return notificationContainer;
	}
}
