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

public class sameTimeStop {

    static TouchSensor touch = new TouchSensor(SensorPort.S1);
    // static LightSensor rightLight = new LightSensor(SensorPort.S2);
    // static LightSensor leftLight = new LightSensor(SensorPort.S3);
    static ColorSensor rightcolor = new ColorSensor(SensorPort.S2);
    static ColorSensor leftcolor = new ColorSensor(SensorPort.S3);
    static UltrasonicSensor sonicSensor = new UltrasonicSensor(SensorPort.S4);
    static RegulatedMotor rightMotor = Motor.B;
    static RegulatedMotor leftMotor = Motor.C;
    static ArrayList<Float> distance = new ArrayList<Float>();

    public static void main(String[] args) {
        int lowSpeed = 150;
        int highSpeed = 600;

        while (!Button.ESCAPE.isDown()) {
            motorSetSpeed(highSpeed, highSpeed);
            motorForward();
            Delay.msDelay(1000);
            rightMotor.stop();
            leftMotor.stop();

            motorSetSpeed(highSpeed, highSpeed);
            motorForward();
            Delay.msDelay(1000);
            rightMotor.stop(true);
            leftMotor.stop();
        }
    }

    private static void motorSetSpeed(int leftMotorSpeed, int rightMotorSpeed) {
        leftMotor.setSpeed(leftMotorSpeed);
        rightMotor.setSpeed(rightMotorSpeed);
    }

    private static void motorForward() {
        leftMotor.forward();
        rightMotor.forward();
    }

}
