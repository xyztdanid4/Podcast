package szakdolgozat.podcast.gui.decorator;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
import szakdolgozat.podcast.gui.borderpane.SerialHBox;

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

	public static final int HELPERVBOXPADDING = 5;

	public Decorator() {

	}

	public static Pane decorateFactory(final Pane pane) {
		pane.setBackground(new Background(
				new BackgroundFill(Color.web(BACKGROUNDCOLOR), new CornerRadii(BORDERPANEBORDERRADIUS), Insets.EMPTY)));
		pane.setBorder(new Border(new BorderStroke(Color.web(BORDERCOLOR), BorderStrokeStyle.SOLID,
				new CornerRadii(BORDERPANEBORDERRADIUS),
				new BorderWidths(BORDERSIZE, BORDERSIZE, BORDERSIZE, BORDERSIZE))));
		return pane;
	}

	public static CheckBox decorateCheckBoxFactory(final CheckBox checkBox) {
		checkBox.setTextFill(Color.web(TEXTCOLOR));
		checkBox.setFont(Font.font("Arial", FontWeight.BOLD, SMALLTEXTSIZE));
		return checkBox;
	}

	public static Text decorateTextFactory(final Text text, final int size) {
		text.setFill(Color.web(TEXTCOLOR));
		text.setFont(Font.font("Arial", FontWeight.BOLD, size));
		return text;
	}

	public static HBox decorateHBoxFactory(final HBox hbox) {
		hbox.setBackground(new Background(
				new BackgroundFill(Color.web(ITEMBACKGROUNDCOLOR), new CornerRadii(CORNERRADIUS), Insets.EMPTY)));
		hbox.setBorder(new Border(new BorderStroke(Color.web(BORDERCOLOR), BorderStrokeStyle.SOLID,
				new CornerRadii(CORNERRADIUS), new BorderWidths(BORDERSIZE))));
		hbox.setAlignment(Pos.CENTER_LEFT);
		// setMouseEnteredEventHBox(hbox);
		// setMouseExitedEventHBox(hbox);
		return hbox;
	}

	public static SerialHBox decorateSerialHBoxFactory(final SerialHBox hbox) {
		hbox.setBackground(new Background(
				new BackgroundFill(Color.web(ITEMBACKGROUNDCOLOR), new CornerRadii(CORNERRADIUS), Insets.EMPTY)));
		hbox.setBorder(new Border(new BorderStroke(Color.web(BORDERCOLOR), BorderStrokeStyle.SOLID,
				new CornerRadii(CORNERRADIUS), new BorderWidths(BORDERSIZE))));
		hbox.setAlignment(Pos.CENTER_LEFT);
		// setMouseEnteredEventHBox(hbox);
		// setMouseExitedEventHBox(hbox);
		return hbox;
	}

	public static HBox decorateEffect(final HBox hbox) {
		setMouseEnteredEventHBox(hbox);
		setMouseExitedEventHBox(hbox);
		return hbox;
	}

	public static ListView<HBox> decorateListViewFactory(final ListView<HBox> listView) {
		listView.setBorder(new Border(new BorderStroke(Color.web(BORDERCOLOR), BorderStrokeStyle.SOLID,
				new CornerRadii(CORNERRADIUS), new BorderWidths(BORDERSIZE))));
		listView.setBackground(new Background(
				new BackgroundFill(Color.web(BACKGROUNDCOLOR), new CornerRadii(CORNERRADIUS), Insets.EMPTY)));
		listView.setPadding(new Insets(LISTVIEWINSETS));
		return listView;
	}

	public static Rectangle decorateRectangleFactory(final Rectangle rectangle, final int height, final int width,
			final String imageURL) {
		rectangle.setArcHeight(RECTANGLEARCHHEIGHT);
		rectangle.setArcWidth(RECTANGLEARCHWIDHT);
		rectangle.setHeight(height);
		rectangle.setWidth(width);
		rectangle.setFill(new ImagePattern(new Image(imageURL)));
		return rectangle;
	}

	public static VBox decorateVBoxFactory(final VBox vBox) {
		vBox.setAlignment(Pos.CENTER_LEFT);
		return vBox;
	}

	public static ImageView decorateImageViewFactory(final ImageView imageView, final int height, final int width) {
		imageView.setFitWidth(width);
		imageView.setFitHeight(height);
		return imageView;
	}

	private static void setMouseEnteredEventHBox(final HBox itemHbox) {
		itemHbox.setOnMouseEntered(e -> itemHbox.setBackground(new Background(
				new BackgroundFill(Color.web(BACKGROUNDCOLOR), new CornerRadii(CORNERRADIUS), Insets.EMPTY))));
	}

	private static void setMouseExitedEventHBox(final HBox itemHbox) {
		itemHbox.setOnMouseExited(e -> itemHbox.setBackground(new Background(
				new BackgroundFill(Color.web(ITEMBACKGROUNDCOLOR), new CornerRadii(CORNERRADIUS), Insets.EMPTY))));
	}

	private static void setMouseEnteredEventButton(final Button button) {
		button.setOnMouseEntered(e -> button.setBackground(new Background(
				new BackgroundFill(Color.web(BACKGROUNDCOLOR), new CornerRadii(CORNERRADIUS), Insets.EMPTY))));
	}

	private static void setMouseExitedEventButton(final Button button) {
		button.setOnMouseExited(e -> button.setBackground(new Background(
				new BackgroundFill(Color.web(ITEMBACKGROUNDCOLOR), new CornerRadii(CORNERRADIUS), Insets.EMPTY))));
	}

	public static Button decorateButtonFactory(final Button button) {
		button.setBorder(new Border(new BorderStroke(Color.web(BORDERCOLOR), BorderStrokeStyle.SOLID,
				new CornerRadii(CORNERRADIUS), new BorderWidths(BORDERSIZE))));
		button.setBackground(new Background(
				new BackgroundFill(Color.web(ITEMBACKGROUNDCOLOR), new CornerRadii(CORNERRADIUS), Insets.EMPTY)));
		button.setTextFill(Color.web(TEXTCOLOR));
		setMouseEnteredEventButton(button);
		setMouseExitedEventButton(button);
		return button;
	}

	public static Label decorateLabelFactory(final Label label) {
		label.setTextFill(Color.web(TEXTCOLOR));
		return label;
	}

	public static PasswordField decoratePasswordFieldFactory(final PasswordField textField) {
		textField.setBorder(new Border(new BorderStroke(Color.web(BORDERCOLOR), BorderStrokeStyle.SOLID,
				new CornerRadii(CORNERRADIUS), new BorderWidths(BORDERSIZE))));
		textField.setBackground(new Background(
				new BackgroundFill(Color.web(ITEMBACKGROUNDCOLOR), new CornerRadii(CORNERRADIUS), Insets.EMPTY)));
		textField.setStyle("-fx-text-inner-color: white;");
		return textField;
	}

	public static TextField decorateTextFieldFactory(final TextField textField) {
		textField.setBorder(new Border(new BorderStroke(Color.web(BORDERCOLOR), BorderStrokeStyle.SOLID,
				new CornerRadii(CORNERRADIUS), new BorderWidths(BORDERSIZE))));
		textField.setBackground(new Background(
				new BackgroundFill(Color.web(ITEMBACKGROUNDCOLOR), new CornerRadii(CORNERRADIUS), Insets.EMPTY)));
		textField.setStyle("-fx-text-inner-color: white;");
		return textField;
	}

	public static TextField decorateTextFieldFactory(final TextField textField, final int width, final int height) {
		textField.setBorder(new Border(new BorderStroke(Color.web(BORDERCOLOR), BorderStrokeStyle.SOLID,
				new CornerRadii(CORNERRADIUS), new BorderWidths(BORDERSIZE))));
		textField.setBackground(new Background(
				new BackgroundFill(Color.web(ITEMBACKGROUNDCOLOR), new CornerRadii(CORNERRADIUS), Insets.EMPTY)));
		textField.setStyle("-fx-text-inner-color: white;");
		textField.setMaxSize(width, height);
		textField.setPrefSize(width, height);
		return textField;
	}
}
