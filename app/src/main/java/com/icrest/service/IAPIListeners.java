package com.icrest.service;

import com.icrest.model.Country;
import com.icrest.model.ExchangeRates;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface IAPIListeners {

    @GET(ConstantAPIs.COUNTRY_CONTROLLER_URL)
    Call<List<Country>> getCountryDetails(@Path("name")String country, @Query("fullText")boolean status);

    @GET
    Call<ExchangeRates> getExRates(@Url String dynamicURL);
}
