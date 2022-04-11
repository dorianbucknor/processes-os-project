package Simulator;

import java.time.LocalTime;
import java.util.concurrent.*;

public class Core {
    private String id;
    private boolean isIdle;

    ScheduledExecutorService executorService;
    Runnable runnable;

    Core(String id) {
        this.id = id;
        isIdle = true;
        executorService = new ScheduledThreadPoolExecutor(1);

    }

    public void runProcess(Process process, Resources resources) {

        ScheduledFuture<?> future;
        if (process != null) {
            isIdle = false;
            runnable = new Runnable() {
                @Override
                public void run() {
                    process.setStartTime(LocalTime.now());
                    process.setState(Process.State.RUNNING);
                    process.executeTask(resources);
                }
            };

            future = executorService.scheduleWithFixedDelay(runnable, 0, process.getBurstTime(), TimeUnit.SECONDS);
            isIdle = future.isDone();
            //process.endProcess();
        }
    }

    public String getId() {
        return id;
    }


    public boolean isIdle() {
        return isIdle;
    }

}