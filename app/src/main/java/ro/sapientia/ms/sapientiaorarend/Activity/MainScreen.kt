package ro.sapientia.ms.sapientiaorarend.Activity

import android.app.Dialog
import android.app.ProgressDialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main_screen.*
import me.aflak.libraries.callback.FingerprintDialogCallback
import me.aflak.libraries.dialog.FingerprintDialog
import ro.sapientia.ms.sapientiaorarend.Adapters.GeneralTimeTableAdapter
import ro.sapientia.ms.sapientiaorarend.Adapters.SearchAdapter
import ro.sapientia.ms.sapientiaorarend.Fragments.GeneralTimeTable
import ro.sapientia.ms.sapientiaorarend.Fragments.OwnTimeTable
import ro.sapientia.ms.sapientiaorarend.Fragments.Profil
import ro.sapientia.ms.sapientiaorarend.R
import ro.sapientia.ms.sapientiaorarend.Services.DatabaseListening
import ro.sapientia.ms.sapientiaorarend.Util.Databuilder
import ro.sapientia.ms.sapientiaorarend.Util.Settings
import ro.sapientia.ms.sapientiaorarend.models.Classes
import ro.sapientia.ms.sapientiaorarend.models.User
import java.util.*


/** az alkalmazas fo kepernyoje mely harom fragmentbol all plussz ket menubol*/
class MainScreen : AppCompatActivity() {

    private var drawerLayout: DrawerLayout? = null
    private var actionBarDrawerToggle: ActionBarDrawerToggle? = null
    private var mAuth: FirebaseAuth? = null
    private var generalTimeTable: GeneralTimeTable? = null
    private var ownTimeTable: OwnTimeTable? = null
    private var profil: Profil? = null
    private var databasereferenc: DatabaseReference? = null
    private var drawmenu: NavigationView? = null
    private var classes: ArrayList<Classes>? = ArrayList<Classes>()
    private var data: Databuilder? = null;
    private var context: Context? = this
    private var generalTimeTableAdapter: GeneralTimeTableAdapter? = null
    private var notireciver: BroadcastReceiver? = null
    private var user: User? = null
    private var progressDialog: ProgressDialog? = null

    companion object {
        public var iscreated: Boolean? = false
    }


