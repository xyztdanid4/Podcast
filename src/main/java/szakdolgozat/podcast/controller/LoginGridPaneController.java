package szakdolgozat.podcast.controller;

import szakdolgozat.podcast.morphia.MorphiaLoginConnector;
import szakdolgozat.podcast.user.User;

/**
 * The Class LoginGridPaneController.
 * 
 * @author Daniel Toth
 * @version 0.0.1
 * @since 0.0.1
 */
public class LoginGridPaneController extends BaseLoginController {

	/**
	 * Instantiates a new login grid pane controller.
	 */
	public LoginGridPaneController() {

	}

	/**
	 * Check user and password.
	 *
	 * @param name
	 *            the name
	 * @param password
	 *            the password
	 * @return true, if successful
	 */
	public boolean checkUserAndPassword(final String name, final String password) {
		return !(MorphiaLoginConnector.getInstance().getDataStore().createQuery(User.class).filter("name = ", name)
				.filter("password = ", password).asList().isEmpty());

	}

	/**
	 * Gets the email from db.
	 *
	 * @param name
	 *            the name
	 * @return the email from db
	 */
	public String getEmailFromDB(final String name) {
		return MorphiaLoginConnector.getInstance().getDataStore().createQuery(User.class).filter("name = ", name)
				.asList().get(0).getEmail();
	}

}
