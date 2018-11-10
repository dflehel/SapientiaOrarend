package ro.sapientia.ms.sapientiaorarend

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main_screen.*
import ro.sapientia.ms.sapientiaorarend.Adapters.GeneralTimeTableAdapter
import ro.sapientia.ms.sapientiaorarend.models.Classes
import java.util.ArrayList

class MainScreen : AppCompatActivity() {

    private var drawerLayout: DrawerLayout? = null
    private var actionBarDrawerToggle: ActionBarDrawerToggle? = null
    private var mAuth: FirebaseAuth? = null
    private var generalTimeTable:BlankFragment?=null
    private var ownTimeTable:OwnTimeTable?=null
    private var profil:Profil?=null
    private var databasereferenc: DatabaseReference?=null
    private var classes:ArrayList<Classes>?=ArrayList<Classes>()

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_general_time_table-> {
                message.setText(R.string.title_home)
                if (this.generalTimeTable == null) {
                    this.generalTimeTable = BlankFragment.newInstance("fdsf", "fdfdsfds",GeneralTimeTableAdapter())
                }
                openFragment(this.generalTimeTable!!)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_own_time_table -> {
                message.setText(R.string.title_dashboard)
                if (this.ownTimeTable ==null) {
                    this.ownTimeTable = OwnTimeTable.newInstance()
                }
                openFragment(this.ownTimeTable!!)
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
        this.mAuth = FirebaseAuth.getInstance()
        super.onCreate(savedInstanceState)
        if (this.mAuth!!.currentUser == null) {
            setContentView(R.layout.activity_main_screen_guest)
        } else {
            setContentView(R.layout.activity_main_screen)
        }
        this.databasereferenc = FirebaseDatabase.getInstance().reference.child("/orarendek/szamitastechnika/4")
        var g:GeneralTimeTableAdapter = GeneralTimeTableAdapter()
        val GeneralTimtablefragment  = BlankFragment.newInstance("dfsdfsfd","dfsfdsfds",g)
        openFragment(GeneralTimtablefragment)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        this.drawerLayout = findViewById<DrawerLayout>(R.id.cont)
        this.actionBarDrawerToggle = ActionBarDrawerToggle(this, this.drawerLayout, R.string.open, R.string.close)
        this.drawerLayout!!.addDrawerListener(this.actionBarDrawerToggle!!)
        this.actionBarDrawerToggle!!.syncState()
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        var  d:Databuilder? =  Databuilder(GeneralTimtablefragment);
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (this.actionBarDrawerToggle!!.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
