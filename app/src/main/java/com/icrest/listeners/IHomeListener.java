package com.icrest.listeners;

import com.icrest.model.Country;
import com.icrest.model.ExchangeRates;
import com.icrest.narendraicrest.CountryDetailsPresenter;
import com.icrest.narendraicrest.ExchangeRatesPresenter;

public interface IHomeListener {
    void setDetailsPresenter(CountryDetailsPresenter countryDetailsPresenter);
    void countryServiceFailed();
    void ratesServiceFailed();
    void setRatesPresenter(ExchangeRatesPresenter ratesPresenter,String code);
    void showProgress();
    void dismissProgress();
}
