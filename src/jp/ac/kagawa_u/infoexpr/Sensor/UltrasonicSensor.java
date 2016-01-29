package jp.ac.kagawa_u.infoexpr.Sensor;

import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.hardware.sensor.SensorMode;

public class UltrasonicSensor extends EV3UltrasonicSensor implements Sensor {

	private SensorMode mode = this.getMode(0);

	/**
	 * UltrasonicSensor�̃R���X�g���N�^
	 * @param Port SensorPort
	 */
	public UltrasonicSensor(Port port) {
		super(port);
	}

	/**
	 * �Z���T�[�l���擾
	 * @return float[] �f�[�^���i�[���ꂽfloat�^�z��
	 */
	public float[] getValue() {
		float[] result = new float[mode.sampleSize()];
		mode.fetchSample(result, 0);
		return result;
	}

	/**
	* �����̒l���擾
	* @return float �����̒l���i�[���ꂽfloat�^
	*/
	public float getDistance() {
		return this.getValue()[0];
	}

}
