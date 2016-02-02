package Multi;
import javax.sound.sampled.Port;

import jp.ac.kagawa_u.infoexpr.Sensor.TouchSensor;
import lejos.hardware.Brick;
import lejos.hardware.BrickFinder;
import lejos.hardware.Button;
import lejos.hardware.LED;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.Motor;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.hardware.sensor.SensorModes;
import lejos.remote.ev3.RMISampleProvider;
import lejos.remote.ev3.RemoteEV3;
import lejos.remote.ev3.RemoteRequestEV3;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;
import lejos.hardware.BrickFinder;
import lejos.hardware.port.*;

public class remoteTouchTest1
{
 
    public static void remoteTouchTest()
    {
        String[] names = {"EV3U", "EV3U2"};
        
        RemoteRequestEV3[] bricks = new RemoteRequestEV3[names.length];
        try {
            for(int i = 1; i < bricks.length; i++)
            {
                System.out.println("Connect " + names[i]);
                bricks[i] = new RemoteRequestEV3(BrickFinder.find(names[i])[0].getIPAddress());
            }
            //全ての元凶↓
            EV3TouchSensor touch = new EV3TouchSensor(bricks[1].getPort("S1"));
            //System.out.println("mimimin");
            
            //RMISampleProvider sp = (RMISampleProvider) touch.getMode("Touch");
            //float[] sample = sp.fetchSample();
            
            while(Button.ENTER.isUp())
            {
            	//sample = sp.fetchSample();
            	//for (int i = 0; i < sample.length; i++) {
            	//	System.out.println(i + " = " + sample[0]);
				//}
            }
            //sp.close();
            
            for(int k = 1; k < bricks.length; k++) {
                bricks[k].disConnect();
            }
            
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