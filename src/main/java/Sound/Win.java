package Sound;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

/**
 * The Win class handles playing a sound effect associated with winning the game.
 */
public class Win {
    String WinSound;
    SoundEffect wse = new SoundEffect();

    /**
     * Constructs a new Win instance, initializes the win sound effect, and starts playing it.
     */
    public Win(){

        WinSound = "Sound/Win.wav";
        wse.setFile(WinSound);
        wse.play();
    }

    /**
     * The SoundEffect class represents a sound effect and is used to play short sound clips.
     */
    public class SoundEffect{
        Clip clip;

        /**
         * Sets the file for the sound effect.
         *
         * @param soundFileName The file path of the sound effect.
         */
        public void setFile(String soundFileName){

            try {
                File file = new File(soundFileName);
                AudioInputStream sound = AudioSystem.getAudioInputStream(file);
                clip = AudioSystem.getClip();
                clip.open(sound);
            }

            catch(Exception e){
                e.printStackTrace();
            }


        }

        /**
         * Plays the sound effect.
         */
        public void play(){
            if(clip != null) {
                clip.setFramePosition(0);
                clip.start();
            }

        }
    }

}
