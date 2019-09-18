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
 * Planung Personen view implementation
 *
 * @author Cem Ikta
 */
public class PlanungPersonViewImpl extends GridPane implements PlanungPersonView {

    private IntegerField nfZahlendeTnDe;
    private IntegerField nfZahlendeTnAusl;
    private IntegerField nfNichtZahlendeTnDe;
    private IntegerField nfNichtZahlendeTnAusl;
    private IntegerField nfTeilnehmerNachWgbDe;
    private IntegerField nfTeilnehmerNachWgbAusl;

    private NumberField nfZusaetzlicheReiseKosten;
    private NumberField nfZusaetzlicheVerpflegungskosten;
    private NumberField nfWeitereAusgaben;

    private IntegerField nfHauptberuflicheMitarbeiter;
    private IntegerField nfHonorarMitarbeiter;
    private IntegerField nfUnbezahlteMitarbeiter;
    private IntegerField nfFremdeMitarbeiter;

    private IntegerField nfAuslMitarbeiter;
    private IntegerField nfUnterkunfstage;
    private NumberField nfVerpflegungstage;
    private IntegerField nfHonorartage;
    private NumberField nfHonorarsatz;
    private NumberField nfReisekosten;

    public PlanungPersonViewImpl() {
        buildView();
    }

    private void buildView() {
        setPadding(new Insets(2, 10, 10, 50));
        setHgap(10);
        setVgap(10);

        nfZahlendeTnDe = new IntegerField();
        nfZahlendeTnAusl = new IntegerField();
        nfNichtZahlendeTnDe = new IntegerField();
        nfNichtZahlendeTnAusl = new IntegerField();
        nfTeilnehmerNachWgbDe = new IntegerField();
        nfTeilnehmerNachWgbAusl = new IntegerField();

        nfZusaetzlicheReiseKosten = new NumberField(new DecimalFormat("#,###,###,##0.00"));
        nfZusaetzlicheVerpflegungskosten = new NumberField(new DecimalFormat("#,###,###,##0.00"));
        nfWeitereAusgaben = new NumberField(new DecimalFormat("#,###,###,##0.00"));

        nfHauptberuflicheMitarbeiter = new IntegerField();
        nfHonorarMitarbeiter = new IntegerField();
        nfUnbezahlteMitarbeiter = new IntegerField();
        nfFremdeMitarbeiter = new IntegerField();

        nfAuslMitarbeiter = new IntegerField();
        nfUnterkunfstage = new IntegerField();
        nfVerpflegungstage = new NumberField(new DecimalFormat("#,##0.00"));
        nfHonorartage =  new IntegerField();
        nfHonorarsatz = new NumberField(new DecimalFormat("#,###,###,##0.00"));
        nfReisekosten = new NumberField(new DecimalFormat("#,###,###,##0.00"));

        // 1.block
        add(new TitledSeparator(I18n.PLANUNG.getString("planung.form.teilnehmer")), 0, 0, 3, 1);
        add(ViewHelpers.createSpaceLabel(), 3, 0);

        add(new Label(I18n.PLANUNG.getString("planung.form.deutsche")), 1, 1);
        add(new Label(I18n.PLANUNG.getString("planung.form.auslaender")), 2, 1);

        add(new Label(I18n.PLANUNG.getString("planung.form.zahlendeTn")), 0, 2);
        add(nfZahlendeTnDe, 1, 2);
        add(nfZahlendeTnAusl, 2, 2);
        add(ViewHelpers.createSpaceLabel(), 3, 2);

        add(new Label(I18n.PLANUNG.getString("planung.form.nichtZahlendeTn")), 0, 3);
        add(nfNichtZahlendeTnDe, 1, 3);
        add(nfNichtZahlendeTnAusl, 2, 3);
        add(ViewHelpers.createSpaceLabel(), 3, 3);

        add(new Label(I18n.PLANUNG.getString("planung.form.teilnehmerNachWbg")), 0, 4);
        add(nfTeilnehmerNachWgbDe, 1, 4);
        add(nfTeilnehmerNachWgbAusl, 2, 4);
        add(ViewHelpers.createSpaceLabel(), 3, 4);

        // 2.block
        add(new TitledSeparator(I18n.PLANUNG.getString("planung.form.deMitarbeiter")), 0, 5, 3, 1);
        add(ViewHelpers.createSpaceLabel(), 3, 5);

        add(new Label(I18n.PLANUNG.getString("planung.form.hauptberuflicheMitarbeiter")), 0, 6);
        add(nfHauptberuflicheMitarbeiter, 1, 6);
        add(ViewHelpers.createSpaceLabel(), 3, 6);

        add(new Label(I18n.PLANUNG.getString("planung.form.honorarMitarbeiter")), 0, 7);
        add(nfHonorarMitarbeiter, 1, 7);
        add(ViewHelpers.createSpaceLabel(), 3, 7);

        add(new Label(I18n.PLANUNG.getString("planung.form.unbezahlteMitarbeiter")), 0, 8);
        add(nfUnbezahlteMitarbeiter, 1, 8);
        add(ViewHelpers.createSpaceLabel(), 3, 8);

        add(new Label(I18n.PLANUNG.getString("planung.form.fremdeMitarbeiter")), 0, 9);
        add(nfFremdeMitarbeiter, 1, 9);
        add(ViewHelpers.createSpaceLabel(), 3, 9);

        add(ViewHelpers.createSpaceLabel(), 3, 10);

        add(ViewHelpers.createSpaceLabel(), 3, 11);

        // 3.block
        add(new TitledSeparator(I18n.PLANUNG.getString("planung.form.zusaetzlicheKostenFuerDeMitarbeiter")), 4, 0, 2, 1);

        add(new Label(I18n.PLANUNG.getString("planung.form.zusaetzlicheReiseKosten")), 4, 2);
        add(nfZusaetzlicheReiseKosten, 5, 2);

        add(new Label(I18n.PLANUNG.getString("planung.form.zusaetzlicheVerpflegungskosten")), 4, 3);
        add(nfZusaetzlicheVerpflegungskosten, 5, 3);

        add(new Label(I18n.PLANUNG.getString("planung.form.weitereAusgaben")), 4, 4);
        add(nfWeitereAusgaben, 5, 4);

        // 4.block
        add(new TitledSeparator(I18n.PLANUNG.getString("planung.form.auslMitarbeiter")), 4, 5, 2, 1);

        add(new Label(I18n.PLANUNG.getString("planung.form.auslMitarbeiter")), 4, 6);
        add(nfAuslMitarbeiter, 5, 6);

        add(new Label(I18n.PLANUNG.getString("planung.form.unterkunfstage")), 4, 7);
        add(nfUnterkunfstage, 5, 7);

        add(new Label(I18n.PLANUNG.getString("planung.form.verpflegungstage")), 4, 8);
        add(nfVerpflegungstage, 5, 8);

        add(new Label(I18n.PLANUNG.getString("planung.form.honorartage")), 4, 9);
        add(nfHonorartage, 5, 9);

        add(new Label(I18n.PLANUNG.getString("planung.form.honorarsatz")), 4, 10);
        add(nfHonorarsatz, 5, 10);

        add(new Label(I18n.PLANUNG.getString("planung.form.reisekosten")), 4, 11);
        add(nfReisekosten, 5, 11);
    }

