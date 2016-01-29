package _04_1;

public class Main {
	public static void main(String[] args) {
		Thread sensorThread = new Thread(new SensorThread());
		Thread runThread = new Thread(new RunThread());
		Thread timerThread = new Thread(new TimerThread());

		sensorThread.start();
		runThread.start();
		timerThread.start();
		try {
			sensorThread.join();
			runThread.join();
			timerThread.join();
		} catch (InterruptedException e) { }
	}
}
