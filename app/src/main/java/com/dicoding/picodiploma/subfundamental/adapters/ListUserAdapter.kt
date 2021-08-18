package com.dicoding.picodiploma.subfundamental.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.picodiploma.subfundamental.R
import com.dicoding.picodiploma.subfundamental.databinding.ItemRowUserBinding
import com.dicoding.picodiploma.subfundamental.repositories.models.User
import com.dicoding.picodiploma.subfundamental.view.DetailActivity

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

        val mContext = holder.itemView.context

        holder.itemView.setOnClickListener {
            val userDetail = Intent(mContext, DetailActivity::class.java)
            userDetail.putExtra(DetailActivity.EXTRA_LOGIN, user.login)
            userDetail.putExtra(DetailActivity.EXTRA_ID, user.id)
            userDetail.putExtra(DetailActivity.EXTRA_AVATAR, user.avatar_url)
            mContext.startActivity(userDetail)
        }
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