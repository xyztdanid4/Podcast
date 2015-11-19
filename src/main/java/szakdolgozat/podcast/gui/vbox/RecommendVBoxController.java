package szakdolgozat.podcast.gui.vbox;

import java.util.ArrayList;
import java.util.List;

import szakdolgozat.podcast.data.podcast.Podcast;
import szakdolgozat.podcast.data.podcast.SimilarPodcastItem;
import szakdolgozat.podcast.jsonparser.PodcastJsonParser;
import szakdolgozat.podcast.jsonparser.SimilarPodcastJsonParser;
import szakdolgozat.podcast.morphia.MorphiaConnector;

/**
 * The Class RecommendVBoxController.
 * 
 * * @author Daniel Toth
 * 
 * @version 0.0.1
 * @since 0.0.1
 */
public class RecommendVBoxController {

	/** The recommend list items. */
	private List<RecommendListItem> recommendListItems;

	/**
	 * Instantiates a new recommend v box controller.
	 */
	public RecommendVBoxController() {
		this.recommendListItems = new ArrayList<RecommendListItem>();
		for (final Podcast podcast : MorphiaConnector.getInstance().getDataStore().createQuery(Podcast.class)
				.asList()) {
			final SimilarPodcastJsonParser similarPodcastJsonParser = new SimilarPodcastJsonParser(
					"https://www.tastekid.com/api/similar?q=" + podcast.getArtistName()
							+ "&k=171743-podcasta-3P4R58KA&limit=2");
			if (isPodcastSubscribed(podcast.getArtistName())) {
				for (final SimilarPodcastItem item : similarPodcastJsonParser.getSearchResult().getSimilar()
						.getResults()) {
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

	/**
	 * Checks if is podcast subscribed.
	 *
	 * @param name
	 *            the name
	 * @return true, if is podcast subscribed
	 */
	private boolean isPodcastSubscribed(final String name) {
		return !(MorphiaConnector.getInstance().getDataStore().createQuery(Podcast.class).filter("artistName = ", name)
				.asList().isEmpty());
	}

	/**
	 * Gets the recommend list items.
	 *
	 * @return the recommend list items
	 */
	public List<RecommendListItem> getRecommendListItems() {
		return this.recommendListItems;
	}

	/**
	 * Sets the recommend list items.
	 *
	 * @param recommendListItems
	 *            the new recommend list items
	 */
	public void setRecommendListItems(final List<RecommendListItem> recommendListItems) {
		this.recommendListItems = recommendListItems;
	}
}
