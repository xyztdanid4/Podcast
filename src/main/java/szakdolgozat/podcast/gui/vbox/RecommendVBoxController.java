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
		this.recommendListItems = new ArrayList<RecommendListItem>();
		for (final Podcast podcast : MorphiaConnector.getDataStore().createQuery(Podcast.class).asList()) {
			final SimilarPodcastJsonParser similarPodcastJsonParser = new SimilarPodcastJsonParser(
					"https://www.tastekid.com/api/similar?q=" + podcast.getArtistName()
							+ "&k=171743-podcasta-3P4R58KA&limit=2");
			if (isPodcastSubscribed(podcast.getArtistName())) {
				for (final SimilarPodcastItem item : similarPodcastJsonParser.getSearchResult().getSimilar()
						.getResults()) {
					// System.out.println(item.getName());
					final PodcastJsonParser jsonParser = new PodcastJsonParser(
							new String("https://itunes.apple.com/search?term=" + item.getName()
									+ "&entity=podcast&media=podcast&limit=5"));
					for (final Podcast podcastIterator : jsonParser.getSearchResult().getResults()) {
						this.recommendListItems.add(new RecommendListItem(podcastIterator.getArtworkUrl60(),
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
		return this.recommendListItems;
	}

	public void setRecommendListItems(final List<RecommendListItem> recommendListItems) {
		this.recommendListItems = recommendListItems;
	}
}
