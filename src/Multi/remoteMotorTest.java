package Multi;
import javax.sound.sampled.Port;

import lejos.hardware.Brick;
import lejos.hardware.BrickFinder;
import lejos.hardware.Button;
import lejos.hardware.LED;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.Motor;
import lejos.remote.ev3.RemoteEV3;
import lejos.remote.ev3.RemoteRequestEV3;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;
import lejos.hardware.BrickFinder;
 
public class remoteMotorTest
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
            RegulatedMotor[] rightMotors = new RegulatedMotor[bricks.length];
            RegulatedMotor[] leftMotors = new RegulatedMotor[bricks.length];
            
            rightMotors[0] = new EV3LargeRegulatedMotor(BrickFinder.getLocal().getPort("B"));
            leftMotors[0] = new EV3LargeRegulatedMotor(BrickFinder.getLocal().getPort("C"));
            
            
            for(int i = 1; i < bricks.length; i++) {
                rightMotors[i] = bricks[i].createRegulatedMotor("B", 'L');
            	leftMotors[i] = bricks[i].createRegulatedMotor("C", 'L');
            }
            
            for(int i = 1; i < bricks.length; i++) {
                rightMotors[i] = bricks[i].createRegulatedMotor("B", 'L');
            	leftMotors[i] = bricks[i].createRegulatedMotor("C", 'L');
            }
            int i = 0;
            while(Button.ENTER.isUp())
            {
                rightMotors[(i) % bricks.length].setSpeed(100);
                leftMotors[(i) % bricks.length].setSpeed(0);
                rightMotors[(i) % bricks.length].forward();
                leftMotors[(i++) % bricks.length].forward();
                Delay.msDelay(100);
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
        }
    }    
 
    public static void main(String[] args)
    {
        remoteLEDTest();
    }
}