package ro.sapientia.ms.sapientiaorarend.Activity

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import ro.sapientia.ms.sapientiaorarend.R
import ro.sapientia.ms.sapientiaorarend.Util.Databuilder
import ro.sapientia.ms.sapientiaorarend.Util.Settings
import ro.sapientia.ms.sapientiaorarend.models.DailyMenu

/** az az napi menu megjelenitese*/
class MENU : AppCompatActivity() {


    private var dailyMenu: DailyMenu? = null;
    private var textViewday: TextView? = null
    private var textViewfoods: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        this.textViewday = findViewById<TextView>(R.id.menu_screen_day)
        this.textViewfoods = findViewById<TextView>(R.id.menu_screen_foods)
        this.dailyMenu = DailyMenu(this.textViewday!!, this.textViewfoods!!, this)
        val intentFilter: IntentFilter? = IntentFilter("menu")
        val revicer: BroadcastReceiver? = object : BroadcastReceiver(){
            override fun onReceive(context: Context?, intent: Intent?) {
                dailyMenu = DailyMenu(textViewday!!,textViewfoods!!,context)
            }

        }
        this.registerReceiver( revicer,intentFilter)
    }





}
