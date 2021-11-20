package com.fulkyJmartRK.controller;

import com.fulkyJmartRK.Account;
import com.fulkyJmartRK.Store;
import com.fulkyJmartRK.dbjson.JsonAutowired;
import com.fulkyJmartRK.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/account")
public class AccountController implements BasicGetController<Account> {
    public static final String REGEX_EMAIL = "^[a-zA-Z0-9&*_~]+([\\.]?[a-zA-Z0-9&*_~]?)*@[A-Za-z0-9]+([-]?[\\.]?[A-Za-z0-9]+)+(^\\.)$";
    public static final String REGEX_PASSWORD = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";
    public static final Pattern REGEX_PATTERN_EMAIL = Pattern.compile(REGEX_EMAIL);
    public static final Pattern REGEX_PATTERN_PASSWORD = Pattern.compile(REGEX_PASSWORD);

    @JsonAutowired(value = Account.class, filepath = "C:/Users/fulky/Documents/Akademik/5th Term/OOP/Prak/jmart backup/account.json")
    public static JsonTable<Account> accountTable;

    @Override
    public JsonTable<Account> getJsonTable() {
        return accountTable;
    }

    @PostMapping("/login")
    Account login( @RequestParam String email, @RequestParam String password){
        Matcher matchEmail = REGEX_PATTERN_EMAIL.matcher(email);
        Matcher matchPassword = REGEX_PATTERN_PASSWORD.matcher(password);
        if (matchEmail.find() && matchPassword.find()) {
            for (Account temp : accountTable) {
                if (temp.email == email && temp.password == password) {
                    return temp;
                } else return null;
            }
        }
        return null;
    }

    @PostMapping("/register")
    Account register(
                    @RequestParam String name,
                    @RequestParam String email,
                    @RequestParam String password)
    {
        Matcher matchEmail = REGEX_PATTERN_EMAIL.matcher(email);
        Matcher matchPassword = REGEX_PATTERN_PASSWORD.matcher(password);
        if(!name.isBlank() && matchEmail.find() && matchPassword.find()){
            return new Account(name, email, password, 0);
        }
        return null;
    }

    @PostMapping("/{id}/registerStore")
    Store registerStore (@RequestParam int id,@RequestParam String name,@RequestParam String address,@RequestParam String phoneNumber){
        for (Account temp : accountTable){
            if(temp.id == id && (temp.store == null)){
                return new Store(name, address, phoneNumber, 0);
            }
        }
        return null;
    }

    @PostMapping("/{id}/topUp")
    boolean topUp (int id, double balance){
        for (Account temp : accountTable){
            if(temp.id == id){
                temp.balance += balance;
                return true;
            }
        }
        return false;
    }

    @GetMapping
    String index() { return "account page"; }

    //@GetMapping("/{id}")
    //String getById(@PathVariable int id) { return "account id " + id + " not found!"; }
}
