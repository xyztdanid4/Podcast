package szakdolgozat.podcast.data.podcast;

import java.util.List;

/**
 * The Class PodcastContainer. Contains information about a whole search, it
 * will be list of podcasts.
 *
 * @author Daniel Toth
 * @version 0.0.1
 * @since 0.0.1
 *
 */

public class PodcastContainer {

	/** The result count. */
	private int resultCount;

	/** The results. */
	private List<Podcast> results;

	/**
	 * Gets the result count.
	 *
	 * @return the result count
	 */
	public int getResultCount() {
		return this.resultCount;
	}

	/**
	 * Sets the resultcount.
	 *
	 * @param resultcount
	 *            the new resultcount
	 */
	public void setResultcount(final int resultcount) {
		this.resultCount = resultcount;
	}

	/**
	 * Gets the results.
	 *
	 * @return the results
	 */
	public List<Podcast> getResults() {
		return this.results;
	}

	/**
	 * Sets the results.
	 *
	 * @param results
	 *            the new results
	 */
	public void setResults(final List<Podcast> results) {
		this.results = results;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SearchResultContainer [resultcount=" + this.resultCount + ", results=" + this.results
				+ ", getResultcount()=" + getResultCount() + ", getResults()=" + getResults() + "]";
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
		result = prime * result + this.resultCount;
		result = prime * result + ((this.results == null) ? 0 : this.results.hashCode());
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
		final PodcastContainer other = (PodcastContainer) obj;
		if (this.resultCount != other.resultCount) {
			return false;
		}
		if (this.results == null) {
			if (other.results != null) {
				return false;
			}
		} else if (!this.results.equals(other.results)) {
			return false;
		}
		return true;
	}

}
