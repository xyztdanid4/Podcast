package szakdolgozat.podcast.data.podcast;

public class SimilarPodcastItem {
	private String Name;
	private String Type;

	public SimilarPodcastItem() {

	}

	public SimilarPodcastItem(String name, String type) {
		Name = name;
		Type = type;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	@Override
	public String toString() {
		return "SimilarPodcastItem [Name=" + Name + ", Type=" + Type + "]";
	}

}