    @Override
    public String getTitle() {
        return I18n.PLANUNG.getString("planung.form.tabPersonen");
    }

    @Override
    public String getIconPath() {
        return Images.PERSONEN_26;
    }

    @Override
    public IntegerField getNfZahlendeTnDe() {
        return nfZahlendeTnDe;
    }

    @Override
    public IntegerField getNfZahlendeTnAusl() {
        return nfZahlendeTnAusl;
    }

    @Override
    public IntegerField getNfNichtZahlendeTnDe() {
        return nfNichtZahlendeTnDe;
    }

    @Override
    public IntegerField getNfNichtZahlendeTnAusl() {
        return nfNichtZahlendeTnAusl;
    }

    @Override
    public IntegerField getNfTeilnehmerNachWgbDe() {
        return nfTeilnehmerNachWgbDe;
    }

    @Override
    public IntegerField getNfTeilnehmerNachWgbAusl() {
        return nfTeilnehmerNachWgbAusl;
    }

    @Override
    public NumberField getNfZusaetzlicheReiseKosten() {
        return nfZusaetzlicheReiseKosten;
    }

    @Override
    public NumberField getNfZusaetzlicheVerpflegungskosten() {
        return nfZusaetzlicheVerpflegungskosten;
    }

    @Override
    public NumberField getNfWeitereAusgaben() {
        return nfWeitereAusgaben;
    }

    @Override
    public IntegerField getNfHauptberuflicheMitarbeiter() {
        return nfHauptberuflicheMitarbeiter;
    }

    @Override
    public IntegerField getNfHonorarMitarbeiter() {
        return nfHonorarMitarbeiter;
    }

    @Override
    public IntegerField getNfUnbezahlteMitarbeiter() {
        return nfUnbezahlteMitarbeiter;
    }

    @Override
    public IntegerField getNfFremdeMitarbeiter() {
        return nfFremdeMitarbeiter;
    }

    @Override
    public IntegerField getNfAuslMitarbeiter() {
        return nfAuslMitarbeiter;
    }

    @Override
    public IntegerField getNfUnterkunfstage() {
        return nfUnterkunfstage;
    }

    @Override
    public NumberField getNfVerpflegungstage() {
        return nfVerpflegungstage;
    }

    @Override
    public IntegerField getNfHonorartage() {
        return nfHonorartage;
    }

    @Override
    public NumberField getNfHonorarsatz() {
        return nfHonorarsatz;
    }

    @Override
    public NumberField getNfReisekosten() {
        return nfReisekosten;
    }

    @Override
    public Node asNode() {
        return this;
    }

}
