package com.icrest.listeners;

import com.icrest.model.RatesModel;

import java.util.ArrayList;

public interface IExchangePresenterListener {
    void setExchangeRates(ArrayList<RatesModel> ratesArray);
}
