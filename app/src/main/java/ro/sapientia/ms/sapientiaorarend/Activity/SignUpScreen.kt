package ro.sapientia.ms.sapientiaorarend.Activity

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
import ro.sapientia.ms.sapientiaorarend.R
import ro.sapientia.ms.sapientiaorarend.models.User


/** regisztralasra szolgal*/
class SignUpScreen : AppCompatActivity() {

    lateinit var name: String
    lateinit var email: String
    lateinit var phone: String
    lateinit var password: String
    lateinit var editextname: EditText
    lateinit var edittextemail: EditText
    lateinit var edittexphone: EditText
    lateinit var edittextpassword: EditText
    lateinit var edittextpasswordconfirm: EditText
    lateinit var button: Button
    private var buttondeparment: Button? = null
    lateinit var progressDialog: ProgressDialog
    private var mAuth: FirebaseAuth? = null
    private var user: FirebaseUser? = null
    private var databaseReference: DatabaseReference? = null


    /** inicializalja a parametereket*/
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
        this.buttondeparment = findViewById<Button>(R.id.sign_up_screen_deparment_selector)
        this.button.setOnClickListener {
            this.registeruser()
        }
        this.buttondeparment!!.setOnClickListener {
            var dialog: Dialog = Dialog(this)

            dialog.setContentView(R.layout.search_view)

            var recyclerView: RecyclerView = dialog.findViewById<RecyclerView>(R.id.search_screen_rec)
            var deparmentSelectorAdapter: DeparmentSelectorAdapter =
                DeparmentSelectorAdapter(dialog.context, dialog, this.buttondeparment)
            //searchAdapter.data = this.data
            //searchAdapter.dialog = dialog
            recyclerView.adapter = deparmentSelectorAdapter
            recyclerView.layoutManager = LinearLayoutManager(dialog.context)

            dialog.show()
        }
    }


    fun elleorzo(pas: String, pas2: String): Boolean {
        return pas.contentEquals(pas2)
    }

    fun fullcheck(name: String, edittextemail: String, phone: String, edittextpassword: String): Boolean {
        if (TextUtils.isEmpty(name) || name.length > 25 ) {
            Toast.makeText(this, "A nev hosszusaga limitalt vagy ures", Toast.LENGTH_LONG).show()
            return false
        }
        if (TextUtils.isEmpty(edittextemail) || !edittextemail.contains("@") || !edittextemail.contains(".")) {
            Toast.makeText(this, "Az emailcimnek tartalmaznia kell '@'-ot es '.'-ot", Toast.LENGTH_LONG).show()
            return false
        }
        if (TextUtils.isEmpty(phone) || !phone.matches("-?\\d+(\\.\\d+)?".toRegex())) {
            Toast.makeText(this, "A telefonszam nem lehet ures es csak szamokat tartalmazhat", Toast.LENGTH_LONG).show()
            return false
        }
        if (TextUtils.isEmpty(edittextpassword) || edittextpassword.length < 6) {
            Toast.makeText(this, "A jelszo minimum 6 karaktert kell tartalmazzon.", Toast.LENGTH_LONG).show()
            return false
        }
        if (this.edittextpassword.text.toString().equals(this.edittextpasswordconfirm.text.toString()) == false) {
            Toast.makeText(this, "Nem egyezik meg a jelszo", Toast.LENGTH_LONG).show()
            return false
        }
        return true
    }


    /** elvegzi a regisztralast es az adabazisba bejegyezendo dolgokat*/
    fun registeruser() {
        this.name = this.editextname.text.toString()
        this.email = this.edittextemail.text.toString()
        this.password = this.edittextpassword.text.toString()
        this.phone = this.edittexphone.text.toString()
        this.progressDialog.setMessage("Regisztralas")
        //this.progressDialog.show()


        /*if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Adjon Email cimmet", Toast.LENGTH_LONG).show()
            return
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Írjon be egy jelszót!", Toast.LENGTH_LONG).show()
            return
        }

        if (this.edittextpassword.text.toString().equals(this.edittextpasswordconfirm.text.toString()) == false) {
            Toast.makeText(this, "A jelszavak nem egyeznek!", Toast.LENGTH_LONG).show()
            return
        }*/


        if (fullcheck(name, email, phone, password)) {
            this.progressDialog.show()

            mAuth!!.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in: success
                        // update UI for current User
                        this.progressDialog.dismiss()
                        Toast.makeText(this, "Sikeres Registracio", Toast.LENGTH_LONG).show()
                        this.user = mAuth!!.currentUser
                        var userprof = UserProfileChangeRequest.Builder().setDisplayName(this.name).build()
                        this.databaseReference = FirebaseDatabase.getInstance().reference.child("/user")
                        this.databaseReference!!.child(user!!.uid).setValue(
                            User(
                                this.name,
                                this.email,
                                this.phone,
                                this.buttondeparment!!.text.toString().replace(" ", "/", false)
                            )
                        )
                        this.user!!.updateProfile(userprof)
                        this.progressDialog.dismiss()
                        var intent = Intent(this, MainScreen::class.java)
                        startActivity(intent)

                        //updateUI(user)
                    } else {
                        // Sign in: fail
                        // updateUI(null)
                        Toast.makeText(this, "Sikeretelen Registracio", Toast.LENGTH_LONG).show()
                        this.progressDialog.dismiss()
                    }
                }
        }
    }
}
