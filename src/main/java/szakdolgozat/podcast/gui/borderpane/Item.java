package szakdolgozat.podcast.gui.borderpane;

import java.io.Serializable;

public class Item implements Serializable {
	private String name = "Unknown";

	public Item(final String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return this.name;
	}
}