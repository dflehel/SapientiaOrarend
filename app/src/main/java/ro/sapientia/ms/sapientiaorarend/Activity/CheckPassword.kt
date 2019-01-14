package ro.sapientia.ms.sapientiaorarend.Activity

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.*
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import me.aflak.libraries.callback.FingerprintDialogCallback
import me.aflak.libraries.dialog.FingerprintDialog
import ro.sapientia.ms.sapientiaorarend.R


/**elleorzo ha notibol lep be a felhasznalo*/
class CheckPassword : AppCompatActivity() {

    private var paslabel: TextView? = null

    private var pass: EditText? = null

    private var image: ImageView? = null

    private var checkbutton: Button? = null

    private var cancelbutton: Button? = null
    private var progressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_password)
        this.cancelbutton = findViewById<Button>(R.id.password_button_cancel)
        this.checkbutton = findViewById<Button>(R.id.password_check_passwod_button)
        this.image = findViewById<ImageView>(R.id.password_image)
        this.pass = findViewById<EditText>(R.id.assword_input)
        this.paslabel = findViewById<TextView>(R.id.password_label)
        this.progressDialog = ProgressDialog(this)
        cancelbutton!!.setOnClickListener {
            this.finish()
        }
        //megprobalok ujra bejelenkezni az altala beirt jelszoval ha sikeres tovabb lepek ha nem akkot leallok s ujra kerem
        checkbutton!!.setOnClickListener {
            this.progressDialog!!.setTitle("Ellenorzes folyamatban")
            this.progressDialog!!.show()
            var user: FirebaseUser? = FirebaseAuth.getInstance().currentUser
            var credential: AuthCredential? =
                EmailAuthProvider.getCredential(user!!.email!!, pass!!.text.toString())
            user!!.reauthenticate(credential!!).addOnCompleteListener {
                if (it.isSuccessful) {


                    image!!.setImageResource(R.mipmap.ic_unlock)
                    paslabel!!.setText("Sikeres bejelentkezes")
                    Toast.makeText(applicationContext, "Sikeres", Toast.LENGTH_SHORT)
                    paslabel!!.setTextColor(resources.getColor(R.color.slapshcolor))
                    var intent2 = Intent(applicationContext, MessageDisplay::class.java)
                    progressDialog!!.dismiss()
                    startActivity(intent2)
                    finish()
                } else {
                    image!!.setImageResource(R.mipmap.ic_lock_error_round)
                    paslabel!!.setText("Sikertelen bejelentkezes")
                    Toast.makeText(applicationContext, "Sikertelen", Toast.LENGTH_SHORT)
                    paslabel!!.setTextColor(Color.RED)
                    progressDialog!!.dismiss()
                }

            }
        }
        //ellenorzom hogy lehet_e ujlenyometot hasznalni ha igen eloszor az jelenik meg
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                if (FingerprintDialog.isAvailable(this)) {
                    FingerprintDialog.initialize(this)
                        .title("Ellenorzes")
                        .message("Hasznalja az ujlenyomatat a tovabb lepeshez")
                        .callback(object : FingerprintDialogCallback {
                            override fun onAuthenticationSucceeded() {
                                var intent2 = Intent(applicationContext, activity_send_message::class.java)
                                startActivity(intent2)
                                finish()
                            }

                            override fun onAuthenticationCancel() {

                            }
                        })
                        .show();
                }

            }

    }
}
