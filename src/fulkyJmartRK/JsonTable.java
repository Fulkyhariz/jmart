package fulkyJmartRK;

import com.google.gson.Gson;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Vector;
import com.google.gson.*;
import com.google.gson.stream.JsonReader;

public class JsonTable <T> extends Vector<T> {
    public final String filepath;
    private static final Gson gson = new Gson();

    public JsonTable(Class<T> clazz, String filepath) throws IOException{
        this.filepath = filepath;
        try
        {
            Class<T[]> array = (Class<T[]>) Array.newInstance(clazz, 0).getClass();
            T[] loaded = readJson(array, filepath);
            if(loaded != null)
            {
                Collections.addAll(this, loaded);
            }
        }
        catch (FileNotFoundException e)
        {
            File f = new File(filepath);
            File f1 =  f.getParentFile();
            if(f1 != null)
            {
                f1.mkdirs();
            }
            f.createNewFile();
        }
    }
    public static <T> T readJson (Class<T> clazz, String filepath) throws FileNotFoundException, IOException{
        JsonReader reader = new JsonReader(new FileReader(filepath));
        return gson.fromJson(reader, clazz);
    }
    public void writeJson()throws IOException{
        writeJson(this, filepath);
    }
    public static void writeJson(Object object, String filepath) throws IOException{
        FileWriter file = new FileWriter(filepath);
        file.write(new Gson().toJson(object));
        file.close();
    }
}
