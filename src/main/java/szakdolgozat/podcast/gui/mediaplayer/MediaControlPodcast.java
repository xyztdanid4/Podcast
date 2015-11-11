package szakdolgozat.podcast.gui.mediaplayer;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import szakdolgozat.podcast.gui.samples.ButtonSample;

public class MediaControlDani extends VBox {
	private static final String PLAYBUTTONURL = "appbar.control.play.png";
	private static final String NEXTBUTTONURL = "appbar.control.fastforward.variant.png";
	private static final String PREVBUTTONURL = "appbar.control.rewind.variant.png";
	private static final String PAUSEBUTTONURL = "appbar.control.pause.png";
	private static final String VOLUMELABE_TEXT = "Volume: ";

	public MediaControlDani() {
		HBox mediaButtonVolume = new HBox(10);
		HBox mediaSlider = new HBox(10);

		ButtonSample playButton = new ButtonSample("", "");
		decorateButton(playButton, PLAYBUTTONURL);
		ButtonSample pauseButton = new ButtonSample("", "");
		decorateButton(pauseButton, PAUSEBUTTONURL);
		ButtonSample prevButton = new ButtonSample("", "");
		decorateButton(prevButton, PREVBUTTONURL);
		ButtonSample nextButton = new ButtonSample("", "");
		decorateButton(nextButton, NEXTBUTTONURL);
		Label volumeLabel = new Label(VOLUMELABE_TEXT);
		decorateLabel(volumeLabel);
		Slider volumeSlider = new Slider();

		mediaButtonVolume.getChildren().addAll(prevButton, pauseButton, playButton, nextButton, volumeLabel,
				volumeSlider);
		mediaButtonVolume.setAlignment(Pos.CENTER);

		Slider timeSlider = new Slider();
		timeSlider.setPrefWidth(600);

		Text playedTime = new Text(new String("00:00:00"));
		playedTime.setFill(Color.web("#FFFFFF"));

		Text remainingTime = new Text(new String("00:00:00"));
		remainingTime.setFill(Color.web("#FFFFFF"));

		mediaSlider.getChildren().addAll(playedTime, timeSlider, remainingTime);
		mediaSlider.setAlignment(Pos.CENTER);
		mediaSlider.setPadding(new Insets(10, 0, 5, 0)); // milyen távol legyen
															// a többi elemtől

		getChildren().addAll(mediaSlider, mediaButtonVolume);
		decorateVBox(this);
	}

	private void decorateButton(Button button, final String imageUrl) {
		Rectangle rectangleButtonShape = new Rectangle(5, 5);
		rectangleButtonShape.setArcHeight(3);
		rectangleButtonShape.setArcWidth(3);
		button.setShape(rectangleButtonShape);
		ImageView buttonImageView = new ImageView(
				new Image(getClass().getClassLoader().getResource(imageUrl).toExternalForm()));
		button.setGraphic(buttonImageView);
		button.setMinSize(50, 50);
		button.setMaxSize(50, 50);
		button.setBackground(
				new Background(new BackgroundFill(Color.web("#808080"), new CornerRadii(3), Insets.EMPTY)));
		button.setBorder(new Border(new BorderStroke(Color.web("#006666"), BorderStrokeStyle.SOLID, new CornerRadii(3),
				new BorderWidths(3))));

		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.print("aaaa");
			}
		});

		setMouseEnteredEventButton(button);
		setMouseExitedEventButton(button);
	}

	private void decorateLabel(Label label) {
		label.setTextFill(Color.web("#FFFFFF"));
	}

	private void decorateVBox(VBox vbox) {
		vbox.setBackground(new Background(new BackgroundFill(Color.web("#191919"), new CornerRadii(0), Insets.EMPTY)));
		vbox.setBorder(new Border(new BorderStroke(Color.web("#006666"), BorderStrokeStyle.SOLID, new CornerRadii(0),
				new BorderWidths(3))));
		vbox.setPrefHeight(80);
	}

	private void setMouseEnteredEventButton(Button button) {
		button.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				button.setBackground(
						new Background(new BackgroundFill(Color.web("#191919"), new CornerRadii(10), Insets.EMPTY)));
			}
		});
	}

	private void setMouseExitedEventButton(Button button) {
		button.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				button.setBackground(
						new Background(new BackgroundFill(Color.web("#808080"), new CornerRadii(10), Insets.EMPTY)));
			}
		});
	}
}
