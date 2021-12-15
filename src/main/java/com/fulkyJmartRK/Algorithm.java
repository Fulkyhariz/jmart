package com.fulkyJmartRK;

import java.util.*;
import java.util.stream.Collectors;

public class Algorithm {
    private Algorithm(){}
    public static <T> List<T> collect(T[]array, T value){
        List<T> tempList = new ArrayList<T>();
        for(T arrayValue : array){
            if(arrayValue.equals(value)) tempList.add(arrayValue);
        }
        return tempList;
    }
    public static <T> List<T> collect(Iterable<T> iterable, T value){
        List<T> tempList = new ArrayList<T>();
        for(T t : iterable){
            if(t.equals(value)) tempList.add(t);
        }
        return tempList;
    }
    public static <T> List<T> collect(Iterator<T> iterator, T value){
        List<T> tempList = new ArrayList<T>();
        while(iterator.hasNext()){
            T temp = iterator.next();
            if(temp.equals(value)) tempList.add(temp);
        }
        return tempList;
    }
    public static <T> List<T> collect(T[]array, Predicate<T>pred){
        List<T> tempList = new ArrayList<T>();
        for(T arrayValue : array){
            if(pred.predicate(arrayValue)) tempList.add(arrayValue);
        }
        return tempList;
    }
    public static <T> List<T> collect(Iterable<T> iterable, Predicate<T>pred){
        List<T> tempList = new ArrayList<T>();
        for(T t : iterable){
            if(pred.predicate(t)) tempList.add(t);
        }
        return tempList;
    }
    public static <T> List<T> collect(Iterator<T> iterator, Predicate<T>pred){
        List<T> tempList = new ArrayList<T>();
        while(iterator.hasNext()){
            T temp = iterator.next();
            if(pred.predicate(temp)) tempList.add(temp);
        }
        return tempList;
    }
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
    public static <T extends Comparable<? super T>> T max(T first, T second) {
        if(first.compareTo(second) > 0) return first;
        return second;
    }
    public static <T extends Comparable<? super T>> T max(T[] array) {
        Iterator<T> iterator = Arrays.stream(array).iterator();
        T temp = iterator.next();
        while(iterator.hasNext()){
            T holder = iterator.next();
            temp = max(temp, holder);
        }
        return temp;
    }
    public static <T extends Comparable<? super T>> T max(Iterable<T> iterable) {
        Iterator<T> iterator = iterable.iterator();
        T temp = iterator.next();
        while(iterator.hasNext()){
            T holder = iterator.next();
            temp = max(temp, holder);
        }
        return temp;
    }
    public static <T extends Comparable<? super T>> T max(Iterator<T> iterator) {
        T temp = iterator.next();
        while(iterator.hasNext()){
            T holder = iterator.next();
            temp = max(temp, holder);
        }
        return temp;
    }
    public static <T> T max(T first, T second, Comparator<? super T> comparator) {
        if(comparator.compare(first, second) > 0) return first;
        return second;
    }
    public static <T> T max(T[] array, Comparator<? super T> comparator) {
        Iterator<T> iterator = Arrays.stream(array).iterator();
        T temp = iterator.next();
        while(iterator.hasNext()){
            T holder = iterator.next();
            temp = max(temp, holder, comparator);
        }
        return temp;
    }
    public static <T> T max(Iterable<T> iterable, Comparator<? super T> comparator) {
        Iterator<T> iterator = iterable.iterator();
        T temp = iterator.next();
        while(iterator.hasNext()){
            T holder = iterator.next();
            temp = max(temp, holder, comparator);
        }
        return temp;
    }
    public static <T> T max(Iterator<T> iterator, Comparator<? super T> comparator) {
        T temp = iterator.next();
        while(iterator.hasNext()){
            T holder = iterator.next();
            temp = max(temp, holder, comparator);
        }
        return temp;
    }
    public static <T extends Comparable<? super T>> T min(T first, T second) {
        if(first.compareTo(second) > 0) return first;
        return second;
    }
    public static <T extends Comparable<? super T>> T min(T[] array) {
        Iterator<T> iterator = Arrays.stream(array).iterator();
        T temp = iterator.next();
        while(iterator.hasNext()){
            T holder = iterator.next();
            temp = min(temp, holder);
        }
        return temp;
    }
    public static <T extends Comparable<? super T>> T min(Iterable<T> iterable) {
        Iterator<T> iterator = iterable.iterator();
        T temp = iterator.next();
        while(iterator.hasNext()){
            T holder = iterator.next();
            temp = min(temp, holder);
        }
        return temp;
    }
    public static <T extends Comparable<? super T>> T min(Iterator<T> iterator) {
        T temp = iterator.next();
        while(iterator.hasNext()){
            T holder = iterator.next();
            temp = min(temp, holder);
        }
        return temp;
    }
    public static <T> T min(T first, T second, Comparator<? super T> comparator) {
        if(comparator.compare(first, second) > 0) return first;
        return second;
    }
    public static <T> T min(T[] array, Comparator<? super T> comparator) {
        Iterator<T> iterator = Arrays.stream(array).iterator();
        T temp = iterator.next();
        while(iterator.hasNext()){
            T holder = iterator.next();
            temp = min(temp, holder, comparator);
        }
        return temp;
    }
    public static <T> T min(Iterable<T> iterable, Comparator<? super T> comparator) {
        Iterator<T> iterator = iterable.iterator();
        T temp = iterator.next();
        while(iterator.hasNext()){
            T holder = iterator.next();
            temp = min(temp, holder, comparator);
        }
        return temp;
    }
    public static <T> T min(Iterator<T> iterator, Comparator<? super T> comparator) {
        T temp = iterator.next();
        while(iterator.hasNext()){
            T holder = iterator.next();
            temp = min(temp, holder, comparator);
        }
        return temp;
    }
    public static <T> List<T> paginate(T[] array, int page, int pageSize, Predicate<T> pred){
        List<T> list = Arrays.asList(array);
        //System.out.println(page);
        if(pageSize < 0 || page < 0) {
            throw new IllegalArgumentException("Invalid page and/or page size is entered. Page = " + page + " and Page size = " + pageSize + ".");
        }else {
            List<T> paginated = (list.stream().filter(tmp -> pred.predicate(tmp)).skip(page * pageSize).limit(pageSize).collect(Collectors.toList()));
            return(paginated);
        }
    }
    public static <T> List<T> paginate(Iterable<T> iterable, int page, int pageSize, Predicate<T> pred){
        List<T> list = new ArrayList<>();
        iterable.forEach(list::add);
        if(pageSize < 0 || page < 0) page = 0;
        System.out.println(page);
        return list.stream().filter(pred::predicate).skip(page * pageSize).limit(pageSize).collect(Collectors.toList());
    }
    public static <T> List<T> paginate(Iterator<T> iterator, int page, int pageSize, Predicate<T> pred){
        List<T> list = new ArrayList<>();
        iterator.forEachRemaining(list::add);
        if(pageSize < 0 || page < 0) page = 0;
        //System.out.println(page);
        return list.stream().filter(pred::predicate).skip(page * pageSize).limit(pageSize).collect(Collectors.toList());
    }
}
