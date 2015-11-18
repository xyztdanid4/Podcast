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
import szakdolgozat.podcast.gui.hbox.SerialHBox;

/**
 * The Class Decorator.
 * 
 * @author Daniel Toth
 * @version 0.0.1
 * @since 0.0.1
 */
public class Decorator {

	/** The Constant CORNERRADIUS. */
	// border - radius
	public static final int CORNERRADIUS = 10;

	/** The Constant BORDERSIZE. */
	public static final int BORDERSIZE = 3;

	/** The Constant BORDERPANEBORDERRADIUS. */
	public static final int BORDERPANEBORDERRADIUS = 0;

	/** The Constant TEXTCOLOR. */
	// colors
	public static final String TEXTCOLOR = "#FFFFFF";

	/** The Constant BORDERCOLOR. */
	public static final String BORDERCOLOR = "#006666";

	/** The Constant ITEMBACKGROUNDCOLOR. */
	public static final String ITEMBACKGROUNDCOLOR = "#808080";

	/** The Constant BACKGROUNDCOLOR. */
	public static final String BACKGROUNDCOLOR = "#191919";

	/** The Constant BIGTEXTSIZE. */
	// textsize
	public static final int BIGTEXTSIZE = 16;

	/** The Constant SMALLTEXTSIZE. */
	public static final int SMALLTEXTSIZE = 12;

	/** The Constant PADDING. */
	// padding
	public static final int PADDING = 20;

	/** The Constant HBOXPADDING. */
	public static final int HBOXPADDING = 10;

	/** The Constant VBOXPADDING. */
	public static final int VBOXPADDING = 10;

	/** The Constant LISTVIEWINSETS. */
	// listview
	public static final int LISTVIEWINSETS = 0;

	/** The Constant RECTANGLEARCHWIDHT. */
	// rectangle
	public static final int RECTANGLEARCHWIDHT = 10;

	/** The Constant RECTANGLEARCHHEIGHT. */
	public static final int RECTANGLEARCHHEIGHT = 10;

	/** The Constant SMALLRECTANGLEHEIGHT. */
	public static final int SMALLRECTANGLEHEIGHT = 40;

	/** The Constant SMALLRECTANGLEWIDTH. */
	public static final int SMALLRECTANGLEWIDTH = 40;

	/** The Constant BIGRECTANGLEHEIGHT. */
	public static final int BIGRECTANGLEHEIGHT = 90;

	/** The Constant BIGRECTANGLEWIDTH. */
	public static final int BIGRECTANGLEWIDTH = 90;

	/** The Constant HELPERVBOXPADDING. */
	public static final int HELPERVBOXPADDING = 5;

	/**
	 * Instantiates a new decorator.
	 */
	public Decorator() {

	}

	/**
	 * Decorate factory.
	 *
	 * @param pane
	 *            the pane
	 * @return the pane
	 */
	public static Pane decorateFactory(final Pane pane) {
		pane.setBackground(new Background(
				new BackgroundFill(Color.web(BACKGROUNDCOLOR), new CornerRadii(BORDERPANEBORDERRADIUS), Insets.EMPTY)));
		pane.setBorder(new Border(new BorderStroke(Color.web(BORDERCOLOR), BorderStrokeStyle.SOLID,
				new CornerRadii(BORDERPANEBORDERRADIUS),
				new BorderWidths(BORDERSIZE, BORDERSIZE, BORDERSIZE, BORDERSIZE))));
		return pane;
	}

	/**
	 * Decorate check box factory.
	 *
	 * @param checkBox
	 *            the check box
	 * @return the check box
	 */
	public static CheckBox decorateCheckBoxFactory(final CheckBox checkBox) {
		checkBox.setTextFill(Color.web(TEXTCOLOR));
		checkBox.setFont(Font.font("Arial", FontWeight.BOLD, SMALLTEXTSIZE));
		return checkBox;
	}

	/**
	 * Decorate text factory.
	 *
	 * @param text
	 *            the text
	 * @param size
	 *            the size
	 * @return the text
	 */
	public static Text decorateTextFactory(final Text text, final int size) {
		text.setFill(Color.web(TEXTCOLOR));
		text.setFont(Font.font("Arial", FontWeight.BOLD, size));
		return text;
	}

