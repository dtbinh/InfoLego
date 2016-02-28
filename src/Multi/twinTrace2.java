package Multi;

import lejos.hardware.BrickFinder;
import lejos.hardware.Button;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.remote.ev3.RemoteRequestEV3;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

public class twinTrace2 {

    public static void twinTrace() {
        String[] names = { "EV3A", "EV3B" };
        int lowSpeed = 0, highSpeed = 200;
        double BLACK = 0.2;

        RemoteRequestEV3[] bricks = new RemoteRequestEV3[names.length];
        try {
            for (int i = 1; i < bricks.length; i++) {
                System.out.println("Connect " + names[i]);
                bricks[i] = new RemoteRequestEV3(BrickFinder.find(names[i])[0].getIPAddress());
            }
            EV3ColorSensor rightColor = new EV3ColorSensor(BrickFinder.getLocal().getPort("S2"));
            EV3ColorSensor leftColor = new EV3ColorSensor(BrickFinder.getLocal().getPort("S3"));

            RegulatedMotor[] rightMotors = new RegulatedMotor[bricks.length];
            RegulatedMotor[] leftMotors = new RegulatedMotor[bricks.length];

            rightMotors[0] = new EV3LargeRegulatedMotor(BrickFinder.getLocal().getPort("B"));
            leftMotors[0] = new EV3LargeRegulatedMotor(BrickFinder.getLocal().getPort("C"));

            for (int i = 1; i < bricks.length; i++) {
                rightMotors[i] = bricks[i].createRegulatedMotor("B", 'L');
                leftMotors[i] = bricks[i].createRegulatedMotor("C", 'L');
            }

            SampleProvider rightSP = rightColor.getMode("Red");
            SampleProvider leftSP = leftColor.getMode("Red");
            float[] rightSample = new float[rightSP.sampleSize()];
            float[] leftSample = new float[leftSP.sampleSize()];

            System.out.println("Ready");
            // ライントレース処理
            while (Button.ENTER.isUp()) {}
            System.out.println("Go");

            float rightRed;
            float leftRed;

            while (Button.ESCAPE.isUp()) {
                rightSP.fetchSample(rightSample, 0);
                leftSP.fetchSample(leftSample, 0);

                rightRed = rightSample[0];
                leftRed = leftSample[0];
                // System.out.println( rightRed + " , " + leftRed );
                // 黒＆黒
                if (leftRed < BLACK && rightRed < BLACK) {
                    for (int i = 0; i < bricks.length; i++) {
                        rightMotors[i].setSpeed(lowSpeed);
                        leftMotors[i].setSpeed(lowSpeed);
                    }
                }
                // 黒＆白
                else if (leftRed < BLACK && rightRed > BLACK) {
                    for (int i = 0; i < bricks.length; i++) {
                        rightMotors[i].setSpeed(highSpeed);
                        leftMotors[i].setSpeed(lowSpeed);
                    }
                }
                // 白＆黒
                else if (leftRed > BLACK && rightRed < BLACK) {
                    for (int i = 0; i < bricks.length; i++) {
                        rightMotors[i].setSpeed(lowSpeed);
                        leftMotors[i].setSpeed(highSpeed);
                    }
                }
                // 白＆白
                else if (leftRed > BLACK && rightRed > BLACK) {
                    for (int i = 0; i < bricks.length; i++) {
                        rightMotors[i].setSpeed(highSpeed);
                        leftMotors[i].setSpeed(highSpeed);
                    }
                }
                
                if (leftMotors[1].getSpeed() == 0) {
                    rightMotors[1].rotate(90, true);
                    rightMotors[0].rotate(90);

                } else if (rightMotors[1].getSpeed() == 0) {
                    leftMotors[1].rotate(90, true);
                    leftMotors[0].rotate(90);
                } else {
                    rightMotors[1].rotate(90, true);
                    leftMotors[1].rotate(90, true);
                    rightMotors[0].rotate(90, true);
                    leftMotors[0].rotate(90);
                }
                Delay.msDelay(1000);
            }

            for (int i = 0; i < bricks.length; i++) {
                rightMotors[i].stop(true);
                leftMotors[i].stop();
            }
            for (RegulatedMotor m : rightMotors) {
                m.close();
            }
            for (RegulatedMotor m : leftMotors) {
                m.close();
            }
            for (int k = 1; k < bricks.length; k++) {
                bricks[k].disConnect();
            }
        } catch (Exception e) {
            System.out.println("Got exception " + e);
            Delay.msDelay(10000);
        }
    }

    public static void main(String[] args) {
        twinTrace();
    }
}
