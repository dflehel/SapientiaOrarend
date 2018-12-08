package ro.sapientia.ms.sapientiaorarend.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import ro.sapientia.ms.sapientiaorarend.Adapters.GeneralTimeTableAdapter
import ro.sapientia.ms.sapientiaorarend.R


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [GeneralTimeTable.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [GeneralTimeTable.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class GeneralTimeTable : Fragment() {
    // TODO: Rename and change types of parameters


    var rec: RecyclerView?=null
    var adaptar: GeneralTimeTableAdapter? = null
    var deparmentview: TextView?=null
    var departmenttext: String? = null




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the gen_time_table_item for this fragment
        if (this.adaptar == null) {
            this.adaptar = GeneralTimeTableAdapter()
        }
        val root = inflater.inflate(R.layout.fragment_blank, container, false)
        this.rec = root.findViewById<View>(R.id.genral_time_table_rec_view) as RecyclerView
        this.deparmentview = root.findViewById<View>(R.id.gen_time_table_department_text_view) as TextView
        if (this.deparmentview.toString().equals("", ignoreCase = true)) {
            this.deparmentview!!.text = this.departmenttext
        }
        this.rec!!.adapter = this.adaptar
        val mLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        this.rec!!.layoutManager = mLayoutManager
        this.deparmentview!!.text = this.departmenttext
        this.rec!!.setItemAnimator(DefaultItemAnimator())
        return root
    }

    companion object {
        fun newInstance(): GeneralTimeTable =
            GeneralTimeTable()
        fun newInstance(g:GeneralTimeTableAdapter): GeneralTimeTable {
            var generalTimeTable: GeneralTimeTable =
                GeneralTimeTable()
            generalTimeTable.adaptar = g
            return generalTimeTable
        }


    }
}
