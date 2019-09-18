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
package com.lagossoftware.ibbfx.fahrtkasse;

import com.lagossoftware.ibbfx.app.I18n;
import com.lagossoftware.ibbfx.app.Images;
import com.lagossoftware.ibbfx.entity.Adresse;
import com.lagossoftware.ibbfx.entity.Planung;
import com.lagossoftware.ibbfx.entity.ZahlungsartType;
import com.lagossoftware.lagosfx.common.ViewHelpers;
import com.lagossoftware.lagosfx.control.NumberField;
import com.lagossoftware.lagosfx.control.TitledSeparator;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import org.controlsfx.validation.decoration.GraphicValidationDecoration;

import java.text.DecimalFormat;

/**
 * Fahrtkasse Erstellen view implementation
 *
 * @author Cem Ikta
 */
public class FahrtkasseErstellenViewImpl extends GridPane implements FahrtkasseErstellenView {

    private TextField tfFahrtkasseNr;
    private DatePicker dpBelegdatum;
    private ComboBox<Adresse> cbAdresse;
    private ComboBox<Planung> cbPlanung;
    private Label lblPlanungTitle;
    private NumberField nfBetrag;
    private ComboBox<ZahlungsartType> cbZahlungsart;
    private DatePicker dpZahlenAm;
    private CheckBox chbAbgerechnet;

    private TextField tfStrasse;
    private TextField tfPlz;
    private TextField tfOrt;
    private TextField tfIban;
    private TextField tfBic;
    private TextField tfBank;
    private TextField tfKontoNr;
    private TextField tfBlz;

    private ValidationSupport validationSupport;

    public FahrtkasseErstellenViewImpl() {
        buildView();
    }

    private void buildView() {
        setPadding(new Insets(2, 10, 10, 50));
        setHgap(10);
        setVgap(10);

        tfFahrtkasseNr = new TextField();
        tfFahrtkasseNr.setPrefWidth(150);
        tfFahrtkasseNr.setMinWidth(150);
        tfFahrtkasseNr.setMaxWidth(150);
        tfFahrtkasseNr.setEditable(false);
        dpBelegdatum = ViewHelpers.createDatePicker();
        getValidationSupport().registerValidator(dpBelegdatum,
                Validator.createEmptyValidator(I18n.COMMON.getString("validation.required")));
        // TODO planung select modal?
        cbPlanung = new ComboBox<>();
        cbPlanung.setPrefWidth(300);
        getValidationSupport().registerValidator(cbPlanung,
                Validator.createEmptyValidator(I18n.COMMON.getString("validation.required")));
        lblPlanungTitle = new Label();
        cbAdresse = new ComboBox<>();
        cbAdresse.setPrefWidth(300);
        getValidationSupport().registerValidator(cbAdresse,
                Validator.createEmptyValidator(I18n.COMMON.getString("validation.required")));
        nfBetrag = new NumberField(new DecimalFormat("#,###,###,##0.00"));
        nfBetrag.setMinWidth(150);
        nfBetrag.setPrefWidth(150);
        nfBetrag.setMaxWidth(150);
        getValidationSupport().registerValidator(nfBetrag,
                Validator.createEmptyValidator(I18n.COMMON.getString("validation.required")));
        cbZahlungsart = new ComboBox<>();
        cbZahlungsart.getItems().addAll(
                ZahlungsartType.UEBERWEISUNG,
                ZahlungsartType.BAR,
                ZahlungsartType.SCHECK,
                ZahlungsartType.KARTENZAHLUNG);
        cbZahlungsart.setPrefWidth(150);
        getValidationSupport().registerValidator(cbZahlungsart,
                Validator.createEmptyValidator(I18n.COMMON.getString("validation.required")));
        dpZahlenAm = ViewHelpers.createDatePicker();
        chbAbgerechnet = new CheckBox(I18n.FAHRTKASSE.getString("fahrtkasse.form.abgerechnet"));

        // adresse and bank
        tfStrasse = new TextField();
        tfStrasse.setPrefWidth(300);
        tfStrasse.setEditable(false);
        tfPlz = new TextField();
        tfPlz.setPrefWidth(100);
        tfPlz.setEditable(false);
        tfOrt = new TextField();
        tfOrt.setPrefWidth(200);
        tfOrt.setEditable(false);
        tfIban = new TextField();
        tfIban.setPrefWidth(300);
        tfIban.setEditable(false);
        tfBic = new TextField();
        tfBic.setPrefWidth(300);
        tfBic.setEditable(false);
        tfBank = new TextField();
        tfBank.setPrefWidth(300);
        tfBank.setEditable(false);
        tfKontoNr = new TextField();
        tfKontoNr.setPrefWidth(300);
        tfKontoNr.setEditable(false);
        tfBlz = new TextField();
        tfBlz.setPrefWidth(300);
        tfBlz.setEditable(false);

        add(new Label(I18n.FAHRTKASSE.getString("fahrtkasse.form.fahrtkasseNr")), 0, 0);
        add(tfFahrtkasseNr, 1, 0);

        add(new Label(I18n.FAHRTKASSE.getString("fahrtkasse.form.belegdatum")), 0, 1);
        add(dpBelegdatum, 1, 1);

        add(new Label(I18n.FAHRTKASSE.getString("fahrtkasse.form.fahrtkassenempfaenger")), 0, 2);
        add(cbAdresse, 1, 2, 2, 1);

        add(new Label(I18n.FAHRTKASSE.getString("fahrtkasse.form.veranstaltung")), 0, 3);
        add(cbPlanung, 1, 3, 2, 1);
        add(ViewHelpers.createSpaceLabel(), 3, 3);
        add(new TitledSeparator(I18n.FAHRTKASSE.getString("fahrtkasse.form.zahlung")), 4, 3, 3, 1);

        add(new Label(I18n.FAHRTKASSE.getString("fahrtkasse.form.veranstaltungsTitle")), 0, 4);
        add(lblPlanungTitle, 1, 4, 2, 1);
        add(ViewHelpers.createSpaceLabel(), 3, 4);
        add(new Label(I18n.FAHRTKASSE.getString("fahrtkasse.form.zahlungsart")), 4, 4);
        add(cbZahlungsart, 5, 4, 2, 1);

        add(new Label(I18n.FAHRTKASSE.getString("fahrtkasse.form.betrag")), 0, 5);
        add(nfBetrag, 1, 5);
        add(ViewHelpers.createSpaceLabel(), 3, 5);
        add(new Label(I18n.FAHRTKASSE.getString("fahrtkasse.form.zahlenAm")), 4, 5);
        add(dpZahlenAm, 5, 5);

        // empfaengerdaten
        add(new TitledSeparator(I18n.FAHRTKASSE.getString("fahrtkasse.form.empfaengerdaten")), 0, 6, 3, 1);

        add(new Label(I18n.FAHRTKASSE.getString("fahrtkasse.form.strasse")), 0, 7);
        add(tfStrasse, 1, 7, 2, 1);

        add(new Label(I18n.FAHRTKASSE.getString("fahrtkasse.form.plzOrt")), 0, 8);
        add(tfPlz, 1, 8);
        add(tfOrt, 2, 8);

        add(new Label(I18n.FAHRTKASSE.getString("fahrtkasse.form.iban")), 0, 9);
        add(tfIban, 1, 9, 2, 1);

        add(new Label(I18n.FAHRTKASSE.getString("fahrtkasse.form.bic")), 0, 10);
        add(tfBic, 1, 10, 2, 1);

        add(new Label(I18n.FAHRTKASSE.getString("fahrtkasse.form.bank")), 0, 11);
        add(tfBank, 1, 11, 2, 1);

        add(new Label(I18n.FAHRTKASSE.getString("fahrtkasse.form.kontoNr")), 0, 12);
        add(tfKontoNr, 1, 12, 2, 1);

        add(new Label(I18n.FAHRTKASSE.getString("fahrtkasse.form.blz")), 0, 13);
        add(tfBlz, 1, 13, 2, 1);

        // status
        add(new TitledSeparator(I18n.FAHRTKASSE.getString("fahrtkasse.form.fahrtkassenstatus")), 0, 14, 3, 1);
        add(chbAbgerechnet, 1, 15, 2, 1);

    }

