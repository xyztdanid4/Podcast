package szakdolgozat.podcast.data.podcast;

import java.util.List;

public class SimilarPodcast {
	private List<SimilarPodcastItem> Info;
	private List<SimilarPodcastItem> Results;

	@Override
	public String toString() {
		return "SimilarPodcastContainer [Info=" + Info + ", Results=" + Results + "]";
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

}
