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
package com.lagossoftware.ibbfx.adresse;

import com.lagossoftware.ibbfx.app.I18n;
import com.lagossoftware.ibbfx.app.Images;
import com.lagossoftware.lagosfx.control.NumberField;
import com.lagossoftware.lagosfx.control.TitledSeparator;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.text.DecimalFormat;

/**
 * Adresse Bank view implementation
 *
 * @author Cem Ikta
 */
public class AdresseBankViewImpl extends GridPane implements AdresseBankView {

    private TextField tfKontoInhaber;
    private TextField tfIban;
    private TextField tfBic;
    private TextField tfBank;
    private TextField tfKontoNr;
    private TextField tfBlz;

    private DatePicker dpLetzteSpendeAm;
    private NumberField nfBetrag;
    private NumberField nfSpendensumme;

    public AdresseBankViewImpl() {
        buildView();
    }

    private void buildView() {
        setPadding(new Insets(2, 10, 10, 50));
        setHgap(10);
        setVgap(5);

        tfKontoInhaber = new TextField();
        tfKontoInhaber.setPrefWidth(300);
        tfIban = new TextField();
        tfIban.setPrefWidth(300);
        tfBic = new TextField();
        tfBic.setPrefWidth(300);
        tfBank = new TextField();
        tfBank.setPrefWidth(300);
        tfKontoNr = new TextField();
        tfKontoNr.setPrefWidth(300);
        tfBlz = new TextField();
        tfBlz.setPrefWidth(300);

        dpLetzteSpendeAm = new DatePicker();
        dpLetzteSpendeAm.setPromptText("TT.MM.JJJJ");
        dpLetzteSpendeAm.setPrefWidth(150);
        dpLetzteSpendeAm.setDisable(true);
        dpLetzteSpendeAm.setShowWeekNumbers(true);
        nfBetrag = new NumberField(new DecimalFormat("#,###,###,##0.00"));
        nfBetrag.setMinWidth(150);
        nfBetrag.setPrefWidth(150);
        nfBetrag.setMaxWidth(150);
        nfSpendensumme = new NumberField(new DecimalFormat("#,###,###,##0.00"));
        nfSpendensumme.setMinWidth(150);
        nfSpendensumme.setPrefWidth(150);
        nfSpendensumme.setMaxWidth(150);
        
        add(new Label(I18n.ADRESSE.getString("adresse.form.kontoInhaber")), 0, 0);
        add(tfKontoInhaber, 1, 0);
        add(new Label(I18n.ADRESSE.getString("adresse.form.iban")), 0, 1);
        add(tfIban, 1, 1);
        add(new Label(I18n.ADRESSE.getString("adresse.form.bic")), 0, 2);
        add(tfBic, 1, 2);
        add(new Label(I18n.ADRESSE.getString("adresse.form.bank")), 0, 3);
        add(tfBank, 1, 3);
        add(new Label(I18n.ADRESSE.getString("adresse.form.kontoNr")), 0, 4);
        add(tfKontoNr, 1, 4);
        add(new Label(I18n.ADRESSE.getString("adresse.form.blz")), 0, 5);
        add(tfBlz, 1, 5);
        
        add(new TitledSeparator(I18n.ADRESSE.getString("adresse.form.spenden")), 0, 6, 3, 1);
        add(new Label(I18n.ADRESSE.getString("adresse.form.letzteSpendeAm")), 0, 7);
        add(dpLetzteSpendeAm, 1, 7, 2, 1);
        add(new Label(I18n.ADRESSE.getString("adresse.form.betrag")), 0, 8);
        add(nfBetrag, 1, 8, 2, 1);
        add(new Label(I18n.ADRESSE.getString("adresse.form.spendensumme")), 0, 9);
        add(nfSpendensumme, 1, 9, 2, 1);
    }

    @Override
    public String getTitle() {
        return I18n.ADRESSE.getString("adresse.form.tabBank");
    }

    @Override
    public String getIconPath() {
        return Images.BANK_26;
    }

    @Override
    public TextField getTfKontoInhaber() {
        return tfKontoInhaber;
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
    public DatePicker getDpLetzteSpendeAm() {
        return dpLetzteSpendeAm;
    }

    @Override
    public NumberField getNfBetrag() {
        return nfBetrag;
    }

    @Override
    public NumberField getNfSpendensumme() {
        return nfSpendensumme;
    }

    @Override
    public Node asNode() {
        return this;
    }

}
