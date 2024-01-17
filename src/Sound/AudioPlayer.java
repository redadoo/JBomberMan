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

	private File audioFile;
    private volatile boolean isRunning = true;
	
	@Override
	public void run() 
	{
		while (isRunning) {
			try {
				playAudio();
			} catch (UnsupportedAudioFileException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (LineUnavailableException e) {
				e.printStackTrace();
			}
		}

	}

	public AudioPlayer(String filePath) {
		audioFile = new File(filePath);
	}

	public void ChangeMusic(String filePath)
	{
		audioFile = new File(filePath);
	}

	public void playAudio() throws UnsupportedAudioFileException, IOException, LineUnavailableException {

		// Verifica che il file audio esista
		if (!audioFile.exists()) {
			System.out.println("Il file audio non esiste: ");
			return;
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

	public void stopThread() {
        isRunning = false;
    }
}
