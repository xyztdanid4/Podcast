package szakdolgozat.podcast.gui.borderpane;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.ListView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import szakdolgozat.podcast.builder.HBoxBuilder;
import szakdolgozat.podcast.builder.ListViewBuilder;
import szakdolgozat.podcast.builder.VBoxBuilder;
import szakdolgozat.podcast.data.podcast.Podcast;
import szakdolgozat.podcast.data.podcast.PodcastEpisode;
import szakdolgozat.podcast.gui.decorator.PlayListBPDecorator;
import szakdolgozat.podcast.morphia.MorphiaConnector;

public class PlayListBorderPane extends BorderPane {
	private static final String PLAY_LIST = "PlayList";
	private static final String EPISODES = "Episodes";

	public PlayListBorderPane() {
		final ObservableList<HBox> episodesList = FXCollections.observableArrayList();
		for (final Podcast podcast : readfromDB()) {
			//-.-off
			for (final PodcastEpisode podcastEpisode : podcast.getPodcastEpisode()) {
				episodesList.add(HBoxBuilder.create()
						.artist(podcast.getArtistName())
						.smallText(" : ")
						.effectOn()
						.title(podcastEpisode.getTitle())
						.build());
			}
			//-.-on
		}
		//-.-off
		final ListView<HBox> episodeListView = ListViewBuilder.create()
															.items(episodesList)
															.size(PlayListBPDecorator.LISTVIEWWIDTH, PlayListBPDecorator.LISTVIEWHEIGHT)
															.build();
		final VBox episodesVBox = VBoxBuilder.create()
										.bigText(EPISODES)
										.noListView(episodeListView)
										.topLeftAlignment()
										.build();
		
		setMargin(episodesVBox, new Insets(20));
		//-.-on
		setLeft(episodesVBox);

		//-.-off
		final ObservableList<HBox> playList = FXCollections.observableArrayList();
		final ListView<HBox> playListView = ListViewBuilder.create()
															.size(PlayListBPDecorator.LISTVIEWWIDTH, PlayListBPDecorator.LISTVIEWHEIGHT)
															.build();
		
		final VBox playListVBox = VBoxBuilder.create()
											.bigText(PLAY_LIST)
											.noListView(playListView)
											.topLeftAlignment()
											.build();
		setMargin(playListVBox, new Insets(20));
		//-.-on
		setCenter(playListVBox);
		PlayListBPDecorator.decorateFactory(this);

		episodeListView.setOnDragDetected(e -> dragDetected(e, episodeListView));
		playListView.setOnDragDetected(e -> dragDetected(e, playListView));
		episodeListView.setOnDragOver(e -> dragOver(e, episodeListView));
		playListView.setOnDragOver(e -> dragOver(e, playListView));
		episodeListView.setOnDragDropped(e -> dragDropped(e, episodeListView));
		playListView.setOnDragDropped(e -> dragDropped(e, playListView));
		episodeListView.setOnDragDone(e -> dragDone(e, episodeListView));
		playListView.setOnDragDone(e -> dragDone(e, playListView));
	}

	private void dragDetected(final MouseEvent e, final ListView<HBox> listView) {
		// Make sure at least one item is selected
		final int selectedCount = listView.getSelectionModel().getSelectedIndices().size();
		if (selectedCount == 0) {
			e.consume();
			return;
		}
		// Initiate a drag-and-drop gesture
		final Dragboard dragboard = listView.startDragAndDrop(TransferMode.COPY_OR_MOVE);
		// Put the the selected items to the dragboard
		final ArrayList<HBox> selectedItems = this.getSelectedItems(listView);
		final ClipboardContent content = new ClipboardContent();
		content.put(new DataFormat(), selectedItems);
		dragboard.setContent(content);
		e.consume();
	}

	private void dragOver(final DragEvent e, final ListView<HBox> listView) {
		// If drag board has an ITEM_LIST and it is not being dragged
		// over itself, we accept the MOVE transfer mode
		final Dragboard dragboard = e.getDragboard();
		if (e.getGestureSource() != listView && dragboard.hasContent(new DataFormat())) {
			e.acceptTransferModes(TransferMode.COPY_OR_MOVE);
		}
		e.consume();
	}

	private void dragDropped(final DragEvent e, final ListView<HBox> listView) {
		boolean dragCompleted = false;
		// Transfer the data to the target
		final Dragboard dragboard = e.getDragboard();
		if (dragboard.hasContent(new DataFormat())) {
			final ArrayList<HBox> list = (ArrayList<HBox>) dragboard.getContent(new DataFormat());
			listView.getItems().addAll(list);
			// Data transfer is successful
			dragCompleted = true;
		}
		// Data transfer is not successful
		e.setDropCompleted(dragCompleted);
		e.consume();
	}

	private void dragDone(final DragEvent e, final ListView<HBox> listView) {
		// Check how data was transfered to the target
		// If it was moved, clear the selected items
		final TransferMode tm = e.getTransferMode();
		if (tm == TransferMode.MOVE) {
			removeSelectedItems(listView);
		}
		e.consume();
	}

	private ArrayList<HBox> getSelectedItems(final ListView<HBox> listView) {
		// Return the list of selected item in an ArratyList, so it is
		// serializable and can be stored in a Dragboard.
		final ArrayList<HBox> list = new ArrayList<>(listView.getSelectionModel().getSelectedItems());
		return list;
	}

	private void removeSelectedItems(final ListView<HBox> listView) {
		// Get all selected items in a separate list to avoid the shared list
		// issue
		final List<HBox> selectedList = new ArrayList<>();
		for (final HBox item : listView.getSelectionModel().getSelectedItems()) {
			selectedList.add(item);
		}
		// Clear the selection
		listView.getSelectionModel().clearSelection();
		// Remove items from the selected list
		listView.getItems().removeAll(selectedList);
	}

	public List<Podcast> readfromDB() {
		return MorphiaConnector.getDataStore().createQuery(Podcast.class).asList();
	}

}
