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
import com.lagossoftware.ibbfx.entity.Params;
import com.lagossoftware.ibbfx.service.ParamsService;
import com.lagossoftware.lagosfx.common.ViewHelpers;
import com.lagossoftware.lagosfx.control.MessageBox;
import com.lagossoftware.lagosfx.control.NumberField;
import com.lagossoftware.lagosfx.control.TitledSeparator;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import org.controlsfx.validation.decoration.GraphicValidationDecoration;

import java.io.*;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.text.DecimalFormat;

/**
 * PlanungParams dialog
 *
 * @author Cem Ikta
 */
public class PlanungParamsDialog extends Dialog<PlanungParamsModel> {

    private PlanungParamsModel planungParamsModel = null;
    private ParamsService paramsService;
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

    private ValidationSupport validationSupport;

    public PlanungParamsDialog(PlanungParamsModel planungParamsModel, ParamsService paramsService) {
        this.planungParamsModel = planungParamsModel;
        this.paramsService = paramsService;
        buildView();
    }

    private void buildView() {
        setTitle(I18n.PLANUNG.getString("planungParamsDialog.title"));
        setHeaderText(I18n.PLANUNG.getString("planungParamsDialog.header"));
        setGraphic(ViewHelpers.createImageView(Images.PLANUNG_PARAMS_HEADER_32));

        TabPane tabPanePages = new TabPane(buildParamsTab(), buildFormulaTab());
        tabPanePages.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        tabPanePages.getStyleClass().add(TabPane.STYLE_CLASS_FLOATING);
        tabPanePages.setPrefSize(900, 550);
        tabPanePages.setMinSize(TabPane.USE_PREF_SIZE, TabPane.USE_PREF_SIZE);
        tabPanePages.setMaxSize(TabPane.USE_PREF_SIZE, TabPane.USE_PREF_SIZE);


        ButtonType btnTypeSetDefaults = new ButtonType(I18n.PLANUNG.getString("planungParamsDialog.btnTypeSetDefaults"),
                ButtonBar.ButtonData.HELP);

        ButtonType btnTypeApply = new ButtonType(I18n.COMMON.getString("action.apply"),
                ButtonBar.ButtonData.OK_DONE);

        getDialogPane().getButtonTypes().addAll(btnTypeSetDefaults, btnTypeApply, ButtonType.CANCEL);


        final Button btnSetDefaults = (Button) getDialogPane().lookupButton(btnTypeSetDefaults);
        btnSetDefaults.setTooltip(new Tooltip(I18n.PLANUNG.getString("planungParamsDialog.btnTypeSetDefaults.tooltip")));
        btnSetDefaults.setPrefWidth(150);
        btnSetDefaults.addEventFilter(ActionEvent.ACTION, (event) -> {
            onSetDefaults();
            event.consume();
        });

        final Button btnApply = (Button) getDialogPane().lookupButton(btnTypeApply);
        btnApply.setPrefWidth(150);
        btnApply.addEventFilter(ActionEvent.ACTION, (event) -> {
            if (validationSupport.isInvalid()) {
                event.consume();
            }
        });

        getDialogPane().setContent(tabPanePages);

        Platform.runLater(() -> nfHonorareFuerIbbBegleitung.requestFocus());

        setResultConverter(dialogButton -> {
            if (dialogButton == btnTypeApply) {
                if (!validationSupport.isInvalid()) {
                    push();
                    return planungParamsModel;
                }
            }
            return null;
        });
    }

