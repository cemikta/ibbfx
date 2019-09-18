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
import com.lagossoftware.lagosfx.control.NumberField;
import com.lagossoftware.lagosfx.control.TitledSeparator;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;

import java.text.DecimalFormat;

/**
 * Planung Kosten view implementation
 *
 * @author Cem Ikta
 */
public class PlanungKostenViewImpl extends GridPane implements PlanungKostenView {

    // versicherung
    private RadioButton rbVeranstaltungInDe;
    private RadioButton rbVeranstaltungImAusland;
    private CheckBox chbRegressVersicherung;
    private CheckBox chbReiserecht;
    // programm
    private NumberField nfFuehrungenGesamt;
    private NumberField nfExterneReferentGesamt;
    private NumberField nfExterneDolmetscherGesamt;
    private NumberField nfMaterialkostenGesamt;
    private NumberField nfEintrittsgelderProDeTn;
    private NumberField nfEintrittsgelderProAuslTn;
    // visa
    private NumberField nfVisagebuehrProPerson;
    private NumberField nfVisaNebenkosten;
    // sonstiges
    private NumberField nfSonstigekostenGesamt;
    private NumberField nfSonstigekostenProTn;

    public PlanungKostenViewImpl() {
        buildView();
    }

    private void buildView() {
        setPadding(new Insets(2, 10, 10, 50));
        setHgap(10);
        setVgap(10);

        final ToggleGroup groupVeranstaltungsLand = new ToggleGroup();
        rbVeranstaltungInDe = new RadioButton(I18n.PLANUNG.getString("planung.form.veranstaltungInDe"));
        rbVeranstaltungInDe.setToggleGroup(groupVeranstaltungsLand);
        rbVeranstaltungInDe.setSelected(true);
        rbVeranstaltungImAusland = new RadioButton(
                I18n.PLANUNG.getString("planung.form.veranstaltungImAusland"));
        rbVeranstaltungImAusland.setToggleGroup(groupVeranstaltungsLand);

        chbRegressVersicherung = new CheckBox();
        chbReiserecht = new CheckBox();

        nfFuehrungenGesamt = new NumberField(new DecimalFormat("#,###,###,##0.00"));
        nfExterneReferentGesamt = new NumberField(new DecimalFormat("#,###,###,##0.00"));
        nfExterneDolmetscherGesamt = new NumberField(new DecimalFormat("#,###,###,##0.00"));
        nfMaterialkostenGesamt = new NumberField(new DecimalFormat("#,###,###,##0.00"));
        nfEintrittsgelderProDeTn = new NumberField(new DecimalFormat("#,###,###,##0.00"));
        nfEintrittsgelderProAuslTn = new NumberField(new DecimalFormat("#,###,###,##0.00"));

        nfVisagebuehrProPerson = new NumberField(new DecimalFormat("#,###,###,##0.00"));
        nfVisaNebenkosten = new NumberField(new DecimalFormat("#,###,###,##0.00"));

        nfSonstigekostenGesamt = new NumberField(new DecimalFormat("#,###,###,##0.00"));
        nfSonstigekostenProTn = new NumberField(new DecimalFormat("#,###,###,##0.00"));

        add(new TitledSeparator(I18n.PLANUNG.getString("planung.form.versicherung")), 0, 0, 2, 1);
        add(ViewHelpers.createSpaceLabel(), 2, 0);

        add(new Label(I18n.PLANUNG.getString("planung.form.veranstaltungsLand")), 0, 1);
        add(new Label(), 1, 1);
        add(ViewHelpers.createSpaceLabel(), 2, 1);

        add(rbVeranstaltungInDe, 0, 2);
        add(rbVeranstaltungImAusland, 1, 2);
        add(ViewHelpers.createSpaceLabel(), 2, 2);

        add(new Label(I18n.PLANUNG.getString("planung.form.regress")), 0, 3);
        add(chbRegressVersicherung, 1, 3);
        add(ViewHelpers.createSpaceLabel(), 2, 3);

        add(new Label(I18n.PLANUNG.getString("planung.form.reiserecht")), 0, 4);
        add(chbReiserecht, 1, 4);
        add(ViewHelpers.createSpaceLabel(), 2, 4);

        add(new Label(), 0, 5);
        add(new Label(), 1, 5);
        add(ViewHelpers.createSpaceLabel(), 2, 5);

        add(new Label(), 0, 6);
        add(new Label(), 1, 6);
        add(ViewHelpers.createSpaceLabel(), 2, 6);

        add(new TitledSeparator(I18n.PLANUNG.getString("planung.form.visa")), 0, 7, 2, 1);
        add(ViewHelpers.createSpaceLabel(), 2, 7);

        add(new Label(I18n.PLANUNG.getString("planung.form.visagebuehrProPerson")), 0, 8);
        add(nfVisagebuehrProPerson, 1, 8);
        add(ViewHelpers.createSpaceLabel(), 2, 8);

        add(new Label(I18n.PLANUNG.getString("planung.form.nebenkosten")), 0, 9);
        add(nfVisaNebenkosten, 1, 9);
        add(ViewHelpers.createSpaceLabel(), 2, 9);

        // 2.block
        add(new TitledSeparator(I18n.PLANUNG.getString("planung.form.programm")), 3, 0, 2, 1);

        add(new Label(I18n.PLANUNG.getString("planung.form.fuehrungenGesamt")), 3, 1);
        add(nfFuehrungenGesamt, 4, 1);

        add(new Label(I18n.PLANUNG.getString("planung.form.externeReferentGesamt")), 3, 2);
        add(nfExterneReferentGesamt, 4, 2);

        add(new Label(I18n.PLANUNG.getString("planung.form.externeDolmetscherGesamt")), 3, 3);
        add(nfExterneDolmetscherGesamt, 4, 3);

        add(new Label(I18n.PLANUNG.getString("planung.form.materialkostenGesamt")), 3, 4);
        add(nfMaterialkostenGesamt, 4, 4);

        add(new Label(I18n.PLANUNG.getString("planung.form.eintrittsgelderProDeTN")), 3, 5);
        add(nfEintrittsgelderProDeTn, 4, 5);

        add(new Label(I18n.PLANUNG.getString("planung.form.eintrittsgelderProAuslTN")), 3, 6);
        add(nfEintrittsgelderProAuslTn, 4, 6);

        add(new TitledSeparator(I18n.PLANUNG.getString("planung.form.sonstiges")), 3, 7, 2, 1);

        add(new Label(I18n.PLANUNG.getString("planung.form.sonstigekostenGesamt")), 3, 8);
        add(nfSonstigekostenGesamt, 4, 8);

        add(new Label(I18n.PLANUNG.getString("planung.form.sonstigekostenProTN")), 3, 9);
        add(nfSonstigekostenProTn, 4, 9);
    }

