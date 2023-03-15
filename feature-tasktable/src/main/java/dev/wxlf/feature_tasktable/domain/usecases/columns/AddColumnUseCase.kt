package dev.wxlf.feature_tasktable.domain.usecases.columns

import dev.wxlf.feature_tasktable.domain.repositories.ColumnsRepository

class AddColumnUseCase(private val columnsRepository: ColumnsRepository) {
    suspend fun execute(columnName: String, columnColor: String): Long =
        columnsRepository.addColumn(columnName, columnColor)
}