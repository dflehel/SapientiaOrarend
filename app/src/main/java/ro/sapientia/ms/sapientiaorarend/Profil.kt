package ro.sapientia.ms.sapientiaorarend

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class Profil : Fragment() {

    private var email: TextView? = null

    private var name: TextView? = null

    private var button: Button? = null

    private var user: FirebaseUser? = null

    private var mAuth: FirebaseAuth? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        this.mAuth = FirebaseAuth.getInstance()
        this.user = this.mAuth!!.currentUser
        val root = inflater.inflate(R.layout.fragment_profil, container, false)
        this.email = root.findViewById<TextView>(R.id.profile_screen_email_id)
        this.name = root.findViewById<TextView>(R.id.profile_screen_name_id)
        this.button = root.findViewById<Button>(R.id.profil_screen_sign_out_button)
        this.email!!.text = user!!.email
        this.name!!.text = user!!.displayName
        this.button!!.setOnClickListener {
            this.mAuth!!.signOut()
            Toast.makeText(root.context, "Kijelentkeztel", Toast.LENGTH_LONG).show()
            this.activity!!.finish()
        }
        return root
    }


    companion object {
            fun newIstance(): Profil = Profil()
    }
}
