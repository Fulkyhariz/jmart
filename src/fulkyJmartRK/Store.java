package fulkyJmartRK;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Store extends Recognizable
{
    public static final String REGEX_PHONE = "^\\d{9,12}$";
    public static final String REGEX_NAME = "^[A-Z](?!.*(\\s)\1).{4,20}$";
    public String name;
    public String address;
    public String phoneNumber;
    public double balance;
//    @Override
//    public boolean read(String content){
//        return false;
//    }
    public Store(String name, String address, String phoneNumber, double balance){
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.balance = balance;
    }
    public String toString(){
        return "name: "+this.name +" \n"+
        "address: "+this.address+" \n"+
        "phoneNumber: "+this.phoneNumber+" \n";
    }
    public boolean validate(){
        Pattern nama = Pattern.compile(REGEX_NAME);
        Matcher match1 = nama.matcher(this.name);
        Pattern phone = Pattern.compile(REGEX_PHONE);
        Matcher match2 = phone.matcher(this.phoneNumber);
        return match1.find() && match2.find();
    }
}
