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
import com.lagossoftware.ibbfx.entity.AdresseStichwort;
import com.lagossoftware.ibbfx.entity.FahrtkasseDetail;
import com.lagossoftware.ibbfx.entity.FahrtkasseWaehrung;
import com.lagossoftware.ibbfx.entity.Waehrung;
import com.lagossoftware.lagosfx.common.TableColumnBuilder;
import com.lagossoftware.lagosfx.common.ViewHelpers;
import com.lagossoftware.lagosfx.control.NumberField;
import com.lagossoftware.lagosfx.control.TitledSeparator;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.decoration.GraphicValidationDecoration;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Fahrtkasse Abrechnen view implementation
 *
 * @author Cem Ikta
 */
public class FahrtkasseAbrechnenViewImpl extends GridPane implements FahrtkasseAbrechnenView {

    // waehrungen
    private Button btnAddNewKurs;
    private Button btnEditKurs;
    private ListView<FahrtkasseWaehrung> waehrungListView;

    // fahrtkassedetails
    private Button btnAddNewDetail;
    private Button btnEditDetail;
    private ListView<FahrtkasseDetail> detailListView;

    // abrechnung
    private NumberField nfAnfangsBetrag;
    private NumberField nfAusgabenGesamt;
    private Label lblAusgabenBelege;
    private NumberField nfEinnahmenGesamt;
    private Label lblEinnahmenBelege;
    private NumberField nfSaldo;
    private DatePicker dpAbgerechnetAm;

    private ValidationSupport validationSupport;

    public FahrtkasseAbrechnenViewImpl() {
        buildView();
    }

