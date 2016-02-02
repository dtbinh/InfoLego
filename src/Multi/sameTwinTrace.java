package Multi;
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
 
public class sameTwinTrace
{

	public class angleMoter implements Runnable {
		RegulatedMotor motor;
		public angleMoter(RegulatedMotor mtr) {
			motor = mtr;
		}
		
		@Override
		public void run() {
			motor.rotate(120);
		}
	}
 
    public static void remoteLEDTest()
    {
        String[] names = {"EV3U", "EV3U2"};
        int lowSpeed = 0, highSpeed = 300;
        double BLACK = 0.2, WHITE = 0.9;
        int angle = 120;
        
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
            
            SampleProvider rightSP = rightColor.getMode("Red");
            SampleProvider leftSP = leftColor.getMode("Red");
            float[] rightSample = new float[rightSP.sampleSize()];
            float[] leftSample = new float[leftSP.sampleSize()];
            
            System.out.println("Start");
            // ライントレース処理
            while(Button.ESCAPE.isUp()) {}
            
            while(Button.ENTER.isUp())
            {
            	rightSP.fetchSample(rightSample, 0);
            	leftSP.fetchSample(leftSample, 0);
            	float rightRed = rightSample[0];
            	float leftRed = leftSample[0];
            	
            	System.out.println( rightRed + " , " + leftRed );
            	
            	// 黒＆黒
                if( leftRed < BLACK && rightRed < BLACK  ) {
                	
                }
                // 黒＆白
                else if( leftRed < BLACK && rightRed > BLACK ) {
                	for (int i = 0; i < bricks.length; i++) {
                		//rightMotors[i].setSpeed(highSpeed);
                        //leftMotors[i].setSpeed(lowSpeed);
                        //rightMotors[i].forward();
                        //leftMotors[i].forward();
                		rightMotors[i].rotate(angle);
					}
                }
                // 白＆黒
                else if( leftRed > BLACK && rightRed < BLACK ) {
                	for (int i = 0; i < bricks.length; i++) {
                		//rightMotors[i].setSpeed(lowSpeed);
                        //leftMotors[i].setSpeed(highSpeed);
                        //rightMotors[i].forward();
                        //leftMotors[i].forward();
                		leftMotors[i].rotate(angle);
					}
                }
                // 白＆白
                else if( leftRed > BLACK && rightRed > BLACK ) {
                	for (int i = 0; i < bricks.length; i++) {
                		//rightMotors[i].rotate(angle);
                        //leftMotors[i].rotate(angle);
                		Thread rightThread = new Thread( new sameTwinTrace().new angleMoter(rightMotors[i]) );
                		Thread leftThread = new Thread( new sameTwinTrace().new angleMoter(leftMotors[i]) );
                		rightThread.start();
                		leftThread.start();
                		 try {
                			 rightThread.join();
                	         leftThread.join();
                	     } catch (InterruptedException e) {
                	    	 System.out.println("Got exception " + e);
                	         Delay.msDelay(10000);
                	     }
                	}
                }
            }
            
            for (int i = 0; i < bricks.length; i++) {
        	    rightMotors[i].stop(true);
                leftMotors[i].stop();	
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
