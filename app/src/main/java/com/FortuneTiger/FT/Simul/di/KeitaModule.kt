package com.FortuneTiger.FT.Simul.di

import android.content.Context
import android.media.MediaPlayer
import com.FortuneTiger.FT.Simul.data.KeitaApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
object KeitaModule {

    @[Singleton Provides]
    fun provideRetrofit() : KeitaApi = Retrofit.Builder()
        .addConverterFactory(ScalarsConverterFactory.create())
        .baseUrl("https://example.com/")
        .build()
        .create(KeitaApi::class.java)

    @[Singleton Provides]
    fun provideMediaPlayer(@ApplicationContext context : Context) : MediaPlayer = MediaPlayer.create(
        context, com.FortuneTiger.FT.Simul.theme.R.raw.soundtrack_compressed
    )

}