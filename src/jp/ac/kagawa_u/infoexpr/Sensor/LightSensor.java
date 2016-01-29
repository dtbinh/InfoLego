package jp.ac.kagawa_u.infoexpr.Sensor;

import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorMode;

public class LightSensor extends EV3ColorSensor implements Sensor {

	private SensorMode mode = this.getMode(1);

	/**
	 * LightSensor�̃R���X�g���N�^
	 * @param Port SensorPort
	 */
	public LightSensor(Port port) {
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
	* ���ˌ��̒l���擾
	* @return float ���ˌ��̒l���i�[���ꂽfloat�^
	*/
	public float getLight() {
		return this.getValue()[0];
	}

}
