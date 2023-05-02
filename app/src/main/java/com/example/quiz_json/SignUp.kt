package com.example.quiz_json

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.os.Bundle
import android.text.InputType
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.quiz_json.Controllers.StartQuizController
import com.example.quiz_json.databinding.SignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

/**
 * A simple [Fragment] subclass.
 * Use the [SignUp.newInstance] factory method to
 * create an instance of this fragment.
 */
class SignUp : Fragment() {
    // Parameters
    private var param1: String? = null
    private var param2: String? = null

    // Firebase authentication object
    private lateinit var auth: FirebaseAuth;

    // Constants for argument keys
    private  val ARG_PARAM1 = "param1"
    private  val ARG_PARAM2 = "param2"

    // View binding
    lateinit var binding: SignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inflate the binding layout
        binding = SignUpBinding.inflate(layoutInflater)
        // Initialize Firebase Auth
        auth = Firebase.auth
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.sign_up, container, false)
        // Find views by id
        val Emaild = view.findViewById<EditText>(R.id.EditSignup)
        val pass = view.findViewById<EditText>(R.id.EditpasswordSignup)
        val signupbtn = view.findViewById<Button>(R.id.signupbtn)
        val logintxt = view.findViewById<TextView>(R.id.logintxt)
        // Set input type of password field
        pass.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD

        // Set click listener for signup button
        signupbtn.setOnClickListener {
            // Get email and password values
            val Editemail = Emaild.text.toString()
            val Editpass = pass.text.toString()
            // Create a new user with the provided email and password
            auth.createUserWithEmailAndPassword(Editemail, Editpass)
                .addOnCompleteListener{
                    if (it.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithCustomToken:success")
                        // Get current user
                        val user = auth.currentUser
                        // Navigate to StartQuizController fragment
                        replaceFragment(StartQuizController())
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithCustomToken:failure", it.exception)
                        Toast.makeText(context, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()

                    }
                }
        }
        // Set click listener for login text view
        logintxt.setOnClickListener{
            // Navigate to Login fragment
            replaceFragment(Login())
        }
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SignUp.
         */
        // Factory method to create a new instance of SignUp fragment


        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SignUp().apply {
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


