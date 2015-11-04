package szakdolgozat.podcast.gui.decorator;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import szakdolgozat.podcast.gui.samples.ButtonSample;
import szakdolgozat.podcast.gui.samples.LabelSample;
import szakdolgozat.podcast.gui.samples.PasswordFieldSample;
import szakdolgozat.podcast.gui.samples.TextFieldSample;

public class Decorator {
	// border - radius
	public static final int CORNERRADIUS = 10;
	public static final int BORDERSIZE = 3;
	public static final int BORDERPANEBORDERRADIUS = 0;
	// colors
	public static final String TEXTCOLOR = "#FFFFFF";
	public static final String BORDERCOLOR = "#006666";
	public static final String ITEMBACKGROUNDCOLOR = "#808080";
	public static final String BACKGROUNDCOLOR = "#191919";
	// textsize
	public static final int BIGTEXTSIZE = 16;
	public static final int SMALLTEXTSIZE = 12;
	// padding
	public static final int PADDING = 20;
	public static final int HBOXPADDING = 10;
	public static final int VBOXPADDING = 10;
	// listview
	public static final int LISTVIEWINSETS = 0;
	// rectangle
	public static final int RECTANGLEARCHWIDHT = 10;
	public static final int RECTANGLEARCHHEIGHT = 10;
	public static final int SMALLRECTANGLEHEIGHT = 40;
	public static final int SMALLRECTANGLEWIDTH = 40;
	public static final int BIGRECTANGLEHEIGHT = 90;
	public static final int BIGRECTANGLEWIDTH = 90;

	public Decorator() {

	}

	public static Pane decorateFactory(Pane pane) {
		pane.setBackground(new Background(
				new BackgroundFill(Color.web(BACKGROUNDCOLOR), new CornerRadii(BORDERPANEBORDERRADIUS), Insets.EMPTY)));
		pane.setBorder(new Border(new BorderStroke(Color.web(BORDERCOLOR), BorderStrokeStyle.SOLID,
				new CornerRadii(BORDERPANEBORDERRADIUS), new BorderWidths(BORDERSIZE, BORDERSIZE, 0, BORDERSIZE))));
		return pane;
	}

	public static Text decorateTextFactory(Text text, final int size) {
		text.setFill(Color.web(TEXTCOLOR));
		text.setFont(Font.font("Arial", FontWeight.BOLD, size));
		return text;
	}

	public static HBox decorateHBoxFactory(HBox hbox, final int prefwidth, final int prefheight, final int maxwidht,
			final int maxheight) {
		hbox.setPrefSize(prefwidth, prefheight);
		hbox.setMaxSize(maxwidht, maxheight);
		hbox.setBackground(new Background(
				new BackgroundFill(Color.web(ITEMBACKGROUNDCOLOR), new CornerRadii(CORNERRADIUS), Insets.EMPTY)));
		hbox.setBorder(new Border(new BorderStroke(Color.web(BORDERCOLOR), BorderStrokeStyle.SOLID,
				new CornerRadii(CORNERRADIUS), new BorderWidths(BORDERSIZE))));
		hbox.setAlignment(Pos.CENTER_LEFT);
		return hbox;
	}

	public static HBox decorateHBoxFactory(HBox hbox) {
		hbox.setBackground(new Background(
				new BackgroundFill(Color.web(ITEMBACKGROUNDCOLOR), new CornerRadii(CORNERRADIUS), Insets.EMPTY)));
		hbox.setBorder(new Border(new BorderStroke(Color.web(BORDERCOLOR), BorderStrokeStyle.SOLID,
				new CornerRadii(CORNERRADIUS), new BorderWidths(BORDERSIZE))));
		hbox.setAlignment(Pos.CENTER_LEFT);
		setMouseEnteredEventHBox(hbox);
		setMouseExitedEventHBox(hbox);
		return hbox;
	}

