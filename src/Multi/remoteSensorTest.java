package Multi;
import lejos.hardware.Brick;
import lejos.hardware.BrickFinder;
import lejos.hardware.Button;
import lejos.hardware.LED;
import lejos.remote.ev3.RemoteEV3;
import lejos.utility.Delay;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.Motor;
import lejos.remote.ev3.RemoteRequestEV3;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.SensorModes;
import lejos.remote.ev3.RemoteRequestSampleProvider;
 
public class remoteSensorTest
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
            EV3TouchSensor[] sensors = new EV3TouchSensor[bricks.length];
            SampleProvider[] touch = new SampleProvider[bricks.length];
            RegulatedMotor[] rightMotors = new RegulatedMotor[bricks.length];
            RegulatedMotor[] leftMotors = new RegulatedMotor[bricks.length];
            
            sensors[0] = new EV3TouchSensor(BrickFinder.getLocal().getPort("S1"));
            touch[0] = sensors[0].getMode("Touch");
            
            rightMotors[0] = new EV3LargeRegulatedMotor(BrickFinder.getLocal().getPort("B"));
            leftMotors[0] = new EV3LargeRegulatedMotor(BrickFinder.getLocal().getPort("C"));
            
            for(int i = 1; i < bricks.length; i++) {
            	
            	//touch[i] = bricks[i].createSampleProvider("S1", "lejos.hardware.sensor.EV3TouchSensor", "Touch");
            	
            	//sensors[i] = new EV3TouchSensor(bricks[i].getPort("S1"));
            	//touch[i] = sensors[i].getMode("Touch");
            	
            	System.out.println("mimimin");
                Delay.msDelay(1000);
            	
            	rightMotors[i] = bricks[i].createRegulatedMotor("B", 'L');
            	leftMotors[i] = bricks[i].createRegulatedMotor("C", 'L');
            }
            
            boolean each_touch = false;
            while(Button.ENTER.isUp())
            {
            	if ( each_touch ) { 
            		rightMotors[0].setSpeed(100);
            		leftMotors[0].setSpeed(0);
                    rightMotors[0].forward();
                    leftMotors[0].forward();
                    Delay.msDelay(100);
                } else {
                	rightMotors[0].stop(true);
                    leftMotors[0].stop();
				}
            	
            	float[] sample1 = new float[touch[0].sampleSize()];
            	
            	touch[0].fetchSample(sample1, 0);
            	for (int i = 0; i < sample1.length; i++) {
            		System.out.println("U1" + " = " + sample1[i]);
				}
            	
            	//bricks[1].createSampleProvider("S1", "lejos.hardware.sensor.EV3TouchSensor", "Touch").fetchSample(sample1, 0);;
            	
            	//float[] sample2 = new float[touch[1].sampleSize()];
            	//touch[1].fetchSample(sample2, 0);
            	for (int i = 0; i < sample1.length; i++) {
            		System.out.println("U2" + " = " + sample1[i]);
				}
            	//if ( sp.sampleSize() > 0 ) { each_touch = true; }
                //sp = touch[1].getTouchMode();
                //if ( sp.sampleSize() > 0 ) { each_touch = false; }
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
        remoteTouchTest();
    }
}