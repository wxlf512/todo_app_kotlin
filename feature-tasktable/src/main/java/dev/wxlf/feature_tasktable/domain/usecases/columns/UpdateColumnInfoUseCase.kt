package dev.wxlf.feature_tasktable.domain.usecases.columns

import dev.wxlf.feature_tasktable.data.entities.ColumnEntity
import dev.wxlf.feature_tasktable.domain.repositories.ColumnsRepository

class UpdateColumnInfoUseCase(private val columnsRepository: ColumnsRepository) {
    suspend fun execute(column: ColumnEntity) =
        columnsRepository.updateColumn(column)
}