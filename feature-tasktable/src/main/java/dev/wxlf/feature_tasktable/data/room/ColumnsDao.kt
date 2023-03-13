package dev.wxlf.feature_tasktable.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import dev.wxlf.feature_tasktable.data.entities.ColumnEntity
import dev.wxlf.feature_tasktable.data.entities.ColumnEntity.Companion.COLUMNS_TABLE
import dev.wxlf.feature_tasktable.data.entities.ColumnNameAndColor

@Dao
interface ColumnsDao {

    @Insert(entity = ColumnEntity::class)
    suspend fun insertColumn(columnNameAndColor: ColumnNameAndColor): Long

    @Query("SELECT * FROM $COLUMNS_TABLE")
    suspend fun loadAllColumns(): List<ColumnEntity>

    @Query("SELECT * FROM $COLUMNS_TABLE WHERE id=:id")
    suspend fun loadColumnInfo(id: Long): ColumnEntity

    @Update(entity = ColumnEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateColumn(column: ColumnEntity)

    @Delete(entity = ColumnEntity::class)
    suspend fun deleteColumn(column: ColumnEntity)
}