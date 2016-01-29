package kadai04_searchMaterial;

import java.util.ArrayList;

import jp.ac.kagawa_u.infoexpr.Sensor.ColorSensor;
import jp.ac.kagawa_u.infoexpr.Sensor.TouchSensor;
import jp.ac.kagawa_u.infoexpr.Sensor.UltrasonicSensor;
import lejos.hardware.Sound;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.SensorPort;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;
import lejos.hardware.lcd.LCD;
import lejos.hardware.Button;

public class TowerTurn {

    static TouchSensor touch = new TouchSensor(SensorPort.S1);
   // static LightSensor rightLight = new LightSensor(SensorPort.S2);
   // static LightSensor leftLight = new LightSensor(SensorPort.S3);
    static ColorSensor rightcolor = new ColorSensor(SensorPort.S2);
    static ColorSensor leftcolor = new ColorSensor(SensorPort.S3);
    static UltrasonicSensor sonicSensor = new UltrasonicSensor(SensorPort.S4);
    static RegulatedMotor rightMotor  = Motor.B;
    static RegulatedMotor leftMotor  = Motor.C;
    static ArrayList<Float> distance  = new ArrayList<Float>();   
    
    public static void main(String[] args) {
        int lowSpeed = 100;
        int highSpeed = 300;
        Boolean turnFlag = true;
        double average;

        while( ! Button.ESCAPE.isDown() ){
        	while (distance.size() > 4) { distance.remove(0); }
        	distance.add(sonicSensor.getDistance());
        	average = getAverage(distance);
        	if ( average < 0.4 && turnFlag && distance.size() > 3 ) {
        		motorSetSpeed(highSpeed, highSpeed);
        		motorForward();
        		Delay.msDelay(500);
        		motorSetSpeed(400, 600);
        		motorForward();
        		Delay.msDelay(7000);
        		turnFlag = false;
        	}
        	
        	// デバック出力
        	//LCD.clear();
            //LCD.drawString(String.valueOf(sonicSensor.getDistance()), 1, 1);
            //LCD.refresh();
        	
        	// 黒＆黒
            if( leftcolor.getColorID() == 7 && rightcolor.getColorID() == 7){
            	//motorSetSpeed(360, 0);
                //motorForward();
                //Delay.msDelay(1000);    		
            }
            // 黒＆白
            else if( leftcolor.getColorID() == 7   && rightcolor.getColorID() == 6 ){
                motorSetSpeed(lowSpeed, highSpeed);
                motorForward();
            }
            // 白＆黒
            else if((leftcolor.getColorID() == 1 || leftcolor.getColorID() == 6 )  && rightcolor.getColorID() == 7){
                motorSetSpeed(highSpeed, lowSpeed);
                motorForward();
                if (leftcolor.getColorID() == 1){

            		          Sound.beep();

            	}
            }
            // 白＆白
            else if((leftcolor.getColorID() == 1 || leftcolor.getColorID() == 6 ) && rightcolor.getColorID() == 6){
                motorSetSpeed(highSpeed, highSpeed);
                motorForward();
                if (leftcolor.getColorID() == 1){
            		          Sound.beep();
            	}
            }
                 
        }
    }

    private static void motorSetSpeed(int leftMotorSpeed, int rightMotorSpeed){
        leftMotor.setSpeed(leftMotorSpeed);
        rightMotor.setSpeed(rightMotorSpeed);
    }

    private static void motorForward(){
        leftMotor.forward();
        rightMotor.forward();
    }
    
    private static Double getAverage( ArrayList<Float> lists ){
    	double ave = 0;
    	for (Float list : lists ) {
    		ave += list;
		}
    	ave /= lists.size();
    	return ave;
    }
}


