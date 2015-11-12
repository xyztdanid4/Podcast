package szakdolgozat.podcast.gui.mediaplayer;

import java.time.LocalTime;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;
import szakdolgozat.podcast.builder.CheckBoxBuilder;
import szakdolgozat.podcast.builder.HBoxBuilder;
import szakdolgozat.podcast.builder.LabelBuilder;
import szakdolgozat.podcast.builder.MediaButtonBuilder;
import szakdolgozat.podcast.builder.SliderBuilder;
import szakdolgozat.podcast.builder.TextBuilder;
import szakdolgozat.podcast.data.podcast.PodcastEpisode;
import szakdolgozat.podcast.gui.decorator.MediaControlDecorator;

//TODO repeat változot lecserélni egy checkboxra
// TODO sebességváltozás megcsinálásása
// TODO random lejátszása
// TODO mute gomb
// TODO repeat checkbox

public class MediaControlPodcast extends VBox {
	private static final String REPEAT2 = "Repeat";
	private static final String _00_00_00 = "00:00:00";
	private static MediaControlPodcast instance = new MediaControlPodcast();
	private static final String PLAYBUTTONURL = "appbar.control.play.png";
	private static final String NEXTBUTTONURL = "appbar.control.fastforward.variant.png";
	private static final String PREVBUTTONURL = "appbar.control.rewind.variant.png";
	private static final String PAUSEBUTTONURL = "appbar.control.pause.png";
	private static final String VOLUMELABEL_TEXT = "Volume: ";
	private static boolean atEndOfMedia = false;
	private static boolean stopRequested = false;
	private static boolean repeat = false;
	private static Button playButton;
	private static Button pauseButton;
	private static Duration duration;
	private static MediaPlayer mediaPlayer;
	private static boolean firstRun = true;

	public static MediaControlPodcast getInstance() {
		if (instance == null) {
			instance = new MediaControlPodcast();
		}
		return instance;
	}

	public MediaControlPodcast create(final PodcastEpisode podcastEpisode) {
		try {
			instance = new MediaControlPodcast(podcastEpisode);
			return instance;
		} catch (final Exception e) {
			e.printStackTrace();
			return new MediaControlPodcast();
		}
	}

	public void stop() {
		if (!firstRun) {
			mediaPlayer.stop();
		}
		firstRun = false;
	}

	public MediaControlPodcast() {
		//-.-off
		final Button playButton = MediaButtonBuilder.create()
													.image(PLAYBUTTONURL)
													.build();
		
		final Button pauseButton = MediaButtonBuilder.create()
													.image(PAUSEBUTTONURL)
													.build();
		
		final Button prevButton = MediaButtonBuilder.create()
													.image(PREVBUTTONURL)
													.build();
		
		final Button nextButton = MediaButtonBuilder.create()
													.image(NEXTBUTTONURL)
													.build();
		
		final Slider volumeSlider = SliderBuilder.create()
												.build();
		
		final Slider timeSlider = SliderBuilder.create()
												.build();
		timeSlider.setPrefWidth(MediaControlDecorator.TIMESLIDER);
		
		final Text playedTime = TextBuilder.create()
											.smallText(_00_00_00)
											.build();
		
		final CheckBox repeatCheckBox = CheckBoxBuilder.create()
														.text(REPEAT2)
														.setDefaultValue(false)
														.build();
		
		final HBox mediaButtonVolumeHBox = HBoxBuilder.create()
													.noButton(prevButton)
													.noButton(pauseButton)
													.noButton(playButton)
													.noButton(nextButton)
													.noLabel(LabelBuilder.create()
																		.text(VOLUMELABEL_TEXT)
																		.build())
													.slider(volumeSlider)
													.build();
		mediaButtonVolumeHBox.setAlignment(Pos.CENTER);
		setMargin(mediaButtonVolumeHBox, new Insets(MediaControlDecorator.PADDING));
		
		final HBox mediaSlider = HBoxBuilder.create()
											.noText(playedTime)
											.slider(timeSlider)
											.checkBox(repeatCheckBox)
											.build();
		mediaSlider.setAlignment(Pos.CENTER);
		setMargin(mediaSlider, new Insets(MediaControlDecorator.PADDING));
		//-.-on

		getChildren().addAll(mediaSlider, mediaButtonVolumeHBox);
		decorateVBox(this);
	}

