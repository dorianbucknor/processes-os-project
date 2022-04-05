package OperatingSystems;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Scheduler {
    PriorityQueue<Process> processQueue = new PriorityQueue<>(20, queueComparator());

    Resources sharedResources = new Resources();


    /**
     * Runs process scheduler. Assigns all processes with a task
     */
    public void run(){
        if(!sharedResources.resources.isEmpty()){ //if there are no resources do nothing
            processQueue.forEach(process -> {
                    process.executeTask(sharedResources);
            });
        }
    }


    public void addProcess(Process newProcess){
        processQueue.add(newProcess);
    }

    public boolean removeProcess(Process process){
        return  processQueue.remove(process);
    }

    /**
     * Tells priority queue how to order processes
     * @return comparator
     */
    Comparator<Process> queueComparator (){
        return new Comparator<Process>() {
            @Override
            public int compare(Process p1, Process p2) {
                return p1.getPriority() - p2.getPriority(); //sort in ascending order by priority
            }
        };
    }
}
