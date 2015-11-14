package szakdolgozat.podcast.data.podcast;

public class SimilarPodcastItem {
	private String Name;
	private String Type;

	public SimilarPodcastItem() {

	}

	public SimilarPodcastItem(final String name, final String type) {
		this.Name = name;
		this.Type = type;
	}

	public String getName() {
		return this.Name;
	}

	public void setName(final String name) {
		this.Name = name;
	}

	public String getType() {
		return this.Type;
	}

	public void setType(final String type) {
		this.Type = type;
	}

	@Override
	public String toString() {
		return "SimilarPodcastItem [Name=" + this.Name + ", Type=" + this.Type + "]";
	}

}
