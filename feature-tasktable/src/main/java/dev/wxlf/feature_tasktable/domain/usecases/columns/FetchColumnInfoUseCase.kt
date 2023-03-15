package dev.wxlf.feature_tasktable.domain.usecases.columns

import dev.wxlf.feature_tasktable.data.entities.ColumnEntity
import dev.wxlf.feature_tasktable.domain.repositories.ColumnsRepository

class FetchColumnInfoUseCase(private val columnsRepository: ColumnsRepository) {
    suspend fun execute(id: Long): ColumnEntity =
        columnsRepository.fetchColumnInfo(id)
}