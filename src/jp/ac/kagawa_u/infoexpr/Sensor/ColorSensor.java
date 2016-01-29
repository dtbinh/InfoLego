package jp.ac.kagawa_u.infoexpr.Sensor;

import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorMode;

public class ColorSensor extends EV3ColorSensor implements Sensor {

private SensorMode mode = this.getMode(2);

/**
 * ColorSensor�̃R���X�g���N�^
 * @param Port SensorPort
 */
	public ColorSensor(Port port) {
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
	 * RGB���擾
	 * @return float RGB���i�[���ꂽfloat�^�z��
	 */
	public float[] getRGB() {
		return this.getValue();
	}

	/**
	* RGB�̐Ԑ������擾
	* @return float RGB�̐Ԑ������i�[���ꂽfloat�^
	*/
	public float getRed() {
		return this.getValue()[0];
	}

	/**
	 * RGB�̗ΐ������擾
	 * @return float RGB�̗ΐ������i�[���ꂽfloat�^
	 */
	public float getGreen() {
		return this.getValue()[1];
	}

	/**
	 * RGB�̐������擾
	 * @return float RGB�̐������i�[���ꂽfloat�^
	 */
	public float getBlue() {
		return this.getValue()[2];
	}
}
