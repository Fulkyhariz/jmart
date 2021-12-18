package com.fulkyJmartRK.controller;

import com.fulkyJmartRK.Account;
import com.fulkyJmartRK.Store;
import com.fulkyJmartRK.dbjson.JsonAutowired;
import com.fulkyJmartRK.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.security.*;

/**
 * fungsi yang mengatur get dan juga POST yang berhubungan dengan data akun
 * @author fulky hariz z
 */
@RestController
@RequestMapping("/account")
public class AccountController implements BasicGetController<Account> {
    //public static final String REGEX_EMAIL = "^[a-zA-Z0-9&*_~]+([\\.]?[a-zA-Z0-9&*_~]?)*@[A-Za-z0-9]+([-]?[\\.]?[A-Za-z0-9]+)+(^\\.)$";
    public static final String REGEX_EMAIL = "/*";
    //public static final String REGEX_PASSWORD = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";
    public static final String REGEX_PASSWORD = "/*";
    //public static final String REGEX_EMAIL = "^\\w+([\\.]?[&\\*~\\w+])*@\\w+([\\.-]?)*(\\.\\w{2,3})+$";
    //public static final String REGEX_PASSWORD = "^(?=.*[0-9])(?=.*[a-z])(?=\\S+$)(?=.*[A-Z]).{8,}$";
    public static final Pattern REGEX_PATTERN_EMAIL = Pattern.compile(REGEX_EMAIL);
    public static final Pattern REGEX_PATTERN_PASSWORD = Pattern.compile(REGEX_PASSWORD);

    @JsonAutowired(value = Account.class, filepath = "C:/Users/fulky/Documents/Akademik/5th Term/OOP/Prak/jmart backup/account.json")
    public static JsonTable<Account> accountTable;

    /**
     * method yang digunakan untuk mendapatkan list account dari JSON file
     * @return JsonTable class account
     */
    @Override
    public JsonTable<Account> getJsonTable() {
        return accountTable;
    }

    /**
     * method untuk melakukan login pada aplikasi, method akan menggunakan regex untuk
     * mengecek input dari user, serta melakukan hashing terhadap password, yang kemudian
     * hasil dari hashed password serta email akan dicocokan dengan data yang ada pada
     * accounttable
     * @param email merupakan email dari pengguna
     * @param password merupakan password dari pengguna yang akan dilakukan hashing nantinya
     * @return account yang berhasil login
     */
    @PostMapping("/login")
    Account login( @RequestParam String email, @RequestParam String password){
        Matcher matchEmail = REGEX_PATTERN_EMAIL.matcher(email);
        Matcher matchPassword = REGEX_PATTERN_PASSWORD.matcher(password);
        String hashedPassword = null;
        if (matchEmail.find() && matchPassword.find()) {
                try{
                    MessageDigest md = MessageDigest.getInstance("MD5");
                    md.update(password.getBytes());
                    byte[] bytes = md.digest();
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i< bytes.length; i++){
                        sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
                    }
                    hashedPassword = sb.toString();
                }catch (NoSuchAlgorithmException e){
                    e.printStackTrace();
                }
            for (Account temp : accountTable) {
                if (temp.email.equals(email) && temp.password.equals(hashedPassword)) {
                    return temp;
                }
            }
        }
        return null;
    }

    /**
     * method yang digunakan untuk mendaftarkan pengguna ke aplikasi
     * data yang dimasukan akan ditambahkan kedalam accountable yang selanjutnya disimpan
     * pada file JSON, password akan disimpan dalam bentuk hash
     * @param name nama dari pengguna
     * @param email email dari pengguna
     * @param password password yang akan disimpan dalam bentuk hash
     * @return account yang berhasil terdaftar
     */
    @PostMapping("/register")
    Account register(
                    @RequestParam String name,
                    @RequestParam String email,
                    @RequestParam String password)
    {
        Matcher matchEmail = REGEX_PATTERN_EMAIL.matcher(email);
        Matcher matchPassword = REGEX_PATTERN_PASSWORD.matcher(password);
        String hashedPassword = null;
        if(!name.isBlank() && matchEmail.find() && matchPassword.find()){
            try{
                MessageDigest md = MessageDigest.getInstance("MD5");
                md.update(password.getBytes());
                byte[] bytes = md.digest();
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i< bytes.length; i++){
                    sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
                }
                hashedPassword = sb.toString();
            }catch (NoSuchAlgorithmException e){
                e.printStackTrace();
            }
            Account akun = new Account(name, email, hashedPassword, 0);
            accountTable.add(akun);
            return akun;
        }
        return null;
    }

    /**
     * method yang digunakan untuk mendaftarkan store bagi user yang telah melakukan register
     * akan dilakukan pengecekan untuk memastikan akun belum memiliki store sebelumnya
     * @param id id dari akun
     * @param name nama toko
     * @param address alamat toko
     * @param phoneNumber nomor telefon toko
     * @return store yang berhasil didaftarkan
     */
    @PostMapping("/{id}/registerStore")
    Store registerStore (@RequestParam int id,@RequestParam String name,@RequestParam String address,@RequestParam String phoneNumber){
        for (Account temp : accountTable){
            if(temp.id == id && (temp.store == null)){
                temp.store = new Store(name, address, phoneNumber, 0);
                return temp.store;
            }
        }
        return null;
    }

    /**
     * method yang digunakan untuk mengatur top up dari saldo suatu akun
     * @param id id dari akun
     * @param balance jumlah saldo yang di topup
     * @return boolean yang menandakan top up berhasil atau tidak
     */
    @PostMapping("/{id}/topUp")
    boolean topUp (@RequestParam int id,@RequestParam double balance){
        for (Account temp : accountTable){
            if(temp.id == id){
                temp.balance += balance;
                return true;
            }
        }
        return false;
    }

}
