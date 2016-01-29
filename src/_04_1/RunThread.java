package _04_1;

import lejos.hardware.motor.Motor;
import lejos.robotics.RegulatedMotor;

public class RunThread implements Runnable {

	private static RegulatedMotor leftMotor = Motor.B;
	private static RegulatedMotor rightMotor = Motor.C;

	@Override
	public void run() {
		while (TimerThread.getTimer() <= 1000) {
			if (SensorThread.getTouchStatus()) {
				this.forwardRobot(500);
			} else {
				this.backwardRobot(-500);
			}
		}
	}

	private void setSpeed(int speed) {
		leftMotor.setSpeed(speed);
		rightMotor.setSpeed(speed);
	}

	private void forwardRobot(int speed) {
		setSpeed(speed);
		leftMotor.forward();
		rightMotor.forward();
	}

	private void backwardRobot(int speed) {
		setSpeed(speed);
		leftMotor.backward();
		rightMotor.backward();
	}
}
