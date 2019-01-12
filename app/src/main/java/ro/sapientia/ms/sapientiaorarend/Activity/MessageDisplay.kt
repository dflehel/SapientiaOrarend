package ro.sapientia.ms.sapientiaorarend.Activity

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import ro.sapientia.ms.sapientiaorarend.Adapters.MessageAdapter
import ro.sapientia.ms.sapientiaorarend.R
import ro.sapientia.ms.sapientiaorarend.Util.Databuilder
import ro.sapientia.ms.sapientiaorarend.Util.Settings

class MessageDisplay : AppCompatActivity() {

    private var recyclerViewmessage:RecyclerView?=null
    private var databuilder:Databuilder?= null
    private var adapter:MessageAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message_display)
        this.adapter = MessageAdapter()
        databuilder = Databuilder(adapter,this)
        this.recyclerViewmessage = findViewById<RecyclerView>(R.id.message_display_rec)
        this.recyclerViewmessage!!.adapter = this.adapter
        this.recyclerViewmessage!!.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val intentFilter: IntentFilter? = IntentFilter("mess")
        val revicer: BroadcastReceiver? = object : BroadcastReceiver(){
            override fun onReceive(context: Context?, intent: Intent?) {
                Databuilder(adapter,context)
            }

        }
        this.registerReceiver( revicer,intentFilter)
    }
}
