package seedu.address.commons.util;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
/**
 * Contains auto-specific code
 */
public class AudioUtil {

    public static final String ERROR_SOUND = "assets/boop.mp3";
    /**
     * Plays an audio given a file path. Note that there is a slight delay due to having to load up the media player
     *
     * @param audioPath File path to the audio file to play. Note that directory starts from project root
     */
    public static void playAudio(String audioPath) {
        Media media = new Media(new File(audioPath).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }
}
