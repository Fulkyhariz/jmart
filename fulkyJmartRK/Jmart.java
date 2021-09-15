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
        System.out.println("");
        System.out.println(getCustomer());
        System.out.println("");
        System.out.println(getDiscountPercentage(1000,900));
        System.out.println(getDiscountPercentage(1000,0));
        System.out.println(getDiscountPercentage(0,0));
        System.out.println(getDiscountPercentage(0,1));
        System.out.println("");
        System.out.println(getDiscountedPrice(1000, 10.0f));
        System.out.println(getDiscountedPrice(1000, 100.0f));
        System.out.println(getDiscountedPrice(1000, 120.0f));
        System.out.println(getDiscountedPrice(0, 0.0f));
        System.out.println("");
        System.out.println(getOriginalPrice(900,10.0f));
        System.out.println(getOriginalPrice(1000,0.0f));
        System.out.println(getOriginalPrice(0,100.0f));
        System.out.println(getOriginalPrice(0,120.0f));
        System.out.println("");
        System.out.println(getAdjustedPrice(1000));
        System.out.println(getAdjustedPrice(500));
        System.out.println(getAdjustedPrice(0));
        System.out.println("");
        System.out.println(getAdminFee(1000));
        System.out.println(getAdminFee(500));
        System.out.println(getAdminFee(0));
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
        float temp = 0.0f;
        if (((float) before) < ((float) after)){
            return 0.0f;
        }
        if (((float) before) != 0.0f){
            temp = (((float)(before - after)/before)*100);
        }
        return temp;
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
