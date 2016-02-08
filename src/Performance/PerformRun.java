package Performance;

import java.awt.Color;

import javax.sound.sampled.Port;

import org.opencv.highgui.Highgui;

import lejos.hardware.Brick;
import lejos.hardware.BrickFinder;
import lejos.hardware.Button;
import lejos.hardware.LED;
import lejos.hardware.Sound;
import lejos.hardware.device.MSC;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.Motor;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.remote.ev3.RemoteEV3;
import lejos.remote.ev3.RemoteRequestEV3;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

public class PerformRun implements Runnable {

	@Override
	public void run() {
		RegulatedMotor[] rightMotors = Performance.getRightMotor();
		RegulatedMotor[] leftMotors = Performance.getLeftMotor();

		Delay.msDelay(3000);

		// 反転
		rightMotors[1].rotate(-180, true);
		leftMotors[1].rotate(180, true);

		rightMotors[2].rotate(180, true);
		leftMotors[2].rotate(-180);

		// 直進
		rightMotors[1].rotate(1000, true);
		leftMotors[1].rotate(1000, true);

		rightMotors[2].rotate(1000, true);
		leftMotors[2].rotate(1000);

		// 反転
		rightMotors[1].rotate(360, true);
		leftMotors[1].rotate(-360, true);

		rightMotors[2].rotate(-360, true);
		leftMotors[2].rotate(360);

		// 直進
		rightMotors[1].rotate(1000, true);
		leftMotors[1].rotate(1000, true);

		rightMotors[2].rotate(1000, true);
		leftMotors[2].rotate(1000);

		// 反転

		rightMotors[1].rotate(180, true);
		leftMotors[1].rotate(-180, true);

		rightMotors[2].rotate(-180, true);
		leftMotors[2].rotate(180);

		// 交差
		rightMotors[1].setSpeed(500);
		leftMotors[1].setSpeed(1000);

		rightMotors[2].setSpeed(1000);
		leftMotors[2].setSpeed(500);

		rightMotors[1].rotate(2000, true);
		leftMotors[1].rotate(1000, true);

		Delay.msDelay(1000);

		rightMotors[2].rotate(1000, true);
		leftMotors[2].rotate(2000);
		
		// 直進
		rightMotors[0].rotate(1500, true);
		leftMotors[0].rotate(1500);
		

		rightMotors[1].setSpeed(1000);
		leftMotors[1].setSpeed(1000);

		rightMotors[2].setSpeed(1000);
		leftMotors[2].setSpeed(1000);
		//while (Button.ESCAPE.isUp()) {
		//	for (int i = 0; i < leftMotors.length; i++) {
		//		rightMotors[i].rotate(2000, true);
		//		leftMotors[i].rotate(2000, true);
		//	}	
		//}
		
		for (int i = 0; i < rightMotors.length; i++) {
			rightMotors[i].stop(true);
			leftMotors[i].stop();
		}
	}

}