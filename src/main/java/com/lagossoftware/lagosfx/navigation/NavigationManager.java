/*
 * LagosFX application framework
 *
 * Copyright(c) 2014 - 2016, Cem Ikta
 */
package com.lagossoftware.lagosfx.navigation;

import com.lagossoftware.lagosfx.mvp.Place;
import com.google.inject.Injector;

/**
 * Navigation manager interface
 *
 * @author Cem Ikta
 */
public interface NavigationManager {

    void setInjector(Injector injector);

    void goTo(Place place);

    Place getCurrentPlace();

    void setCurrentPlace(Place currentPlace);

    void setCurrentPageTitle(String title);

}
