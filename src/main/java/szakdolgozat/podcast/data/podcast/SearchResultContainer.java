package szakdolgozat.podcast.data.podcast;

import java.util.List;

public class SearchResultContainer {
	private int resultCount;
	private List<Podcast> results;

	public int getResultCount() {
		return resultCount;
	}

	public void setResultcount(int resultcount) {
		this.resultCount = resultcount;
	}

	public List<Podcast> getResults() {
		return results;
	}

	public void setResults(List<Podcast> results) {
		this.results = results;
	}

	@Override
	public String toString() {
		return "SearchResultContainer [resultcount=" + resultCount
				+ ", results=" + results + ", getResultcount()="
				+ getResultCount() + ", getResults()=" + getResults() + "]";
	}
}
