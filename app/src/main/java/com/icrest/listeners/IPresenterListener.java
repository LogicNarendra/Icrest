package com.icrest.listeners;

import com.icrest.narendraicrest.CountryDetailsPresenter;
import com.icrest.narendraicrest.ExchangeRatesPresenter;

public interface IPresenterListener {
    void setDetailsPresenter(CountryDetailsPresenter detailsPresenter,String country);
    void setExcRatesPresenter(ExchangeRatesPresenter excRaesPresenter, String code);
    void setCountryCodeAPI();
}
