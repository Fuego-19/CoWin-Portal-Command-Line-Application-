package com.company;
import java.util.ArrayList;

class slot {
    private String Huid;
    private ArrayList<hslot> slots_days = new ArrayList<hslot>();            // This is the list where slots for different days has been store for a particular hospital

    slot(String Huid,int day, String vaccine, int quantity){
        hslot s1 = new hslot(day,vaccine,quantity);                         // hslot's object is created for a particular day
        slots_days.add(s1);                                                 //Added the object in the slot_days list.
        this.Huid = Huid;
    }
    public ArrayList<hslot> getSlots_days() {return this.slots_days;}
    public String getHuid(){
        return this.Huid;
    }


}
class hslot {
    private int day;                            //day for vaccine slot was booked.
    private String vaccine;
    private int quantity;

    hslot(int day, String vaccine, int quantity) {
        this.day = day;
        this.quantity = quantity;
        this.vaccine = vaccine;
    }

    public int getDay() {
        return this.day;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public String getVaccine() {
        return this.vaccine;
    }

    public void decQty() {
        this.quantity--;
    }
}