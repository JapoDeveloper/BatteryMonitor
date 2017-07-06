package co.japo.tools.batterymonitor.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

/**
 *
 * @author JapoDeveloper
 */
public class SoundUtil {
    
    public static void playAlert(){
         Thread soundPlayer = new Thread(new Runnable() {
            public void run() {
                try {
                    File soundFile = new File(getClass().getClassLoader().getResource("sounds/tone.wav").getFile());
                    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream(soundFile)));
                    AudioFormat audioFormat = audioInputStream.getFormat();
                    DataLine.Info dataLineInfo = new DataLine.Info(Clip.class, audioFormat);
                    Clip clip = (Clip) AudioSystem.getLine(dataLineInfo);
                    clip.open(audioInputStream);
                    clip.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        soundPlayer.start();
    }
}
