package fulkyJmartRK;

import java.util.Vector;
import java.util.function.Function;

public class ObjectPoolThread <T> extends Thread {
    private boolean exitSignal;
    private Vector<T> objectPool = new Vector<T>();
    private Function<T, Boolean> routine = new Function<T, Boolean>() {
        @Override
        public Boolean apply(T t) {
            return false;
        }
    };

    public ObjectPoolThread(String name, Function<T, Boolean> routine){
        super(name);
        //routine.apply();
    }
    public ObjectPoolThread(Function<T, Boolean> routine){

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
                this.add(t);
            }
        }
    }
    public int size(){
        return this.objectPool.size();
    }
}
