package szakdolgozat.podcast.gui.vbox;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import szakdolgozat.podcast.data.podcast.Podcast;
import szakdolgozat.podcast.data.podcast.PodcastContainer;
import szakdolgozat.podcast.data.podcast.SimilarPodcastContainer;
import szakdolgozat.podcast.data.podcast.SimilarPodcastItem;
import szakdolgozat.podcast.gui.decorator.RecommendListDecorator;
import szakdolgozat.podcast.jsonparser.PodcastJsonParser;
import szakdolgozat.podcast.jsonparser.UrlReader;
import szakdolgozat.podcast.morphia.MorphiaConnector;

public class RecommendVBox extends VBox {
	private static final String RECOMMEND = "Similar podcast";
	private ListView<HBox> recommendListView;
	private ObservableList<HBox> recommendList;
	private List<String> artistNames;
	private PodcastContainer searchPodcastContainer;

	public RecommendVBox() {
		recommendList = FXCollections.observableArrayList();
		recommendListView = new ListView<HBox>();
		RecommendListDecorator.decorateListView(recommendListView, RecommendListDecorator.LISTWIDTH,
				RecommendListDecorator.LISTHEIGHT);
		setMargin(recommendListView, new Insets(RecommendListDecorator.PADDING));

		List<Podcast> podcastsFromDBList = MorphiaConnector.getDataStore().createQuery(Podcast.class).asList();
		artistNames = new ArrayList<String>();
		for (Podcast podcast : podcastsFromDBList) {
			UrlReader urlReader = new UrlReader();
			String result = "";
			try {
				result = urlReader.readUrl("https://www.tastekid.com/api/similar?q=" + podcast.getArtistName()
						+ "&k=171743-podcasta-T2Y4SDX0");
				// System.out.println(result);
			} catch (Exception e) {
				e.printStackTrace();
			}
			Gson gson = new Gson();
			SimilarPodcastContainer similarPodcastContainer = gson.fromJson(result, SimilarPodcastContainer.class);
			System.out.println(similarPodcastContainer);
			for (SimilarPodcastItem item : similarPodcastContainer.getSimilar().getResults()) {
				System.out.println(item.getName());
				PodcastJsonParser jsonParser = new PodcastJsonParser(new String("https://itunes.apple.com/search?term="
						+ item.getName() + "&entity=podcast&media=podcast&limit=5"));
				searchPodcastContainer = jsonParser.getSearchResult();
				for (Podcast podcastIterator : searchPodcastContainer.getResults()) {
					Rectangle imageRectangle = new Rectangle();
					RecommendListDecorator.decorateRectangle(imageRectangle,
							RecommendListDecorator.SMALLRECTANGLEHEIGHT, RecommendListDecorator.SMALLRECTANGLEWIDTH,
							podcastIterator.getArtworkUrl60());
					Text artistName = new Text(podcastIterator.getArtistName().length() > 20
							? new String(
									new StringBuilder(podcastIterator.getArtistName().substring(0, 20)).append("..."))
							: podcastIterator.getArtistName());
					RecommendListDecorator.decorateText(artistName, RecommendListDecorator.SMALLTEXTSIZE);
					HBox itemHBox = new HBox(RecommendListDecorator.PADDING, imageRectangle, artistName);
					RecommendListDecorator.decorateHBox(itemHBox);
					recommendList.add(itemHBox);
				}
			}
		}
		// System.out.println(artistNames);

		recommendListView.setItems(recommendList);
		Text recommendText = new Text(new String(RECOMMEND));
		RecommendListDecorator.decorateText(recommendText, RecommendListDecorator.BIGTEXTSIZE);
		getChildren().addAll(recommendText, recommendListView);

		RecommendListDecorator.decorate(this);
		setPadding();
	}

	private boolean isPodcastSubscribed(final String name) {
		return !(MorphiaConnector.getDataStore().createQuery(Podcast.class).filter("artistName = ", name).asList()
				.isEmpty());
	}

	private void setPadding() {
		setPadding(new Insets(RecommendListDecorator.PADDING, 0, 0, RecommendListDecorator.PADDING));
	}
}
