package com.fulkyJmartRK;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import java.util.stream.Collectors;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Write a description of class Jmart here.
 *
 * @author (Fulky Hariz Z)
 * @version (11/9/21)
 */
@SpringBootApplication
public class Jmart
{
/*    class Country{
        public String name;
        public int population;
        public List<String> listOfStates;
    }*//*
    public static long DELIVERED_LIMIT_MS = 0;
    public static long ON_DELIVERY_LIMIT_MS = 1;
    public static long ON_PROGRESS_LIMIT_MS = 2;
    public static long WAITING_CONF_LIMIT_MS = 3;*/
    public static void main(String[] args)
    {
        SpringApplication.run(Jmart.class, args);
/*        try {
            JsonTable<Payment> table = new JsonTable<>(Payment.class, "C:/Users/fulky/Documents/Akademik/5th Term/OOP/Prak/Jmart/jmart/randomPaymentList.json");
            ObjectPoolThread<Payment> paymentPool = new ObjectPoolThread<Payment>("Thread-PP", Jmart::paymentTimekeeper);
            paymentPool.start();
            table.forEach(payment -> paymentPool.add(payment));
            while (paymentPool.size() != 0);
            paymentPool.exit();
            while (paymentPool.isAlive());
            System.out.println("Thread exited succesfully");
            Gson gson = new Gson();
            table.forEach(payment -> {
                String history = gson.toJson(payment.history);
                System.out.println(history);
            });
        }catch (Throwable t){
            t.printStackTrace();
        }*/
    }
    public static boolean paymentTimekeeper(Payment payment){

        long start = new Date().getTime();

        long end = new Date().getTime();
        long elapsed = end - start;
        return false;
    }

/*    public static List<Product> read (String filepath) throws FileNotFoundException, IOException{
        List<Product> temp = new ArrayList<>();
        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new FileReader(filepath));
        reader.beginArray();
        while(reader.hasNext()){
            temp.add(gson.fromJson(reader, Product.class));
        }
        return temp;
    }
    public static List<Product> filterByCategory (List<Product> list, ProductCategory category){
        List<Product> filtered = new ArrayList<>();
        for(Product temp : list){
            if(temp.category == category){
                filtered.add(temp);
            }
        }
        return filtered;
    }
    public static List<Product> filterByPrice (List<Product> list, double minPrice, double maxPrice){
        List<Product> filtered = new ArrayList<>();
        for(Product temp : list){
            if(minPrice == 0.0 && maxPrice >= temp.price){
                filtered.add(temp);
            }else if(maxPrice == 0.0 && minPrice <= temp.price){
                filtered.add(temp);
            }else if(maxPrice >= temp.price && minPrice <= temp.price){
                filtered.add(temp);
            }
        }
        return filtered;
    }
    public static List<Product> filterByAccountId(List<Product> list, int accountId, int page, int pageSize){
        Predicate<Product> pred= (tmp -> (tmp.accountId == accountId));
        return paginate(list, page, pageSize, pred);
    }
    public static List<Product> filterByName(List<Product> list, String search, int page, int pageSize){
        Predicate<Product> pred = (tmp -> (tmp.name.toLowerCase().contains(search.toLowerCase())));
        return paginate(list, page, pageSize, pred);
    }
    private static List<Product> paginate(List<Product> list, int page, int pageSize, Predicate<Product> pred){
        if(pageSize < 0 || page < 0) {
            throw new IllegalArgumentException("Invalid page and/or page size is entered. Page = " + page + " and Page size = " + pageSize + ".");
        }else {
            List<Product> paginated = (list.stream().filter(tmp -> pred.predicate(tmp)).skip(page * pageSize).limit(pageSize).collect(Collectors.toList()));
            return(paginated);
        }
    }*/
}
