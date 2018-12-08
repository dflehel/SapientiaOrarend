package ro.sapientia.ms.sapientiaorarend

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.LayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_own_time_table.*
import ro.sapientia.ms.sapientiaorarend.Adapters.OnwTimeTableAdapter


class OwnTimeTable : Fragment() {

    lateinit var recyclerView: RecyclerView
    var adapter = OnwTimeTableAdapter()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the gen_time_table_item for this fragment
        val root = inflater.inflate(R.layout.fragment_own_time_table, container, false)
        this.recyclerView = root.findViewById<RecyclerView>(R.id.own_time_table_rec)
        this.recyclerView.adapter = this.adapter
        this.recyclerView.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)
        return root
    }




    companion object {
        fun newInstance(): OwnTimeTable =  OwnTimeTable()
    }
}
