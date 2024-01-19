package Src.Sound;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

/**
 * Classe per la gestione dell'audio (AudioPlayer.java)
 */
public class AudioPlayer implements Runnable {

	private Clip clip;               			// The audio clip
    private String filepath;         			// The file path of the audio
    private File audioFile;          			// The audio file
    private volatile boolean isRunning = true;  // Flag to control the running state of the audio thread
	
    /**
     * The run method of the AudioPlayer thread.
     * Continuously plays the audio until isRunning is set to false.
     */
    @Override
    public void run() {
        try {
			while (isRunning) {
				playAudio();
			}
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    /**
     * Constructs an AudioPlayer object with the specified file path.
     *
     * @param filePath The file path of the audio.
     */
	public AudioPlayer(String filePath) {
		this.filepath = filepath;
		audioFile = new File(filePath);
	}

	/**
     * Plays the audio file specified by the file path.
     *
     * @throws UnsupportedAudioFileException  If the audio file format is not supported.
     * @throws IOException                    If there is an issue reading the audio file.
     * @throws LineUnavailableException       If a line cannot be opened because it is unavailable.
     */
	public void playAudio() throws UnsupportedAudioFileException, IOException, LineUnavailableException {

		// Verifica che il file audio esista
		if (!audioFile.exists()) {
			System.out.println("Il file audio non esiste: ");
			return;
		}

		// If a clip is already running, stop and close it
		if (clip != null && clip.isRunning()) {
			clip.stop();
			clip.close();
		}

		// Ottieni un'istanza di AudioInputStream
		AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

		// Ottieni un'istanza di Clip
		Clip clip = AudioSystem.getClip();

		// Apri il file audio e collega il Clip ad esso
		clip.open(audioStream);

		// Avvia la riproduzione
		clip.start();
		
		clip.loop(Clip.LOOP_CONTINUOUSLY);
		// Attendi la fine della riproduzione prima di chiudere
		while (!clip.isRunning()) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		while (clip.isRunning()) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		// Chiudi il Clip e rilascia le risorse
		clip.close();
		audioStream.close();
	}

	/**
     * Stops the audio thread, interrupts it, and closes the audio clip.
     */
    public void stopThread() {
		Thread.currentThread().interrupt(); 
        isRunning = false;
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.close();
        }
    }
	
    /**
     * Sets the audio file to be played based on the specified file path.
     *
     * @param filePath The file path of the new audio.
     */
	public void setClip(String filePath)
	{
		if (this.filepath != filePath)
			audioFile = new File(filePath);
	}
}