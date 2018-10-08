package com.icrest.narendraicrest;

import com.icrest.listeners.IDetailsPresenter;
import com.icrest.model.Country;

public class CountryDetailsPresenter implements IDetailsPresenter {
    IDetailsPresenter detailsPresenter;
    CountryDetailsPresenter(IDetailsPresenter iDetailsPresenter)
    {
        this.detailsPresenter = iDetailsPresenter;
    }

    @Override
    public void setCountryDetails(Country countryDetails) {
        detailsPresenter.setCountryDetails(countryDetails);
    }

    @Override
    public String setCountryCode() {

        return detailsPresenter.setCountryCode();
    }
}
