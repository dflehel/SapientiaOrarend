package ro.sapientia.ms.sapientiaorarend.models;

import android.support.annotation.NonNull;
import com.google.firebase.database.*;

import java.util.ArrayList;
import java.util.HashMap;

public class ClassColorsBuilder {


    private DatabaseReference mdatabase;
    public static HashMap<String,Integer>  colors = new HashMap<>();






    public ClassColorsBuilder() {
        this.mdatabase = FirebaseDatabase.getInstance().getReference().child("/colors/");
        this.mdatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot data : dataSnapshot.getChildren()){
                    ClassColor classColor = data.getValue(ClassColor.class);
                    ClassColorsBuilder.colors.put(classColor.getTeacher(),Integer.parseInt(classColor.getClasscolor()));
                }
                System.out.println(ClassColorsBuilder.colors.toString());
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
