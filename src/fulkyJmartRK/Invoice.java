package fulkyJmartRK;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public abstract class Invoice extends Recognizable
{
    public enum Rating{
        NONE, BAD, NEUTRAL, GOOD
    }
    public enum Status{
        WAITING_CONFIRMATION, CANCELLED, ON_PROGRESS, ON_DELIVERY, COMPLAINT,
        FINISHED, FAILED
    }
    public class Record{
        public Status status;
        public Date date;
        public String message;
    }
    public Date date;
    public int buyerId;
    public int productId;
    public int complaintId;
    public Rating rating;
    public Status status;
    public ArrayList<Record> history = new ArrayList<Record>();
    
    protected Invoice(int buyerId,int productId){
        this.buyerId = buyerId;
        this.productId = productId;
        this.date = new Date();
        this.rating = Rating.NONE;
        this.status = Status.WAITING_CONFIRMATION;
    }
    /*
    @Override
    public boolean read(String content){
        return false;
    }*/
    public abstract double getTotalPay();
}
