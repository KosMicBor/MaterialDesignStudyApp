package kosmicbor.giftapp.pictureofthedayapp.domain

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APODRepositoryImpl {
    private val baseURL = "https://api.nasa.gov/"

    fun getAPODRetrofit(): APODRepository {
        val APODRetrofit = Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .client(createHttpClient())
            .build()

        return APODRetrofit.create(APODRepository::class.java)
    }

    private fun createHttpClient(): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))

        return httpClient.build()
    }
}