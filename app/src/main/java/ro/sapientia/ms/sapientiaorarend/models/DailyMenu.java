package ro.sapientia.ms.sapientiaorarend.models;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.TextView;
import com.google.firebase.database.*;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DailyMenu {

    private DatabaseReference mdatabase;
    private String[] foods;
    private TextView daydisplay;
    private TextView foodsdisplay;
    private String date;
    private ProgressDialog progressDialog;


    public DailyMenu() {

    }


    public DailyMenu(TextView daydisplay, TextView foodsdisplay, Context c) {
        this.daydisplay = daydisplay;
        this.foodsdisplay = foodsdisplay;
        this.progressDialog  = new ProgressDialog(c);
        this.progressDialog.setMessage("Betoltes");
        this.progressDialog.show();
        this.mdatabase = FirebaseDatabase.getInstance().getReference().child("/menu");

        this.mdatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DailyMenu.this.daydisplay.setText(dataSnapshot.getValue(String.class).split("&")[0]);
                DailyMenu.this.foods =  dataSnapshot.getValue(String.class).split("&")[1].split(",");
                DailyMenu.this.foodsdisplay.setText("");
                for (int i=0;i<DailyMenu.this.foods.length;++i){
                    DailyMenu.this.foodsdisplay.append(DailyMenu.this.foods[i]+"\n");
                }
                System.out.println(dataSnapshot.getValue(String.class));
                DailyMenu.this.progressDialog.dismiss();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public DatabaseReference getMdatabase() {
        return mdatabase;
    }

    public void setMdatabase(DatabaseReference mdatabase) {
        this.mdatabase = mdatabase;
    }

    public String[] getFoods() {
        return foods;
    }

    public void setFoods(String[] foods) {
        this.foods = foods;
    }

    public TextView getDaydisplay() {
        return daydisplay;
    }

    public void setDaydisplay(TextView daydisplay) {
        this.daydisplay = daydisplay;
    }

    public TextView getFoodsdisplay() {
        return foodsdisplay;
    }

    public void setFoodsdisplay(TextView foodsdisplay) {
        this.foodsdisplay = foodsdisplay;
    }
}
