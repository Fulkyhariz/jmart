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
        /*this.idCounter += 1;
        this.id = idCounter;
        this.name = name;
        this.weight = weight;
        this.conditionUsed = conditionUsed;
        this.priceTag = priceTag;
        this.category = category;
        this.rating = rating;*/
        System.out.println(create().id);
        System.out.println(create().name);
        System.out.println(create().weight);
        System.out.println(create().conditionUsed);
        System.out.println(create().priceTag.price);
        System.out.println(create().category);
        System.out.println(create().rating);
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
    public static Product create(){
        Product produk = new Product("lock&lock", 50, false, new PriceTag(100000), ProductCategory.SPORTS);
        return produk;
    }
}
