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
import com.lagossoftware.lagosfx.control.IntegerField;
import com.lagossoftware.lagosfx.control.TitledSeparator;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 * Adresse Pass view implementation
 *
 * @author Cem Ikta
 */
public class AdressePassViewImpl extends GridPane implements AdressePassView {

    private TextField tfBeruf;
    private DatePicker dpGeburtsdatum;
    private TextField tfGeburtsort;
    private TextField tfStaatsangehoerigkeit;
    private TextField tfPassNr;
    private DatePicker dpAusstellungsdatum;
    private TextField tfAusstellungsort;
    
    private IntegerField nfKostenstelle;

    public AdressePassViewImpl() {
        buildView();
    }

    private void buildView() {
        setPadding(new Insets(2, 10, 10, 50));
        setHgap(10);
        setVgap(5);

        tfBeruf = new TextField();
        tfBeruf.setPrefWidth(300);
        dpGeburtsdatum = new DatePicker();
        dpGeburtsdatum.setPromptText("TT.MM.JJJJ");
        dpGeburtsdatum.setPrefWidth(150);
        dpGeburtsdatum.setShowWeekNumbers(true);
        tfGeburtsort = new TextField();
        tfGeburtsort.setPrefWidth(300);
        tfStaatsangehoerigkeit = new TextField();
        tfStaatsangehoerigkeit.setPrefWidth(300);
        tfPassNr = new TextField();
        tfPassNr.setPrefWidth(300);
        dpAusstellungsdatum = new DatePicker();
        dpAusstellungsdatum.setPromptText("TT.MM.JJJJ");
        dpAusstellungsdatum.setPrefWidth(150);
        dpAusstellungsdatum.setShowWeekNumbers(true);
        tfAusstellungsort = new TextField();
        tfAusstellungsort.setPrefWidth(300);
        nfKostenstelle = new IntegerField();
        nfKostenstelle.setMinWidth(150);
        nfKostenstelle.setPrefWidth(150);
        nfKostenstelle.setMaxWidth(150);
        
        
        add(new Label(I18n.ADRESSE.getString("adresse.form.beruf")), 0, 0);
        add(tfBeruf, 1, 0);
        add(new Label(I18n.ADRESSE.getString("adresse.form.geburtsdatum")), 0, 1);
        add(dpGeburtsdatum, 1, 1);
        add(new Label(I18n.ADRESSE.getString("adresse.form.geburtsort")), 0, 2);
        add(tfGeburtsort, 1, 2);
        add(new Label(I18n.ADRESSE.getString("adresse.form.staatsangehoerigkeit")), 0, 3);
        add(tfStaatsangehoerigkeit, 1, 3);
        add(new Label(I18n.ADRESSE.getString("adresse.form.passNr")), 0, 4);
        add(tfPassNr, 1, 4);
        add(new Label(I18n.ADRESSE.getString("adresse.form.ausstellungsdatum")), 0, 5);
        add(dpAusstellungsdatum, 1, 5);
        add(new Label(I18n.ADRESSE.getString("adresse.form.ausstellungsort")), 0, 6);
        add(tfAusstellungsort, 1, 6);
        
        add(new TitledSeparator(I18n.ADRESSE.getString("adresse.form.mitarbeiter")), 0, 7, 3, 1);
        add(new Label(I18n.ADRESSE.getString("adresse.form.kostenstelle")), 0, 8);
        add(nfKostenstelle, 1, 8);
    }

    @Override
    public String getTitle() {
        return I18n.ADRESSE.getString("adresse.form.tabPass");
    }

    @Override
    public String getIconPath() {
        return Images.PASS_26;
    }

    @Override
    public TextField getTfBeruf() {
        return tfBeruf;
    }

    @Override
    public DatePicker getDpGeburtsdatum() {
        return dpGeburtsdatum;
    }

    @Override
    public TextField getTfGeburtsort() {
        return tfGeburtsort;
    }

    @Override
    public TextField getTfStaatsangehoerigkeit() {
        return tfStaatsangehoerigkeit;
    }

    @Override
    public TextField getTfPassNr() {
        return tfPassNr;
    }

    @Override
    public DatePicker getDpAusstellungsdatum() {
        return dpAusstellungsdatum;
    }

    @Override
    public TextField getTfAusstellungsort() {
        return tfAusstellungsort;
    }
    
    @Override
    public IntegerField getNfKostenstelle() {
        return nfKostenstelle;
    }
    
    @Override
    public Node asNode() {
        return this;
    }

}
