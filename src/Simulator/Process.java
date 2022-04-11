package Simulator;

import java.util.Random;
import java.time.LocalTime;

/**
 * Class for Process Data Structure with data members specified below:
 *  i.	 pid (uniquely assigned between 1 and 20)
 * ii.	 task (a randomly select option of the four above)
 * iii.  priority (integer value 1 – 5 with 1 being the highest)
 * iv.	 arrival time (randomized number between 0 and 29)
 * v.	 start time (system time)
 * vi.	 end time (system time)
 * vii.	 blocked time (numeric value)
 * viii. burst time (randomized number between 1 and 5 seconds)`
 */
public class Process {
    private int pid;
    private int priority;
    private int arrivalTime;
    private LocalTime startTime;
    private LocalTime endTime;
    private int blockedTime;
    private int burstTime;
    private Task task = Task.NONE;
    private State state;

    /**
     * Constructor for Process. Creates a new process object that has methods to read/write shared resources
     * @param pid process id
     */
    public Process(int pid) {
        this.pid = pid;
        this.priority = getRandomInt(1, 5); //sets a random priority between and inclusive of 1 and 5
        this.burstTime = getRandomInt(1, 5); // sets a random burst time between and inclusive of 1 and 5
        this.startTime = LocalTime.now(); // sets start time to system time when process object was created
        arrivalTime = getRandomInt(0, 29); // sets a random arrival time between and inclusive of 0 and 29
        state = State.NEW;
    }

    /**
     * Generates a random integer between min and max, and inclusive of both min and max
     * @param min minimum value
     * @param max maximum value
     * @return an integer between and inclusive of min and max value
     */
    private int getRandomInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(min, max + 1);
    }

    /**
     * Ends this process
     */
    public void endProcess(){
        task = Task.NONE;
        state = State.TERMINATED;
        endTime = LocalTime.now();
    }

    /**
     * Randomly assigns a task to this process
     */
    public void assignTask(){
        switch (getRandomInt(1, 4)) {
            case 1 -> task = Task.ADD;
            case 2 -> task = Task.REMOVE;
            case 3 -> task = Task.RETRIEVE;
            case 4 -> task = Task.CALCULATE;
        }
        state = State.READY;
    }

    /**
     * Task that adds a new resource to resources
     * @param resources the shared resources to complete the task on
     */
    public void AddRecord(Resources resources) {
        Record record = resources.remove(getRandomInt(1, 20)); //gets a random resourcs
        System.out.println("\tRecord Update (ADD):\n\t\tResource Id: " + record.getId() + " - Resource Data: " + record.getData());
        record.setData(getRandomInt(1, 100));
        resources.add(record); // changes its value to an integer between and inclusive of 1 and 100
        System.out.println("\t\tResource Id: " + record.getId() + " - Resource Data: " + record.getData());
    }

    /**
     * Task that removes a resource from shared resources by setting its value to 0
     * @param resources the shared resources to complete the task on
     */
    public void delete(Resources resources) {
        Record record = resources.getRecord(getRandomInt(1,20));
        System.out.println("\tRecord Update (REMOVE):\n\t\tResource Id: " + record.getId() + " - Resource Data: " + record.getData());
        record = resources.remove(record.getId());
        System.out.println("\t\tResource Id: " + record.getId() + " - Resource Data: " + record.getData());
    }

    /**
     * Task that retrieves a resource from shared resources and output its data
     * @param resources the shared resources to complete the task on
     */
    public void retrieve(Resources resources) {
        Record record = resources.getRecord(getRandomInt(1,20));
        System.out.println("\tRETRIEVE: \n\t\tResource Id: " + record.getId() + " - Resource Data: " + record.getData());
        //output that resources data
    }

    /**
     * Task that calculates all the values of the shared resources and outputs the total value.
     * @param resources the shared resources to complete the task on
     */
    public void calculate(Resources resources) {
        //this is the same as looping through the list of data values and adding them to a variable and then
        // printing the total
        resources.getResources().values().stream().reduce(Integer::sum).ifPresent(total -> System.out.println(
                "\t\tTotal" +
                " Value: " + total));
    }

    /**
     * Executes given task on current record
     */
    public boolean executeTask(Resources resources){
        state = State.RUNNING;
        System.out.println("\t\t" + this.toString());
        switch (task){
            case NONE -> {} //do nothing
            case ADD -> AddRecord(resources);
            case RETRIEVE -> retrieve(resources);
            case REMOVE -> delete(resources);
            case CALCULATE -> calculate(resources);
        }
        task = Task.NONE;
        state = State.TERMINATED;
        endTime = LocalTime.now();
        System.out.println("\t\t" + this.toString());
        return true;
    }

    /**
     * Checks if this process will modify the contents of the shared resources
     * @return true if task is a modifying task, false otherwise
     */
    public boolean willModify(){
        return task == Task.ADD || task == Task.REMOVE;
    }

    //Getters and setters
    public int getPid() {
        return pid;
    }

    public int getPriority() {
        return priority;
    }

    public Task getTask() {
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

    public void setTask(Task task) {
        this.task = task;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Process{" +
                "pid=" + pid +
                ", priority=" + priority +
                ", arrivalTime=" + arrivalTime +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", blockedTime=" + blockedTime +
                ", burstTime=" + burstTime +
                ", task=" + task +
                ", state=" + state +
                '}';
    }

    /**
     * Task enum
     */
    public enum Task{
        NONE,
        ADD,
        REMOVE,
        RETRIEVE,
        CALCULATE
    }

    public enum State {
        NEW,
        READY,
        RUNNING,
        WAITING,
        SUSPENDED,
        BLOCKED,
        TERMINATED
    }
}