    @Override
    public String getTitle() {
        return I18n.FAHRTKASSE.getString("fahrtkasse.form.tabErstellen");
    }

    @Override
    public String getIconPath() {
        return Images.RAHMENDATEN_26;
    }

    @Override
    public TextField getTfFahrtkasseNr() {
        return tfFahrtkasseNr;
    }

    @Override
    public DatePicker getDpBelegdatum() {
        return dpBelegdatum;
    }

    @Override
    public ComboBox<Adresse> getCbAdresse() {
        return cbAdresse;
    }

    @Override
    public ComboBox<Planung> getCbPlanung() {
        return cbPlanung;
    }

    public Label getLblPlanungTitle() {
        return lblPlanungTitle;
    }

    @Override
    public NumberField getNfBetrag() {
        return nfBetrag;
    }

    @Override
    public ComboBox<ZahlungsartType> getCbZahlungsart() {
        return cbZahlungsart;
    }

    @Override
    public DatePicker getDpZahlenAm() {
        return dpZahlenAm;
    }

    @Override
    public CheckBox getChbAbgerechnet() {
        return chbAbgerechnet;
    }

    @Override
    public TextField getTfStrasse() {
        return tfStrasse;
    }

    @Override
    public TextField getTfPlz() {
        return tfPlz;
    }

    @Override
    public TextField getTfOrt() {
        return tfOrt;
    }

    @Override
    public TextField getTfIban() {
        return tfIban;
    }

    @Override
    public TextField getTfBic() {
        return tfBic;
    }

    @Override
    public TextField getTfBank() {
        return tfBank;
    }

    @Override
    public TextField getTfKontoNr() {
        return tfKontoNr;
    }

    @Override
    public TextField getTfBlz() {
        return tfBlz;
    }

    @Override
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
