package com.fulkyJmartRK.controller;

import com.fulkyJmartRK.*;
import com.fulkyJmartRK.dbjson.JsonAutowired;
import com.fulkyJmartRK.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.fulkyJmartRK.Algorithm.min;
import static com.fulkyJmartRK.Algorithm.paginate;

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
                                     @RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "10") int pageSize){
        Predicate<Product> predicateId = productTable -> productTable.accountId == id;
        System.out.println("this line works");

        System.out.println(predicateId);
        return paginate(productTable, page, pageSize, predicateId);
    }

    @GetMapping("/getFiltered")
    List<Product> getProductFiltered (@RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "10") int pageSize,
                                      @RequestParam int id,
                                      @RequestParam String search,
                                      @RequestParam double minPrice,
                                      @RequestParam double maxPrice,
                                      @RequestParam Boolean isUsed,
                                      @RequestParam ProductCategory category){
        List<Product> temp;
        String searchLowercase = search.toLowerCase();
        Predicate<Product> predicateSearch = product -> (product.name.toLowerCase().contains(searchLowercase) && (product.conditionUsed == isUsed)
            && (product.price >= minPrice) && (product.price <= maxPrice) /*&& (product.accountId == id)*/ && (product.category.equals(category)));
        System.out.println(search);
        temp = Algorithm.<Product>paginate(productTable, page, pageSize, predicateSearch);
        for (Product temp2 : temp){
            System.out.println(temp2.name);
        }
        return temp;
    }
}
