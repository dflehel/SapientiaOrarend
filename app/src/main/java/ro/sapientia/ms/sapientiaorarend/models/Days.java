package ro.sapientia.ms.sapientiaorarend.models;

import ro.sapientia.ms.sapientiaorarend.Contans.AdapterContans;

import java.util.ArrayList;
import java.util.HashMap;

public class Days {


    private ArrayList<Classes> classes = new ArrayList<>();


    public Days(ArrayList<Classes> classes) {
        this.classes = classes;
    }


    public Days() {
    }

    public ArrayList<Classes> getClasses() {
        return classes;
    }

    public void setClasses(ArrayList<Classes> classes) {
        this.classes = classes;
    }


    @Override
    public String toString() {
        return "Days{" +
                "classes=" + classes +
                '}';
    }


    public void  addClass(Classes c){
        this.classes.add(c);
    }
}