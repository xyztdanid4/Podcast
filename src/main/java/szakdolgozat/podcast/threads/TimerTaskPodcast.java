package szakdolgozat.podcast.threads;

import java.util.Timer;
import java.util.TimerTask;

import szakdolgozat.podcast.data.podcast.Podcast;
import szakdolgozat.podcast.morphia.MorphiaConnector;

public class TimerTaskPodcast extends TimerTask {
	private int prevTrackCount;
	private int currentTrackCount;
	private int index;
	private Podcast podcast;
	private Timer timer;

	public TimerTaskPodcast(final int index) {
		this.index = index;
		this.podcast = MorphiaConnector.getDataStore().createQuery(Podcast.class).asList()
				.get(TimerTaskPodcast.this.index);
	}

	public Timer getTimer() {
		return this.timer;
	}

	public Podcast getPodcast() {
		return this.podcast;
	}

	public TimerTaskPodcast(final int prevTrackCount, final int currentTrackCount) {
		super();
		this.prevTrackCount = prevTrackCount;
		this.currentTrackCount = currentTrackCount;
	}

	@Override
	public void run() {
		this.timer = new Timer();
		this.timer.schedule(new TimerTask() {
			@Override
			public void run() {
				TimerTaskPodcast.this.prevTrackCount = MorphiaConnector.getDataStore().createQuery(Podcast.class)
						.asList().get(TimerTaskPodcast.this.index).getPodcastEpisode().size();
			}
		}, 0);

		this.timer.schedule(new TimerTask() {
			@Override
			public void run() {
				TimerTaskPodcast.this.currentTrackCount = MorphiaConnector.getDataStore().createQuery(Podcast.class)
						.asList().get(TimerTaskPodcast.this.index).getPodcastEpisode().size();
				System.out.println(
						TimerTaskPodcast.this.podcast.getArtistName() + " prev" + TimerTaskPodcast.this.prevTrackCount);
				System.out.println(TimerTaskPodcast.this.podcast.getArtistName() + " current"
						+ TimerTaskPodcast.this.currentTrackCount);

				if (TimerTaskPodcast.this.prevTrackCount < TimerTaskPodcast.this.currentTrackCount) {
					System.out.println("FRISSÍTÉS");
				}
			}
		}, 4000);
	}

}
