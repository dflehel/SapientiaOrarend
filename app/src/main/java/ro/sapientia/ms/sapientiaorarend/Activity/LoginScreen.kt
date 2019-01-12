package ro.sapientia.ms.sapientiaorarend.Activity

import android.app.PendingIntent.getActivity
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.database.*
import ro.sapientia.ms.sapientiaorarend.R
import ro.sapientia.ms.sapientiaorarend.Util.ClassColorsBuilder
import ro.sapientia.ms.sapientiaorarend.Util.ClassPathBuilder
import ro.sapientia.ms.sapientiaorarend.Util.Settings
import ro.sapientia.ms.sapientiaorarend.models.User
import java.util.concurrent.TimeUnit


/** A bejelenkezes kepernyo*/
class LoginScreen : AppCompatActivity() {


    lateinit var Email: EditText
    lateinit var Phone: EditText
    lateinit var Password: EditText
    lateinit var Login: Button
    lateinit var Signup: TextView
    private var email: String? = null
    private var password: String? = null
    private var mAuth: FirebaseAuth? = null
    private var databasereferenc2: DatabaseReference? = null
    private var guest: TextView? = null
    var progressDialog: ProgressDialog? = null
    private var user: User? = null
    var terminated: Boolean? = false
    private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken
    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    private lateinit var auth: FirebaseAuth


    /**A fugvenybe kapcsolom ossze a view elemek azokkal az osztalymezokkel melyekkel majd ellenorzom a bejelentkezest
    es a view elemekre rateszem a a vendeg bejelenkezest vagy a regisztraciot*/
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
        ClassPathBuilder(this)
        ClassColorsBuilder(this)
        this.progressDialog = ProgressDialog(this)
        this.Login.setOnClickListener {
            loggingig()
           // logginginwhitphone()
        }
        this.Signup.setOnClickListener {
            var intent2 = Intent(this, SignUpScreen::class.java)
            startActivity(intent2)
        }
    }

    /** a startba megnezzem elozoleg volt bejelenkezve ha izen egybol adobom a fokepernyore elotte lekerem a user adatokat*/
    override fun onStart() {
        super.onStart()
        if (mAuth!!.currentUser != null) {
            this.progressDialog!!.setMessage("Bejelentkezés")
            this.progressDialog!!.show()
            var intent = Intent(this, MainScreen::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            val context:Context = this.applicationContext
            this.databasereferenc2 = FirebaseDatabase.getInstance().reference.child("/user")
                .child(FirebaseAuth.getInstance().currentUser!!.uid)
            val listener: ValueEventListener = object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    // Get Post object and use the values to update the UI
                    user = dataSnapshot.getValue<User>(User::class.java)
                    Settings.user = user!!
                    if (ClassColorsBuilder.terminated && ClassPathBuilder.terminated) {
                        startingmainscreen()
                        progressDialog!!.dismiss()
                    }
                    Toast.makeText(context,"Sikereres bejelentkezés", Toast.LENGTH_LONG).show()
                    terminated = true
                    // ...
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    // Getting Post failed, log a message
                    Toast.makeText(context,"Sikertelen bejelentkezés", Toast.LENGTH_LONG).show()
                    progressDialog!!.dismiss()
                    // ...
                }
            }
            this.databasereferenc2!!.addListenerForSingleValueEvent(listener)
        }
    }

    /**a bejelenkezes ellenorzese*/
    fun loggingig() {
        this.progressDialog!!.setMessage("Bejelentkezés")
        this.progressDialog!!.show()
        this.email = this.Email.text.toString()
        this.password = this.Password.text.toString()
        mAuth!!.signInWithEmailAndPassword(this.email!!, this.password!!)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in: success
                    // update UI for current User
                    val user = mAuth!!.getCurrentUser()
                    this.databasereferenc2 = FirebaseDatabase.getInstance().reference.child("/user")
                        .child(FirebaseAuth.getInstance().currentUser!!.uid)
                    val listener: ValueEventListener = object : ValueEventListener {
                        override fun onDataChange(dataSnapshot: DataSnapshot) {
                            // Get Post object and use the values to update the U
                            Settings.user = dataSnapshot.getValue<User>(User::class.java)
                            if (ClassColorsBuilder.terminated && ClassPathBuilder.terminated) {
                                startingmainscreen()
                                progressDialog!!.dismiss()
                            }
                            terminated = true
                            // ...
                        }

                        override fun onCancelled(databaseError: DatabaseError) {
                            // Getting Post failed, log a message

                            // ...
                        }
                    }
                    this.databasereferenc2!!.addListenerForSingleValueEvent(listener)
                    Toast.makeText(this, "Sikeres bejelentkezés", Toast.LENGTH_LONG).show()
                } else {
                    // Sign in: fail
                    Toast.makeText(this, "Sikertelen bejelentkezés", Toast.LENGTH_LONG).show()
                    this.progressDialog!!.dismiss()
                }

                // ...
            }
    }

    /** fo kepernyo inditasa*/
    fun startingmainscreen() {
        var intent = Intent(this, MainScreen::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
    }

  /*  fun logginginwhitphone() {
        var phone =  this.Phone.text.toString()
        val smsCode = "123456"

        val firebaseAuth = FirebaseAuth.getInstance()
        val firebaseAuthSettings = firebaseAuth.firebaseAuthSettings

       // firebaseAuthSettings.setAutoRetrievedSmsCodeForPhoneNumber(phone, smsCode)

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
            phone, 1 /*timeout*/, TimeUnit.MINUTES,
            this, object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                override fun onCodeSent(
                    verificationId: String?,
                    forceResendingToken: PhoneAuthProvider.ForceResendingToken?
                ) {
                    // Save the verification id somewhere
                    // ...
                    mProgressDialog.dismiss();
                    // The corresponding whitelisted code above should be used to complete sign-in.
                    // this@MainActivity.enableUserManuallyInputCode()
                }

                override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {
                    // Sign in with the credential
                    // ...
                    var mAuthCredentials = phoneAuthCredential;
                   // startingmainscreen()
                }

                override fun onVerificationFailed(e: FirebaseException) {
                    e.printStackTrace();
                    Log.e("dgsgf", e.message);
                    // ...
                }
            })
        var mProgressDialog =  ProgressDialog (this);
        mProgressDialog.setMessage("Sending verification code...");
        mProgressDialog.show();

    }*/



}

