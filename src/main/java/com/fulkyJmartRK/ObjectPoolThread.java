package com.fulkyJmartRK;

import java.util.Vector;
import java.util.function.Function;

public class ObjectPoolThread <T> extends Thread {
    private boolean exitSignal = false;
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
        super.notify();
    }
    @Override
    public void run() {
        while(true){
            synchronized(this) {
                while(objectPool.isEmpty() && !exitSignal) {
                    try {
                        super.wait();
                    }
                    catch(InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if(exitSignal) {
                    break;
                }
            }

            for(int i=0; i<objectPool.size();++i) {
                if(routine.apply(objectPool.get(i))) {
                    objectPool.set(i, null);
                }
            }

            objectPool.removeIf(obj -> obj==null);
        }
    }
    public int size(){
        return this.objectPool.size();
    }
}
