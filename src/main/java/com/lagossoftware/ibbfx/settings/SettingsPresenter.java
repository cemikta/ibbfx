/*
 * Copyright (C) 2016 IBB Management Project, Cem Ikta
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.lagossoftware.ibbfx.settings;

import com.google.inject.Inject;
import com.lagossoftware.ibbfx.IbbManagement;
import com.lagossoftware.ibbfx.app.AppPlaces;
import com.lagossoftware.ibbfx.app.I18n;
import com.lagossoftware.lagosfx.mvp.AbstractPresenter;
import com.lagossoftware.lagosfx.mvp.HasParameters;
import com.lagossoftware.lagosfx.mvp.Place;
import com.lagossoftware.lagosfx.mvp.PlaceBuilder;
import com.lagossoftware.lagosfx.navigation.NavigationManager;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import org.controlsfx.control.Notifications;

import java.util.Map;

/**
 * Settings presenter
 *
 * @author Cem Ikta
 */
public class SettingsPresenter extends AbstractPresenter<SettingsView> implements HasParameters {

    private Map<String, Object> parameters;

    @Inject
    public SettingsPresenter(final NavigationManager navigationManager,
            final SettingsView view) {
        super(navigationManager, view);
    }

    @Override
    public void start(BorderPane container) {
        container.setLeft(getView().asNode());
        container.setRight(null);
        container.setTop(null);
        container.setCenter(null);
        container.setBottom(null);
        bind();

        AppPlaces openDefaultPlace = (AppPlaces) parameters.get("openDefaultPlace");

        switch (openDefaultPlace) {
            case APP_USER:
                navigationManager.goTo(new Place(AppPlaces.APP_USER));
                break;
            case APP_USER_GROUP:
                navigationManager.goTo(new Place(AppPlaces.APP_USER_GROUP));
                break;
            case APP_SETTINGS:
                navigationManager.goTo(new Place(AppPlaces.APP_SETTINGS));
                break;
            default:
                navigationManager.goTo(new Place(AppPlaces.APP_USER));
                break;
        }
    }

    @Override
    public void bind() {
        view.getUserLink().setOnAction((ActionEvent event) -> {
            if (IbbManagement.get().isReadAllowed(AppPlaces.APP_USER)) {
                navigationManager.goTo(new PlaceBuilder(AppPlaces.SETTINGS)
                        .parameter("openDefaultPlace", AppPlaces.APP_USER).build());
            }
        });

        view.getUserGroupLink().setOnAction((ActionEvent event) -> {
            if (IbbManagement.get().isReadAllowed(AppPlaces.APP_USER_GROUP)) {
                navigationManager.goTo(new PlaceBuilder(AppPlaces.SETTINGS)
                        .parameter("openDefaultPlace", AppPlaces.APP_USER_GROUP).build());
            }
        });

        view.getAppSettingsLink().setOnAction((ActionEvent event) -> {
            if (IbbManagement.get().isReadAllowed(AppPlaces.APP_SETTINGS)) {
                navigationManager.goTo(new PlaceBuilder(AppPlaces.SETTINGS)
                        .parameter("openDefaultPlace", AppPlaces.APP_SETTINGS).build());
            }
        });
    }

    @Override
    public Map<String, Object> getParameters() {
        return parameters;
    }

    @Override
    public void setParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
    }

}
