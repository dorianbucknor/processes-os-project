package OperatingSystems;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Scheduler {
    PriorityQueue<Process> processes = new PriorityQueue<>(20, queueComparator());
    Resources sharedResources;

    Scheduler(Resources resources){
        for (int i = 0; i < 20; i++) {
            processes.add(new Process(i));
        }
        sharedResources = resources;
    }

    public void run(){
        if(sharedResources == null){
            return;
        }
        processes.forEach(process -> process.asignTask(sharedResources));
    }



    Comparator<Process> queueComparator (){
        return Comparator.comparingInt(Process::getPriority);
    }
}
