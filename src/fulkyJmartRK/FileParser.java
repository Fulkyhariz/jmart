package fulkyJmartRK;

public abstract interface FileParser
{
    public boolean read(String content);
    default public Object write(){
        return null;
    }
    public static Object newInstance(String content){
        return null;
    }
}