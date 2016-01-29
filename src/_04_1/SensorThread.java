package _04_1;

import jp.ac.kagawa_u.infoexpr.Sensor.TouchSensor;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;
import lejos.utility.Delay;

public class SensorThread implements Runnable {

	private static TouchSensor touchSensor = new TouchSensor(SensorPort.S1);
	private static boolean status = true;

	@Override
	public void run() {
		while (TimerThread.getTimer() <= 10000) {
			toggleStatus();
			LCD.clearDisplay();
			LCD.drawString(String.valueOf(status), 3, 3);
		}
	}

	public static void toggleStatus() {
		if (touchSensor.isPressed()) {
			status = ! status;
			Delay.msDelay(500);
		}
	}

	public static boolean getTouchStatus() {
		return status;
	}
}
