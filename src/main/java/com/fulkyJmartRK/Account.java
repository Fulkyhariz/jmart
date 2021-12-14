package com.fulkyJmartRK;
import com.fulkyJmartRK.dbjson.Serializable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Account extends Serializable
{
    //public static final String REGEX_EMAIL = "^[a-zA-Z0-9&*_~]+([\\.]?[a-zA-Z0-9&*_~]?)*@[A-Za-z0-9]+([-]?[\\.]?[A-Za-z0-9]+)+(^\\.)$";
    //public static final String REGEX_PASSWORD = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";
    public static final String REGEX_EMAIL = "/*";
    public static final String REGEX_PASSWORD = "/*";
    public String name;
    public String email;
    public String password;
    public double balance;
    public Store store;
    
//    @Override
//    public boolean read(String content){
//        return false;
//    }
    public Account(String name, String email, String password, double balance){
        this.name = name;
        this.email = email;
        this.password = password;
        this.balance = balance;
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
