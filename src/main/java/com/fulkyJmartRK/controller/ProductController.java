package com.fulkyJmartRK.controller;

import com.fulkyJmartRK.Account;
import com.fulkyJmartRK.Product;
import com.fulkyJmartRK.ProductCategory;
import com.fulkyJmartRK.dbjson.JsonAutowired;
import com.fulkyJmartRK.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;
import com.fulkyJmartRK.Algorithm;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController implements BasicGetController<Product> {
    @JsonAutowired(value = Product.class, filepath = "C:/Users/fulky/Documents/Akademik/5th Term/OOP/Prak/randomProductList.json")
    public static JsonTable<Product> productTable;

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

    @GetMapping("/{id}/store")
    List<Product> getProductByStore (@RequestParam int id,
                                     @RequestParam int page,
                                     @RequestParam int pageSize){
        return null;
    }

    @GetMapping("/getFiltered")
    List<Product> getProductFiltered (@RequestParam int page,
                                      @RequestParam int pageSize,
                                      @RequestParam int id,
                                      @RequestParam String search,
                                      @RequestParam int minPrice,
                                      @RequestParam int maxPrice,
                                      @RequestParam ProductCategory category){
        return null;
    }
}
