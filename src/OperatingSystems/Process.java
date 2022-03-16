package OperatingSystems;

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


    public Process( int priority, int burstTime) {
        this.pid = getRandomInt(1, 20);
        this.priority = priority;
        this.burstTime = getRandomInt(1, 5);
        this.startTime = LocalTime.now();
    }
    private int getRandomInt(int min, int max){
        Random random = new Random();
        return random.nextInt(max + 1 - min) + min;
    }

    public void AddRecord (int data, Resource resource){

    }


    public int getPid() {
        return pid;
    }

    public int getPriority() {
        return priority;
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