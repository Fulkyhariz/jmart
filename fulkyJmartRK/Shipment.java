package fulkyJmartRK;

public class Shipment implements FileParser
{
    public static class Duration{
        public static Duration INSTANT = new Duration((byte)(1<<0));
        public static Duration SAME_DAY = new Duration((byte)(1<<1));
        public static Duration NEXT_DAY = new Duration((byte)(1<<2));
        public static Duration REGULER = new Duration((byte)(1<<3));
        public static Duration KARGO = new Duration((byte)(1<<4));

        public final byte bit;

        private Duration(byte bit){
            this.bit = bit;
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
