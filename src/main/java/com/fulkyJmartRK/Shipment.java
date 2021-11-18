package com.fulkyJmartRK;
import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;

public class Shipment{
    public static class Plan{
        byte bit;
        public Plan(byte bit){
            this.bit = bit;
        }
    }
    public static final SimpleDateFormat ESTIMATION_FORMAT =
        new SimpleDateFormat("E MMMM dd yyyy");
    public static final Plan INSTANT = new Plan((byte) (1<<0));
    public static final Plan KARGO = new Plan((byte)(1<<1));
    public static final Plan NEXT_DAY = new Plan((byte)(1<<2));
    public static final Plan REGULER = new Plan((byte)(1<<3));
    public static final Plan SAME_DAY = new Plan((byte)(1<<4));
    public String address;
    public int Cost;
    public String receipt;
    byte plan;

    public Shipment (String address,int shipmentCost,String receipt){
        this.address = address;
        this.Cost = shipmentCost;
        this.receipt = receipt;
    }
    public String getEstimatedArrival(Date reference){
          Calendar waktu = Calendar.getInstance();
          waktu.setTime(reference);
          if  (this.plan == INSTANT.bit ||
          this.plan == SAME_DAY.bit){
              waktu.setTime(reference);
          }else if (this.plan == NEXT_DAY.bit){
              waktu.add(Calendar.DATE, 1);
          }else if (this.plan == REGULER.bit){
              waktu.add(Calendar.DATE, 2);
          }else if (this.plan == KARGO.bit){
              waktu.add(Calendar.DATE, 5);
          }return ESTIMATION_FORMAT.format(waktu.getTime());
   }
    public boolean isDuration(Plan reference){
        if((this.plan & reference.bit) == 0){
            return false;
        }
        return true;
    }
    public static boolean isDuration(byte object, Plan reference){
        if((object & reference.bit) == 0){
            return false;
        }
        return true;
    }
}
