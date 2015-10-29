package szakdolgozat.podcast.gui.vbox;

public class RecommendListItem {
	private String image;
	private String artist;

	public RecommendListItem(String image, String artist) {
		super();
		this.image = image;
		this.artist = artist;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getArtist() {
		return artist;
	}

	public void setTitle(String title) {
		this.artist = title;
	}

	public RecommendListItem() {

	}

	@Override
	public String toString() {
		return "RecommendListItem [image=" + image + ", artist=" + artist + "]";
	}
}
