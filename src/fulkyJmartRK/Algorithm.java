package fulkyJmartRK;

import java.util.Iterator;

public class Algorithm {
    private Algorithm(){}
    public static <T> int count(T[]array, T value){
        int counter = 0;
        for(T arrayValue : array){
            if (arrayValue.equals(value)){
                counter++;
            }
        }
        return counter;
    }
    public static <T> int count(Iterable<T> iterable, T value){
        int counter = 0;
        for(T t : iterable){
            if(t.equals(value)){
                counter++;
            }
        }
        return counter;
    }
    public static <T> int count(Iterator<T> iterator, T value){
        int counter = 0;
        while(iterator.hasNext()){
            if(iterator.next().equals(value)){
                counter++;
            }
        }
        return counter;
    }
    public static <T> int count(T[] array, Predicate<T>pred){
        int counter = 0;
        for(T arrayValue : array){
            if (pred.predicate(arrayValue)){
                counter++;
            }
        }
        return counter;
    }
    public static <T> int count(Iterable<T> iterable, Predicate<T>pred){
        int counter = 0;
        for(T t : iterable){
            if(pred.predicate(t)){
                counter++;
            }
        }
        return counter;
    }
    public static <T> int count(Iterator<T> iterator, Predicate<T>pred){
        int counter = 0;
        while(iterator.hasNext()){
            if(pred.predicate(iterator.next())){
                counter++;
            }
        }
        return counter;
    }
    public static <T> boolean exists(T[] array, T value){
        for(T arrayValue : array){
            if (arrayValue.equals(value)){
                return true;
            }
        }
        return false;
    }
    public static <T> boolean exists(Iterable<T> iterable, T value){
        for(T t : iterable){
            if(t.equals(value)){
                return true;
            }
        }
        return false;
    }
    public static <T> boolean exists(Iterator<T> iterator, T value){
        while(iterator.hasNext()){
            if(iterator.next().equals(value)){
                return true;
            }
        }
        return false;
    }
    public static <T> boolean exists(T[] array, Predicate<T>pred){
        for(T arrayValue : array){
            if (pred.predicate(arrayValue)){
                return true;
            }
        }
        return false;
    }
    public static <T> boolean exists(Iterable<T> iterable, Predicate<T>pred){
        int counter = 0;
        for(T t : iterable){
            if(pred.predicate(t)){
                return true;
            }
        }
        return false;
    }
    public static <T> boolean exists(Iterator<T> iterator, Predicate<T>pred){
        int counter = 0;
        while(iterator.hasNext()){
            if(pred.predicate(iterator.next())){
                return true;
            }
        }
        return false;
    }
    public static <T> T find(T[] array, T value){
        for(T arrayValue : array){
            if (arrayValue.equals(value)){
                return arrayValue;
            }
        }
        return null;
    }
    public static <T> T find(Iterable<T> iterable, T value){
        for(T t : iterable){
            if(t.equals(value)){
                return t;
            }
        }
        return null;
    }
    public static <T> T find(Iterator<T> iterator, T value){
        while(iterator.hasNext()){
            if(iterator.next().equals(value)){
                return iterator.next();
            }
        }
        return null;
    }
    public static <T> T find(T[] array, Predicate<T>pred){
        for(T arrayValue : array){
            if (pred.predicate(arrayValue)){
                return arrayValue;
            }
        }
        return null;
    }
    public static <T> T find(Iterable<T> iterable, Predicate<T>pred){
        int counter = 0;
        for(T t : iterable){
            if(pred.predicate(t)){
                return t;
            }
        }
        return null;
    }
    public static <T> T find(Iterator<T> iterator, Predicate<T>pred){
        int counter = 0;
        while(iterator.hasNext()){
            if(pred.predicate(iterator.next())){
                return iterator.next();
            }
        }
        return null;
    }
    public static<T extends Comparable<? super T>> T max(T first, T second) {
        if(first.compareTo(second) > 0) return first;
        return second;
    }

    public static<T extends Comparable<? super T>> T min(T first, T second) {
        if(first.compareTo(second) < 0) return first;
        return second;
    }
}
