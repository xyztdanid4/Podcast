package szakdolgozat.podcast.data.basicinformation;

/**
 * The Class InformationContainer.
 *
 * @author Daniel Toth
 * @version 0.0.1
 * @since 0.0.1
 * 
 *        This class represents data, which is needed everywhere in the
 *        application. Singleton class.
 * 
 */

public class InformationContainer {

	/**
	 * owner, this field represents the owner of the program, it is needed when
	 * we connect, or create the database, owner will be the database name.
	 */
	private static String owner;

	/**
	 * mail, this field is represents the mail address which is used when we
	 * would like to notify the user through email.
	 */
	private static String mail;

	/**
	 * instance, this field is represents the only instance of this object. We
	 * can only reach the object's methods through this.
	 */
	private static InformationContainer instance = new InformationContainer();

	/**
	 * isEmailRequired, boolean variable, which is used for decide if we should
	 * send email as notification.
	 */
	private static boolean isEmailRequired;

	/** updateFrequency, represents the update frequency in hours. */
	private static int updateFrequency;

	/**
	 * home, this variable contains the information, where we would like to
	 * download the contents which is related with our podcast.
	 */
	private static String home = System.getProperty("user.home");

	/**
	 * private CTOR cause of singleton class, currently it is empty, doing
	 * nothing.
	 */
	private InformationContainer() {

	}

	/**
	 * Getter for instance. Important method, cause we can only reach methods
	 * through this.
	 * 
	 * @return instance
	 */
	public static InformationContainer getInstance() {
		if (instance == null) {
			instance = new InformationContainer();
		}
		return instance;
	}

	/**
	 * Getter for home field.
	 * 
	 * @return home
	 * 
	 */
	public String getHome() {
		return home;
	}

	/**
	 * Getter for the owner field.
	 * 
	 * @return owner
	 */
	public String getOwner() {
		return owner;
	}

	/**
	 * Sets the owner.
	 *
	 * @param owner
	 * 
	 */
	public void setOwner(final String owner) {
		InformationContainer.owner = owner;
	}

	/**
	 * Sets the instance.
	 *
	 * @param instance
	 */
	public static void setInstance(final InformationContainer instance) {
		InformationContainer.instance = instance;
	}

	/**
	 * Getter for mail field.
	 * 
	 * @return mail
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * Sets the home.
	 *
	 * @param home
	 * 
	 */
	public void setHome(final String home) {
		InformationContainer.home = home;
	}

	/**
	 * Sets the mail.
	 *
	 * @param mail
	 *
	 */
	public void setMail(final String mail) {
		InformationContainer.mail = mail;
	}

	/**
	 * Sets the email required.
	 *
	 * @param isEmailRequired
	 *
	 */
	public void setEmailRequired(final boolean isEmailRequired) {
		InformationContainer.isEmailRequired = isEmailRequired;
	}

	/**
	 * Checks if is email required.
	 *
	 * @return true, if is email required
	 */
	public boolean isEmailRequired() {
		return isEmailRequired;
	}

	/**
	 * Gets the update frequency.
	 *
	 * @return updateFrequency
	 */
	public int getUpdateFrequency() {
		return updateFrequency;
	}

	/**
	 * Sets the update frequency.
	 *
	 * @param updateFrequency
	 * 
	 */
	public void setUpdateFrequency(final int updateFrequency) {
		InformationContainer.updateFrequency = updateFrequency;
	}

}
