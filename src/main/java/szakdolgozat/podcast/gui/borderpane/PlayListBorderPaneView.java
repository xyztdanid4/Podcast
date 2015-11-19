package szakdolgozat.podcast.gui.borderpane;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import szakdolgozat.podcast.controller.PlayListController;
import szakdolgozat.podcast.data.podcast.Podcast;
import szakdolgozat.podcast.data.podcast.PodcastEpisode;
import szakdolgozat.podcast.gui.builder.SerialHboxBuilder;
import szakdolgozat.podcast.gui.builder.SerialListViewBuilder;
import szakdolgozat.podcast.gui.builder.SerialVBoxBuilder;
import szakdolgozat.podcast.gui.decorator.PlayListBPDecorator;
import szakdolgozat.podcast.gui.hbox.SerialHBox;

public class PlayListBorderPaneView extends BorderPane {
	private static final String PLAY_LIST = "PlayList";
	private static final String EPISODES = "Episodes";
	private static final DataFormat ITEM_LIST = new DataFormat("jdojo/itemlist");
	private static ListView<SerialHBox> realEpisodeListView;
	private static ListView<SerialHBox> realPlayListView;
	private final VBox playListVBox;
	private final VBox episodesVBox;

	public PlayListBorderPaneView() {
		populateEpisodesList();
		populateEpisodesListView();
		//-.-off
		this.episodesVBox = SerialVBoxBuilder.create()
										.bigText(EPISODES)
										.noListView(realEpisodeListView)
										.topLeftAlignment()
										.build();
		//-.-on
		setLeft(this.episodesVBox);

		//-.-off
		populatePlayList();
		populatePlayListView();
		this.playListVBox = SerialVBoxBuilder.create()
											.bigText(PLAY_LIST)
											.noListView(realPlayListView)
											.topLeftAlignment()
											.build();
		//-.-on
		setCenter(this.playListVBox);
		decorate();
		setSelectionMode();
		setRealEpisodeListViewSetOnDragEvent();
		setRealPlayListViewSetOnDragEvent();
		setRealEpisodeListViewSetOnDragOverEvent();
		setRealPlayListViewDragOverEvent();
		setRealEpisodeListViewSetOnDragDroppedEvent();
		setRealPlayListViewSetOnDragDroppedEvent();
		setRealEpisodeListViewOnDragDoneEvent();
		setRealPlayListViewOnDragDoneEvent();
	}

	/**
	 * 
	 */
	private void setRealPlayListViewOnDragDoneEvent() {
		realPlayListView.setOnDragDone(e -> {
			final TransferMode tm = e.getTransferMode();
			if (tm == TransferMode.MOVE) {

			}
			e.consume();
		});
	}

	/**
	 * 
	 */
	private void setRealEpisodeListViewOnDragDoneEvent() {
		realEpisodeListView.setOnDragDone(e -> {
			final TransferMode tm = e.getTransferMode();
			if (tm == TransferMode.MOVE) {

			}
			e.consume();
		});
	}

	/**
	 * 
	 */
	private void setRealPlayListViewSetOnDragDroppedEvent() {
		realPlayListView.setOnDragDropped(event -> {
			boolean dragCompleted = false;
			final Dragboard dragboard = event.getDragboard();
			if (dragboard.hasContent(ITEM_LIST)) {
				final PodcastEpisode podcastEpisode = (PodcastEpisode) dragboard.getContent(ITEM_LIST);
				// playList.add(podcastEpisode);
				PlayListController.getInstance().getPlayList().add(podcastEpisode);
				final SerialHBox realPodcastEpisode = SerialHboxBuilder.create().artist(podcastEpisode.getAuthor())
						.title(podcastEpisode.getTitle()).build();
				realPlayListView.getItems().add(realPodcastEpisode);
				// final int index =
				// PlayListBorderPaneView.episodeList.indexOf(podcastEpisode);
				final int index = PlayListController.getInstance().getEpisodeList().indexOf(podcastEpisode);
				// episodeList.remove(podcastEpisode);
				PlayListController.getInstance().getEpisodeList().remove(podcastEpisode);
				realEpisodeListView.getItems().remove(index);
				dragCompleted = true;
			}
			event.setDropCompleted(dragCompleted);
			event.consume();
		});
	}

	/**
	 * 
	 */
	private void setRealEpisodeListViewSetOnDragDroppedEvent() {
		realEpisodeListView.setOnDragDropped(event -> {
			boolean dragCompleted = false;
			final Dragboard dragboard = event.getDragboard();
			if (dragboard.hasContent(ITEM_LIST)) {
				final PodcastEpisode podcastEpisode = (PodcastEpisode) dragboard.getContent(ITEM_LIST);
				// episodeList.add(podcastEpisode);
				if (!PlayListController.getInstance().getEpisodeList().contains(podcastEpisode)) {
					PlayListController.getInstance().getEpisodeList().add(podcastEpisode);
					final SerialHBox realPodcastEpisode = SerialHboxBuilder.create().artist(podcastEpisode.getAuthor())
							.title(podcastEpisode.getTitle()).build();
					realEpisodeListView.getItems().add(realPodcastEpisode);
				}
				// final int index =
				// PlayListBorderPaneView.playList.indexOf(podcastEpisode);
				final int index = PlayListController.getInstance().getPlayList().indexOf(podcastEpisode);
				// playList.remove(podcastEpisode);
				PlayListController.getInstance().getPlayList().remove(podcastEpisode);
				realPlayListView.getItems().remove(index);
				dragCompleted = true;
			}
			event.setDropCompleted(dragCompleted);
			event.consume();
		});
	}

