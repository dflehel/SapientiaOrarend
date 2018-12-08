package ro.sapientia.ms.sapientiaorarend.Adapters;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import ro.sapientia.ms.sapientiaorarend.Util.Databuilder;
import ro.sapientia.ms.sapientiaorarend.R;
import ro.sapientia.ms.sapientiaorarend.Util.ClassPathBuilder;

import java.util.ArrayList;


/** a regisztralashoz szukseg szak megadasa*/
public class DeparmentSelectorAdapter extends RecyclerView.Adapter<DeparmentSelectorAdapter.DepartmentSelectorAdapterViewHolder> {

    private ArrayList<String> searchitem = new ArrayList<>() ;
    private Button button;
    private Context con;
    private Databuilder data;
    private Dialog dialog;

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

    /** a utilban levo classpath builder segitsegevel kiveszem az adatokat is bealitom az itteni adattagoknak*/
    public DeparmentSelectorAdapter(Context c,Dialog d, Button button){
        for(String deparment:ClassPathBuilder.classPath.keySet()){
            for(String year:ClassPathBuilder.classPath.get(deparment).keySet()){
                for(String group:ClassPathBuilder.classPath.get(deparment).get(year)){
                    this.searchitem.add(new String(deparment+" "+year+" "+group));
                }
            }
        }
        this.button = button;
        this.dialog = d;
        this.con =c;
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
            departmentSelectorAdapterViewHolder.pos = i;
    }

    @Override
    public int getItemCount() {
        return this.searchitem.size();
    }

    public class DepartmentSelectorAdapterViewHolder extends RecyclerView.ViewHolder{

        public TextView text;
        public int pos;

        public DepartmentSelectorAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            this.text = (TextView) itemView.findViewById(R.id.search_screen_item_text);
            this.text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /* a regisztralasnal levo buttonra felteszem a kivalasztot szakot igy konnyen elerheto kintrol*/
                    DeparmentSelectorAdapter.this.button.setText(DeparmentSelectorAdapter.this.searchitem.get(DepartmentSelectorAdapterViewHolder.this.pos));
                    DeparmentSelectorAdapter.this.dialog.dismiss();
                }
            });
        }
    }
}
