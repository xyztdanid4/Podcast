package szakdolgozat.podcast.morphia;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;

import szakdolgozat.podcast.basicinformation.InformationContainer;
import szakdolgozat.podcast.data.podcast.Podcast;

public class MorphiaConnector {

	private static Datastore dataStore;
	private static MorphiaConnector instance = new MorphiaConnector();
	private static Morphia morphia;

	private MorphiaConnector() {
		morphia = new Morphia();
		morphia.mapPackage("szakdolgozat.podcast.data.podcast");
		dataStore = morphia.createDatastore(new MongoClient(), InformationContainer.getInstance().getOwner());
	}

	public static void setDataStore(final Datastore dataStore) {
		MorphiaConnector.dataStore = dataStore;
	}

	public static void setInstance(final MorphiaConnector instance) {
		MorphiaConnector.instance = instance;
	}

	public static void setMorphia(final Morphia morphia) {
		MorphiaConnector.morphia = morphia;
	}

	public static Morphia getMorphia() {
		return morphia;
	}

	public static Datastore getDataStore() {
		return dataStore;
	}

	public static void save(final Podcast podcast) {
		dataStore.save(podcast);
	}

	public static void update(final Podcast podcast) {
		// dataStore.update(key, ops);
	}

	public static void remove(final Podcast podcast) {
		dataStore.delete(podcast);
	}

	public static MorphiaConnector getInstance() {
		return instance;
	}

}