	public static ListView<HBox> decorateListViewFactory(ListView<HBox> listView, final int width, final int height) {
		listView.setPrefSize(width, height);
		listView.setMaxSize(width, height);
		listView.setBorder(new Border(new BorderStroke(Color.web(BORDERCOLOR), BorderStrokeStyle.SOLID,
				new CornerRadii(CORNERRADIUS), new BorderWidths(BORDERSIZE))));
		listView.setBackground(new Background(
				new BackgroundFill(Color.web(BACKGROUNDCOLOR), new CornerRadii(CORNERRADIUS), Insets.EMPTY)));
		listView.setPadding(new Insets(LISTVIEWINSETS));
		return listView;
	}

	public static ListView<HBox> decorateListViewFactory(ListView<HBox> listView) {
		listView.setBorder(new Border(new BorderStroke(Color.web(BORDERCOLOR), BorderStrokeStyle.SOLID,
				new CornerRadii(CORNERRADIUS), new BorderWidths(BORDERSIZE))));
		listView.setBackground(new Background(
				new BackgroundFill(Color.web(BACKGROUNDCOLOR), new CornerRadii(CORNERRADIUS), Insets.EMPTY)));
		listView.setPadding(new Insets(LISTVIEWINSETS));
		return listView;
	}

	public static Rectangle decorateRectangleFactory(Rectangle rectangle, final int height, final int width,
			String imageURL) {
		rectangle.setArcHeight(RECTANGLEARCHHEIGHT);
		rectangle.setArcWidth(RECTANGLEARCHWIDHT);
		rectangle.setHeight(height);
		rectangle.setWidth(width);
		rectangle.setFill(new ImagePattern(new Image(imageURL)));
		return rectangle;
	}

	public static VBox decorateHelperVBoxFactory(VBox vBox) {
		vBox.setAlignment(Pos.CENTER_LEFT);
		return vBox;
	}

	public static VBox decorateVBoxFactory(VBox vBox) {
		vBox.setAlignment(Pos.CENTER_LEFT);
		return vBox;
	}

	public static ImageView decorateImageViewFactory(ImageView imageView, final int height, final int width) {
		imageView.setFitWidth(width);
		imageView.setFitHeight(height);
		return imageView;
	}

