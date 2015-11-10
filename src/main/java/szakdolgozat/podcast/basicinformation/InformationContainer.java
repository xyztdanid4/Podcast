package szakdolgozat.podcast.basicinformation;

public class InformationContainer {
	private static String owner;
	private static InformationContainer instance = new InformationContainer();

	private InformationContainer() {
	}

	public static InformationContainer getInstance() {
		return instance;
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

}
