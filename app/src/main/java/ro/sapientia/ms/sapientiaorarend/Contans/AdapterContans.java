package ro.sapientia.ms.sapientiaorarend.Contans;

import ro.sapientia.ms.sapientiaorarend.models.Classes;

import java.util.ArrayList;
import java.util.Date;

public class AdapterContans {
    public AdapterContans() {
    }

    public final static String[] days = {"hetfo","kedd","szerda","csutortok","pentek","szombat"};

    public ArrayList<Classes> makedata(){
        ArrayList<Classes> c = new ArrayList<>();
        Classes cl = new Classes("Marton Lorincz","Robotika","128","DGDFg","fdgfgfdgfdgfdgfdgdfg");
        for (int i = 0 ; i<10;++i){
            c.add(cl);
        }
        return c;
    }
}
