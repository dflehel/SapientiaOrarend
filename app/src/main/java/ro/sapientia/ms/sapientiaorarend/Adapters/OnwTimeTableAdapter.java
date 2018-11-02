package ro.sapientia.ms.sapientiaorarend.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import ro.sapientia.ms.sapientiaorarend.Contans.AdapterContans;

public class OnwTimeTableAdapter extends RecyclerView.Adapter<OnwTimeTableAdapter.OwnTimeTableViewHolder> {
    @NonNull



    @Override
    public OwnTimeTableViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull OwnTimeTableViewHolder ownTimeTableViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return AdapterContans.days.length;
    }

    public class OwnTimeTableViewHolder extends RecyclerView.ViewHolder {
        public OwnTimeTableViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
