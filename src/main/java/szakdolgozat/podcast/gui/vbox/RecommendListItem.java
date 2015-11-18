package szakdolgozat.podcast.gui.vbox;

/**
 * The Class RecommendListItem.
 * 
 * * @author Daniel Toth
 * 
 * @version 0.0.1
 * @since 0.0.1
 */
public class RecommendListItem {

	/** The image. */
	private String image;

	/** The artist. */
	private String artist;

	/**
	 * Instantiates a new recommend list item.
	 *
	 * @param image
	 *            the image
	 * @param artist
	 *            the artist
	 */
	public RecommendListItem(final String image, final String artist) {
		super();
		this.image = image;
		this.artist = artist;
	}

	/**
	 * Gets the image.
	 *
	 * @return the image
	 */
	public String getImage() {
		return this.image;
	}

	/**
	 * Sets the image.
	 *
	 * @param image
	 *            the new image
	 */
	public void setImage(final String image) {
		this.image = image;
	}

	/**
	 * Gets the artist.
	 *
	 * @return the artist
	 */
	public String getArtist() {
		return this.artist;
	}

	/**
	 * Sets the title.
	 *
	 * @param title
	 *            the new title
	 */
	public void setTitle(final String title) {
		this.artist = title;
	}

	/**
	 * Instantiates a new recommend list item.
	 */
	public RecommendListItem() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RecommendListItem [image=" + this.image + ", artist=" + this.artist + "]";
	}
}
