package com.company;

public class citizen {
    private String name;
    private int age;
    private String uid;
    private String vstatus;                     //vaccine status
    private String vname;                      //vaccine name
    private int dosesGiven = 0;               //Total doses given
    private int VaccineDay;                  //Day of last vaccination dose
    citizen(String name, int age, String uid){
        this.age = age;
        this.uid = uid;
        this.name = name;
        this.vstatus = "REGISTERED";
    }
    public int getAge(){
        return this.age;
    }
    public String getUid(){
        return this.uid;
    }
    public String getName(){
        return this.name;
    }
    public void setVstatus(String vstatus){
        this.vstatus = vstatus;
    }
    public String getVstatus(){                     // For updating vaccination status.
        return this.vstatus;
    }
    public void setVname(String vname){             // For updating vaccine name by which the person has been vaccinated.
        this.vname = vname;
    }
    public String getVname(){
        return this.vname;
    }
    public void incdosesGiven(){ this.dosesGiven+=1;}
    public int getDosesGiven(){ return this.dosesGiven;}
    public void setVaccineDay(int day){ this.VaccineDay = day;}
    public int getVaccineDay(){
        return this.VaccineDay;
    }
}
