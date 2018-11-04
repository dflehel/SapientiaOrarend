package ro.sapientia.ms.sapientiaorarend

import android.app.ProgressDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class SignUpScreen : AppCompatActivity() {

    lateinit var name:String
    lateinit var email:String
    lateinit var phone:String
    lateinit var password:String
    lateinit var editextname:EditText
    lateinit var edittextemail:EditText
    lateinit var edittexphone:EditText
    lateinit var edittextpassword:EditText
    lateinit var edittextpasswordconfirm:EditText
    lateinit var button: Button
    lateinit var progressDialog: ProgressDialog
    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_screen)
        mAuth = FirebaseAuth.getInstance()
        this.editextname = findViewById<EditText>(R.id.sign_up_screen_name_editText)
        this.edittexphone = findViewById<EditText>(R.id.sign_up_screen_phone_editText)
        this.edittextemail = findViewById<EditText>(R.id.sign_up_screen_email_editText)
        this.edittextpassword = findViewById<EditText>(R.id.sign_up_screen_passwod_editText)
        this.edittextpasswordconfirm = findViewById<EditText>(R.id.sign_up_screen_configuration_password_textEdit)
        this.button = findViewById<Button>(R.id.sign_up_screen_sign_up_button)
        this.progressDialog = ProgressDialog(this)
        this.button.setOnClickListener{
            this.registeruser()
        }
    }


    fun elleorzo(pas:String,pas2:String):Boolean{
        return pas.contentEquals(pas2)
    }

    fun registeruser(){
        this.name = this.editextname.text.toString()
        this.email = this.edittextemail.text.toString()
        this.password = this.edittextpassword.text.toString()
        this.progressDialog.setMessage("Regisztralas")
        this.progressDialog.show()
       if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Adjon Email cimmet",Toast.LENGTH_LONG).show()
            return
       }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Irjon be passwordot",Toast.LENGTH_LONG).show()
            return
        }

        if(this.edittextpassword.text.toString().equals(this.edittextpasswordconfirm.text.toString()) == false){
            Toast.makeText(this,"Nem egyezik meg a jelszo",Toast.LENGTH_LONG).show()
            return
        }

        mAuth!!.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in: success
                    // update UI for current User
                    Toast.makeText(this,"Sikeres Registracio",Toast.LENGTH_LONG).show()
                    val user = mAuth!!.currentUser
                    //updateUI(user)
                } else {
                    // Sign in: fail
                    // updateUI(null)
                    Toast.makeText(this,"Sikeretelen Registracio",Toast.LENGTH_LONG).show()
                }
            }
        this.progressDialog.dismiss()
    }
}
