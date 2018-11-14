package ro.sapientia.ms.sapientiaorarend

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import ro.sapientia.ms.sapientiaorarend.models.ClassPathBuilder

class LoginScreen : AppCompatActivity() {


    lateinit var Email: EditText
    lateinit var Phone:EditText
    lateinit var Password:EditText
    lateinit var Login: Button
    lateinit var Signup: TextView
    private var email:String?= null
    private var password:String?=null
    private var mAuth: FirebaseAuth? = null
    private var guest: TextView? = null
    private var progressDialog: ProgressDialog?=null



    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)
        mAuth = FirebaseAuth.getInstance()
        this.guest = findViewById<TextView>(R.id.log_in_screen_guet_text_view)
        this.guest!!.setOnClickListener {
            var intent = Intent(this, MainScreen::class.java)
            startActivity(intent)
        }
        this.Email = findViewById(R.id.login_screen_email)
        this.Phone = findViewById(R.id.login_screen_phone)
        this.Password = findViewById(R.id.login_screen_password)
        this.Login = findViewById(R.id.login_screen_login_button)
        this.Signup = findViewById(R.id.login_screen_signup_button)
        var clas: ClassPathBuilder = ClassPathBuilder()
        this.progressDialog = ProgressDialog(this)
        this.Login.setOnClickListener{
                    loggingig()
            }
        this.Signup.setOnClickListener{
             var intent2 = Intent(this, SignUpScreen::class.java)
             startActivity(intent2)
        }
        }

    override fun onStart() {
        super.onStart()
        val user = mAuth!!.currentUser
        if (user != null){
            var intent = Intent(this, MainScreen::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
        }
    }

    fun loggingig(){
        this.progressDialog!!.setMessage("Bejelentkezes")
        this.progressDialog!!.show()
        this.email = this.Email.text.toString()
        this.password = this.Password.text.toString()
        mAuth!!.signInWithEmailAndPassword(this.email!!, this.password!!)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in: success
                    // update UI for current User
                    val user = mAuth!!.getCurrentUser()
                    this.progressDialog!!.dismiss()
                    Toast.makeText(this,"Sikeres bejelentkezes", Toast.LENGTH_LONG).show()
                    var intent = Intent(this, MainScreen::class.java)
                    startActivity(intent)
                } else {
                    // Sign in: fail
                    Toast.makeText(this,"Sikeres bejelentkezes",Toast.LENGTH_LONG).show()
                    this.progressDialog!!.dismiss()
                }

                // ...
            }
    }
    }

