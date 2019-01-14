package ro.sapientia.ms.sapientiaorarend.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;
import ro.sapientia.ms.sapientiaorarend.R;
import ro.sapientia.ms.sapientiaorarend.models.Floor;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MapFloorAdapter extends RecyclerView.Adapter<MapFloorAdapter.MyViewHolderAdapter> {

    private ArrayList<Floor> floors;
    private Context c;


    public MapFloorAdapter(ArrayList<Floor> floors, Context c) {
        this.floors = floors;
        this.c = c;
    }

    public ArrayList<Floor> getFloors() {
        return floors;
    }

    public void setFloors(ArrayList<Floor> floors) {
        this.floors = floors;
    }

    public Context getC() {
        return c;
    }

    public void setC(Context c) {
        this.c = c;
    }

    @NonNull
    @Override
    public MyViewHolderAdapter onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.map_items, viewGroup, false);
        return new MapFloorAdapter.MyViewHolderAdapter(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderAdapter myViewHolderAdapter, int i) {
        myViewHolderAdapter.text.setText(this.floors.get(i).getName());
        try {
            Glide.with(c).load(new URL(this.floors.get(i).getUrl())).into(myViewHolderAdapter.photoView);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return this.floors.size();
    }


    public class MyViewHolderAdapter extends RecyclerView.ViewHolder {

        public TextView text;
        public PhotoView photoView;

        public MyViewHolderAdapter(@NonNull View itemView) {
            super(itemView);
            this.text = itemView.findViewById(R.id.map_item_floor_text_view);
            this.photoView = itemView.findViewById(R.id.map_items_photo_view);
        }
    }
}