	/**
	 * Decorate h box factory.
	 *
	 * @param hbox
	 *            the hbox
	 * @return the h box
	 */
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

	/**
	 * Decorate serial h box factory.
	 *
	 * @param hbox
	 *            the hbox
	 * @return the serial h box
	 */
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

	/**
	 * Decorate effect.
	 *
	 * @param hbox
	 *            the hbox
	 * @return the h box
	 */
	public static HBox decorateEffect(final HBox hbox) {
		setMouseEnteredEventHBox(hbox);
		setMouseExitedEventHBox(hbox);
		return hbox;
	}

	/**
	 * Decorate serial effect.
	 *
	 * @param hbox
	 *            the hbox
	 * @return the serial h box
	 */
	public static SerialHBox decorateSerialEffect(final SerialHBox hbox) {
		setMouseEnteredEventHBox(hbox);
		setMouseExitedEventHBox(hbox);
		return hbox;
	}

	/**
	 * Decorate list view factory.
	 *
	 * @param listView
	 *            the list view
	 * @return the list view
	 */
	public static ListView<HBox> decorateListViewFactory(final ListView<HBox> listView) {
		listView.setBorder(new Border(new BorderStroke(Color.web(BORDERCOLOR), BorderStrokeStyle.SOLID,
				new CornerRadii(CORNERRADIUS), new BorderWidths(BORDERSIZE))));
		listView.setBackground(new Background(
				new BackgroundFill(Color.web(BACKGROUNDCOLOR), new CornerRadii(CORNERRADIUS), Insets.EMPTY)));
		listView.setPadding(new Insets(LISTVIEWINSETS));
		return listView;
	}

	/**
	 * Decorate serial list view factory.
	 *
	 * @param listView
	 *            the list view
	 * @return the list view
	 */
	public static ListView<SerialHBox> decorateSerialListViewFactory(final ListView<SerialHBox> listView) {
		listView.setBorder(new Border(new BorderStroke(Color.web(BORDERCOLOR), BorderStrokeStyle.SOLID,
				new CornerRadii(CORNERRADIUS), new BorderWidths(BORDERSIZE))));
		listView.setBackground(new Background(
				new BackgroundFill(Color.web(BACKGROUNDCOLOR), new CornerRadii(CORNERRADIUS), Insets.EMPTY)));
		listView.setPadding(new Insets(LISTVIEWINSETS));
		return listView;
	}

	/**
	 * Decorate play list view factory.
	 *
	 * @param listView
	 *            the list view
	 * @return the list view
	 */
	public static ListView<SerialHBox> decoratePlayListViewFactory(final ListView<SerialHBox> listView) {
		listView.setBorder(new Border(new BorderStroke(Color.web(BORDERCOLOR), BorderStrokeStyle.SOLID,
				new CornerRadii(CORNERRADIUS), new BorderWidths(BORDERSIZE))));
		listView.setBackground(new Background(
				new BackgroundFill(Color.web(BACKGROUNDCOLOR), new CornerRadii(CORNERRADIUS), Insets.EMPTY)));
		listView.setPadding(new Insets(LISTVIEWINSETS));
		return listView;
	}

	/**
	 * Decorate rectangle factory.
	 *
	 * @param rectangle
	 *            the rectangle
	 * @param height
	 *            the height
	 * @param width
	 *            the width
	 * @param imageURL
	 *            the image url
	 * @return the rectangle
	 */
	public static Rectangle decorateRectangleFactory(final Rectangle rectangle, final int height, final int width,
			final String imageURL) {
		rectangle.setArcHeight(RECTANGLEARCHHEIGHT);
		rectangle.setArcWidth(RECTANGLEARCHWIDHT);
		rectangle.setHeight(height);
		rectangle.setWidth(width);
		rectangle.setFill(new ImagePattern(new Image(imageURL)));
		return rectangle;
	}

	/**
	 * Decorate v box factory.
	 *
	 * @param vBox
	 *            the v box
	 * @return the v box
	 */
	public static VBox decorateVBoxFactory(final VBox vBox) {
		vBox.setAlignment(Pos.CENTER_LEFT);
		return vBox;
	}

