package szakdolgozat.podcast.morphia;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import szakdolgozat.podcast.user.User;

import com.mongodb.MongoClient;

public class MorphiaLoginConnector {
	private static Datastore dataStore;
	private static MorphiaLoginConnector instance = new MorphiaLoginConnector();
	private static Morphia morphia;

	private MorphiaLoginConnector() {
		morphia = new Morphia();
		morphia.mapPackage("szakdolgozat.podcast.user");
		dataStore = morphia.createDatastore(new MongoClient(), "Users");
	}

	public static MorphiaLoginConnector getInstance() {
		return instance;
	}

	public static void save(User user) {
		dataStore.save(user);
	}

	public static Datastore getDataStore() {
		return dataStore;
	}
}
