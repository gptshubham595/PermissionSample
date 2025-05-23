package com.shubham.permissions.di

import com.shubham.permissions.core.data.repositories.PermissionRepositoryImpl
import com.shubham.permissions.core.domain.repositories.PermissionRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface AppModule {
    @Binds
    fun bindPermissionRepo(impl: PermissionRepositoryImpl): PermissionRepository
}