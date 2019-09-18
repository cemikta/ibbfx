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

import com.lagossoftware.ibbfx.app.I18n;
import com.lagossoftware.ibbfx.app.Images;
import com.lagossoftware.ibbfx.dashboard.DashboardView;
import com.lagossoftware.lagosfx.common.ViewHelpers;
import com.lagossoftware.lagosfx.control.*;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import org.controlsfx.validation.decoration.GraphicValidationDecoration;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * App settings view implementation
 *
 * @author Cem Ikta
 */
public class AppSettingsViewImpl extends BorderPane implements AppSettingsView {

    private HBox toolBar;
    private Button btnSave;
    private ButtonBarPane bbpPages;
    private ValidationSupport validationSupport;

    // nummernkreis tab
    private GridPane nummernkreisTab;
    private IntegerField nfLastAdresseNr;
    private IntegerField nfLastEingangsrechnungBelegNr;
    private IntegerField nfLastFahrtkasseNr;

    // planung tab
    private GridPane planungTab;
    private NumberField nfHonorareFuerIbbBegleitung;
    private NumberField nfIbbStrukturkostenProTNProTag;
    private NumberField nfIbbErloes;
    // versicherung
    private NumberField nfHaftpflichtUnfallVersicherungProPersonTag;
    private NumberField nfAuslandsreisekrankenVersicherungProPersonTag;
    private NumberField nfKrankenversicherungFuerAuslTNProPersonTag;
    private NumberField nfRegressversicherungVaInDeBis8Tage;
    private NumberField nfRegressversicherungVaInDeBis22Tage;
    private NumberField nfRegressversicherungVaInDeBis42Tage;
    private NumberField nfRegressversicherungVaImAuslandBis8Tage;
    private NumberField nfRegressversicherungVaImAuslandBis22Tage;
    private NumberField nfRegressversicherungVaImAuslandBis42Tage;
    private NumberField nfRechtschutzversicherungVaBis8Tage;
    private NumberField nfRechtschutzversicherungVaBis14Tage;
    private NumberField nfRechtschutzversicherungVaBis22Tage;
    private NumberField nfReisepreissicherungProPersonTag;

    public AppSettingsViewImpl() {
        super();
        buildView();
    }

    private void buildView() {
        setTop(buildToolBar());

        bbpPages = new ButtonBarPane();
        bbpPages.addPage(Images.NUMMERNKREIS_26,
                I18n.SETTINGS.getString("appSettings.tabNummernkreis"),
                buildNummernkreisTab(),
                ContentDisplay.LEFT);

        bbpPages.addPage(Images.APP_SETTINGS_26,
                I18n.SETTINGS.getString("appSettings.tabPlanung"),
                buildPlanungTab(),
                ContentDisplay.LEFT);

        setCenter(bbpPages);
        BorderPane.setMargin(bbpPages, new Insets(20, 10, 10, 10));
    }

