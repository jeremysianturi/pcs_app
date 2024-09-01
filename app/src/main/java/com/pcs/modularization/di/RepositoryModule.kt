package com.pcs.modularization.di

import com.pcs.data.repoimpl.UserImpl
import com.pcs.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule{

    @Binds
    fun bindUserRepository(userImpl: UserImpl): UserRepository

}