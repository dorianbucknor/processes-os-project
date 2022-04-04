package OperatingSystems;

import java.util.HashMap;

public class Resources {
    HashMap<Integer, Integer> resources = new HashMap<>();

    Resources (){
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

    public void add(Resource resource){
        resources.put(resource.getId(), resource.getData());
    }

    public Resource getResource(int id){
        if(resources.size() > 0){
            return new Resource(resources.get(id), id);
        }
        return null;
    }

    public void remove(Resource resource){
        if(resources.size() > 0){
           resources.replace(resource.getId(), resource.getData());
        }
    }

}
