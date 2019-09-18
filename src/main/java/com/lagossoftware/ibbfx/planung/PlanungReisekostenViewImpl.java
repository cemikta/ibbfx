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
import com.lagossoftware.lagosfx.common.ViewHelpers;
import com.lagossoftware.lagosfx.control.IntegerField;
import com.lagossoftware.lagosfx.control.NumberField;
import com.lagossoftware.lagosfx.control.TitledSeparator;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.text.DecimalFormat;

/**
 * Planung Reisekosten view implementation
 *
 * @author Cem Ikta
 */
public class PlanungReisekostenViewImpl extends GridPane implements PlanungReisekostenView {

    // reisekosten
    private NumberField nfBahnfahrtProPerson;
    private NumberField nfFlugkostenProPerson;
    private NumberField nfBustransfersGesamt;
    private NumberField nfWeitereFahrtenImLandProPerson;
    private NumberField nfFahrtkostenFuerAuslTnProPerson;
    // unterkunft
    private NumberField nfUebernachtungskostenProNacht;
    private NumberField nfAufpreisEinzelzimmerProNacht;
    private IntegerField nfAnzahlNaechteProDeTn;
    private IntegerField nfAnzahlNaechteProAuslTn;
    private NumberField nfVerpflegungskostenProTag;
    private NumberField nfVerpflegungstageProDeTn;
    private NumberField nfVerpflegungstageProAuslTn;
    private NumberField nfPauschalpreis;

    public PlanungReisekostenViewImpl() {
        buildView();
    }

    private void buildView() {
        setPadding(new Insets(2, 10, 10, 50));
        setHgap(10);
        setVgap(10);

        nfBahnfahrtProPerson = new NumberField(new DecimalFormat("#,###,###,##0.00"));
        nfFlugkostenProPerson = new NumberField(new DecimalFormat("#,###,###,##0.00"));
        nfBustransfersGesamt = new NumberField(new DecimalFormat("#,###,###,##0.00"));
        nfWeitereFahrtenImLandProPerson = new NumberField(new DecimalFormat("#,###,###,##0.00"));
        nfFahrtkostenFuerAuslTnProPerson = new NumberField(new DecimalFormat("#,###,###,##0.00"));

        nfUebernachtungskostenProNacht  = new NumberField(new DecimalFormat("#,###,###,##0.00"));
        nfAufpreisEinzelzimmerProNacht = new NumberField(new DecimalFormat("#,###,###,##0.00"));
        nfAnzahlNaechteProDeTn = new IntegerField();
        nfAnzahlNaechteProAuslTn = new IntegerField();
        nfVerpflegungskostenProTag = new NumberField(new DecimalFormat("#,###,###,##0.00"));
        nfVerpflegungstageProDeTn = new NumberField(new DecimalFormat("#,##0.00"));
        nfVerpflegungstageProAuslTn = new NumberField(new DecimalFormat("#,##0.00"));
        nfPauschalpreis = new NumberField(new DecimalFormat("#,###,###,##0.00"));

        // 1.block
        add(new TitledSeparator(I18n.PLANUNG.getString("planung.form.reisekostenLine")), 0, 0, 2, 1);
        add(ViewHelpers.createSpaceLabel(), 2, 0);

        add(new Label(I18n.PLANUNG.getString("planung.form.bahnfahrtProPerson")), 0, 1);
        add(nfBahnfahrtProPerson, 1, 1);
        add(ViewHelpers.createSpaceLabel(), 2, 1);

        add(new Label(I18n.PLANUNG.getString("planung.form.flugkostenProPerson")), 0, 2);
        add(nfFlugkostenProPerson, 1, 2);
        add(ViewHelpers.createSpaceLabel(), 2, 2);

        add(new Label(I18n.PLANUNG.getString("planung.form.bustransfersGesamt")), 0, 3);
        add(nfBustransfersGesamt, 1, 3);
        add(ViewHelpers.createSpaceLabel(), 2, 3);

        add(new Label(I18n.PLANUNG.getString("planung.form.weitereFahrtenImLandProPerson")), 0, 4);
        add(nfWeitereFahrtenImLandProPerson, 1, 4);
        add(ViewHelpers.createSpaceLabel(), 2, 4);

        add(new Label(I18n.PLANUNG.getString("planung.form.fahrtkostenFuerAuslTNProPerson")), 0, 5);
        add(nfFahrtkostenFuerAuslTnProPerson, 1, 5);
        add(ViewHelpers.createSpaceLabel(), 2, 5);

        add(ViewHelpers.createSpaceLabel(), 2, 6);

        add(ViewHelpers.createSpaceLabel(), 2, 7);

        add(ViewHelpers.createSpaceLabel(), 2, 8);

        // 2.block
        add(new TitledSeparator(I18n.PLANUNG.getString("planung.form.unterkunft")), 3, 0, 2, 1);

        add(new Label(I18n.PLANUNG.getString("planung.form.uebernachtungskostenProNacht")), 3, 1);
        add(nfUebernachtungskostenProNacht, 4, 1);

        add(new Label(I18n.PLANUNG.getString("planung.form.aufpreisEinzelzimmerProNacht")), 3, 2);
        add(nfAufpreisEinzelzimmerProNacht, 4, 2);

        add(new Label(I18n.PLANUNG.getString("planung.form.anzahlNaechteProDeTN")), 3, 3);
        add(nfAnzahlNaechteProDeTn, 4, 3);

        add(new Label(I18n.PLANUNG.getString("planung.form.anzahlNaechteProAuslTN")), 3, 4);
        add(nfAnzahlNaechteProAuslTn, 4, 4);

        add(new Label(I18n.PLANUNG.getString("planung.form.verpflegungskostenProTag")), 3, 5);
        add(nfVerpflegungskostenProTag, 4, 5);

        add(new Label(I18n.PLANUNG.getString("planung.form.verpflegungsTageProDeTN")), 3, 6);
        add(nfVerpflegungstageProDeTn, 4, 6);

        add(new Label(I18n.PLANUNG.getString("planung.form.verpflegungsTageProAuslTN")), 3, 7);
        add(nfVerpflegungstageProAuslTn, 4, 7);

        add(new Label(I18n.PLANUNG.getString("planung.form.pauschalpreis")), 3, 8);
        add(nfPauschalpreis, 4, 8);
    }

