package szakdolgozat.podcast.gui.mainwindow;

import javafx.application.Application;
import javafx.stage.Stage;
import szakdolgozat.podcast.gui.stage.LoginStage;

public class MainWindow extends Application {
	public static void main(final String[] args) {
		launch(args);
	}

	@Override
	public void start(final Stage primaryStage) {
		LoginStage.getInstance().show();

	}
}