package ro.sapientia.ms.sapientiaorarend.models;

import ro.sapientia.ms.sapientiaorarend.Contans.AdapterContans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class TimeTable implements Serializable {

    private HashMap<String,HashMap<String,Days>> d = new HashMap<>();





    @Override
    public String toString() {
        return "TimeTable{" +
                "d=" + d +
                '}';
    }

    public TimeTable() {
        this.d.put("paratlanhet",new HashMap<String, Days>());
        for (String s : AdapterContans.days){
            this.d.get("paratlanhet").put(s,new Days());
        }
        this.d.put("paroshet",new HashMap<String, Days>());
        for (String s:AdapterContans.days ){
            this.d.get("paroshet").put(s,new Days());
        }
    }


    public HashMap<String, HashMap<String, Days>> getD() {
        return d;
    }

    public void setD(HashMap<String, HashMap<String, Days>> d) {
        this.d = d;
    }

    public void adClass(String whichweek, String whichday, Classes c){
        this.d.get(whichweek).get(whichday).addClass(c);
    }
}
