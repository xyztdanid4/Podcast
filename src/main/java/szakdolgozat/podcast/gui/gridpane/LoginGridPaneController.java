package szakdolgozat.podcast.gui.gridpane;

import szakdolgozat.podcast.morphia.MorphiaLoginConnector;
import szakdolgozat.podcast.user.User;

public class LoginGridPaneController {
	public LoginGridPaneController() {

	}

	public boolean checkUserAndPassword(final String name, final String password) {
		return !(MorphiaLoginConnector.getDataStore().createQuery(User.class).filter("name = ", name)
				.filter("password = ", password).asList().isEmpty());
	}
}
