package fulkyJmartRK;
import java.util.ArrayList;

public class Filter
{
    public static ArrayList<PriceTag> fiterPriceTag (PriceTag[] list,
    double value, boolean less){
        ArrayList<PriceTag> priceTags = new ArrayList<PriceTag>();
        for (PriceTag filter : list){
            if (less){
                if(filter.getAdjustedPrice() < value){
                    priceTags.add(filter);
                }
            }else{
                if(filter.getAdjustedPrice() >= value){
                    priceTags.add(filter);
                }
            }
        }
        return priceTags;
    }
    public static void filterProductRating(ArrayList<ProductRating> list,
    double value, boolean less){
        for (int i = 0; i < list.size(); ++i){
            ProductRating filter = list.get(i);
            if (less){
                if(filter.getAverage() < value){
                    list.remove(filter);
                }
            }else{
                if(filter.getAverage() >= value){
                    list.remove(filter);
                }
            }
        }
    }
}
