package com.sample.gitrepos.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sample.gitrepos.models.Timestamp

@Dao
interface TimestampDao {

    @Query("SELECT * from `timestamp`")
    suspend fun getLastSavedTimeStamp(): Timestamp?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTimeStamp(timestamp: Timestamp)

    @Query("DELETE from `timestamp`")
    suspend fun deleteTimeStamp()
}