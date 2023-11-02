package es.clean.architecture.data.di


import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.clean.architecture.data.common.BASE_URL
import es.clean.architecture.data.remote.characters.datastore.RemoteCharacterDataStoreImpl
import es.clean.architecture.data.remote.characters.repository.CharactersRepository
import es.clean.architecture.data.service.RickyMortyService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): RickyMortyService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(provideOkHttpClient())
            .build().create(RickyMortyService::class.java)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }

   /* @Provides
    @Singleton
    fun provideCharacterRepository(remoteService: RickyMortyService): CharactersDataStore {
        return RemoteCharacterDataStoreImpl(remoteService)
    }*/
    @Provides
    @Singleton
    fun provideCharacterRepository(remoteService: RickyMortyService): CharactersRepository {
        return RemoteCharacterDataStoreImpl(remoteService)
    }

}