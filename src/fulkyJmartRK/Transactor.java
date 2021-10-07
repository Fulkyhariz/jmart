package fulkyJmartRK;

public interface Transactor
{
    public boolean validate();
    public Invoice perform();
    /*public enum Rating{
        NONE, BAD, NEUTRAL, GOOD
    }
    public String time = "Time";
    public int buyerId;
    public int storeId;
    public Rating rating = Rating.NONE;
    protected Transactor(int id, int buyerId, int storeId){
        this.buyerId = buyerId;
        this.storeId = storeId;
    }
    protected Transactor(int id, Account buyer, Store store){
        this.buyerId = buyerId;
        this.storeId = storeId;
    }
    public boolean validate(){
        return false;
    }
    public Transactor perform(){
        return null;
    }*/
}
