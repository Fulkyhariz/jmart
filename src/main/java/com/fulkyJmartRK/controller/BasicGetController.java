package com.fulkyJmartRK.controller;

import com.fulkyJmartRK.Algorithm;
import com.fulkyJmartRK.dbjson.JsonTable;
import com.fulkyJmartRK.dbjson.Serializable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * interface yang digunakan untuk melakukan beberapa basic get (by id dan juga page) terhadap controller yang ada
 * @author fulky hariz
 * @param <T> generic yang menyesuaikan class yang mengimplementasi interface ini
 */
public interface BasicGetController <T extends Serializable> {
    /**
     * method untuk mendapatkan suatu objek berdasarkan id nya
     * @param id id yang dibutuhkan menyesuaikan dengan class yang mengimplementasikan
     * @return sebuah objek dengan id tertentu
     */
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

    /**
     * method yang digunakan untuk mendapatkan list dari data yang telah dilakukan paginasi
     * @param page halaman yang ingin ditampilkan, dimulai dari 0
     * @param pageSize ukuran setiap halaman
     * @return list objek tertentu
     */
    @GetMapping("/page")
    default List<T> getPage(int page, int pageSize) {
        return Algorithm.<T>paginate(getJsonTable(), page, pageSize, e -> true);
    }
}
