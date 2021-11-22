package com.fulkyJmartRK.controller;

import com.fulkyJmartRK.Treasury;
import com.fulkyJmartRK.dbjson.JsonAutowired;
import com.fulkyJmartRK.dbjson.JsonTable;
import com.fulkyJmartRK.Coupon;
import com.fulkyJmartRK.Treasury;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/coupon")
public class CouponController implements BasicGetController<Coupon> {
    @JsonAutowired(value = Coupon.class, filepath = "C:/Users/fulky/Documents/Akademik/5th Term/OOP/Prak/coupon.json")
    public static JsonTable<Coupon> couponTable;

    @Override
    public JsonTable<Coupon> getJsonTable() {
        return couponTable;
    }

    @GetMapping("/{id}/canApply")
    boolean canApply (@RequestParam int id,
                      @RequestParam double price,
                      @RequestParam double discount){
        Coupon coupon = getById(id);
        if(coupon.id == id) {
            return coupon.canApply(new Treasury());
        }
        return false;
    }

    @GetMapping("/getAvailable")
    List<Coupon> getAvailable (@RequestParam int page,
                               @RequestParam int pageSize){
        return null;
    }

    @GetMapping("/{id}/isUsed")
    boolean isUsed(@RequestParam int id) {
        Coupon coupon = getById(id);
        if(coupon.id == id) {
            return coupon.isUsed();
        }
        return false;
    }
}
