package szakdolgozat.podcast.data.podcast;

import java.io.Serializable;

public class PodcastEpisode implements Serializable {
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

	public PodcastEpisode(final String title, final String author, final String subtitle, final String summary,
			final String guid, final String pubDate, final String duration, final String image) {
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
		return this.title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(final String title) {
		this.title = title;
	}

	/**
	 * @return the author
	 */
	public String getAuthor() {
		return this.author;
	}

	/**
	 * @param author
	 *            the author to set
	 */
	public void setAuthor(final String author) {
		this.author = author;
	}

	/**
	 * @return the subtitle
	 */
	public String getSubtitle() {
		return this.subtitle;
	}

	/**
	 * @param subtitle
	 *            the subtitle to set
	 */
	public void setSubtitle(final String subtitle) {
		this.subtitle = subtitle;
	}

	/**
	 * @return the summary
	 */
	public String getSummary() {
		return this.summary;
	}

	/**
	 * @param summary
	 *            the summary to set
	 */
	public void setSummary(final String summary) {
		this.summary = summary;
	}

	/**
	 * @return the guid
	 */
	public String getGuid() {
		return this.guid;
	}

	/**
	 * @param guid
	 *            the guid to set
	 */
	public void setGuid(final String guid) {
		this.guid = guid;
	}

	/**
	 * @return the pubdate
	 */
	public String getPubdate() {
		return this.pubDate;
	}

	/**
	 * @param pubdate
	 *            the pubdate to set
	 */
	public void setPubdate(final String pubdate) {
		this.pubDate = pubdate;
	}

	/**
	 * @return the duration
	 */
	public String getDuration() {
		return this.duration;
	}

	/**
	 * @param duration
	 *            the duration to set
	 */
	public void setDuration(final String duration) {
		this.duration = duration;
	}

	/**
	 * @return the image
	 */
	public String getImage() {
		return this.image;
	}

	/**
	 * @param image
	 *            the image to set
	 */
	public void setImage(final String image) {
		this.image = image;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.title;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.author == null) ? 0 : this.author.hashCode());
		result = prime * result + ((this.duration == null) ? 0 : this.duration.hashCode());
		result = prime * result + ((this.guid == null) ? 0 : this.guid.hashCode());
		result = prime * result + ((this.image == null) ? 0 : this.image.hashCode());
		result = prime * result + ((this.pubDate == null) ? 0 : this.pubDate.hashCode());
		result = prime * result + ((this.subtitle == null) ? 0 : this.subtitle.hashCode());
		result = prime * result + ((this.summary == null) ? 0 : this.summary.hashCode());
		result = prime * result + ((this.title == null) ? 0 : this.title.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final PodcastEpisode other = (PodcastEpisode) obj;
		if (this.author == null) {
			if (other.author != null) {
				return false;
			}
		} else if (!this.author.equals(other.author)) {
			return false;
		}
		if (this.duration == null) {
			if (other.duration != null) {
				return false;
			}
		} else if (!this.duration.equals(other.duration)) {
			return false;
		}
		if (this.guid == null) {
			if (other.guid != null) {
				return false;
			}
		} else if (!this.guid.equals(other.guid)) {
			return false;
		}
		if (this.image == null) {
			if (other.image != null) {
				return false;
			}
		} else if (!this.image.equals(other.image)) {
			return false;
		}
		if (this.pubDate == null) {
			if (other.pubDate != null) {
				return false;
			}
		} else if (!this.pubDate.equals(other.pubDate)) {
			return false;
		}
		if (this.subtitle == null) {
			if (other.subtitle != null) {
				return false;
			}
		} else if (!this.subtitle.equals(other.subtitle)) {
			return false;
		}
		if (this.summary == null) {
			if (other.summary != null) {
				return false;
			}
		} else if (!this.summary.equals(other.summary)) {
			return false;
		}
		if (this.title == null) {
			if (other.title != null) {
				return false;
			}
		} else if (!this.title.equals(other.title)) {
			return false;
		}
		return true;
	}

}
