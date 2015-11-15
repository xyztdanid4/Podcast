package szakdolgozat.podcast.basicinformation;

public class InformationContainer {
	private static String owner;
	private static String mail;
	private static InformationContainer instance = new InformationContainer();
	private static boolean isEmailRequired;
	private static int updateFrequency;
	private static String home = System.getProperty("user.home");

	private InformationContainer() {
	}

	public static InformationContainer getInstance() {
		return instance;
	}

	public String getHome() {
		return home;
	}

	/**
	 * @return the owner
	 */
	public String getOwner() {
		return owner;
	}

	/**
	 * @param owner
	 *            the owner to set
	 */
	public void setOwner(final String owner) {
		InformationContainer.owner = owner;
	}

	/**
	 * @param instance
	 *            the instance to set
	 */
	public static void setInstance(final InformationContainer instance) {
		InformationContainer.instance = instance;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(final String mail) {
		InformationContainer.mail = mail;
	}

	public void setEmailRequired(final boolean isEmailRequired) {
		InformationContainer.isEmailRequired = isEmailRequired;
	}

	public boolean isEmailRequired() {
		return isEmailRequired;
	}

	public static int getUpdateFrequency() {
		return updateFrequency;
	}

	public void setUpdateFrequency(final int updateFrequency) {
		InformationContainer.updateFrequency = updateFrequency;
	}

}
