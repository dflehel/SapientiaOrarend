package ro.sapientia.ms.sapientiaorarend.Util;

import android.support.annotation.NonNull;
import com.google.firebase.database.*;
import ro.sapientia.ms.sapientiaorarend.Activity.LoginScreen;

import java.util.ArrayList;
import java.util.HashMap;

public class ClassPathBuilder {


    private DatabaseReference mdatabase;
    public static HashMap<String,HashMap<String,ArrayList<String>>>  classPath = new HashMap<>();
    public static boolean terminated = false;
    private static LoginScreen loginScreen;


    public ClassPathBuilder(HashMap<String, HashMap<String, ArrayList<String>>> classPath) {
        this.classPath = classPath;
    }


    public HashMap<String, HashMap<String, ArrayList<String>>> getClassPath() {
        return classPath;
    }

    public void setClassPath(HashMap<String, HashMap<String, ArrayList<String>>> classPath) {
        this.classPath = classPath;
    }

    public ClassPathBuilder(LoginScreen loginScreen) {
        this.loginScreen = loginScreen;
        this.mdatabase = FirebaseDatabase.getInstance().getReference().child("/timetables/");
        this.mdatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot depatment : dataSnapshot.getChildren()){
                        ClassPathBuilder.this.classPath.put(depatment.getKey(),new HashMap<String, ArrayList<String>>());
                        for(DataSnapshot departmentyear: depatment.getChildren()){
                           System.out.println(departmentyear.getKey());
                            System.out.println(departmentyear.getValue());
                            ClassPathBuilder.this.classPath.get(depatment.getKey()).put(departmentyear.getKey(),new ArrayList<String>());
                            for (DataSnapshot deparmentgroup:departmentyear.getChildren()){
                                ClassPathBuilder.this.classPath.get(depatment.getKey()).get(departmentyear.getKey()).add(deparmentgroup.getKey());
                            }
                        }
                    }
                    ClassPathBuilder.terminated = true;
                    if(ClassColorsBuilder.terminated && ClassPathBuilder.loginScreen.getTerminated()){
                        ClassPathBuilder.loginScreen.startingmainscreen();
                        ClassPathBuilder.loginScreen.getProgressDialog().dismiss();
                    }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public String toString() {
        return "ClassPathBuilder{" +
                "mdatabase=" + mdatabase +
                ", classPath=" + classPath +
                '}';
    }
}
