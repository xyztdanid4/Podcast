package szakdolgozat.podcast.gui.listbuilder;

import javafx.collections.ObservableList;
import javafx.scene.layout.HBox;
import szakdolgozat.podcast.data.podcast.PodcastContainer;

public class SearchListBuilder {
	public SearchListBuilder(ObservableList<HBox> searchResultList, PodcastContainer searchResultContainer) {
		for (int i = 0; i < searchResultContainer.getResults().size(); i++) {
			SearchListItemBuilder searchResutlItem = new SearchListItemBuilder(searchResultContainer, i);
			searchResultList.add(searchResutlItem.getSearchResultItem());
		}
	}
}
