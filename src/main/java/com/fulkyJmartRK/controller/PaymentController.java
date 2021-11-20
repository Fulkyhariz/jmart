package com.fulkyJmartRK.controller;

import com.fulkyJmartRK.ObjectPoolThread;
import com.fulkyJmartRK.Payment;
import com.fulkyJmartRK.dbjson.JsonTable;
import org.springframework.web.bind.annotation.RequestParam;

public class PaymentController implements BasicGetController<Payment> {
    public static  final long DELIVERD_LIMIT_MS = 0;
    public static  final long ON_DELIVERY_LIMIT_MS = 0;
    public static  final long ON_PROGRESS_LIMIT_MS = 0;
    public static  final long WAITING_CONF_LIMIT_MS = 0;

    public static JsonTable<Payment> paymentTable;
    public static ObjectPoolThread<Payment> poolThread;

    boolean accept (@RequestParam int id){
        return false;
    }

    boolean cancel (@RequestParam int id){
        return false;
    }

    Payment create (@RequestParam int buyerId, @RequestParam int productId,
                    @RequestParam String shipmentAddress, @RequestParam byte shipmentPlan){
        return null;
    }

    public JsonTable<Payment> getJsonTable(){
        return null;
    }

    boolean submit (@RequestParam int id, @RequestParam String receipt){
        return false;
    }

    private static boolean timekeeper (@RequestParam Payment payment){
        return false;
    }
}
