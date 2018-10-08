package com.icrest.listeners;

import com.icrest.model.Country;
import com.icrest.model.ExchangeRates;

public interface IDetailsPresenter {

    void setCountryDetails(Country countryDetails);
    String setCountryCode();

}
