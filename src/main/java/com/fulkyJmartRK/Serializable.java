package com.fulkyJmartRK;

import java.util.HashMap;
import java.util.Map;

public class Serializable implements Comparable <Serializable>
{
    public final int id;
    private static HashMap<Class<?>, Integer> mapCounter = new HashMap<>();

    protected Serializable(){
        Integer counter = mapCounter.get(getClass());
        if(counter == null) counter = -1;
        mapCounter.put(getClass(), counter+1);
        this.id = mapCounter.get(getClass());
    }
    public static <T extends Serializable> int setClosingId(Class<T> clazz, int id){
        int prevId = mapCounter.get(clazz);
        mapCounter.replace(clazz, id);
        System.out.println(mapCounter.get(clazz));
        return prevId;
    }
    public static <T extends Serializable> int getClosingId(Class<T> clazz){
        return mapCounter.get(clazz);
    }
    public boolean equals(Object other){
        if (other instanceof Serializable){
            Serializable recognizable = (Serializable) other;
            return recognizable.id == this.id;
        }
        return false;
    }
    public boolean equals(Serializable other){
        return other.id == this.id;
    }

    @Override
    public int compareTo(Serializable other) {
        return 0;
    }
//    @Override
//    public int compareTo(Recognizable other) {
//        if(this.id == other.id) {
//            return 1;
//        }
//        return 0;
//    }
}