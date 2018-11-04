package ro.sapientia.ms.sapientiaorarend

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class LoginScreen : AppCompatActivity() {


    lateinit var Email: EditText
    lateinit var Phone:EditText
    lateinit var Password:EditText
    lateinit var Login: Button
    lateinit var Signup: TextView




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)

        this.Email = findViewById(R.id.login_screen_email)
        this.Phone = findViewById(R.id.login_screen_phone)
        this.Password = findViewById(R.id.login_screen_password)
        this.Login = findViewById(R.id.login_screen_login_button)
        this.Signup = findViewById(R.id.login_screen_signup_button)

        this.Login.setOnClickListener{
            var intent = Intent(this, MainScreen::class.java)
                startActivity(intent)
            }
        this.Signup.setOnClickListener{
             var intent2 = Intent(this, SignUpScreen::class.java)
             startActivity(intent2)
        }
        }
    }
