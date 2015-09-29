package szakdolgozat.podcast.morphia;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import szakdolgozat.podcast.user.User;

public class MorphiaLoginConnector {
	private static Datastore dataStore;
	private static MorphiaLoginConnector instance = new MorphiaLoginConnector();
	private static Morphia morphia;

	private static Morphia morphia2;
	private static Datastore dataStore2;

	private MorphiaLoginConnector() {
		morphia = new Morphia();
		morphia.mapPackage("szakdolgozat.podcast.user");
		dataStore = morphia.createDatastore(new MongoClient(), "Users");

		morphia2 = new Morphia();
		morphia2.mapPackage("szakdolgozat.podcast.user");

		dataStore2 = morphia2.createDatastore(
				new MongoClient(new MongoClientURI("mongodb://admin:admin@ds059702.mongolab.com:59702/users")),
				"users");

	}

	public static MorphiaLoginConnector getInstance() {
		return instance;
	}

	public static void save(User user) {
		dataStore.save(user);
		dataStore2.save(user);
	}

	public static Datastore getDataStore() {
		return dataStore;
	}
}
