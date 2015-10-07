package szakdolgozat.podcast.gui.borderpane;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import szakdolgozat.podcast.tabpane.ApplicationTabPane;

public class MainBorderPane extends BorderPane {
	private ApplicationTabPane tabPaneSample;
	private Label rightLabel;

	public MainBorderPane() {
		tabPaneSample = new ApplicationTabPane();
		setCenter(tabPaneSample);

		rightLabel = new Label("RIGHTSIDE");
		setRight(rightLabel);

		VBox mediaController = new VBox(10);
		HBox mediaButtonVolume = new HBox(10);
		HBox mediaSlider = new HBox(10);

		mediaButtonVolume.getChildren().addAll(new Button(new String("prev")), new Button(new String("play")),
				new Button("next"), new Label("Volume: "), new Slider());
		mediaButtonVolume.setAlignment(Pos.CENTER);

		Slider slider = new Slider();
		slider.setPrefWidth(600);
		Text playedTime = new Text(new String("00:00:00"));
		playedTime.setFill(Color.web("#FFFFFF"));
		Text remainingTime = new Text(new String("00:00:00"));
		remainingTime.setFill(Color.web("#FFFFFF"));
		mediaSlider.getChildren().addAll(playedTime, slider, remainingTime);
		mediaSlider.setAlignment(Pos.CENTER);
		mediaSlider.setPadding(new Insets(10, 0, 5, 0)); // milyen távol legyen
															// a többi elemtől

		mediaController.getChildren().addAll(mediaSlider, mediaButtonVolume);

		mediaController.setBackground(
				new Background(new BackgroundFill(Color.web("#191919"), new CornerRadii(0), Insets.EMPTY)));
		mediaController.setBorder(new Border(new BorderStroke(Color.web("#006666"), BorderStrokeStyle.SOLID,
				new CornerRadii(0), new BorderWidths(3))));
		mediaController.setPrefHeight(80);

		setBottom(mediaController);
	}
}
