package ro.sapientia.ms.sapientiaorarend.models;

import java.io.Serializable;
import java.util.ArrayList;

public class Days implements Serializable {


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


    public void addClass(Classes c) {
        this.classes.add(c);
    }
}
