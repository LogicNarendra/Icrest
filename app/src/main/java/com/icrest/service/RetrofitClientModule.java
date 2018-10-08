package com.icrest.service;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class RetrofitClientModule {

    String baseURL;
    public RetrofitClientModule(String base_url) {
        this.baseURL = base_url;
    }

    @Provides
    @Singleton
    HttpLoggingInterceptor provideIntercepter() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }
    @Provides
    @Singleton
    OkHttpClient provideOkhttpClient(HttpLoggingInterceptor intercepter) {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.addInterceptor(intercepter);
        return client.build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseURL)
                .client(okHttpClient)
                .build();
    }
}

