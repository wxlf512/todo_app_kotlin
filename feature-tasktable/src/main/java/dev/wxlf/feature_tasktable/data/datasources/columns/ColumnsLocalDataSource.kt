package dev.wxlf.feature_tasktable.data.datasources.columns

import dev.wxlf.feature_tasktable.data.entities.ColumnEntity
import dev.wxlf.feature_tasktable.data.entities.ColumnNameAndColor

interface ColumnsLocalDataSource {

    suspend fun insertColumn(columnNameAndColor: ColumnNameAndColor): Long
    suspend fun loadAllColumns(): List<ColumnEntity>
    suspend fun loadColumnInfo(id: Long): ColumnEntity
    suspend fun updateColumn(column: ColumnEntity)
    suspend fun deleteColumn(column: ColumnEntity)
}