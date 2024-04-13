package src.Sound;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * AudioManager class for managing audio playback.
 * This class provides methods for playing audio files.
 */
public class AudioManager {

    /**
     * Singleton instance of AudioManager.
     */
    private static AudioManager instance;

    /**
     * Private constructor for singleton pattern.
     */
    private AudioManager() {
        // Private constructor to enforce singleton pattern.
    }

    /**
     * Gets the singleton instance of AudioManager.
     *
     * @return The singleton instance of AudioManager.
     */
    public static AudioManager getInstance() {
        if (instance == null)
            instance = new AudioManager();
        return instance;
    }

    /**
     * Plays the specified audio file.
     *
     * @param filename The path to the audio file to be played.
     */
    public void play(String filename) {
        try {
            InputStream in = new BufferedInputStream(new FileInputStream(filename));
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(in);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (UnsupportedAudioFileException e1) {
            e1.printStackTrace();
        } catch (LineUnavailableException e1) {
            e1.printStackTrace();
        }
    }
}
