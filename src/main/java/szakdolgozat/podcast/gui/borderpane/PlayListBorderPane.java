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
	static ObservableList<SerialHBox> playList;
	static ListView<SerialHBox> playListView;
	static ListView<SerialHBox> episodeListView;
	static ObservableList<SerialHBox> episodesList;

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

		setDragAndDropEvents();

		// episodeListView.setOnDragDetected(e -> dragDetected(e,
		// episodeListView));
		// playListView.setOnDragDetected(e -> dragDetected(e, playListView));
		// episodeListView.setOnDragOver(e -> dragOver(e, episodeListView));
		// playListView.setOnDragOver(e -> dragOver(e, playListView));
		// episodeListView.setOnDragDropped(e -> dragDropped(e,
		// episodeListView));
		// playListView.setOnDragDropped(e -> dragDropped(e, playListView));
		// episodeListView.setOnDragDone(e -> dragDone(e, episodeListView));
		// playListView.setOnDragDone(e -> dragDone(e, playListView));
	}

	/**
	 * 
	 */
	private void setDragAndDropEvents() {
		episodeListView.setOnDragDetected(event -> {
			final int selectedCount = episodeListView.getSelectionModel().getSelectedIndices().size();
			if (selectedCount == 0) {
				event.consume();
				return;
			}
			final Dragboard dragboard = episodeListView.startDragAndDrop(TransferMode.COPY_OR_MOVE);
			final ArrayList<SerialHBox> selectedItems = this.getSelectedItems(episodeListView);
			final ClipboardContent content = new ClipboardContent();
			content.put(ITEM_LIST, selectedItems);
			dragboard.setContent(content);
			event.consume();
		});

		playListView.setOnDragDetected(event -> {
			final int selectedCount = playListView.getSelectionModel().getSelectedIndices().size();
			if (selectedCount == 0) {
				event.consume();
				return;
			}
			final Dragboard dragboard = playListView.startDragAndDrop(TransferMode.COPY_OR_MOVE);
			final ArrayList<SerialHBox> selectedItems = this.getSelectedItems(playListView);
			final ClipboardContent content = new ClipboardContent();
			content.put(ITEM_LIST, selectedItems);
			dragboard.setContent(content);
			event.consume();
		});

		episodeListView.setOnDragOver(event -> {
			final Dragboard dragboard = event.getDragboard();
			if (event.getGestureSource() != episodeListView && dragboard.hasContent(ITEM_LIST)) {
				event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
			}
			event.consume();
		});

		playListView.setOnDragOver(event -> {
			final Dragboard dragboard = event.getDragboard();
			if (event.getGestureSource() != playListView && dragboard.hasContent(ITEM_LIST)) {
				event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
			}
			event.consume();
		});

		episodeListView.setOnDragDropped(event -> {
			boolean dragCompleted = false;
			final Dragboard dragboard = event.getDragboard();
			if (dragboard.hasContent(ITEM_LIST)) {
				final ArrayList<SerialHBox> list = (ArrayList<SerialHBox>) dragboard.getContent(ITEM_LIST);
				// episodeListView.getItems().addAll(list);
				episodesList.addAll(list);
				episodeListView.setItems(episodesList);
				dragCompleted = true;
			}
			event.setDropCompleted(dragCompleted);
			event.consume();
		});

		playListView.setOnDragDropped(event -> {
			boolean dragCompleted = false;
			final Dragboard dragboard = event.getDragboard();
			if (dragboard.hasContent(ITEM_LIST)) {
				final ArrayList<SerialHBox> list = (ArrayList<SerialHBox>) dragboard.getContent(ITEM_LIST);
				// playListView.getItems().addAll(list);
				playList.addAll(list);
				playListView.setItems(episodesList);
				dragCompleted = true;
			}
			event.setDropCompleted(dragCompleted);
			event.consume();
		});

		episodeListView.setOnDragDone(event -> {
			final TransferMode tm = event.getTransferMode();
			if (tm == TransferMode.MOVE) {
				removeSelectedItems(episodeListView);
			}
			event.consume();
		});

		playListView.setOnDragDone(event -> {
			final TransferMode tm = event.getTransferMode();
			if (tm == TransferMode.MOVE) {
				removeSelectedItems(playListView);
			}
			event.consume();
		});
	}

	/**
	 * 
	 */
	private void populatePlayListView() {
		//-.-off
		playListView = SerialListViewBuilder.create()
											.size(PlayListBPDecorator.LISTVIEWWIDTH, PlayListBPDecorator.LISTVIEWHEIGHT)
											.items(playList)
											.build();
		//-.-on
	}

	/**
	 * 
	 */
	private void populatePlayList() {
		playList = FXCollections.observableArrayList();
	}

	/**
	 * 
	 */
	private void populateEpisodesList() {
		episodesList = FXCollections.observableArrayList();
		for (final Podcast podcast : readfromDB()) {
			//-.-off
			for (final PodcastEpisode podcastEpisode : podcast.getPodcastEpisode()) {
				episodesList.add(SerialHboxBuilder.create()
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
		//-.-off
		episodeListView = SerialListViewBuilder.create()
												.items(episodesList)
												.size(PlayListBPDecorator.LISTVIEWWIDTH, PlayListBPDecorator.LISTVIEWHEIGHT)
												.build();
		//-.-on
	}

	private void dragDetected(final MouseEvent e, final ListView<SerialHBox> listView) {
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
		playList.add(SerialHboxBuilder.create().smallText("aaaa").build());
		playListView.setItems(playList);

		boolean dragCompleted = false;
		// Transfer the data to the target
		final Dragboard dragboard = e.getDragboard();
		System.out.println("dragdropped");
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

	public List<Podcast> readfromDB() {
		return MorphiaConnector.getDataStore().createQuery(Podcast.class).asList();
	}

}
