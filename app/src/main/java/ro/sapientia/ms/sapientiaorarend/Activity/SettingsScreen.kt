package ro.sapientia.ms.sapientiaorarend.Activity

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.Button
import android.widget.Switch
import android.widget.TimePicker
import ro.sapientia.ms.sapientiaorarend.BroadcastReceiver.AlarmReceiver
import ro.sapientia.ms.sapientiaorarend.R

class SettingsScreen : AppCompatActivity() {


    private lateinit var switch: Switch
    private lateinit var button: Button
    private lateinit var timePicker: TimePicker
    private var alarmMgr: AlarmManager? = null
    private lateinit var alarmIntent: PendingIntent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings_screen)
        this.button = findViewById<Button>(R.id.settinsgscreenmentes)
        alarmMgr = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        var intent =  Intent(this,AlarmReceiver::class.java)
        alarmIntent = PendingIntent.getBroadcast(this,0,intent,0)

        this.alarmMgr!!.setRepeating(AlarmManager.RTC_WAKEUP,System.currentTimeMillis()+AlarmManager.INTERVAL_FIFTEEN_MINUTES,AlarmManager.INTERVAL_HALF_HOUR,this.alarmIntent)
      //  this.alarmMgr!!.setExact(AlarmManager.RTC_WAKEUP,System.currentTimeMillis()+AlarmManager.INTERVAL_FIFTEEN_MINUTES,this.alarmIntent)
      //  alarmMgr?.setInexactRepeating(
        //    AlarmManager.ELAPSED_REALTIME_WAKEUP,
          //  SystemClock.elapsedRealtime() + AlarmManager.INTERVAL_HALF_HOUR,
           // AlarmManager.INTERVAL_HALF_HOUR,
          //  alarmIntent
        //)
        this.timePicker = findViewById<TimePicker>(R.id.idobeoszto)
        this.switch = findViewById<Switch>(R.id.ebresztesonoff)
        this.switch.setOnClickListener(){
            if(switch.isChecked){
                timePicker.visibility = View.VISIBLE
                button.visibility = View.VISIBLE
            }
            else{
                timePicker.visibility = View.INVISIBLE
                button.visibility = View.INVISIBLE
            }
        }
    }
}