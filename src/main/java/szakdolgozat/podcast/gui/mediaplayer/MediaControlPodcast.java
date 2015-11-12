package szakdolgozat.podcast.gui.mediaplayer;

import java.time.LocalTime;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
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

// TODO random lejátszása
// TODO mute gomb

public class MediaControlPodcast extends VBox {
	private static final String MUTE = "Mute";
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
	private static Button prevButton;
	private static Button nextButton;
	private static Slider volumeSlider;
	private static Slider timeSlider;
	private static Text playedTime;
	private static CheckBox repeatCheckBox;
	private static HBox mediaButtonVolumeHBox;
	private static HBox mediaSlider;
	private static CheckBox muteCheckhBox;

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
		playButton = MediaButtonBuilder.create()
										.image(PLAYBUTTONURL)
										.build();
		
		pauseButton = MediaButtonBuilder.create()
										.image(PAUSEBUTTONURL)
										.build();
		
		prevButton = MediaButtonBuilder.create()
										.image(PREVBUTTONURL)
										.build();
		
		nextButton = MediaButtonBuilder.create()
										.image(NEXTBUTTONURL)
										.build();
		
		volumeSlider = SliderBuilder.create()
									.build();
		
		timeSlider = SliderBuilder.create()
									.build();
		timeSlider.setPrefWidth(MediaControlDecorator.TIMESLIDER);
		
		playedTime = TextBuilder.create()
								.smallText(_00_00_00)
								.build();
		
		repeatCheckBox = CheckBoxBuilder.create()
										.text(REPEAT2)
										.setDefaultValue(false)
										.build();
		
		muteCheckhBox = CheckBoxBuilder.create()
										.text(MUTE)
										.setDefaultValue(false)
										.build();
		
		mediaButtonVolumeHBox = HBoxBuilder.noCreate()
											.noButton(prevButton)
											.noButton(pauseButton)
											.noButton(playButton)
											.noButton(nextButton)
											.noLabel(LabelBuilder.create()
																.text(VOLUMELABEL_TEXT)
																.build())
											.slider(volumeSlider)
											.checkBox(repeatCheckBox)
											.checkBox(muteCheckhBox)
											.build();
		mediaButtonVolumeHBox.setAlignment(Pos.CENTER);
		setMargin(mediaButtonVolumeHBox, new Insets(MediaControlDecorator.PADDING));
		
		mediaSlider = HBoxBuilder.noCreate()
									.noText(playedTime)
									.slider(timeSlider)
									.build();
		mediaSlider.setAlignment(Pos.CENTER);
		setMargin(mediaSlider, new Insets(MediaControlDecorator.PADDING));
		//-.-on

		getChildren().addAll(mediaSlider, mediaButtonVolumeHBox);
		MediaControlDecorator.decorateFactory(this);
	}

	private MediaControlPodcast(final PodcastEpisode podcastEpisode) throws Exception {

		//-.-off
				playButton = MediaButtonBuilder.create()
												.image(PLAYBUTTONURL)
												.build();
				
				pauseButton = MediaButtonBuilder.create()
												.image(PAUSEBUTTONURL)
												.build();
				
				prevButton = MediaButtonBuilder.create()
												.image(PREVBUTTONURL)
												.build();
				
				nextButton = MediaButtonBuilder.create()
												.image(NEXTBUTTONURL)
												.build();
				
				volumeSlider = SliderBuilder.create()
											.build();
				
				timeSlider = SliderBuilder.create()
											.build();
				timeSlider.setPrefWidth(MediaControlDecorator.TIMESLIDER);
				
				playedTime = TextBuilder.create()
										.smallText(_00_00_00)
										.build();
				
				repeatCheckBox = CheckBoxBuilder.create()
												.text(REPEAT2)
												.setDefaultValue(false)
												.build();
				
				muteCheckhBox = CheckBoxBuilder.create()
												.text(MUTE)
												.setDefaultValue(false)
												.build();
		
		// emiatt kell throws exception
		mediaPlayer = new MediaPlayer(new Media(podcastEpisode.getGuid()));
		mediaPlayer.setAutoPlay(true);
		
		muteCheckhBox.selectedProperty().addListener((InvalidationListener) observable -> {
			if(muteCheckhBox.isSelected()){
				mediaPlayer.setMute(true);
			}
			else{
				mediaPlayer.setMute(false);
			}
		});

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
			if (!repeatCheckBox.isSelected()) {
				MediaControlPodcast.pauseButton.setDisable(true);
				MediaControlPodcast.playButton.setDisable(false);
				stopRequested = true;
				atEndOfMedia = true;
			}
			else{
				MediaControlPodcast.pauseButton.setDisable(false);
				MediaControlPodcast.playButton.setDisable(true);
				stopRequested = false;
				atEndOfMedia = false;
				mediaPlayer.seek(mediaPlayer.getStartTime());
				mediaPlayer.play();
				final Duration currentTime = mediaPlayer.getCurrentTime();
				timeSlider.setValue(currentTime.divide(duration).toMillis() * 100.0);
			}
		});

		//mediaPlayer.setCycleCount(repeat ? MediaPlayer.INDEFINITE : 1);

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

		//-.-off
		final Text artistText = TextBuilder.create()
											.bigText(podcastEpisode.getAuthor())
											.build();
		
		final Text episodeText = TextBuilder.create()
											.bigText(podcastEpisode.getTitle())
											.build();
		
		mediaButtonVolumeHBox = HBoxBuilder.noCreate()
											.noButton(prevButton)
											.noButton(pauseButton)
											.noButton(playButton)
											.noButton(nextButton)
											.noLabel(LabelBuilder.create()
																.text(VOLUMELABEL_TEXT)
																.build())
											.slider(volumeSlider)
											.checkBox(repeatCheckBox)
											.checkBox(muteCheckhBox)
											.build();
		mediaButtonVolumeHBox.setAlignment(Pos.CENTER);
		
		setMargin(mediaButtonVolumeHBox, new Insets(MediaControlDecorator.PADDING));

		mediaSlider = HBoxBuilder.noCreate()
				.noText(artistText)
				.noText(playedTime)
				.slider(timeSlider)
				.noText(episodeText)
				.build();
		mediaSlider.setAlignment(Pos.CENTER);
		setMargin(mediaSlider, new Insets(MediaControlDecorator.PADDING));
		//-.-on

		getChildren().addAll(mediaSlider, mediaButtonVolumeHBox);
		MediaControlDecorator.decorateFactory(this);
	}

}
