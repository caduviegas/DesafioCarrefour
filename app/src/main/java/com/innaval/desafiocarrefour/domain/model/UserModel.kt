package com.innaval.desafiocarrefour.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserModel(
    val id: Long?,
    @SerializedName("avatar_url")
    val avatarUrl: String?,
    val login: String,
    val name: String?,
    val followers: Int?,
    val following: Int?,
    @SerializedName("public_repos")
    val publicRepos: Int?,
) : Parcelable