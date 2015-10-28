package szakdolgozat.podcast.data.podcast;

public class SimilarPodcastContainer {
	private SimilarPodcast Similar;

	public SimilarPodcastContainer() {

	}

	@Override
	public String toString() {
		return "SimilarPodcastContainer [Similar=" + Similar + "]";
	}

	public SimilarPodcast getSimilar() {
		return Similar;
	}

	public void setSimilar(SimilarPodcast similar) {
		Similar = similar;
	}

}
