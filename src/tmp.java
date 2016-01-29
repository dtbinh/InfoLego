import jp.ac.kagawa_u.infoexpr.Sensor.LightSensor;
import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;
import jp.ac.kagawa_u.infoexpr.Sensor.ColorSensor;
import lejos.utility.Delay;
 
public class tmp {
 
	 static ColorSensor color = new ColorSensor(SensorPort.S2);
	    static float value;
	 
	    public static void main(String[] args) {
	        while( ! Button.ESCAPE.isDown() ) {
	            LCD.clear();
	            LCD.drawString("R:" + color.getRed(), 0, 0);
	            LCD.drawString("G:" + color.getGreen(), 0, 1);
	            LCD.drawString("B:" + color.getBlue(), 0, 2);
	            LCD.refresh();
	            Delay.msDelay(1000);
	        }
	        LCD.clear();
	        LCD.refresh();
	    }
}