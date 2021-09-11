package fulkyJmartRK;


/**
 * Write a description of class Jmart here.
 *
 * @author (Fulky Hariz Z)
 * @version (11/9/21)
 */
public class Jmart
{
    public static void main(String[] args)
    {
        System.out.println(getPromo());
        System.out.println(getCustomer());
        System.out.println(getDiscountPercentage(1000,900));
        System.out.println(getOriginalPrice(900,10.0f));
        System.out.println(getDiscountedPrice(1000, 10.0f));
        System.out.println(getAdjustedPrice(1000));
        System.out.println(getAdminFee(1000));
    }
    public static int getPromo()
    {
        // put your code here
        return 0;
    }
    public static String getCustomer()
    {
        // put your code here
        return "oop";
    }
    public static float getDiscountPercentage(int before, int after)
    {
        if (before < after){
            return 0.0f;
        }
        return (int)((before - after)/before);
    }
    public static int getDiscountedPrice(int price, float discountPercentage)
    {
        if (discountPercentage > 100){
            return 0;
        }
        return (int)(price - ((discountPercentage/100)*price));
    }
    public static int getOriginalPrice(int discountedPrice, float discountPercentage)
    {
        
        return (int)(discountedPrice/(1-(discountPercentage/100)));
    }
    public static float getCommissionMultiplier()
    {
        // put your code here
        return 0.05f;
    }
    public static int getAdjustedPrice(int price)
    {
        // put your code here
        return (int)(price + (getCommissionMultiplier() * price));
    }
    public static int getAdminFee(int price)
    {
        // put your code here
        return (int)(getCommissionMultiplier()*price);
    }
}
