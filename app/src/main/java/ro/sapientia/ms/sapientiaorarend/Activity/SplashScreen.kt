package ro.sapientia.ms.sapientiaorarend.Activity


import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import gr.net.maroulis.library.EasySplashScreen
import ro.sapientia.ms.sapientiaorarend.R

class SplashScreen : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var easySplashScreen: EasySplashScreen? = null






        easySplashScreen = EasySplashScreen(this)
            .withFullScreen()
            .withTargetActivity(LoginScreen::class.java)
            .withSplashTimeOut(5000)
            .withBackgroundColor(Color.WHITE)
            .withLogo(R.drawable.splash)
            .withHeaderText("Üdvözöljük")
            .withFooterText("Copyrigth lehel atti")
            .withBeforeLogoText("Sapientia órarend")
            .withAfterLogoText("Hogy mindig napra kész legyen")




        easySplashScreen!!.afterLogoTextView.setTextAppearance(
            applicationContext,
            android.R.style.TextAppearance_DeviceDefault_Large
        )
        easySplashScreen!!.afterLogoTextView.setTextColor(resources.getColor(R.color.slapshcolor))
        easySplashScreen!!.beforeLogoTextView.setTextAppearance(
            applicationContext,
            android.R.style.TextAppearance_Large
        )
        easySplashScreen!!.beforeLogoTextView.setTextColor(resources.getColor(R.color.slapshcolor))
        easySplashScreen!!.headerTextView.setTextColor(resources.getColor(R.color.slapshcolor))
        easySplashScreen!!.footerTextView.setTextColor(resources.getColor(R.color.slapshcolor))


        var v: View = easySplashScreen.create()

        setContentView(v)

    }
}
