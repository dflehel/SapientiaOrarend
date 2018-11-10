package ro.sapientia.ms.sapientiaorarend;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import ro.sapientia.ms.sapientiaorarend.Adapters.GeneralTimeTableAdapter;
import ro.sapientia.ms.sapientiaorarend.Adapters.OnwTimeTableAdapter;


public class BlankFragment extends Fragment {

    public RecyclerView rec;
    public GeneralTimeTableAdapter adaptar;



    public BlankFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BlankFragment newInstance(String param1, String param2,GeneralTimeTableAdapter g) {
        BlankFragment fragment = new BlankFragment();
        fragment.adaptar = g;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.adaptar= new GeneralTimeTableAdapter();
        View root = inflater.inflate(R.layout.fragment_blank, container, false);
        this.rec = (RecyclerView)root.findViewById(R.id.own_time_table_rec);
        this.rec.setAdapter(new OnwTimeTableAdapter());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        this.rec.setLayoutManager(mLayoutManager);
        this.rec.setItemAnimator(new DefaultItemAnimator() );
        return root;
    }


}
