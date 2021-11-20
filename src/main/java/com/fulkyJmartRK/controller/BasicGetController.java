package com.fulkyJmartRK.controller;

import com.fulkyJmartRK.Algorithm;
import com.fulkyJmartRK.dbjson.JsonTable;
import com.fulkyJmartRK.dbjson.Serializable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    default List<T> getPage(int page, int pageSize) {
        return Algorithm.<T>paginate(getJsonTable(), page, pageSize, e -> true);
    }
}
