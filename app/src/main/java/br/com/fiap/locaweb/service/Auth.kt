package br.com.fiap.locaweb.service

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST


class Auth {


    data class LoginRequest(
        val login: String,
        val password: String
    )

    data class LoginResponse(val token: String)

    interface AuthService {
        @POST("auth")
        suspend fun login(@Body request: LoginRequest): LoginResponse
    }

    object RetrofitClient {
        private val client = OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor())
            .build()

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        val authService: AuthService = retrofit.create(AuthService::class.java)
    }


}