    @Override
    public String getTitle() {
        return I18n.PLANUNG.getString("planung.form.tabReisekosten");
    }

    @Override
    public String getIconPath() {
        return Images.REISEKOSTEN_26;
    }

    public NumberField getNfBahnfahrtProPerson() {
        return nfBahnfahrtProPerson;
    }

    public NumberField getNfFlugkostenProPerson() {
        return nfFlugkostenProPerson;
    }

    public NumberField getNfBustransfersGesamt() {
        return nfBustransfersGesamt;
    }

    public NumberField getNfWeitereFahrtenImLandProPerson() {
        return nfWeitereFahrtenImLandProPerson;
    }

    public NumberField getNfFahrtkostenFuerAuslTnProPerson() {
        return nfFahrtkostenFuerAuslTnProPerson;
    }

    public NumberField getNfUebernachtungskostenProNacht() {
        return nfUebernachtungskostenProNacht;
    }

    public NumberField getNfAufpreisEinzelzimmerProNacht() {
        return nfAufpreisEinzelzimmerProNacht;
    }

    public IntegerField getNfAnzahlNaechteProDeTn() {
        return nfAnzahlNaechteProDeTn;
    }

    public IntegerField getNfAnzahlNaechteProAuslTn() {
        return nfAnzahlNaechteProAuslTn;
    }

    public NumberField getNfVerpflegungskostenProTag() {
        return nfVerpflegungskostenProTag;
    }

    public NumberField getNfVerpflegungstageProDeTn() {
        return nfVerpflegungstageProDeTn;
    }

    public NumberField getNfVerpflegungstageProAuslTn() {
        return nfVerpflegungstageProAuslTn;
    }

    public NumberField getNfPauschalpreis() {
        return nfPauschalpreis;
    }

    @Override
    public Node asNode() {
        return this;
    }

}
