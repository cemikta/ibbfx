/*
 * LagosFX application framework
 *
 * Copyright(c) 2014 - 2016, Cem Ikta
 */
package com.lagossoftware.lagosfx.navigation;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import com.lagossoftware.ibbfx.app.AppPlaces;
import com.lagossoftware.ibbfx.app.PresenterMapper;
import com.lagossoftware.lagosfx.mvp.AppLayout;
import com.lagossoftware.lagosfx.mvp.HasParameters;
import com.lagossoftware.lagosfx.mvp.Place;
import com.lagossoftware.lagosfx.mvp.Presenter;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Navigation manager implementation
 *
 * @author Cem Ikta
 */
@Singleton
public class NavigationManagerImpl implements NavigationManager {

    private final Logger logger;
    private Injector injector;
    private PresenterMapper presenterMapper;
    private Place currentPlace;

    private AppLayout appLayout;

    @Inject
    public NavigationManagerImpl(Logger logger, PresenterMapper presenterMapper,
            AppLayout appLayout) {
        this.logger = logger;
        this.presenterMapper = presenterMapper;
        this.appLayout = appLayout;
    }

    @Override
    public void goTo(Place place) {
        logger.log(Level.INFO, "navigation manager go to place: {0}", place.getName());
        if (currentPlace != place) {
            Presenter presenter = injector.getInstance(
                    presenterMapper.findPresenter(place.getName()));

            currentPlace = place;

            if (presenter instanceof HasParameters) {
                HasParameters presenterHasParameters = (HasParameters) presenter;
                presenterHasParameters.setParameters(place.getParameters());
            }

            if (place.getName() == AppPlaces.LOGIN) {
                appLayout.setAppHeader(true);
                presenter.start(appLayout.getAppContent());
            } else {
                appLayout.setAppHeader(false);
                presenter.start(appLayout.getAppContent());
            }

        }
    }

    @Override
    public void setInjector(Injector injector) {
        this.injector = injector;
    }

    @Override
    public Place getCurrentPlace() {
        return this.currentPlace;
    }

    @Override
    public void setCurrentPlace(Place currentPlace) {
        this.currentPlace = currentPlace;
    }
    
    @Override
    public void setCurrentPageTitle(String title) {
        // TODO set app page title over app presenter!
        this.appLayout.getAppHeader().setTitleText(title);
    }

}
