package br.com.fiap.locaweb.service

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val token = TokenManager.getToken()

        val newRequest = originalRequest.newBuilder()
            .apply {
                if (token != null) {
                    header("Authorization", "Bearer $token")
                }
            }
            .addHeader("Content-Type", "application/json")
            .build()

        return chain.proceed(newRequest)
    }
}

