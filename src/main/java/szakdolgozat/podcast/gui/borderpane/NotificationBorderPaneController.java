package szakdolgozat.podcast.gui.borderpane;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javafx.collections.ObservableList;
import javafx.scene.layout.HBox;
import szakdolgozat.podcast.data.podcast.Podcast;
import szakdolgozat.podcast.morphia.MorphiaConnector;

public class NotificationBorderPaneController {
	private static NotificationBorderPaneController instance = null;
	private static ObservableList<HBox> notificationContainer;
	private final List<Podcast> podcastsFromDBList;

	private NotificationBorderPaneController() {
		this.podcastsFromDBList = MorphiaConnector.getDataStore().createQuery(Podcast.class).asList();
		final int podcastCount = this.podcastsFromDBList.size();

		for (int i = 0; i < podcastCount; i++) {
			final Timer timer = new Timer();
			final int ii = i;
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					final int previousTrackCount = Integer.parseInt(MorphiaConnector.getDataStore()
							.createQuery(Podcast.class).asList().get(ii).getTrackCount());
					try {
						wait(2000);
					} catch (final InterruptedException e) {
						e.printStackTrace();
					}
					final int currentTrackCount = Integer.parseInt(MorphiaConnector.getDataStore()
							.createQuery(Podcast.class).asList().get(ii).getTrackCount());

					System.out.println(MorphiaConnector.getDataStore().createQuery(Podcast.class).asList().get(ii)
							.getArtistName());
					System.out.println(MorphiaConnector.getDataStore().createQuery(Podcast.class).asList().get(ii)
							.getTrackCount());

					if (previousTrackCount < currentTrackCount) {
						// frissítés
						// notificationContainer.add(HBoxBuilder.create().smallText("new
						// episode: ").artist().build());
					}
				}
			}, 5000, 2000);
		}

		final ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
		exec.scheduleAtFixedRate(() -> {
			System.out.println("asd");
			try {
				exec.wait(2000);
			} catch (final Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} , 0, 1, TimeUnit.SECONDS);

	}

	public static NotificationBorderPaneController getInstance() {
		if (instance == null) {
			instance = new NotificationBorderPaneController();
		}
		return instance;
	}
}
