package com.icrest.narendraicrest;


import android.graphics.drawable.Drawable;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ahmadrosid.svgloader.SvgLoader;
import com.icrest.listeners.IDetailsPresenter;
import com.icrest.model.Country;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class CountryDetailsFragment extends Fragment implements View.OnClickListener,IDetailsPresenter {


    private TextView _CountryCode, _CountryName, _CountryDomain, _Capital, _Population, C_Code, C_Name, C_Symbol;
    private ImageView _CountryFlag;
    private CountryDetailsPresenter detailsPresenter;
    public CountryDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_country_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        _CountryCode = view.findViewById(R.id.country_code);
        _CountryName = view.findViewById(R.id.country_name);
        _CountryDomain = view.findViewById(R.id.country_domain);
        _Capital = view.findViewById(R.id.country_capital);
        _Population = view.findViewById(R.id.country_population);
        C_Code = view.findViewById(R.id.currency_code);
        C_Code.setOnClickListener(this);
        C_Name = view.findViewById(R.id.currency_name);
        C_Symbol = view.findViewById(R.id.currency_symbol);
        _CountryFlag = view.findViewById(R.id.country_flag);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        detailsPresenter = new CountryDetailsPresenter(this);
        try {
            ((HomeActivity)getActivity()).setDetailsPresenter(detailsPresenter);
        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
        }

    }

    @Override
    public void setCountryDetails(Country countryDetails) {
        //.makeText(getActivity(), countryDetails.getName(), Toast.LENGTH_SHORT).show();
        _CountryCode.setText(seCallingCodes(countryDetails.getCallingCodes()));
        _CountryName.setText(countryDetails.getName());
        _CountryDomain.setText(seCallingCodes(countryDetails.getTopLevelDomain()));
        _Capital.setText(countryDetails.getCapital());
        _Population.setText(String.valueOf(countryDetails.getPopulation()));
        C_Code.setText(countryDetails.getCurrencies().get(0).getCode());
        C_Name.setText(countryDetails.getCurrencies().get(0).getName());
        C_Symbol.setText(countryDetails.getCurrencies().get(0).getSymbol());
        loadSVGImage(countryDetails.getFlag());
    }

    private void loadSVGImage(String url)
    {
        SvgLoader.pluck()
                .with(getActivity())
                .setPlaceHolder(R.mipmap.ic_launcher, R.mipmap.ic_launcher)
                .load(url, _CountryFlag);
    }
    private String seCallingCodes(List<String> codes)
    {
        String code = "";
        for (String code1:codes ) {
            code = code+code1+", ";
        }
        return code.length()>2 ? code.substring(0,code.length()-2) : "";
    }

    @Override
    public void onClick(View v) {
        ((HomeActivity)getActivity()).getCountryCode();
    }

    @Override
    public String setCountryCode() {
        return C_Code.getText().toString();
    }
}
