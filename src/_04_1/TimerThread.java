package _04_1;

import lejos.utility.Delay;

public class TimerThread implements Runnable {
	private static int timer = 0;

	@Override
	public void run() {
		while (timer <= 10000) {
			Delay.msDelay(100);
			timer += 100;
		}
	}

	public static int getTimer() {
		return timer;
	}
}
