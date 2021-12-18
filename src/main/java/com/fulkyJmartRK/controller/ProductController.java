package com.fulkyJmartRK.controller;

import com.fulkyJmartRK.*;
import com.fulkyJmartRK.dbjson.JsonAutowired;
import com.fulkyJmartRK.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.fulkyJmartRK.Algorithm.min;
import static com.fulkyJmartRK.Algorithm.paginate;

/**
 * class yang mengatur get dan post terhadap produk
 * @author fulky hariz
 */
@RestController
@RequestMapping("/product")
public class ProductController implements BasicGetController<Product> {
    @JsonAutowired(value = Product.class, filepath = "C:/Users/fulky/Documents/Akademik/5th Term/OOP/Prak/randomProductList.json")
    public static JsonTable<Product> productTable;

    /**
     * method yang digunakann untuk membuat suatu produk baru, user hanya dapat
     * membuat produk baru jika telah memiliki store
     * @param accountId id dari akun pemilik store
     * @param name nama produk
     * @param weight berat produk
     * @param conditionUsed apakah produk baru/bekas
     * @param price harga produk
     * @param discount diskon produk
     * @param category kategori produk
     * @param shipmentPlans jenis pengiriman yang tersedia untuk produk
     * @return object produk yang berhasil ditambahkan
     */
    @PostMapping("/create")
    Product create (@RequestParam int accountId,
                    @RequestParam String name,
                    @RequestParam int weight,
                    @RequestParam boolean conditionUsed,
                    @RequestParam double price,
                    @RequestParam double discount,
                    @RequestParam ProductCategory category,
                    @RequestParam byte shipmentPlans){
        Account temp = new AccountController().getById(accountId);
        if(temp!= null && temp.store != null){
            Product temporary = new Product(accountId, name,
                    weight, conditionUsed, price, discount, category, shipmentPlans);
            productTable.add(temporary);
            return temporary;
        }else return null;
    }


    @Override
    public JsonTable<Product> getJsonTable() {
        return productTable;
    }

    /**
     * method yang digunakan untuk mendapatkan produk berdasarkan toko tertentu
     * @param accountId id dari user pemilik toko tertentu
     * @param id id dari produk
     * @param page halaman paginasi
     * @param pageSize jumlah data per halaman
     * @return list berisi produk dari toko tertentu yang telah dipaginasi
     */
    @GetMapping("/{id}/getStore")
    List<Product> getProductByStore (@RequestParam int accountId,
                                     @RequestParam int id,
                                     @RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "10") int pageSize){
        Predicate<Product> predicateId = productTable -> productTable.accountId == accountId && productTable.id == id;
        return paginate(productTable, page, pageSize, predicateId);
    }

    /**
     * method yang digunakan untuk melakukan filtering terhadap data produk yang ditampilkan
     * @param page halaman produk
     * @param pageSize jumlah list produk per halaman
     * @param id id dari pemilik akun
     * @param search nama produk yang ingin ditampilkan
     * @param minPrice harga minimal produk
     * @param maxPrice harga maksimal produk
     * @param isUsed menandakan produk bekas atau tidak
     * @param isNew menandakan produk baru atau tidak
     * @param category kateogri dari produk
     * @return list produk yang sesuai kriteria filter dan telah dipaginasi
     */
    @GetMapping("/getFiltered")
    List<Product> getProductFiltered (@RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "10") int pageSize,
                                      @RequestParam(defaultValue = "1")  int id,
                                      @RequestParam(defaultValue = "") String search,
                                      @RequestParam double minPrice,
                                      @RequestParam double maxPrice,
                                      @RequestParam Boolean isUsed,
                                      @RequestParam Boolean isNew,
                                      @RequestParam ProductCategory category){
        List<Product> temp;
        String searchLowercase = search.toLowerCase();
        Predicate<Product> predicateSearch = product -> (
                (product.name.toLowerCase().contains(searchLowercase) || product.name.equalsIgnoreCase("")) &&
                ((product.conditionUsed == isUsed) || (isUsed == isNew))
            && (product.price >= minPrice) && (product.price <= maxPrice) && /*(product.accountId == id) &&*/
                ((product.category.equals(category) || (category == ProductCategory.NOT_CATEGORY))));
        temp = Algorithm.<Product>paginate(productTable, page, pageSize, predicateSearch);
        return temp;
    }
}
