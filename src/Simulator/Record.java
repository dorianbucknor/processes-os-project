package Simulator;


public class Record {
    private int data;
    private int id;


    /**
     * Creates a new resource
     * @param data data value
     * @param id identifier
     */
    public Record(int data, int id) {
        this.data = data;
        this.id = id;
    }



    //Getters and Setters
    public void setData(int data) {
        this.data = data;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getData() {
        return data;
    }

    public int getId() {
        return id;
    }
}
