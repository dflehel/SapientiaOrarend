package ro.sapientia.ms.sapientiaorarend.Adapters;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import ro.sapientia.ms.sapientiaorarend.R;
import ro.sapientia.ms.sapientiaorarend.models.Classes;

import java.util.ArrayList;
import java.util.Objects;

public class GeneralTimeTableClassAdapter  extends  RecyclerView.Adapter<GeneralTimeTableClassAdapter.OwnTimeTableViewClassHolder> {

    private ArrayList<Classes> c = new ArrayList<>();
    public int valto;
    public ArrayList<Classes> getC() {
        return c;
    }

    public void setC(ArrayList<Classes> c) {
        this.c = c;
    }

    public GeneralTimeTableClassAdapter(ArrayList<Classes> c) {
        this.c = c;
    }

    @NonNull
    @Override
    public GeneralTimeTableClassAdapter.OwnTimeTableViewClassHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.own_time_table_class_item,viewGroup,false);
        return new GeneralTimeTableClassAdapter.OwnTimeTableViewClassHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull GeneralTimeTableClassAdapter.OwnTimeTableViewClassHolder ownTimeTableViewClassHolder, int i) {
        ownTimeTableViewClassHolder.teacher.setText(this.c.get(i).getTeacher());
        ownTimeTableViewClassHolder.material.setText(this.c.get(i).getMaterial());
        ownTimeTableViewClassHolder.classroom.setText(this.c.get(i).getClassroom());
        ownTimeTableViewClassHolder.startt.setText(this.c.get(i).getStart().toString());
        ownTimeTableViewClassHolder.endt.setText(this.c.get(i).getEnd().toString());

        String tr = this.c.get(i).getTeacher();
        if(tr.equals("Dr. Szentes Erzsébet"))
        {
           valto=1;
        } else if (tr.equals("Dr. Fazakas Csaba")) {
            valto=2;
        } else if (tr.equals("Dr. Pásztor Judit")) {
            valto=3;
        }
    }

    @Override
    public int getItemCount() {
        return this.c.size();
    }

    public class OwnTimeTableViewClassHolder  extends RecyclerView.ViewHolder  {
        public TextView teacher;
        public TextView classroom;
        public TextView material;
        public TextView startt;
        public TextView endt;
        public CardView testview;


        @SuppressLint("ResourceAsColor")
        public OwnTimeTableViewClassHolder(@NonNull View itemView) {
            super(itemView);
            this.testview = (CardView) itemView.findViewById(R.id.timetableview);
            if(valto == 1){
            testview.setCardBackgroundColor(Integer.parseInt("-769321"));}
           /* testview.setCardBackgroundColor(-6054003)
            testview.setCardBackgroundColor(-10306381)
            testview.setCardBackgroundColor(-11178244)
            testview.setCardBackgroundColor(-6202881)
            testview.setCardBackgroundColor(-11272964)
            testview.setCardBackgroundColor(-5903844)
            testview.setCardBackgroundColor(-59758)
            testview.setCardBackgroundColor(-10218)
            testview.setCardBackgroundColor(-862800)*/


            else if (valto == 2){
                testview.setCardBackgroundColor(Integer.parseInt("-6054003"));}
            else if (valto == 3){
                testview.setCardBackgroundColor(Integer.parseInt("-10306381"));}
            this.teacher = (TextView) itemView.findViewById(R.id.teacher_name);
            this.classroom = (TextView) itemView.findViewById(R.id.class_number);
            this.material =(TextView) itemView.findViewById(R.id.class_name);
            this.startt = (TextView) itemView.findViewById(R.id.start_time);
            this.endt = (TextView) itemView.findViewById(R.id.end_time);
            }
        }
    }

