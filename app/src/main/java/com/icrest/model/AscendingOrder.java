package com.icrest.model;

import java.util.Comparator;

public class AscendingOrder implements Comparator<RatesModel>
{

    @Override
    public int compare(RatesModel o1, RatesModel o2) {

        return o1.getCountry().compareTo(o2.getCountry());
    }
}
