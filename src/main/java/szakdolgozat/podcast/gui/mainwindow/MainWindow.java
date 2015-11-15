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
		/*
		 * final MongoClient mongoClient = new MongoClient(); final
		 * MongoDatabase database = mongoClient.getDatabase("dani"); final
		 * MongoCollection<Document> table = database.getCollection("Podcast");
		 * 
		 * final DB db = mongoClient.getDB("dani"); final DBObject command = new
		 * BasicDBObject();
		 * 
		 * 
		 * final String s =
		 * "db.Podcast.update({'podcastEpisodes.image': 'http://www.dannicpodcast.com/foh001.jpg'},{$set:{"
		 * + '"' + "podcastEpisodes.0.image" + '"' + ':' + '"' + " anyad" + '"'
		 * + "}})"; System.out.println(s); final String s1 =
		 * "db.Podcast.update({artistName: " + '"' + "Dannic" + '"' + "}" + ","
		 * + "{$set:{artistName: " + '"' + "anyad" + '"' + "}})";
		 * System.out.println(s1);
		 * 
		 * //final CommandResult r = db.command(s1); //System.out.println(r);
		 * db.eval(s1);
		 */
	}
}