package ro.sapientia.ms.sapientiaorarend.Activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.TextView
import ro.sapientia.ms.sapientiaorarend.R
import com.google.firebase.database.*
import ro.sapientia.ms.sapientiaorarend.Adapters.MapFloorAdapter
import ro.sapientia.ms.sapientiaorarend.models.Floor
import java.util.ArrayList


class Map : AppCompatActivity() {

    private var textViewFloor: TextView? = null
    private var textViewRoom: TextView? = null
    private var recyclerView: RecyclerView? = null
    private var floors:ArrayList<Floor>?=null
    private var adapter : MapFloorAdapter?=null
    private var databaseReference:DatabaseReference?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
     //   val photoView = findViewById<View>(R.id.photo_view) as PhotoView
      //  photoView.setImageResource(R.drawable.splash)
           this.recyclerView = findViewById<RecyclerView>(R.id.map_screen_rec)
            this.floors = ArrayList<Floor>()
           this.databaseReference = FirebaseDatabase.getInstance().getReference("/uri/")
            this.databaseReference!!.addListenerForSingleValueEvent(object :ValueEventListener{
                override fun onCancelled(p0: DatabaseError) {

                }

                override fun onDataChange(p0: DataSnapshot) {
                    for(data in p0.children){
                        var f : Floor?= data.getValue(Floor::class.java)
                        floors!!.add(f!!)
                    }
                    adapter = MapFloorAdapter(floors!!,applicationContext)
                    recyclerView!!.adapter = adapter
                    recyclerView!!.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
                }
            })

    }
}
