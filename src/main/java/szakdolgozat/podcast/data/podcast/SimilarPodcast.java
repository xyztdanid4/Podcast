package szakdolgozat.podcast.data.podcast;

import java.util.List;

/**
 * The Class SimilarPodcast.
 *
 * @author Daniel Toth
 * @version 0.0.1
 * @since 0.0.1
 * 
 *        This class is containing information about Similar Podcasts
 */
public class SimilarPodcast {

	/** The Info. */
	private List<SimilarPodcastItem> Info;

	/** The Results. */
	private List<SimilarPodcastItem> Results;

	/**
	 * Instantiates a new similar podcast.
	 */
	public SimilarPodcast() {
		super();
	}

	/**
	 * Instantiates a new similar podcast.
	 *
	 * @param info
	 *            the info
	 * @param results
	 *            the results
	 */
	public SimilarPodcast(final List<SimilarPodcastItem> info, final List<SimilarPodcastItem> results) {
		super();
		this.Info = info;
		this.Results = results;
	}

	/**
	 * Gets the info.
	 *
	 * @return the info
	 */
	public List<SimilarPodcastItem> getInfo() {
		return this.Info;
	}

	/**
	 * Sets the info.
	 *
	 * @param info
	 *            the new info
	 */
	public void setInfo(final List<SimilarPodcastItem> info) {
		this.Info = info;
	}

	/**
	 * Gets the results.
	 *
	 * @return the results
	 */
	public List<SimilarPodcastItem> getResults() {
		return this.Results;
	}

	/**
	 * Sets the results.
	 *
	 * @param results
	 *            the new results
	 */
	public void setResults(final List<SimilarPodcastItem> results) {
		this.Results = results;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SimilarPodcastContainer [Info=" + this.Info + ", Results=" + this.Results + "]";
	}
}
