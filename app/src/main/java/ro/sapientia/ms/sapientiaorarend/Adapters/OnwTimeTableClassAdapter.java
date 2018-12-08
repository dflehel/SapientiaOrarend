package ro.sapientia.ms.sapientiaorarend.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import ro.sapientia.ms.sapientiaorarend.Contans.AdapterContans;
import ro.sapientia.ms.sapientiaorarend.OwnTimeTable;
import ro.sapientia.ms.sapientiaorarend.R;
import ro.sapientia.ms.sapientiaorarend.models.ClassColorsBuilder;
import ro.sapientia.ms.sapientiaorarend.models.Classes;

import java.util.ArrayList;

public class OnwTimeTableClassAdapter extends  RecyclerView.Adapter<OnwTimeTableClassAdapter.OwnTimeTableViewClassHolder>{
    @NonNull
    private ArrayList<Classes> c = new ArrayList<>();
    public int valto;
    public ArrayList<Classes> getC() {
        return c;
    }

    public void setC(ArrayList<Classes> c) {
        this.c = c;
    }
    public OnwTimeTableClassAdapter(@NonNull ArrayList<Classes> c) {
        this.c = c;
    }

    @Override
    public OwnTimeTableViewClassHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.own_time_table_class_item,viewGroup,false);
        return new OwnTimeTableViewClassHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull OwnTimeTableViewClassHolder ownTimeTableViewClassHolder, int i) {
        ownTimeTableViewClassHolder.teacher.setText(this.c.get(i).getTeacher());
        ownTimeTableViewClassHolder.material.setText(this.c.get(i).getMaterial());
        ownTimeTableViewClassHolder.classroom.setText(this.c.get(i).getClassroom());
        ownTimeTableViewClassHolder.startt.setText(this.c.get(i).getStart().toString());
        ownTimeTableViewClassHolder.endt.setText(this.c.get(i).getEnd().toString());
        if (ClassColorsBuilder.colors.get(this.c.get(i).getTeacher()) != null) {
            ownTimeTableViewClassHolder.cardView.setCardBackgroundColor(ClassColorsBuilder.colors.get(this.c.get(i).getTeacher()));
        }
    }

    @Override
    public int getItemCount() {
        return this.c.size();
    }

    public class OwnTimeTableViewClassHolder extends RecyclerView.ViewHolder  {
        public TextView teacher;
        public TextView classroom;
        public TextView material;
        public TextView startt;
        public TextView endt;
        public CardView cardView;


        public OwnTimeTableViewClassHolder(@NonNull View itemView) {
            super(itemView);
            this.cardView = (CardView) itemView.findViewById(R.id.class_view);
            this.teacher = (TextView) itemView.findViewById(R.id.teacher_name);
            this.classroom = (TextView) itemView.findViewById(R.id.class_number);
            this.material =(TextView) itemView.findViewById(R.id.class_name);
            this.startt = (TextView) itemView.findViewById(R.id.start_time);
            this.endt = (TextView) itemView.findViewById(R.id.end_time);
        }
    }
}
