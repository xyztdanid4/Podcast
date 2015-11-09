package szakdolgozat.podcast.gui.vbox;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import szakdolgozat.podcast.builder.HBoxBuilder;
import szakdolgozat.podcast.builder.ListViewBuilder;
import szakdolgozat.podcast.builder.TextBuilder;
import szakdolgozat.podcast.gui.borderpane.SearchBorderPaneView;
import szakdolgozat.podcast.gui.decorator.RecommendListDecorator;
import szakdolgozat.podcast.gui.tab.SearchTab;
import szakdolgozat.podcast.gui.tabpane.ApplicationTabPane;

public class RecommendVBoxView extends VBox {
	private static final String RECOMMEND = "Similar podcast";
	final RecommendVBoxController recommendVBoxController;

	public RecommendVBoxView() {
		super(RecommendListDecorator.PADDING);
		this.recommendVBoxController = new RecommendVBoxController();

		final ObservableList<HBox> recommendList = FXCollections.observableArrayList();
		//-.-off
		for (final RecommendListItem item : this.recommendVBoxController.getRecommendListItems()) {
			final HBox hboxitem = HBoxBuilder.create()
									.smallRectangle(item.getImage())
									.artist(item.getArtist())
									.effectOn()
									.build();
			
			hboxitem.setOnMousePressed(event -> {
				ApplicationTabPane.getInstance().getSelectionModel().select(0);
				SearchTab.getInstance().setContent(new SearchBorderPaneView(this.recommendVBoxController.getRecommendListItems()
						.get(this.recommendVBoxController.getRecommendListItems().indexOf(item)).getArtist()));
			});
			
			recommendList.add(hboxitem);
		}
		//-.-off
		final ListView<HBox> recommendListView =  ListViewBuilder.create()
															.items(recommendList)
															.size(RecommendListDecorator.LISTWIDTH, RecommendListDecorator.LISTHEIGHT)
															.build();
		
		getChildren().addAll(TextBuilder.create().bigText(RECOMMEND).build(), recommendListView);

		//-.-on
		RecommendListDecorator.decorate(this);
		setPadding();

	}

	private void setPadding() {
		setPadding(new Insets(RecommendListDecorator.PADDING, RecommendListDecorator.PADDING,
				RecommendListDecorator.PADDING, RecommendListDecorator.PADDING));
	}
}
