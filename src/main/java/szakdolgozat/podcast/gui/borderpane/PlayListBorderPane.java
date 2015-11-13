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
import javafx.scene.layout.VBox;
import szakdolgozat.podcast.builder.SerialHboxBuilder;
import szakdolgozat.podcast.builder.SerialListViewBuilder;
import szakdolgozat.podcast.builder.SerialVBoxBuilder;
import szakdolgozat.podcast.data.podcast.Podcast;
import szakdolgozat.podcast.data.podcast.PodcastEpisode;
import szakdolgozat.podcast.gui.decorator.PlayListBPDecorator;
import szakdolgozat.podcast.gui.hbox.SerialHBox;
import szakdolgozat.podcast.morphia.MorphiaConnector;

public class PlayListBorderPane extends BorderPane {
	private static final String PLAY_LIST = "PlayList";
	private static final String EPISODES = "Episodes";
	static final DataFormat ITEM_LIST = new DataFormat("jdojo/itemlist");

	static ObservableList<SerialHBox> episodeList;
	static ListView<SerialHBox> episodeListView;

	static ObservableList<SerialHBox> playList;
	static ListView<SerialHBox> playListView;

	public PlayListBorderPane() {
		populateEpisodesList();
		populateEpisodesListView();
		//-.-off
		final VBox episodesVBox = SerialVBoxBuilder.create()
										.bigText(EPISODES)
										.noListView(episodeListView)
										.topLeftAlignment()
										.build();
		//-.-on
		setMargin(episodesVBox, new Insets(20));
		setLeft(episodesVBox);

		//-.-off
		populatePlayList();
		populatePlayListView();
		final VBox playListVBox = SerialVBoxBuilder.create()
											.bigText(PLAY_LIST)
											.noListView(playListView)
											.topLeftAlignment()
											.build();
		//-.-on
		setMargin(playListVBox, new Insets(20));
		setCenter(playListVBox);
		PlayListBPDecorator.decorateFactory(this);
		addDnDEventHanders();
	}

	private void populateEpisodesList() {
		PlayListBorderPane.episodeList = FXCollections.<SerialHBox> observableArrayList();
		for (final Podcast podcast : readfromDB()) {
			//-.-off
			for (final PodcastEpisode podcastEpisode : podcast.getPodcastEpisode()) {
				PlayListBorderPane.episodeList.add(SerialHboxBuilder.create()
						.artist(podcast.getArtistName())
						.smallText(" : ")
						.effectOn()
						.title(podcastEpisode.getTitle())
						.build());
			}
			//-.-on
		}
	}

	private void populateEpisodesListView() {
		episodeListView = SerialListViewBuilder.create()
				.size(PlayListBPDecorator.LISTVIEWWIDTH, PlayListBPDecorator.LISTVIEWHEIGHT).build();
		episodeListView.getItems().addAll(PlayListBorderPane.episodeList);
	}

	private void populatePlayList() {
		playList = FXCollections.<SerialHBox> observableArrayList();
	}

	private void populatePlayListView() {
		//-.-off
		playListView = SerialListViewBuilder.create()
									.size(PlayListBPDecorator.LISTVIEWWIDTH, PlayListBPDecorator.LISTVIEWHEIGHT)
									.build();
		playListView.getItems().addAll(playList);
		//-.-on
	}

	public List<Podcast> readfromDB() {
		return MorphiaConnector.getDataStore().createQuery(Podcast.class).asList();
	}

	private void addDnDEventHanders() {
		PlayListBorderPane.episodeListView.setOnDragDetected(e -> dragDetected(e, PlayListBorderPane.episodeListView));
		PlayListBorderPane.playListView.setOnDragDetected(e -> dragDetected(e, PlayListBorderPane.playListView));
		PlayListBorderPane.episodeListView.setOnDragOver(e -> dragOver(e, PlayListBorderPane.episodeListView));
		PlayListBorderPane.playListView.setOnDragOver(e -> dragOver(e, PlayListBorderPane.playListView));
		PlayListBorderPane.episodeListView.setOnDragDropped(e -> dragDropped(e, PlayListBorderPane.episodeListView));
		PlayListBorderPane.playListView.setOnDragDropped(e -> dragDropped(e, PlayListBorderPane.playListView));
		PlayListBorderPane.episodeListView.setOnDragDone(e -> dragDone(e, PlayListBorderPane.episodeListView));
		PlayListBorderPane.playListView.setOnDragDone(e -> dragDone(e, PlayListBorderPane.playListView));
	}

	private void dragDetected(final MouseEvent e, final ListView<SerialHBox> listView) {
		// Make sure at least one item is selected
		final int selectedCount = listView.getSelectionModel().getSelectedIndices().size();
		if (selectedCount == 0) {
			e.consume();
			return;
		}
		// Initiate a drag-and-drop gesture
		final Dragboard dragboard = listView.startDragAndDrop(TransferMode.COPY_OR_MOVE);
		// Put the the selected items to the dragboard
		final ArrayList<SerialHBox> selectedItems = this.getSelectedItems(listView);
		final ClipboardContent content = new ClipboardContent();
		content.put(ITEM_LIST, selectedItems);
		dragboard.setContent(content);
		e.consume();
	}

	private void dragOver(final DragEvent e, final ListView<SerialHBox> listView) {
		// If drag board has an ITEM_LIST and it is not being dragged
		// over itself, we accept the MOVE transfer mode
		final Dragboard dragboard = e.getDragboard();
		if (e.getGestureSource() != listView && dragboard.hasContent(ITEM_LIST)) {
			e.acceptTransferModes(TransferMode.COPY_OR_MOVE);
		}
		e.consume();
	}

	private void dragDropped(final DragEvent e, final ListView<SerialHBox> listView) {
		System.out.println("dragdropped");
		boolean dragCompleted = false;
		// Transfer the data to the target
		final Dragboard dragboard = e.getDragboard();
		if (dragboard.hasContent(ITEM_LIST)) {
			final ArrayList<SerialHBox> list = (ArrayList<SerialHBox>) dragboard.getContent(ITEM_LIST);
			listView.getItems().addAll(list);
			// Data transfer is successful
			dragCompleted = true;
		}
		// Data transfer is not successful
		e.setDropCompleted(dragCompleted);
		e.consume();
	}

	private void dragDone(final DragEvent e, final ListView<SerialHBox> listView) {
		// Check how data was transfered to the target
		// If it was moved, clear the selected items
		final TransferMode tm = e.getTransferMode();
		if (tm == TransferMode.MOVE) {
			removeSelectedItems(listView);
		}
		e.consume();
	}

	private ArrayList<SerialHBox> getSelectedItems(final ListView<SerialHBox> listView) {
		// Return the list of selected item in an ArratyList, so it is
		// serializable and can be stored in a Dragboard.
		final ArrayList<SerialHBox> list = new ArrayList<>(listView.getSelectionModel().getSelectedItems());
		return list;
	}

	private void removeSelectedItems(final ListView<SerialHBox> listView) {
		// Get all selected items in a separate list to avoid the shared list
		// issue
		final List<SerialHBox> selectedList = new ArrayList<>();
		for (final SerialHBox item : listView.getSelectionModel().getSelectedItems()) {
			selectedList.add(item);
		}
		// Clear the selection
		listView.getSelectionModel().clearSelection();
		// Remove items from the selected list
		listView.getItems().removeAll(selectedList);
	}

}
