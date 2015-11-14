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
import szakdolgozat.podcast.builder.SerialListViewBuilder;
import szakdolgozat.podcast.builder.SerialVBoxBuilder;
import szakdolgozat.podcast.data.podcast.Podcast;
import szakdolgozat.podcast.data.podcast.PodcastEpisode;
import szakdolgozat.podcast.gui.decorator.PlayListBPDecorator;
import szakdolgozat.podcast.morphia.MorphiaConnector;

public class PlayListBorderPane extends BorderPane {
	private static final String PLAY_LIST = "PlayList";
	private static final String EPISODES = "Episodes";
	static final DataFormat ITEM_LIST = new DataFormat("jdojo/itemlist");

	static ObservableList<PodcastEpisode> episodeList;
	static ListView<PodcastEpisode> episodeListView;

	static ObservableList<PodcastEpisode> playList;
	static ListView<PodcastEpisode> playListView;

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
		PlayListBorderPane.episodeList = FXCollections.<PodcastEpisode> observableArrayList();
		for (final Podcast podcast : readfromDB()) {
			for (final PodcastEpisode podcastEpisode : podcast.getPodcastEpisode()) {
				episodeList.add(podcastEpisode);
			}
		}
	}

	private void populateEpisodesListView() {
		episodeListView = SerialListViewBuilder.create()
				.size(PlayListBPDecorator.LISTVIEWWIDTH, PlayListBPDecorator.LISTVIEWHEIGHT).build();
		episodeListView.getItems().addAll(PlayListBorderPane.episodeList);
	}

	private void populatePlayList() {
		playList = FXCollections.<PodcastEpisode> observableArrayList();
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

	private void dragDetected(final MouseEvent e, final ListView<PodcastEpisode> listView) {
		final int selectedCount = listView.getSelectionModel().getSelectedIndices().size();
		if (selectedCount == 0) {
			e.consume();
			return;
		}
		final Dragboard dragboard = listView.startDragAndDrop(TransferMode.COPY_OR_MOVE);
		final ArrayList<PodcastEpisode> selectedItems = this.getSelectedItems(listView);
		final ClipboardContent content = new ClipboardContent();
		content.put(ITEM_LIST, selectedItems);
		dragboard.setContent(content);
		e.consume();
	}

	private void dragOver(final DragEvent e, final ListView<PodcastEpisode> listView) {
		final Dragboard dragboard = e.getDragboard();
		if (e.getGestureSource() != listView && dragboard.hasContent(ITEM_LIST)) {
			e.acceptTransferModes(TransferMode.COPY_OR_MOVE);
		}
		e.consume();
	}

	private void dragDropped(final DragEvent e, final ListView<PodcastEpisode> listView) {
		System.out.println("dragdropped");
		boolean dragCompleted = false;
		final Dragboard dragboard = e.getDragboard();
		if (dragboard.hasContent(ITEM_LIST)) {
			final ArrayList<PodcastEpisode> list = (ArrayList<PodcastEpisode>) dragboard.getContent(ITEM_LIST);
			listView.getItems().addAll(list);
			dragCompleted = true;
		}
		e.setDropCompleted(dragCompleted);
		e.consume();
	}

	private void dragDone(final DragEvent e, final ListView<PodcastEpisode> listView) {
		final TransferMode tm = e.getTransferMode();
		if (tm == TransferMode.MOVE) {
			removeSelectedItems(listView);
		}
		e.consume();
	}

	private ArrayList<PodcastEpisode> getSelectedItems(final ListView<PodcastEpisode> listView) {
		final ArrayList<PodcastEpisode> list = new ArrayList<PodcastEpisode>(
				listView.getSelectionModel().getSelectedItems());
		return list;
	}

	private void removeSelectedItems(final ListView<PodcastEpisode> listView) {
		final List<PodcastEpisode> selectedList = new ArrayList<PodcastEpisode>();
		for (final PodcastEpisode item : listView.getSelectionModel().getSelectedItems()) {
			selectedList.add(item);
		}
		listView.getSelectionModel().clearSelection();
		listView.getItems().removeAll(selectedList);
	}

}