	private MediaControlPodcast(final PodcastEpisode podcastEpisode) throws Exception {
		// emiatt kell throws exception
		mediaPlayer = new MediaPlayer(new Media(podcastEpisode.getGuid()));
		mediaPlayer.setAutoPlay(true);

		final HBox mediaButtonVolume = new HBox(10);
		final HBox mediaSlider = new HBox(10);
		setMargin(mediaButtonVolume, new Insets(5));
		setMargin(mediaSlider, new Insets(5));

		final Button playButton = MediaButtonBuilder.create().image(PLAYBUTTONURL).build();
		final Button pauseButton = MediaButtonBuilder.create().image(PAUSEBUTTONURL).build();
		final Button prevButton = MediaButtonBuilder.create().image(PREVBUTTONURL).build();
		final Button nextButton = MediaButtonBuilder.create().image(NEXTBUTTONURL).build();
		final Label volumeLabel = LabelBuilder.create().text(VOLUMELABEL_TEXT).build();
		final Slider volumeSlider = new Slider();
		final Slider timeSlider = new Slider();
		timeSlider.setPrefWidth(600);
		final Text playedTime = TextBuilder.create().smallText(_00_00_00).build();
		final Text artistText = TextBuilder.create().bigText(podcastEpisode.getAuthor()).build();
		final Text episodeText = TextBuilder.create().bigText(podcastEpisode.getTitle()).build();
		final CheckBox repeatCheckBox = CheckBoxBuilder.create().text(REPEAT2).setDefaultValue(false).build();

		playButton.setOnAction(event -> {
			final Status status = mediaPlayer.getStatus();
			if (status == Status.UNKNOWN || status == Status.HALTED) {
				return;
			}
			if (status == Status.PAUSED || status == Status.READY || status == Status.STOPPED) {
				if (MediaControlPodcast.atEndOfMedia) {
					mediaPlayer.seek(mediaPlayer.getStartTime());
					MediaControlPodcast.atEndOfMedia = false;
				}
				mediaPlayer.play();
				MediaControlPodcast.playButton.setDisable(false);
				MediaControlPodcast.pauseButton.setDisable(true);
			} else {
				mediaPlayer.pause();
				MediaControlPodcast.pauseButton.setDisable(false);
				MediaControlPodcast.playButton.setDisable(true);
			}
		});

		pauseButton.setOnAction(event -> {
			final Status status = mediaPlayer.getStatus();
			if (status == Status.UNKNOWN || status == Status.HALTED) {
				return;
			}
			if (status == Status.PLAYING) {
				mediaPlayer.pause();
				MediaControlPodcast.pauseButton.setDisable(false);
				MediaControlPodcast.playButton.setDisable(true);
			}
		});

		mediaPlayer.setOnPlaying(() -> {
			if (stopRequested) {
				mediaPlayer.pause();
				stopRequested = false;
			} else {
				MediaControlPodcast.pauseButton.setDisable(false);
				MediaControlPodcast.playButton.setDisable(true);
			}
		});

		mediaPlayer.setOnPaused(() -> {
			MediaControlPodcast.playButton.setDisable(false);
			MediaControlPodcast.pauseButton.setDisable(true);
		});

		mediaPlayer.setOnEndOfMedia(() -> {
			if (!repeat) {
				MediaControlPodcast.pauseButton.setDisable(false);
				MediaControlPodcast.playButton.setDisable(true);
				stopRequested = true;
				atEndOfMedia = true;
			}
		});

		mediaPlayer.setCycleCount(repeat ? MediaPlayer.INDEFINITE : 1);

		mediaPlayer.setOnReady(() -> {
			System.out.println("ready");
			duration = mediaPlayer.getMedia().getDuration();
			Platform.runLater(() -> {
				final Duration currentTime = mediaPlayer.getCurrentTime();
				timeSlider.setDisable(duration.isUnknown());
				if (!timeSlider.isDisabled() && duration.greaterThan(Duration.ZERO) && !timeSlider.isValueChanging()) {
					timeSlider.setValue(currentTime.divide(duration).toMillis() * 100.0);
				}
				if (!volumeSlider.isValueChanging()) {
					volumeSlider.setValue((int) Math.round(mediaPlayer.getVolume() * 100));
				}

			});
		});

		mediaPlayer.currentTimeProperty().addListener((InvalidationListener) ov -> {
			final LocalTime timeOfDay = LocalTime.ofSecondOfDay((long) mediaPlayer.getCurrentTime().toSeconds());
			final String time = timeOfDay.toString();
			playedTime.setText(time);

		});

		volumeSlider.valueProperty().addListener((InvalidationListener) ov -> {
			if (volumeSlider.isValueChanging()) {
				mediaPlayer.setVolume(volumeSlider.getValue() / 100.0);
			}
		});

		timeSlider.valueProperty().addListener((InvalidationListener) ov -> {
			if (timeSlider.isValueChanging()) {
				mediaPlayer.seek(duration.multiply(timeSlider.getValue() / 100.0));
			}
		});

		mediaButtonVolume.getChildren().addAll(prevButton, pauseButton, playButton, nextButton, volumeLabel,
				volumeSlider);
		mediaButtonVolume.setAlignment(Pos.CENTER);

		mediaSlider.getChildren().addAll(artistText, playedTime, timeSlider, episodeText, repeatCheckBox);
		mediaSlider.setAlignment(Pos.CENTER);

		getChildren().addAll(mediaSlider, mediaButtonVolume);
		decorateVBox(this);
	}

	private void decorateVBox(final VBox vbox) {
		vbox.setBackground(new Background(new BackgroundFill(Color.web("#191919"), new CornerRadii(0), Insets.EMPTY)));
		vbox.setBorder(new Border(new BorderStroke(Color.web("#006666"), BorderStrokeStyle.SOLID, new CornerRadii(0),
				new BorderWidths(3))));
		vbox.setPrefHeight(80);
	}
}
