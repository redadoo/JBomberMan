package src;

// Classe per la gestione dell'audio (AudioPlayer.java)
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

public class AudioPlayer {

    public void playAudio(String filePath) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        File audioFile = new File(filePath);

        // Verifica che il file audio esista
        if (!audioFile.exists()) {
            System.out.println("Il file audio non esiste: " + filePath);
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
}

