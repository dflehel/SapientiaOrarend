package ro.sapientia.ms.sapientiaorarend

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main_screen.*
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.fragment_own_time_table.*
import kotlinx.android.synthetic.main.own_time_table_item.*
import ro.sapientia.ms.sapientiaorarend.Adapters.OnwTimeTableAdapter

class MainScreen : AppCompatActivity() {


    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_general_time_table-> {
                message.setText(R.string.title_home)
                val GeneralTimtablefragment  = BlankFragment.newInstance("fdsf","fdfdsfds")
                openFragment(GeneralTimtablefragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_own_time_table -> {
                message.setText(R.string.title_dashboard)
                val OwnTimeTablefragment = OwnTimeTable.newInstance()
                openFragment(OwnTimeTablefragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                message.setText(R.string.title_notifications)
                val Profilfragment = Profil.newIstance()

                openFragment(Profilfragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun openFragment(fragment : Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.con, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)
        val GeneralTimtablefragment  = BlankFragment.newInstance("dfsdfsfd","dfsfdsfds")
        openFragment(GeneralTimtablefragment)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }
}