    private GridPane buildNummernkreisTab() {
        nummernkreisTab = new GridPane();
        nummernkreisTab.setPadding(new Insets(2, 10, 10, 50));
        nummernkreisTab.setHgap(10);
        nummernkreisTab.setVgap(10);

        Label lblWarning = new Label(I18n.SETTINGS.getString("appSettings.nummernkreisTab.warning"));
        lblWarning.setGraphic(ViewHelpers.createImageView(Images.WARNING_26));

        nfLastAdresseNr = new IntegerField();
        nfLastAdresseNr.setMinWidth(150);
        nfLastAdresseNr.setPrefWidth(150);
        nfLastAdresseNr.setMaxWidth(150);
        getValidationSupport().registerValidator(nfLastAdresseNr,
                Validator.createEmptyValidator(I18n.COMMON.getString("validation.required")));

        nfLastEingangsrechnungBelegNr = new IntegerField();
        nfLastEingangsrechnungBelegNr.setMinWidth(150);
        nfLastEingangsrechnungBelegNr.setPrefWidth(150);
        nfLastEingangsrechnungBelegNr.setMaxWidth(150);
        getValidationSupport().registerValidator(nfLastEingangsrechnungBelegNr,
                Validator.createEmptyValidator(I18n.COMMON.getString("validation.required")));

        nfLastFahrtkasseNr = new IntegerField();
        nfLastFahrtkasseNr.setMinWidth(150);
        nfLastFahrtkasseNr.setPrefWidth(150);
        nfLastFahrtkasseNr.setMaxWidth(150);
        getValidationSupport().registerValidator(nfLastFahrtkasseNr,
                Validator.createEmptyValidator(I18n.COMMON.getString("validation.required")));

        nummernkreisTab.add(lblWarning, 0, 0, 2, 1);
        nummernkreisTab.add(new Label(I18n.SETTINGS.getString("appSettings.nummernkreisTab.lastAdresseNr")), 0, 1);
        nummernkreisTab.add(nfLastAdresseNr, 1, 1);

        nummernkreisTab.add(new Label(I18n.SETTINGS.getString("appSettings.nummernkreisTab.lastEingangsrechnungBelegNr")), 0, 2);
        nummernkreisTab.add(nfLastEingangsrechnungBelegNr, 1, 2);

        nummernkreisTab.add(new Label(I18n.SETTINGS.getString("appSettings.nummernkreisTab.lastFahrtkasseNr")), 0, 3);
        nummernkreisTab.add(nfLastFahrtkasseNr, 1, 3);

        return nummernkreisTab;
    }

