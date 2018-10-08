package com.icrest.narendraicrest;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.icrest.listeners.IHomeListener;
import com.icrest.listeners.IPresenterListener;
import com.icrest.model.Country;
import com.icrest.model.ExchangeRates;
import com.icrest.service.ConstantAPIs;
import com.icrest.service.DaggerRetrofitComponent;
import com.icrest.service.IAPIListeners;
import com.icrest.service.RetrofitClientModule;
import com.icrest.service.RetrofitComponent;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;




public class HomePresenter implements IPresenterListener
{
    private IHomeListener listener;
    private RetrofitComponent countryComponent;
    private RetrofitComponent RatesComponent;
    private CountryDetailsPresenter detailsPresenter;
    private ExchangeRatesPresenter ratesPresenter;
    private HomeActivity context;

    @Inject
    Retrofit retrofitClient;



    public HomePresenter(HomeActivity homeListener)
    {
        context = homeListener;
        this.listener = (IHomeListener)homeListener;

    }

    public void loadDetailsFragment(Fragment fragment)
    {
        FragmentManager manager = context.getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.load_fragment, fragment).commit();
        manager.popBackStack();                                         //Removing fragment from back stack
    }
    public void loadExchangeFragment()
    {
        FragmentManager manager = context.getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.load_fragment, ExchangeRatesFragment.getInstance(detailsPresenter.setCountryCode())).commit();
        transaction.addToBackStack(ExchangeRatesFragment.class.getName());
    }

    @Override
    public void setDetailsPresenter(CountryDetailsPresenter detailsPresenter,String country) {
        this.detailsPresenter = detailsPresenter;
        listener.showProgress();
        createCountryComponent();
        countryServiceCall(country);
    }

    @Override
    public void setExcRatesPresenter(ExchangeRatesPresenter excRaesPresenter,String code) {
        this.ratesPresenter=excRaesPresenter;
        listener.showProgress();
        exchangeServiceCall(code);
    }

    @Override
    public void setCountryCodeAPI() {

    }

    private void createCountryComponent(){
        countryComponent = DaggerRetrofitComponent.builder()
                .retrofitClientModule(new RetrofitClientModule(ConstantAPIs.COUNTRY_BASE_URL))
                .build();
        countryComponent.inject(this);
    }

    private void countryServiceCall(String countryName)
    {
        IAPIListeners serviceListener = retrofitClient.create(IAPIListeners.class);
        Call<List<Country>> countryService = serviceListener.getCountryDetails(countryName,true);
        countryService.enqueue(new Callback<List<Country>>() {
            @Override
            public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {

                List<Country> country = response.body();
                if(country!=null)
                    detailsPresenter.setCountryDetails(country.get(0));
                else
                    listener.countryServiceFailed();

                listener.dismissProgress();
            }

            @Override
            public void onFailure(Call<List<Country>> call, Throwable t) {
                listener.countryServiceFailed();
                listener.dismissProgress();
            }
        });
    }

    private void exchangeServiceCall(String code)
    {
        IAPIListeners serviceListener = retrofitClient.create(IAPIListeners.class);
        String excURL = "https://api.exchangeratesapi.io/latest?base=" + code;
        Call<ExchangeRates> countryService = serviceListener.getExRates(excURL);
        countryService.enqueue(new Callback<ExchangeRates>() {
            @Override
            public void onResponse(Call<ExchangeRates> call, Response<ExchangeRates> response) {

                ExchangeRates country = response.body();
                if(country!=null)
                    ratesPresenter.setExchangeRates(country);
                else
                    listener.ratesServiceFailed();
                listener.dismissProgress();
            }

            @Override
            public void onFailure(Call<ExchangeRates> call, Throwable t) {
                listener.ratesServiceFailed();
                listener.dismissProgress();
            }
        });
    }
}
