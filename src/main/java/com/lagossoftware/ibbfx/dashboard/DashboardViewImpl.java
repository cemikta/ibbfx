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

import com.lagossoftware.ibbfx.app.I18n;
import com.lagossoftware.ibbfx.app.Images;
import com.lagossoftware.lagosfx.common.ViewHelpers;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.layout.FlowPane;
import org.controlsfx.dialog.Wizard;

/**
 * Dashboard view implementation
 *
 * @author Cem Ikta
 */
public class DashboardViewImpl extends FlowPane implements DashboardView {

    private Button btnAdresse;
    private Button btnEingangsrechnung;
    private Button btnFahrtkasse;
    private Button btnPlanung;
    private Button btnGrundlagen;
    private Button btnReports;
    private Button btnSettings;

    public DashboardViewImpl() {
        buildView();
    }

    private void buildView() {
        setId("dashboard-menu");
        setMinWidth(200);
        setMaxWidth(600);
        setPrefWidth(600);
        setPrefWrapLength(510);

        btnAdresse = ViewHelpers.createIconButton(
                Images.ADRESSE_32,
                I18n.COMMON.getString("appModule.adresse"),
                ContentDisplay.TOP);
        btnEingangsrechnung = ViewHelpers.createIconButton(
                Images.EINGANGSRECHNUNG_32,
                I18n.COMMON.getString("appModule.eingangsrechnung"),
                ContentDisplay.TOP);
        btnPlanung = ViewHelpers.createIconButton(
                Images.PLANUNG_32,
                I18n.COMMON.getString("appModule.planung"),
                ContentDisplay.TOP);
        btnFahrtkasse = ViewHelpers.createIconButton(
                Images.FAHRTKASSE_32,
                I18n.COMMON.getString("appModule.fahrtkasse"),
                ContentDisplay.TOP);
        btnGrundlagen = ViewHelpers.createIconButton(
                Images.GRUNDLAGEN_32,
                I18n.COMMON.getString("appModule.grundlagen"),
                ContentDisplay.TOP);
        btnReports = ViewHelpers.createIconButton(
                Images.REPORTS_32,
                I18n.COMMON.getString("appModule.reports"),
                ContentDisplay.TOP);
        btnSettings = ViewHelpers.createIconButton(
                Images.SETTINGS_32,
                I18n.COMMON.getString("appModule.settings"),
                ContentDisplay.TOP);

        getChildren().addAll(btnAdresse, btnEingangsrechnung, btnPlanung,
                btnFahrtkasse, btnGrundlagen, btnReports,
                btnSettings);
    }

    @Override
    public String getHeaderTitle() {
        return I18n.COMMON.getString("dashboard.title");
    }
    
    @Override
    public Button getBtnAdresse() {
        return btnAdresse;
    }

    @Override
    public Button getBtnEingangsrechnung() {
        return btnEingangsrechnung;
    }

    @Override
    public Button getBtnFahrtkasse() {
        return btnFahrtkasse;
    }

    @Override
    public Button getBtnPlanung() {
        return btnPlanung;
    }

    @Override
    public Button getBtnGrundlagen() {
        return btnGrundlagen;
    }

    @Override
    public Button getBtnReports() {
        return btnReports;
    }

    @Override
    public Button getBtnSettings() {
        return btnSettings;
    }

    @Override
    public Node asNode() {
        return this;
    }

}
