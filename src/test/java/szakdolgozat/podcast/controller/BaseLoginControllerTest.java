package szakdolgozat.podcast.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import szakdolgozat.podcast.data.basicinformation.InformationContainer;

/**
 * @author Daniel Toth
 * @version 0.0.1
 * @since 0.0.1
 */

public class BaseLoginControllerTest {
	BaseLoginController baseLoginController;

	@Before
	public void create() {
		this.baseLoginController = new BaseLoginController();
		InformationContainer.getInstance();
	}

	/**
	 * test for creating the object
	 * 
	 * @see {@link BaseLoginController}
	 */
	@Test
	public void BaseLoginControllerInstanceTest() {
		assertNotNull(this.baseLoginController);
	}

	/**
	 * test for creating the object
	 * 
	 * @see {@link InformationContainer}
	 * @see {@link InformationContainer#getInstance()}
	 */
	@Test
	public void informationContainerInstanceTest() {
		assertNotNull(InformationContainer.getInstance());
	}

	/**
	 * test for settings after did the setting
	 * 
	 * @see {@link BaseLoginController#setDBowner(String)}
	 * @see {@link InformationContainer#getOwner()}
	 */
	@Test
	public void setterTestForOwner() {
		this.baseLoginController.setDBowner("test");
		assertEquals("test", InformationContainer.getInstance().getOwner());
	}

	/**
	 * test for settings after did the setting
	 * 
	 * @see {@link BaseLoginController#setEmail(String)}
	 * @see {@link InformationContainer#getMail()}
	 */
	@Test
	public void setterTestForEmail() {
		this.baseLoginController.setEmail("test");
		assertEquals("test", InformationContainer.getInstance().getMail());
	}

}
