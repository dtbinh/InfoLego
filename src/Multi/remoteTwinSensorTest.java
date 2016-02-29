package Multi;

import lejos.hardware.BrickFinder;
import lejos.hardware.Button;
import lejos.remote.ev3.RemoteEV3;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;
import lejos.hardware.sensor.EV3TouchSensor;

public class remoteTwinSensorTest {

    public static void remoteTouchTest() {
        String[] names = { "EV3A", "EV3B" }; // EV3の名前

        try {
            RemoteEV3 brick[] = new RemoteEV3[names.length];
            for (int i = 1; i < brick.length; i++) {
                brick[i] = new RemoteEV3(
                        BrickFinder.find(names[i])[0].getIPAddress());
                System.out
                        .println(BrickFinder.find(names[i])[0].getIPAddress());
            }

            // センサーのインスタンスの生成
            EV3TouchSensor touch[] = new EV3TouchSensor[names.length];
            touch[0] = new EV3TouchSensor(BrickFinder.getLocal().getPort("S1"));
            for (int i = 1; i < touch.length; i++) {
                touch[i] = new EV3TouchSensor(brick[i].getPort("S1"));
            }

            // センサーの取得したもの一度SampleProviderに保存する
            SampleProvider sp[] = new SampleProvider[names.length];

            float[] sample = new float[1];
            while (Button.ESCAPE.isUp()) {
                for (int i = 0; i < names.length; i++) {
                    // センサーの値を更新
                    sp[i] = touch[i].getTouchMode();
                    sp[i].fetchSample(sample, 0);

                    for (int j = 0; j < sample.length; j++) {
                        System.out.println(names[i] + "Touch" + " = "
                                + sample[j]);
                        Delay.msDelay(1000);
                    }
                }
            }

            // センサーの接続終了の処理
            for (int i = 0; i < names.length; i++) {
                touch[i].close();
            }
        } catch (Exception e) {
            System.out.println("Got exception " + e);
            Delay.msDelay(10000);
        }
    }

    public static void main(String[] args) {
        remoteTouchTest();
    }
}