    @Override
    public String getTitle() {
        return I18n.PLANUNG.getString("planung.form.tabKosten");
    }

    @Override
    public String getIconPath() {
        return Images.KOSTEN_26;
    }

    public RadioButton getRbVeranstaltungInDe() {
        return rbVeranstaltungInDe;
    }

    public RadioButton getRbVeranstaltungImAusland() {
        return rbVeranstaltungImAusland;
    }

    public CheckBox getChbRegressVersicherung() {
        return chbRegressVersicherung;
    }

    public CheckBox getChbReiserecht() {
        return chbReiserecht;
    }

    public NumberField getNfFuehrungenGesamt() {
        return nfFuehrungenGesamt;
    }

    public NumberField getNfExterneReferentGesamt() {
        return nfExterneReferentGesamt;
    }

    public NumberField getNfExterneDolmetscherGesamt() {
        return nfExterneDolmetscherGesamt;
    }

    public NumberField getNfMaterialkostenGesamt() {
        return nfMaterialkostenGesamt;
    }

    public NumberField getNfEintrittsgelderProDeTn() {
        return nfEintrittsgelderProDeTn;
    }

    public NumberField getNfEintrittsgelderProAuslTn() {
        return nfEintrittsgelderProAuslTn;
    }

    public NumberField getNfVisagebuehrProPerson() {
        return nfVisagebuehrProPerson;
    }

    public NumberField getNfVisaNebenkosten() {
        return nfVisaNebenkosten;
    }

    public NumberField getNfSonstigekostenGesamt() {
        return nfSonstigekostenGesamt;
    }

    public NumberField getNfSonstigekostenProTn() {
        return nfSonstigekostenProTn;
    }

    @Override
    public Node asNode() {
        return this;
    }

}
