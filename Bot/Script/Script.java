package Bot.Script;

/**
 * Created by Vik on 23/11/2016.
 */
public abstract class Script implements Runnable{
    abstract int loop();
    abstract void onStart();
    abstract void onStop();

    public final void run() {
        onStart();
        while(!Thread.currentThread().isInterrupted()) {
            try {

                Thread.currentThread().sleep(loop());

            } catch (InterruptedException e) {
              e.printStackTrace();
            }
        }
        onStop();
    }
}
