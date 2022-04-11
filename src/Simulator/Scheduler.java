package Simulator;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Timer;

public class Scheduler {
    PriorityQueue<Process> processQueue = new PriorityQueue<>(20, queueComparator());

    final Core core1 = new Core("1");
    final Core core2 = new Core("2");

    Resources sharedResources = new Resources();

    /**
     * Runs process scheduler. Assigns all processes with a task
     */
    public void run() {
        System.out.println("Started");
        while (!processQueue.isEmpty()) {
            Process process = processQueue.poll();
                if (core1.isIdle()) {
                    core1.runProcess(process, sharedResources);
                } else if (core2.isIdle()) {
                    core2.runProcess(process, sharedResources);
                } else {

                }
            //run();
        }
        System.out.println("Done");
    }


    public void addProcess(Process newProcess){
        processQueue.add(newProcess);
    }

    public boolean removeProcess(Process process){
        return  processQueue.remove(process);
    }

    @Override
    public String toString() {
        return "Scheduler{" +
                "processQueue=" + processQueue.toString() +
                '}';
    }

    /**
     * Tells priority queue how to order processes
     * @return comparator
     */
    Comparator<Process> queueComparator (){
        return Comparator.comparingInt(Process::getArrivalTime).thenComparing(Process::getPriority);
    }
}
