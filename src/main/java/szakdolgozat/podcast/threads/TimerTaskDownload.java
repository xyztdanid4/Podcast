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

import szakdolgozat.podcast.basicinformation.InformationContainer;
import szakdolgozat.podcast.data.podcast.Podcast;
import szakdolgozat.podcast.data.podcast.PodcastEpisode;

public class TimerTaskDownload extends TimerTask {
	private static Podcast podcast;

	public TimerTaskDownload(final Podcast podcast) {
		TimerTaskDownload.podcast = podcast;
	}

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

	public static void saveImage(final PodcastEpisode podcastEpisode) throws IOException {
		final URL url = new URL(podcastEpisode.getImage());
		final String fileName = url.getFile();
		String fileUrl = InformationContainer.getInstance().getHome();
		fileUrl = new StringBuilder().append(fileUrl).append("\\").append("Documents").append("\\").append("Podcast")
				.toString();
		fileUrl = fileUrl.replace("\\", "\\\\");
		final File file = new File(fileUrl);
		if (file.exists()) {
		} else {
			file.mkdir();
		}
		final String destName = fileUrl + fileName.substring(fileName.lastIndexOf("/"));
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
		final String realFileUrl = new String("file:\\" + fileUrl + "\\" + url.getFile());
		final String command = "db.Podcast.update({'podcastEpisodes.image':" + "'" + oldImageUrl + "'" + "},{$set:{"
				+ '"' + "podcastEpisodes." + indexOf + ".image" + '"' + ':' + '"' + realFileUrl + '"' + "}})";
		db.eval(command);
	}

	public static void saveMP3(final PodcastEpisode podcastEpisode) throws IOException {
		final URL url = new URL(podcastEpisode.getGuid());
		final String fileName = url.getFile();
		String fileUrl = InformationContainer.getInstance().getHome();
		fileUrl = new StringBuilder().append(fileUrl).append("\\").append("Documents").append("\\").append("Podcast")
				.toString();
		fileUrl = fileUrl.replace("\\", "\\\\");
		final File file = new File(fileUrl);
		if (file.exists()) {
		} else {
			file.mkdir();
		}
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
		String realFileUrl = new String("file:/" + fileUrl + "/" + url.getFile());
		realFileUrl = realFileUrl.replace("\\", "/");
		realFileUrl = realFileUrl.replace("?", "");
		System.out.println(realFileUrl);
		final String command = "db.Podcast.update({'podcastEpisodes.guid':" + "'" + oldImageUrl + "'" + "},{$set:{"
				+ '"' + "podcastEpisodes." + indexOf + ".guid" + '"' + ':' + '"' + realFileUrl + '"' + "}})";
		db.eval(command);
	}
}
