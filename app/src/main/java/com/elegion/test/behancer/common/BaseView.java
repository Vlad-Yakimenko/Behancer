package com.elegion.test.behancer.common;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(value = SkipStrategy.class)
public interface BaseView extends MvpView {

    void showRefresh();

    void hideRefresh();

    void showError();
}
