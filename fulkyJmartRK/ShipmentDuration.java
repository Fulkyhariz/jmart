package fulkyJmartRK;

public class ShipmentDuration
{
    public static ShipmentDuration INSTANT = new ShipmentDuration(1<<0);
    public static ShipmentDuration SAME_DAY = new ShipmentDuration(1<<1);
    public static ShipmentDuration NEXT_DAY = new ShipmentDuration(1<<2);
    public static ShipmentDuration REGULER = new ShipmentDuration(1<<3);
    public static ShipmentDuration KARGO = new ShipmentDuration(1<<4);
    
    private final int bit;
    
    private ShipmentDuration(int bit){
        this.bit = bit;
    }
    public ShipmentDuration(ShipmentDuration... args){
        int flags = 0;
        for (ShipmentDuration arg : args){
            flags |= arg.bit;
        }
        bit = flags;
    }
    public boolean isDuration(ShipmentDuration reference){
        if((this.bit & reference.bit) == 0){
            return false;
        }
        return true;
    }
}
