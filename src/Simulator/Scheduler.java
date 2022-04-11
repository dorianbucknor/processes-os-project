package Simulator;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Timer;

public class Scheduler {
    PriorityQueue<Process> processQueue = new PriorityQueue<>(20, queueComparator());
    ThreadGroup threadGroup = new ThreadGroup("CPU");
    final Core core1 = new Core(1, threadGroup);
    final Core core2 = new Core( 2, threadGroup);

    Resources sharedResources = new Resources();

    /**
     * Runs process scheduler. Assigns all processes with a task
     */
    public void run() {
        System.out.println("Started");
        int elapsedTime = 0;

        while (processQueue.size() > 0) {
            Process process = processQueue.poll();

            if (core1.blockAll() || core2.blockAll()) {
                process.setState(Process.State.BLOCKED);
                process.setBlockedTime(elapsedTime);
                System.out.println("\tProcess Blocked\n\t\t " + process);
                processQueue.add(process);
            } else {
                if (core1.isIdle()) {
                    core1.runProcess(process, sharedResources);
                    elapsedTime += process.getBurstTime();
                } else {
                    if (core2.isIdle()) {
                        core2.runProcess(process, sharedResources);
                        elapsedTime += process.getBurstTime();
                    } else {
                        process.setState(Process.State.BLOCKED);
                        process.setBlockedTime(elapsedTime);
                        /*System.out.println("\tProcess Blocked\n\t\t " + process.toString());*/
                        processQueue.add(process);
                    }
                }
            }
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
                "\nresources="+ sharedResources.toString()+
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
