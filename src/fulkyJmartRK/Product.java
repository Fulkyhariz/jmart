package fulkyJmartRK;

/**
 * Write a description of class Product here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Product extends Recognizable
{
    public static int idCounter = 0;
    public int id;
    public String name;
    public int weight;
    public boolean conditionUsed;
    public PriceTag priceTag;
    public ProductCategory category;
    public ProductRating rating;
    public int storeId;
    //public Shipment.MultiDuration multiDuration;
    public Product(int id, int storeId, String name, int weight,
    boolean conditionUsed, PriceTag priceTag, ProductCategory category)
    {
        this.id = idCounter;
        this.idCounter++;
        this.name = name;
        this.weight = weight;
        this.conditionUsed = conditionUsed;
        this.priceTag = priceTag;
        this.category = category;
        this.rating = rating;
        this.storeId = storeId;
        //this.multiDuration = multiDuration;
    }
//    @Override
//    public boolean read(String content){
//        return false;
//    }
    public String toString(){
        return "Name: "+this.name +" \n"+
        "Weight: "+this.weight+" \n"+
        "conditionUsed: "+this.conditionUsed+" \n"+
        "priceTag: "+this.priceTag+" \n"+
        "category: "+this.category+" \n"+
        "rating: "+this.rating+" \n"+
        "storeId: "+this.storeId+" \n";
    }
}
