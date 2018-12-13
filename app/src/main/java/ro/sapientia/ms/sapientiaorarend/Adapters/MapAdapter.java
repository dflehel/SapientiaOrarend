package ro.sapientia.ms.sapientiaorarend.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import ro.sapientia.ms.sapientiaorarend.R;
import ro.sapientia.ms.sapientiaorarend.models.Rooms;

import java.util.ArrayList;

public class MapAdapter extends RecyclerView.Adapter<MapAdapter.MyViewHolderAdapter> {

    private ArrayList<Rooms> x = new ArrayList<>();

    public MapAdapter() {
        Rooms r0 = new Rooms("Pince", "Tornaterem\nJapán labor\nRomán kabinet\nKönyvtár\nGépész labor\nFöldrajz labor");
        Rooms r1 = new Rooms("Első emelet", "Terem 114\nTerem 113\nTerem 112\nTerem 111\nTerem 128\nTerem 129\nTerem 130\nTerem 131\nTerem 132\nGépész tanszék\n");
        Rooms r2 = new Rooms("Második emelet", "Terem 230\nTerem 231\nTerem 216\nTerem 217\nTerem 218\nTerem 242\nTerem 243\nTitkárság\nVillamos tanszék");
        Rooms r3 = new Rooms("Harmadik emelet", "Terem 307\nTerem 308\nTerem 309\nTerem 310\nTerem 311\nTerem 312\nTerem 313\nInfo tanszék");
        Rooms r4 = new Rooms("Negyedik emelet", "Terem 408\nTerem 409\nTerem 410\nTerem 411\nTerem 412\nTerem 413\nTerem 414\nTerem 415\nTerem 416");
        this.x.add(r0);
        this.x.add(r1);
        this.x.add(r2);
        this.x.add(r3);
        this.x.add(r4);

    }

    @NonNull
    @Override
    public MapAdapter.MyViewHolderAdapter onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.map_item, viewGroup, false);
        return new MapAdapter.MyViewHolderAdapter(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MapAdapter.MyViewHolderAdapter MyViewHolderAdapter, int i) {
        MyViewHolderAdapter.floortextview.setText(this.x.get(i).getFloor());
        MyViewHolderAdapter.roomtextview.setText(this.x.get(i).getRoom());
    }

    @Override
    public int getItemCount() {
        return this.x.size();
    }

    public class MyViewHolderAdapter extends RecyclerView.ViewHolder {
        public TextView floortextview;
        public TextView roomtextview;

        public MyViewHolderAdapter(@NonNull View itemView) {
            super(itemView);
            this.floortextview = (TextView) itemView.findViewById(R.id.map_screen_floor);
            this.roomtextview = (TextView) itemView.findViewById(R.id.map_screen_room);

        }
    }
}
