package jp.ac.kagawa_u.infoexpr.Sensor;

import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorMode;

public class ColorIDSensor extends EV3ColorSensor implements Sensor {

private SensorMode mode = this.getMode(0);


	/**
	 * ColorIDSensorのコンストラクタ
	 * @param Port SensorPort
	 */
	public ColorIDSensor(Port port) {
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
	 * 色を取得
	 * @return float 色が格納されたfloat型
	 */
	public float[] getColor() {
		return this.getValue();
	}

	/**
	 * 色を取得
	 * @return String データが格納されたString型配列
	 */
	public String getColorName()
	{
		switch (this.getColorID())
		{
		case 0: return "ENPTY";
		case 1: return "BLACK";
		case 2: return "BLUE";
		case 3: return "GREEN";
		case 4: return "YELLOW";
		case 5: return "RED";
		case 6: return "WHITE";
		case 7: return "BROWN";
		default: return "FAILE";
		}
	}
}
