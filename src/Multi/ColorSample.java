package Multi;
import java.awt.Color;

import javax.sound.sampled.Port;

import lejos.hardware.Brick;
import lejos.hardware.BrickFinder;
import lejos.hardware.Button;
import lejos.hardware.LED;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.Motor;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.remote.ev3.RemoteEV3;
import lejos.remote.ev3.RemoteRequestEV3;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;
import lejos.hardware.BrickFinder;
 
public class ColorSample
{
 
    public static void remoteLEDTest()
    {
        String[] names = {"EV3U", "EV3U2"};
        
        RemoteRequestEV3[] bricks = new RemoteRequestEV3[names.length];
        try {
            for(int i = 1; i < bricks.length; i++)
            {
                System.out.println("Connect " + names[i]);
                bricks[i] = new RemoteRequestEV3(BrickFinder.find(names[i])[0].getIPAddress());
            }
            EV3ColorSensor rightColor = new EV3ColorSensor(BrickFinder.getLocal().getPort("S2"));
            EV3ColorSensor leftColor = new EV3ColorSensor(BrickFinder.getLocal().getPort("S3"));
            
            RegulatedMotor[] rightMotors = new RegulatedMotor[bricks.length];
            RegulatedMotor[] leftMotors = new RegulatedMotor[bricks.length];
            
            rightMotors[0] = new EV3LargeRegulatedMotor(BrickFinder.getLocal().getPort("B"));
            leftMotors[0] = new EV3LargeRegulatedMotor(BrickFinder.getLocal().getPort("C"));
            			
            for(int i = 1; i < bricks.length; i++) {
                rightMotors[i] = bricks[i].createRegulatedMotor("B", 'L');
            	leftMotors[i] = bricks[i].createRegulatedMotor("C", 'L');
            }
            
            System.out.println("turai");
            
            SampleProvider rightSP = rightColor.getMode("ColorID");
            SampleProvider leftSP = leftColor.getMode("ColorID");
            float[] rightSample = new float[rightSP.sampleSize()];
            float[] leftSample = new float[leftSP.sampleSize()];
            
            while(Button.ENTER.isUp())
            {	
            	rightSP.fetchSample(rightSample, 0);
            	leftSP.fetchSample(leftSample, 0);
            	for (int i = 0; i < leftSample.length; i++) {
            		System.out.println( leftSample[i] + " , " + rightSample[i]);
            		Delay.msDelay(1000);
				}
                
            }
            
            for(RegulatedMotor m : rightMotors) {
            	m.close();
            }
            for(RegulatedMotor m : leftMotors) {
            	m.close();
            }
                
     
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
        remoteLEDTest();
    }
}