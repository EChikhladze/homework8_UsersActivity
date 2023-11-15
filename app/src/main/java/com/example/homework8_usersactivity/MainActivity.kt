package com.example.homework8_usersactivity

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatEditText
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homework8_usersactivity.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val users: MutableList<User> = mutableListOf()

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            val updatedFirstName = data?.getStringExtra("updatedFirstName")
            val updatedLastName = data?.getStringExtra("updatedLastName")
            val updatedEmail = data?.getStringExtra("updatedEmail")
            val position = data?.getIntExtra("position", -1)

            if (updatedFirstName != null) users[position!!].firstName = updatedFirstName
            if (updatedLastName != null) users[position!!].lastName = updatedLastName
            if (updatedEmail != null) users[position!!].email = updatedEmail
            binding.recyclerView.adapter!!.notifyItemChanged(position!!)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUp()
    }

    private fun setUp() {
        setUpRecycler()

        binding.btnAdd.setOnClickListener {
            addUser()
        }
    }

    private fun setUpRecycler() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = UserRecyclerAdapter(users)
    }

    private fun addUser() {
        if (isInputValid()) {
            val user = User(
                binding.edFirstName.text.toString(),
                binding.edLastName.text.toString(),
                binding.edEmail.text.toString()
            )

            users.add(user)
            binding.recyclerView.adapter!!.notifyItemInserted(users.size - 1)
        }
    }


    private fun isInputValid(): Boolean {
        return isFilled() && isEmailValid()
    }

    private fun isFilled(): Boolean {
        val firstName = binding.edFirstName
        val lastName = binding.edLastName
        val email = binding.edEmail

        if (firstName.text.toString().isEmpty() || lastName.text.toString().isEmpty() || email.text.toString().isEmpty()) {
            displayErrorMessage(firstName, getString(R.string.required_field))
            displayErrorMessage(lastName, getString(R.string.required_field))
            displayErrorMessage(email, getString(R.string.required_field))
            return false
        }

        return true
    }

    private fun isEmailValid(): Boolean {
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(binding.edEmail.text.toString()).matches()) {
            displayErrorMessage(binding.edEmail, getString(R.string.invalid_email))
            return false
        }

        return true
    }

    private fun displayErrorMessage(element: AppCompatEditText, errorMsg: String) {
        element.setText("")
        element.setHintTextColor(Color.RED)
        element.hint = errorMsg
    }
}