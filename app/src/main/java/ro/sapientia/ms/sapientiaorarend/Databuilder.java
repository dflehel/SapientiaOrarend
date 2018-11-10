package ro.sapientia.ms.sapientiaorarend;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.firebase.database.*;
import ro.sapientia.ms.sapientiaorarend.models.Classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Databuilder {

    private ArrayList<Classes> classes = new ArrayList<>();
    private DatabaseReference mdatabase;
    private ArrayList<Object> sd = new ArrayList<>();
    private ArrayList<String> ds = new ArrayList<>();
    private HashMap<String,String> f = new HashMap<>();
    //private OwnTimeTableAdapter own = null;

    public Databuilder(ArrayList<Classes> classes) {
        this.classes = classes;
    }

    public Databuilder() {
        this.mdatabase= FirebaseDatabase.getInstance().getReference().child("orarendek/szamitastechnika/4");
       // this.mdatabase.
      /* mdatabase.addChildEventListener(new ChildEventListener() {
           @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                //if (Databuilder.this.own == null){
                  //  Databuilder.this.own = timetable;
               // }
                /*Classes c = new Classes();
                Databuilder.this.f.putAll((Map<? extends String, ? extends String>) dataSnapshot.getValue());
                for (String ss :(Map<? extends String, ? extends String>) ((Map<? extends String, ? extends String>) dataSnapshot.getValue()).keySet() ){
                    c.setClas(Map<? extends String, ? extends String>) ((Map<? extends String, ? extends String>) dataSnapshot.getValue()).get(ss));
                    c.getMaterial(ss);
                    Databuilder.this.classes.add(c);
                }

               // Databuilder.this.own.no
                System.out.println("dfdsgHGFHfg'hg");
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    System.out.println("dfgdfh");
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("dfgdfh");
            }
        });*/
        this.mdatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot s:dataSnapshot.getChildren()){
                    System.out.println(s.getValue());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public ArrayList<Classes> getClasses() {
        return classes;
    }

    public void setClasses(ArrayList<Classes> classes) {
        this.classes = classes;
    }
}
