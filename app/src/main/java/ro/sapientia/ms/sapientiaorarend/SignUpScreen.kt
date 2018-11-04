package ro.sapientia.ms.sapientiaorarend

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_screen)

        this.editextname = findViewById<EditText>(R.id.sign_up_screen_name_editText)
        this.edittexphone = findViewById<EditText>(R.id.sign_up_screen_phone_editText)
        this.edittextemail = findViewById<EditText>(R.id.sign_up_screen_email_editText)
        this.edittextpassword = findViewById<EditText>(R.id.sign_up_screen_passwod_editText)
        this.edittextpasswordconfirm = findViewById<EditText>(R.id.sign_up_screen_configuration_password_textEdit)
        this.button = findViewById<Button>(R.id.sign_up_screen_sign_up_button)
    }


    fun elleorzo(pas:String,pas2:String):Boolean{
        return pas.contentEquals(pas2)
    }

    fun registeruser(){
        this.name = this.editextname.toString().trim()
        this.email = this.edittextemail.toString().trim()
        this.password = this.edittextpassword.toString().trim()
        if(TextUtils.isEmpty(email))
    }
}
