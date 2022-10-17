package com.ekalyoncu.weatherapp.service.interceptor

import com.ekalyoncu.weatherapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class WeatherInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val apiKeyRequest = originalRequest
            .newBuilder()
            .header("X-Api-Key", BuildConfig.API_KEY)
            .build()

        return chain.proceed(apiKeyRequest)
    }
}