package szakdolgozat.podcast.gui.borderpane;

import javafx.geometry.Insets;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import szakdolgozat.podcast.builder.ListViewBuilder;
import szakdolgozat.podcast.builder.TextBuilder;
import szakdolgozat.podcast.builder.VBoxBuilder;
import szakdolgozat.podcast.gui.decorator.Decorator;
import szakdolgozat.podcast.gui.decorator.NotificationBPDecorator;

public class NotificationBorderPaneView extends BorderPane {
	// private static

	public NotificationBorderPaneView() {
		setPadding();
		//-.-off
		NotificationBPDecorator.decorateFactory(this);
		final Text text = TextBuilder.create()
									.bigText("Notifications")
									.build();
		
		final ListView<HBox> listView = ListViewBuilder.create()
				.items(NotificationBorderPaneController.getInstance().getNotificationContainer())
				.size(600, 600)
				.build();
		
		final VBox vbox = VBoxBuilder.create()
									.bigText("Notifications")
									.noListView(listView)
									.build(); 
		setLeft(vbox);
		//-.-on
	}

	private void setPadding() {
		setPadding(new Insets(Decorator.PADDING, Decorator.PADDING, Decorator.PADDING, Decorator.PADDING));
	}
}
