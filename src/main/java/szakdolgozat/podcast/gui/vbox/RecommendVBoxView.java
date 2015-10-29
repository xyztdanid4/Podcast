package szakdolgozat.podcast.gui.vbox;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import szakdolgozat.podcast.builder.HBoxBuilder;
import szakdolgozat.podcast.gui.decorator.RecommendListDecorator;

public class RecommendVBoxView extends VBox {
	private static final String RECOMMEND = "Similar podcast";
	private ListView<HBox> recommendListView;
	private ObservableList<HBox> recommendList;
	private RecommendVBoxData recommendVBoxController;

	public RecommendVBoxView() {
		super(RecommendListDecorator.PADDING);
		recommendVBoxController = new RecommendVBoxData();
		recommendList = FXCollections.observableArrayList();
		recommendListView = new ListView<HBox>();
		RecommendListDecorator.decorateListViewFactory(recommendListView, RecommendListDecorator.LISTWIDTH,
				RecommendListDecorator.LISTHEIGHT);
		setMargin(recommendListView, new Insets(RecommendListDecorator.PADDING));

		for (RecommendListItem item : recommendVBoxController.getRecommendListItems()) {
			recommendList.add(HBoxBuilder.getInstance().build(item.getImage(), item.getArtist()));
			// TESZT MIATT
			// recommendList.add(ListItemBuilder.getInstance().build(item.getArtist()));
		}

		recommendListView.setItems(recommendList);
		Text recommendText = new Text(new String(RECOMMEND));
		RecommendListDecorator.decorateTextFactory(recommendText, RecommendListDecorator.BIGTEXTSIZE);
		recommendListView = new ListView<HBox>(FXCollections.observableArrayList(recommendList));
		RecommendListDecorator.decorateListViewFactory(recommendListView, RecommendListDecorator.LISTWIDTH,
				RecommendListDecorator.LISTHEIGHT);
		getChildren().addAll(recommendText, recommendListView);

		RecommendListDecorator.decorate(this);
		setPadding();
	}

	private void setPadding() {
		setPadding(new Insets(RecommendListDecorator.PADDING, RecommendListDecorator.PADDING,
				RecommendListDecorator.PADDING, RecommendListDecorator.PADDING));
	}
}
