package dev.wxlf.feature_tasktable.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.wxlf.feature_tasktable.data.datasources.columns.ColumnsLocalDataSource
import dev.wxlf.feature_tasktable.data.datasources.columns.ColumnsRoomDataSource
import dev.wxlf.feature_tasktable.data.datasources.tasks.TasksLocalDataSource
import dev.wxlf.feature_tasktable.data.datasources.tasks.TasksRoomDataSource
import dev.wxlf.feature_tasktable.data.repositories.ColumnsRepositoryImpl
import dev.wxlf.feature_tasktable.data.repositories.TasksRepositoryImpl
import dev.wxlf.feature_tasktable.data.room.TasksDatabase
import dev.wxlf.feature_tasktable.domain.repositories.ColumnsRepository
import dev.wxlf.feature_tasktable.domain.repositories.TasksRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideTasksDatabase(@ApplicationContext context: Context): TasksDatabase =
        Room.databaseBuilder(
            context = context,
            TasksDatabase::class.java,
            "tasks_database"
        ).build()

    @Provides
    @Singleton
    fun provideColumnsRoomDataSource(tasksDatabase: TasksDatabase): ColumnsLocalDataSource =
        ColumnsRoomDataSource(tasksDatabase.columnsDao())

    @Provides
    @Singleton
    fun provideTasksRoomDataSource(tasksDatabase: TasksDatabase): TasksLocalDataSource =
        TasksRoomDataSource(tasksDatabase.tasksDao())

    @Provides
    @Singleton
    fun provideColumnsRepository(local: ColumnsLocalDataSource): ColumnsRepository =
        ColumnsRepositoryImpl(local)

    @Provides
    @Singleton
    fun provideTasksRepository(local: TasksLocalDataSource): TasksRepository =
        TasksRepositoryImpl(local)
}