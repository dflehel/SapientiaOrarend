package ro.sapientia.ms.sapientiaorarend

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import ro.sapientia.ms.sapientiaorarend.models.DailyMenu


class MENU : AppCompatActivity() {

    private  var dailyMenu:DailyMenu?=null;
    private var textViewday:TextView?=null
    private var textViewfoods:TextView?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        this.textViewday = findViewById<TextView>(R.id.menu_screen_day)
        this.textViewfoods = findViewById<TextView>(R.id.menu_screen_foods)
        this.dailyMenu = DailyMenu(this.textViewday!!,this.textViewfoods!!,this)
    }
}
