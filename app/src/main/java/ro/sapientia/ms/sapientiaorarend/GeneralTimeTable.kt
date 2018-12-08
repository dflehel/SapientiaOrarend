package ro.sapientia.ms.sapientiaorarend

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


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



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the gen_time_table_item for this fragment
        return inflater.inflate(R.layout.fragment_general_time_table, container, false)
    }

    companion object {
        fun newInstance():GeneralTimeTable= GeneralTimeTable()
    }
}
