package OperatingSystems;

import java.util.HashMap;
import java.util.Optional;
import java.util.Random;
import java.time.LocalTime;

public class Process {
//   ` i.	pid (uniquely assigned between 1 and 20)
//            ii.	task (a randomly select option of the four above)
//            iii.	priority (integer value 1 – 5 with 1 being the highest)
//    iv.	arrival time (randomized number between 0 and 29)
//    v.	start time (system time)
//    vi.	end time (system time)
//    vii.	blocked time (numeric value)
//    viii.	burst time (randomized number between 1 to 5 seconds)`

    private int pid;
    private int priority;
    private int arrivalTime;
    private LocalTime startTime;
    private LocalTime endTime;
    private int blockedTime;
    private int burstTime;
    private String task = "none";


    public Process(int priority) {
        this.pid = getRandomInt(1, 20);
        this.priority = priority;
        this.burstTime = getRandomInt(1, 5);
        this.startTime = LocalTime.now();
        arrivalTime = getRandomInt(0, 29);
    }

    private int getRandomInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(min, max + 1);
    }

    public void end(){
        endTime = LocalTime.now();
    }

    public void asignTask(Resources resources){
        switch (getRandomInt(1, 4)) {
            case 1 -> AddRecord(resources);
            case 2 -> delete(resources);
            case 3 -> retrieve(resources);
            case 4 -> calculate(resources);
        }
    }

    public void AddRecord(Resources resources) {
        task = "add";
        resources.add(new Resource(getRandomInt(1, 20), getRandomInt(1, 100)));
    }

    public void delete(Resources resources) {
        task = "remove";
        resources.remove(new Resource(0, getRandomInt(1, 20)));
    }

    public void retrieve(Resources resources) {
        task = "retrieve";
        Resource resource = resources.getResource(getRandomInt(1, 20));
        System.out.println("Resource Id: " + resource.getId() + " - Resource Data: " + resource.getData());
    }

    public void calculate(Resources resources) {
        task = "calculate";
        resources.getResources().values().stream().reduce(Integer::sum).ifPresent(total -> System.out.println("Total" +
                " Value: " + total));
    }

    public int getPid() {
        return pid;
    }

    public int getPriority() {
        return priority;
    }

    public String getTask() {
        return task;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public int getBlockedTime() {
        return blockedTime;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public void setBlockedTime(int blockedTime) {
        this.blockedTime = blockedTime;
    }

    public void setBurstTime(int burstTime) {
        this.burstTime = burstTime;
    }
}