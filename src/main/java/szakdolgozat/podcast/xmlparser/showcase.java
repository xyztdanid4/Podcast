package szakdolgozat.podcast.xmlparser;

import java.util.ArrayList;
import java.util.List;

import szakdolgozat.podcast.data.podcast.PodcastEpisode;

public class showcase {
	public static void main(String args[]) {
		XmlParser podcast = new XmlParser(
				"http://www.dannicpodcast.com/podcast.xml");
		List<PodcastEpisode> readConfig = new ArrayList<PodcastEpisode>();
		readConfig = podcast.readFeed();
		for (PodcastEpisode item : readConfig) {
			System.out.println(item);
		}
	}
}
