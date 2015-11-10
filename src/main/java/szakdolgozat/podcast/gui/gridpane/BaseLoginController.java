package szakdolgozat.podcast.gui.gridpane;

import szakdolgozat.podcast.basicinformation.InformationContainer;

public class BaseLoginController {
	public BaseLoginController() {

	}

	protected void setDBowner(final String name) {
		InformationContainer.getInstance().setOwner(name);
	}

	protected void setEmail(final String mail) {
		InformationContainer.getInstance().setMail(mail);
	}
}
