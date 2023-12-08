package Sound;

import javax.sound.sampled.*;
import java.io.File;

/**
 * The Bgm class handles background music (BGM) for the game.
 */
public class Bgm {

    private static Clip clip;
    private static String BgmSound = "Sound/Bgm.wav";
    private static final SoundEffect se = new SoundEffect();
    private static long savedBgmPosition;

    /**
     * Constructs a new Bgm instance, initializes the background music, and starts playing it.
     */
    public Bgm(){
        if(clip == null) {
            se.setFile(BgmSound);
            se.play();
        }
    }

    /**
     * Pauses the background music.
     */

    public static void pause() {
        if(clip != null  && clip.isRunning()){
            savedBgmPosition = clip.getMicrosecondPosition();
            clip.stop();
        }

    }

    /**
     * Resumes the background music from the point where it was paused.
     */
    public static void resume() {
        if(clip != null){
            clip.setMicrosecondPosition(savedBgmPosition);
            clip.start();

        }
    }

    /**
     * The SoundEffect class represents a sound effect and is used to play short sound clips.
     */
    public static class SoundEffect{

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





