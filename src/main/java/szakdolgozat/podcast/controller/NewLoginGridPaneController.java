package szakdolgozat.podcast.controller;

import szakdolgozat.podcast.morphia.MorphiaLoginConnector;
import szakdolgozat.podcast.user.User;

/**
 * The Class NewLoginGridPaneController.
 * 
 * @author Daniel Toth
 * @version 0.0.1
 * @since 0.0.1
 */
public class NewLoginGridPaneController extends BaseLoginController {

	/**
	 * Instantiates a new new login grid pane controller.
	 */
	public NewLoginGridPaneController() {

	}

	/**
	 * Check values in db.
	 *
	 * @param name
	 *            the name
	 * @param email
	 *            the email
	 * @return true, if successful
	 */
	public boolean checkValuesInDB(final String name, final String email) {
		return MorphiaLoginConnector.getInstance().getDataStore().createQuery(User.class).filter("name = ", name)
				.asList().isEmpty()
				&& MorphiaLoginConnector.getInstance().getDataStore().createQuery(User.class).filter("email = ", email)
						.asList().isEmpty();
	}

	/**
	 * Save user.
	 *
	 * @param name
	 *            the name
	 * @param password
	 *            the password
	 * @param email
	 *            the email
	 */
	public void saveUser(final String name, final String password, final String email) {
		MorphiaLoginConnector.getInstance().save(new User(name, password, email));
	}

}
