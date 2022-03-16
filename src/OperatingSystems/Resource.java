package OperatingSystems;

public class Resource {
    private int data;
    private int id;

    public Resource(int data, int id) {
        this.data = data;
        this.id = id;
    }

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
