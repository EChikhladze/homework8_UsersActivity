package com.example.homework8_usersactivity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.homework8_usersactivity.databinding.ActivityEditUserBinding

class EditActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUp()
    }

    private fun setUp() {
        val ogFirstName = intent.getStringExtra("firstName")
        val ogLastName = intent.getStringExtra("lastName")
        val ogEmail = intent.getStringExtra("email")
        val position = intent.getIntExtra("position", -1)

        setData(ogFirstName, ogLastName, ogEmail)
        setOnClickListeners(ogFirstName, ogLastName, ogEmail, position)
    }

    private fun setData(firstName: String?, lastName: String?, email: String?) {
        binding.edFirstName.setText(firstName)
        binding.edLastName.setText(lastName)
        binding.edEmail.setText(email)
    }

    private fun setOnClickListeners(
        ogFirstName: String?,
        ogLastName: String?,
        ogEmail: String?,
        position: Int
    ) {
        binding.btnCancel.setOnClickListener {
            finish()
        }

        val intent = Intent(this, MainActivity::class.java)
        val firstName = binding.edFirstName.text.toString()
        val lastName = binding.edLastName.text.toString()
        val email = binding.edEmail.text.toString()


        binding.btnSave.setOnClickListener {
            if (firstName != ogFirstName) intent.putExtra("updatedFirstName", firstName)
            if (lastName != ogLastName) intent.putExtra("updatedLastName", lastName)
            if (email != ogEmail) intent.putExtra("updatedEmail", email)

            intent.putExtra("position", position)
            setResult(Activity.RESULT_OK, intent)
            startActivity(intent)
        }
    }
}