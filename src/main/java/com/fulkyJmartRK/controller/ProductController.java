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

    @GetMapping("/{id}/getStore")
    List<Product> getProductByStore (@RequestParam int accountId,
                                     @RequestParam int id,
                                     @RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "10") int pageSize){
        Predicate<Product> predicateId = productTable -> productTable.accountId == accountId && productTable.id == id;
        return paginate(productTable, page, pageSize, predicateId);
    }

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
