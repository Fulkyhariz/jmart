package fulkyJmartRK;

public class Recognizable implements Comparable <Recognizable>
{
    public final int id;

    protected Recognizable(){
        this.id = 4;
    }
    public static <T extends Recognizable> int setClosingId(Class<T> clazz, int id){
        return 0;
    }
    public static <T extends Recognizable> int getClosingId(Class<T> clazz){
        return 0;
    }
    public boolean equals(Object object){
        if (object instanceof Recognizable){
            Recognizable recognizable = (Recognizable) object;
            if(recognizable.id == this.id){
                return true;
            }else{
                return false;
            }
        }
        return false;
    }
    public boolean equals(Recognizable recognizable){
        if(recognizable.id == this.id){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public int compareTo(Recognizable o) {
        return 0;
    }
//    @Override
//    public int compareTo(Recognizable other) {
//        if(this.id == other.id) {
//            return 1;
//        }
//        return 0;
//    }
}