    private Node buildPlanungTab() {
        planungTab = new GridPane();
        planungTab.setPadding(new Insets(2, 10, 10, 50));
        planungTab.setHgap(10);
        planungTab.setVgap(10);

        nfHonorareFuerIbbBegleitung = new NumberField(new DecimalFormat("#,###,###,##0.00"));
        getValidationSupport().registerValidator(nfHonorareFuerIbbBegleitung,
                Validator.createEmptyValidator(I18n.COMMON.getString("validation.required")));

        nfIbbStrukturkostenProTNProTag = new NumberField(new DecimalFormat("#,###,###,##0.00"));
        getValidationSupport().registerValidator(nfIbbStrukturkostenProTNProTag,
                Validator.createEmptyValidator(I18n.COMMON.getString("validation.required")));

        nfIbbErloes = new NumberField(new DecimalFormat("#,##0.00"));
        getValidationSupport().registerValidator(nfIbbErloes,
                Validator.createEmptyValidator(I18n.COMMON.getString("validation.required")));

        // versicherung
        nfHaftpflichtUnfallVersicherungProPersonTag = new NumberField(new DecimalFormat("#,##0.00"));
        getValidationSupport().registerValidator(nfHaftpflichtUnfallVersicherungProPersonTag,
                Validator.createEmptyValidator(I18n.COMMON.getString("validation.required")));

        nfAuslandsreisekrankenVersicherungProPersonTag = new NumberField(new DecimalFormat("#,##0.00"));
        getValidationSupport().registerValidator(nfAuslandsreisekrankenVersicherungProPersonTag,
                Validator.createEmptyValidator(I18n.COMMON.getString("validation.required")));

        nfKrankenversicherungFuerAuslTNProPersonTag = new NumberField(new DecimalFormat("#,##0.00"));
        getValidationSupport().registerValidator(nfKrankenversicherungFuerAuslTNProPersonTag,
                Validator.createEmptyValidator(I18n.COMMON.getString("validation.required")));

        nfRegressversicherungVaInDeBis8Tage = new NumberField(new DecimalFormat("#,##0.00"));
        getValidationSupport().registerValidator(nfRegressversicherungVaInDeBis8Tage,
                Validator.createEmptyValidator(I18n.COMMON.getString("validation.required")));

        nfRegressversicherungVaInDeBis22Tage = new NumberField(new DecimalFormat("#,##0.00"));
        getValidationSupport().registerValidator(nfRegressversicherungVaInDeBis22Tage,
                Validator.createEmptyValidator(I18n.COMMON.getString("validation.required")));

        nfRegressversicherungVaInDeBis42Tage = new NumberField(new DecimalFormat("#,##0.00"));
        getValidationSupport().registerValidator(nfRegressversicherungVaInDeBis42Tage,
                Validator.createEmptyValidator(I18n.COMMON.getString("validation.required")));

        nfRegressversicherungVaImAuslandBis8Tage = new NumberField(new DecimalFormat("#,##0.00"));
        getValidationSupport().registerValidator(nfRegressversicherungVaImAuslandBis8Tage,
                Validator.createEmptyValidator(I18n.COMMON.getString("validation.required")));

        nfRegressversicherungVaImAuslandBis22Tage = new NumberField(new DecimalFormat("#,##0.00"));
        getValidationSupport().registerValidator(nfRegressversicherungVaImAuslandBis22Tage,
                Validator.createEmptyValidator(I18n.COMMON.getString("validation.required")));

        nfRegressversicherungVaImAuslandBis42Tage = new NumberField(new DecimalFormat("#,##0.00"));
        getValidationSupport().registerValidator(nfRegressversicherungVaImAuslandBis42Tage,
                Validator.createEmptyValidator(I18n.COMMON.getString("validation.required")));

        nfRechtschutzversicherungVaBis8Tage = new NumberField(new DecimalFormat("#,##0.00"));
        getValidationSupport().registerValidator(nfRechtschutzversicherungVaBis8Tage,
                Validator.createEmptyValidator(I18n.COMMON.getString("validation.required")));

        nfRechtschutzversicherungVaBis14Tage = new NumberField(new DecimalFormat("#,##0.00"));
        getValidationSupport().registerValidator(nfRechtschutzversicherungVaBis14Tage,
                Validator.createEmptyValidator(I18n.COMMON.getString("validation.required")));

        nfRechtschutzversicherungVaBis22Tage = new NumberField(new DecimalFormat("#,##0.00"));
        getValidationSupport().registerValidator(nfRechtschutzversicherungVaBis22Tage,
                Validator.createEmptyValidator(I18n.COMMON.getString("validation.required")));

        nfReisepreissicherungProPersonTag = new NumberField(new DecimalFormat("#,##0.00"));
        getValidationSupport().registerValidator(nfReisepreissicherungProPersonTag,
                Validator.createEmptyValidator(I18n.COMMON.getString("validation.required")));

        planungTab.add(new TitledSeparator(I18n.SETTINGS.getString("appSettings.planungTab.honorarkosten")), 0, 0, 2, 1);
        planungTab.add(new Label(I18n.SETTINGS.getString("appSettings.planungTab.honorareFuerIbbBegleitung")), 0, 1);
        planungTab.add(nfHonorareFuerIbbBegleitung, 1, 1);

        planungTab.add(new TitledSeparator(I18n.SETTINGS.getString("appSettings.planungTab.ibbStrukturkosten")), 0, 2, 2, 1);
        planungTab.add(new Label(I18n.SETTINGS.getString("appSettings.planungTab.ibbStrukturkostenProTNProTag")), 0, 3);
        planungTab.add(nfIbbStrukturkostenProTNProTag, 1, 3);

        planungTab.add(new TitledSeparator(I18n.SETTINGS.getString("appSettings.planungTab.ibbErloesTitle")), 0, 4, 2, 1);
        planungTab.add(new Label(I18n.SETTINGS.getString("appSettings.planungTab.ibbErloes")), 0, 5);
        planungTab.add(nfIbbErloes, 1, 5);

        planungTab.add(new TitledSeparator(I18n.SETTINGS.getString("appSettings.planungTab.versicherungTitle")), 0, 6, 2, 1);
        planungTab.add(new Label(I18n.SETTINGS.getString("appSettings.planungTab.haftpflichtUnfallVersicherungProPersonTag")), 0, 7);
        planungTab.add(nfHaftpflichtUnfallVersicherungProPersonTag, 1, 7);

        planungTab.add(new Label(I18n.SETTINGS.getString("appSettings.planungTab.auslandsreisekrankenVersicherungProPersonTag")), 0, 8);
        planungTab.add(nfAuslandsreisekrankenVersicherungProPersonTag, 1, 8);

        planungTab.add(new Label(I18n.SETTINGS.getString("appSettings.planungTab.krankenversicherungFuerAuslTNProPersonTag")), 0, 9);
        planungTab.add(nfKrankenversicherungFuerAuslTNProPersonTag, 1, 9);

        planungTab.add(new TitledSeparator(I18n.SETTINGS.getString("appSettings.planungTab.regressversicherungInDeutschland")), 0, 10, 2, 1);
        planungTab.add(ViewHelpers.createSpaceLabel(), 2, 10);
        planungTab.add(new TitledSeparator(I18n.SETTINGS.getString("appSettings.planungTab.regressversicherungImAusland")), 3, 10, 2, 1);

        planungTab.add(new Label(I18n.SETTINGS.getString("appSettings.planungTab.regressversicherungVaInDeBis8Tage")), 0, 11);
        planungTab.add(nfRegressversicherungVaInDeBis8Tage, 1, 11);
        planungTab.add(ViewHelpers.createSpaceLabel(), 2, 11);
        planungTab.add(new Label(I18n.SETTINGS.getString("appSettings.planungTab.regressversicherungVaImAuslandBis8Tage")), 3, 11);
        planungTab.add(nfRegressversicherungVaImAuslandBis8Tage, 4, 11);

        planungTab.add(new Label(I18n.SETTINGS.getString("appSettings.planungTab.regressversicherungVaInDeBis22Tage")), 0, 12);
        planungTab.add(nfRegressversicherungVaInDeBis22Tage, 1, 12);
        planungTab.add(ViewHelpers.createSpaceLabel(), 2, 12);
        planungTab.add(new Label(I18n.SETTINGS.getString("appSettings.planungTab.regressversicherungVaImAuslandBis22Tage")), 3, 12);
        planungTab.add(nfRegressversicherungVaImAuslandBis22Tage, 4, 12);

        planungTab.add(new Label(I18n.SETTINGS.getString("appSettings.planungTab.regressversicherungVaInDeBis42Tage")), 0, 13);
        planungTab.add(nfRegressversicherungVaInDeBis42Tage, 1, 13);
        planungTab.add(ViewHelpers.createSpaceLabel(), 2, 13);
        planungTab.add(new Label(I18n.SETTINGS.getString("appSettings.planungTab.regressversicherungVaImAuslandBis42Tage")), 3, 13);
        planungTab.add(nfRegressversicherungVaImAuslandBis42Tage, 4, 13);

        planungTab.add(new TitledSeparator(I18n.SETTINGS.getString("appSettings.planungTab.rechtschutzversicherung")), 0, 14, 2, 1);
        planungTab.add(new Label(I18n.SETTINGS.getString("appSettings.planungTab.rechtschutzversicherungVaBis8Tage")), 0, 15);
        planungTab.add(nfRechtschutzversicherungVaBis8Tage, 1, 15);

        planungTab.add(new Label(I18n.SETTINGS.getString("appSettings.planungTab.rechtschutzversicherungVaBis14Tage")), 0, 16);
        planungTab.add(nfRechtschutzversicherungVaBis14Tage, 1, 16);

        planungTab.add(new Label(I18n.SETTINGS.getString("appSettings.planungTab.rechtschutzversicherungVaBis22Tage")), 0, 17);
        planungTab.add(nfRechtschutzversicherungVaBis22Tage, 1, 17);

        planungTab.add(new TitledSeparator(I18n.SETTINGS.getString("appSettings.planungTab.reisepreissicherung")), 0, 18, 2, 1);
        planungTab.add(new Label(I18n.SETTINGS.getString("appSettings.planungTab.reisepreissicherungProPersonTag")), 0, 19);
        planungTab.add(nfReisepreissicherungProPersonTag, 1, 19);

        return new ScrollPane(planungTab);
    }

