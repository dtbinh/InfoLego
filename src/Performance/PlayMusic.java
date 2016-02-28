package Performance;

import java.io.File;
import lejos.hardware.Sound;

public class PlayMusic implements Runnable {
    @Override
    public void run() {
        String fileName = "sample.wav";
        File wavFile = new File("./" + fileName);
        Sound.playSample(wavFile);
    }
}
