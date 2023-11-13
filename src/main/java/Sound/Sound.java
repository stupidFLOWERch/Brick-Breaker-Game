package Sound;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Sound {

    String CrackSound;
    SoundEffect SE = new SoundEffect();

    public Sound(){

        CrackSound = "Sound/Crack.wav";
        SE.setFile(CrackSound);
        SE.play();
    }

    public class SoundEffect{
        Clip clip;

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
            public void play(){
            if(clip != null) {
                clip.setFramePosition(0);
                clip.start();
            }

            }
            }


        }




