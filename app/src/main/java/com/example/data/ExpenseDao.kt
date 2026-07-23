package com.example.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseDao {
    @Query("SELECT * FROM expenses ORDER BY dateMillis DESC")
    fun getAllExpenses(): Flow<List<ExpenseEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExpense(expense: ExpenseEntity)

    @Delete
    suspend fun deleteExpense(expense: ExpenseEntity)
    
    @Query("SELECT SUM(amount) FROM expenses WHERE type = 'INCOME'")
    fun getTotalIncome(): Flow<Double?>
    
    @Query("SELECT SUM(amount) FROM expenses WHERE type = 'EXPENSE'")
    fun getTotalExpense(): Flow<Double?>
}
