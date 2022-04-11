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
        boolean b = false;

        while (processQueue.size() > 0){
            Process p1 = processQueue.poll();


            if (core1.isIdle()) {
                core1.runProcess(p1, sharedResources);
            } else {
                if (core2.isIdle()) {
                    if(p1.willModify() && processQueue.peek() != null){
                        processQueue.peek().setState(Process.State.BLOCKED);
                    }
                    core2.runProcess(p1, sharedResources);
                } else {
                    p1.setState(Process.State.BLOCKED);
                    processQueue.add(p1);
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
