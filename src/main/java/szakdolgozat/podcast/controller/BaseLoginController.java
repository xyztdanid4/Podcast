package szakdolgozat.podcast.controller;

import szakdolgozat.podcast.basicinformation.InformationContainer;

/**
 * The Class BaseLoginController.
 * 
 * @author Daniel Toth
 * @version 0.0.1
 * @since 0.0.1
 */
public class BaseLoginController {

	/**
	 * Instantiates a new base login controller.
	 */
	public BaseLoginController() {

	}

	/**
	 * Sets the DB owner.
	 *
	 * @param name
	 *            the new DB owner
	 */
	public void setDBowner(final String name) {
		InformationContainer.getInstance().setOwner(name);
	}

	/**
	 * Sets the email.
	 *
	 * @param mail
	 *            the new email
	 */
	public void setEmail(final String mail) {
		InformationContainer.getInstance().setMail(mail);
	}
}
