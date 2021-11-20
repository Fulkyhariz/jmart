package com.fulkyJmartRK.controller;

import com.fulkyJmartRK.dbjson.JsonTable;
import com.fulkyJmartRK.dbjson.Serializable;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public interface BasicGetController <T extends Serializable> {
    @GetMapping("/{id}")
    public default T getById(int id) {
        for(T t : getJsonTable()){
            if (t.id == id){
                return t;
            }
        }
        return null;
    }
    public abstract JsonTable<T> getJsonTable();

    @GetMapping("/page")
    public default List<T> getPage(int page, int pageSize){
        for(T t : getJsonTable()){
            return null;
        }
        return null;
    }
}
