package com.icrest.narendraicrest;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.icrest.listeners.IHomeListener;
import com.icrest.model.Country;
import com.icrest.model.ExchangeRates;

public class HomeActivity extends AppCompatActivity implements IHomeListener {

    private HomePresenter presenter;
    private EditText searchTxt;
    private ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        searchTxt = findViewById(R.id.search_view);
        presenter = new HomePresenter(this);
        loadErrorFragment("Search Country name to view country detais");

        dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setMessage("Connecting...");
    }

    public void loadErrorFragment(String message){

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.load_fragment,ErrorFragment.newInstance(message)).commit();
    }
    /*
    * XML OnClick for search button
    * */
    public void onSubmitCountry(View view)
    {
        if(searchTxt.getText().toString().trim().length()>2) {
            presenter.loadDetailsFragment(new CountryDetailsFragment());
        }
        else
            Toast.makeText(this, "Enter valid country name", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setDetailsPresenter(CountryDetailsPresenter countryDetailsPresenter) {

        presenter.setDetailsPresenter(countryDetailsPresenter, searchTxt.getText().toString().trim());
    }

    @Override
    public void countryServiceFailed() {

    }
    
    @Override
    public void ratesServiceFailed() {
        loadErrorFragment("Something went wrong. Please try later");
    }

    public void getCountryCode()
    {
        presenter.loadExchangeFragment();
    }

    @Override
    public void setRatesPresenter(ExchangeRatesPresenter ratesPresenter, String countryCode) {
        presenter.setExcRatesPresenter(ratesPresenter,countryCode);
    }

    @Override
    public void showProgress() {
        dialog.show();
    }

    @Override
    public void dismissProgress() {
        dialog.cancel();
    }
}
