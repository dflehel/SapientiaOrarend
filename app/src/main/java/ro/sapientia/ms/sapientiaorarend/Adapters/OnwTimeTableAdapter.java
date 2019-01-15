package ro.sapientia.ms.sapientiaorarend.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import ro.sapientia.ms.sapientiaorarend.Constants.AdapterConstants;
import ro.sapientia.ms.sapientiaorarend.R;
import ro.sapientia.ms.sapientiaorarend.models.Classes;
import ro.sapientia.ms.sapientiaorarend.models.Days;
import ro.sapientia.ms.sapientiaorarend.models.TimeTable;

import java.util.ArrayList;
import java.util.HashMap;

public class OnwTimeTableAdapter extends RecyclerView.Adapter<OnwTimeTableAdapter.OwnTimeTableViewHolder> {

    private ArrayList<Classes> c = new ArrayList<>();
    private HashMap<String, Days> d = new HashMap<>();
    private String wichweek = "paratlanhet";
    private TimeTable m = new TimeTable();


    public OnwTimeTableAdapter() {


    }

    public HashMap<String, Days> getD() {
        return d;
    }

    public void setD(HashMap<String, Days> d) {
        this.d = d;
    }

    public String getWichweek() {
        return wichweek;
    }

    public void setWichweek(String wichweek) {
        this.wichweek = wichweek;
    }

    public TimeTable getM() {
        return m;
    }

    public void setM(TimeTable m) {
        this.m = m;
    }

    @NonNull
    public ArrayList<Classes> getC() {
        return c;
    }

    public void setC(@NonNull ArrayList<Classes> c) {
        this.c = c;
    }

    @Override
    public OwnTimeTableViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.own_time_table_item, viewGroup, false);
        return new OwnTimeTableViewHolder(v, this.c);
    }

    @Override
    public void onBindViewHolder(@NonNull OwnTimeTableViewHolder ownTimeTableViewHolder, int i) {
        ownTimeTableViewHolder.daytext.setText(AdapterConstants.days[i]);
        if (this.m.getD().get(this.wichweek).get(AdapterConstants.days[i]) != null) {
            ownTimeTableViewHolder.setdata(this.m.getD().get(this.wichweek).get(AdapterConstants.days[i]).getClasses());
        } else {
            ownTimeTableViewHolder.setdata(new ArrayList<Classes>());
        }
    }

    @Override
    public int getItemCount() {
        return AdapterConstants.days.length;
    }

    public class OwnTimeTableViewHolder extends RecyclerView.ViewHolder {

        public TextView daytext;
        public RecyclerView rec;
        public ArrayList<Classes> classes;

        public OwnTimeTableViewHolder(@NonNull View itemView, ArrayList<Classes> c) {
            super(itemView);
            this.daytext = itemView.findViewById(R.id.own_time_table_item_day);
            this.rec = itemView.findViewById(R.id.own_time_table_item_rec);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(itemView.getContext());
            this.rec.setLayoutManager(mLayoutManager);
            this.rec.setItemAnimator(new DefaultItemAnimator());

        }

        public void setdata(ArrayList<Classes> c) {
            this.classes = c;
            this.rec.setAdapter(new OnwTimeTableClassAdapter(this.classes));
        }
    }
}
