package com.innaval.desafiocarrefour.presentation.user.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.innaval.desafiocarrefour.databinding.ItemUserDetailBinding
import com.innaval.desafiocarrefour.domain.model.UserRepositoryModel

class UserDetailsAdapter :
    ListAdapter<UserRepositoryModel, UserDetailsAdapter.UserReposViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserReposViewHolder {
        return UserReposViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: UserReposViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int) = position

    class UserReposViewHolder(
        private val itemBinding: ItemUserDetailBinding,
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(userReposModel: UserRepositoryModel) {
            itemBinding.run {
                userDetailName.text = userReposModel.name
                userDetailLang.text = userReposModel.language
                userDetailWatchersCount.text = "${userReposModel.watchersCount}"
                userDetailForksCount.text = "${userReposModel.forksCount}"
                userDetailStarsCount.text = "${userReposModel.stargazersCount}"
            }
        }

        companion object {
            fun create(parent: ViewGroup): UserReposViewHolder {
                val itemBinding = ItemUserDetailBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false)

                return UserReposViewHolder(itemBinding)
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<UserRepositoryModel>() {
            override fun areItemsTheSame(
                oldItem: UserRepositoryModel,
                newItem: UserRepositoryModel,
            ): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(
                oldItem: UserRepositoryModel,
                newItem: UserRepositoryModel,
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}
