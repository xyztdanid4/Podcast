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
		InformationContainer.getInstance();
		dataStore = morphia.createDatastore(new MongoClient(), InformationContainer.getInstance().getOwner());
	}

	public static void setDataStore(Datastore dataStore) {
		MorphiaConnector.dataStore = dataStore;
	}

	public static void setInstance(MorphiaConnector instance) {
		MorphiaConnector.instance = instance;
	}

	public static void setMorphia(Morphia morphia) {
		MorphiaConnector.morphia = morphia;
	}

	public static Morphia getMorphia() {
		return morphia;
	}

	public static Datastore getDataStore() {
		return dataStore;
	}

	public static void save(Podcast podcast) {
		dataStore.save(podcast);
	}

	public static MorphiaConnector getInstance() {
		return instance;
	}

}
