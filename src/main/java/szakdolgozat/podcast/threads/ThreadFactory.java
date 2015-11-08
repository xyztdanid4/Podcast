package szakdolgozat.podcast.threads;

public class ThreadFactory {
	private static ThreadFactory instance = null;
	private Thread thread;

	public static ThreadFactory getInstance() {
		if (instance == null) {
			instance = new ThreadFactory();
		}
		return instance;
	}

	public static ThreadFactory create() {
		// this.thread = new Thread();
		return getInstance();
	}
}
