package szakdolgozat.podcast.jsonparser;

import szakdolgozat.podcast.gui.dialog.OtherErrorDialog;

/**
 * The Class JsonParser.
 * 
 * @author Daniel Toth
 * @version 0.0.1
 * @since 0.0.1
 */
public abstract class JsonParser {

	/** The result. */
	protected String result;

	/**
	 * Instantiates a new json parser.
	 *
	 * @param searchText
	 *            the search text
	 */
	public JsonParser(String searchText) {
		final UrlReader urlReader = new UrlReader();
		searchText = searchText.replace(" ", "+");
		this.result = "";
		try {
			this.result = urlReader.readUrl(searchText);
		} catch (final Exception e) {
			e.printStackTrace();
			final OtherErrorDialog errorDialog = new OtherErrorDialog(
					new String("Error: cannot read Url for json parsing!"));
		}

	}

	/**
	 * Json to object.
	 */
	protected abstract void jsonToObject();

}
