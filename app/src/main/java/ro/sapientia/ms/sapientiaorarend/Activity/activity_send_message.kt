package ro.sapientia.ms.sapientiaorarend.Activity

import android.app.Dialog
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.*
import ro.sapientia.ms.sapientiaorarend.Adapters.SendMessageDeparmentSelectorAdapter
import ro.sapientia.ms.sapientiaorarend.R
import ro.sapientia.ms.sapientiaorarend.Util.Settings
import ro.sapientia.ms.sapientiaorarend.models.SendMessage
import java.util.*

/**uzenetkuldes kepernyo*/
class activity_send_message : AppCompatActivity() {


    private var buttondeparment: Button? = null
    private var recivers: TreeSet<String>? = null
    private var buttoncancel: Button? = null
    private var buttonok: Button? = null
    private var mlayout: LinearLayout? = null
    private var buttonsend: Button? = null
    private var content: EditText? = null
    private var deletebutton: ImageButton? = null

    //bealitom a valtozokat
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send_message)
        this.recivers = TreeSet<String>()
        this.content = findViewById<EditText>(R.id.send_message_screen_edittext)
        this.buttonsend = findViewById<Button>(R.id.send_message_screen_send)
        this.deletebutton = findViewById<ImageButton>(R.id.send_message_delete_button)
        this.deletebutton!!.setOnClickListener {
            recivers!!.clear()
            mlayout!!.removeAllViews()
            mlayout!!.refreshDrawableState()
            content!!.setText("")
            content!!.refreshDrawableState()
        }
        this.buttonsend!!.setOnClickListener {
            var sendMessage: SendMessage =
                SendMessage(
                    Settings.user.name,
                    content!!.text.toString(),
                    recivers!!,
                    System.currentTimeMillis()
                )
            sendMessage.sendingmesage()
            recivers!!.clear()
            mlayout!!.removeAllViews()
            mlayout!!.refreshDrawableState()
            content!!.setText("")
            content!!.refreshDrawableState()
        }
        this.mlayout = findViewById<LinearLayout>(R.id.send_activity_scrool_view_layout)
        //bealittom hogy lehessen szakokat kivalasztani
        this.buttondeparment = findViewById<Button>(R.id.send_message_screen_deparment_selector)
        this.buttondeparment!!.setOnClickListener {

            recivers!!.clear()
            var dialog: Dialog = Dialog(this)
            dialog.setCancelable(true)
            dialog.setTitle("Vállasz csoportokat")
            dialog.setCanceledOnTouchOutside(true)

            dialog.setContentView(R.layout.send_view)
            buttoncancel = dialog.findViewById<Button>(R.id.send_view_cancel_button)
            buttoncancel!!.setOnClickListener {
                recivers!!.clear()
                dialog.dismiss()
            }
            buttonok = dialog.findViewById<Button>(R.id.send_view_ok_button)
            buttonok!!.setOnClickListener {
                for (i in recivers!!.iterator()) {
                    var text: TextView? = TextView(applicationContext)
                    text!!.setText(i.toString())
                    // text!!.setTextSize(10.00F)
                    text!!.setTextAppearance(applicationContext, android.R.style.TextAppearance_DeviceDefault_Medium)
                    text.setTextColor(Color.BLACK)
                    mlayout!!.addView(text!!)
                }
                dialog.dismiss()
            }

            var recyclerView: RecyclerView = dialog.findViewById<RecyclerView>(R.id.search_screen_rec)
            var deparmentSelectorAdapter: SendMessageDeparmentSelectorAdapter =
                SendMessageDeparmentSelectorAdapter(dialog.context, dialog, this.buttondeparment, recivers)
            recyclerView.adapter = deparmentSelectorAdapter
            recyclerView.layoutManager = LinearLayoutManager(dialog.context)

            dialog.show()
        }
    }
}
