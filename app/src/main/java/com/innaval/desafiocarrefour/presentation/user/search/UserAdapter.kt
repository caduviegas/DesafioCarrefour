package com.innaval.desafiocarrefour.presentation.user.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.innaval.desafiocarrefour.databinding.ItemUserBinding
import com.innaval.desafiocarrefour.domain.model.UserModel

class UserAdapter (
    var onClickListener: (userModel: UserModel) -> Unit = {},
) : ListAdapter<UserModel, UserAdapter.UsersViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        return UsersViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        holder.bind(getItem(position), onClickListener)
    }

    override fun getItemViewType(position: Int) = position

    class UsersViewHolder(
        private val itemBinding: ItemUserBinding,
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(userModel: UserModel, onClickListener: (userModel: UserModel) -> Unit) {
            itemBinding.run {
                userLogin.text = userModel.login
                avatarUrl = userModel.avatarUrl
                itemUserRoot.setOnClickListener {
                    onClickListener(userModel)
                }
            }
        }

        companion object {
            fun create(parent: ViewGroup): UsersViewHolder {
                val itemBinding = ItemUserBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false)

                return UsersViewHolder(itemBinding)
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<UserModel>() {
            override fun areItemsTheSame(
                oldItem: UserModel,
                newItem: UserModel,
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: UserModel,
                newItem: UserModel,
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}
