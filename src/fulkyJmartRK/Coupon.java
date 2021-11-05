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

    public Coupon(String name, int code, Type type,
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
    public boolean canApply(Treasury priceTag){
        if (priceTag.getAdjustedPrice(0,0) >= this.minimum && !this.used){
            return true;
        }
        return false;
    }
    public double apply (Treasury priceTag){
        this.used = true;
        if (type == Type.DISCOUNT){
            return (priceTag.getAdjustedPrice(0,0) * (100 - this.cut)/100);
        }
        return (priceTag.getAdjustedPrice(0,0) - this.cut);
    }
}