    /** az also mennu kezelesere szolgalo fuggveny*/
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_general_time_table -> {
                //  message.setText(R.string.title_home)
                if (this.generalTimeTable == null) {
                    this.generalTimeTable = GeneralTimeTable.newInstance(GeneralTimeTableAdapter())
                }
                // this.generalTimeTableAdapter!!.notifyDataSetChanged()

                openFragment(this.generalTimeTable!!)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_own_time_table -> {
                // message.setText(R.string.title_dashboard)
                if (this.ownTimeTable == null) {
                    this.ownTimeTable = OwnTimeTable.newInstance()
                }
                openFragment(this.ownTimeTable!!)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                // message.setText(R.string.title_notifications)
                if (this.profil == null) {
                    this.profil = Profil.newIstance()
                }
                openFragment(this.profil!!)
                return@OnNavigationItemSelectedListener true
            }


        }
        false
    }
    /** oldalso mennu kinyitasara es becsuasara szolgalo fuggveny*/
    private var selector: NavigationView.OnNavigationItemSelectedListener =
        NavigationView.OnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.etkezde -> {
                    var intent2 = Intent(this, DailyMenuScreen::class.java)
                    startActivity(intent2)
                    this.drawerLayout!!.closeDrawer(Gravity.START, false)
                    true
                }
                R.id.terkep -> {
                    var intent2 = Intent(this, MapScreen::class.java)
                    startActivity(intent2)
                    this.drawerLayout!!.closeDrawer(Gravity.START, false)
                    true
                }
                R.id.uzenet -> {
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                        if (FingerprintDialog.isAvailable(this)) {
                            FingerprintDialog.initialize(this)
                                .title("Ellenőrzés")
                                .message("Használja az újlenyomatát a továbblépéshez")
                                .callback(object : FingerprintDialogCallback {
                                    override fun onAuthenticationSucceeded() {
                                        var intent2 = Intent(context, activity_send_message::class.java)
                                        startActivity(intent2)
                                        drawerLayout!!.closeDrawer(Gravity.START, false)
                                    }

                                    override fun onAuthenticationCancel() {
                                        passwordformessagewritiing()
                                    }
                                })
                                .show();
                        } else {
                            passwordformessagewritiing()
                        }
                    } else {
                        passwordformessagewritiing()
                    }
                    true
                }
                R.id.bealitasok -> {
                    var intent = Intent(this, SettingsScreen::class.java)
                    startActivity(intent)
                    this.drawerLayout!!.closeDrawer(Gravity.START, false)
                    true
                }
                R.id.uzenetek -> {
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                        if (FingerprintDialog.isAvailable(this)) {
                            FingerprintDialog.initialize(this)
                                .title("Ellenőrzés")
                                .message("Használja az újlenyomatát a továbblépéshez")
                                .callback(object : FingerprintDialogCallback {
                                    override fun onAuthenticationSucceeded() {
                                        var intent = Intent(context, MessageDisplay::class.java)
                                        startActivity(intent)
                                        drawerLayout!!.closeDrawer(Gravity.START, false)
                                    }

                                    override fun onAuthenticationCancel() {
                                        passwordformessageview()
                                    }
                                })
                                .show();
                        } else {
                            passwordformessageview()
                        }
                    } else {
                        passwordformessageview()
                    }
                    true
                }
            }
            false
        }

    //uzenetek megtekindese szolgalok ellenorzes
    fun passwordformessageview() {
        this.progressDialog = ProgressDialog(this)
        val dialog: Dialog? = Dialog(this)
        dialog!!.setContentView(R.layout.passwordcheck)
        dialog!!.setTitle("Ellenőrzés")
        var cancelbutton: Button? = dialog.findViewById<Button>(R.id.password_button_cancel)
        var checkbutton: Button? = dialog.findViewById<Button>(R.id.password_check_passwod_button)
        var image: ImageView? = dialog.findViewById<ImageView>(R.id.password_image)
        var pass: EditText? = dialog.findViewById<EditText>(R.id.assword_input)
        var paslabel: TextView? = dialog.findViewById<TextView>(R.id.password_label)
        cancelbutton!!.setOnClickListener {
            dialog.dismiss()
        }
        checkbutton!!.setOnClickListener {
            if(pass!!.text != null && pass!!.text.length >0){
            progressDialog!!.setMessage("Ellenőrzés folyamatban")
            progressDialog!!.show()
            var user: FirebaseUser? = FirebaseAuth.getInstance().currentUser
            var credential: AuthCredential? =
                EmailAuthProvider.getCredential(user!!.email!!, pass!!.text.toString())
            user!!.reauthenticate(credential!!).addOnCompleteListener {
                if (it.isSuccessful) {
                    image!!.setImageResource(R.mipmap.ic_unlock)
                    paslabel!!.setText("Sikeres bejelentkezés")
                    Toast.makeText(context, "Sikeres", Toast.LENGTH_SHORT)
                    paslabel!!.setTextColor(resources.getColor(R.color.slapshcolor))
                    progressDialog!!.dismiss()
                    var intent2 = Intent(context, MessageDisplay::class.java)
                    startActivity(intent2)
                    drawerLayout!!.closeDrawer(Gravity.START, false)
                    dialog.dismiss()
                } else {
                    image!!.setImageResource(R.mipmap.ic_lock_error_round)
                    paslabel!!.setText("Sikertelen bejelentkezés!")
                    Toast.makeText(context, "Sikertelen", Toast.LENGTH_SHORT)
                    paslabel!!.setTextColor(Color.RED)
                    progressDialog!!.dismiss()
                }
            }

            }
            else{
                image!!.setImageResource(R.mipmap.ic_lock_error_round)
                paslabel!!.setText("Nem adott meg jelszót")
                Toast.makeText(context, "Sikertelen", Toast.LENGTH_SHORT)
                paslabel!!.setTextColor(Color.RED)
                progressDialog!!.dismiss()
            }


        }
        dialog!!.show()

    }

    //uzenet irashoz szukseges ellenorzes
    fun passwordformessagewritiing() {
        this.progressDialog = ProgressDialog(this)
        val dialog: Dialog? = Dialog(this)
        dialog!!.setContentView(R.layout.passwordcheck)
        dialog!!.setTitle("Ellenőrzés")
        var cancelbutton: Button? = dialog.findViewById<Button>(R.id.password_button_cancel)
        var checkbutton: Button? = dialog.findViewById<Button>(R.id.password_check_passwod_button)
        var image: ImageView? = dialog.findViewById<ImageView>(R.id.password_image)
        var pass: EditText? = dialog.findViewById<EditText>(R.id.assword_input)
        var paslabel: TextView? = dialog.findViewById<TextView>(R.id.password_label)
        cancelbutton!!.setOnClickListener {
            dialog.dismiss()
        }
        checkbutton!!.setOnClickListener {
            if(pass!!.text != null && pass!!.text.length >0) {
                progressDialog!!.setMessage("Ellenőrzés folyamatban")
                progressDialog!!.show()
                var user: FirebaseUser? = FirebaseAuth.getInstance().currentUser
                var credential: AuthCredential? =
                    EmailAuthProvider.getCredential(user!!.email!!, pass!!.text.toString())
                user!!.reauthenticate(credential!!).addOnCompleteListener {
                    if (it.isSuccessful) {


                        image!!.setImageResource(R.mipmap.ic_unlock)
                        paslabel!!.setText("Sikeres bejelentkezés")
                        Toast.makeText(context, "Sikeres", Toast.LENGTH_SHORT)
                        paslabel!!.setTextColor(resources.getColor(R.color.slapshcolor))
                        var intent2 = Intent(context, activity_send_message::class.java)
                        startActivity(intent2)
                        progressDialog!!.dismiss()
                        drawerLayout!!.closeDrawer(Gravity.START, false)
                        dialog.dismiss()
                    } else {
                        image!!.setImageResource(R.mipmap.ic_lock_error_round)
                        paslabel!!.setText("Sikertelen bejelentkezés")
                        Toast.makeText(context, "Sikertelen", Toast.LENGTH_SHORT)
                        paslabel!!.setTextColor(Color.RED)
                        progressDialog!!.dismiss()
                    }

                }
            }
            else{
                image!!.setImageResource(R.mipmap.ic_lock_error_round)
                paslabel!!.setText("Nem adott meg jelszót")
                Toast.makeText(context, "Sikertelen", Toast.LENGTH_SHORT)
                paslabel!!.setTextColor(Color.RED)
                progressDialog!!.dismiss()
            }

        }
        dialog!!.show()
    }

    /** A frament cserelest oldja meg*/
    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.con, fragment)

        transaction.commit()
    }


    override fun onStart() {
        super.onStart()
        this.progressDialog!!.dismiss()
    }

    override fun onDestroy() {
        super.onDestroy()
        MainScreen.iscreated = false
        //leveszem a recivert mert mar nem kell fogadjon semmit hisz halot az activity
        if (Settings.user != null) {
            unregisterReceiver(this.notireciver!!)
        }
    }


    /**letrehozza a fokepernyot fragmentek nelkul*/
    override fun onCreate(savedInstanceState: Bundle?) {
        this.mAuth = FirebaseAuth.getInstance()
        super.onCreate(savedInstanceState)
        MainScreen.iscreated = true
        /*vendegkent vagy nem ugy van bejelenkezve*/
        if (this.mAuth!!.currentUser == null) {
            setContentView(R.layout.activity_main_screen_guest)


        } else {
            setContentView(R.layout.activity_main_screen)
        }
        this.progressDialog = ProgressDialog(this)
        this.progressDialog!!.setMessage("Betöltés")
        this.progressDialog!!.show()
        bottonnavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        val navigationView: NavigationView = findViewById(R.id.drawernavigation)
        navigationView.setNavigationItemSelectedListener(selector)
        this.ownTimeTable = OwnTimeTable.newInstance()
        this.user = intent.getSerializableExtra("User") as User?
        this.databasereferenc = FirebaseDatabase.getInstance().reference.child("/orarendek/szamitastechnika/4")
        var g: GeneralTimeTableAdapter = GeneralTimeTableAdapter()
        //this.generalTimeTableAdapter!! = g
        val GeneralTimtablefragment = GeneralTimeTable.newInstance(g)
        this.generalTimeTable = GeneralTimtablefragment
        openFragment(this.generalTimeTable!!)
        this.drawerLayout = findViewById<DrawerLayout>(R.id.cont)
        var deparmentext: String? = null
        this.data = Databuilder(this.generalTimeTable!!, this, deparmentext)
        this.actionBarDrawerToggle = ActionBarDrawerToggle(
            this, this.drawerLayout,
            R.string.open,
            R.string.close
        )
        if (Settings.user != null) {
            if (Settings.user!!.timetable == null) {
                Databuilder(ownTimeTable!!, context, Settings.user)
            } else {
                ownTimeTable!!.adapter!!.m = Settings.user.timetable
                ownTimeTable!!.adapter!!.notifyDataSetChanged()
            }
        }
        this.drawerLayout!!.addDrawerListener(this.actionBarDrawerToggle!!)
        this.actionBarDrawerToggle!!.syncState()
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        //ha letezik a felhasznalo elinditon a noti fogadot es a ricivert hogy lehessen egybol updatolni az adatokat
        if (Settings.user != null) {
            var intent = Intent(this, DatabaseListening::class.java)
            startService(intent)

            val intentFilter: IntentFilter? = IntentFilter("time")
            this.notireciver = object : BroadcastReceiver() {
                override fun onReceive(context: Context?, intent: Intent?) {
                    Databuilder(ownTimeTable!!, context, Settings.user)

                }

            }
            this.registerReceiver(this.notireciver!!, intentFilter)
        }
    }

    /** a menu activiti elinditasara szolgal*/
    fun start() {
        var intent2 = Intent(this, DailyMenuScreen::class.java)
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


    /** az oldalso mennu kivalasztasara szolgal*/
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.switchbetweenweeks) {
            if (item.title.toString().equals("Második hét")) {
                item.title = "Első hét"
                item.setIcon(resources.getDrawable(R.drawable.ic_second_week))
                this.generalTimeTable!!.adaptar!!.wichweek = "paroshet"
                this.generalTimeTable!!.adaptar!!.notifyDataSetChanged()
                this.ownTimeTable!!.adapter.wichweek = "paroshet"
                this.ownTimeTable!!.adapter!!.notifyDataSetChanged()

            } else {
                this.generalTimeTable!!.adaptar!!.wichweek = "paratlanhet"
                this.generalTimeTable!!.adaptar!!.notifyDataSetChanged()
                item.title = "Második hét"
                item.setIcon(resources.getDrawable(R.drawable.ic_first_week))
                this.ownTimeTable!!.adapter!!.wichweek = "paratlanhet"
                this.ownTimeTable!!.adapter!!.notifyDataSetChanged()

            }
            return true
        }
        if (item.itemId == R.id.kereses) {
            var dialog: Dialog = Dialog(this)

            dialog.setContentView(R.layout.search_view)

            var recyclerView: RecyclerView = dialog.findViewById<RecyclerView>(R.id.search_screen_rec)
            var searchAdapter: SearchAdapter =
                SearchAdapter(dialog.context, dialog, this.generalTimeTable!!.deparmentview)
            searchAdapter.data = this.data
            //searchAdapter.dialog = dialog
            recyclerView.adapter = searchAdapter
            recyclerView.layoutManager = LinearLayoutManager(dialog.context)

            dialog.show()
            return true
        }

        if (this.actionBarDrawerToggle!!.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
