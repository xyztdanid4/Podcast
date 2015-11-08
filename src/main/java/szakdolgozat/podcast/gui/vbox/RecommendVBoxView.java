package szakdolgozat.podcast.gui.vbox;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import szakdolgozat.podcast.builder.HBoxBuilder;
import szakdolgozat.podcast.builder.ListViewBuilder;
import szakdolgozat.podcast.builder.TextBuilder;
import szakdolgozat.podcast.gui.decorator.RecommendListDecorator;

public class RecommendVBoxView extends VBox {
	private static final String RECOMMEND = "Similar podcast";
	final RecommendVBoxController recommendVBoxController;

	public RecommendVBoxView() {
		super(RecommendListDecorator.PADDING);
		this.recommendVBoxController = new RecommendVBoxController();

		final ObservableList<HBox> recommendList = FXCollections.observableArrayList();
		//-.-off
		for (final RecommendListItem item : this.recommendVBoxController.getRecommendListItems()) {
			recommendList.add(HBoxBuilder.create()
										.smallRectangle(item.getImage())
										.artist(item.getArtist())
										.build());
		}
		getChildren().addAll(TextBuilder.create()
										.bigText(RECOMMEND)
										.build(),
							ListViewBuilder.create()
											.items(recommendList)
											.size(RecommendListDecorator.LISTWIDTH, RecommendListDecorator.LISTHEIGHT)
											.build());
		
		//-.-on
		RecommendListDecorator.decorate(this);
		setPadding();

	}

	private void setPadding() {
		setPadding(new Insets(RecommendListDecorator.PADDING, RecommendListDecorator.PADDING,
				RecommendListDecorator.PADDING, RecommendListDecorator.PADDING));
	}
}
