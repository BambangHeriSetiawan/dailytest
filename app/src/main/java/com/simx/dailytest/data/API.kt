package com.simx.dailytest.data

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.simx.dailytest.data.models.Albums
import com.simx.dailytest.data.models.Photos
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface API {
    @Headers("Accept: application/json", "Content-type: application/json")
    @GET("albums")
    fun getAlbums(): Observable<List<Albums>>

    @Headers("Accept: application/json", "Content-type: application/json")
    @GET("photos")
    fun getPhotos(@Query("albumId") albumId:Int): Observable<List<Photos>>

    object Factory {
        val retrofit: Retrofit? = null
        /**
         * Config GSON
         * @return
         */
        val gson: Gson
            get() {
                val gsonBuilder = GsonBuilder()
                gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                return gsonBuilder.create()
            }

        fun create(): API {
            return getRetrofitConfig().create(API::class.java)
        }

        /**
         * Config retrofilt
         * @return
         */
        fun getRetrofitConfig(): Retrofit {
            return Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client())
                .build()
        }

        /**
         * Config OkhttpClient and interceptions
         * @return
         */
        private fun client(): OkHttpClient {
            return OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(
                    HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY)
                )

                .build()
        }




    }
}