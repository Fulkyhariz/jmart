package fulkyJmartRK;
import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;

public class Complaint extends Recognizable implements FileParser
{
    public Date date;
    public String desc;
    
    public Complaint(int id, String desc){
        super(id);
        this.desc = desc;
        this.date = new Date();
    }
    public String toString(){
        Calendar waktu = Calendar.getInstance();
        waktu.setTime(this.date);
        SimpleDateFormat formatWaktu = new SimpleDateFormat("dd/MM/yyyy");
        return "date="+ formatWaktu.format(waktu.getTime()) +", desc="+ this.desc;
    }
    @Override
    public boolean read(String content){
        return false;
    }
}
