package com.sample.gitrepos.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "repositories")
data class GitReposModel(
    @PrimaryKey
    @field:SerializedName("author")
    val author: String,
    @field:SerializedName("avatar")
    val avatar: String,
    val currentPeriodStars: Int? = 0,
    val description: String? = "",
    @field:SerializedName("forks")
    val forks: Int,
    @field:SerializedName("language")
    val language: String?,
    @field:SerializedName("languageColor")
    val languageColor: String?,
    @field:SerializedName("name")
    val name: String,
    @field:SerializedName("stars")
    val stars: Int,
    @field:SerializedName("url")
    val url: String
)