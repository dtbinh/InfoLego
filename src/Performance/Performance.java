package Performance;
import java.awt.Color;

import javax.sound.sampled.Port;

import org.opencv.highgui.Highgui;

import lejos.hardware.Brick;
import lejos.hardware.BrickFinder;
import lejos.hardware.Button;
import lejos.hardware.LED;
import lejos.hardware.Sound;
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
 
public class Performance
{
 
	static RegulatedMotor[] rightMotors = new RegulatedMotor[3];
    static RegulatedMotor[] leftMotors = new RegulatedMotor[3];
    
    public static void PerformRun()
    {
        String[] names = {"EV3U", "EV3U2", "EV3U3"};
        int lowSpeed = 0, highSpeed = 200;
        
        RemoteRequestEV3[] bricks = new RemoteRequestEV3[names.length];
        try {
            for(int i = 1; i < bricks.length; i++)
            {
                System.out.println("Connect " + names[i]);
                bricks[i] = new RemoteRequestEV3(BrickFinder.find(names[i])[0].getIPAddress());
            }
            
            rightMotors[0] = new EV3LargeRegulatedMotor(BrickFinder.getLocal().getPort("B"));
            leftMotors[0] = new EV3LargeRegulatedMotor(BrickFinder.getLocal().getPort("C"));
            
            
            for(int i = 1; i < bricks.length; i++) {
                rightMotors[i] = bricks[i].createRegulatedMotor("B", 'L');
            	leftMotors[i] = bricks[i].createRegulatedMotor("C", 'L');
            }
            
            // スタート
            System.out.println("Ready");
            while(Button.ESCAPE.isUp()) {}
            System.out.println("Go");
            
            Thread musicThread = new Thread(new PlayMusic());
            Thread exitThread = new Thread(new RunExit());
            Thread runThread = new Thread(new PerformRun());
            
            musicThread.start();
            exitThread.start();
            runThread.start();
            
            try {
                musicThread.join();
                exitThread.join();
                runThread.join();
            } catch (InterruptedException e) {}
            
            
            
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
 
    public static RegulatedMotor[] getRightMotor() {
    	return rightMotors;
	}
    
    public static RegulatedMotor[] getLeftMotor() {
    	return leftMotors;
	}
    
    public static void main(String[] args)
    {
        PerformRun();
    }
    
}
