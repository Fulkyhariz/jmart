package com.fulkyJmartRK;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

public class Payment extends Invoice
{
    public ArrayList<Record> history = new ArrayList<>();
    public Shipment shipment;
    public int productCount;

    public static class Record{
        public final Date date;
        public String message;
        public Status status;
        public Record(Status status, String message){
            this.message = message;
            this.status = status;
            this.date  = new Date();
        }
    }

    public Payment(int buyerId, int productId, int productCount,
    Shipment shipment){
        super(buyerId, productId);
        this.productCount = productCount;
        this.shipment = shipment;
    }
    @Override
    public double getTotalPay(Product product){
        return 0.0;
    }


}
