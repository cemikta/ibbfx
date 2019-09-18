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
import com.lagossoftware.ibbfx.entity.Arbeitsaufwand;
import com.lagossoftware.ibbfx.entity.Fachbereich;
import com.lagossoftware.ibbfx.entity.Geschaeftsbereich;
import com.lagossoftware.ibbfx.entity.Zgfaktor;
import com.lagossoftware.lagosfx.common.ViewHelpers;
import com.lagossoftware.lagosfx.control.IntegerField;
import com.lagossoftware.lagosfx.control.NumberField;
import com.lagossoftware.lagosfx.control.TitledSeparator;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import org.apache.commons.lang3.StringUtils;
import org.controlsfx.validation.ValidationResult;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import org.controlsfx.validation.decoration.GraphicValidationDecoration;

import java.text.DecimalFormat;

/**
 * Planung Rahmendaten view implementation
 *
 * @author Cem Ikta
 */
public class PlanungRahmendatenViewImpl extends GridPane implements PlanungRahmendatenView {

    private IntegerField nfPlanungNr;
    private TextField tfZusatz;
    private TextField tfTitel;
    private TextField tfVeranstaltungOrt;
    private DatePicker dpVeranstaltungBeginn;
    private DatePicker dpVeranstaltungEnde;
    private TextField tfVorbereitungstreffenOrt;
    private DatePicker dpVorbereitungstreffenBeginn;
    private DatePicker dpVorbereitungstreffenEnde;
    private TextField tfNachbereitungstreffenOrt;
    private DatePicker dpNachbereitungstreffenBeginn;
    private DatePicker dpNachbereitungstreffenEnde;

    // bereiche
    private ComboBox<Fachbereich> cbFachbereich;
    private ComboBox<Geschaeftsbereich> cbGeschaeftsbereich;
    private ComboBox<Arbeitsaufwand> cbArbeitsaufwand;
    private NumberField nfArbeitsaufwandAufschlag;
    private IntegerField nfTageNachWbg;
    private ComboBox<Zgfaktor> cbZgfaktor;
    private NumberField nfZgfaktorAufschlag;

    private ValidationSupport validationSupport;

    public PlanungRahmendatenViewImpl() {
        buildView();
    }

