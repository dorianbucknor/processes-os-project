package Simulator;


public class Core {
    private String id;
    private boolean isIdle;

    Thread thread;
    Runnable runnable;

    Core(String id) {
        this.id = id;
        isIdle = true;
    }

    public void runProcess(Process process, Resources resources) {
        isIdle = false;
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("\nRunning on Core" + id);
                process.setState(Process.State.RUNNING);
                isIdle = process.executeTask(resources);
                System.out.println("Finished on Core" + id);
            }
        });
        thread.start();
    }

    public String getId() {
        return id;
    }

    public boolean isIdle() {
        return isIdle;
    }

}