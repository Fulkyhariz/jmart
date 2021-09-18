package fulkyJmartRK;

/**
 * Write a description of class ProductRating here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ProductRating
{
    private long total;
    private long count;
    public ProductRating(){
        this.total = 0;
        this.count = 0;
    }
    public void insert(int rating){
        this.total += rating;
        this.count += 1;
    }
    public double getAverage(){
        if(count != 0){
            return total/count;
        }
        return 0.0;
    }
    public long getCount(){
        return count;
    }
    public long getTotal(){
        return total;
    }
}
