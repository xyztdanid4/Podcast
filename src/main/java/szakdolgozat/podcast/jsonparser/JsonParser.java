package szakdolgozat.podcast.jsonparser;

import szakdolgozat.podcast.gui.dialog.OtherErrorDialog;

public abstract class JsonParser {
	protected String result;

	public JsonParser(String searchText) {
		UrlReader urlReader = new UrlReader();
		searchText = searchText.replace(" ", "+");
		result = "";
		try {
			result = urlReader.readUrl(searchText);
		} catch (Exception e) {
			e.printStackTrace();
			OtherErrorDialog errorDialog = new OtherErrorDialog(new String("Error: cannot read Url for json parsing!"));
		}

	}

	protected abstract void jsonToObject();

}