    private HBox buildToolBar() {
        btnSave = ViewHelpers.createIconButton(Images.SAVE_24,
                I18n.COMMON.getString("action.save"));
        HBox.setMargin(btnSave, new Insets(0, 10, 0, 0));

        toolBar = new HBox(0);
        toolBar.setPadding(new Insets(10, 10, 10, 10));
        toolBar.getStyleClass().add("page-tool-bar");
        toolBar.getChildren().add(btnSave);

        return toolBar;
    }

    @Override
    public String getHeaderTitle() {
        return I18n.SETTINGS.getString("appSettings.title");
    }

    @Override
    public Button getBtnSave() {
        return btnSave;
    }

    @Override
    public IntegerField getNfLastAdresseNr() {
        return nfLastAdresseNr;
    }

    @Override
    public IntegerField getNfLastEingangsrechnungBelegNr() {
        return nfLastEingangsrechnungBelegNr;
    }

    @Override
    public IntegerField getNfLastFahrtkasseNr() {
        return nfLastFahrtkasseNr;
    }

    @Override
    public NumberField getNfHonorareFuerIbbBegleitung() {
        return nfHonorareFuerIbbBegleitung;
    }

    @Override
    public NumberField getNfIbbStrukturkostenProTNProTag() {
        return nfIbbStrukturkostenProTNProTag;
    }

