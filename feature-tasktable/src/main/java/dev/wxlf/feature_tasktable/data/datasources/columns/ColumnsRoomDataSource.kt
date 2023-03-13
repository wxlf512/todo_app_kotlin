package dev.wxlf.feature_tasktable.data.datasources.columns

import dev.wxlf.feature_tasktable.data.entities.ColumnEntity
import dev.wxlf.feature_tasktable.data.entities.ColumnNameAndColor
import dev.wxlf.feature_tasktable.data.room.ColumnsDao

class ColumnsRoomDataSource(private val columnsDao: ColumnsDao) : ColumnsLocalDataSource {
    override suspend fun insertColumn(columnNameAndColor: ColumnNameAndColor): Long =
        columnsDao.insertColumn(columnNameAndColor)

    override suspend fun loadAllColumns(): List<ColumnEntity> =
        columnsDao.loadAllColumns()

    override suspend fun loadColumnInfo(id: Long): ColumnEntity =
        columnsDao.loadColumnInfo(id)

    override suspend fun updateColumn(column: ColumnEntity) =
        columnsDao.updateColumn(column)

    override suspend fun deleteColumn(column: ColumnEntity) =
        columnsDao.deleteColumn(column)
}