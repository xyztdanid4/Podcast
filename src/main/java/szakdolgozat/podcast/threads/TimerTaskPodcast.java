package szakdolgozat.podcast.threads;

import java.util.ArrayList;
import java.util.TimerTask;

import org.mongodb.morphia.query.Query;

import javafx.application.Platform;
import szakdolgozat.podcast.basicinformation.InformationContainer;
import szakdolgozat.podcast.builder.HBoxBuilder;
import szakdolgozat.podcast.controller.NotificationBorderPaneController;
import szakdolgozat.podcast.data.podcast.Podcast;
import szakdolgozat.podcast.data.podcast.PodcastContainer;
import szakdolgozat.podcast.data.podcast.PodcastEpisode;
import szakdolgozat.podcast.jsonparser.PodcastJsonParser;
import szakdolgozat.podcast.mail.MailSender;
import szakdolgozat.podcast.morphia.MorphiaConnector;
import szakdolgozat.podcast.xmlparser.XmlParser;

/**
 * The Class TimerTaskPodcast.
 * 
 * @author Daniel Toth
 * @version 0.0.1
 * @since 0.0.1
 */
public class TimerTaskPodcast extends TimerTask {

	/**
	 * Instantiates a new timer task podcast.
	 */
	public TimerTaskPodcast() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.TimerTask#run()
	 */
	@Override
	public void run() {
		// a lényeg itt az hogy a dbn, tehét a feliratkozott podcasteken végig
		// kell menni.
		// egyesével nézzü, hogy van a frissítés, ezt onna tudjuk, hogy a
		// trackcount mezö nőtt e.
		// tehát az aktuális mezőn állunk elkérjük a trackcountot eltároljuk a
		// prev változoba.
		// viszont utána a neten is meg kell keresni a hozzátartozo
		// trackcountot.
		// ehhez ujra kell keresni majd parsolni a podcastet, ezt a podcast
		// artist alapján tesszük meg. ha megvan a podcast a neten akkor már
		// csak a hasonlitás kell
		// és ennek függvényében járunk el
		// amikor ujra feláll a rednszer akkor is nézi, hogy mik az ujak és azt
		// is kirakja.

		for (final Podcast podcast : MorphiaConnector.getDataStore().createQuery(Podcast.class).asList()) {
			final int prev = Integer.parseInt(podcast.getTrackCount());
			// System.out.println(podcast.getArtistName() + " prev: " + prev);

			final PodcastJsonParser jsonParser = new PodcastJsonParser(
					new String("https://itunes.apple.com/search?term=" + podcast.getArtistName()
							+ "&entity=podcast&media=podcast&limit=5"));
			final PodcastContainer searchResultContainer = jsonParser.getSearchResult();
			Podcast actual = new Podcast();
			for (final Podcast podcastIterator : searchResultContainer.getResults()) {
				if (podcastIterator.getArtistName().equals(podcast.getArtistName())) {
					actual = podcastIterator;
				}
			}
			final int current = Integer.parseInt(actual.getTrackCount());
			// System.out.println(actual.getArtistName() + "current: " +
			// current);
			if (prev == current) {
				// System.out.println("EGYENLO");
			}
			if (prev < current) {
				// System.out.println("FRISSITES");
				// 1, el kell menteni a dbbe az uj részt
				// törlés
				final Query<Podcast> deletePodcast = MorphiaConnector.getDataStore().createQuery(Podcast.class)
						.filter("artistName =", podcast.getArtistName());
				MorphiaConnector.getDataStore().delete(deletePodcast);

				// ujra vissza kell rakni a db be
				final XmlParser xmlParser = new XmlParser(actual.getFeedUrl());
				actual.setPodcastEpisode(new ArrayList<PodcastEpisode>(xmlParser.readFeed()));
				MorphiaConnector.getInstance();
				MorphiaConnector.getDataStore().save(actual);
				// 2, ki kell tenni a notificationlistbe
				final int difference = current - prev;
				for (int i = 1; i <= difference; i++) {
					// System.out
					// .println(actual.getPodcastEpisode().get(actual.getPodcastEpisode().size()
					// - i).getTitle());
					//-.-off
					final Podcast a = actual;
					final int ii = i;
					Platform.runLater(() -> NotificationBorderPaneController.getInstance().getNotificationContainer()
									.add(HBoxBuilder.create()
									.smallText("NEW EPISODE: ")
									.artist(a.getArtistName())
									.smallText(" ")
									.smallText(a.getPodcastEpisode().get(a.getPodcastEpisode().size() - ii).getTitle())
									.build())
							);
					Platform.runLater(() -> NotificationBorderPaneController.getInstance().getNewEpisodeContainer()
							.add(HBoxBuilder.create()
							.smallText("NEW EPISODE: ")
							.artist(a.getArtistName())
							.smallText(" ")
							.smallText(a.getPodcastEpisode().get(a.getPodcastEpisode().size() - ii).getTitle())
							.build())
					);
					//-.-on
					// 3, küldeni kell rola mailt ha az illető ugy akarja
					if (InformationContainer.getInstance().isEmailRequired()) {
						MailSender.getInstance().send(InformationContainer.getInstance().getMail(),
								a.getArtistName() + " " + a.getTrackName() + " " + a.getReleaseDate());
					}
					// 4, le kell tölteni és frissiteni az adatbázisba

				}
			}
		}
	}
}