    @Override
    public NumberField getNfIbbErloes() {
        return nfIbbErloes;
    }

    @Override
    public NumberField getNfHaftpflichtUnfallVersicherungProPersonTag() {
        return nfHaftpflichtUnfallVersicherungProPersonTag;
    }

    @Override
    public NumberField getNfAuslandsreisekrankenVersicherungProPersonTag() {
        return nfAuslandsreisekrankenVersicherungProPersonTag;
    }

    @Override
    public NumberField getNfKrankenversicherungFuerAuslTNProPersonTag() {
        return nfKrankenversicherungFuerAuslTNProPersonTag;
    }

    @Override
    public NumberField getNfRegressversicherungVaInDeBis8Tage() {
        return nfRegressversicherungVaInDeBis8Tage;
    }

    @Override
    public NumberField getNfRegressversicherungVaInDeBis22Tage() {
        return nfRegressversicherungVaInDeBis22Tage;
    }

    @Override
    public NumberField getNfRegressversicherungVaInDeBis42Tage() {
        return nfRegressversicherungVaInDeBis42Tage;
    }

    @Override
    public NumberField getNfRegressversicherungVaImAuslandBis8Tage() {
        return nfRegressversicherungVaImAuslandBis8Tage;
    }

    @Override
    public NumberField getNfRegressversicherungVaImAuslandBis22Tage() {
        return nfRegressversicherungVaImAuslandBis22Tage;
    }

    @Override
    public NumberField getNfRegressversicherungVaImAuslandBis42Tage() {
        return nfRegressversicherungVaImAuslandBis42Tage;
    }

    @Override
    public NumberField getNfRechtschutzversicherungVaBis8Tage() {
        return nfRechtschutzversicherungVaBis8Tage;
    }

    @Override
    public NumberField getNfRechtschutzversicherungVaBis14Tage() {
        return nfRechtschutzversicherungVaBis14Tage;
    }

    @Override
    public NumberField getNfRechtschutzversicherungVaBis22Tage() {
        return nfRechtschutzversicherungVaBis22Tage;
    }

    @Override
    public NumberField getNfReisepreissicherungProPersonTag() {
        return nfReisepreissicherungProPersonTag;
    }

    public ValidationSupport getValidationSupport() {
        if (validationSupport == null) {
            validationSupport = new ValidationSupport();
            validationSupport.setValidationDecorator(new GraphicValidationDecoration());
        }

        return validationSupport;
    }

    @Override
    public Node asNode() {
        return this;
    }

}
