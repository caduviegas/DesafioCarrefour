package com.innaval.desafiocarrefour.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserRepositoryModel(
val name: String,
@SerializedName("watchers_count")
val watchersCount: Int,
@SerializedName("forks_count")
val forksCount: Int,
@SerializedName("stargazers_count")
val stargazersCount: Int,
val language: String,
) : Parcelable