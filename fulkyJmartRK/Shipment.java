package fulkyJmartRK;
import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;

public class Shipment implements FileParser
{
    public static class Duration{
        public static Duration INSTANT = new Duration((byte)(1<<0));
        public static Duration SAME_DAY = new Duration((byte)(1<<1));
        public static Duration NEXT_DAY = new Duration((byte)(1<<2));
        public static Duration REGULER = new Duration((byte)(1<<3));
        public static Duration KARGO = new Duration((byte)(1<<4));

        public final byte bit;
        
        public static final SimpleDateFormat ESTIMATION_FORMAT = 
        new SimpleDateFormat("E MMMM dd yyyy");

        private Duration(byte bit){
            this.bit = bit;
        }
        public String getEstimatedArrival(Date reference){
            Calendar waktu = Calendar.getInstance();
            waktu.setTime(reference);
            if  (this.bit == Duration.INSTANT.bit || 
            this.bit == Duration.SAME_DAY.bit){
                waktu.setTime(reference);
            }else if (this.bit == Duration.NEXT_DAY.bit){
                waktu.add(Calendar.DATE, 1);
            }else if (this.bit == Duration.REGULER.bit){
                waktu.add(Calendar.DATE, 2);
            }else if (this.bit == Duration.KARGO.bit){
                waktu.add(Calendar.DATE, 5);
            }return ESTIMATION_FORMAT.format(waktu.getTime());
        }
    }
    public static class MultiDuration{
        public final byte bit;
        public MultiDuration(Duration... args){
            byte flags = 0;
            for (Duration arg : args){
                flags |= arg.bit;
            }
            bit = flags;
        }
        public boolean isDuration(Duration reference){
            if((this.bit & reference.bit) == 0){
                return false;
            }
            return true;
        }
    }
    public String address;
    public int shipmentCost;
    public Duration duration;
    public String receipt;
    
    @Override
    public boolean read(String content){
        return false;
    }
    
    public Shipment (String address,int shipmentCost,Duration duration,
    String receipt){
        this.address = address;
        this.shipmentCost = shipmentCost;
        this.duration = duration;
        this.receipt = receipt;
    }
    
}
