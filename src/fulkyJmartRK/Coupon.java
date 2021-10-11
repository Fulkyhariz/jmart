package fulkyJmartRK;

public class Coupon extends Recognizable
{
    public enum Type {
        DISCOUNT, REBATE
    }
    public final String name;
    public final int code;
    public final double cut;
    public final Type type;
    public final double minimum;
    private boolean used;

    /**
     * Constructor for objects of class Coupon
     */
    public Coupon(int id, String name, int code, Type type, 
    double cut, double minimum)
    {
        this.name = name;
        this.code = code;
        this.cut = cut;
        this.type = type;
        this.minimum = minimum;
        this.used = false;
    }
    public boolean isUsed(){
        return used;
    }
    public boolean canApply(PriceTag priceTag){
        if (priceTag.getAdjustedPrice() >= this.minimum && this.used == false){
            return true;
        }
        return false;
    }
    public double apply (PriceTag priceTag){
        this.used = true;
        if (type == Type.DISCOUNT){
            return (priceTag.getAdjustedPrice() * (100 - this.cut)/100);
        }
        return (priceTag.getAdjustedPrice() - this.cut);
    }
//    @Override
//    public boolean read(String content){
//        return false;
//    }
}
