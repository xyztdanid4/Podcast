package szakdolgozat.podcast.gui.decorator;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import szakdolgozat.podcast.gui.samples.TextFieldSample;

public class LoginGridPaneDecorator extends Decorator {
	public static final int MESSAGELABELX = 1;
	public static final int MESSAGLABELY = 1;
	public static final int NAMELABELX = 1;
	public static final int NAMELABELY = 2;
	public static final int PASSWORDLABELX = 1;
	public static final int PASSWORDLABELY = 3;
	public static final int NAMETEXTFIELDX = 2;
	public static final int NAMETEXTFIELDY = 2;
	public static final int PASSWORDFIELDX = 2;
	public static final int PASSWORDFIELDY = 3;
	public static final int OKBUTTONX = 2;
	public static final int OKBUTTONY = 4;
	public static final int CANCELBUTTONX = 2;
	public static final int CANCELBUTTONY = 5;

	public LoginGridPaneDecorator() {

	}

	public static TextFieldSample decorateTextFieldFactory(TextFieldSample textField) {
		textField.setBorder(new Border(new BorderStroke(Color.web(BORDERCOLOR), BorderStrokeStyle.SOLID,
				new CornerRadii(CORNERRADIUS), new BorderWidths(BORDERSIZE))));
		textField.setBackground(new Background(
				new BackgroundFill(Color.web(ITEMBACKGROUNDCOLOR), new CornerRadii(CORNERRADIUS), Insets.EMPTY)));
		textField.setStyle("-fx-text-inner-color: white;");
		return textField;
	}
}
