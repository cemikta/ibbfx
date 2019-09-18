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
package com.lagossoftware.ibbfx.planung;

import com.lagossoftware.ibbfx.app.I18n;
import com.lagossoftware.ibbfx.app.Images;
import com.lagossoftware.ibbfx.entity.Planung;
import com.lagossoftware.lagosfx.common.ViewHelpers;
import com.lagossoftware.lagosfx.mvp.AbstractMultiPageFormView;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

/**
 * Planung form view implementation
 *
 * @author Cem Ikta
 */
public class PlanungFormViewImpl extends AbstractMultiPageFormView<Planung>
        implements PlanungFormView {

    private MenuItem mnuMassnahmedatenblatt1;
    private MenuItem mnuMassnahmekalkulation;
    private MenuButton btnPrintPreview;
    private MenuItem mnuMassnahmedatenblatt1Print;
    private MenuItem mnuMassnahmekalkulationPrint;
    private MenuButton btnPrint;
    private Button btnPlanungParams;
    private Label lblPlanungNr;
    private Label lblPlanungTitle;
    private Label lblVATage;
    private Label lblZahlendeTN;
    private Label lblNichtZahlendeMitfahrer;
    private Label lblTeilnehmerabhaengigeKosten;
    private Label lblTeilnehmerunabhaengigeKosten;
    private Label lblKostenFuerAlleAuslaendischeTN;
    private Label lblIBBStrukturkosten;
    private Label lblGesamtkosten;
    private Label lblAbzZuschuesse;
    private Label lblPreisKalk;
    private Label lblVerkaufspreis;
    private Label lblIBBErloes;

    public PlanungFormViewImpl() {
        super();
        buildView();
    }

    private void buildView() {
        mnuMassnahmedatenblatt1 = new MenuItem(I18n.PLANUNG.getString("planung.form.printMassnahmedatenblatt1"),
                ViewHelpers.createImageView(Images.ARROW_LEFT_16));
        mnuMassnahmekalkulation = new MenuItem(I18n.PLANUNG.getString("planung.form.printMassnahmekalkulation"),
                ViewHelpers.createImageView(Images.ARROW_LEFT_16));

        btnPrintPreview = ViewHelpers.createMenuButton(I18n.COMMON.getString("action.printPreview"),
                Images.PRINT_PREVIEW_24);
        btnPrintPreview.getStyleClass().add("left-pill");
        btnPrintPreview.getItems().addAll(mnuMassnahmedatenblatt1, mnuMassnahmekalkulation);

        mnuMassnahmedatenblatt1Print = new MenuItem(I18n.PLANUNG.getString("planung.form.printMassnahmedatenblatt1"),
                ViewHelpers.createImageView(Images.ARROW_LEFT_16));
        mnuMassnahmekalkulationPrint = new MenuItem(
                I18n.PLANUNG.getString("planung.form.printMassnahmekalkulation"),
                ViewHelpers.createImageView(Images.ARROW_LEFT_16));

        btnPrint = ViewHelpers.createMenuButton(I18n.COMMON.getString("action.print"), Images.PRINT_24);
        btnPrint.getStyleClass().add("right-pill");
        btnPrint.getItems().addAll(mnuMassnahmedatenblatt1Print, mnuMassnahmekalkulationPrint);
        HBox.setMargin(btnPrint, new Insets(0, 10, 0, 0));

        btnPlanungParams = ViewHelpers.createIconButton(Images.PLANUNG_PARAMS_24,
                I18n.PLANUNG.getString("planung.form.params"));

        getToolBar().getChildren().addAll(btnPrintPreview, btnPrint, btnPlanungParams);

        // overview pane
        lblPlanungNr = new Label();
        lblPlanungNr.setPrefWidth(200);
        lblPlanungNr.getStyleClass().add("font-bold");

        lblPlanungTitle = new Label();
        lblPlanungTitle.setPrefWidth(200);
        lblPlanungTitle.getStyleClass().add("font-bold");

        lblVATage = new Label();
        lblVATage.setPrefWidth(200);
        lblVATage.getStyleClass().add("font-bold");

        lblZahlendeTN = new Label();
        lblZahlendeTN.setPrefWidth(200);
        lblZahlendeTN.getStyleClass().add("font-bold");

        lblNichtZahlendeMitfahrer = new Label();
        lblNichtZahlendeMitfahrer.setPrefWidth(200);
        lblNichtZahlendeMitfahrer.getStyleClass().add("font-bold");

        lblTeilnehmerabhaengigeKosten = new Label();
        lblTeilnehmerabhaengigeKosten.setPrefWidth(100);
        lblTeilnehmerabhaengigeKosten.getStyleClass().add("font-bold");

        lblTeilnehmerunabhaengigeKosten = new Label();
        lblTeilnehmerunabhaengigeKosten.setPrefWidth(100);
        lblTeilnehmerunabhaengigeKosten.getStyleClass().add("font-bold");

        lblKostenFuerAlleAuslaendischeTN = new Label();
        lblKostenFuerAlleAuslaendischeTN.setPrefWidth(100);
        lblKostenFuerAlleAuslaendischeTN.getStyleClass().add("font-bold");

        lblIBBStrukturkosten = new Label();
        lblIBBStrukturkosten.setPrefWidth(100);
        lblIBBStrukturkosten.getStyleClass().add("font-bold");

        lblGesamtkosten = new Label();
        lblGesamtkosten.setPrefWidth(100);
        lblGesamtkosten.getStyleClass().add("font-bold");

        lblAbzZuschuesse = new Label();
        lblAbzZuschuesse.setPrefWidth(100);
        lblGesamtkosten.getStyleClass().add("font-bold");

        lblPreisKalk = new Label();
        lblPreisKalk.setPrefWidth(100);
        lblPreisKalk.getStyleClass().add("font-bold");

        lblVerkaufspreis = new Label();
        lblVerkaufspreis.setPrefWidth(100);
        lblVerkaufspreis.getStyleClass().add("font-bold");

        lblIBBErloes = new Label();
        lblIBBErloes.setPrefWidth(100);
        lblIBBErloes.getStyleClass().add("font-bold");

        getOverviewPane().add(new Label(I18n.PLANUNG.getString("planung.overview.planungNr")), 0, 0);
        getOverviewPane().add(lblPlanungNr, 1, 0);
        getOverviewPane().add(ViewHelpers.createSpaceLabel(), 2, 0);
        getOverviewPane().add(new Label(I18n.PLANUNG.getString("planung.overview.teilnehmerabhaengigeKosten")), 3, 0);
        getOverviewPane().add(lblTeilnehmerabhaengigeKosten, 4, 0);
        getOverviewPane().add(ViewHelpers.createSpaceLabel(), 5, 0);
        getOverviewPane().add(new Label(I18n.PLANUNG.getString("planung.overview.gesamtkosten")), 6, 0);
        getOverviewPane().add(lblGesamtkosten, 7, 0);

        getOverviewPane().add(new Label(I18n.PLANUNG.getString("planung.overview.planungTitle")), 0, 1);
        getOverviewPane().add(lblPlanungTitle, 1, 1);
        getOverviewPane().add(ViewHelpers.createSpaceLabel(), 2, 1);
        getOverviewPane().add(new Label(I18n.PLANUNG.getString("planung.overview.teilnehmerunabhaengigeKosten")), 3, 1);
        getOverviewPane().add(lblTeilnehmerunabhaengigeKosten, 4, 1);
        getOverviewPane().add(ViewHelpers.createSpaceLabel(), 5, 1);
        getOverviewPane().add(new Label(I18n.PLANUNG.getString("planung.overview.abzZuschuesse")), 6, 1);
        getOverviewPane().add(lblAbzZuschuesse, 7, 1);

        getOverviewPane().add(new Label(I18n.PLANUNG.getString("planung.overview.vaTage")), 0, 2);
        getOverviewPane().add(lblVATage, 1, 2);
        getOverviewPane().add(ViewHelpers.createSpaceLabel(), 2, 2);
        getOverviewPane().add(new Label(I18n.PLANUNG.getString("planung.overview.kostenFuerAlleAuslaendischeTN")), 3, 2);
        getOverviewPane().add(lblKostenFuerAlleAuslaendischeTN, 4, 2);
        getOverviewPane().add(ViewHelpers.createSpaceLabel(), 5, 2);
        getOverviewPane().add(new Label(I18n.PLANUNG.getString("planung.overview.preisKalk")), 6, 2);
        getOverviewPane().add(lblPreisKalk, 7, 2);

        getOverviewPane().add(new Label(I18n.PLANUNG.getString("planung.overview.zahlendeTN")), 0, 3);
        getOverviewPane().add(lblZahlendeTN, 1, 3);
        getOverviewPane().add(ViewHelpers.createSpaceLabel(), 2, 3);
        getOverviewPane().add(new Label(I18n.PLANUNG.getString("planung.overview.ibbStrukturkosten")), 3, 3);
        getOverviewPane().add(lblIBBStrukturkosten, 4, 3);
        getOverviewPane().add(ViewHelpers.createSpaceLabel(), 5, 3);
        getOverviewPane().add(new Label(I18n.PLANUNG.getString("planung.overview.verkaufspreis")), 6, 3);
        getOverviewPane().add(lblVerkaufspreis, 7, 3);

        getOverviewPane().add(new Label(I18n.PLANUNG.getString("planung.overview.nichtZahlendeMitfahrer")), 0, 4);
        getOverviewPane().add(lblNichtZahlendeMitfahrer, 1, 4);
        getOverviewPane().add(ViewHelpers.createSpaceLabel(), 2, 4);
        getOverviewPane().add(new Label(), 3, 3);
        getOverviewPane().add(new Label(), 4, 3);
        getOverviewPane().add(ViewHelpers.createSpaceLabel(), 5, 4);
        getOverviewPane().add(new Label(I18n.PLANUNG.getString("planung.overview.ibbErloes")), 6, 4);
        getOverviewPane().add(lblIBBErloes, 7, 4);
    }
    
    @Override
    public String getHeaderTitle() {
        return I18n.PLANUNG.getString("planung.form.title");
    }

    @Override
    public boolean hasOverviewPane() {
        return true;
    }

    @Override
    public MenuItem getMnuMassnahmedatenblatt1() {
        return mnuMassnahmedatenblatt1;
    }

    @Override
    public MenuItem getMnuMassnahmekalkulation() {
        return mnuMassnahmekalkulation;
    }

    @Override
    public MenuItem getMnuMassnahmedatenblatt1Print() {
        return mnuMassnahmedatenblatt1Print;
    }

    @Override
    public MenuItem getMnuMassnahmekalkulationPrint() {
        return mnuMassnahmekalkulationPrint;
    }

    @Override
    public Button getBtnPlanungParams() {
        return btnPlanungParams;
    }

    @Override
    public Label getLblPlanungNr() {
        return lblPlanungNr;
    }

    @Override
    public Label getLblPlanungTitle() {
        return lblPlanungTitle;
    }

    @Override
    public Label getLblVATage() {
        return lblVATage;
    }

    @Override
    public Label getLblZahlendeTN() {
        return lblZahlendeTN;
    }

    @Override
    public Label getLblNichtZahlendeMitfahrer() {
        return lblNichtZahlendeMitfahrer;
    }

    @Override
    public Label getLblTeilnehmerabhaengigeKosten() {
        return lblTeilnehmerabhaengigeKosten;
    }

    @Override
    public Label getLblTeilnehmerunabhaengigeKosten() {
        return lblTeilnehmerunabhaengigeKosten;
    }

    @Override
    public Label getLblKostenFuerAlleAuslaendischeTN() {
        return lblKostenFuerAlleAuslaendischeTN;
    }

    @Override
    public Label getLblIBBStrukturkosten() {
        return lblIBBStrukturkosten;
    }

    @Override
    public Label getLblGesamtkosten() {
        return lblGesamtkosten;
    }

    @Override
    public Label getLblAbzZuschuesse() {
        return lblAbzZuschuesse;
    }

    @Override
    public Label getLblPreisKalk() {
        return lblPreisKalk;
    }

    @Override
    public Label getLblVerkaufspreis() {
        return lblVerkaufspreis;
    }

    @Override
    public Label getLblIBBErloes() {
        return lblIBBErloes;
    }
}
