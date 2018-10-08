package com.icrest.narendraicrest;

import com.icrest.listeners.IExchangePresenterListener;
import com.icrest.listeners.IratesPresenter;
import com.icrest.model.ExchangeRates;
import com.icrest.model.RatesModel;

import java.util.ArrayList;

public class ExchangeRatesPresenter implements IratesPresenter{

    private IExchangePresenterListener listener;
    ExchangeRatesPresenter(IExchangePresenterListener iratesPresenter)
    {
        this.listener = iratesPresenter;
    }

    @Override
    public void setExchangeRates(ExchangeRates exchangeRates) {
        mergeExchRates(exchangeRates);
    }

    private void mergeExchRates(ExchangeRates rates)
    {
        ArrayList<RatesModel> ratesArray = new ArrayList<>();
        ratesArray.add(setModel("RON",rates.getRates().getRON()));
        ratesArray.add(setModel("ISK",rates.getRates().getISK()));
        ratesArray.add(setModel("RUB",rates.getRates().getRUB()));
        ratesArray.add(setModel("BRL",rates.getRates().getBRL()));
        ratesArray.add(setModel("TRY",rates.getRates().getTRY()));
        ratesArray.add(setModel("DKK",rates.getRates().getDKK()));
        ratesArray.add(setModel("JPY",rates.getRates().getJPY()));
        ratesArray.add(setModel("EUR",rates.getRates().getEUR()));
        ratesArray.add(setModel("HUF",rates.getRates().getHUF()));
        ratesArray.add(setModel("SGD",rates.getRates().getSGD()));
        ratesArray.add(setModel("PHP",rates.getRates().getPHP()));
        ratesArray.add(setModel("CNY",rates.getRates().getCNY()));
        ratesArray.add(setModel("NOK",rates.getRates().getNOK()));
        ratesArray.add(setModel("SEK",rates.getRates().getSEK()));
        ratesArray.add(setModel("MXN",rates.getRates().getMXN()));
        ratesArray.add(setModel("GBP",rates.getRates().getGBP()));
        ratesArray.add(setModel("IDR",rates.getRates().getIDR()));
        ratesArray.add(setModel("HRK",rates.getRates().getHRK()));
        ratesArray.add(setModel("KRW",rates.getRates().getKRW()));
        ratesArray.add(setModel("ZAR",rates.getRates().getZAR()));
        ratesArray.add(setModel("BGN",rates.getRates().getBGN()));
        ratesArray.add(setModel("CZK",rates.getRates().getCZK()));
        ratesArray.add(setModel("MYR",rates.getRates().getMYR()));
        ratesArray.add(setModel("INR",rates.getRates().getINR()));
        ratesArray.add(setModel("CAD",rates.getRates().getCAD()));
        ratesArray.add(setModel("THB",rates.getRates().getTHB()));
        ratesArray.add(setModel("HKD",rates.getRates().getHKD()));
        ratesArray.add(setModel("NZD",rates.getRates().getNZD()));
        ratesArray.add(setModel("PLN",rates.getRates().getPLN()));
        ratesArray.add(setModel("ILS",rates.getRates().getILS()));
        ratesArray.add(setModel("AUD",rates.getRates().getAUD()));
        ratesArray.add(setModel("USD",rates.getRates().getUSD()));
        ratesArray.add(setModel("CHF",rates.getRates().getCHF()));
        listener.setExchangeRates(ratesArray);

    }
    private RatesModel setModel(String country, float rate)
    {
        RatesModel model = new RatesModel();
        model.setCountry(country);
        model.setRate(rate);
        return model;
    }
}
