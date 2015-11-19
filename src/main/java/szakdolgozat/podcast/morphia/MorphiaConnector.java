package szakdolgozat.podcast.morphia;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;

import szakdolgozat.podcast.data.basicinformation.InformationContainer;
import szakdolgozat.podcast.data.podcast.Podcast;

/**
 * The Class MorphiaConnector.
 * 
 * @author Daniel Toth
 * @version 0.0.1
 * @since 0.0.1
 */
public class MorphiaConnector {

	/** The data store. */
	private static Datastore dataStore;

	/** The instance. */
	private static MorphiaConnector instance = new MorphiaConnector();

	/** The morphia. */
	private static Morphia morphia;

	/**
	 * Instantiates a new morphia connector.
	 */
	private MorphiaConnector() {
		morphia = new Morphia();
		morphia.mapPackage("szakdolgozat.podcast.data.podcast");
		dataStore = morphia.createDatastore(new MongoClient("localhost", 27017),
				InformationContainer.getInstance().getOwner());
	}

	/**
	 * Sets the data store.
	 *
	 * @param dataStore
	 *            the new data store
	 */
	public static void setDataStore(final Datastore dataStore) {
		MorphiaConnector.dataStore = dataStore;
	}

	/**
	 * Sets the instance.
	 *
	 * @param instance
	 *            the new instance
	 */
	public static void setInstance(final MorphiaConnector instance) {
		MorphiaConnector.instance = instance;
	}

	/**
	 * Sets the morphia.
	 *
	 * @param morphia
	 *            the new morphia
	 */
	public static void setMorphia(final Morphia morphia) {
		MorphiaConnector.morphia = morphia;
	}

	/**
	 * Gets the morphia.
	 *
	 * @return the morphia
	 */
	public static Morphia getMorphia() {
		return morphia;
	}

	/**
	 * Gets the data store.
	 *
	 * @return the data store
	 */
	public Datastore getDataStore() {
		return dataStore;
	}

	/**
	 * Save.
	 *
	 * @param podcast
	 *            the podcast
	 */
	public static void save(final Podcast podcast) {
		dataStore.save(podcast);
	}

	/**
	 * Removes the.
	 *
	 * @param podcast
	 *            the podcast
	 */
	public static void remove(final Podcast podcast) {
		dataStore.delete(podcast);
	}

	/**
	 * Gets the single instance of MorphiaConnector.
	 *
	 * @return single instance of MorphiaConnector
	 */
	public static MorphiaConnector getInstance() {
		if (instance == null) {
			instance = new MorphiaConnector();
		}
		return instance;
	}

}
