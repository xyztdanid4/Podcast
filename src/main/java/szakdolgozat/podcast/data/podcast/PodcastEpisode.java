package szakdolgozat.podcast.data.podcast;

public class PodcastEpisode {
	private String title;
	private String author;
	private String subtitle;
	private String summary;
	private String guid;
	private String pubDate;
	private String duration;
	private String image;

	public PodcastEpisode() {

	}

	public PodcastEpisode(String title, String author, String subtitle,
			String summary, String guid, String pubDate, String duration,
			String image) {
		super();
		this.title = title;
		this.author = author;
		this.subtitle = subtitle;
		this.summary = summary;
		this.guid = guid;
		this.pubDate = pubDate;
		this.duration = duration;
		this.image = image;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @param author
	 *            the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * @return the subtitle
	 */
	public String getSubtitle() {
		return subtitle;
	}

	/**
	 * @param subtitle
	 *            the subtitle to set
	 */
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	/**
	 * @return the summary
	 */
	public String getSummary() {
		return summary;
	}

	/**
	 * @param summary
	 *            the summary to set
	 */
	public void setSummary(String summary) {
		this.summary = summary;
	}

	/**
	 * @return the guid
	 */
	public String getGuid() {
		return guid;
	}

	/**
	 * @param guid
	 *            the guid to set
	 */
	public void setGuid(String guid) {
		this.guid = guid;
	}

	/**
	 * @return the pubdate
	 */
	public String getPubdate() {
		return pubDate;
	}

	/**
	 * @param pubdate
	 *            the pubdate to set
	 */
	public void setPubdate(String pubdate) {
		this.pubDate = pubdate;
	}

	/**
	 * @return the duration
	 */
	public String getDuration() {
		return duration;
	}

	/**
	 * @param duration
	 *            the duration to set
	 */
	public void setDuration(String duration) {
		this.duration = duration;
	}

	/**
	 * @return the image
	 */
	public String getImage() {
		return image;
	}

	/**
	 * @param image
	 *            the image to set
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PodcastEpisode [title=" + title + ", author=" + author
				+ ", subtitle=" + subtitle + ", summary=" + summary + ", guid="
				+ guid + ", pubdate=" + pubDate + ", duration=" + duration
				+ ", image=" + image + "]";
	}

}
