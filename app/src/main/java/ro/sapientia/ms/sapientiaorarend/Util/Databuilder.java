package ro.sapientia.ms.sapientiaorarend.Util;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.*;
import ro.sapientia.ms.sapientiaorarend.Fragments.GeneralTimeTable;
import ro.sapientia.ms.sapientiaorarend.Fragments.OwnTimeTable;
import ro.sapientia.ms.sapientiaorarend.models.Classes;
import ro.sapientia.ms.sapientiaorarend.models.TimeTable;
import ro.sapientia.ms.sapientiaorarend.models.User;

import java.util.ArrayList;
import java.util.HashMap;

public class Databuilder {

    private ArrayList<Classes> classes = new ArrayList<>();
    private DatabaseReference mdatabase;
    private ArrayList<Object> sd = new ArrayList<>();
    private ArrayList<String> ds = new ArrayList<>();
    private HashMap<String, String> f = new HashMap<>();
    private GeneralTimeTable g;
    private OwnTimeTable ownTimeTable;
    private ProgressDialog progressDialog;
    private TextView deparmentview;

    public Databuilder(ArrayList<Classes> classes) {
        this.classes = classes;
    }


    public Databuilder(GeneralTimeTable g, Context c, String s) {
        this.g = g;
        this.progressDialog = new ProgressDialog(c);
        this.progressDialog.setMessage("Betöltés");
        this.progressDialog.show();
        this.mdatabase = FirebaseDatabase.getInstance().getReference().child("/timetables");
        this.mdatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot department : dataSnapshot.getChildren()) {
                    for (DataSnapshot year : department.getChildren()) {
                        for (DataSnapshot group : year.getChildren()) {
                            TimeTable m = new TimeTable();

                            for (DataSnapshot week : group.getChildren()) {
                                for (DataSnapshot day : week.getChildren()) {
                                    for (DataSnapshot clas : day.getChildren()) {

                                        //System.out.println(sss.getValue());
                                        Classes c = clas.getValue(Classes.class);
                                        m.adClass(week.getKey(), day.getKey(), c);
                                        // System.out.println(c.toString());
                                    }
                                }

                            }
                            System.out.println(m.toString());
                            Databuilder.this.g.getAdaptar().setM(m);
                            // Databuilder.this.g.adaptar.setD(m.getD().get("paratlanhet"));
                            Databuilder.this.g.getRec().setAdapter(Databuilder.this.g.getAdaptar());
                            Databuilder.this.g.getDeparmentview().setText(department.getKey() + " " + year.getKey() + " " + group.getKey());
                            Databuilder.this.g.setDepartmenttext(department.getKey() + " " + year.getKey() + " " + group.getKey());
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


    public Databuilder(OwnTimeTable ownTimeTable, Context c, final User user) {
        this.ownTimeTable = ownTimeTable;
        this.progressDialog = new ProgressDialog(c);
        this.progressDialog.setMessage("Betöltés");
        this.progressDialog.show();
        this.mdatabase = FirebaseDatabase.getInstance().getReference().child("/timetables/").child(user.getDeparment());
        this.mdatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                TimeTable m = new TimeTable();

                for (DataSnapshot whichweek : dataSnapshot.getChildren()) {
                    for (DataSnapshot whichday : whichweek.getChildren()) {
                        for (DataSnapshot whichclass : whichday.getChildren()) {

                            //System.out.println(sss.getValue());
                            Classes c = whichclass.getValue(Classes.class);
                            m.adClass(whichweek.getKey(), whichday.getKey(), c);
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

    public void shearchfortimetable(final String s) {
        String path[] = s.split(" ");
        this.mdatabase = FirebaseDatabase.getInstance().getReference().child("/timetables/" + path[0] + "/" + path[1] + "/" + path[2]);
        this.progressDialog.setMessage("Betöltés");
        this.progressDialog.show();
        this.mdatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                TimeTable m = new TimeTable();

                for (DataSnapshot whichweek : dataSnapshot.getChildren()) {
                    for (DataSnapshot whichday : whichweek.getChildren()) {
                        for (DataSnapshot whichclass : whichday.getChildren()) {
                            Classes c = whichclass.getValue(Classes.class);
                            m.adClass(whichweek.getKey(), whichday.getKey(), c);
                        }
                    }

                }
                System.out.println(m.toString());
                Databuilder.this.g.getAdaptar().setM(m);
                Databuilder.this.g.getRec().setAdapter(Databuilder.this.g.getAdaptar());
                Databuilder.this.g.getDeparmentview().setText(s);
                Databuilder.this.g.setDepartmenttext(s);

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
