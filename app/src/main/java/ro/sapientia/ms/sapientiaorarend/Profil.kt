package ro.sapientia.ms.sapientiaorarend

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import android.os.Build;
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import java.io.File


class Profil : Fragment() {

    private var email: TextView? = null

    private var name: TextView? = null

    private var phone: TextView? = null

    private var button: Button? = null

    private var user: FirebaseUser? = null

    private var mAuth: FirebaseAuth? = null


    private var imageView:ImageView?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        this.mAuth = FirebaseAuth.getInstance()
        this.user = this.mAuth!!.currentUser
        val root = inflater.inflate(R.layout.fragment_profil, container, false)
        this.email = root.findViewById<TextView>(R.id.profile_screen_database_email_id)
        this.name = root.findViewById<TextView>(R.id.profile_screen_database_name_id)
        this.phone = root.findViewById<TextView>(R.id.profile_screen_database_phone_id)
        this.button = root.findViewById<Button>(R.id.profile_screen_sign_out_button)
        this.imageView = root.findViewById<ImageView>(R.id.prifile_screen_image_view)
        this.email!!.text = user!!.email
        this.name!!.text = user!!.displayName
        this.phone!!.text = user!!.phoneNumber
        this.button!!.setOnClickListener {
            this.mAuth!!.signOut()
            Toast.makeText(root.context, "Kijelentkeztel", Toast.LENGTH_LONG).show()
            this.activity!!.finish()
        }
        this.imageView!!.setOnClickListener {
                var intent = Intent(Intent.ACTION_GET_CONTENT)
                intent.setType("image/*")
                startActivityForResult(intent,0)
        }
        return root
    }


    companion object {
            fun newIstance(): Profil = Profil()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && data !=null){

            if(Build.VERSION.SDK_INT < 19){
                var realPath = RealPathUtil.getRealPathFromURI_API11to18(this.context, data.getData());
            }
            else{
                var realPath = RealPathUtil.getRealPathFromURI_API19(this.context, data.getData());
            }
        }
    }

    private fun imageload(uripath:String,realpath:String){

        var urifrompath = Uri.fromFile( File(realpath));
        var bitmap:Bitmap? = null;
        try{
            bitmap = BitmapFactory.decodeFile(realpath)

        }
        catch (e:Exception){
            print(e)
        }
        this.imageView!!.setImageBitmap(bitmap!!)

    }
}
