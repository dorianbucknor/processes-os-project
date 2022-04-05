package OperatingSystems;

import java.util.HashMap;

//Data Structure for resources list
//The shared resource list (list of integer pairs) can hold up to twenty (20) records which contains:
//        i.	Id (sequential list between 1 and 20)
//        ii.	Data value (integer )

public class Resources {
    /**
     * List of shared resources as a map
     */
    HashMap<Integer, Integer> resources = new HashMap<>(20);


    Resources (){
        //At the beginning of the program the shared resource list should initialize all data values to 0.
        for (int i = 0; i < 20; i++) {
            resources.put(i+1, 0);
        }
    }


    public HashMap<Integer, Integer> getResources() {
        return resources;
    }

    public void setResources(HashMap<Integer, Integer> resources) {
        this.resources = resources;
    }

    /**
     * Adds new record to resources
     * @param record the record to add
     */
    public void add(Record record){
        resources.put(record.getId(), record.getData());
    }

    /**
     * Gets a record from resources
     * @param id the record identifier
     * @return the record found, null otherwise
     */
    public Record getRecord(int id){
        if(resources.size() > 0){
            return new Record(resources.get(id), id);
        }
        return null;
    }

    /**
     * Removes a record from resources
     * @param id identifier of record to remove
     */
    public void remove(int id){
        if(resources.size() > 0){
           resources.replace(id, 0);
        }
    }

}
