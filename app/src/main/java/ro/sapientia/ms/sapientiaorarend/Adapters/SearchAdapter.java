package ro.sapientia.ms.sapientiaorarend.Adapters;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import ro.sapientia.ms.sapientiaorarend.R;
import ro.sapientia.ms.sapientiaorarend.Util.ClassPathBuilder;
import ro.sapientia.ms.sapientiaorarend.Util.Databuilder;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchAdapterViewHolder> {

    private ArrayList<String> searchitem = new ArrayList<>();
    private Context con;
    private Databuilder data;
    private Dialog dialog;
    private TextView departmentview;


    public SearchAdapter(ArrayList<String> searchitem) {
        this.searchitem = searchitem;
    }

    public SearchAdapter(Context c, Dialog d, TextView textView) {
        for (String deparment : ClassPathBuilder.classPath.keySet()) {
            for (String year : ClassPathBuilder.classPath.get(deparment).keySet()) {
                for (String group : ClassPathBuilder.classPath.get(deparment).get(year)) {
                    this.searchitem.add(new String(deparment + " " + year + " " + group));
                }
            }
        }
        this.dialog = d;
        this.con = c;
        this.departmentview = textView;
    }

    public SearchAdapter(ClassPathBuilder classPathBuilder) {
        for (String deparment : classPathBuilder.getClassPath().keySet()) {
            for (String year : classPathBuilder.getClassPath().get(deparment).keySet()) {
                for (String group : classPathBuilder.getClassPath().get(deparment).get(year)) {
                    this.searchitem.add(new String(deparment + " " + year + " " + group));
                }
            }
        }

    }

    public Databuilder getData() {
        return data;
    }

    public void setData(Databuilder data) {
        this.data = data;
    }

    public Dialog getDialog() {
        return dialog;
    }

    public void setDialog(Dialog dialog) {
        this.dialog = dialog;
    }

    @NonNull
    @Override
    public SearchAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(con).inflate(R.layout.search_item_view, viewGroup, false);
        return new SearchAdapterViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapterViewHolder searchAdapterViewHolder, int i) {
        searchAdapterViewHolder.text.setText(this.searchitem.get(i).toString());
        searchAdapterViewHolder.pos = i;
    }

    @Override
    public int getItemCount() {
        return this.searchitem.size();
    }

    public class SearchAdapterViewHolder extends RecyclerView.ViewHolder {

        public TextView text;
        public int pos;


        public SearchAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            this.text = (TextView) itemView.findViewById(R.id.search_screen_item_text);
            this.text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SearchAdapter.this.data.shearchfortimetable(SearchAdapter.this.searchitem.get(SearchAdapterViewHolder.this.pos));
                    // SearchAdapter.this.departmentview.setText(SearchAdapter.this.searchitem.get(SearchAdapterViewHolder.this.pos));
                    SearchAdapter.this.dialog.dismiss();
                }
            });
        }


    }

}
