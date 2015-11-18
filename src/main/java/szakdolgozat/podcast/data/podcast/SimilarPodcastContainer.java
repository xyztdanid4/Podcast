package szakdolgozat.podcast.data.podcast;

// TODO: Auto-generated Javadoc
/**
 * The Class SimilarPodcastContainer.
 * 
 * @author Daniel Toth
 * @version 0.0.1
 * @since 0.0.1
 * 
 *        This class represents the container that is used for containing the
 *        result of the similar search.
 */
public class SimilarPodcastContainer {

	/** The Similar. */
	private SimilarPodcast Similar;

	/**
	 * Instantiates a new similar podcast container.
	 */
	public SimilarPodcastContainer() {

	}

	/**
	 * Gets the similar.
	 *
	 * @return the similar
	 */
	public SimilarPodcast getSimilar() {
		return this.Similar;
	}

	/**
	 * Sets the similar.
	 *
	 * @param similar
	 *            the new similar
	 */
	public void setSimilar(final SimilarPodcast similar) {
		this.Similar = similar;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SimilarPodcastContainer [Similar=" + this.Similar + "]";
	}

}
