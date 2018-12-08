package ro.sapientia.ms.sapientiaorarend.Activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.TextView
import ro.sapientia.ms.sapientiaorarend.Adapters.MapAdapter
import ro.sapientia.ms.sapientiaorarend.R

class Map : AppCompatActivity() {

    private var textViewFloor: TextView? = null
    private var textViewRoom: TextView? = null
    private var recyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        this.recyclerView = findViewById<RecyclerView>(R.id.map_rec_view)
        var gh = MapAdapter()
        this.recyclerView!!.adapter = gh
        this.recyclerView!!.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)


    }
}
