package szakdolgozat.podcast.gui.listbuilder;

import javafx.collections.ObservableList;
import szakdolgozat.podcast.data.podcast.SearchResultContainer;
import szakdolgozat.podcast.gui.samples.HBoxSample;

public class SearchListBuilder {
	public SearchListBuilder(ObservableList<HBoxSample> searchResultList,
			SearchResultContainer searchResultContainer) {
		for (int i = 0; i < searchResultContainer.getResults().size(); i++) {
			SearchListItemBuilder searchResutlItem = new SearchListItemBuilder(
					searchResultContainer, i);
			searchResultList.add(searchResutlItem.getSearchResultItem());
		}
	}
}
