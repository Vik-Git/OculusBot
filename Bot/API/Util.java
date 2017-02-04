package Bot.API;

/**
 * Created by Vik on 22/01/2017.
 */
public class Util {
    public static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