	/**
	 * 
	 */
	private void setRealPlayListViewDragOverEvent() {
		realPlayListView.setOnDragOver(event -> {
			final Dragboard dragboard = event.getDragboard();
			if (event.getGestureSource() != realPlayListView && dragboard.hasContent(ITEM_LIST)) {
				event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
			}
			event.consume();
		});
	}

	/**
	 * 
	 */
	private void setRealEpisodeListViewSetOnDragOverEvent() {
		realEpisodeListView.setOnDragOver(event -> {
			final Dragboard dragboard = event.getDragboard();
			if (event.getGestureSource() != realEpisodeListView && dragboard.hasContent(ITEM_LIST)) {
				event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
			}
			event.consume();
		});
	}

	/**
	 * 
	 */
	private void decorate() {
		PlayListBPDecorator.decorateFactory(this);
		setMargin(this.playListVBox, new Insets(PlayListBPDecorator.INSETS));
		setMargin(this.episodesVBox, new Insets(PlayListBPDecorator.INSETS));
	}

	/**
	 * 
	 */
	private void setRealPlayListViewSetOnDragEvent() {
		realPlayListView.setOnDragDetected(event -> {
			final Dragboard dragboard = realPlayListView.startDragAndDrop(TransferMode.COPY_OR_MOVE);
			// final PodcastEpisode selectedItem =
			// playList.get(realPlayListView.getSelectionModel().getSelectedIndex());
			final PodcastEpisode selectedItem = PlayListController.getInstance().getPlayList()
					.get(realPlayListView.getSelectionModel().getSelectedIndex());
			final ClipboardContent content = new ClipboardContent();
			content.put(ITEM_LIST, selectedItem);
			dragboard.setContent(content);
			event.consume();
		});
	}

	/**
	 * 
	 */
	private void setRealEpisodeListViewSetOnDragEvent() {
		realEpisodeListView.setOnDragDetected(event -> {
			final Dragboard dragboard = realEpisodeListView.startDragAndDrop(TransferMode.COPY_OR_MOVE);
			// final PodcastEpisode selectedItem = episodeList
			// .get(realEpisodeListView.getSelectionModel().getSelectedIndex());
			final PodcastEpisode selectedItem = PlayListController.getInstance().getEpisodeList()
					.get(realEpisodeListView.getSelectionModel().getSelectedIndex());
			final ClipboardContent content = new ClipboardContent();
			content.put(ITEM_LIST, selectedItem);
			dragboard.setContent(content);
			event.consume();
		});
	}

	/**
	 * 
	 */
	private void setSelectionMode() {
		realEpisodeListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		realPlayListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
	}

	private void populateEpisodesList() {
		// PlayListBorderPaneView.episodeList = FXCollections.<PodcastEpisode>
		// observableArrayList();
		PlayListController.getInstance().setEpisodeList(FXCollections.<PodcastEpisode> observableArrayList());
		for (final Podcast podcast : PlayListController.getInstance().readfromDB()) {
			for (final PodcastEpisode podcastEpisode : podcast.getPodcastEpisode()) {
				// episodeList.add(podcastEpisode);
				PlayListController.getInstance().getEpisodeList().add(podcastEpisode);
			}
		}
		// realEpisodeList = FXCollections.<SerialHBox> observableArrayList();
		PlayListController.getInstance().setRealEpisodeList(FXCollections.<SerialHBox> observableArrayList());
		for (final Podcast podcast : PlayListController.getInstance().readfromDB()) {
			for (final PodcastEpisode podcastEpisode : podcast.getPodcastEpisode()) {
				// realEpisodeList.add(SerialHboxBuilder.create().artist(podcast.getArtistName())
				// .title(podcastEpisode.getTitle()).build());
				PlayListController.getInstance().getRealEpisodeList().add(SerialHboxBuilder.create()
						.artist(podcast.getArtistName()).title(podcastEpisode.getTitle()).build());
			}
		}
	}

	private void populateEpisodesListView() {
		// realEpisodeListView =
		// SerialListViewBuilder.create().items(realEpisodeList)
		// .size(PlayListBPDecorator.LISTVIEWWIDTH,
		// PlayListBPDecorator.LISTVIEWHEIGHT).build();
		realEpisodeListView = SerialListViewBuilder.create()
				.items(PlayListController.getInstance().getRealEpisodeList())
				.size(PlayListBPDecorator.LISTVIEWWIDTH, PlayListBPDecorator.LISTVIEWHEIGHT).build();
	}

	private void populatePlayList() {
		// realPlayList = FXCollections.<SerialHBox> observableArrayList();
		// PlayListController.getInstance().setRealPlayList(FXCollections.<SerialHBox>
		// observableArrayList());
		PlayListController.getInstance().setRealPlayList(PlayListController.getInstance().getRealPlayList());
		// playList = FXCollections.<PodcastEpisode> observableArrayList();
		// PlayListController.getInstance().setPlayList(FXCollections.<PodcastEpisode>
		// observableArrayList());
		PlayListController.getInstance().setPlayList(PlayListController.getInstance().getPlayList());
	}

	private void populatePlayListView() {
		//-.-off
		realPlayListView = SerialListViewBuilder.create()
									.items(PlayListController.getInstance().getRealPlayList())
									.size(PlayListBPDecorator.LISTVIEWWIDTH, PlayListBPDecorator.LISTVIEWHEIGHT)
									.build();
		//realPlayListView.getItems().addAll(realPlayList);
		//realPlayListView.getItems().addAll(PlayListController.getInstance().getRealPlayList());
		//-.-on
	}

}
