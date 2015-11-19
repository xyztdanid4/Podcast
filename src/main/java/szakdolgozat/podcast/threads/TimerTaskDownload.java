package szakdolgozat.podcast.threads;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.TimerTask;

import com.mongodb.DB;
import com.mongodb.MongoClient;

import szakdolgozat.podcast.data.basicinformation.InformationContainer;
import szakdolgozat.podcast.data.podcast.Podcast;
import szakdolgozat.podcast.data.podcast.PodcastEpisode;

/**
 * The Class TimerTaskDownload.
 * 
 * * @author Daniel Toth
 * 
 * @version 0.0.1
 * @since 0.0.1
 */
public class TimerTaskDownload extends TimerTask {

	/** The podcast. */
	private static Podcast podcast;

	/**
	 * Instantiates a new timer task download.
	 *
	 * @param podcast
	 *            the podcast
	 */
	public TimerTaskDownload(final Podcast podcast) {
		TimerTaskDownload.podcast = podcast;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.TimerTask#run()
	 */
	@Override
	public void run() {

		for (final PodcastEpisode podcastEpisode : TimerTaskDownload.podcast.getPodcastEpisode()) {
			try {
				saveImage(podcastEpisode);
			} catch (final IOException e) {
				e.printStackTrace();
			}
		}

		for (int i = podcast.getPodcastEpisode().size() - 1; i > podcast.getPodcastEpisode().size() - 6; i--) {
			try {
				saveMP3(podcast.getPodcastEpisode().get(i));
			} catch (final IOException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * Save image.
	 *
	 * @param podcastEpisode
	 *            the podcast episode
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public void saveImage(final PodcastEpisode podcastEpisode) throws IOException {
		final URL url = new URL(podcastEpisode.getImage());
		System.out.println("url: " + url);
		String fileName = url.getFile();
		System.out.println("filename: " + fileName);
		fileName = fileName.substring(fileName.lastIndexOf("/"));
		System.out.println("filename: " + fileName);
		String fileUrl = InformationContainer.getInstance().getHome();
		System.out.println("home: " + InformationContainer.getInstance().getHome());
		fileUrl = new StringBuilder().append(fileUrl).append("\\").append("Documents").append("\\").append("Podcast")
				.toString();
		fileUrl = fileUrl.replace("\\", "\\\\");
		System.out.println("fileurl: " + fileUrl);

		final File file = new File(fileUrl);
		if (file.exists()) {
		} else {
			file.mkdir();
		}
		// this.getClass().getResourceAsStream(fileUrl);
		final String destName = fileUrl + fileName.substring(fileName.lastIndexOf("/"));
		System.out.println("destName: " + destName);
		try {
			final InputStream is = url.openStream();
			final OutputStream os = new FileOutputStream(destName);
			final byte[] b = new byte[2048];
			int length;
			while ((length = is.read(b)) != -1) {
				os.write(b, 0, length);
			}
			is.close();
			os.close();

			final MongoClient mongoClient = new MongoClient();
			final DB db = mongoClient.getDB(InformationContainer.getInstance().getOwner());
			final int indexOf = podcast.getPodcastEpisode().indexOf(podcastEpisode);
			final String oldImageUrl = podcastEpisode.getImage();
			final String realFileUrl = new String("file:\\" + fileUrl + "\\" + fileName);
			System.out.println("realfileurl: " + realFileUrl);
			final String command = "db.Podcast.update({'podcastEpisodes.image':" + "'" + oldImageUrl + "'" + "},{$set:{"
					+ '"' + "podcastEpisodes." + indexOf + ".image" + '"' + ':' + '"' + realFileUrl + '"' + "}})";
			db.eval(command);
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Save m p3.
	 *
	 * @param podcastEpisode
	 *            the podcast episode
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public void saveMP3(final PodcastEpisode podcastEpisode) throws IOException {
		final URL url = new URL(podcastEpisode.getGuid());
		String fileName = url.getFile();
		fileName = fileName.substring(fileName.lastIndexOf("/"));
		String fileUrl = InformationContainer.getInstance().getHome();
		fileUrl = new StringBuilder().append(fileUrl).append("\\").append("Documents").append("\\").append("Podcast")
				.toString();
		fileUrl = fileUrl.replace("\\", "\\\\");
		final File file = new File(fileUrl);
		if (file.exists()) {
		} else {
			file.mkdir();
		}
		// this.getClass().getResourceAsStream(fileUrl);
		String destName = fileUrl + fileName.substring(fileName.lastIndexOf("/"));
		destName = destName.replace("?", "");
		System.out.println(destName);
		final InputStream is = url.openStream();
		final OutputStream os = new FileOutputStream(destName);
		final byte[] b = new byte[100000];
		int length;
		while ((length = is.read(b)) != -1) {
			os.write(b, 0, length);
		}
		is.close();
		os.close();

		final MongoClient mongoClient = new MongoClient();
		final DB db = mongoClient.getDB(InformationContainer.getInstance().getOwner());
		final int indexOf = podcast.getPodcastEpisode().indexOf(podcastEpisode);
		System.out.println(indexOf);
		final String oldImageUrl = podcastEpisode.getGuid();
		System.out.println(oldImageUrl);
		String realFileUrl = new String("file:/" + fileUrl + "/" + fileName);
		realFileUrl = realFileUrl.replace("\\", "/");
		realFileUrl = realFileUrl.replace("?", "");
		System.out.println(realFileUrl);
		final String command = "db.Podcast.update({'podcastEpisodes.guid':" + "'" + oldImageUrl + "'" + "},{$set:{"
				+ '"' + "podcastEpisodes." + indexOf + ".guid" + '"' + ':' + '"' + realFileUrl + '"' + "}})";
		db.eval(command);
	}
}
