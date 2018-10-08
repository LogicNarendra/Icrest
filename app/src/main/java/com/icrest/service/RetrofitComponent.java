package com.icrest.service;

import com.icrest.narendraicrest.HomePresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {RetrofitClientModule.class})
public interface RetrofitComponent {
    void inject(HomePresenter presenter);
}
