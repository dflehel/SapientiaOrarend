package ro.sapientia.ms.sapientiaorarend.Adapters;


import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import ro.sapientia.ms.sapientiaorarend.Contans.AdapterContans;
import ro.sapientia.ms.sapientiaorarend.R;
import ro.sapientia.ms.sapientiaorarend.models.Classes;
import ro.sapientia.ms.sapientiaorarend.models.Days;
import ro.sapientia.ms.sapientiaorarend.models.Mas;

import java.util.ArrayList;
import java.util.HashMap;

public class GeneralTimeTableAdapter extends RecyclerView.Adapter<GeneralTimeTableAdapter.OwnTimeTableViewHolder>{


    private HashMap<String,Days> d = new HashMap<>();
    private Mas m = new Mas();
    private String wichweek = "paratlanhet";

    public String getWichweek() {
        return wichweek;
    }

    public void setWichweek(String wichweek) {
        this.wichweek = wichweek;
    }

    public Mas getM() {
        return m;
    }

    public void setM(Mas m) {
        this.m = m;
    }

    public GeneralTimeTableAdapter() {
    }

    public GeneralTimeTableAdapter(HashMap<String, Days> d) {
        this.d = d;
    }




    @NonNull
    @Override
    public GeneralTimeTableAdapter.OwnTimeTableViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.own_time_table_item,viewGroup,false);
        return new GeneralTimeTableAdapter.OwnTimeTableViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull GeneralTimeTableAdapter.OwnTimeTableViewHolder ownTimeTableViewHolder, int i) {
        ownTimeTableViewHolder.daytext.setText(AdapterContans.days[i]);
        ownTimeTableViewHolder.setdata(this.m.getD().get(this.wichweek).get(AdapterContans.days[i]).getClasses());
    }

    @Override
    public int getItemCount() {
        return AdapterContans.days.length;
    }

    public HashMap<String, Days> getD() {
        return d;
    }

    public void setD(HashMap<String, Days> d) {
        this.d = d;
    }

    public class OwnTimeTableViewHolder extends RecyclerView.ViewHolder{

        public TextView daytext;
        public RecyclerView rec;
        public ArrayList<Classes> classes;


        public OwnTimeTableViewHolder(@NonNull View itemView) {
            super(itemView);
            this.daytext = itemView.findViewById(R.id.own_time_table_item_day);
            this.rec = itemView.findViewById(R.id.own_time_table_item_rec);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(itemView.getContext());
            this.rec.setLayoutManager(mLayoutManager);
            this.rec.setItemAnimator(new DefaultItemAnimator() );
    }
        public  void setdata( ArrayList<Classes> c){
            this.classes = c;
            this.rec.setAdapter(new GeneralTimeTableClassAdapter(this.classes));
        }
    }
}
