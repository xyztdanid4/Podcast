package szakdolgozat.podcast.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.HBox;

public class NotificationBorderPaneController {
	private static NotificationBorderPaneController instance = null;
	private static ObservableList<HBox> notificationContainer;
	private static ObservableList<HBox> newEpisodeContainer;
	private static ObservableList<HBox> subscribeContainer;
	private static ObservableList<HBox> unsubscribeContainer;

	private NotificationBorderPaneController() {
		notificationContainer = FXCollections.observableArrayList();
		newEpisodeContainer = FXCollections.observableArrayList();
		subscribeContainer = FXCollections.observableArrayList();
		unsubscribeContainer = FXCollections.observableArrayList();
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

	public ObservableList<HBox> getNewEpisodeContainer() {
		return newEpisodeContainer;
	}

	public ObservableList<HBox> getSubscribeContainer() {
		return subscribeContainer;
	}

	public ObservableList<HBox> getUnsubscribeContainer() {
		return unsubscribeContainer;
	}

}
