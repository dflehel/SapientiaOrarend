package ro.sapientia.ms.sapientiaorarend

import android.app.Dialog
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import ro.sapientia.ms.sapientiaorarend.Adapters.DeparmentSelectorAdapter
import ro.sapientia.ms.sapientiaorarend.Adapters.SearchAdapter
import ro.sapientia.ms.sapientiaorarend.models.User

class SignUpScreen : AppCompatActivity() {

    private var name:String? = null
    private var email:String? = null
    private var phone:String? = null
    private var password:String? = null
    private var editextname:EditText? = null
    private var edittextemail:EditText? = null
    private var edittexphone:EditText? = null
    private var edittextpassword:EditText? = null
    private var edittextpasswordconfirm:EditText? = null
    private var button: Button? = null
    private var progressDialog: ProgressDialog? = null
    private var mAuth: FirebaseAuth? = null
    private var user: FirebaseUser? = null
    private var deparmentselectorbutton : Button? = null
    private var databaseReference:DatabaseReference = FirebaseDatabase.getInstance().reference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_screen)
        mAuth = FirebaseAuth.getInstance()
        this.deparmentselectorbutton = findViewById<Button>(R.id.sign_up_screen_deparment_selector)
        this.editextname = findViewById<EditText>(R.id.sign_up_screen_name_editText)
        this.edittexphone = findViewById<EditText>(R.id.sign_up_screen_phone_editText)
        this.edittextemail = findViewById<EditText>(R.id.sign_up_screen_email_editText)
        this.edittextpassword = findViewById<EditText>(R.id.sign_up_screen_passwod_editText)
        this.edittextpasswordconfirm = findViewById<EditText>(R.id.sign_up_screen_configuration_password_textEdit)
        this.button = findViewById<Button>(R.id.sign_up_screen_sign_up_button)
        this.progressDialog = ProgressDialog(this)
        this.button!!.setOnClickListener{
            this.registeruser()
        }
        this.deparmentselectorbutton!!.setOnClickListener{
            var dialog: Dialog = Dialog(this)

            dialog.setContentView(R.layout.search_view)

            var recyclerView: RecyclerView = dialog.findViewById<RecyclerView>(R.id.search_screen_rec)
            var deparmentSelectorAdapter = DeparmentSelectorAdapter(dialog.context,dialog,this.deparmentselectorbutton)
            //searchAdapter.dialog = dialog
            recyclerView.adapter = deparmentSelectorAdapter
            recyclerView.layoutManager = LinearLayoutManager(dialog.context) as RecyclerView.LayoutManager?

            dialog.show()
        }
    }


    fun elleorzo(pas:String,pas2:String):Boolean{
        return pas.contentEquals(pas2)
    }

    fun registeruser(){
        this.name = this.editextname!!.text.toString()
        this.email = this.edittextemail!!.text.toString()
        this.password = this.edittextpassword!!.text.toString()
        this.phone = this.edittexphone!!.text.toString()
        this.progressDialog!!.setMessage("Regisztralas")
        this.progressDialog!!.show()
       if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Adjon Email cimmet",Toast.LENGTH_LONG).show()
            return
       }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Irjon be passwordot",Toast.LENGTH_LONG).show()
            return
        }

        if(this.edittextpassword!!.text.toString().equals(this.edittextpasswordconfirm!!.text.toString()) == false){
            Toast.makeText(this,"Nem egyezik meg a jelszo",Toast.LENGTH_LONG).show()
            return
        }

        mAuth!!.createUserWithEmailAndPassword(email!!, password!!)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in: success
                    // update UI for current User
                    Toast.makeText(this,"Sikeres Registracio",Toast.LENGTH_LONG).show()
                    this.user = mAuth!!.currentUser
                    var userprof = UserProfileChangeRequest.Builder().setDisplayName(this.name).build()
                    this.user!!.updateProfile(userprof)

                    var user:User = User(this.name,this.email,this.phone,this.deparmentselectorbutton!!.text.toString().replace(" ","/",false))
                    this.databaseReference.child("User").child(this.user!!.uid).setValue(user)
                    this.progressDialog!!.dismiss()
                    var intent = Intent(this, MainScreen::class.java)
                    startActivity(intent)
                    //updateUI(user)
                } else {
                    // Sign in: fail
                    // updateUI(null)
                    Toast.makeText(this,"Sikeretelen Registracio"+ task.exception,Toast.LENGTH_LONG).show()
                    this.progressDialog!!.dismiss()
                }
            }
    }
}
