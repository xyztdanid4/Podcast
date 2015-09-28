package szakdolgozat.podcast.gui.samples;

import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class BorderPaneSample extends BorderPane {
	// private StatusBar statusBar;
	private TextField textField;
	private ListView listView;
	private ObservableList<String> rightlist;
	private HBox hbox;
	private Label label;
	private Button button;
	private Image image;
	private ImageView imageView;
	private TabPaneSample tabPaneSample;

	public BorderPaneSample() {

		// textField = new TextField();
		// setTop(textField);

		/*
		 * rightlist = FXCollections.observableArrayList();
		 * rightlist.add("dani"); rightlist.add("dani2");
		 * rightlist.add("dani3"); listView = new ListView();
		 * listView.setItems(rightlist); setLeft(listView);
		 */

		// tabPaneSample = new TabPaneSample();
		// setLeft(tabPaneSample);

		// imageView = new ImageView();
		// image = new Image(
		// "http://is5.mzstatic.com/image/pf/us/r30/Podcasts7/v4/f3/07/0c/f3070c7c-1ad7-8873-6a11-8f62d07d7456/mza_7184718992580057148.60x60-50.jpg");
		// imageView.setImage(image);

		// label = new Label("dani");
		// button = new Button("dani");
		// hbox = new HBox();
		// hbox.getChildren().addAll(label, button);
		// hbox.getChildren().add(imageView);
		// setRight(hbox);

		// statusBar = new StatusBar();
		// statusBar.setText("STATUSBAR");
		// setBottom(statusBar);
	}
}
