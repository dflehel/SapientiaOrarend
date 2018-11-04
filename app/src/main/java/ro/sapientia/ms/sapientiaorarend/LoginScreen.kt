package ro.sapientia.ms.sapientiaorarend

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class LoginScreen : AppCompatActivity() {

        lateinit var signuptextview : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)
        this.signuptextview = findViewById(R.id.login_screen_signup_button) as TextView
        this.signuptextview.setOnClickListener{
            val intent  = Intent(this,SignUpScreen::class.java)
            startActivity(intent)
        }
    }
}
