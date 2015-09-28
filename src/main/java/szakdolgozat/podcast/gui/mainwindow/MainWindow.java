package szakdolgozat.podcast.gui.mainwindow;

import javafx.application.Application;
import javafx.stage.Stage;
import szakdolgozat.podcast.gui.borderpane.MainBorderPane;
import szakdolgozat.podcast.gui.gridpane.LoginGridPane;
import szakdolgozat.podcast.gui.stage.LoginStage;

public class MainWindow extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	private MainBorderPane mainBorderPane;
	private LoginGridPane loginGridPane;

	@Override
	public void start(Stage primaryStage) {

		/*
		 * mainBorderPane = new MainBorderPane(); Scene scene = new
		 * Scene(mainBorderPane, 1300, 600); primaryStage.setTitle("Podcast");
		 * 
		 * primaryStage.setScene(scene); primaryStage.setResizable(false); //
		 * primaryStage.show();
		 */
		LoginStage.getInstance().show();
	}
}
