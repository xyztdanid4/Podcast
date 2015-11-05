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

	public RecommendVBoxView() {
		super(RecommendListDecorator.PADDING);
		RecommendVBoxController recommendVBoxController = new RecommendVBoxController();
		ObservableList<HBox> recommendList = FXCollections.observableArrayList();
		for (RecommendListItem item : recommendVBoxController.getRecommendListItems()) {
			recommendList.add(HBoxBuilder.create().smallRectangle(item.getImage()).artist(item.getArtist()).build());
		}

		getChildren().addAll(
				RecommendListDecorator.decorateTextFactory(new Text(new String(RECOMMEND)),
						RecommendListDecorator.BIGTEXTSIZE),
				RecommendListDecorator.decorateListViewFactory(
						new ListView<HBox>(FXCollections.observableArrayList(recommendList)),
						RecommendListDecorator.LISTWIDTH, RecommendListDecorator.LISTHEIGHT));

		RecommendListDecorator.decorate(this);
		setPadding();
	}

	private void setPadding() {
		setPadding(new Insets(RecommendListDecorator.PADDING, RecommendListDecorator.PADDING,
				RecommendListDecorator.PADDING, RecommendListDecorator.PADDING));
	}
}
