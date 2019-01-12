package ro.sapientia.ms.sapientiaorarend.Fragments

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import ro.sapientia.ms.sapientiaorarend.R
import ro.sapientia.ms.sapientiaorarend.Util.RealPathUtil
import ro.sapientia.ms.sapientiaorarend.Util.Settings
import java.io.File
import android.R.attr.path
import android.R.attr.bitmap
import android.R.attr.data
import android.provider.MediaStore
import android.R.attr.data
import com.bumptech.glide.Glide


val RESULT_LOAD_IMAGE = 1
class Profil : Fragment() {

    private var email: TextView? = null

    private var name: TextView? = null

    private var phone: TextView? = null

    private var deparment: TextView? = null

    private var group_year: TextView? = null

    private var button: Button? = null


    private var mAuth: FirebaseAuth? = null


    private var imageView: ImageView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the gen_time_table_item for this fragment
        this.mAuth = FirebaseAuth.getInstance()
        val root = inflater.inflate(R.layout.fragment_profil, container, false)
        this.email = root.findViewById<TextView>(R.id.profile_screen_database_email_id)
        this.name = root.findViewById<TextView>(R.id.profile_screen_database_name_id)
        this.phone = root.findViewById<TextView>(R.id.profile_screen_database_phone_id)
        this.button = root.findViewById<Button>(R.id.profile_screen_sign_out_button)
        this.deparment = root.findViewById<TextView>(R.id.profile_screen_database_class_id)
        this.group_year = root.findViewById<TextView>(R.id.profile_screen_database_year_id)
        this.imageView = root.findViewById<ImageView>(R.id.prifile_screen_image_view)
        this.email!!.text = Settings.user.email
        this.name!!.text = Settings.user.name
        this.phone!!.text = Settings.user.phonenumber
        this.deparment!!.text = Settings.user.deparment.split("/")[0]
        this.group_year!!.text = Settings.user.deparment.split("/")[1] + " ev " +
                Settings.user.deparment.split("/")[2] + " csoport"
        this.button!!.setOnClickListener {
            this.mAuth!!.signOut()
            Toast.makeText(root.context, "Kijelentkeztél", Toast.LENGTH_LONG).show()
            this.activity!!.finish()
        }
        this.imageView!!.setOnClickListener {
            var intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.setType("image/*")
            startActivityForResult(intent, 0)
        }
        return root
    }


    companion object {
        fun newIstance(): Profil =
            Profil()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && data != null) {
            if (Build.VERSION.SDK_INT < 19) {
                var realPath = RealPathUtil.getRealPathFromURI_API11to18(this.context, data.getData());
            } else {
                var realPath = RealPathUtil.getRealPathFromURI_API19(this.context, data.getData());
            }

            /*

            val selectedImage = data.data
            val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
            val cursor = this.activity!!.getContentResolver().query(
                selectedImage,
                filePathColumn, null, null, null)
            cursor.moveToFirst();
            val columnIndex = cursor.getColumnIndex(filePathColumn[0])
            val picturePath = cursor.getString(columnIndex)
            cursor.close();
            val imageView = this.activity!!.findViewById(R.id.prifile_screen_image_view) as ImageView
            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
             */

            val selectedImageUri = data.data
            val imageView = this.activity!!.findViewById(R.id.prifile_screen_image_view) as ImageView
            Glide.with(this).load(selectedImageUri).into(imageView)
        }
    }
/*
    private fun imageload(uripath: String, realpath: String) {

        var urifrompath = Uri.fromFile(File(realpath));
        var bitmap: Bitmap? = null;
        try {
            bitmap = BitmapFactory.decodeFile(realpath)
        } catch (e: Exception) {
            print(e)
        }
        this.imageView!!.setImageBitmap(bitmap!!)

    }*/
}
