package ro.sapientia.ms.sapientiaorarend

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main_screen.*
import ro.sapientia.ms.sapientiaorarend.Adapters.GeneralTimeTableAdapter
import ro.sapientia.ms.sapientiaorarend.models.Classes
import java.util.*

class MainScreen : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var drawerLayout: DrawerLayout? = null
    private var actionBarDrawerToggle: ActionBarDrawerToggle? = null
    private var mAuth: FirebaseAuth? = null
    private var generalTimeTable:BlankFragment?=null
    private var ownTimeTable:OwnTimeTable?=null
    private var profil:Profil?=null
    private var databasereferenc: DatabaseReference?=null
    private var drawmenu: NavigationView? = null
    private var classes:ArrayList<Classes>?=ArrayList<Classes>()

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_general_time_table-> {
                //  message.setText(R.string.title_home)
                if (this.generalTimeTable == null) {
                    this.generalTimeTable = BlankFragment.newInstance("fdsf", "fdfdsfds",GeneralTimeTableAdapter())
                }
                openFragment(this.generalTimeTable!!)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_own_time_table -> {
                // message.setText(R.string.title_dashboard)
                if (this.ownTimeTable ==null) {
                    this.ownTimeTable = OwnTimeTable.newInstance()
                }
                openFragment(this.ownTimeTable!!)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                // message.setText(R.string.title_notifications)
                val Profilfragment = Profil.newIstance()

                openFragment(Profilfragment)
                return@OnNavigationItemSelectedListener true
            }

            R.id.etkezde -> {
                var intent2 = Intent(this, MENU::class.java)
                startActivity(intent2)
                return@OnNavigationItemSelectedListener true
            }

        }
        false
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.etkezde -> Toast.makeText(this, "Clicked item one", Toast.LENGTH_SHORT).show()

        }
        return true
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
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        val navigationView: NavigationView = findViewById(R.id.navigationView)
        navigationView.setNavigationItemSelectedListener(this)
        this.databasereferenc = FirebaseDatabase.getInstance().reference.child("/orarendek/szamitastechnika/4")
        var g:GeneralTimeTableAdapter = GeneralTimeTableAdapter()
        val GeneralTimtablefragment = BlankFragment.newInstance("dfsdfsfd", "dfsfdsfds", g)
        this.generalTimeTable = GeneralTimtablefragment
        openFragment(GeneralTimtablefragment)

        this.drawerLayout = findViewById<DrawerLayout>(R.id.cont)
        this.actionBarDrawerToggle = ActionBarDrawerToggle(this, this.drawerLayout, R.string.open, R.string.close)
        this.drawerLayout!!.addDrawerListener(this.actionBarDrawerToggle!!)
        /*    this.drawerLayout!!.setOnClickListener {
                if(it.id == R.id.etkezde){
                    var intent2 = Intent(this, MENU::class.java)
                    startActivity(intent2)
                }
            }*/

        this.actionBarDrawerToggle!!.syncState()

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        var  d:Databuilder? =  Databuilder(GeneralTimtablefragment);
    }

    public fun start() {
        var intent2 = Intent(this, MENU::class.java)
        startActivity(intent2)
    }

    private fun Context.toast(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }


    override fun onBackPressed() {
        this.finish()
        super.onBackPressed()
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.optionmenu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.kereses) {
            return true
        }
        if (item.itemId == R.id.switchbetweenweeks) {
            if (item.title.toString().equals("Második hét")) {
                item.title = "Első hét"
                this.generalTimeTable!!.adaptar!!.wichweek = "paratlanhet"
                this.generalTimeTable!!.adaptar!!.notifyDataSetChanged()

            } else {
                this.generalTimeTable!!.adaptar!!.wichweek = "paroshet"
                this.generalTimeTable!!.adaptar!!.notifyDataSetChanged()
                item.title = "Második hét"

            }
            return true

        }

        if (this.actionBarDrawerToggle!!.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
