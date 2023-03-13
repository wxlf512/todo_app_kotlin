package dev.wxlf.feature_tasktable.data.repositories

import dev.wxlf.feature_tasktable.data.datasources.columns.ColumnsLocalDataSource
import dev.wxlf.feature_tasktable.data.entities.ColumnEntity
import dev.wxlf.feature_tasktable.data.entities.ColumnNameAndColor
import dev.wxlf.feature_tasktable.domain.repositories.ColumnsRepository

class ColumnsRepositoryImpl(private val local: ColumnsLocalDataSource) : ColumnsRepository {
    override suspend fun addColumn(columnName: String, columnColor: String): Long =
        local.insertColumn(ColumnNameAndColor(columnName, columnColor))

    override suspend fun fetchAllColumns(): List<ColumnEntity> =
        local.loadAllColumns()

    override suspend fun fetchColumnInfo(id: Long): ColumnEntity =
        local.loadColumnInfo(id)

    override suspend fun updateColumn(column: ColumnEntity) =
        local.updateColumn(column)

    override suspend fun deleteColumn(column: ColumnEntity) =
        local.deleteColumn(column)
}