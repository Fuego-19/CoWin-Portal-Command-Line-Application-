package com.company;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static ArrayList<vaccine> vaccineList = new ArrayList<vaccine>();         //All vaccines data is stored here.
    public static ArrayList<citizen> citizens = new ArrayList<citizen>();            //All citizens data is stored here.
    public static ArrayList<hospitalData> hospitals = new ArrayList<hospitalData>(); //All hospitals data is stored here.
    public static ArrayList<slot> slots = new ArrayList<slot>();                     //All hospitals slots are stored here.
    public static Scanner scn = new Scanner(System.in);


    public static void addVaccine(){
        System.out.print("Vaccine Name: ");
        String vaccine_name = scn.next();
        System.out.print("Number of Doses: ");
        int doses = scn.nextInt();
        if(doses==1){
            int gap = 0;
            vaccine v = new vaccine(vaccine_name,doses,gap);
            vaccineList.add(v);
            System.out.println("Vaccine Name: " + vaccine_name + ", " + "Number of Doses: " + doses + ", " + "Gap Between Doses: " + gap);
            return;
        }
        System.out.print("Gap Between Doses: ");
        int gap = scn.nextInt();
        vaccine v = new vaccine(vaccine_name,doses,gap);
        vaccineList.add(v);
        System.out.println("Vaccine Name: " + vaccine_name + ", " + "Number of Doses: " + doses + ", " + "Gap Between Doses: " + gap);
    }
    public static void registerHospital(){
        System.out.print("Hospital Name: ");
        String hname = scn.next();
        System.out.print("PinCode: ");
        int pincode = scn.nextInt();
        if(String.valueOf(pincode).length() != 6){
            System.out.println("Pincode must be a 6 digit number.");
            return;
        }
        hospitalData d = new hospitalData(hname,pincode);
        hospitals.add(d);
        System.out.println("Hospital Name: " + hname + ", PinCode: " + pincode + ", Unique ID: " + d.getHuid());
    }
    public static void registerCitizen(){
        System.out.print("Citizen Name: ");
        String name = scn.next();
        System.out.print("Age: ");
        int age = scn.nextInt();
        if(age<18){
            System.out.println("Only above 18 are allowed");
            return;
        }
        System.out.print("Unique ID: ");
        String uid = scn.next();
        for(int m =0; m<citizens.size();m++){
            if(citizens.get(m).getUid().equals(uid)){
                System.out.println("Another citizen already registered with the given uid. TRY AGAIN");
                return;
            }
        }
        int len1 = uid.length();
        if(len1!=12){
            System.out.println("wrong input of uid");
            return;
        }
        citizen c = new citizen(name, age,uid);
        citizens.add(c);
        System.out.println("Citizen Name: " + name + ", Age: " + age + ", Unique ID: " + uid);
    }
    public static void addslot(){
        System.out.print("Enter Hospital ID: ");
        String huid = scn.next();
        boolean flag = true;
        for (int j = 0; j < hospitals.size(); j++) {
            if (hospitals.get(j).getHuid().equals(huid)) {
                flag = false;
                break;
            }
        }
        if (flag) {
            System.out.println("Hospital hasn't been registered yet.");
            return;
        }
        System.out.print("Enter number of Slots to be added: ");
        int slts = scn.nextInt();
        while(slts > 0) {
            System.out.print("Enter Day Number: ");
            int dnum = scn.nextInt();
            System.out.print("Enter Quantity: ");
            int qty = scn.nextInt();
            System.out.println("Select Vaccine");
            for (int i = 0; i < vaccineList.size(); i++) {
                System.out.println(i + ". " + vaccineList.get(i).getVname());
            }
            boolean flagsslot = true;
            int sel = scn.nextInt();
            String vac = vaccineList.get(sel).getVname();
            for(int m =0; m < slots.size(); m++){
                if(huid == slots.get(m).getHuid()){
                    flagsslot= false;
                    hslot h1 = new hslot(dnum,vac,qty);
                    slots.get(m).getSlots_days().add(h1);
                    System.out.println("Slot added by Hospital " + huid + " for Day: " + dnum +", Available Quantity: " + qty + " of vaccine " + vac );
                }
            }
            if(flagsslot) {
                slot s1 = new slot(huid, dnum, vac, qty);
                slots.add(s1);
                System.out.println("Slot added by Hospital " + huid + " for Day: " + dnum + ", Available Quantity: " + qty + " of vaccine " + vac);
            }
            slts--;
        }
    }
    public static void bookslot(){
        System.out.print("Enter patient Unique ID: ");
        String uid = scn.next();
        citizen c1 = citizens.get(0);
        boolean flag1 = true;
        for(int k = 0; k<citizens.size(); k++){
            if(citizens.get(k).getUid().equals(uid)){
                c1 = citizens.get(k);
                flag1 = false;
            }
        }
        if(flag1){
            System.out.println("No citizen registered with this uid.");
            return;
        }
        System.out.print("1. Search by area\n2. Search by Vaccine\n3. Exit");
        System.out.print("\nEnter option: ");
        int op= scn.nextInt();
        if(op == 1){
            System.out.print("Enter PinCode: ");
            int pc = scn.nextInt();
            boolean flag = true;
            for(int i = 0; i<hospitals.size();i++){
                if(hospitals.get(i).getPin() == pc){
                    System.out.println(hospitals.get(i).getHuid() + " " + hospitals.get(i).getName());
                    flag = false;
                }
            }
            if(flag){
                System.out.println("No hospitals in your area.");
                return;
            }
            System.out.print("Enter hospital id: ");
            String id = scn.next();
            boolean flaghospital = true;
            int i;
            int slt1 = 0;
            for( i =0 ; i< slots.size(); i++){
                int cnt = 0;
                if(slots.get(i).getHuid().equals(id)){
                    slt1 = i;
                    flaghospital = false;
                    if(slots.get(i).getSlots_days().size()==0) {                         //This will lead us to a particular hospital's slots for different days list.
                        System.out.println("No slots available");
                        return;
                    }
                    else{
                        for(int j = 0; j<slots.get(i).getSlots_days().size();j++){
                            int Day = slots.get(i).getSlots_days().get(j).getDay();      // A particular hospital's particular day will be printed with this.
                            int Qty = slots.get(i).getSlots_days().get(j).getQuantity();
                            String vacName = slots.get(i).getSlots_days().get(j).getVaccine();
                            System.out.println(cnt + "-> " + "Day: " + Day + " Available Qty: " + Qty + " Vaccine: " + vacName);
                            cnt++;
                        }
                    }
                }
            }

            if(flaghospital){
                System.out.println("No slots available or invalid hospital ID entered");
                return;
            }
            System.out.print("Choose slot: ");
            int sl = scn.nextInt();
            if(sl>slots.get(slt1).getSlots_days().size()){
                System.out.println("wrong input");
                return;
            }
            if(slots.get(slt1).getSlots_days().get(sl).getQuantity() == 0){                    //Checking if quantity of vaccine is greater than zero
                System.out.println("No doses available for this slot(quantity is zero).");
                return;
            }
            String vname = slots.get(slt1).getSlots_days().get(sl).getVaccine();               //Vaccine choosen
            int doses = 0;
            int Vgap = 1;
            int h;
            for(h = 0;h<vaccineList.size();h++){
                if(vaccineList.get(h).getVname().equals(vname)){
                    doses = vaccineList.get(h).getDoses();              //finding required doses for the Vaccine
                    Vgap = vaccineList.get(h).getGap();
                }
            }
            if(c1.getDosesGiven() == 0){
                c1.incdosesGiven();                                                 //Incrementing dose given by 1.
                ArrayList<hslot> s1 = slots.get(slt1).getSlots_days();
                c1.setVname(s1.get(sl).getVaccine());                              //Vaccine name set after the 1st dose
                int vday = slots.get(slt1).getSlots_days().get(sl).getDay();
                c1.setVaccineDay(vday);                                             //setting latest vaccination day
                slots.get(slt1).getSlots_days().get(sl).decQty();                      //Quantity of dose will be decremented by 1
                System.out.println(c1.getName() + " vaccinated with " +c1.getVname() );
                if(c1.getDosesGiven() < doses) c1.setVstatus("PARTIALLY VACCINATED");
                else c1.setVstatus("FULLY VACCINATED");
            }

            else if(c1.getDosesGiven() == doses){
                System.out.println("Already vaccinated with the required no. of doses.");
                return;
            }
            else if(c1.getDosesGiven() < doses){
                if(slots.get(slt1).getSlots_days().get(sl).getVaccine().equals(c1.getVname())){          //checking fro vaccine mixing.
                    if(c1.getVaccineDay() + Vgap <= slots.get(slt1).getSlots_days().get(sl).getDay()){  //checking for the gap b/w doses
                        c1.incdosesGiven();                                                          //incrementing no. of doses given by 1
                        c1.setVaccineDay(slots.get(slt1).getSlots_days().get(sl).getDay());             //setting latest vaccination day
                        slots.get(slt1).getSlots_days().get(sl).decQty();                               //Quantity of dose will be decremented by 1
                        System.out.println(c1.getName() + " vaccinated with " + c1.getVname() );
                        if(c1.getDosesGiven() < doses) c1.setVstatus("PARTIALLY VACCINATED");
                        else c1.setVstatus("FULLY VACCINATED");

                    }
                    else{
                        System.out.println("You're booking vaccine earlier than the stipulated duration.");
                        return;
                    }
                }
                else{
                    System.out.println("You were vaccinated with a different vaccine earlier.");
                    return;
                }
            }

        }
        else{
            System.out.print("Enter Vaccine name: ");
            String vaccName = scn.next();
            boolean flags = true;
            for(int i = 0; i<slots.size();i++){
                for(int j =0; j<slots.get(i).getSlots_days().size(); j++){                      //Iterating in list of hospitals's list of slots.
                    if(slots.get(i).getSlots_days().get(j).getVaccine().equals(vaccName)){       // If slot's vaccince name is same as required vaccine
                        String hname = hospitalName(slots.get(i).getHuid());
                        System.out.println(slots.get(i).getHuid() + " " +  hname );
                        flags = false;
                        break;
                    }
                }
            }
            if(flags){
                System.out.println("No hospital available with the given Vaccine");
                return;
            }
            System.out.print("Enter hospital id: ");
            String id = scn.next();
            boolean flaghospital = true;
            int i;
            int slt = 0;
            for( i =0 ; i< slots.size(); i++){
                if(slots.get(i).getHuid().equals(id)){
                    slt = i;
                    flaghospital = false;
                    int cnt =0;
                    if(slots.get(i).getSlots_days().size()==0) {                    //This will lead us to a particular hospital's slots for different days list.
                        System.out.println("No slots available");
                        return;
                    }
                    else{
                        for(int j = 0; j<slots.get(i).getSlots_days().size();j++){
                            if(vaccName.equals(slots.get(i).getSlots_days().get(j).getVaccine())) {
                                int Day = slots.get(i).getSlots_days().get(j).getDay();  // A particular hospital's particular day will be printed with this.
                                int Qty = slots.get(i).getSlots_days().get(j).getQuantity();
                                System.out.println(cnt + "->" + "Day: " + Day + " Available Qty: " + Qty + " Vaccine: " + vaccName);
                                cnt++;
                            }
                        }
                    }
                }
            }
            i--;
            if(flaghospital){
                System.out.println("No slots available or invalid hospital ID entered");
                return;
            }
            System.out.print("Choose slot: ");
            int sl = scn.nextInt();
            if(sl>slots.get(slt).getSlots_days().size()){
                System.out.println("wrong input");
                return;
            }
            if(slots.get(slt).getSlots_days().get(sl).getQuantity() == 0){
                System.out.println("No doses available for this slot(quantity is zero).");
                return;
            }
            String vname = slots.get(slt).getSlots_days().get(sl).getVaccine();
            int doses = 0;
            int Vgap = 1;
            for(int h = 0;h<vaccineList.size();h++){
                if(vaccineList.get(h).getVname().equals(vname)){
                    doses = vaccineList.get(h).getDoses();              //finding required doses for the Vaccine
                    Vgap = vaccineList.get(h).getGap();
                }
            }
            if(c1.getDosesGiven() == 0){
                c1.incdosesGiven();                                                 //Incrementing dose given by 1.
                c1.setVname(slots.get(slt).getSlots_days().get(sl).getVaccine());     //Vaccine name set after the 1st dose
                c1.setVaccineDay(slots.get(slt).getSlots_days().get(sl).getDay());    //setting latest vaccination day
                slots.get(slt).getSlots_days().get(sl).decQty();                      //Quantity of dose will be decremented by 1
                System.out.println(c1.getName() + " vaccinated with " + c1.getVname() );
                if(c1.getDosesGiven() < doses) c1.setVstatus("PARTIALLY VACCINATED");
                else c1.setVstatus("FULLY VACCINATED");
            }

            else if(c1.getDosesGiven() == doses){
                System.out.println("Already vaccinated with the required no. of doses.");
                return;
            }
            else if(c1.getDosesGiven() < doses){
                if(slots.get(slt).getSlots_days().get(sl).getVaccine().equals(c1.getVname())){         //checking for vaccine mixing.
                    if(c1.getVaccineDay() + Vgap <= slots.get(slt).getSlots_days().get(sl).getDay()){  //checking for the gap b/w doses
                        c1.incdosesGiven();                                                          //incrementing no. of doses given by 1
                        c1.setVaccineDay(slots.get(slt).getSlots_days().get(sl).getDay());             //setting latest vaccination day
                        slots.get(slt).getSlots_days().get(sl).decQty();                               //Quantity of dose will be decremented by 1
                        System.out.println(c1.getName() + " vaccinated with " + c1.getVname());
                        if(c1.getDosesGiven() < doses) c1.setVstatus("PARTIALLY VACCINATED");
                        else c1.setVstatus("FULLY VACCINATED");
                    }
                    else{
                        System.out.println("You're booking vaccine earlier than the stipulated duration.");
                        return;
                    }
                }
                else{
                    System.out.println("You were vaccinated with a different vaccine earlier.");
                    return;
                }
            }
        }
    }

    public static void listSlots(){
        System.out.print("Enter Hospital ID: ");
        String huid = scn.next();

        for(int i =0; i<slots.size();i++){
            if(huid.equals(slots.get(i).getHuid())){
                for(int j =0; j<slots.get(i).getSlots_days().size();j++){
                    hslot slt = slots.get(i).getSlots_days().get(j);
                    System.out.println("Day: " + slt.getDay() + " Vaccine: " + slt.getVaccine() + " Available Qty: " + slt.getQuantity());
                }
            }
        }
    }
    public static void status(){
        System.out.print("Enter Patient ID: ");
        String id = scn.next();
        citizen c = citizens.get(0);
        boolean flag = true;
        for(int i =0; i<citizens.size(); i++){
            if(id.equals(citizens.get(i).getUid())){
                c = citizens.get(i);
                System.out.println(c.getVstatus());
                System.out.println("Vaccine given: " + c.getVname());
                System.out.println("Number of Doses given: " + c.getDosesGiven());
                if(c.getVstatus().equals("PARTIALLY VACCINATED")){
                    int dose = c.getVaccineDay() + doseGetter(c.getVname());
                    System.out.println("Next Dose due date: " + dose);
                }
                flag = false;
                return;
            }
        }
        System.out.println("Citizen with this uid has not been registered yet.");
    }

    /* ______ Some extra functions used ______:- */

    public static String hospitalName(String huid){
        for(int i =0; i<hospitals.size(); i++){
            if(huid.equals(hospitals.get(i).getHuid())){
                return hospitals.get(i).getName();
            }
        }
        return "-1";
    }
    public static void menu(){
        System.out.println("\nCoWin Portal initialized....");
        System.out.println("---------------------------------");
        System.out.println("1. Add Vaccine\n2. Register Hospital\n3. Register Citizen\n4. Add Slot for Vaccination");
        System.out.println("5. Book Slot for Vaccination\n6. List all slots for a hospital\n7. Check Vaccination Status\n8. Exit");
        System.out.println("---------------------------------");
    }

    public static int doseGetter(String VaccineName){
        for(int i =0; i< vaccineList.size(); i++){
            if(VaccineName.equals(vaccineList.get(i).getVname())){
                return vaccineList.get(i).getDoses();
            }
        }
        return -1;
    }

    /* ----- Main Function ----- */

    public static void main(String[] args) {

        while(true){
            menu();
            System.out.print("\nEnter your choice: ");
            int choice = scn.nextInt();
            if(choice == 8){
                break;
            }
            if(choice<1 || choice>8) {
                System.out.println("Invalid input, try again: ");
                continue;
            }

            switch (choice) {
                case 1:
                    addVaccine();
                    break;
                case 2:
                    registerHospital();
                    break;
                case 3:
                    registerCitizen();
                    break;
                case 4:
                    addslot();
                    break;
                case 5:
                    bookslot();
                    break;
                case 6:
                    listSlots();
                    break;
                case 7:
                    status();
                    break;
            }
        }
    }
}
