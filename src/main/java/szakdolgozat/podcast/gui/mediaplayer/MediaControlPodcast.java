package szakdolgozat.podcast.gui.mediaplayer;

import java.time.LocalTime;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.binding.Bindings;
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
import szakdolgozat.podcast.controller.PlayListController;
import szakdolgozat.podcast.data.podcast.PodcastEpisode;
import szakdolgozat.podcast.gui.borderpane.MainBorderPaneView;
import szakdolgozat.podcast.gui.decorator.MediaControlDecorator;

/**
 * The Class MediaControlPodcast.
 * 
 * @author Daniel Toth
 * @version 0.0.1
 * @since 0.0.1
 */
public class MediaControlPodcast extends VBox {

	/** The Constant MUTE. */
	private static final String MUTE = "Mute";

	/** The Constant REPEAT2. */
	private static final String REPEAT2 = "Repeat";

	/** The Constant _00_00_00. */
	private static final String _00_00_00 = "00:00:00";

	/** The instance. */
	private static MediaControlPodcast instance = new MediaControlPodcast();

	/** The Constant PLAYBUTTONURL. */
	private static final String PLAYBUTTONURL = "appbar.control.play.png";

	/** The Constant NEXTBUTTONURL. */
	private static final String NEXTBUTTONURL = "appbar.control.fastforward.variant.png";

	/** The Constant PREVBUTTONURL. */
	private static final String PREVBUTTONURL = "appbar.control.rewind.variant.png";

	/** The Constant PAUSEBUTTONURL. */
	private static final String PAUSEBUTTONURL = "appbar.control.pause.png";

	/** The Constant VOLUMELABEL_TEXT. */
	private static final String VOLUMELABEL_TEXT = "Volume: ";

	/** The at end of media. */
	private static boolean atEndOfMedia = false;

	/** The stop requested. */
	private static boolean stopRequested = false;

	/** The play button. */
	private static Button playButton;

	/** The pause button. */
	private static Button pauseButton;

	/** The duration. */
	private static Duration duration;

	/** The media player. */
	private static MediaPlayer mediaPlayer;

	/** The first run. */
	private static boolean firstRun = true;

	/** The prev button. */
	private static Button prevButton;

	/** The next button. */
	private static Button nextButton;

	/** The volume slider. */
	private static Slider volumeSlider;

	/** The time slider. */
	private static Slider timeSlider;

	/** The played time. */
	private static Text playedTime;

	/** The repeat check box. */
	private static CheckBox repeatCheckBox;

	/** The media button volume h box. */
	private static HBox mediaButtonVolumeHBox;

	/** The media slider. */
	private static HBox mediaSlider;

	/** The mute checkh box. */
	private static CheckBox muteCheckhBox;

	/** The play list counter. */
	private static int playListCounter = 0;

	/**
	 * Gets the single instance of MediaControlPodcast.
	 *
	 * @return single instance of MediaControlPodcast
	 */
	public static MediaControlPodcast getInstance() {
		if (instance == null) {
			instance = new MediaControlPodcast();
		}
		return instance;
	}

	/**
	 * Creates the.
	 *
	 * @param podcastEpisode
	 *            the podcast episode
	 * @return the media control podcast
	 */
	public MediaControlPodcast create(final PodcastEpisode podcastEpisode) {
		try {
			instance = new MediaControlPodcast(podcastEpisode);
			return instance;
		} catch (final Exception e) {
			e.printStackTrace();
			return new MediaControlPodcast();
		}
	}

	/**
	 * Stop.
	 */
	public void stop() {
		if (!firstRun) {
			mediaPlayer.stop();
		}
		firstRun = false;
	}

	/**
	 * Instantiates a new media control podcast.
	 */
	public MediaControlPodcast() {
		//-.-off
		playButton = MediaButtonBuilder.create()
										.image(PLAYBUTTONURL)
										.disable(true)
										.build();
		
		pauseButton = MediaButtonBuilder.create()
										.image(PAUSEBUTTONURL)
										.disable(true)
										.build();
		
		prevButton = MediaButtonBuilder.create()
										.image(PREVBUTTONURL)
										.disable(true)
										.build();
		
		nextButton = MediaButtonBuilder.create()
										.image(NEXTBUTTONURL)
										.disable(true)
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

	/**
	 * Instantiates a new media control podcast.
	 *
	 * @param podcastEpisode
	 *            the podcast episode
	 * @throws Exception
	 *             the exception
	 */
	private MediaControlPodcast(final PodcastEpisode podcastEpisode) throws Exception {

		//-.-off
				playButton = MediaButtonBuilder.create()
												.image(PLAYBUTTONURL)
												.disable(false)
												.build();
				
				pauseButton = MediaButtonBuilder.create()
												.image(PAUSEBUTTONURL)
												.disable(false)
												.build();
				
				prevButton = MediaButtonBuilder.create()
												.image(PREVBUTTONURL)
												.disable(false)
												.build();
				
				nextButton = MediaButtonBuilder.create()
												.image(NEXTBUTTONURL)
												.disable(false)
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
		
		nextButton.disableProperty().bind(Bindings.isEmpty(PlayListController.getInstance().getPlayList()));
		
		nextButton.setOnAction(event -> {
			try {
				MediaControlPodcast.getInstance().stop();
				MainBorderPaneView.getInstance().buildBottom(PlayListController.getInstance().getPlayList().get(0));
				playListCounter++;
				PlayListController.getInstance().getPlayList().remove(0);
				PlayListController.getInstance().getRealPlayList().remove(0);
			} catch (final Exception e) {
				e.printStackTrace();
			}
		});
		
		prevButton.setOnAction(event -> {
			mediaPlayer.seek(mediaPlayer.getStartTime());
			mediaPlayer.play();
			final Duration currentTime = mediaPlayer.getCurrentTime();
			timeSlider.setValue(currentTime.divide(duration).toMillis() * 100.0);
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
