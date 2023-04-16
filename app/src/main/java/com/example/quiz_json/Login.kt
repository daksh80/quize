package com.example.quiz_json

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil.setContentView
import com.example.quiz_json.Controllers.StartQuizController
import com.example.quiz_json.databinding.LoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Login.newInstance] factory method to
 * create an instance of this fragment.
 */
class Login : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var binding: LoginBinding
    private lateinit var auth: FirebaseAuth;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginBinding.inflate(layoutInflater)

        auth = Firebase.auth
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.login, container, false)
        val login = view.findViewById<EditText>(R.id.Editlogin)
        val password = view.findViewById<EditText>(R.id.Editpassword)
        val submitbtn = view.findViewById<Button>(R.id.loginidbtn)
        val signuptxt = view.findViewById<TextView>(R.id.signuptxt)

        submitbtn.setOnClickListener {
             val EditText = login.text.toString()
             val EditPass = password.text.toString()
             auth.signInWithEmailAndPassword(EditText,EditPass)
                 .addOnCompleteListener{ task ->
                     if (task.isSuccessful) {
                         // Sign in success, update UI with the signed-in user's information
                         Log.d(TAG, "signInWithCustomToken:success")
                         val user = auth.currentUser
                         replaceFragment(StartQuizController())
                     } else {
                         // If sign in fails, display a message to the user.
                         Log.w(TAG, "signInWithCustomToken:failure", task.exception)
                         Toast.makeText(context, "Authentication failed.",
                             Toast.LENGTH_SHORT).show()

                     }
                 }

        }
        signuptxt.setOnClickListener {
            replaceFragment(SignUp())
        }

        return  view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Login.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Login().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
    private fun replaceFragment(fragment: Fragment){
        parentFragmentManager.beginTransaction().replace(R.id.fragment_container3,fragment).addToBackStack("null").commit()
    }
}