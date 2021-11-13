package fulkyJmartRK;

import com.google.gson.Gson;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Collection;
import java.util.Vector;
import com.google.gson.*;
import com.google.gson.stream.JsonReader;

public class JsonTable <T> extends Vector<T> {
    public final String filepath;
    private static final Gson gson = new Gson();

    JsonTable(Class<T> clazz, String filepath) throws IOException{
        List<T> obj = new ArrayList<>();
        this.filepath = filepath;
        obj.add(readJson(clazz, filepath));
    }
    public static <T> T readJson (Class<T> clazz, String filepath) throws FileNotFoundException{
        JsonReader reader = new JsonReader(new FileReader(filepath));
        return gson.fromJson(reader, clazz);
    }
    public void writeJson()throws IOException{
        writeJson(gson, filepath);
    }
    public static void writeJson(Object object, String filepath) throws IOException{
        FileWriter file = new FileWriter(filepath);
        file.write(new Gson().toJson(object));
        file.close();
    }
}
