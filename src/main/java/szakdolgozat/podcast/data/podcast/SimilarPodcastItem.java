package szakdolgozat.podcast.data.podcast;

/**
 * The Class SimilarPodcastItem.
 * 
 * @author Daniel Toth
 * @version 0.0.1
 * @since 0.0.1
 * 
 *        This class represent one item as similar search provide this.
 */
public class SimilarPodcastItem {

	/** The Name. */
	private String Name;

	/** The Type. */
	private String Type;

	/**
	 * Instantiates a new similar podcast item.
	 */
	public SimilarPodcastItem() {

	}

	/**
	 * Instantiates a new similar podcast item.
	 *
	 * @param name
	 *            the name
	 * @param type
	 *            the type
	 */
	public SimilarPodcastItem(final String name, final String type) {
		this.Name = name;
		this.Type = type;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return this.Name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name
	 *            the new name
	 */
	public void setName(final String name) {
		this.Name = name;
	}

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType() {
		return this.Type;
	}

	/**
	 * Sets the type.
	 *
	 * @param type
	 *            the new type
	 */
	public void setType(final String type) {
		this.Type = type;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SimilarPodcastItem [Name=" + this.Name + ", Type=" + this.Type + "]";
	}

}
