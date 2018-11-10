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

import java.util.ArrayList;

public class OnwTimeTableAdapter extends RecyclerView.Adapter<OnwTimeTableAdapter.OwnTimeTableViewHolder> {

    private ArrayList<Classes> c = new ArrayList<>();

    @NonNull
    public ArrayList<Classes> getC() {
        return c;
    }

    public void setC(@NonNull ArrayList<Classes> c) {
        this.c = c;
    }

    public OnwTimeTableAdapter() {


    }

    @Override
    public OwnTimeTableViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.own_time_table_item,viewGroup,false);
        return new OwnTimeTableViewHolder(v,this.c);
    }

    @Override
    public void onBindViewHolder(@NonNull OwnTimeTableViewHolder ownTimeTableViewHolder, int i) {
            ownTimeTableViewHolder.daytext.setText(AdapterContans.days[i]);
    }

    @Override
    public int getItemCount() {
        return AdapterContans.days.length;
    }

    public class OwnTimeTableViewHolder extends RecyclerView.ViewHolder {

        public TextView daytext;
        public RecyclerView rec;

        public OwnTimeTableViewHolder(@NonNull View itemView,ArrayList<Classes> c) {
            super(itemView);
            this.daytext = itemView.findViewById(R.id.own_time_table_item_day);
            this.rec = itemView.findViewById(R.id.own_time_table_item_rec);
            OnwTimeTableClassAdapter adapter = new OnwTimeTableClassAdapter(c);
            this.rec.setAdapter(adapter);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(itemView.getContext());
            this.rec.setLayoutManager(mLayoutManager);
            this.rec.setItemAnimator(new DefaultItemAnimator() );

        }
    }
}
