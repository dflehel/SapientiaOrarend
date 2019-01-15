package ro.sapientia.ms.sapientiaorarend.Fragments


//import com.google.firebase.storage.FirebaseStorage
//import com.google.firebase.storage.StorageReference
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.storage.FirebaseStorage
import ro.sapientia.ms.sapientiaorarend.R
import ro.sapientia.ms.sapientiaorarend.Util.RealPathUtil
import ro.sapientia.ms.sapientiaorarend.Util.Settings


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

    // private var selectedImageUri: Uri? = null
    private var pImageURI: Uri? = null
    private val PICK_IMAGE_REQUEST = 1

    // private var storageRef: StorageReference? = null
    //  private var fileRef: StorageReference? = null
    private var databaseRef: DatabaseReference? = null
    // private var bitmap: Bitmap? = null

    private var existingImage: Boolean = false
    private val filename = Settings.user.email + "-" + Settings.user.name
    private val storageRef2 = FirebaseStorage.getInstance().getReference("profilkepek/$filename")
    private var pImageURI2: Uri? = null

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

        //this.storageRef = FirebaseStorage.getInstance().getReference("profilkep")
        //this.databaseRef = FirebaseDatabase.getInstance().getReference("profilkep")

        this.button!!.setOnClickListener {
            this.mAuth!!.signOut()
            Toast.makeText(root.context, "Kijelentkeztél", Toast.LENGTH_LONG).show()
            this.activity!!.finish()
        }
        this.imageView!!.setOnClickListener {
            Log.d("UploadLog", "ImageSelector")
            var intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.setType("image/*")
            startActivityForResult(intent, PICK_IMAGE_REQUEST)
        }

        if (existingImage) {
            Log.d("UploadLog", "Load image from uri to avatar")
            Glide.with(this).load(pImageURI).into(imageView!!)
        } else { storageRef2.downloadUrl.addOnFailureListener {
                // Handle unsuccessful uploads
                Log.d("UploadLog", "no url here")
            }.addOnSuccessListener {
                // taskSnapshot.metadata contains file metadata such as size, content-type, etc.
                // ...
                Log.d("UploadLog", "Load image from URL to avatar")
                Glide.with(this).load(it).into(imageView!!)
            }
        }

        return root
    }


    companion object {
        fun newIstance(): Profil =
            Profil()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.data != null) {

            if (Build.VERSION.SDK_INT < 19) {
                var realPath = RealPathUtil.getRealPathFromURI_API11to18(this.context, data.data);
            } else {
                var realPath = RealPathUtil.getRealPathFromURI_API19(this.context, data.data);
            }
            Log.d("UploadLog", "ImageSelectorSuccess")
            //val cR: ContentResolver? = null
            pImageURI = data.data
            val imageView = this.activity!!.findViewById(R.id.prifile_screen_image_view) as ImageView
            Glide.with(this).load(pImageURI).into(imageView)
            Log.d("UploadLog", "URI: ${pImageURI.toString()}")
            existingImage = true
              storageRef2.putFile(pImageURI!!)
                  .addOnFailureListener {
                      // Handle unsuccessful uploads
                      Log.d("UploadLog", "Upload Failed")
                  }.addOnSuccessListener {
                      // taskSnapshot.metadata contains file metadata such as size, content-type, etc.
                      // ...
                      Log.d("UploadLog", "Upload Success")
                     /* storageRef2.downloadUrl.addOnFailureListener {
                          // Handle unsuccessful uploads
                          Log.d("UploadLog", "URL FAILED Failed")
                      }.addOnSuccessListener {
                          // taskSnapshot.metadata contains file metadata such as size, content-type, etc.
                          // ...
                          Log.d("UploadLog", "URL success: $storageRef2.downloadUrl")
                          }*/
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

            //val selectedImageUri = data.data
            /*pImageURI = data.data
            val imageView = this.activity!!.findViewById(R.id.prifile_screen_image_view) as ImageView
            Glide.with(this).load(pImageURI).into(imageView)*/

//            fileRef = storageRef!!.child(System.currentTimeMillis().toString() +  "." + getFileExtension(pImageURI))
            /*fileRef!!.putFile(this.pImageURI!!)
            .addOnFailureListener {
            // Handle unsuccessful uploads
            Log.d("kep2", "nincsfeltoltes")
            }.addOnSuccessListener {
            // taskSnapshot.metadata contains file metadata such as size, content-type, etc.
            // ...
            Log.d("kep", "feltoltes")
            }*/

            //storage reference
            /* val urifrompath = Uri.fromFile(File(realPath));

             val storage = FirebaseStorage.getInstance()
             val storageRef = storage.reference

             val uploadTask = storageRef.putFile(urifrompath)
             uploadTask.addOnFailureListener {
                 // Handle unsuccessful uploads
                 Log.d("kep2", "nincsfeltoltes")
             }.addOnSuccessListener {
                 // taskSnapshot.metadata contains file metadata such as size, content-type, etc.
                 // ...
                 Log.d("kep", "feltoltes")
             }*/
/*
            val storage = FirebaseStorage.getInstance()
            val storageRef = storage.reference;



            uploadTask.addOnFailureListener {
                // Handle unsuccessful uploads
                Log.d("kep2", "nincsfeltoltes")
            }.addOnSuccessListener {
                // taskSnapshot.metadata contains file metadata such as size, content-type, etc.
                // ...
                Log.d("kep", "feltoltes")
            }*/


        }


    }
    /*
    private fun getFileExtension(uri: Uri?): String? {
        val cR: ContentResolver? = null
        val mime = MimeTypeMap.getSingleton()

        return mime.getExtensionFromMimeType(cR!!.getType(uri))
    }*/
    /*private fun imageload(uripath: String, realpath: String) {


        val urifrompath = Uri.fromFile(File(realpath));
        var bitmap: Bitmap? = null;
        try {
            bitmap = BitmapFactory.decodeFile(realpath)
        } catch (e: Exception) {
            print(e)
        }
        this.imageView!!.setImageBitmap(bitmap!!)

    }*/
    /*private fun imgUpload() {
        fileRef = storageRef!!.child(System.currentTimeMillis().toString() +  "." + getFileExtension(pImageURI))
        pImageURI?.let { fileRef!!.putFile(it) }
    }*/

}
