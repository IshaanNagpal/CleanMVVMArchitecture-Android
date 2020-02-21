package com.sample.gitrepos.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "timestamp")
data class Timestamp(
    @PrimaryKey
    @field:SerializedName("time")
    val timesTamp: Long)