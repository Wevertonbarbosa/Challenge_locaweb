package br.com.fiap.locaweb.service

import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Url

interface ApiService {
    @GET
    suspend fun get(
        @Url url: String,
        @HeaderMap headers: Map<String, String>
    ): ResponseBody

    @POST
    suspend fun post(
        @Url url: String,
        @HeaderMap headers: Map<String, String>,
        @Body body: RequestBody
    ): ResponseBody

    @PUT
    suspend fun put(
        @Url url: String,
        @HeaderMap headers: Map<String, String>,
        @Body body: RequestBody
    ): ResponseBody

    @DELETE
    suspend fun delete(
        @Url url: String,
        @HeaderMap headers: Map<String, String>
    ): ResponseBody
}

object RetrofitClient {
    private var token: String? = TokenManager.getToken()

//    fun setToken(newToken: String) {
//        token = newToken
//    }

    private val client = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()

            token?.let {
                requestBuilder.addHeader("Authorization", "Bearer $it")
            }

            val request = requestBuilder.build()
            chain.proceed(request)
        }
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:8080/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    val apiService: ApiService = retrofit.create(ApiService::class.java)

    suspend fun get(
        url: String,
        headers: Map<String, String> = emptyMap()
    ): ResponseBody {
        return apiService.get(url, headers)
    }

    suspend fun post(
        url: String,
        headers: Map<String, String> = emptyMap(),
        body: RequestBody
    ): ResponseBody {
        return apiService.post(url, headers, body)
    }

    suspend fun put(
        url: String,
        headers: Map<String, String> = emptyMap(),
        body: RequestBody
    ): ResponseBody {
        return apiService.put(url, headers, body)
    }

    suspend fun delete(
        url: String,
        headers: Map<String, String> = emptyMap()
    ): ResponseBody {
        return apiService.delete(url, headers)
    }
}
