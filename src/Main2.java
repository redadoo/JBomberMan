package src;

public class Main2
{
    public static void main(String[] args) {
        // Fornisci il percorso corretto al tuo file audio
        String filePath = "C:/Users/banan/Desktop/Test/Fizzy.wav";

        // Crea un'istanza della classe AudioPlayer e riproduci l'audio
        AudioPlayer audioPlayer = new AudioPlayer();
        try {
            audioPlayer.playAudio(filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}