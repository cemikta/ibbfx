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
import com.lagossoftware.ibbfx.entity.Adresse;
import com.lagossoftware.lagosfx.common.ViewHelpers;
import com.lagossoftware.lagosfx.control.NumberField;
import com.lagossoftware.lagosfx.control.TitledSeparator;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.text.DecimalFormat;

/**
 * Planung Namen und Preise view implementation
 *
 * @author Cem Ikta
 */
public class PlanungNamenViewImpl extends GridPane implements PlanungNamenView {

    // leitung
    private ComboBox<Adresse> cbMitarbeiter1;
    private ComboBox<Adresse> cbMitarbeiter2;
    // partner
    private ComboBox<Adresse> cbPartner;
    private TextField tfPlz;
    private TextField tfOrt;
    private TextField tfMobiltelefon;
    private TextField tfTelefonPrivat;
    private TextField tfTelefonDienst;
    private TextField tfEmail;
    // anmeldung
    private CheckBox chbAnmeldungIbb;
    private CheckBox chbAnmeldungPartner;
    //preise
    private NumberField nfFestpreis;
    private TextField tfBedingung1;
    private TextField tfBedingung2;
    private TextField tfBedingung3;
    private TextField tfBedingung4;
    private TextField tfBedingung5;
    private TextField tfBedingung6;
    private NumberField nfPreis1;
    private NumberField nfPreis2;
    private NumberField nfPreis3;
    private NumberField nfPreis4;
    private NumberField nfPreis5;
    private NumberField nfPreis6;
    private CheckBox chbWirdDurchgefuehrt;
    private TitledSeparator kurzbeschreibungEbPlanTitle;
    private TextArea tfKurzbeschreibungEbPlan;

    public PlanungNamenViewImpl() {
        buildView();
    }

