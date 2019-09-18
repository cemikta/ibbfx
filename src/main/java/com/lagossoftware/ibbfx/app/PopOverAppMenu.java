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
package com.lagossoftware.ibbfx.app;

import com.lagossoftware.ibbfx.IbbManagement;
import com.lagossoftware.lagosfx.common.ViewHelpers;
import com.lagossoftware.lagosfx.mvp.Place;
import com.lagossoftware.lagosfx.mvp.PlaceBuilder;
import com.lagossoftware.lagosfx.navigation.NavigationManager;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.PopOver;

/**
 * Pop over app menu
 * 
 * @author Cem Ikta
 */
public class PopOverAppMenu extends PopOver {

    private NavigationManager navigationManager;
    private Button btnAdresse;
    private Button btnEingangsrechnung;
    private Button btnFahrtkasse;
    private Button btnPlanung;
    private Button btnGrundlagen;
    private Button btnReports;
    private Button btnSettings;

    public PopOverAppMenu(NavigationManager navigationManager) {
        super();
        this.navigationManager = navigationManager;
        buildView();
    }

    private void buildView() {
        setId("pop-over-app-menu");
        setDetachable(false);
        setArrowLocation(PopOver.ArrowLocation.TOP_LEFT);
        setCornerRadius(10);
        setAutoHide(true);
        setHideOnEscape(true);
        setContentNode(getModulesPane());
    }

    private GridPane getModulesPane() {
        // TODO menu presenter?
        btnAdresse = ViewHelpers.createIconButton(
                Images.ADRESSE_32,
                I18n.COMMON.getString("appModule.adresse"),
                ContentDisplay.TOP);
        btnAdresse.setOnAction((ActionEvent event) -> {
            this.hide(Duration.ZERO);
            if (IbbManagement.get().isReadAllowed(AppPlaces.ADDRESSE)) {
                navigationManager.goTo(new Place(AppPlaces.ADDRESSE));
            }
        });

        btnEingangsrechnung = ViewHelpers.createIconButton(
                Images.EINGANGSRECHNUNG_32,
                I18n.COMMON.getString("appModule.eingangsrechnung"),
                ContentDisplay.TOP);
        btnEingangsrechnung.setOnAction((ActionEvent event) -> {
            this.hide(Duration.ZERO);
            if (IbbManagement.get().isReadAllowed(AppPlaces.EINGANGSRECHNUNG)) {
                navigationManager.goTo(new Place(AppPlaces.EINGANGSRECHNUNG));
            }
        });

        btnPlanung = ViewHelpers.createIconButton(
                Images.PLANUNG_32,
                I18n.COMMON.getString("appModule.planung"),
                ContentDisplay.TOP);
        btnPlanung.setOnAction((ActionEvent event) -> {
            this.hide(Duration.ZERO);
            if (IbbManagement.get().isReadAllowed(AppPlaces.PLANUNG)) {
                navigationManager.goTo(new Place(AppPlaces.PLANUNG));
            }
        });

        btnFahrtkasse = ViewHelpers.createIconButton(
                Images.FAHRTKASSE_32,
                I18n.COMMON.getString("appModule.fahrtkasse"),
                ContentDisplay.TOP);
        btnFahrtkasse.setOnAction((ActionEvent event) -> {
            this.hide(Duration.ZERO);
            if (IbbManagement.get().isReadAllowed(AppPlaces.FAHRTKASSE)) {
                navigationManager.goTo(new Place(AppPlaces.FAHRTKASSE));
            }
        });

        btnGrundlagen = ViewHelpers.createIconButton(
                Images.GRUNDLAGEN_32,
                I18n.COMMON.getString("appModule.grundlagen"),
                ContentDisplay.TOP);
        btnGrundlagen.setOnAction((ActionEvent event) -> {
            this.hide(Duration.ZERO);
            if (IbbManagement.get().isReadAllowed(AppPlaces.ANREDE)) {
                navigationManager.goTo(new PlaceBuilder(AppPlaces.GRUNDLAGEN)
                        .parameter("openDefaultPlace", AppPlaces.ANREDE).build());
            }
        });
        
        btnReports = ViewHelpers.createIconButton(
                Images.REPORTS_32,
                I18n.COMMON.getString("appModule.reports"),
                ContentDisplay.TOP);
        btnReports.setOnAction((ActionEvent event) -> {
            this.hide(Duration.ZERO);
            // TODO add reports
            Notifications.create()
                    .text(I18n.COMMON.getString("notification.comingSoon"))
                    .position(Pos.TOP_RIGHT).showInformation();

//            if (IbbManagement.get().isReadAllowed(AppPlaces.REPORTS)) {
//                navigationManager.goTo(new Place(AppPlaces.REPORTS));
//            }
        });
        
        btnSettings = ViewHelpers.createIconButton(
                Images.SETTINGS_32,
                I18n.COMMON.getString("appModule.settings"),
                ContentDisplay.TOP);
        btnSettings.setOnAction((ActionEvent event) -> {
            this.hide(Duration.ZERO);
            if (IbbManagement.get().isReadAllowed(AppPlaces.APP_USER)) {
                navigationManager.goTo(new PlaceBuilder(AppPlaces.SETTINGS)
                        .parameter("openDefaultPlace", AppPlaces.APP_USER).build());
            }
        });

        GridPane modulesPane = new GridPane();
        modulesPane.setHgap(10);
        modulesPane.setVgap(10);
        modulesPane.setPadding(new Insets(20));

        modulesPane.add(btnAdresse, 0, 0);
        modulesPane.add(btnEingangsrechnung, 1, 0);

        modulesPane.add(btnPlanung, 0, 1);
        modulesPane.add(btnFahrtkasse, 1, 1);

        modulesPane.add(btnGrundlagen, 0, 2);
        modulesPane.add(btnReports, 1, 2);

        modulesPane.add(btnSettings, 0, 3);
        
        return modulesPane;
    }
    
}
