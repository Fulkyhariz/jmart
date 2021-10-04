package fulkyJmartRK;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Account extends Recognizable implements FileParser
{
    public static final String REGEX_EMAIL = "^[a-zA-Z0-9&*_~]+([\\.]?[a-zA-Z0-9&*_~]?)*@[A-Za-z0-9]+([-]?[\\.]?[A-Za-z0-9]+)+(^\\.)$";
    public static final String REGEX_PASSWORD = "^([A-Z]+[a-z]+[0-9]+){8,}$";
    public String name;
    public String email;
    public String password;
    
    @Override
    public boolean read(String content){
        return false;
    }
    Account(int id, String name, String email, String password){
        super(id);
        this.name = name;
        this.email = email;
        this.password = password;
    }
    public String toString(){
        return "name: "+this.name +" \n"+
        "email: "+this.email +" \n"+
        "password: "+this.password +" \n";
    }
    public boolean validate(){
        Pattern email = Pattern.compile(REGEX_EMAIL);
        Matcher match1 = email.matcher(this.email);
        Pattern password = Pattern.compile(REGEX_PASSWORD);
        Matcher match2 = password.matcher(this.password);
        return match1.find() && match2.find();
    }
}
