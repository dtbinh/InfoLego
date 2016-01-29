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

public class SearchTower {

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
        int lowSpeed = 150;
        int highSpeed = 300;
        Boolean isFind = false;
        double average;

        while( ! Button.ESCAPE.isDown() ){
        	// 音響センサーの更新
        	while (distance.size() > 4) { distance.remove(0); }
        	distance.add(sonicSensor.getDistance());
        	average = getAverage(distance);
        	
        	LCD.clear();
            LCD.drawString(String.valueOf(sonicSensor.getDistance()), 1, 1);
            LCD.refresh();
        	
        	if ( average < 0.1 ) {
        		rightMotor.stop(true);
        	    leftMotor.stop();
        		return;
        	}
        	
        	if ( isFind ) {
        		motorSetSpeed( lowSpeed, lowSpeed );
            	motorForward();
            	continue;
        	}
        	
        	// タワーが見つかった場合
        	if ( average < 0.7 && distance.size() > 3 ) {
        		isFind = true;
        		Delay.msDelay(100);
                continue;
        	}
        	
        	// タワーが見つかるまでの回転
        	motorSetSpeed( lowSpeed, 0 );
        	motorForward();
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


