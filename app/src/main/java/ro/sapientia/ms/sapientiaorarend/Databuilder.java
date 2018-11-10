package ro.sapientia.ms.sapientiaorarend;

import android.support.annotation.NonNull;
import com.google.firebase.database.*;
import ro.sapientia.ms.sapientiaorarend.Adapters.GeneralTimeTableAdapter;
import ro.sapientia.ms.sapientiaorarend.models.Classes;
import ro.sapientia.ms.sapientiaorarend.models.Days;
import ro.sapientia.ms.sapientiaorarend.models.Mas;

import java.util.ArrayList;
import java.util.HashMap;

public class Databuilder {

    private ArrayList<Classes> classes = new ArrayList<>();
    private DatabaseReference mdatabase;
    private ArrayList<Object> sd = new ArrayList<>();
    private ArrayList<String> ds = new ArrayList<>();
    private HashMap<String,String> f = new HashMap<>();
    private BlankFragment g ;
    //private OwnTimeTableAdapter own = null;

    public Databuilder(ArrayList<Classes> classes) {
        this.classes = classes;
    }

    public Databuilder(BlankFragment g) {
        this.g = g;
        this.mdatabase= FirebaseDatabase.getInstance().getReference().child("/timetables/automatizalas/4/b/");
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
                Mas m = new Mas();

               for (DataSnapshot s:dataSnapshot.getChildren()) {
                   for (DataSnapshot ss : s.getChildren()) {
                       for (DataSnapshot sss : ss.getChildren()) {

                           //System.out.println(sss.getValue());
                           Classes c = sss.getValue(Classes.class);
                           m.adClass(s.getKey(),ss.getKey(),c);
                          // System.out.println(c.toString());
                       }
                   }

               }
               System.out.println(m.toString());
               Databuilder.this.g.adaptar.setD(m.getD().get("paratlanhet"));
               Databuilder.this.g.rec.setAdapter(Databuilder.this.g.adaptar);
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
