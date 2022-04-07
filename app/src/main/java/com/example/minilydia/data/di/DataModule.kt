package com.example.minilydia.data.di

import android.content.Context
import androidx.paging.RxPagedListBuilder
import androidx.room.Room
import com.example.minilydia.BuildConfig
import com.example.minilydia.data.DataConstants
import com.example.minilydia.data.DataConstants.BASE_URL
import com.example.minilydia.data.DataConstants.DATABASE_NAME
import com.example.minilydia.data.api.IUsersApi
import com.example.minilydia.data.repository.UsersRepositoryImpl
import com.example.minilydia.data.repository.local.UsersLocalRepositoryImpl
import com.example.minilydia.data.repository.remote.UsersRemoteRepositoryImpl
import com.example.minilydia.domain.model.User
import com.example.minilydia.data.paging.UsersBoundaryCallback
import com.example.minilydia.domain.repository.IUsersRepository
import com.example.minilydia.domain.repository.local.IUsersLocalRepository
import com.example.minilydia.domain.repository.remote.IUsersRemoteRepository
import com.example.minilydia.data.room.UsersLocalDB
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {
    single<IUsersLocalRepository> { UsersLocalRepositoryImpl(get()) }
    single { provideUsersLocalDB(androidContext()) }

    single<IUsersRemoteRepository> { UsersRemoteRepositoryImpl(get()) }
    single { provideUsersApi(get()) }
    single { provideRetrofit(get()) }
    single { provideOkHttpClient() }

    single<IUsersRepository> { UsersRepositoryImpl(get(), get()) }
    single { provideRxPagedListBuilder(get(), get()) }
    single { UsersBoundaryCallback(get(), get()) }

}

fun provideUsersLocalDB(context: Context): UsersLocalDB = Room.databaseBuilder(
    context,
    UsersLocalDB::class.java,
    DATABASE_NAME
).fallbackToDestructiveMigration().build()

fun provideRxPagedListBuilder(
    usersLocalRepository: IUsersLocalRepository,
    usersBoundaryCallback: UsersBoundaryCallback
): RxPagedListBuilder<Int, User> =
    RxPagedListBuilder(
        usersLocalRepository.getUsersFromLocalDB(),
        DataConstants.DEFAULT_PAGE_SIZE
    ).setBoundaryCallback(usersBoundaryCallback)

fun provideUsersApi(retrofit: Retrofit): IUsersApi = retrofit.create(IUsersApi::class.java)

fun provideRetrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    .client(client)
    .build()

fun provideOkHttpClient(): OkHttpClient = OkHttpClient().newBuilder()
    .addInterceptor(HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
        else HttpLoggingInterceptor.Level.NONE
    })
    .build()