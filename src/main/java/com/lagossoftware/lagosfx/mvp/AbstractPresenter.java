/*
 * LagosFX application framework
 *
 * Copyright(c) 2014 - 2016, Cem Ikta
 */
package com.lagossoftware.lagosfx.mvp;

import com.lagossoftware.lagosfx.navigation.NavigationManager;

/**
 * Presenter interface
 *
 * @param <V> view
 *
 * @author Cem Ikta
 */
public abstract class AbstractPresenter<V extends View> implements Presenter<V> {

    protected final NavigationManager navigationManager;
    protected final V view;

    public AbstractPresenter(final NavigationManager navigationManager, final V view) {
        this.navigationManager = navigationManager;
        this.view = view;
    }

    @Override
    public V getView() {
        return this.view;
    }

}
