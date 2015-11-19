package szakdolgozat.podcast.controller;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mongodb.morphia.query.Query;

import szakdolgozat.podcast.morphia.MorphiaLoginConnector;
import szakdolgozat.podcast.user.User;

/**
 * 
 * @author Daniel Toth
 * @version 0.0.1
 * @since 0.0.1
 *
 */
public class NewLoginGridPaneControllerTest {
	NewLoginGridPaneController newLoginGridPaneController;

	@Before
	public void create() {
		this.newLoginGridPaneController = new NewLoginGridPaneController();
	}

	/**
	 * test for checkValuesInDB method
	 * 
	 * @see {@link MorphiaLoginConnector}
	 * @see {@link MorphiaLoginConnector#getInstance()}
	 * @see {@link NewLoginGridPaneController}
	 * @see {@link NewLoginGridPaneController#checkValuesInDB(String, String)}
	 * @see {@link MorphiaLoginConnector#save(User)}}
	 */
	@Test
	public void checkValuesInDBTest1() {
		MorphiaLoginConnector.getInstance().save(new User("test", "test", "test@test.test"));
		assertEquals(this.newLoginGridPaneController.checkValuesInDB("test", "test@test.test"), false);
	}

	/**
	 * test for checkValuesInDB method
	 * 
	 * @see {@link NewLoginGridPaneController}
	 * @see {@link NewLoginGridPaneController#checkValuesInDB(String, String)}
	 */
	@Test
	public void checkValuesInDBTest2() {
		assertEquals(this.newLoginGridPaneController.checkValuesInDB("goodtest", "goodtest@goodtest.goodtest"), true);
	}

	/**
	 * test for saveUser method
	 * 
	 * @see {@link MorphiaLoginConnector}
	 * @see {@link MorphiaLoginConnector#getInstance()}
	 * @see {@link NewLoginGridPaneController}
	 * @see {@link NewLoginGridPaneController#checkValuesInDB(String, String)}
	 * @see {@link MorphiaLoginConnector#save(User)}}
	 */
	@Test
	public void saveUserTest() {
		MorphiaLoginConnector.getInstance().save(new User("savetest", "savetest", "savetest@savetest.savetest"));
		assertEquals(MorphiaLoginConnector.getInstance().getDataStore().createQuery(User.class)
				.filter("name = ", "savetest").asList().isEmpty()
				&& MorphiaLoginConnector.getInstance().getDataStore().createQuery(User.class)
						.filter("email = ", "savetest@savetest.savetest").asList().isEmpty(),
				false);

	}

	/**
	 * cleaning up thing we use for this test
	 * 
	 * @see {@link MorphiaLoginConnector#getInstance()}
	 * @see {@link MorphiaLoginConnector}
	 * @see {@link MorphiaLoginConnector#getDataStore()}
	 */
	@After
	public void destroy() {
		final Query<User> deleteUser1 = MorphiaLoginConnector.getInstance().getDataStore().createQuery(User.class)
				.filter("name", "test");
		MorphiaLoginConnector.getInstance().getDataStore().delete(deleteUser1);
		final Query<User> deleteUser2 = MorphiaLoginConnector.getInstance().getDataStore().createQuery(User.class)
				.filter("name", "goodtest");
		MorphiaLoginConnector.getInstance().getDataStore().delete(deleteUser2);
		final Query<User> deleteUser3 = MorphiaLoginConnector.getInstance().getDataStore().createQuery(User.class)
				.filter("name", "savetest");
		MorphiaLoginConnector.getInstance().getDataStore().delete(deleteUser3);

	}
}