    private void buildView() {
        setPadding(new Insets(2, 10, 10, 50));
        setHgap(10);
        setVgap(10);

        cbMitarbeiter1 = new ComboBox<>();
        cbMitarbeiter1.setPrefWidth(300);
        cbMitarbeiter2 = new ComboBox<>();
        cbMitarbeiter2.setPrefWidth(300);

        cbPartner = new ComboBox<>();
        cbPartner.setPrefWidth(300);
        tfPlz = new TextField();
        tfPlz.setPrefWidth(100);
        tfPlz.setEditable(false);
        tfOrt = new TextField();
        tfOrt.setPrefWidth(200);
        tfOrt.setEditable(false);
        tfMobiltelefon = new TextField();
        tfMobiltelefon.setPrefWidth(300);
        tfMobiltelefon.setEditable(false);
        tfTelefonPrivat = new TextField();
        tfTelefonPrivat.setPrefWidth(300);
        tfTelefonPrivat.setEditable(false);
        tfTelefonDienst = new TextField();
        tfTelefonDienst.setPrefWidth(300);
        tfTelefonDienst.setEditable(false);
        tfEmail = new TextField();
        tfEmail.setPrefWidth(300);
        tfEmail.setEditable(false);

        chbAnmeldungIbb = new CheckBox(I18n.PLANUNG.getString("planung.form.anmeldungIBB"));
        chbAnmeldungPartner = new CheckBox(I18n.PLANUNG.getString("planung.form.anmeldungKoopPartner"));
        setValignment(chbAnmeldungIbb, VPos.TOP);
        setValignment(chbAnmeldungPartner, VPos.TOP);

        nfFestpreis = new NumberField(new DecimalFormat("#,###,###,##0.00"));
        tfBedingung1 = new TextField();
        tfBedingung1.setPrefWidth(200);
        tfBedingung1.setEditable(false);
        tfBedingung2 = new TextField();
        tfBedingung2.setPrefWidth(200);
        tfBedingung2.setEditable(false);
        tfBedingung3 = new TextField();
        tfBedingung3.setPrefWidth(200);
        tfBedingung4 = new TextField();
        tfBedingung4.setPrefWidth(200);
        tfBedingung5 = new TextField();
        tfBedingung5.setPrefWidth(200);
        tfBedingung6 = new TextField();
        tfBedingung6.setPrefWidth(200);
        nfPreis1 = new NumberField(new DecimalFormat("#,###,###,##0.00"));
        nfPreis1.setEditable(false);
        nfPreis2 = new NumberField(new DecimalFormat("#,###,###,##0.00"));
        nfPreis3 = new NumberField(new DecimalFormat("#,###,###,##0.00"));
        nfPreis4 = new NumberField(new DecimalFormat("#,###,###,##0.00"));
        nfPreis5 = new NumberField(new DecimalFormat("#,###,###,##0.00"));
        nfPreis6 = new NumberField(new DecimalFormat("#,###,###,##0.00"));
        chbWirdDurchgefuehrt = new CheckBox(I18n.PLANUNG.getString("planung.form.wirdDurchgefuehrt"));

        kurzbeschreibungEbPlanTitle = new TitledSeparator(
                I18n.PLANUNG.getString("planung.form.kurzbeschreibungEbPlan"));
        tfKurzbeschreibungEbPlan = new TextArea();
        tfKurzbeschreibungEbPlan.setPrefWidth(400);
        tfKurzbeschreibungEbPlan.setPrefRowCount(5);

        // 1.block
        add(new TitledSeparator(I18n.PLANUNG.getString("planung.form.leitung")), 0, 0, 3, 1);
        add(ViewHelpers.createSpaceLabel(), 3, 0);

        add(new Label(I18n.PLANUNG.getString("planung.form.mitarbeiter1")), 0, 1);
        add(cbMitarbeiter1, 1, 1, 2, 1);
        add(ViewHelpers.createSpaceLabel(), 3, 1);

        add(new Label(I18n.PLANUNG.getString("planung.form.mitarbeiter2")), 0, 2);
        add(cbMitarbeiter2, 1, 2, 2, 1);
        add(ViewHelpers.createSpaceLabel(), 3, 2);

        add(new TitledSeparator(I18n.PLANUNG.getString("planung.form.koopPartner")), 0, 3, 3, 1);
        add(ViewHelpers.createSpaceLabel(), 3, 3);

        add(new Label(I18n.PLANUNG.getString("planung.form.koopPartner")), 0, 4);
        add(cbPartner, 1, 4, 2, 1);
        add(ViewHelpers.createSpaceLabel(), 3, 4);

        add(new Label(I18n.PLANUNG.getString("planung.form.plzOrt")), 0, 5);
        add(tfPlz, 1, 5);
        add(tfOrt, 2, 5);
        add(ViewHelpers.createSpaceLabel(), 3, 5);

        add(new Label(I18n.PLANUNG.getString("planung.form.mobil")), 0, 6);
        add(tfMobiltelefon, 1, 6, 2, 1);
        add(ViewHelpers.createSpaceLabel(), 3, 6);

        add(new Label(I18n.PLANUNG.getString("planung.form.telefonPrivat")), 0, 7);
        add(tfTelefonPrivat, 1, 7, 2, 1);
        add(ViewHelpers.createSpaceLabel(), 3, 7);

        add(new Label(I18n.PLANUNG.getString("planung.form.telefonDienst")), 0, 8);
        add(tfTelefonDienst, 1, 8, 2, 1);
        add(ViewHelpers.createSpaceLabel(), 3, 8);

        add(new Label(I18n.PLANUNG.getString("planung.form.email")), 0, 9);
        add(tfEmail, 1, 9, 2, 1);
        add(ViewHelpers.createSpaceLabel(), 3, 9);

        add(new TitledSeparator(I18n.PLANUNG.getString("planung.form.anmeldung")), 0, 10, 3, 1);
        add(ViewHelpers.createSpaceLabel(), 3, 10);

        add(chbAnmeldungIbb, 1, 11);
        add(chbAnmeldungPartner, 2, 11);
        add(ViewHelpers.createSpaceLabel(), 3, 11);

        // 2.block
        add(new TitledSeparator(I18n.PLANUNG.getString("planung.form.preise")), 4, 0, 3, 1);

        add(new Label(""), 4, 1);
        add(new Label(I18n.PLANUNG.getString("planung.form.festPreis")), 5, 1);
        add(nfFestpreis, 6, 1);

        add(new Label(""), 4, 2);
        add(new Label(I18n.PLANUNG.getString("planung.form.sonderpreiseBedingung")), 5, 2);
        add(new Label(I18n.PLANUNG.getString("planung.form.sonderpreisePreis")), 6, 2);

        add(new Label("1."), 4, 3);
        add(tfBedingung1, 5, 3);
        add(nfPreis1, 6, 3);

        add(new Label("2."), 4, 4);
        add(tfBedingung2, 5, 4);
        add(nfPreis2, 6, 4);

        add(new Label("3."), 4, 5);
        add(tfBedingung3, 5, 5);
        add(nfPreis3, 6, 5);

        add(new Label("4."), 4, 6);
        add(tfBedingung4, 5, 6);
        add(nfPreis4, 6, 6);

        add(new Label("5."), 4, 7);
        add(tfBedingung5, 5, 7);
        add(nfPreis5, 6, 7);

        add(new Label("6."), 4, 8);
        add(tfBedingung6, 5, 8);
        add(nfPreis6, 6, 8);

        add(chbWirdDurchgefuehrt, 5, 9, 2, 1);

        add(kurzbeschreibungEbPlanTitle, 4, 10, 3, 1);

        add(tfKurzbeschreibungEbPlan, 4, 11, 3, 1);
    }

