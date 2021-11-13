package fulkyJmartRK;
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

/**
 * Write a description of class Jmart here.
 *
 * @author (Fulky Hariz Z)
 * @version (11/9/21)
 */
public class Jmart
{
/*    class Country{
        public String name;
        public int population;
        public List<String> listOfStates;
    }*/
    public static void main(String[] args)
    {
        /*System.out.println("account id:" + new Account(null, null, null, -1).id);
        System.out.println("account id:" + new Account(null, null, null, -1).id);
        System.out.println("account id:" + new Account(null, null, null, -1).id);

        System.out.println("payment id:" + new Payment(-1, -1, -1, null).id);
        System.out.println("payment id:" + new Payment(-1, -1, -1, null).id);
        System.out.println("payment id:" + new Payment(-1, -1, -1, null).id);*/

/*        try{
            List<Product> list = read("C:/Users/fulky/Documents/Akademik/5th Term/OOP/Prak/[Modul 6] Case Study - Resource-20211106/randomProductList.json");
            List<Product> filtered = filterByName(list, "gtx", 1, 4);
            filtered.forEach(product -> System.out.println(product.name));
        }catch (Throwable t){
            t.printStackTrace();
        }*/
        try{
            String filepath = "C:/Users/fulky/Documents/Akademik/5th Term/OOP/Prak/Jmart/jmart.account.json";
            JsonTable<Account> tableAccount = new JsonTable<>(Account.class, filepath);
            tableAccount.add(new Account("name", "email", "password", 200));
            tableAccount.writeJson();

            tableAccount = new JsonTable<>(Account.class, filepath);
            tableAccount.forEach(account -> System.out.println(account.toString()));
        }catch(Throwable t){
            t.printStackTrace();
        }
    }
    public static List<Product> read (String filepath) throws FileNotFoundException, IOException{
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
    }
}
