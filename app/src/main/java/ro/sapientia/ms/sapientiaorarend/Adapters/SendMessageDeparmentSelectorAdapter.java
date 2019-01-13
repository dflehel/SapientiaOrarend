package ro.sapientia.ms.sapientiaorarend.Adapters;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import ro.sapientia.ms.sapientiaorarend.R;
import ro.sapientia.ms.sapientiaorarend.Util.Databuilder;
import ro.sapientia.ms.sapientiaorarend.Util.ClassPathBuilder;

import java.util.ArrayList;

public class SendMessageDeparmentSelectorAdapter extends RecyclerView.Adapter<SendMessageDeparmentSelectorAdapter.DepartmentSelectorAdapterViewHolder> {

    private ArrayList<String> searchitem = new ArrayList<>() ;
    private ArrayList<Integer> colors = new ArrayList<>();
    private Button button;
    private Context con;
    private Databuilder data;
    private Dialog dialog;
    private ArrayList<String> revivers = new ArrayList<>();

    public Databuilder getData() {
        return data;
    }

    public Dialog getDialog() {
        return dialog;
    }

    public void setDialog(Dialog dialog) {
        this.dialog = dialog;
    }

    public void setData(Databuilder data) {
        this.data = data;
    }

    public SendMessageDeparmentSelectorAdapter(Context c, Dialog d, Button button,ArrayList<String> recivers){
        for(String deparment:ClassPathBuilder.classPath.keySet()){
            for(String year:ClassPathBuilder.classPath.get(deparment).keySet()){
                for(String group:ClassPathBuilder.classPath.get(deparment).get(year)){
                    this.searchitem.add(new String(deparment+" "+year+" "+group));
                    this.colors.add(Color.BLACK);
                }
            }
        }
        this.button = button;
        this.dialog = d;
        this.con =c;
        this.revivers = recivers;
    }

    @NonNull
    @Override
    public DepartmentSelectorAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(con).inflate(R.layout.search_item_view,viewGroup,false);
        return new DepartmentSelectorAdapterViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DepartmentSelectorAdapterViewHolder departmentSelectorAdapterViewHolder, int i) {
        departmentSelectorAdapterViewHolder.text.setText(this.searchitem.get(i).toString());
        departmentSelectorAdapterViewHolder.color = this.colors.get(i);
        departmentSelectorAdapterViewHolder.text.setBackgroundColor(this.colors.get(i));
        departmentSelectorAdapterViewHolder.pos = i;
    }

    @Override
    public int getItemCount() {
        return this.searchitem.size();
    }

    public class DepartmentSelectorAdapterViewHolder extends RecyclerView.ViewHolder{

        public TextView text;
        public int pos;
        public int color = Color.BLACK;

        public DepartmentSelectorAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            this.text = (TextView) itemView.findViewById(R.id.search_screen_item_text);
            this.text.setBackgroundColor(this.color);
            this.text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (DepartmentSelectorAdapterViewHolder.this.color == Color.BLACK) {
                        SendMessageDeparmentSelectorAdapter.this.revivers.add(SendMessageDeparmentSelectorAdapter.this.searchitem.get(DepartmentSelectorAdapterViewHolder.this.pos));
                            DepartmentSelectorAdapterViewHolder.this.color = Color.RED;
                            DepartmentSelectorAdapterViewHolder.this.text.setBackgroundColor(DepartmentSelectorAdapterViewHolder.this.color);
                            SendMessageDeparmentSelectorAdapter.this.colors.set(DepartmentSelectorAdapterViewHolder.this.pos,Color.RED);

                    }
                    else {
                        if (DepartmentSelectorAdapterViewHolder.this.color == Color.RED) {
                            DepartmentSelectorAdapterViewHolder.this.color = Color.BLACK;
                            DepartmentSelectorAdapterViewHolder.this.text.setBackgroundColor(DepartmentSelectorAdapterViewHolder.this.color);
                            SendMessageDeparmentSelectorAdapter.this.revivers.remove(SendMessageDeparmentSelectorAdapter.this.searchitem.get(DepartmentSelectorAdapterViewHolder.this.pos));
                            SendMessageDeparmentSelectorAdapter.this.colors.set(DepartmentSelectorAdapterViewHolder.this.pos,Color.BLACK);
                        }
                    }
                }
            });
        }
    }
}