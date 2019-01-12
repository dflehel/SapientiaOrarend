package ro.sapientia.ms.sapientiaorarend.Activity

import android.app.Dialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Button
import ro.sapientia.ms.sapientiaorarend.Adapters.SendMessageDeparmentSelectorAdapter
import ro.sapientia.ms.sapientiaorarend.R
import java.util.ArrayList

class activity_send_message : AppCompatActivity() {
    private var buttondeparment: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send_message)
        this.buttondeparment = findViewById<Button>(R.id.send_message_screen_deparment_selector)
        this.buttondeparment!!.setOnClickListener {
            var dialog: Dialog = Dialog(this)

            dialog.setContentView(R.layout.search_view)

            var recyclerView: RecyclerView = dialog.findViewById<RecyclerView>(R.id.search_screen_rec)
            var deparmentSelectorAdapter: SendMessageDeparmentSelectorAdapter = SendMessageDeparmentSelectorAdapter(dialog.context, dialog, this.buttondeparment)
            recyclerView.adapter = deparmentSelectorAdapter
            recyclerView.layoutManager = LinearLayoutManager(dialog.context)

            dialog.show()
        }
    }
}
