package dev.wxlf.feature_tasktable.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.wxlf.feature_tasktable.domain.repositories.TasksRepository
import dev.wxlf.feature_tasktable.domain.usecases.tasks.AddTaskUseCase
import dev.wxlf.feature_tasktable.domain.usecases.tasks.DeleteTaskUseCase
import dev.wxlf.feature_tasktable.domain.usecases.tasks.FetchAllTasksUseCase
import dev.wxlf.feature_tasktable.domain.usecases.tasks.FetchTaskUseCase
import dev.wxlf.feature_tasktable.domain.usecases.tasks.UpdateTaskUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class TasksUseCaseModule {

    @Provides
    @Singleton
    fun provideAddTaskUseCase(tasksRepository: TasksRepository): AddTaskUseCase =
        AddTaskUseCase(tasksRepository)

    @Provides
    @Singleton
    fun provideDeleteTaskUseCase(tasksRepository: TasksRepository): DeleteTaskUseCase =
        DeleteTaskUseCase(tasksRepository)

    @Provides
    @Singleton
    fun provideFetchAllTasksUseCase(tasksRepository: TasksRepository): FetchAllTasksUseCase =
        FetchAllTasksUseCase(tasksRepository)

    @Provides
    @Singleton
    fun provideFetchTaskUseCase(tasksRepository: TasksRepository): FetchTaskUseCase =
        FetchTaskUseCase(tasksRepository)

    @Provides
    @Singleton
    fun provideUpdateTaskUseCase(tasksRepository: TasksRepository): UpdateTaskUseCase =
        UpdateTaskUseCase(tasksRepository)
}