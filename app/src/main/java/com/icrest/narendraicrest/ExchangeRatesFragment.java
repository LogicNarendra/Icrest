package com.icrest.narendraicrest;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.icrest.listeners.IExchangePresenterListener;
import com.icrest.listeners.IratesPresenter;
import com.icrest.model.AscendingOrder;
import com.icrest.model.DescendingOrder;
import com.icrest.model.ExchangeRates;
import com.icrest.model.RatesModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;


/**
 * A simple {@link Fragment} subclass.
 */
public class ExchangeRatesFragment extends Fragment implements IExchangePresenterListener, View.OnClickListener {

private LinearLayout exchangeView;
    private ExchangeRatesPresenter ratesPresenter;
    private static final String BUNDLE_CODE = "country_code";
    ArrayList<RatesModel> ratesArray;
    private boolean isAscending = true;
    public ExchangeRatesFragment() {
        // Required empty public constructor
    }

    public static ExchangeRatesFragment getInstance(String code)
    {
        ExchangeRatesFragment ratesFragment = new ExchangeRatesFragment();
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_CODE,code);
        ratesFragment.setArguments(bundle);
        return ratesFragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_exchange_rates, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        exchangeView = view.findViewById(R.id.rates_view);
        ImageView sortIcon = view.findViewById(R.id.sort);
        sortIcon.setOnClickListener(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ratesPresenter = new ExchangeRatesPresenter(this);
        Bundle bundle = getArguments();
        if(bundle!=null)
            ((HomeActivity)getActivity()).setRatesPresenter(ratesPresenter,bundle.getString(BUNDLE_CODE));

    }



    @Override
    public void setExchangeRates(ArrayList<RatesModel> ratesArray) {
        this.ratesArray = ratesArray;
        Collections.sort(this.ratesArray, new AscendingOrder());
        setExchangeRates();
    }
    private void setExchangeRates()
    {
        exchangeView.removeAllViews();
        for (RatesModel model: ratesArray ) {
            exchangeView.addView(addRate(model));
        }
    }

    private View addRate(RatesModel model)
    {
        LayoutInflater inflater = LayoutInflater.from(getActivity());

        View view = inflater.inflate(R.layout.rate_layout,null);
        TextView codeView = view.findViewById(R.id.currency_code);
        TextView rateView = view.findViewById(R.id.currency_rate);
        view.setOnClickListener(this);
        codeView.setText(model.getCountry());
        rateView.setText(String.valueOf(model.getRate()));
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sort:
                if (!isAscending)
                    Collections.sort(this.ratesArray, new AscendingOrder());
                else
                    Collections.sort(this.ratesArray, new DescendingOrder());
                isAscending = !isAscending;
                setExchangeRates();
                break;
            default:
                TextView textView = v.findViewById(R.id.currency_code);
                TextView textView1 = v.findViewById(R.id.currency_rate);
                calculateDialog(textView.getText().toString(), Float.valueOf(textView1.getText().toString()));
                break;
        }
    }

    private void calculateDialog(String code, final float rate)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(false);
        builder.setTitle("Currency Calculator");
        LayoutInflater inflater = LayoutInflater.from(getActivity());

        View view = inflater.inflate(R.layout.calculate_layout,null);
        builder.setView(view);
        final EditText yourView = view.findViewById(R.id.your_currency);
        final TextView otherView = view.findViewById(R.id.other_currency);
        TextView yourCodeView = view.findViewById(R.id.your_code);
        TextView otherCodeView = view.findViewById(R.id.other_code);
        final TextView totalView = view.findViewById(R.id.total_currency);

        yourView.setHint(getArguments().getString(BUNDLE_CODE));
        yourCodeView.setText(getArguments().getString(BUNDLE_CODE));
        otherView.setHint(code);
        otherCodeView.setText(code);
        otherView.setText(String.valueOf(rate));

        yourView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(yourView.getText().toString().trim().length()>=1) {
                    Float yourValue = Float.valueOf(yourView.getText().toString().trim());
                    Float total = yourValue * rate;
                    totalView.setText(String.valueOf(total));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        Dialog dialog = builder.create();
        dialog.show();

    }
}
