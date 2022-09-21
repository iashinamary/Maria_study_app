package com.example.maria_study_app

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.scope.get
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val module = module {

    single<MyDataBase>{
        Room.databaseBuilder(androidContext(), MyDataBase::class.java, "my DB").allowMainThreadQueries().build()
    }

    single {
        Retrofit.Builder().baseUrl("https://catfact.ninja/").addConverterFactory(GsonConverterFactory.create()).build()
    }

    single {
        Repository(get<MyDataBase>().getMyDao())
    }

    single {
        androidContext().getSharedPreferences("Prefs", Context.MODE_PRIVATE)
    }

    single {
        get<Retrofit>().create(Api::class.java)
    }

    single {
        ApiService(get())
    }

    viewModel {
        FragmentOneVm(get())
    }
}