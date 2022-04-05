import OperatingSystems.Process;
import OperatingSystems.Scheduler;

public class Main {

    public static void main(String[] args) {
        Scheduler scheduler = new Scheduler();

        //Create and add processes
        for (int i = 0; i < 20; i++) {
            Process process = new Process(i);
            process.assignTask();
            scheduler.addProcess(process);
        }

        scheduler.run();
    }
}
