package com.company;
import java.util.Random;

public class hospitalData {
    private String name;
    private int pincode;
    private String Huid;

    hospitalData(String name, int pincode){
        this.name = name;
        this.pincode = pincode;
        Random rnd = new Random();
        int temp  = rnd.nextInt(999999);
        this.Huid = String.format("%06d",temp);
    }
    public String getName(){
        return this.name;
    }
    public int getPin(){
        return this.pincode;
    }
    public String getHuid(){
        return this.Huid;
    }
}
