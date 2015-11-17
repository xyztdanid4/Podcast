package szakdolgozat.podcast.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.HBox;
import szakdolgozat.podcast.gui.borderpane.NotificationBorderPaneView;

/**
 * The NotificationBorderPaneController class
 * 
 * @author Daniel Toth
 * @version 0.0.1
 * @since 0.0.1
 *
 *        This class is responsible for the {@link NotificationBorderPaneView}
 *        actions. The main functions for this class are that contains
 *        information about refreshing. There are four containers:
 *        {@link NotificationBorderPaneController#notificationContainer} is
 *        getting items from all kind of actions, for example if we subscribe,
 *        unsubscribe, or notice if we got new episode.
 *        {@link NotificationBorderPaneController#newEpisodeContainer} is
 *        getting information when we got new episode.
 *        {@link NotificationBorderPaneController#subscribeContainer} is getting
 *        information when we subscribe.
 *        {@link NotificationBorderPaneController#unsubscribeContainer} is
 *        getting information when we unsubscribe. The class represents data to
 *        {@link NotificationBorderPaneView}. The class is singleton cause we
 *        need only one instance, and we need to reach that class from another
 *        controller classes.
 * 
 * @see NotificationBorderPaneView
 * 
 */
public class NotificationBorderPaneController {
	/**
	 * instance, we need only one of that. Part of the singleton class model.
	 */
	private static NotificationBorderPaneController instance = null;
	/**
	 * notificationContainer, is a observableList which contains HBox. It is the
	 * source for {@link NotificationBorderPaneView} which ha a local listView
	 * instance in the constructor.
	 */
	private static ObservableList<HBox> notificationContainer;
	/**
	 * newEpisodeContainer, is a observableList which contains HBox. It is the
	 * source for {@link NotificationBorderPaneView} which ha a local listView
	 * instance in the constructor.
	 */
	private static ObservableList<HBox> newEpisodeContainer;
	/**
	 * subscribeContainer, is a observableList which contains HBox. It is the
	 * source for {@link NotificationBorderPaneView} which ha a local listView
	 * instance in the constructor.
	 */
	private static ObservableList<HBox> subscribeContainer;
	/**
	 * unsubscribeContainer, is a observableList which contains HBox. It is the
	 * source for {@link NotificationBorderPaneView} which ha a local listView
	 * instance in the constructor.
	 */
	private static ObservableList<HBox> unsubscribeContainer;

	/**
	 * The constructor main function is that make the instances of the four
	 * container.
	 */
	private NotificationBorderPaneController() {
		notificationContainer = FXCollections.observableArrayList();
		newEpisodeContainer = FXCollections.observableArrayList();
		subscribeContainer = FXCollections.observableArrayList();
		unsubscribeContainer = FXCollections.observableArrayList();
	}

	/**
	 * 
	 * @return instance of this class object.
	 */
	public static NotificationBorderPaneController getInstance() {
		if (instance == null) {
			instance = new NotificationBorderPaneController();
		}
		return instance;
	}

	/**
	 * 
	 * @return notificationContainer.
	 */
	public ObservableList<HBox> getNotificationContainer() {
		return notificationContainer;
	}

	/**
	 * 
	 * @return newEpisodeContainer.
	 */
	public ObservableList<HBox> getNewEpisodeContainer() {
		return newEpisodeContainer;
	}

	/**
	 * 
	 * @return subscribeContainer.
	 */
	public ObservableList<HBox> getSubscribeContainer() {
		return subscribeContainer;
	}

	/**
	 * 
	 * @return unsubscribeContainer.
	 */
	public ObservableList<HBox> getUnsubscribeContainer() {
		return unsubscribeContainer;
	}

}
