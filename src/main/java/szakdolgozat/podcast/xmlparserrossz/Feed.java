package szakdolgozat.podcast.xmlparserrossz;

import java.util.ArrayList;
import java.util.List;

public class Feed {

	final String title;
	final String link;
	final String description;
	final String language;
	final String pubDate;

	final List<FeedMessage> entries = new ArrayList<FeedMessage>();

	public Feed(String title, String link, String description, String language,
			String pubDate) {
		this.title = title;
		this.link = link;
		this.description = description;
		this.language = language;
		this.pubDate = pubDate;
	}

	public List<FeedMessage> getMessages() {
		return entries;
	}

	public String getTitle() {
		return title;
	}

	public String getLink() {
		return link;
	}

	public String getDescription() {
		return description;
	}

	public String getLanguage() {
		return language;
	}

	public String getPubDate() {
		return pubDate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Feed [title=" + title + ", link=" + link + ", description="
				+ description + ", language=" + language + ", pubDate="
				+ pubDate + ", entries=" + entries + "]\n";
	}

}
