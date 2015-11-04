package szakdolgozat.podcast.gui.vbox;

import java.util.ArrayList;
import java.util.List;

import szakdolgozat.podcast.data.podcast.Podcast;
import szakdolgozat.podcast.data.podcast.SimilarPodcastItem;
import szakdolgozat.podcast.jsonparser.PodcastJsonParser;
import szakdolgozat.podcast.jsonparser.SimilarPodcastJsonParser;
import szakdolgozat.podcast.morphia.MorphiaConnector;

public class RecommendVBoxController {
	private List<RecommendListItem> recommendListItems;

	public RecommendVBoxController() {
		recommendListItems = new ArrayList<RecommendListItem>();
		for (Podcast podcast : MorphiaConnector.getDataStore().createQuery(Podcast.class).asList()) {
			SimilarPodcastJsonParser similarPodcastJsonParser = new SimilarPodcastJsonParser(
					"https://www.tastekid.com/api/similar?q=" + podcast.getArtistName()
							+ "&k=171743-podcasta-T2Y4SDX0&limit=2");
			if (isPodcastSubscribed(podcast.getArtistName())) {
				for (SimilarPodcastItem item : similarPodcastJsonParser.getSearchResult().getSimilar().getResults()) {
					System.out.println(item.getName());
					PodcastJsonParser jsonParser = new PodcastJsonParser(
							new String("https://itunes.apple.com/search?term=" + item.getName()
									+ "&entity=podcast&media=podcast&limit=5"));
					for (Podcast podcastIterator : jsonParser.getSearchResult().getResults()) {
						recommendListItems.add(new RecommendListItem(podcastIterator.getArtworkUrl60(),
								podcastIterator.getArtistName()));
					}
				}
			}
		}
	}

	private boolean isPodcastSubscribed(final String name) {
		return !(MorphiaConnector.getDataStore().createQuery(Podcast.class).filter("artistName = ", name).asList()
				.isEmpty());
	}

	public List<RecommendListItem> getRecommendListItems() {
		return recommendListItems;
	}

	public void setRecommendListItems(List<RecommendListItem> recommendListItems) {
		this.recommendListItems = recommendListItems;
	}
}
