package szakdolgozat.podcast.gui.mainwindow;

import javafx.application.Application;
import javafx.stage.Stage;
import szakdolgozat.podcast.gui.stage.LoginStage;

/**
 * The Class MainWindow.
 * 
 * @author Daniel Toth
 * @version 0.0.1
 * @since 0.0.1
 */
public class MainWindow extends Application {

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(final String[] args) {
		launch(args);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(final Stage primaryStage) {
		LoginStage.getInstance().show();
	}
}