package szakdolgozat.podcast.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mongodb.morphia.query.Query;

import szakdolgozat.podcast.morphia.MorphiaLoginConnector;
import szakdolgozat.podcast.user.User;

/**
 * @author Daniel Toth
 * @version 0.0.1
 * @since 0.0.1
 *
 */

public class LoginGridPaneControllerTest {
	LoginGridPaneController loginGridPaneController;

	@Before
	public void create() {
		this.loginGridPaneController = new LoginGridPaneController();
	}

	/**
	 * test for the instance
	 * 
	 * @see {@link LoginGridPaneController}
	 */
	@Test
	public void instanceTest() {
		assertNotNull(this.loginGridPaneController);
	}

	/**
	 * test for the instance
	 * 
	 * @see {@link MorphiaLoginConnector#getInstance()}
	 */
	@Test
	public void dbInstanceTest() {
		assertNotNull(MorphiaLoginConnector.getInstance());
	}

	/**
	 * test for the checkUserAndPassword method
	 * 
	 * @see {@link LoginGridPaneController#checkUserAndPassword(String, String)}
	 * @see {@link MorphiaLoginConnector#getInstance()}
	 * @see {@link MorphiaLoginConnector}
	 * @see {@link MorphiaLoginConnector#save(User)}
	 * 
	 */
	@Test
	public void checkUserAndPasswordTest1() {
		MorphiaLoginConnector.getInstance().save(new User("test", "test", "test@test.test"));
		assertEquals(true, this.loginGridPaneController.checkUserAndPassword("test", "test"));
	}

	/**
	 * test for the checkUserAndPassword method
	 * 
	 * @see {@link LoginGridPaneController#checkUserAndPassword(String, String)}
	 * 
	 */
	@Test
	public void checkUserAndPasswordTest2() {
		assertEquals(false, this.loginGridPaneController.checkUserAndPassword("qwertzuiop", "qwertzuiop"));
	}

	/**
	 * test for the getEmailFromDB method
	 * 
	 * @see {@link MorphiaLoginConnector#getInstance()}
	 * @see {@link MorphiaLoginConnector}
	 * @see {@link MorphiaLoginConnector#save(User)}
	 * @see {@link LoginGridPaneController#getEmailFromDB(String)}
	 */
	@Test
	public void getEmailFromDBtest() {
		MorphiaLoginConnector.getInstance().save(new User("test", "test", "test@test.test"));
		assertEquals("test@test.test", this.loginGridPaneController.getEmailFromDB("test"));
	}

	/**
	 * cleaning up things we use for testing
	 * 
	 * @see {@link MorphiaLoginConnector#getInstance()}
	 * @see {@link MorphiaLoginConnector}
	 * @see {@link MorphiaLoginConnector#getDataStore()}
	 */
	@After
	public void destroy() {
		final Query<User> deleteUser = MorphiaLoginConnector.getInstance().getDataStore().createQuery(User.class)
				.filter("name", "test");
		MorphiaLoginConnector.getInstance().getDataStore().delete(deleteUser);
	}

}
