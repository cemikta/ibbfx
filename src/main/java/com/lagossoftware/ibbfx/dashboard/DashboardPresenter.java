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
package com.lagossoftware.ibbfx.dashboard;

import com.google.inject.Inject;
import com.lagossoftware.ibbfx.IbbManagement;
import com.lagossoftware.ibbfx.app.AppPlaces;
import com.lagossoftware.ibbfx.app.AppRightType;
import com.lagossoftware.ibbfx.app.I18n;
import com.lagossoftware.ibbfx.settings.AppRightModel;
import com.lagossoftware.lagosfx.mvp.AbstractPresenter;
import com.lagossoftware.lagosfx.mvp.Place;
import com.lagossoftware.lagosfx.mvp.PlaceBuilder;
import com.lagossoftware.lagosfx.navigation.NavigationManager;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import org.controlsfx.control.Notifications;

/**
 * Dashboard presenter
 *
 * @author Cem Ikta
 */
public class DashboardPresenter extends AbstractPresenter<DashboardView> {

    @Inject
    public DashboardPresenter(final NavigationManager navigationManager, final DashboardView view) {
        super(navigationManager, view);
    }

    @Override
    public void start(BorderPane container) {
        container.setLeft(null);
        container.setRight(null);
        container.setTop(null);
        container.setCenter(getView().asNode());
        container.setBottom(null);
        bind();
        navigationManager.setCurrentPageTitle(view.getHeaderTitle());
    }

    @Override
    public void bind() {
        view.getBtnAdresse().setOnAction((ActionEvent event) -> {
            if (IbbManagement.get().isReadAllowed(AppPlaces.ADDRESSE)) {
                navigationManager.goTo(new Place(AppPlaces.ADDRESSE));
            }
        });

        view.getBtnEingangsrechnung().setOnAction((ActionEvent event) -> {
            if (IbbManagement.get().isReadAllowed(AppPlaces.EINGANGSRECHNUNG)) {
                navigationManager.goTo(new Place(AppPlaces.EINGANGSRECHNUNG));
            }
        });

        view.getBtnFahrtkasse().setOnAction((ActionEvent event) -> {
            if (IbbManagement.get().isReadAllowed(AppPlaces.FAHRTKASSE)) {
                navigationManager.goTo(new Place(AppPlaces.FAHRTKASSE));
            }
        });

        view.getBtnPlanung().setOnAction((ActionEvent event) -> {
            if (IbbManagement.get().isReadAllowed(AppPlaces.PLANUNG)) {
                navigationManager.goTo(new Place(AppPlaces.PLANUNG));
            }
        });

        view.getBtnGrundlagen().setOnAction((ActionEvent event) -> {
            if (IbbManagement.get().isReadAllowed(AppPlaces.ANREDE)) {
                navigationManager.goTo(new PlaceBuilder(AppPlaces.GRUNDLAGEN)
                        .parameter("openDefaultPlace", AppPlaces.ANREDE).build());
            }
        });

        view.getBtnReports().setOnAction((ActionEvent event) -> {
            // TODO add reports
            Notifications.create()
                    .text(I18n.COMMON.getString("notification.comingSoon"))
                    .position(Pos.TOP_RIGHT).showInformation();
//            if (IbbManagement.get().isReadAllowed(AppPlaces.REPORTS)) {
//                navigationManager.goTo(new Place(AppPlaces.REPORTS));
//            }
        });

        view.getBtnSettings().setOnAction((ActionEvent event) -> {
            if (IbbManagement.get().isReadAllowed(AppPlaces.APP_USER)) {
                navigationManager.goTo(new PlaceBuilder(AppPlaces.SETTINGS)
                        .parameter("openDefaultPlace", AppPlaces.APP_USER).build());
            }
        });
    }

}