	/**
	 * Decorate image view factory.
	 *
	 * @param imageView
	 *            the image view
	 * @param height
	 *            the height
	 * @param width
	 *            the width
	 * @return the image view
	 */
	public static ImageView decorateImageViewFactory(final ImageView imageView, final int height, final int width) {
		imageView.setFitWidth(width);
		imageView.setFitHeight(height);
		return imageView;
	}

	/**
	 * Sets the mouse entered event h box.
	 *
	 * @param itemHbox
	 *            the new mouse entered event h box
	 */
	private static void setMouseEnteredEventHBox(final HBox itemHbox) {
		itemHbox.setOnMouseEntered(e -> itemHbox.setBackground(new Background(
				new BackgroundFill(Color.web(BACKGROUNDCOLOR), new CornerRadii(CORNERRADIUS), Insets.EMPTY))));
	}

	/**
	 * Sets the mouse exited event h box.
	 *
	 * @param itemHbox
	 *            the new mouse exited event h box
	 */
	private static void setMouseExitedEventHBox(final HBox itemHbox) {
		itemHbox.setOnMouseExited(e -> itemHbox.setBackground(new Background(
				new BackgroundFill(Color.web(ITEMBACKGROUNDCOLOR), new CornerRadii(CORNERRADIUS), Insets.EMPTY))));
	}

	/**
	 * Sets the mouse entered event button.
	 *
	 * @param button
	 *            the new mouse entered event button
	 */
	private static void setMouseEnteredEventButton(final Button button) {
		button.setOnMouseEntered(e -> button.setBackground(new Background(
				new BackgroundFill(Color.web(BACKGROUNDCOLOR), new CornerRadii(CORNERRADIUS), Insets.EMPTY))));
	}

	/**
	 * Sets the mouse exited event button.
	 *
	 * @param button
	 *            the new mouse exited event button
	 */
	private static void setMouseExitedEventButton(final Button button) {
		button.setOnMouseExited(e -> button.setBackground(new Background(
				new BackgroundFill(Color.web(ITEMBACKGROUNDCOLOR), new CornerRadii(CORNERRADIUS), Insets.EMPTY))));
	}

	/**
	 * Decorate button factory.
	 *
	 * @param button
	 *            the button
	 * @return the button
	 */
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

	/**
	 * Decorate label factory.
	 *
	 * @param label
	 *            the label
	 * @return the label
	 */
	public static Label decorateLabelFactory(final Label label) {
		label.setTextFill(Color.web(TEXTCOLOR));
		return label;
	}

	/**
	 * Decorate password field factory.
	 *
	 * @param textField
	 *            the text field
	 * @return the password field
	 */
	public static PasswordField decoratePasswordFieldFactory(final PasswordField textField) {
		textField.setBorder(new Border(new BorderStroke(Color.web(BORDERCOLOR), BorderStrokeStyle.SOLID,
				new CornerRadii(CORNERRADIUS), new BorderWidths(BORDERSIZE))));
		textField.setBackground(new Background(
				new BackgroundFill(Color.web(ITEMBACKGROUNDCOLOR), new CornerRadii(CORNERRADIUS), Insets.EMPTY)));
		textField.setStyle("-fx-text-inner-color: white;");
		return textField;
	}

	/**
	 * Decorate text field factory.
	 *
	 * @param textField
	 *            the text field
	 * @return the text field
	 */
	public static TextField decorateTextFieldFactory(final TextField textField) {
		textField.setBorder(new Border(new BorderStroke(Color.web(BORDERCOLOR), BorderStrokeStyle.SOLID,
				new CornerRadii(CORNERRADIUS), new BorderWidths(BORDERSIZE))));
		textField.setBackground(new Background(
				new BackgroundFill(Color.web(ITEMBACKGROUNDCOLOR), new CornerRadii(CORNERRADIUS), Insets.EMPTY)));
		textField.setStyle("-fx-text-inner-color: white;");
		return textField;
	}

	/**
	 * Decorate text field factory.
	 *
	 * @param textField
	 *            the text field
	 * @param width
	 *            the width
	 * @param height
	 *            the height
	 * @return the text field
	 */
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
