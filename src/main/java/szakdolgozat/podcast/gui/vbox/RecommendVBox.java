package szakdolgozat.podcast.gui.vbox;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import szakdolgozat.podcast.data.podcast.Podcast;
import szakdolgozat.podcast.gui.decorator.RecommendListDecorator;
import szakdolgozat.podcast.morphia.MorphiaConnector;

public class RecommendVBox extends VBox {
	private ListView<HBox> recommendListView;
	private ObservableList<HBox> recommendList;
	private List<String> genreIDs;

	public RecommendVBox() {
		recommendList = FXCollections.observableArrayList();
		recommendListView = new ListView<HBox>();
		recommendList.add(new HBox(new Text(new String("PROBA"))));
		recommendList.add(new HBox(new Text(new String("PROBA"))));
		recommendList.add(new HBox(new Text(new String("PROBA"))));
		recommendList.add(new HBox(new Text(new String("PROBA"))));
		recommendList.add(new HBox(new Text(new String("PROBA"))));
		RecommendListDecorator.decorateListView(recommendListView, RecommendListDecorator.LISTWIDTH,
				RecommendListDecorator.LISTHEIGHT);
		setMargin(recommendListView, new Insets(RecommendListDecorator.PADDING));
		recommendListView.setItems(recommendList);
		getChildren().add(recommendListView);

		List<Podcast> podcastsFromDBList = MorphiaConnector.getDataStore().createQuery(Podcast.class).asList();
		genreIDs = new ArrayList<String>();
		for (Podcast podcast : podcastsFromDBList) {
			genreIDs.addAll(podcast.getGenres());
		}
		System.out.println(genreIDs);
	}
}
