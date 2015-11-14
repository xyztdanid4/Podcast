package szakdolgozat.podcast.gui.borderpane;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.Dragboard;
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
	private static final DataFormat ITEM_LIST = new DataFormat("jdojo/itemlist");

	private static ObservableList<PodcastEpisode> episodeList;
	private static ObservableList<SerialHBox> realEpisodeList;
	private static ListView<SerialHBox> realEpisodeListView;

	private static ObservableList<PodcastEpisode> playList;
	private static ObservableList<SerialHBox> realPlayList;
	private static ListView<SerialHBox> realPlayListView;

	public PlayListBorderPane() {
		populateEpisodesList();
		populateEpisodesListView();
		//-.-off
		final VBox episodesVBox = SerialVBoxBuilder.create()
										.bigText(EPISODES)
										.noListView(realEpisodeListView)
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
											.noListView(realPlayListView)
											.topLeftAlignment()
											.build();
		//-.-on
		setMargin(playListVBox, new Insets(20));
		setCenter(playListVBox);
		PlayListBPDecorator.decorateFactory(this);

		realEpisodeListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		realPlayListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

		realEpisodeListView.setOnDragDetected(event -> {
			final Dragboard dragboard = realEpisodeListView.startDragAndDrop(TransferMode.COPY_OR_MOVE);
			final PodcastEpisode selectedItem = episodeList
					.get(realEpisodeListView.getSelectionModel().getSelectedIndex());
			final ClipboardContent content = new ClipboardContent();
			content.put(ITEM_LIST, selectedItem);
			dragboard.setContent(content);
			event.consume();

		});

		realPlayListView.setOnDragDetected(event -> {
			final Dragboard dragboard = realPlayListView.startDragAndDrop(TransferMode.COPY_OR_MOVE);
			final PodcastEpisode selectedItem = playList.get(realPlayListView.getSelectionModel().getSelectedIndex());
			final ClipboardContent content = new ClipboardContent();
			content.put(ITEM_LIST, selectedItem);
			dragboard.setContent(content);
			event.consume();
		});

		realEpisodeListView.setOnDragOver(event -> {
			final Dragboard dragboard = event.getDragboard();
			if (event.getGestureSource() != realEpisodeListView && dragboard.hasContent(ITEM_LIST)) {
				event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
			}
			event.consume();
		});

		realPlayListView.setOnDragOver(event -> {
			final Dragboard dragboard = event.getDragboard();
			if (event.getGestureSource() != realPlayListView && dragboard.hasContent(ITEM_LIST)) {
				event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
			}
			event.consume();
		});

		realEpisodeListView.setOnDragDropped(event -> {
			boolean dragCompleted = false;
			final Dragboard dragboard = event.getDragboard();
			if (dragboard.hasContent(ITEM_LIST)) {
				final PodcastEpisode podcastEpisode = (PodcastEpisode) dragboard.getContent(ITEM_LIST);
				episodeList.add(podcastEpisode);
				final SerialHBox realPodcastEpisode = SerialHboxBuilder.create().artist(podcastEpisode.getAuthor())
						.title(podcastEpisode.getTitle()).build();
				realEpisodeListView.getItems().add(realPodcastEpisode);
				final int index = PlayListBorderPane.playList.indexOf(podcastEpisode);
				playList.remove(podcastEpisode);
				realPlayListView.getItems().remove(index);
				dragCompleted = true;
			}
			event.setDropCompleted(dragCompleted);
			event.consume();
		});

		realPlayListView.setOnDragDropped(event -> {
			boolean dragCompleted = false;
			final Dragboard dragboard = event.getDragboard();
			if (dragboard.hasContent(ITEM_LIST)) {
				final PodcastEpisode podcastEpisode = (PodcastEpisode) dragboard.getContent(ITEM_LIST);
				playList.add(podcastEpisode);
				final SerialHBox realPodcastEpisode = SerialHboxBuilder.create().artist(podcastEpisode.getAuthor())
						.title(podcastEpisode.getTitle()).build();
				realPlayListView.getItems().add(realPodcastEpisode);
				final int index = PlayListBorderPane.episodeList.indexOf(podcastEpisode);
				episodeList.remove(podcastEpisode);
				realEpisodeListView.getItems().remove(index);
				dragCompleted = true;
			}
			event.setDropCompleted(dragCompleted);
			event.consume();
		});

		realEpisodeListView.setOnDragDone(e -> {
			final TransferMode tm = e.getTransferMode();
			if (tm == TransferMode.MOVE) {

			}
			e.consume();
		});

		realPlayListView.setOnDragDone(e -> {
			final TransferMode tm = e.getTransferMode();
			if (tm == TransferMode.MOVE) {

			}
			e.consume();
		});

	}

	private void populateEpisodesList() {
		PlayListBorderPane.episodeList = FXCollections.<PodcastEpisode> observableArrayList();
		for (final Podcast podcast : readfromDB()) {
			for (final PodcastEpisode podcastEpisode : podcast.getPodcastEpisode()) {
				episodeList.add(podcastEpisode);
			}
		}
		realEpisodeList = FXCollections.<SerialHBox> observableArrayList();
		for (final Podcast podcast : readfromDB()) {
			for (final PodcastEpisode podcastEpisode : podcast.getPodcastEpisode()) {
				realEpisodeList.add(SerialHboxBuilder.create().artist(podcast.getArtistName())
						.title(podcastEpisode.getTitle()).build());
			}
		}
	}

	private void populateEpisodesListView() {
		realEpisodeListView = SerialListViewBuilder.create().items(realEpisodeList)
				.size(PlayListBPDecorator.LISTVIEWWIDTH, PlayListBPDecorator.LISTVIEWHEIGHT).build();
	}

	private void populatePlayList() {
		realPlayList = FXCollections.<SerialHBox> observableArrayList();
		playList = FXCollections.<PodcastEpisode> observableArrayList();
	}

	private void populatePlayListView() {
		//-.-off
		realPlayListView = SerialListViewBuilder.create()
									.size(PlayListBPDecorator.LISTVIEWWIDTH, PlayListBPDecorator.LISTVIEWHEIGHT)
									.build();
		realPlayListView.getItems().addAll(realPlayList);
		//-.-on
	}

	public List<Podcast> readfromDB() {
		return MorphiaConnector.getDataStore().createQuery(Podcast.class).asList();
	}

}
