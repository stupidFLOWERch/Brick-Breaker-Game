package Sound;

import javax.sound.sampled.*;
import java.io.File;

public class Bgm {

    private static Clip clip;
    private static String BgmSound = "Sound/Bgm.wav";
    private static SoundEffect se = new SoundEffect();

    public Bgm(){
        if(clip == null) {
            se.setFile(BgmSound);
            se.play();
        }
    }

    public static class SoundEffect{
        public void setFile(String soundFileName){

            try {
                File file = new File(soundFileName);
                AudioInputStream sound = AudioSystem.getAudioInputStream(file);
                clip = AudioSystem.getClip();
                clip.open(sound);

                // Add LineListener to handle STOP event
                clip.addLineListener(new LineListener() {
                    @Override
                    public void update(LineEvent event) {
                        if (event.getType() == LineEvent.Type.STOP) {
                            // When STOP event is received, reset frame position and restart
                            clip.setFramePosition(0);
                            clip.start();
                        }
                    }
                });
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





