package dev.wxlf.feature_tasktable.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.wxlf.feature_tasktable.domain.repositories.ColumnsRepository
import dev.wxlf.feature_tasktable.domain.usecases.columns.AddColumnUseCase
import dev.wxlf.feature_tasktable.domain.usecases.columns.DeleteColumnUseCase
import dev.wxlf.feature_tasktable.domain.usecases.columns.FetchAllColumnsUseCase
import dev.wxlf.feature_tasktable.domain.usecases.columns.FetchColumnInfoUseCase
import dev.wxlf.feature_tasktable.domain.usecases.columns.UpdateColumnInfoUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ColumnsUseCaseModule {

    @Provides
    @Singleton
    fun provideAddColumnUseCase(columnsRepository: ColumnsRepository): AddColumnUseCase =
        AddColumnUseCase(columnsRepository)

    @Provides
    @Singleton
    fun provideDeleteColumnUseCase(columnsRepository: ColumnsRepository): DeleteColumnUseCase =
        DeleteColumnUseCase(columnsRepository)

    @Provides
    @Singleton
    fun provideFetchAllColumnsUseCase(columnsRepository: ColumnsRepository): FetchAllColumnsUseCase =
        FetchAllColumnsUseCase(columnsRepository)

    @Provides
    @Singleton
    fun provideFetchColumnInfoUseCase(columnsRepository: ColumnsRepository): FetchColumnInfoUseCase =
        FetchColumnInfoUseCase(columnsRepository)

    @Provides
    @Singleton
    fun provideUpdateColumnInfoUseCase(columnsRepository: ColumnsRepository): UpdateColumnInfoUseCase =
        UpdateColumnInfoUseCase(columnsRepository)
}