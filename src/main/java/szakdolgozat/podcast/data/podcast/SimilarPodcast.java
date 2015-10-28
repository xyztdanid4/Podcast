package szakdolgozat.podcast.data.podcast;

import java.util.List;

public class SimilarPodcast {
	private List<SimilarPodcastItem> Info;
	private List<SimilarPodcastItem> Results;

	public SimilarPodcast() {
		super();
	}

	public SimilarPodcast(List<SimilarPodcastItem> info, List<SimilarPodcastItem> results) {
		super();
		Info = info;
		Results = results;
	}

	public List<SimilarPodcastItem> getInfo() {
		return Info;
	}

	public void setInfo(List<SimilarPodcastItem> info) {
		Info = info;
	}

	public List<SimilarPodcastItem> getResults() {
		return Results;
	}

	public void setResults(List<SimilarPodcastItem> results) {
		Results = results;
	}

	@Override
	public String toString() {
		return "SimilarPodcastContainer [Info=" + Info + ", Results=" + Results + "]";
	}
}
