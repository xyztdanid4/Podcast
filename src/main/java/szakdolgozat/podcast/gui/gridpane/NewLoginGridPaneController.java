package szakdolgozat.podcast.gui.gridpane;

import szakdolgozat.podcast.morphia.MorphiaLoginConnector;
import szakdolgozat.podcast.user.User;

public class NewLoginGridPaneController extends BaseLoginController {
	public NewLoginGridPaneController() {

	}

	public boolean checkValuesInDB(final String name, final String email) {
		return MorphiaLoginConnector.getDataStore().createQuery(User.class).filter("name = ", name).asList().isEmpty()
				&& MorphiaLoginConnector.getDataStore().createQuery(User.class).filter("email = ", email).asList()
						.isEmpty();
	}

	public void saveUser(final String name, final String password, final String email) {
		MorphiaLoginConnector.save(new User(name, password, email));
	}

}
