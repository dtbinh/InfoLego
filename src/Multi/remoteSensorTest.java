package Multi;
import java.io.Closeable;

import javax.sound.sampled.Port;
import javax.swing.text.html.HTMLDocument.HTMLReader.SpecialAction;

import lejos.hardware.Audio;
import lejos.hardware.BrickFinder;
import lejos.hardware.Button;
import lejos.remote.ev3.RMISampleProvider;
import lejos.remote.ev3.RemoteEV3;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;

 
public class remoteSensorTest
{
 
    public static void remoteTouchTest()
    {
        String[] names = {"EV3U", "EV3U2"};
        
        ;
        try {
            RemoteEV3 brick = new RemoteEV3(BrickFinder.find(names[1])[0].getIPAddress());
            System.out.println(BrickFinder.find(names[1])[0].getIPAddress());
            EV3TouchSensor touch = new EV3TouchSensor(brick.getPort("S1"));
            
            SampleProvider sp = touch.getTouchMode();
            
        	float[] sample = new float[1];
            while(Button.ENTER.isUp())
            {
            	sp = touch.getTouchMode();
            	sp.fetchSample(sample, 0);
            	for (int i = 0; i < sample.length; i++) {
            		System.out.println("Touch " + i + " = " + sample[i]);
            		Delay.msDelay(1000);
				}
            }
            touch.close();
        }
        catch (Exception e)
        {
            System.out.println("Got exception " + e);
            Delay.msDelay(10000);
        }
    }    
 
    public static void main(String[] args)
    {
        remoteTouchTest();
    }
}