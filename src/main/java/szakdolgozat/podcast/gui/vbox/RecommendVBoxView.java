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
	// private ListView<HBox> recommendListView;
	// private ObservableList<HBox> recommendList;
	// private RecommendVBoxController recommendVBoxController;

	public RecommendVBoxView() {
		super(RecommendListDecorator.PADDING);
		RecommendVBoxController recommendVBoxController = new RecommendVBoxController();
		ObservableList<HBox> recommendList = FXCollections.observableArrayList();
		// RecommendListDecorator.decorateListViewFactory(recommendListView,
		// RecommendListDecorator.LISTWIDTH,
		// RecommendListDecorator.LISTHEIGHT);
		for (RecommendListItem item : recommendVBoxController.getRecommendListItems()) {
			recommendList.add(HBoxBuilder.create().image(item.getImage()).artist(item.getArtist()).build());
			// TESZT MIATT
			// recommendList.add(ListItemBuilder.getInstance().build(item.getArtist()));
		}

		ListView<HBox> recommendListView = RecommendListDecorator.decorateListViewFactory(
				new ListView<HBox>(FXCollections.observableArrayList(recommendList)), RecommendListDecorator.LISTWIDTH,
				RecommendListDecorator.LISTHEIGHT);
		setMargin(recommendListView, new Insets(RecommendListDecorator.PADDING));

		/*
		 * Text recommendText = RecommendListDecorator.decorateTextFactory(new
		 * Text(new String(RECOMMEND)), RecommendListDecorator.BIGTEXTSIZE);
		 */

		getChildren().addAll(RecommendListDecorator.decorateTextFactory(new Text(new String(RECOMMEND)),
				RecommendListDecorator.BIGTEXTSIZE), recommendListView);

		RecommendListDecorator.decorate(this);
		setPadding();
	}

	private void setPadding() {
		setPadding(new Insets(RecommendListDecorator.PADDING, RecommendListDecorator.PADDING,
				RecommendListDecorator.PADDING, RecommendListDecorator.PADDING));
	}
}
