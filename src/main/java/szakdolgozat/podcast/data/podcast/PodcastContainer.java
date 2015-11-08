package szakdolgozat.podcast.data.podcast;

import java.util.List;

public class PodcastContainer {
	private int resultCount;
	private List<Podcast> results;

	public int getResultCount() {
		return this.resultCount;
	}

	public void setResultcount(final int resultcount) {
		this.resultCount = resultcount;
	}

	public List<Podcast> getResults() {
		return this.results;
	}

	public void setResults(final List<Podcast> results) {
		this.results = results;
	}

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
