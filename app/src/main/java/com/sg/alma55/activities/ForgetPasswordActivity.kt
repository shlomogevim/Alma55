package com.sg.alma55.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.sg.alma55.R
import com.sg.alma55.databinding.ActivityForgetPasswordBinding
import com.sg.alma55.utilities.BaseActivity

class ForgetPasswordActivity : BaseActivity() {

    private lateinit var binding: ActivityForgetPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgetPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnSubmit.setOnClickListener {
            val email: String = binding.etEmail.text.toString().trim { it <= ' ' }
            if (email.isEmpty()) {
                //   showErrorSnackBar(resources.getString(R.string.err_msg_enter_email), true)
                Toast.makeText(this, "Please enter email adress.", Toast.LENGTH_LONG).show()
            } else {

                // Show the progress dialog.
                showProgressDialog(resources.getString(R.string.please_wait))

                // This piece of code is used to send the reset password link to the user's email id if the user is registered.
                FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                    .addOnCompleteListener { task ->

                        // Hide the progress dialog
                        hideProgressDialog()

                        if (task.isSuccessful) {
                            // Show the toast message and finish the forgot password activity to go back to the login screen.
                            Toast.makeText(
                                this,
                                resources.getString(R.string.email_sent_success),
                                Toast.LENGTH_LONG
                            ).show()

                            finish()
                        } else {
                            //  showErrorSnackBar(task.exception!!.message.toString(), true)
                            Toast.makeText(
                                this, task.exception!!.message, Toast.LENGTH_LONG
                            ).show()
                        }
                    }
            }
        }
    }
}