    private Tab buildParamsTab() {
        Tab paramsTab = new Tab();
        paramsTab.setText(I18n.PLANUNG.getString("planungParamsDialog.paramsPage"));

        validationSupport = new ValidationSupport();
        validationSupport.setValidationDecorator(new GraphicValidationDecoration());

        nfHonorareFuerIbbBegleitung = new NumberField(new DecimalFormat("#,###,###,##0.00"));
        validationSupport.registerValidator(nfHonorareFuerIbbBegleitung,
                Validator.createEmptyValidator(I18n.COMMON.getString("validation.required")));

        nfIbbStrukturkostenProTNProTag = new NumberField(new DecimalFormat("#,###,###,##0.00"));
        validationSupport.registerValidator(nfIbbStrukturkostenProTNProTag,
                Validator.createEmptyValidator(I18n.COMMON.getString("validation.required")));

        nfIbbErloes = new NumberField(new DecimalFormat("#,##0.00"));
        validationSupport.registerValidator(nfIbbErloes,
                Validator.createEmptyValidator(I18n.COMMON.getString("validation.required")));

        // versicherung
        nfHaftpflichtUnfallVersicherungProPersonTag = new NumberField(new DecimalFormat("#,##0.00"));
        validationSupport.registerValidator(nfHaftpflichtUnfallVersicherungProPersonTag,
                Validator.createEmptyValidator(I18n.COMMON.getString("validation.required")));

        nfAuslandsreisekrankenVersicherungProPersonTag = new NumberField(new DecimalFormat("#,##0.00"));
        validationSupport.registerValidator(nfAuslandsreisekrankenVersicherungProPersonTag,
                Validator.createEmptyValidator(I18n.COMMON.getString("validation.required")));

        nfKrankenversicherungFuerAuslTNProPersonTag = new NumberField(new DecimalFormat("#,##0.00"));
        validationSupport.registerValidator(nfKrankenversicherungFuerAuslTNProPersonTag,
                Validator.createEmptyValidator(I18n.COMMON.getString("validation.required")));

        nfRegressversicherungVaInDeBis8Tage = new NumberField(new DecimalFormat("#,##0.00"));
        validationSupport.registerValidator(nfRegressversicherungVaInDeBis8Tage,
                Validator.createEmptyValidator(I18n.COMMON.getString("validation.required")));

        nfRegressversicherungVaInDeBis22Tage = new NumberField(new DecimalFormat("#,##0.00"));
        validationSupport.registerValidator(nfRegressversicherungVaInDeBis22Tage,
                Validator.createEmptyValidator(I18n.COMMON.getString("validation.required")));

        nfRegressversicherungVaInDeBis42Tage = new NumberField(new DecimalFormat("#,##0.00"));
        validationSupport.registerValidator(nfRegressversicherungVaInDeBis42Tage,
                Validator.createEmptyValidator(I18n.COMMON.getString("validation.required")));

        nfRegressversicherungVaImAuslandBis8Tage = new NumberField(new DecimalFormat("#,##0.00"));
        validationSupport.registerValidator(nfRegressversicherungVaImAuslandBis8Tage,
                Validator.createEmptyValidator(I18n.COMMON.getString("validation.required")));

        nfRegressversicherungVaImAuslandBis22Tage = new NumberField(new DecimalFormat("#,##0.00"));
        validationSupport.registerValidator(nfRegressversicherungVaImAuslandBis22Tage,
                Validator.createEmptyValidator(I18n.COMMON.getString("validation.required")));

        nfRegressversicherungVaImAuslandBis42Tage = new NumberField(new DecimalFormat("#,##0.00"));
        validationSupport.registerValidator(nfRegressversicherungVaImAuslandBis42Tage,
                Validator.createEmptyValidator(I18n.COMMON.getString("validation.required")));

        nfRechtschutzversicherungVaBis8Tage = new NumberField(new DecimalFormat("#,##0.00"));
        validationSupport.registerValidator(nfRechtschutzversicherungVaBis8Tage,
                Validator.createEmptyValidator(I18n.COMMON.getString("validation.required")));

        nfRechtschutzversicherungVaBis14Tage = new NumberField(new DecimalFormat("#,##0.00"));
        validationSupport.registerValidator(nfRechtschutzversicherungVaBis14Tage,
                Validator.createEmptyValidator(I18n.COMMON.getString("validation.required")));

        nfRechtschutzversicherungVaBis22Tage = new NumberField(new DecimalFormat("#,##0.00"));
        validationSupport.registerValidator(nfRechtschutzversicherungVaBis22Tage,
                Validator.createEmptyValidator(I18n.COMMON.getString("validation.required")));

        nfReisepreissicherungProPersonTag = new NumberField(new DecimalFormat("#,##0.00"));
        validationSupport.registerValidator(nfReisepreissicherungProPersonTag,
                Validator.createEmptyValidator(I18n.COMMON.getString("validation.required")));

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20, 50, 20, 20));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        gridPane.add(new TitledSeparator(I18n.PLANUNG.getString("planungParamsDialog.honorarkosten")), 0, 0, 2, 1);
        gridPane.add(new Label(I18n.PLANUNG.getString("planungParamsDialog.honorareFuerIbbBegleitung")), 0, 1);
        gridPane.add(nfHonorareFuerIbbBegleitung, 1, 1);

        gridPane.add(new TitledSeparator(I18n.PLANUNG.getString("planungParamsDialog.ibbStrukturkosten")), 0, 2, 2, 1);
        gridPane.add(new Label(I18n.PLANUNG.getString("planungParamsDialog.ibbStrukturkostenProTNProTag")), 0, 3);
        gridPane.add(nfIbbStrukturkostenProTNProTag, 1, 3);

        gridPane.add(new TitledSeparator(I18n.PLANUNG.getString("planungParamsDialog.ibbErloesTitle")), 0, 4, 2, 1);
        gridPane.add(new Label(I18n.PLANUNG.getString("planungParamsDialog.ibbErloes")), 0, 5);
        gridPane.add(nfIbbErloes, 1, 5);

        gridPane.add(new TitledSeparator(I18n.PLANUNG.getString("planungParamsDialog.versicherungTitle")), 0, 6, 2, 1);
        gridPane.add(new Label(I18n.PLANUNG.getString("planungParamsDialog.haftpflichtUnfallVersicherungProPersonTag")), 0, 7);
        gridPane.add(nfHaftpflichtUnfallVersicherungProPersonTag, 1, 7);

        gridPane.add(new Label(I18n.PLANUNG.getString("planungParamsDialog.auslandsreisekrankenVersicherungProPersonTag")), 0, 8);
        gridPane.add(nfAuslandsreisekrankenVersicherungProPersonTag, 1, 8);

        gridPane.add(new Label(I18n.PLANUNG.getString("planungParamsDialog.krankenversicherungFuerAuslTNProPersonTag")), 0, 9);
        gridPane.add(nfKrankenversicherungFuerAuslTNProPersonTag, 1, 9);

        gridPane.add(new TitledSeparator(I18n.PLANUNG.getString("planungParamsDialog.regressversicherungInDeutschland")), 0, 10, 2, 1);
        gridPane.add(ViewHelpers.createSpaceLabel(), 2, 10);
        gridPane.add(new TitledSeparator(I18n.PLANUNG.getString("planungParamsDialog.regressversicherungImAusland")), 3, 10, 2, 1);

        gridPane.add(new Label(I18n.PLANUNG.getString("planungParamsDialog.regressversicherungVaInDeBis8Tage")), 0, 11);
        gridPane.add(nfRegressversicherungVaInDeBis8Tage, 1, 11);
        gridPane.add(ViewHelpers.createSpaceLabel(), 2, 11);
        gridPane.add(new Label(I18n.PLANUNG.getString("planungParamsDialog.regressversicherungVaImAuslandBis8Tage")), 3, 11);
        gridPane.add(nfRegressversicherungVaImAuslandBis8Tage, 4, 11);

        gridPane.add(new Label(I18n.PLANUNG.getString("planungParamsDialog.regressversicherungVaInDeBis22Tage")), 0, 12);
        gridPane.add(nfRegressversicherungVaInDeBis22Tage, 1, 12);
        gridPane.add(ViewHelpers.createSpaceLabel(), 2, 12);
        gridPane.add(new Label(I18n.PLANUNG.getString("planungParamsDialog.regressversicherungVaImAuslandBis22Tage")), 3, 12);
        gridPane.add(nfRegressversicherungVaImAuslandBis22Tage, 4, 12);

        gridPane.add(new Label(I18n.PLANUNG.getString("planungParamsDialog.regressversicherungVaInDeBis42Tage")), 0, 13);
        gridPane.add(nfRegressversicherungVaInDeBis42Tage, 1, 13);
        gridPane.add(ViewHelpers.createSpaceLabel(), 2, 13);
        gridPane.add(new Label(I18n.PLANUNG.getString("planungParamsDialog.regressversicherungVaImAuslandBis42Tage")), 3, 13);
        gridPane.add(nfRegressversicherungVaImAuslandBis42Tage, 4, 13);

        gridPane.add(new TitledSeparator(I18n.PLANUNG.getString("planungParamsDialog.rechtschutzversicherung")), 0, 14, 2, 1);
        gridPane.add(new Label(I18n.PLANUNG.getString("planungParamsDialog.rechtschutzversicherungVaBis8Tage")), 0, 15);
        gridPane.add(nfRechtschutzversicherungVaBis8Tage, 1, 15);

        gridPane.add(new Label(I18n.PLANUNG.getString("planungParamsDialog.rechtschutzversicherungVaBis14Tage")), 0, 16);
        gridPane.add(nfRechtschutzversicherungVaBis14Tage, 1, 16);

        gridPane.add(new Label(I18n.PLANUNG.getString("planungParamsDialog.rechtschutzversicherungVaBis22Tage")), 0, 17);
        gridPane.add(nfRechtschutzversicherungVaBis22Tage, 1, 17);

        gridPane.add(new TitledSeparator(I18n.PLANUNG.getString("planungParamsDialog.reisepreissicherung")), 0, 18, 2, 1);
        gridPane.add(new Label(I18n.PLANUNG.getString("planungParamsDialog.reisepreissicherungProPersonTag")), 0, 19);
        gridPane.add(nfReisepreissicherungProPersonTag, 1, 19);

        paramsTab.setContent(new ScrollPane(gridPane));

        return paramsTab;
    }

    private Tab buildFormulaTab() {
        Tab formulaTab = new Tab();
        formulaTab.setText(I18n.PLANUNG.getString("planungParamsDialog.formulaPage"));
        TextArea taFormula = new TextArea();
        taFormula.setEditable(false);

        try {
            taFormula.setText(new String(Files.readAllBytes(
                    Paths.get(PlanungParamsDialog.class.getResource("/formula/Formula.txt").toURI()))));
        } catch (IOException | URISyntaxException e) {
            MessageBox.get()
                    .contentText(I18n.PLANUNG.getString("planungParamsDialog.formulaNotFoundException"))
                    .showError(e);
        }

        formulaTab.setContent(taFormula);

        return formulaTab;
    }

    private void onSetDefaults() {
        Params params = paramsService.find(1L);
        planungParamsModel.setHonorareFuerIbbBegleitung(params.getHonorareFuerIbbBegleitung());
        planungParamsModel.setIbbStrukturkostenProTNProTag(params.getIbbStrukturkostenProTNProTag());
        planungParamsModel.setIbbErloes(params.getIbbErloes());
        // versicherung
        planungParamsModel.setHaftpflichtUnfallVersicherungProPersonTag(params.getHaftpflichtUnfallVersicherungProPersonTag());
        planungParamsModel.setAuslandsreisekrankenVersicherungProPersonTag(params.getAuslandsreisekrankenVersicherungProPersonTag());
        planungParamsModel.setKrankenversicherungFuerAuslTNProPersonTag(params.getKrankenversicherungFuerAuslTNProPersonTag());
        planungParamsModel.setRegressversicherungVaInDeBis8Tage(params.getRegressversicherungVaInDeBis8Tage());
        planungParamsModel.setRegressversicherungVaInDeBis22Tage(params.getRegressversicherungVaInDeBis22Tage());
        planungParamsModel.setRegressversicherungVaInDeBis42Tage(params.getRegressversicherungVaInDeBis42Tage());
        planungParamsModel.setRegressversicherungVaImAuslandBis8Tage(params.getRegressversicherungVaImAuslandBis8Tage());
        planungParamsModel.setRegressversicherungVaImAuslandBis22Tage(params.getRegressversicherungVaImAuslandBis22Tage());
        planungParamsModel.setRegressversicherungVaImAuslandBis42Tage(params.getRegressversicherungVaImAuslandBis42Tage());
        planungParamsModel.setRechtschutzversicherungVaBis8Tage(params.getRechtschutzversicherungVaBis8Tage());
        planungParamsModel.setRechtschutzversicherungVaBis14Tage(params.getRechtschutzversicherungVaBis14Tage());
        planungParamsModel.setRechtschutzversicherungVaBis22Tage(params.getRechtschutzversicherungVaBis22Tage());
        planungParamsModel.setReisepreissicherungProPersonTag(params.getReisepreissicherungProPersonTag());

        pop();
    }

    public void pop() {
        nfHonorareFuerIbbBegleitung.setValue(planungParamsModel.getHonorareFuerIbbBegleitung() != null ?
                planungParamsModel.getHonorareFuerIbbBegleitung() : BigDecimal.ZERO);
        nfIbbStrukturkostenProTNProTag.setValue(planungParamsModel.getIbbStrukturkostenProTNProTag() != null ?
                planungParamsModel.getIbbStrukturkostenProTNProTag() : BigDecimal.ZERO);
        nfIbbErloes.setValue(planungParamsModel.getIbbErloes() != null ?
                planungParamsModel.getIbbErloes() : BigDecimal.ZERO);

        // versicherung
        nfHaftpflichtUnfallVersicherungProPersonTag.setValue(planungParamsModel.getHaftpflichtUnfallVersicherungProPersonTag() != null ?
                planungParamsModel.getHaftpflichtUnfallVersicherungProPersonTag() : BigDecimal.ZERO);

        nfAuslandsreisekrankenVersicherungProPersonTag.setValue(planungParamsModel.getAuslandsreisekrankenVersicherungProPersonTag() != null ?
                planungParamsModel.getAuslandsreisekrankenVersicherungProPersonTag() : BigDecimal.ZERO);

        nfKrankenversicherungFuerAuslTNProPersonTag.setValue(planungParamsModel.getKrankenversicherungFuerAuslTNProPersonTag() != null ?
                planungParamsModel.getKrankenversicherungFuerAuslTNProPersonTag() : BigDecimal.ZERO);

        nfRegressversicherungVaInDeBis8Tage.setValue(planungParamsModel.getRegressversicherungVaInDeBis8Tage() != null ?
                planungParamsModel.getRegressversicherungVaInDeBis8Tage() : BigDecimal.ZERO);

        nfRegressversicherungVaInDeBis22Tage.setValue(planungParamsModel.getRegressversicherungVaInDeBis22Tage() != null ?
                planungParamsModel.getRegressversicherungVaInDeBis22Tage() : BigDecimal.ZERO);

        nfRegressversicherungVaInDeBis42Tage.setValue(planungParamsModel.getRegressversicherungVaInDeBis42Tage() != null ?
                planungParamsModel.getRegressversicherungVaInDeBis42Tage() : BigDecimal.ZERO);

        nfRegressversicherungVaImAuslandBis8Tage.setValue(planungParamsModel.getRegressversicherungVaImAuslandBis8Tage() != null ?
                planungParamsModel.getRegressversicherungVaImAuslandBis8Tage() : BigDecimal.ZERO);

        nfRegressversicherungVaImAuslandBis22Tage.setValue(planungParamsModel.getRegressversicherungVaImAuslandBis22Tage() != null ?
                planungParamsModel.getRegressversicherungVaImAuslandBis22Tage() : BigDecimal.ZERO);

        nfRegressversicherungVaImAuslandBis42Tage.setValue(planungParamsModel.getRegressversicherungVaImAuslandBis42Tage() != null ?
                planungParamsModel.getRegressversicherungVaImAuslandBis42Tage() : BigDecimal.ZERO);

        nfRechtschutzversicherungVaBis8Tage.setValue(planungParamsModel.getRechtschutzversicherungVaBis8Tage() != null ?
                planungParamsModel.getRechtschutzversicherungVaBis8Tage() : BigDecimal.ZERO);

        nfRechtschutzversicherungVaBis14Tage.setValue(planungParamsModel.getRechtschutzversicherungVaBis14Tage() != null ?
                planungParamsModel.getRechtschutzversicherungVaBis14Tage() : BigDecimal.ZERO);

        nfRechtschutzversicherungVaBis22Tage.setValue(planungParamsModel.getRechtschutzversicherungVaBis22Tage() != null ?
                planungParamsModel.getRechtschutzversicherungVaBis22Tage() : BigDecimal.ZERO);

        nfReisepreissicherungProPersonTag.setValue(planungParamsModel.getReisepreissicherungProPersonTag() != null ?
                planungParamsModel.getReisepreissicherungProPersonTag() : BigDecimal.ZERO);
    }

    public void push() {
        planungParamsModel.setHonorareFuerIbbBegleitung(nfHonorareFuerIbbBegleitung.getValue());
        planungParamsModel.setIbbStrukturkostenProTNProTag(nfIbbStrukturkostenProTNProTag.getValue());
        planungParamsModel.setIbbErloes(nfIbbErloes.getValue());
        // versicherung
        planungParamsModel.setHaftpflichtUnfallVersicherungProPersonTag(nfHaftpflichtUnfallVersicherungProPersonTag.getValue());
        planungParamsModel.setAuslandsreisekrankenVersicherungProPersonTag(nfAuslandsreisekrankenVersicherungProPersonTag.getValue());
        planungParamsModel.setKrankenversicherungFuerAuslTNProPersonTag(nfKrankenversicherungFuerAuslTNProPersonTag.getValue());
        planungParamsModel.setRegressversicherungVaInDeBis8Tage(nfRegressversicherungVaInDeBis8Tage.getValue());
        planungParamsModel.setRegressversicherungVaInDeBis22Tage(nfRegressversicherungVaInDeBis22Tage.getValue());
        planungParamsModel.setRegressversicherungVaInDeBis42Tage(nfRegressversicherungVaInDeBis42Tage.getValue());
        planungParamsModel.setRegressversicherungVaImAuslandBis8Tage(nfRegressversicherungVaImAuslandBis8Tage.getValue());
        planungParamsModel.setRegressversicherungVaImAuslandBis22Tage(nfRegressversicherungVaImAuslandBis22Tage.getValue());
        planungParamsModel.setRegressversicherungVaImAuslandBis42Tage(nfRegressversicherungVaImAuslandBis42Tage.getValue());
        planungParamsModel.setRechtschutzversicherungVaBis8Tage(nfRechtschutzversicherungVaBis8Tage.getValue());
        planungParamsModel.setRechtschutzversicherungVaBis14Tage(nfRechtschutzversicherungVaBis14Tage.getValue());
        planungParamsModel.setRechtschutzversicherungVaBis22Tage(nfRechtschutzversicherungVaBis22Tage.getValue());
        planungParamsModel.setReisepreissicherungProPersonTag(nfReisepreissicherungProPersonTag.getValue());
    }

}
