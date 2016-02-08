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

public class RunExit implements Runnable {

	@Override
	public void run() {
		while (Button.ENTER.isUp()) {
		}
		System.exit(0);
	}
}
