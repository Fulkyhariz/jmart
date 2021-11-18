package com.fulkyJmartRK;

import java.util.Vector;
import java.util.function.Function;

public class ObjectPoolThread <T> extends Thread {
    private boolean exitSignal;
    private Vector<T> objectPool = new Vector<T>();
    private Function<T, Boolean> routine;

    public ObjectPoolThread(String name, Function<T, Boolean> routine){
        super(name);
        this.routine = routine;
    }
    public ObjectPoolThread(Function<T, Boolean> routine){
        this.routine = routine;
    }
    public synchronized void add(T object){
        this.objectPool.add(object);
    }
    public synchronized void exit(){
        this.exitSignal = true;
    }
    @Override
    public void run(){
        while(!exitSignal){
            for(T t : objectPool) {
                routine.apply(t);
                if(exitSignal) {
                    this.exit();
                }
            }
        }
    }
    public int size(){
        return this.objectPool.size();
    }
}
