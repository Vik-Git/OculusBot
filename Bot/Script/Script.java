package Bot.Script;

/**
 * Created by Vik on 23/11/2016.
 */
public abstract class Script implements Runnable{
    public abstract int loop() throws InterruptedException;
    public abstract void onStart();
    public abstract void onStop();

    public final void run() {
        onStart();
        try {

        } catch (Exception e) {
            System.out.println("Substance Graphite failed to initialize");
        }
        while(!Thread.currentThread().isInterrupted()) {

            try {
                Thread.currentThread().sleep(loop());
            } catch (InterruptedException e) {
                break;
            }


        }
        onStop();
    }
}
