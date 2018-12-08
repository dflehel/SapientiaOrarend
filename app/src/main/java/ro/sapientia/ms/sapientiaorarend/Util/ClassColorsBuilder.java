package ro.sapientia.ms.sapientiaorarend.Util;

import android.support.annotation.NonNull;
import com.google.firebase.database.*;
import ro.sapientia.ms.sapientiaorarend.Activity.LoginScreen;
import ro.sapientia.ms.sapientiaorarend.models.ClassColor;

import java.util.HashMap;

public class ClassColorsBuilder {


    private DatabaseReference mdatabase;
    public static HashMap<String,Integer>  colors = new HashMap<>();
    public static boolean terminated = false;
    private static LoginScreen loginScreen;






    public ClassColorsBuilder(LoginScreen loginScreen) {
        this.loginScreen = loginScreen;
        this.mdatabase = FirebaseDatabase.getInstance().getReference().child("/colors/");
        this.mdatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot data : dataSnapshot.getChildren()){
                    ClassColor classColor = data.getValue(ClassColor.class);
                    ClassColorsBuilder.colors.put(classColor.getTeacher(),Integer.parseInt(classColor.getClasscolor()));
                }
                ClassColorsBuilder.terminated = true;
                if(ClassPathBuilder.terminated && ClassColorsBuilder.loginScreen.getTerminated()){
                    ClassColorsBuilder.loginScreen.startingmainscreen();
                    ClassColorsBuilder.loginScreen.getProgressDialog().dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    @Override
    public String toString() {
        return "ClassColorsBuilder{" +
                "mdatabase=" + mdatabase +
                '}';
    }
}
