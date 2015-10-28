package szakdolgozat.podcast.data.podcast;

public class SimilarPodcastContainer {
	private SimilarPodcast Similar;

	public SimilarPodcastContainer() {

	}

	public SimilarPodcast getSimilar() {
		return Similar;
	}

	public void setSimilar(SimilarPodcast similar) {
		Similar = similar;
	}

	@Override
	public String toString() {
		return "SimilarPodcastContainer [Similar=" + Similar + "]";
	}

}
