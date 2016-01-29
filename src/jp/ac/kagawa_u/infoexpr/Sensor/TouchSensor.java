package jp.ac.kagawa_u.infoexpr.Sensor;

import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.SensorMode;

public class TouchSensor extends EV3TouchSensor implements Sensor {

	private SensorMode mode = this.getMode(0);

	/**
	 * TouchSensorのコンストラクタ
	 * @param Port SensorPort
	 */
	public TouchSensor(Port port) {
		super(port);
	}

	/**
	 * センサー値を取得
	 * @return float[] データが格納されたfloat型配列
	 */
	public float[] getValue() {
		float[] result = new float[mode.sampleSize()];
		mode.fetchSample(result, 0);
		return result;
	}

	/**
	 * タッチセンサの押下を取得
	 * @return boolean タッチセンサの状態が格納されたboolean型
	 */
	public boolean isPressed() {
		float[] values = this.getValue();
		return values[0] == 1 ? true : false;
	}
}
