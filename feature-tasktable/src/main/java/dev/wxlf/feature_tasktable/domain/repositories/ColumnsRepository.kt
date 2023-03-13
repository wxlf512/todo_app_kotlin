package dev.wxlf.feature_tasktable.domain.repositories

import dev.wxlf.feature_tasktable.data.entities.ColumnEntity

interface ColumnsRepository {

    suspend fun addColumn(columnName: String, columnColor: String): Long
    suspend fun fetchAllColumns(): List<ColumnEntity>
    suspend fun fetchColumnInfo(id: Long): ColumnEntity
    suspend fun updateColumn(column: ColumnEntity)
    suspend fun deleteColumn(column: ColumnEntity)
}