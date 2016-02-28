package Multi;
import lejos.hardware.Brick;
import lejos.hardware.BrickFinder;
import lejos.hardware.Button;
import lejos.hardware.LED;
import lejos.remote.ev3.RemoteEV3;
import lejos.utility.Delay;
 
public class remoteLEDTest
{
 
    public static void remoteLEDTest()
    {
        String[] names = {"EV3A", "EV3B"};
        
        Brick[] bricks = new Brick[names.length];
        try {
            bricks[0] = BrickFinder.getLocal();
            for(int i = 1; i < bricks.length; i++)
            {
                System.out.println("Connect " + names[i]);
                bricks[i] = new RemoteEV3(BrickFinder.find(names[i])[0].getIPAddress());
            }
            LED[] leds = new LED[bricks.length];
            for(int i = 0; i < bricks.length; i++)
                leds[i] = bricks[i].getLED();
            int i = 0;
            int pat = 1;
            while(Button.ENTER.isUp())
            {
                leds[(i++) % leds.length].setPattern(0);
                if (i % leds.length == 0)
                {
                    pat = ((pat + 1) % 3) + 1;
                }
                leds[(i) % leds.length].setPattern(pat);
                Delay.msDelay(100);
            }
            for(LED l : leds)
                l.setPattern(0);
        }
        catch (Exception e)
        {
            System.out.println("Got exception " + e);
        }
    }    
 
    public static void main(String[] args)
    {
        remoteLEDTest();
    }
}