    private void buildView() {
        setPadding(new Insets(2, 10, 10, 50));
        setHgap(10);
        setVgap(10);

        nfPlanungNr = new IntegerField();
        nfPlanungNr.setMinWidth(150);
        nfPlanungNr.setPrefWidth(150);
        nfPlanungNr.setMaxWidth(150);
        getValidationSupport().registerValidator(nfPlanungNr,
                Validator.createEmptyValidator(I18n.COMMON.getString("validation.required")));
        tfZusatz = new TextField();
        tfZusatz.setPrefWidth(150);
        // only 1-5 uppercase letters
        String zusatzRegex = "[A-ZßÄÖÜ]{1,5}";
        getValidationSupport().registerValidator(tfZusatz, (Control c, String newValue) -> {
            if (StringUtils.isEmpty(newValue)) {
                newValue = " ";
            }
            return ValidationResult.fromErrorIf(tfZusatz,
                    I18n.PLANUNG.getString("planung.form.zusatzError"),
                    !(newValue.matches(zusatzRegex)));
        });
        tfTitel = new TextField();
        tfTitel.setPrefWidth(300);
        getValidationSupport().registerValidator(tfTitel,
                Validator.createEmptyValidator(I18n.COMMON.getString("validation.required")));

        tfVeranstaltungOrt = new TextField();
        tfVeranstaltungOrt.setPrefWidth(300);
        getValidationSupport().registerValidator(tfVeranstaltungOrt,
                Validator.createEmptyValidator(I18n.COMMON.getString("validation.required")));
        dpVeranstaltungBeginn = ViewHelpers.createDatePicker();
        getValidationSupport().registerValidator(dpVeranstaltungBeginn,
                Validator.createEmptyValidator(I18n.COMMON.getString("validation.required")));
        dpVeranstaltungEnde = ViewHelpers.createDatePicker();
        getValidationSupport().registerValidator(dpVeranstaltungEnde,
                Validator.createEmptyValidator(I18n.COMMON.getString("validation.required")));

        tfVorbereitungstreffenOrt = new TextField();
        tfVorbereitungstreffenOrt.setPrefWidth(300);
        dpVorbereitungstreffenBeginn = ViewHelpers.createDatePicker();
        dpVorbereitungstreffenEnde = ViewHelpers.createDatePicker();

        tfNachbereitungstreffenOrt = new TextField();
        tfNachbereitungstreffenOrt.setPrefWidth(300);
        dpNachbereitungstreffenBeginn = ViewHelpers.createDatePicker();
        dpNachbereitungstreffenEnde = ViewHelpers.createDatePicker();

        // berecihe
        cbFachbereich = new ComboBox<>();
        cbFachbereich.setPrefWidth(300);
        getValidationSupport().registerValidator(cbFachbereich,
                Validator.createEmptyValidator(I18n.COMMON.getString("validation.required")));

        cbGeschaeftsbereich = new ComboBox<>();
        cbGeschaeftsbereich.setPrefWidth(300);
        getValidationSupport().registerValidator(cbGeschaeftsbereich,
                Validator.createEmptyValidator(I18n.COMMON.getString("validation.required")));

        cbArbeitsaufwand = new ComboBox<>();
        cbArbeitsaufwand.setPrefWidth(300);
        getValidationSupport().registerValidator(cbArbeitsaufwand,
                Validator.createEmptyValidator(I18n.COMMON.getString("validation.required")));

        nfArbeitsaufwandAufschlag = new NumberField(new DecimalFormat("#,##0.00"));
        nfArbeitsaufwandAufschlag.setMinWidth(150);
        nfArbeitsaufwandAufschlag.setPrefWidth(150);
        nfArbeitsaufwandAufschlag.setMaxWidth(150);

        nfTageNachWbg = new IntegerField();
        nfTageNachWbg.setMinWidth(150);
        nfTageNachWbg.setPrefWidth(150);
        nfTageNachWbg.setMaxWidth(150);
        getValidationSupport().registerValidator(nfTageNachWbg,
                Validator.createEmptyValidator(I18n.COMMON.getString("validation.required")));

        cbZgfaktor = new ComboBox<>();
        cbZgfaktor.setPrefWidth(300);
        getValidationSupport().registerValidator(cbZgfaktor,
                Validator.createEmptyValidator(I18n.COMMON.getString("validation.required")));
        nfZgfaktorAufschlag = new NumberField(new DecimalFormat("#,##0.00"));
        nfZgfaktorAufschlag.setMinWidth(150);
        nfZgfaktorAufschlag.setPrefWidth(150);
        nfZgfaktorAufschlag.setMaxWidth(150);

        // 1.block
        add(new Label(I18n.PLANUNG.getString("planung.form.planungNrZusatz")), 0, 0);
        add(nfPlanungNr, 1, 0);
        add(tfZusatz, 2, 0);
        add(new Label(I18n.PLANUNG.getString("planung.form.titel")), 0, 1);
        add(tfTitel, 1, 1, 2, 1);

        add(new TitledSeparator(I18n.PLANUNG.getString("planung.form.veranstaltung")), 0, 2, 3, 1);
        add(ViewHelpers.createSpaceLabel(), 3, 2);

        add(new Label(I18n.PLANUNG.getString("planung.form.ort")), 0, 3);
        add(tfVeranstaltungOrt, 1, 3, 2, 1);
        add(ViewHelpers.createSpaceLabel(), 3, 3);

        add(new Label(I18n.PLANUNG.getString("planung.form.beginnEnde")), 0, 4);
        add(dpVeranstaltungBeginn, 1, 4);
        add(dpVeranstaltungEnde, 2, 4);
        add(ViewHelpers.createSpaceLabel(), 3, 4);

        add(new TitledSeparator(I18n.PLANUNG.getString("planung.form.vorbereitungstreffen")), 0, 5, 3, 1);
        add(ViewHelpers.createSpaceLabel(), 3, 5);

        add(new Label(I18n.PLANUNG.getString("planung.form.ort")), 0, 6);
        add(tfVorbereitungstreffenOrt, 1, 6, 2, 1);
        add(ViewHelpers.createSpaceLabel(), 3, 6);

        add(new Label(I18n.PLANUNG.getString("planung.form.beginnEnde")), 0, 7);
        add(dpVorbereitungstreffenBeginn, 1, 7);
        add(dpVorbereitungstreffenEnde, 2, 7);
        add(ViewHelpers.createSpaceLabel(), 3, 7);

        add(new TitledSeparator(I18n.PLANUNG.getString("planung.form.nachbereitungstreffen")), 0, 8, 3, 1);
        add(ViewHelpers.createSpaceLabel(), 3, 8);

        add(new Label(I18n.PLANUNG.getString("planung.form.ort")), 0, 9);
        add(tfNachbereitungstreffenOrt, 1, 9, 2, 1);
        add(ViewHelpers.createSpaceLabel(), 3, 9);

        add(new Label(I18n.PLANUNG.getString("planung.form.beginnEnde")), 0, 10);
        add(dpNachbereitungstreffenBeginn, 1, 10);
        add(dpNachbereitungstreffenEnde, 2, 10);

        // 2.block
        add(new TitledSeparator(I18n.PLANUNG.getString("planung.form.bereiche")), 4, 2, 2, 1);

        add(new Label(I18n.PLANUNG.getString("planung.form.fachbereich")), 4, 3);
        add(cbFachbereich, 5, 3);

        add(new Label(I18n.PLANUNG.getString("planung.form.geschaeftsbereich")), 4, 4);
        add(cbGeschaeftsbereich, 5, 4);

        add(new Label(I18n.PLANUNG.getString("planung.form.arbeitsaufwand")), 4, 5);
        add(cbArbeitsaufwand, 5, 5);

        add(new Label(I18n.PLANUNG.getString("planung.form.arbeitsaufwandAufschlag")), 4, 6);
        add(nfArbeitsaufwandAufschlag, 5, 6);

        add(new Label(I18n.PLANUNG.getString("planung.form.tageNachWbg")), 4, 7);
        add(nfTageNachWbg, 5, 7);

        add(new Label(I18n.PLANUNG.getString("planung.form.zgfaktor")), 4, 8);
        add(cbZgfaktor, 5, 8);

        add(new Label(I18n.PLANUNG.getString("planung.form.zgfaktorAufschlag")), 4, 9);
        add(nfZgfaktorAufschlag, 5, 9);
    }

