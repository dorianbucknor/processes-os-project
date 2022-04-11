package Simulator;


import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeoutException;

public class Core {
    private String id;
    private boolean isIdle;

    Thread thread;
    ThreadGroup threadGroup;
    int coreNumber;

    Core( int coreNumber, ThreadGroup threadGroup ) {
        isIdle = true;
        this.threadGroup = threadGroup;
        this.coreNumber = coreNumber;
    }

    public void runProcess(Process process, Resources resources) {
        isIdle = false;
        thread = new Thread(threadGroup, new Runnable() {
            @Override
            public void run() {
                try {
                    //Thread.currentThread().sleep(process.getBurstTime() * 1000L);
                    Thread.sleep(process.getBurstTime() * 1000L);
                    System.out.println("\nRunning on Core" + coreNumber);
                    process.setState(Process.State.RUNNING);

                    isIdle = process.executeTask(resources);
                    System.out.println("Finished on Core" + coreNumber);

                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                }
            }
        });
        thread.setPriority(coreNumber);
        thread.start();
    }

    public String getId() {
        return id;
    }

    public boolean isIdle() {
        return isIdle;
    }

}