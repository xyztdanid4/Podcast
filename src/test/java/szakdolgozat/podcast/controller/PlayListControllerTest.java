package szakdolgozat.podcast.controller;

import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mongodb.morphia.query.Query;

import szakdolgozat.podcast.data.basicinformation.InformationContainer;
import szakdolgozat.podcast.morphia.MorphiaLoginConnector;
import szakdolgozat.podcast.user.User;

/**
 * 
 * @author Daniel Toth
 * @version 0.0.1
 * @since 0.0.1
 *
 */
public class PlayListControllerTest {
	@Before
	public void create() {

	}

	/**
	 * test for the insance
	 * 
	 * @see {@link PlayListController}
	 * @see {@link PlayListController#getInstance()}
	 */
	@Test
	public void instanceTest1() {
		assertNotNull(PlayListController.getInstance());
	}

	/**
	 * test for reading the DB
	 * 
	 * @see {@link MorphiaLoginConnector}
	 * @see {@link MorphiaLoginConnector#getInstance()}
	 * @see {@link MorphiaLoginConnector#save(User)}
	 * @see {@link User#User(String, String, String)()}
	 * @see {@link User}
	 * @see {@link InformationContainer}
	 * @see {@link InformationContainer#getInstance()}
	 * @see {@link InformationContainer#setOwner(String)}
	 * @see {@link PlayListController}
	 * @see {@link PlayListController#getInstance()}
	 * @see {@link PlayListController#readfromDB()}
	 */
	@Test
	public void readfromDBTest() {
		MorphiaLoginConnector.getInstance().save(new User("test", "test", "test@test.test"));
		InformationContainer.getInstance().setOwner("test");
		assertNotNull(PlayListController.getInstance().readfromDB());

	}

	/**
	 * test for instance of the playlist
	 * 
	 * @see {@link PlayListController}
	 * @see {@link PlayListController#getInstance()}
	 * @see {@link PlayListController#getPlayList()}
	 */
	@Test
	public void instanceTest2() {
		assertNotNull(PlayListController.getInstance().getPlayList());
	}

	/**
	 * test for instance of the realplaylist
	 * 
	 * @see {@link PlayListController}
	 * @see {@link PlayListController#getInstance()}
	 * @see {@link PlayListController#getPlayList()}
	 */
	@Test
	public void instanceTest3() {
		assertNotNull(PlayListController.getInstance().getRealPlayList());
	}

	/**
	 * clearing the used resources of the test
	 */
	@After
	public void destroy() {
		final Query<User> deleteUser1 = MorphiaLoginConnector.getInstance().getDataStore().createQuery(User.class)
				.filter("name", "test");
		MorphiaLoginConnector.getInstance().getDataStore().delete(deleteUser1);
	}
}
