package szakdolgozat.podcast.morphia;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;

import szakdolgozat.podcast.user.User;

/**
 * The Class MorphiaLoginConnector.
 *
 * @author Daniel Toth
 * @version 0.0.1
 * @since 0.0.1
 */
public class MorphiaLoginConnector {

	/** The data store. */
	private static Datastore dataStore;

	/** The instance. */
	private static MorphiaLoginConnector instance = null;

	/** The morphia. */
	private static Morphia morphia;

	/**
	 * Instantiates a new morphia login connector.
	 */
	private MorphiaLoginConnector() {
		morphia = new Morphia();
		morphia.mapPackage("szakdolgozat.podcast.user");
		dataStore = morphia.createDatastore(new MongoClient("localhost", 27017), "Users");
	}

	/**
	 * Gets the single instance of MorphiaLoginConnector.
	 *
	 * @return single instance of MorphiaLoginConnector
	 */
	public static MorphiaLoginConnector getInstance() {
		if (instance == null) {
			instance = new MorphiaLoginConnector();
		}
		return instance;
	}

	/**
	 * Save.
	 *
	 * @param user
	 *            the user
	 */
	public void save(final User user) {
		dataStore.save(user);
	}

	/**
	 * Gets the data store.
	 *
	 * @return the data store
	 */
	public Datastore getDataStore() {
		return dataStore;
	}
}
