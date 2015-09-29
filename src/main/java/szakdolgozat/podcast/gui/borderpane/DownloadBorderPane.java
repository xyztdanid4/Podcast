package szakdolgozat.podcast.gui.borderpane;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import szakdolgozat.podcast.data.podcast.SearchResultContainer;
import szakdolgozat.podcast.gui.listbuilder.SearchListItemBuilder;
import szakdolgozat.podcast.gui.samples.BorderPaneSample;
import szakdolgozat.podcast.gui.samples.ButtonSample;
import szakdolgozat.podcast.gui.samples.HBoxSample;
import szakdolgozat.podcast.gui.samples.TextFieldSample;
import szakdolgozat.podcast.gui.samples.TreeViewSample;
import szakdolgozat.podcast.jsonparser.JsonParser;
import szakdolgozat.podcast.xmlparserrossz.RssFeedParser;

public class DownloadBorderPane extends BorderPaneSample {
	private HBoxSample hboxSample;
	private TreeItem<HBoxSample> rootItemHBoxSample;
	private TreeItem<HBoxSample> treeItemHBoxSample;
	private TreeView<HBoxSample> treeView;
	private TreeViewSample tre;
	private Text text;
	private TextFieldSample searchTextFieldSample;
	private final String SEARCHTEXTFIELD_PROMPTTEXT = "SEARCH ME!";
	private final String SEARCHTEXTFIELD_TOOLTIP = "Type something, you would like to podcast!";
	private final ButtonSample searchButtonSample;
	private final String SEARCHBUTTON_TOOLTIP = "Click me for search!";
	private final String SEARCHBUTTON_TEXT = "Search!";
	private final HBoxSample searchTextFieldSampleAndSearchButtonSampleContainerHbox;
	private final int SEARCHTEXTFIELD_WIDTH = 800;
	private SearchResultContainer searchResultContainer;
	private JsonParser jsonParser;
	private ObservableList<HBoxSample> searchResultList;
	private ListView searchResultlistView;
	private RssFeedParser rssFeedParser;

	public DownloadBorderPane() {
		searchTextFieldSample = new TextFieldSample(SEARCHTEXTFIELD_PROMPTTEXT, SEARCHTEXTFIELD_TOOLTIP);
		searchTextFieldSample.setPrefWidth(SEARCHTEXTFIELD_WIDTH);
		searchButtonSample = new ButtonSample(SEARCHBUTTON_TEXT, SEARCHBUTTON_TOOLTIP);
		searchTextFieldSampleAndSearchButtonSampleContainerHbox = new HBoxSample(searchTextFieldSample,
				searchButtonSample);
		setTop(searchTextFieldSampleAndSearchButtonSampleContainerHbox);

		setButtonDisability();
		setSearchButtonFunctionality();
		setSearchTextFieldKeyEvent();
		setPadding(new Insets(20, 20, 20, 20));
	}

	private void setSearchTextFieldKeyEvent() {
		searchTextFieldSample.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.ENTER) {
					searchButtonSample.fire();
				}
			}
		});
	}

	private void setButtonDisability() {
		searchButtonSample.disableProperty().bind(Bindings.isEmpty(searchTextFieldSample.textProperty()));
	}

	private void setSearchButtonFunctionality() {
		searchButtonSample.setOnAction((ActionEvent event) -> {
			startSearchPodcast();
		});
	}

	private void startSearchPodcast() {
		String searchText = "https://itunes.apple.com/search?term=" + searchTextFieldSample.getText()
				+ "&entity=podcast&media=podcast&limit=5";
		jsonParser = new JsonParser(searchText);
		searchResultContainer = jsonParser.getSearchResultContainer();
		// System.out.println(searchResultContainer);
		showSearchResult();
	}

	private void showSearchResult() {
		searchResultList = FXCollections.observableArrayList();
		rootItemHBoxSample = new TreeItem<HBoxSample>(new HBoxSample(new Label("root")));
		rootItemHBoxSample.setExpanded(true);
		for (int i = 0; i < searchResultContainer.getResults().size(); i++) {
			SearchListItemBuilder searchResultItem = new SearchListItemBuilder(searchResultContainer, i);
			treeItemHBoxSample = new TreeItem<HBoxSample>(searchResultItem.getSearchResultItem());
			rootItemHBoxSample.getChildren().add(treeItemHBoxSample);
			for (int j = 0; j < searchResultContainer.getResults().get(i).getPodcastEpisode().size(); j++) {
				Text title = new Text(searchResultContainer.getResults().get(i).getPodcastEpisode().get(j).getTitle());
				Text author = new Text(
						searchResultContainer.getResults().get(i).getPodcastEpisode().get(j).getAuthor());
				Text subtitle = new Text(
						searchResultContainer.getResults().get(i).getPodcastEpisode().get(j).getPubdate());
				ImageView imageView = new ImageView();
				Image image;
				try {
					image = new Image(searchResultContainer.getResults().get(i).getPodcastEpisode().get(j).getImage());
				} catch (Exception e) {
					e.printStackTrace();
					continue;
				}
				imageView.setImage(image);
				imageView.setFitHeight(30);
				imageView.setFitWidth(30);
				HBoxSample h = new HBoxSample(imageView, author, title, subtitle);
				TreeItem<HBoxSample> t = new TreeItem<HBoxSample>(h);
				treeItemHBoxSample.getChildren().add(t);
			}

		}
		treeView = new TreeViewSample(rootItemHBoxSample);
		setAlignment(treeView, Pos.CENTER_LEFT);
		treeView.setShowRoot(false);
		setCenter(treeView);
	}
}
