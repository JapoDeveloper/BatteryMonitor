package co.japo.tools.batterymonitor;

import co.japo.tools.batterymonitor.util.Kernel32;
import java.awt.Toolkit;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("\t\t*** Monitoring Laptop Battery ***");
        
        do {
            Kernel32.SYSTEM_POWER_STATUS batteryStatus = new Kernel32.SYSTEM_POWER_STATUS();
            Kernel32.INSTANCE.GetSystemPowerStatus(batteryStatus);
            System.out.println("Battery life: " + batteryStatus.getBatteryLife());
            Thread.sleep(3000 * 60);//1min
        } while (true);
        
    }

    private static void playAlertSound() {
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
