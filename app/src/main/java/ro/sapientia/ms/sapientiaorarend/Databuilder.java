package ro.sapientia.ms.sapientiaorarend;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.*;
import ro.sapientia.ms.sapientiaorarend.models.Classes;
import ro.sapientia.ms.sapientiaorarend.models.Mas;
import ro.sapientia.ms.sapientiaorarend.models.User;

import java.util.ArrayList;
import java.util.HashMap;

public class Databuilder {

    private ArrayList<Classes> classes = new ArrayList<>();
    private DatabaseReference mdatabase;
    private ArrayList<Object> sd = new ArrayList<>();
    private ArrayList<String> ds = new ArrayList<>();
    private HashMap<String,String> f = new HashMap<>();
    private BlankFragment g ;
    private OwnTimeTable ownTimeTable;
    private ProgressDialog progressDialog;
    private TextView deparmentview;
    //private OwnTimeTableAdapter own = null;

    public Databuilder(ArrayList<Classes> classes) {
        this.classes = classes;
    }


    public void shearchfortimetable(final String s){
        String path[] = s.split(" ");
        this.mdatabase = FirebaseDatabase.getInstance().getReference().child("/timetables/"+path[0]+"/"+path[1]+"/"+path[2]);
        this.progressDialog.setMessage("Betöltés");
        this.progressDialog.show();
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
                Databuilder.this.g.adaptar.setM(m);
                // Databuilder.this.g.adaptar.setD(m.getD().get("paratlanhet"));
                Databuilder.this.g.rec.setAdapter(Databuilder.this.g.adaptar);
                Databuilder.this.g.deparmentview.setText(s);
                Databuilder.this.g.departmenttext = s;

                Databuilder.this.progressDialog.dismiss();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    public Databuilder(BlankFragment g, Context c,String s) {
        this.g = g;
        this.progressDialog = new ProgressDialog(c);
        this.progressDialog.setMessage("Betöltés");
        this.progressDialog.show();
        //this.mdatabase = FirebaseDatabase.getInstance().getReference().child("/timetables/szamitastechnika/4/a");
        this.mdatabase = FirebaseDatabase.getInstance().getReference().child("/timetables");
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
                for (DataSnapshot d : dataSnapshot.getChildren()) {
                    for (DataSnapshot dd: d.getChildren()) {
                        for (DataSnapshot ddd: dd.getChildren()) {
                            Mas m = new Mas();

                            for (DataSnapshot s : ddd
                                    .getChildren()) {
                                for (DataSnapshot ss : s.getChildren()) {
                                    for (DataSnapshot sss : ss.getChildren()) {

                                        //System.out.println(sss.getValue());
                                        Classes c = sss.getValue(Classes.class);
                                        m.adClass(s.getKey(), ss.getKey(), c);
                                        // System.out.println(c.toString());
                                    }
                                }

                            }
                            System.out.println(m.toString());
                            Databuilder.this.g.adaptar.setM(m);
                            // Databuilder.this.g.adaptar.setD(m.getD().get("paratlanhet"));
                            Databuilder.this.g.rec.setAdapter(Databuilder.this.g.adaptar);
                            Databuilder.this.g.deparmentview.setText(d.getKey()+" "+dd.getKey()+" "+ddd.getKey());
                            Databuilder.this.g.departmenttext = d.getKey()+" "+dd.getKey()+" "+ddd.getKey();
                            Databuilder.this.progressDialog.dismiss();
                            break;
                        }
                        break;
                    }
                    break;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public Databuilder(OwnTimeTable ownTimeTable, Context c, final User user){
        this.ownTimeTable = ownTimeTable;
        this.progressDialog = new ProgressDialog(c);
        this.progressDialog.setMessage("Betöltés");
        this.progressDialog.show();
        this.mdatabase = FirebaseDatabase.getInstance().getReference().child("/timetables/").child(user.getDeparment());
        this.mdatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Mas m = new Mas();

                for (DataSnapshot s : dataSnapshot.getChildren()) {
                    for (DataSnapshot ss : s.getChildren()) {
                        for (DataSnapshot sss : ss.getChildren()) {

                            //System.out.println(sss.getValue());
                            Classes c = sss.getValue(Classes.class);
                            m.adClass(s.getKey(), ss.getKey(), c);
                            // System.out.println(c.toString());
                        }
                    }

                }
                System.out.println(m.toString());
                Databuilder.this.ownTimeTable.getAdapter().setM(m);
                Databuilder.this.ownTimeTable.getAdapter().setD(m.getD().get("paratlanhet"));
                DatabaseReference databse = FirebaseDatabase.getInstance().getReference().child("/user").child(FirebaseAuth.getInstance().getUid());
                user.setTimetable(m);
                databse.setValue(user);
                Databuilder.this.progressDialog.dismiss();
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