    private void buildView() {
        setPadding(new Insets(2, 10, 10, 50));
        setHgap(10);
        setVgap(10);

        // waehrungen
        btnAddNewKurs = ViewHelpers.createIconButton(Images.PLUS_16);
        btnAddNewKurs.setTooltip(new Tooltip(
                I18n.FAHRTKASSE.getString("fahrtkasse.form.addNewKurs")));
        btnAddNewKurs.getStyleClass().add("left-pill");

        btnEditKurs = ViewHelpers.createIconButton(Images.EDIT_16);
        btnEditKurs.setTooltip(new Tooltip(
                I18n.FAHRTKASSE.getString("fahrtkasse.form.editKurs")));
        btnEditKurs.getStyleClass().add("right-pill");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        HBox waehrungBar = new HBox(0, spacer, btnAddNewKurs, btnEditKurs);

        waehrungListView = new ListView<>();
        waehrungListView.setMinSize(250, 250);
        waehrungListView.setMaxSize(250, 250);
        waehrungListView.setPrefSize(250, 250);
        waehrungListView.setPlaceholder(new Label(I18n.COMMON.getString("listView.dataNotFound")));

        // fahrtkassedetails
        btnAddNewDetail = ViewHelpers.createIconButton(Images.PLUS_16);
        btnAddNewDetail.setTooltip(new Tooltip(
                I18n.FAHRTKASSE.getString("fahrtkasse.form.addNewDetail")));
        btnAddNewDetail.getStyleClass().add("left-pill");

        btnEditDetail = ViewHelpers.createIconButton(Images.EDIT_16);
        btnEditDetail.setTooltip(new Tooltip(
                I18n.FAHRTKASSE.getString("fahrtkasse.form.editDetail")));
        btnEditDetail.getStyleClass().add("right-pill");

        Region spacer2 = new Region();
        HBox.setHgrow(spacer2, Priority.ALWAYS);
        HBox detailBar = new HBox(0, spacer2, btnAddNewDetail, btnEditDetail);

        detailListView = new ListView<>();
        detailListView.setMinSize(550, 250);
        detailListView.setMaxSize(550, 250);
        detailListView.setPrefSize(550, 250);
        detailListView.setPlaceholder(new Label(I18n.COMMON.getString("listView.dataNotFound")));

        // abrechnung
        nfAnfangsBetrag = ViewHelpers.createCurrencyField(150, false);
        nfAusgabenGesamt = ViewHelpers.createCurrencyField(150, false);
        lblAusgabenBelege = new Label();
        nfEinnahmenGesamt = ViewHelpers.createCurrencyField(150, false);
        lblEinnahmenBelege = new Label();
        nfSaldo = ViewHelpers.createCurrencyField(150, false);
        dpAbgerechnetAm = ViewHelpers.createDatePicker();


        add(new TitledSeparator(I18n.FAHRTKASSE.getString("fahrtkasse.form.details")), 0, 0, 3, 1);
        add(ViewHelpers.createSpaceLabel(), 3, 0);
        add(new TitledSeparator(I18n.FAHRTKASSE.getString("fahrtkasse.form.waehrungen")), 4, 0, 3, 1);

        add(detailBar, 0, 1, 3, 1);
        add(ViewHelpers.createSpaceLabel(), 3, 1);
        add(waehrungBar, 4, 1, 3, 1);

        add(detailListView, 0, 2, 3, 1);
        add(ViewHelpers.createSpaceLabel(), 3, 2);
        add(waehrungListView, 4, 2, 3, 1);

        add(new TitledSeparator(I18n.FAHRTKASSE.getString("fahrtkasse.form.abrechnung")), 0, 6, 3, 1);
        add(new Label(I18n.FAHRTKASSE.getString("fahrtkasse.form.anfangsBetrag")), 0, 7);
        add(nfAnfangsBetrag, 1, 7);

        add(new Label(I18n.FAHRTKASSE.getString("fahrtkasse.form.ausgabenGesamt")), 0, 8);
        add(nfAusgabenGesamt, 1, 8);
        add(lblAusgabenBelege, 2, 8);

        add(new Label(I18n.FAHRTKASSE.getString("fahrtkasse.form.einnahmenGesamt")), 0, 9);
        add(nfEinnahmenGesamt, 1, 9);
        add(lblEinnahmenBelege, 2, 9);

        add(new Label(I18n.FAHRTKASSE.getString("fahrtkasse.form.saldo")), 0, 10);
        add(nfSaldo, 1, 10);

        add(new Label(I18n.FAHRTKASSE.getString("fahrtkasse.form.abgerechnetAm")), 0, 11);
        add(dpAbgerechnetAm, 1, 11);
    }

    @Override
    public String getTitle() {
        return I18n.FAHRTKASSE.getString("fahrtkasse.form.tabAbrechnen");
    }

    @Override
    public String getIconPath() {
        return Images.ABRECHNEN_26;
    }

    @Override
    public Button getBtnAddNewKurs() {
        return btnAddNewKurs;
    }

    @Override
    public Button getBtnEditKurs() {
        return btnEditKurs;
    }

    @Override
    public ListView<FahrtkasseWaehrung> getWaehrungListView() {
        return waehrungListView;
    }

    @Override
    public Button getBtnAddNewDetail() {
        return btnAddNewDetail;
    }

    @Override
    public Button getBtnEditDetail() {
        return btnEditDetail;
    }

    @Override
    public ListView<FahrtkasseDetail> getDetailListView() {
        return detailListView;
    }

    @Override
    public NumberField getNfAnfangsBetrag() {
        return nfAnfangsBetrag;
    }

    @Override
    public NumberField getNfAusgabenGesamt() {
        return nfAusgabenGesamt;
    }

    @Override
    public Label getLblAusgabenBelege() {
        return lblAusgabenBelege;
    }

    @Override
    public NumberField getNfEinnahmenGesamt() {
        return nfEinnahmenGesamt;
    }

    @Override
    public Label getLblEinnahmenBelege() {
        return lblEinnahmenBelege;
    }

    @Override
    public NumberField getNfSaldo() {
        return nfSaldo;
    }

    @Override
    public DatePicker getDpAbgerechnetAm() {
        return dpAbgerechnetAm;
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
