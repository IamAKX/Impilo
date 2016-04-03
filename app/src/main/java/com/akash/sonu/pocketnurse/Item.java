package com.akash.sonu.pocketnurse;

/**
 * Created by Akash on 8/9/2015.
 */
public class Item {
    private String Name;
    private String Phone;
    private String Address;

    public Item(){

    }

    public Item(String n, String p, String a){
        this.Name = n;
        this.Phone = p;
        this.Address = a;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

}