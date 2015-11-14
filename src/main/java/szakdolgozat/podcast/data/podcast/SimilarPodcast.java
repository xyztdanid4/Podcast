package szakdolgozat.podcast.data.podcast;

import java.util.List;

public class SimilarPodcast {
	private List<SimilarPodcastItem> Info;
	private List<SimilarPodcastItem> Results;

	public SimilarPodcast() {
		super();
	}

	public SimilarPodcast(final List<SimilarPodcastItem> info, final List<SimilarPodcastItem> results) {
		super();
		this.Info = info;
		this.Results = results;
	}

	public List<SimilarPodcastItem> getInfo() {
		return this.Info;
	}

	public void setInfo(final List<SimilarPodcastItem> info) {
		this.Info = info;
	}

	public List<SimilarPodcastItem> getResults() {
		return this.Results;
	}

	public void setResults(final List<SimilarPodcastItem> results) {
		this.Results = results;
	}

	@Override
	public String toString() {
		return "SimilarPodcastContainer [Info=" + this.Info + ", Results=" + this.Results + "]";
	}
}
