package com.example.vkeducation.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AppDetailsDao {
    @Query("SELECT * FROM app_details WHERE id = :id")
    suspend fun getAppDetails(id: String): AppDetailsEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAppDetails(appDetails: AppDetailsEntity)

//    @Query("DELETE FROM app_details WHERE id = :id")
//    suspend fun deleteAppDetails(id: String)
}