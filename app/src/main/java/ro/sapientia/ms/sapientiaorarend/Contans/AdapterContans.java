package ro.sapientia.ms.sapientiaorarend.Contans;

import ro.sapientia.ms.sapientiaorarend.models.Classes;

import java.util.ArrayList;
import java.util.Date;

public class AdapterContans {
    public AdapterContans() {
    }

    public final static String[] days = {"Hetfo","Kedd","Szerda","Csutortok","Pentek","Szombat"};

    public ArrayList<Classes> makedata(){
        ArrayList<Classes> c = new ArrayList<>();
        Classes cl = new Classes("Marton Lorincz","Robotika","128",new Date(),new Date());
        for (int i = 0 ; i<10;++i){
            c.add(cl);
        }
        return c;
    }
}
