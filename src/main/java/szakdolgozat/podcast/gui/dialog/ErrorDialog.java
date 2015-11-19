package szakdolgozat.podcast.gui.dialog;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import szakdolgozat.podcast.gui.builder.ButtonBuilder;
import szakdolgozat.podcast.gui.builder.StageBuilder;
import szakdolgozat.podcast.gui.builder.TextBuilder;
import szakdolgozat.podcast.gui.decorator.Decorator;
import szakdolgozat.podcast.gui.decorator.ErrorDialogDecorator;
import szakdolgozat.podcast.gui.stage.BaseStage;

/**
 * The Class ErrorDialog.
 *
 * @author Daniel Toth
 * @version 0.0.1
 * @since 0.0.1
 * 
 *        This class is representing the errordialog class. It is showed when we
 *        catch an error while the program is running.
 */
public abstract class ErrorDialog extends GridPane {

	/** The Constant OKBUTTONTEXT. */
	private static final String OKBUTTONTEXT = "Ok";

	/** The Constant WRONG. */
	private static final String WRONG = "Something went wrong!";

	/** The stage. */
	protected static BaseStage stage;

	/** The ok button. */
	protected static Button okButton;

	/** The text. */
	protected static Text text;

	/**
	 * Instantiates a new error dialog. Main function for this constructor is
	 * that create and show an error dialog window. It is an abstract class
	 * cause we have two kind of error in the program. Common things are in this
	 * class.
	 * 
	 * @param message
	 *            the message
	 */

	public ErrorDialog(final String message) {
		//-.-off
		ErrorDialog.okButton = ButtonBuilder.create()
									.text(OKBUTTONTEXT)
									.build();
		setOkButtonEnterEvent();
		
		ErrorDialog.text = TextBuilder.create()
									.smallText(message)
									.build();
		
		add(ErrorDialog.text, ErrorDialogDecorator.labelX, ErrorDialogDecorator.labelY);
		add(ErrorDialog.okButton, ErrorDialogDecorator.buttonX, ErrorDialogDecorator.buttonY);

		ErrorDialog.stage = StageBuilder.create(WRONG)
								.scene(this, ErrorDialogDecorator.SCENE_WIDTH, ErrorDialogDecorator.SCENE_HIGHT)
								.build();
		//-.-on
		decorate();
		show();
	}

	/**
	 * Show the stage.
	 */
	private void show() {
		ErrorDialog.stage.show();
	}

	/**
	 * Decorate the stage.
	 */
	private void decorate() {
		ErrorDialogDecorator.decorate(this);
		setMargin(text, new Insets(Decorator.PADDING));
	}

	/**
	 * Sets the ok button function. It is important what the button do. There
	 * are two children of this class, they will override this.
	 */
	protected abstract void setOkButtonFunction();

	/**
	 * Sets the ok button enter event. It is used for we can hit enter right
	 * after the dialog shows up.
	 */
	public void setOkButtonEnterEvent() {
		ErrorDialog.okButton.defaultButtonProperty().bind(ErrorDialog.okButton.focusedProperty());
	}

}
