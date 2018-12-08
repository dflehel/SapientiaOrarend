package ro.sapientia.ms.sapientiaorarend

import android.os.Bundle
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private var drawerLayout: DrawerLayout? = null
    private var actionBarDrawerToggle: ActionBarDrawerToggle? = null
    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        this.mAuth = FirebaseAuth.getInstance()
        super.onCreate(savedInstanceState)
        if (this.mAuth!!.currentUser == null) {
            setContentView(R.layout.activity_main_screen_guest)
        } else {
            setContentView(R.layout.activity_main)
        }
        this.drawerLayout = findViewById<DrawerLayout>(R.id.cont)
        this.actionBarDrawerToggle = ActionBarDrawerToggle(this, this.drawerLayout, R.string.open, R.string.close)
        this.drawerLayout!!.addDrawerListener(this.actionBarDrawerToggle!!)
        this.actionBarDrawerToggle!!.syncState()
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }
}