	private static void setMouseEnteredEventHBox(HBox itemHbox) {
		itemHbox.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				itemHbox.setBackground(new Background(
						new BackgroundFill(Color.web(BACKGROUNDCOLOR), new CornerRadii(CORNERRADIUS), Insets.EMPTY)));
			}
		});
	}

	private static void setMouseExitedEventHBox(HBox itemHbox) {
		itemHbox.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				itemHbox.setBackground(new Background(new BackgroundFill(Color.web(ITEMBACKGROUNDCOLOR),
						new CornerRadii(CORNERRADIUS), Insets.EMPTY)));
			}
		});
	}

	private static void setMouseEnteredEventButton(Button button) {
		button.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				button.setBackground(new Background(
						new BackgroundFill(Color.web(BACKGROUNDCOLOR), new CornerRadii(CORNERRADIUS), Insets.EMPTY)));
			}
		});
	}

	private static void setMouseExitedEventButton(Button button) {
		button.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				button.setBackground(new Background(new BackgroundFill(Color.web(ITEMBACKGROUNDCOLOR),
						new CornerRadii(CORNERRADIUS), Insets.EMPTY)));
			}
		});
	}

	public static Button decorateButtonFactory(Button button) {
		button.setBorder(new Border(new BorderStroke(Color.web(BORDERCOLOR), BorderStrokeStyle.SOLID,
				new CornerRadii(CORNERRADIUS), new BorderWidths(BORDERSIZE))));
		button.setBackground(new Background(
				new BackgroundFill(Color.web(ITEMBACKGROUNDCOLOR), new CornerRadii(CORNERRADIUS), Insets.EMPTY)));
		button.setTextFill(Color.web(TEXTCOLOR));
		setMouseEnteredEventButton(button);
		setMouseExitedEventButton(button);
		return button;
	}

	public static ButtonSample decorateButtonSampleFactory(ButtonSample button) {
		button.setBorder(new Border(new BorderStroke(Color.web(BORDERCOLOR), BorderStrokeStyle.SOLID,
				new CornerRadii(CORNERRADIUS), new BorderWidths(BORDERSIZE))));
		button.setBackground(new Background(
				new BackgroundFill(Color.web(ITEMBACKGROUNDCOLOR), new CornerRadii(CORNERRADIUS), Insets.EMPTY)));
		button.setTextFill(Color.web(TEXTCOLOR));
		setMouseEnteredEventButton(button);
		setMouseExitedEventButton(button);
		return button;
	}

	public static Label decorateLabelFactory(Label label) {
		label.setTextFill(Color.web(TEXTCOLOR));
		return label;
	}

	public static LabelSample decorateLabelSampleFactory(LabelSample label) {
		label.setTextFill(Color.web(TEXTCOLOR));
		return label;
	}

	public static TextField decorateTextFieldFactory(TextField textField) {
		textField.setBorder(new Border(new BorderStroke(Color.web(BORDERCOLOR), BorderStrokeStyle.SOLID,
				new CornerRadii(CORNERRADIUS), new BorderWidths(BORDERSIZE))));
		textField.setBackground(new Background(
				new BackgroundFill(Color.web(ITEMBACKGROUNDCOLOR), new CornerRadii(CORNERRADIUS), Insets.EMPTY)));
		textField.setStyle("-fx-text-inner-color: white;");
		return textField;
	}

	public static PasswordField decoratePasswordFieldFactory(PasswordField textField) {
		textField.setBorder(new Border(new BorderStroke(Color.web(BORDERCOLOR), BorderStrokeStyle.SOLID,
				new CornerRadii(CORNERRADIUS), new BorderWidths(BORDERSIZE))));
		textField.setBackground(new Background(
				new BackgroundFill(Color.web(ITEMBACKGROUNDCOLOR), new CornerRadii(CORNERRADIUS), Insets.EMPTY)));
		textField.setStyle("-fx-text-inner-color: white;");
		return textField;
	}

	public static PasswordFieldSample decoratePasswordFieldSampleFactory(PasswordFieldSample textField) {
		textField.setBorder(new Border(new BorderStroke(Color.web(BORDERCOLOR), BorderStrokeStyle.SOLID,
				new CornerRadii(CORNERRADIUS), new BorderWidths(BORDERSIZE))));
		textField.setBackground(new Background(
				new BackgroundFill(Color.web(ITEMBACKGROUNDCOLOR), new CornerRadii(CORNERRADIUS), Insets.EMPTY)));
		textField.setStyle("-fx-text-inner-color: white;");
		return textField;
	}

	public static TextFieldSample decorateTextFieldSampleFactory(TextFieldSample textFieldSample, final int width,
			final int height) {
		textFieldSample.setBorder(new Border(new BorderStroke(Color.web(BORDERCOLOR), BorderStrokeStyle.SOLID,
				new CornerRadii(CORNERRADIUS), new BorderWidths(BORDERSIZE))));
		textFieldSample.setBackground(new Background(
				new BackgroundFill(Color.web(ITEMBACKGROUNDCOLOR), new CornerRadii(CORNERRADIUS), Insets.EMPTY)));
		textFieldSample.setStyle("-fx-text-inner-color: white;");
		textFieldSample.setMaxSize(width, height);
		textFieldSample.setPrefSize(width, height);
		return textFieldSample;
	}

	public static TextFieldSample decorateTextFieldSampleFactory(TextFieldSample textFieldSample) {
		textFieldSample.setBorder(new Border(new BorderStroke(Color.web(BORDERCOLOR), BorderStrokeStyle.SOLID,
				new CornerRadii(CORNERRADIUS), new BorderWidths(BORDERSIZE))));
		textFieldSample.setBackground(new Background(
				new BackgroundFill(Color.web(ITEMBACKGROUNDCOLOR), new CornerRadii(CORNERRADIUS), Insets.EMPTY)));
		textFieldSample.setStyle("-fx-text-inner-color: white;");

		return textFieldSample;
	}

}