    @Override
    public String getTitle() {
        return I18n.PLANUNG.getString("planung.form.tabRahmendaten");
    }

    @Override
    public String getIconPath() {
        return Images.RAHMENDATEN_26;
    }

    @Override
    public IntegerField getNfPlanungNr() {
        return nfPlanungNr;
    }

    public void setNfPlanungNr(IntegerField nfPlanungNr) {
        this.nfPlanungNr = nfPlanungNr;
    }

    @Override
    public TextField getTfZusatz() {
        return tfZusatz;
    }

    public void setTfZusatz(TextField tfZusatz) {
        this.tfZusatz = tfZusatz;
    }

    public TextField getTfTitel() {
        return tfTitel;
    }

    public void setTfTitel(TextField tfTitel) {
        this.tfTitel = tfTitel;
    }

    @Override
    public TextField getTfVeranstaltungOrt() {
        return tfVeranstaltungOrt;
    }

    public void setTfVeranstaltungOrt(TextField tfVeranstaltungOrt) {
        this.tfVeranstaltungOrt = tfVeranstaltungOrt;
    }

    @Override
    public DatePicker getDpVeranstaltungBeginn() {
        return dpVeranstaltungBeginn;
    }

    public void setDpVeranstaltungBeginn(DatePicker dpVeranstaltungBeginn) {
        this.dpVeranstaltungBeginn = dpVeranstaltungBeginn;
    }

    @Override
    public DatePicker getDpVeranstaltungEnde() {
        return dpVeranstaltungEnde;
    }

    public void setDpVeranstaltungEnde(DatePicker dpVeranstaltungEnde) {
        this.dpVeranstaltungEnde = dpVeranstaltungEnde;
    }

    @Override
    public TextField getTfVorbereitungstreffenOrt() {
        return tfVorbereitungstreffenOrt;
    }

    public void setTfVorbereitungstreffenOrt(TextField tfVorbereitungstreffenOrt) {
        this.tfVorbereitungstreffenOrt = tfVorbereitungstreffenOrt;
    }

    @Override
    public DatePicker getDpVorbereitungstreffenBeginn() {
        return dpVorbereitungstreffenBeginn;
    }

    public void setDpVorbereitungstreffenBeginn(DatePicker dpVorbereitungstreffenBeginn) {
        this.dpVorbereitungstreffenBeginn = dpVorbereitungstreffenBeginn;
    }

    @Override
    public DatePicker getDpVorbereitungstreffenEnde() {
        return dpVorbereitungstreffenEnde;
    }

    public void setDpVorbereitungstreffenEnde(DatePicker dpVorbereitungstreffenEnde) {
        this.dpVorbereitungstreffenEnde = dpVorbereitungstreffenEnde;
    }

    @Override
    public TextField getTfNachbereitungstreffenOrt() {
        return tfNachbereitungstreffenOrt;
    }

    public void setTfNachbereitungstreffenOrt(TextField tfNachbereitungstreffenOrt) {
        this.tfNachbereitungstreffenOrt = tfNachbereitungstreffenOrt;
    }

    @Override
    public DatePicker getDpNachbereitungstreffenBeginn() {
        return dpNachbereitungstreffenBeginn;
    }

    public void setDpNachbereitungstreffenBeginn(DatePicker dpNachbereitungstreffenBeginn) {
        this.dpNachbereitungstreffenBeginn = dpNachbereitungstreffenBeginn;
    }

    @Override
    public DatePicker getDpNachbereitungstreffenEnde() {
        return dpNachbereitungstreffenEnde;
    }

    public void setDpNachbereitungstreffenEnde(DatePicker dpNachbereitungstreffenEnde) {
        this.dpNachbereitungstreffenEnde = dpNachbereitungstreffenEnde;
    }

    @Override
    public ComboBox<Fachbereich> getCbFachbereich() {
        return cbFachbereich;
    }

    @Override
    public ComboBox<Geschaeftsbereich> getCbGeschaeftsbereich() {
        return cbGeschaeftsbereich;
    }

    @Override
    public ComboBox<Arbeitsaufwand> getCbArbeitsaufwand() {
        return cbArbeitsaufwand;
    }

    @Override
    public NumberField getNfArbeitsaufwandAufschlag() {
        return nfArbeitsaufwandAufschlag;
    }

    @Override
    public IntegerField getNfTageNachWbg() {
        return nfTageNachWbg;
    }

    @Override
    public ComboBox<Zgfaktor> getCbZgfaktor() {
        return cbZgfaktor;
    }

    public NumberField getNfZgfaktorAufschlag() {
        return nfZgfaktorAufschlag;
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
