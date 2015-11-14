package szakdolgozat.podcast.gui.borderpane;

import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import szakdolgozat.podcast.builder.CheckBoxBuilder;
import szakdolgozat.podcast.builder.ListViewBuilder;
import szakdolgozat.podcast.builder.VBoxBuilder;
import szakdolgozat.podcast.controller.NotificationBorderPaneController;
import szakdolgozat.podcast.gui.decorator.Decorator;
import szakdolgozat.podcast.gui.decorator.NotificationBPDecorator;

public class NotificationBorderPaneView extends BorderPane {
	private static final String SHOW_SUBSCRIPTIONS = "Show subscriptions";
	private static final String SHOW_UNSUBSCRITPIONS = "Show unsubscritpions";
	private static final String SHOW_NEW_EPISODES = "Show new episodes";
	private static final String NOTIFICAITON = "Notifications";

	public NotificationBorderPaneView() {
		final ObservableList<HBox> filterableList = FXCollections.observableArrayList();
		// final CheckBox newEpisodeCB = new CheckBox("Show new episodes");
		// final CheckBox unsubscribeCB = new CheckBox("Show unsubscritpions");
		// final CheckBox subscribeCB = new CheckBox("Show subscriptions");
		//-.-off
		final CheckBox newEpisodeCB =  CheckBoxBuilder.create()
														.text(SHOW_NEW_EPISODES)
														.build();
		final CheckBox unsubscribeCB = CheckBoxBuilder.create()
														.text(SHOW_UNSUBSCRITPIONS)
														.build();
		final CheckBox subscribeCB = CheckBoxBuilder.create()
													.text(SHOW_SUBSCRIPTIONS)
													.build();
		setDefaultCheckBoxValues(newEpisodeCB, unsubscribeCB, subscribeCB);
		
		final ListView<HBox> listView = ListViewBuilder.create()
														.size(NotificationBPDecorator.LISTWIDTH, NotificationBPDecorator.LISTHEIGHT)
														.build();
		
		buildFilteredList(filterableList, newEpisodeCB, unsubscribeCB, subscribeCB, listView);
		
		setLeft(VBoxBuilder.create()
				.bigText(NOTIFICAITON)
				.noListView(listView)
				.build());
		
		//-.-on
		setCheckBoxActions(filterableList, newEpisodeCB, unsubscribeCB, subscribeCB, listView);
		//-.-off
		final VBox optionHBox = VBoxBuilder.create()
											.checkBox(newEpisodeCB)
											.checkBox(unsubscribeCB)
											.checkBox(subscribeCB)
											.build();
		//-.-on
		NotificationBPDecorator.decorateFactory(this);
		setCenter(optionHBox);
		setMargin(optionHBox);
		setPadding();
	}

	/**
	 * @param filterableList
	 * @param newEpisodeCB
	 * @param unsubscribeCB
	 * @param subscribeCB
	 * @param listView
	 */
	private void setCheckBoxActions(final ObservableList<HBox> filterableList, final CheckBox newEpisodeCB,
			final CheckBox unsubscribeCB, final CheckBox subscribeCB, final ListView<HBox> listView) {
		subscribeCB.selectedProperty().addListener((InvalidationListener) observable -> {
			if (subscribeCB.isSelected()) {
				filterableList.addAll(NotificationBorderPaneController.getInstance().getSubscribeContainer());
				listView.setItems(filterableList);
			} else {
				filterableList.removeAll(NotificationBorderPaneController.getInstance().getSubscribeContainer());
				listView.setItems(filterableList);
			}
		});
		unsubscribeCB.selectedProperty().addListener((InvalidationListener) observable -> {
			if (unsubscribeCB.isSelected()) {
				filterableList.addAll(NotificationBorderPaneController.getInstance().getUnsubscribeContainer());
				listView.setItems(filterableList);
			} else {
				filterableList.removeAll(NotificationBorderPaneController.getInstance().getUnsubscribeContainer());
				listView.setItems(filterableList);
			}
		});
		newEpisodeCB.selectedProperty().addListener((InvalidationListener) observable -> {
			if (newEpisodeCB.isSelected()) {
				filterableList.addAll(NotificationBorderPaneController.getInstance().getNewEpisodeContainer());
				listView.setItems(filterableList);
			} else {
				filterableList.removeAll(NotificationBorderPaneController.getInstance().getNewEpisodeContainer());
				listView.setItems(filterableList);
			}
		});
	}

	/**
	 * @param newEpisodeCB
	 * @param unsubscribeCB
	 * @param subscribeCB
	 */
	private void setDefaultCheckBoxValues(final CheckBox newEpisodeCB, final CheckBox unsubscribeCB,
			final CheckBox subscribeCB) {
		newEpisodeCB.setSelected(true);
		unsubscribeCB.setSelected(true);
		subscribeCB.setSelected(true);
	}

	/**
	 * @param optionHBox
	 */
	private void setMargin(final VBox optionHBox) {
		setMargin(optionHBox, new Insets(20));
	}

	/**
	 * @param filterableList
	 * @param newEpisodeCB
	 * @param unsubscribeCB
	 * @param subscribeCB
	 * @param listView
	 */
	private void buildFilteredList(final ObservableList<HBox> filterableList, final CheckBox newEpisodeCB,
			final CheckBox unsubscribeCB, final CheckBox subscribeCB, final ListView<HBox> listView) {
		if (subscribeCB.isSelected()) {
			filterableList.addAll(NotificationBorderPaneController.getInstance().getSubscribeContainer());
			listView.setItems(filterableList);
		}
		if (unsubscribeCB.isSelected()) {
			filterableList.addAll(NotificationBorderPaneController.getInstance().getUnsubscribeContainer());
			listView.setItems(filterableList);
		}
		if (newEpisodeCB.isSelected()) {
			filterableList.addAll(NotificationBorderPaneController.getInstance().getNewEpisodeContainer());
			listView.setItems(filterableList);
		}
	}

	private void setPadding() {
		setPadding(new Insets(Decorator.PADDING, Decorator.PADDING, Decorator.PADDING, Decorator.PADDING));
	}
}
