package fr.chess.game.window.audio;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

import static java.util.Objects.requireNonNull;

/**
 * {@link AudioManager} est utilisée pour jouer des sons, ça ajoute une vraie ambience
 * au jeu, super agréable.
 */
public class AudioManager {

    /** La musique par défaut **/
    public final static String DEFAULT = "childhood.wav";

    /** La musique jouée actuellement **/
    private Clip current;

    /**
     * Joue la musique par défaut du jeu, celle du menu.
     */
    public void playDefault() {
       play(DEFAULT, Clip.LOOP_CONTINUOUSLY);
    }

    /**
     * Joue la musique avec le nom indiquée.
     *
     * @param name Le nom de la musique avec son format
     * @param loop Loop si la musique doit s'arrêter ou non
     */
    public void play(String name, int loop) {
        if(current != null && current.isRunning()) {
            return;
        }

        new Thread(() -> {
            try {
                current = AudioSystem.getClip();
                URL url = getClass().getResource("/audio/" + name);
                AudioInputStream ais = AudioSystem.getAudioInputStream(requireNonNull(url));
                current.open(ais);
                current.loop(loop);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    /**
     * Arrête la musique actuelle.
     */
    public void stop() {
        current.stop();
    }

}
