package com.haberturm.tutuinternship.di

import android.app.Application
import com.haberturm.tutuinternship.HeroDatabase
import com.haberturm.tutuinternship.data.db.DataSource
import com.haberturm.tutuinternship.data.db.DataSourceImpl
import com.haberturm.tutuinternship.data.repositories.detailScreen.DetailRepository
import com.haberturm.tutuinternship.data.repositories.detailScreen.DetailRepositoryImpl
import com.haberturm.tutuinternship.data.repositories.listScreen.ListScreenRepository
import com.haberturm.tutuinternship.data.repositories.listScreen.ListScreenRepositoryImpl
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSqlDriver(app: Application): SqlDriver {
        return AndroidSqliteDriver(
            schema = HeroDatabase.Schema,
            context = app,
            name = "hero.db"
        )
    }

    @Provides
    @Singleton
    fun provideDataSource(driver: SqlDriver): DataSource {
        return DataSourceImpl(HeroDatabase(driver))
    }

    @Provides
    @Singleton
    fun provideListScreenRepository(db: DataSource): ListScreenRepository {
        return ListScreenRepositoryImpl(db)
    }

    @Provides
    @Singleton
    fun provideDetailRepository(db: DataSource): DetailRepository {
        return DetailRepositoryImpl(db)
    }
}