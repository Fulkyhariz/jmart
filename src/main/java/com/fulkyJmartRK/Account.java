package com.fulkyJmartRK;
import com.fulkyJmartRK.dbjson.Serializable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * class yang akan menyimpan data mengenai akun yang disimpan pada json table
 * @author fulky hariz
 */
public class Account extends Serializable
{
    public static final String REGEX_EMAIL = "^\\w+([\\.]?[&\\*~\\w+])*@\\w+([\\.-]?)*(\\.\\w{2,3})+$";
    public static final String REGEX_PASSWORD = "^(?=.*[0-9])(?=.*[a-z])(?=\\S+$)(?=.*[A-Z]).{8,}$";
    //public static final String REGEX_EMAIL = "^[a-zA-Z0-9&*_~]+([\\.]?[a-zA-Z0-9&*_~]?)*@[A-Za-z0-9]+([-]?[\\.]?[A-Za-z0-9]+)+(^\\.)$";
    //public static final String REGEX_PASSWORD = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";
    //public static final String REGEX_EMAIL = "/*";
    //public static final String REGEX_PASSWORD = "/*";
    public String name;
    public String email;
    public String password;
    public double balance;
    public Store store;

    /**
     * constructor untuk akun
     * @param name
     * @param email
     * @param password
     * @param balance
     */
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

    /**
     * method untuk memvalidasi akun terhadap regex
     * tidak digunakan karena validasi dilakukan pada account controller
     * @deprecated
     * @return
     */
    public boolean validate(){
        Pattern email = Pattern.compile(REGEX_EMAIL);
        Matcher match1 = email.matcher(this.email);
        Pattern password = Pattern.compile(REGEX_PASSWORD);
        Matcher match2 = password.matcher(this.password);
        return match1.find() && match2.find();
    }
}
