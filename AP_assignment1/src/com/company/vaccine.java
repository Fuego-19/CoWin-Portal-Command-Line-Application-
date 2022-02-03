package com.company;

public class vaccine {
    private String vname;
    private int doses;
    private int gap;

    vaccine(String vname, int doses, int gap){
        this.doses = doses;
        this.gap = gap;
        this.vname = vname;
    }

    public String getVname(){
        return this.vname;
    }
    public int getDoses(){
        return this.doses;
    }
    public int getGap(){
        return this.gap;
    }

}
