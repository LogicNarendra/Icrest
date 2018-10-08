package com.icrest.model;

import java.util.Comparator;

public class DescendingOrder implements Comparator<RatesModel>
{

    @Override
    public int compare(RatesModel o1, RatesModel o2) {

        return o2.getCountry().compareTo(o1.getCountry());
    }
}
