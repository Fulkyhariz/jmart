package fulkyJmartRK;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public abstract class Invoice extends Serializable
{
    public enum Rating{
        NONE, BAD, NEUTRAL, GOOD
    }
    public enum Status{
        WAITING_CONFIRMATION, CANCELLED, ON_PROGRESS, ON_DELIVERY, COMPLAINT,
        FINISHED, FAILED, DELIVERED
    }
    public class Record{
        public Status status;
        public Date date;
        public String message;
    }
    public final Date date = new Date();
    public int buyerId;
    public int productId;
    public int complaintId = -1;
    public Rating rating = Rating.NONE;
/*  public Status status;
    public ArrayList<Record> history = new ArrayList<Record>();*/
    
    protected Invoice(int buyerId,int productId){
        this.buyerId = buyerId;
        this.productId = productId;
        this.rating = Rating.NONE;
        /*this.status = Status.WAITING_CONFIRMATION;*/
    }
    public abstract double getTotalPay(Product product);
}
