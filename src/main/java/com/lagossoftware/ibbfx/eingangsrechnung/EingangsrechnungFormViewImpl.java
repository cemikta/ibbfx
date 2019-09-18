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
package com.lagossoftware.ibbfx.eingangsrechnung;

import com.lagossoftware.ibbfx.app.I18n;
import com.lagossoftware.ibbfx.app.Images;
import com.lagossoftware.ibbfx.entity.*;
import com.lagossoftware.lagosfx.common.ViewHelpers;
import com.lagossoftware.lagosfx.control.NumberField;
import com.lagossoftware.lagosfx.control.TitledSeparator;
import com.lagossoftware.lagosfx.mvp.AbstractFormView;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import org.controlsfx.validation.Validator;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Eingangsrechnung form view implementation
 *
 * @author Cem Ikta
 */
public class EingangsrechnungFormViewImpl extends AbstractFormView<Eingangsrechnung>
        implements EingangsrechnungFormView {

    private Button btnPrintPreview;
    private Button btnPrint;
    private TextField tfBelegNr;
    private DatePicker dpBelegdatum;
    private TextField tfRechnungsNr;
    private DatePicker dpRechnungsdatum;
    private TextField tfLieferant;
    private TextField tfGegenstand;
    private NumberField nfBetrag;
    private ComboBox<Waehrung> cbWaehrung;
    private ComboBox<Adresse> cbAdresse;
    private ComboBox<ZahlungsstatusType> cbZahlungsstatus;
    private TextArea tfNotizen;

    public EingangsrechnungFormViewImpl() {
        super();
        buildView();
    }

    private void buildView() {
        btnPrintPreview = ViewHelpers.createIconButton(Images.PRINT_PREVIEW_24,
                I18n.COMMON.getString("action.printPreview"));
        btnPrintPreview.getStyleClass().add("left-pill");

        btnPrint = ViewHelpers.createIconButton(Images.PRINT_24,
                I18n.COMMON.getString("action.print"));
        btnPrint.getStyleClass().add("right-pill");
        HBox.setMargin(btnPrint, new Insets(0, 10, 0, 0));

        getToolBar().getChildren().addAll(btnPrintPreview, btnPrint);
    }
    
    @Override
    public GridPane getFormPane() {
        GridPane formPane = new GridPane();
        formPane.setPadding(new Insets(2, 10, 10, 50));
        formPane.setHgap(10);
        formPane.setVgap(10);
        
        NumberFormat longFormat = NumberFormat.getNumberInstance();
        longFormat.setGroupingUsed(false);
        tfBelegNr = new TextField();
        tfBelegNr.setPrefWidth(150);
        tfBelegNr.setMinWidth(150);
        tfBelegNr.setMaxWidth(150);
        tfBelegNr.setEditable(false);
        dpBelegdatum = new DatePicker();
        dpBelegdatum.setPromptText("TT.MM.JJJJ");
        dpBelegdatum.setPrefWidth(150);
        dpBelegdatum.setShowWeekNumbers(true);
        getValidationSupport().registerValidator(dpBelegdatum, 
                Validator.createEmptyValidator(I18n.COMMON.getString("validation.required")));
        tfRechnungsNr = new TextField();
        tfRechnungsNr.setPrefWidth(300);
        getValidationSupport().registerValidator(tfRechnungsNr, 
                Validator.createEmptyValidator(I18n.COMMON.getString("validation.required")));
        dpRechnungsdatum = new DatePicker();
        dpRechnungsdatum.setPromptText("TT.MM.JJJJ");
        dpRechnungsdatum.setPrefWidth(150);
        dpRechnungsdatum.setShowWeekNumbers(true);
        getValidationSupport().registerValidator(dpRechnungsdatum, 
                Validator.createEmptyValidator(I18n.COMMON.getString("validation.required")));
        tfLieferant = new TextField();
        tfLieferant.setPrefWidth(300);
        GridPane.setHgrow(tfLieferant, Priority.ALWAYS);
        getValidationSupport().registerValidator(tfLieferant, 
                Validator.createEmptyValidator(I18n.COMMON.getString("validation.required")));
        tfGegenstand = new TextField();
        tfGegenstand.setPrefWidth(300);
        getValidationSupport().registerValidator(tfGegenstand, 
                Validator.createEmptyValidator(I18n.COMMON.getString("validation.required")));
        nfBetrag = new NumberField(new DecimalFormat("#,###,###,##0.00"));
        nfBetrag.setMinWidth(150);
        nfBetrag.setPrefWidth(150);
        nfBetrag.setMaxWidth(150);
        getValidationSupport().registerValidator(nfBetrag,
                Validator.createEmptyValidator(I18n.COMMON.getString("validation.required")));
        
        cbWaehrung = new ComboBox<>();
        cbWaehrung.setPrefWidth(100);
        getValidationSupport().registerValidator(cbWaehrung, 
                Validator.createEmptyValidator(I18n.COMMON.getString("validation.required")));
        cbAdresse = new ComboBox<>();
        cbAdresse.setPrefWidth(300);

        cbZahlungsstatus = new ComboBox<>();
        cbZahlungsstatus.getItems().addAll(
                ZahlungsstatusType.OFFEN,
                ZahlungsstatusType.BEZAHLT);
        cbZahlungsstatus.setPrefWidth(300);
        getValidationSupport().registerValidator(cbZahlungsstatus,
                Validator.createEmptyValidator(I18n.COMMON.getString("validation.required")));
        tfNotizen = new TextArea();
        tfNotizen.setPrefWidth(300);
        tfNotizen.setPrefRowCount(5);

        formPane.add(new Label(I18n.EINGANGSRECHNUNG.getString("eingangsrechnung.form.belegNr")), 0, 0);
        formPane.add(tfBelegNr, 1, 0);
        formPane.add(new Label(I18n.EINGANGSRECHNUNG.getString("eingangsrechnung.form.belegdatum")), 0, 1);
        formPane.add(dpBelegdatum, 1, 1);
        
        formPane.add(new TitledSeparator(I18n.EINGANGSRECHNUNG.getString("eingangsrechnung.form.rechnungsdaten")), 0, 2, 3, 1);
        formPane.add(new Label(I18n.EINGANGSRECHNUNG.getString("eingangsrechnung.form.rechnungsNr")), 0, 3);
        formPane.add(tfRechnungsNr, 1, 3, 2, 1);
        formPane.add(new Label(I18n.EINGANGSRECHNUNG.getString("eingangsrechnung.form.rechnungsdatum")), 0, 4);
        formPane.add(dpRechnungsdatum, 1, 4);
        formPane.add(new Label(I18n.EINGANGSRECHNUNG.getString("eingangsrechnung.form.lieferant")), 0, 5);
        formPane.add(tfLieferant, 1, 5, 2, 1);
        formPane.add(new Label(I18n.EINGANGSRECHNUNG.getString("eingangsrechnung.form.gegenstand")), 0, 6);
        formPane.add(tfGegenstand, 1, 6, 2, 1);
        formPane.add(new Label(I18n.EINGANGSRECHNUNG.getString("eingangsrechnung.form.betrag")), 0, 7);
        formPane.add(nfBetrag, 1, 7);
        formPane.add(cbWaehrung, 2, 7);

        formPane.add(new TitledSeparator(I18n.EINGANGSRECHNUNG.getString("eingangsrechnung.form.weitergeleitetAn")), 0, 8, 3, 1);
        formPane.add(cbAdresse, 1, 9, 2, 1);

        formPane.add(new TitledSeparator(I18n.EINGANGSRECHNUNG.getString("eingangsrechnung.form.status")), 0, 10, 3, 1);
        formPane.add(cbZahlungsstatus, 1, 11, 2, 1);

        formPane.add(new TitledSeparator(I18n.EINGANGSRECHNUNG.getString("eingangsrechnung.form.notizen")), 0, 12, 3, 1);
        formPane.add(tfNotizen, 0, 13, 3, 1);
        
        return formPane;
    }

    @Override
    public String getHeaderTitle() {
        return I18n.EINGANGSRECHNUNG.getString("eingangsrechnung.form.title");
    }

    @Override
    public Button getBtnPrintPreview() {
        return btnPrintPreview;
    }

    @Override
    public Button getBtnPrint() {
        return btnPrint;
    }

    @Override
    public TextField getTfBelegNr() {
        return tfBelegNr;
    }

    @Override
    public DatePicker getDpBelegdatum() {
        return dpBelegdatum;
    }

    @Override
    public TextField getTfRechnungsNr() {
        return tfRechnungsNr;
    }

    @Override
    public DatePicker getDpRechnungsdatum() {
        return dpRechnungsdatum;
    }

    @Override
    public TextField getTfLieferant() {
        return tfLieferant;
    }

    @Override
    public TextField getTfGegenstand() {
        return tfGegenstand;
    }

    @Override
    public NumberField getNfBetrag() {
        return nfBetrag;
    }

    @Override
    public ComboBox<Waehrung> getCbWaehrung() {
        return cbWaehrung;
    }

    @Override
    public ComboBox<Adresse> getCbAdresse() {
        return cbAdresse;
    }

    @Override
    public ComboBox<ZahlungsstatusType> getCbZahlungsstatus() {
        return cbZahlungsstatus;
    }

    @Override
    public TextArea getTfNotizen() {
        return tfNotizen;
    }

}
