package com.androidapp.abilitytohelp.activity.profile

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.androidapp.abilitytohelp.R
import com.parse.ParseException
import com.parse.ParseUser
import com.parse.LogInCallback;
class SignInFragment : Fragment() {

    private lateinit var email_et: EditText
    private lateinit var password_et: EditText
    private lateinit var signInBtn: RelativeLayout
    private lateinit var loading: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_in, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        email_et = view.findViewById(R.id.email)
        password_et = view.findViewById(R.id.password)
        signInBtn = view.findViewById(R.id.loginBtn)
        loading = view.findViewById(R.id.loading)

        signInBtn.setOnClickListener {
            if (isInputValid(email_et.text.toString(),password_et.text.toString())){
                requireActivity().currentFocus?.let { it1 ->
                    hideKeyboardFrom(requireContext(),
                        it1
                    )
                }
                loading.visibility = View.VISIBLE
                ParseUser.logInInBackground(email_et.text.toString(), password_et.text.toString()) { parseUser: ParseUser?, parseException: ParseException? ->
                    if (parseUser != null) {
                        loading.visibility = View.GONE
                        requireActivity().finish()
                    } else {
                        loading.visibility = View.GONE
                        ParseUser.logOut()
                        if (parseException != null) {
                            Toast.makeText(requireContext(), parseException.message, Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
        }
    }

    private fun isInputValid(email: String, password: String): Boolean {
        if (email.isNullOrEmpty()){
            Toast.makeText(requireContext(),"Enter email", Toast.LENGTH_SHORT).show()
            return false
        }else if (password.isNullOrEmpty()){
            Toast.makeText(requireContext(),"Enter password", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }


    fun hideKeyboardFrom(context: Context, view: View) {
        val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

}