package szakdolgozat.podcast.gui.gridpane;

import szakdolgozat.podcast.morphia.MorphiaLoginConnector;
import szakdolgozat.podcast.user.User;

public class LoginGridPaneController extends BaseLoginController {
	public LoginGridPaneController() {

	}

	public boolean checkUserAndPassword(final String name, final String password) {
		return !(MorphiaLoginConnector.getDataStore().createQuery(User.class).filter("name = ", name)
				.filter("password = ", password).asList().isEmpty());
	}

	public String getEmailFromDB(final String name) {
		return MorphiaLoginConnector.getDataStore().createQuery(User.class).filter("name = ", name).asList().get(0)
				.getEmail();
	}

}
