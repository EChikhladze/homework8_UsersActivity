package com.example.homework8_usersactivity

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.recyclerview.widget.RecyclerView
import com.example.homework8_usersactivity.databinding.UserRecyclerviewBinding

class UserRecyclerAdapter(private val users: MutableList<User>) :
    RecyclerView.Adapter<UserRecyclerAdapter.UserViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            UserRecyclerviewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]
        holder.bind(user)

    }

    inner class UserViewHolder(private val binding: UserRecyclerviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) {
            binding.tvUserFirstName.text = user.firstName
            binding.tvUserLastName.text = user.lastName
            binding.tvUserEmail.text = user.email

            binding.btnDelete.setOnClickListener {
                users.removeAt(adapterPosition)
                notifyItemRemoved(adapterPosition)
                notifyItemRangeChanged(adapterPosition, users.size)
            }

            val intent = Intent(binding.root.context, EditActivity::class.java)
            intent.putExtra("firstName", user.firstName)
            intent.putExtra("lastName", user.lastName)
            intent.putExtra("email", user.email)
            intent.putExtra("position", adapterPosition)

            binding.btnEdit.setOnClickListener {
//                startActivityForResult(EditActivity(), intent, 1, null)

                binding.root.context.startActivity(intent)

//                binding.root.context.startActivity(intent)
//                user.firstName = intent.getStringExtra("updatedFirstName").toString()
//                user.lastName = intent.getStringExtra("updatedLastName").toString()
//                user.email = intent.getStringExtra("updatedEmail").toString()
//                notifyItemChanged(adapterPosition)
            }

        }
    }
}