    @Override
    public String getTitle() {
        return I18n.PLANUNG.getString("planung.form.tabNamen");
    }

    @Override
    public String getIconPath() {
        return Images.NAMEN_UND_PREISE_26;
    }

    public ComboBox<Adresse> getCbMitarbeiter1() {
        return cbMitarbeiter1;
    }

    public ComboBox<Adresse> getCbMitarbeiter2() {
        return cbMitarbeiter2;
    }

    public ComboBox<Adresse> getCbPartner() {
        return cbPartner;
    }

    public TextField getTfPlz() {
        return tfPlz;
    }

    public TextField getTfOrt() {
        return tfOrt;
    }

    public TextField getTfMobiltelefon() {
        return tfMobiltelefon;
    }

    public TextField getTfTelefonPrivat() {
        return tfTelefonPrivat;
    }

    public TextField getTfTelefonDienst() {
        return tfTelefonDienst;
    }

    public TextField getTfEmail() {
        return tfEmail;
    }

    public CheckBox getChbAnmeldungIbb() {
        return chbAnmeldungIbb;
    }

    public CheckBox getChbAnmeldungPartner() {
        return chbAnmeldungPartner;
    }

    public NumberField getNfFestpreis() {
        return nfFestpreis;
    }

    public TextField getTfBedingung1() {
        return tfBedingung1;
    }

    public TextField getTfBedingung2() {
        return tfBedingung2;
    }

    public TextField getTfBedingung3() {
        return tfBedingung3;
    }

    public TextField getTfBedingung4() {
        return tfBedingung4;
    }

    public TextField getTfBedingung5() {
        return tfBedingung5;
    }

    public TextField getTfBedingung6() {
        return tfBedingung6;
    }

    public NumberField getNfPreis1() {
        return nfPreis1;
    }

    public NumberField getNfPreis2() {
        return nfPreis2;
    }

    public NumberField getNfPreis3() {
        return nfPreis3;
    }

    public NumberField getNfPreis4() {
        return nfPreis4;
    }

    public NumberField getNfPreis5() {
        return nfPreis5;
    }

    public NumberField getNfPreis6() {
        return nfPreis6;
    }

    public CheckBox getChbWirdDurchgefuehrt() {
        return chbWirdDurchgefuehrt;
    }

    public TitledSeparator getKurzbeschreibungEbPlanTitle() {
        return kurzbeschreibungEbPlanTitle;
    }

    public TextArea getTfKurzbeschreibungEbPlan() {
        return tfKurzbeschreibungEbPlan;
    }

    @Override
    public Node asNode() {
        return this;
    }

}
