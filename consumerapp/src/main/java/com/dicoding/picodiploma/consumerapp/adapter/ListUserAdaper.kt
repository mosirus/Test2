package com.dicoding.picodiploma.consumerapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.picodiploma.consumerapp.R
import com.dicoding.picodiploma.consumerapp.databinding.ItemRowUserBinding
import com.dicoding.picodiploma.consumerapp.model.User

class ListUserAdapter : RecyclerView.Adapter<ListUserAdapter.ListUserViewHolder>() {

    private val listUser = ArrayList<User>()

    fun setData(items: ArrayList<User>) {
        listUser.clear()
        listUser.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int ): ListUserViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_user, parent, false)
        return ListUserViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListUserViewHolder, position: Int) {
        val user = listUser[position]
        holder.bind(user)
    }

    override fun getItemCount(): Int {
        return listUser.size
    }

    class ListUserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemRowUserBinding.bind(itemView)

        internal fun bind(user : User) {
            Glide.with(itemView.context)
                    .load(user.avatar_url)
                    .centerCrop()
                    .into(binding.ivUser)

            binding.tvName.text = user.login
        }
    }
}