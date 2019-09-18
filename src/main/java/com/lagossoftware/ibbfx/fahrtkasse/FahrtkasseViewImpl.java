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
import com.lagossoftware.ibbfx.entity.Eingangsrechnung;
import com.lagossoftware.ibbfx.entity.Fahrtkasse;
import com.lagossoftware.ibbfx.settings.AppRightModel;
import com.lagossoftware.lagosfx.common.TableColumnBuilder;
import com.lagossoftware.lagosfx.mvp.AbstractBrowserView;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.CheckBoxTableCell;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Fahrtkasse view implementation
 *
 * @author Cem Ikta
 */
public class FahrtkasseViewImpl extends AbstractBrowserView<Fahrtkasse>
        implements FahrtkasseView {

    public FahrtkasseViewImpl() {
        super();
        buildView();
    }

    private void buildView() {
    }

    @Override
    public List<TableColumn<Fahrtkasse, ?>> getTableViewColumns() {
        List<TableColumn<Fahrtkasse, ?>> columns = new ArrayList<>();

        columns.add(TableColumnBuilder.<Fahrtkasse, Long>create("fahrtkasseNr",
                I18n.FAHRTKASSE.getString("fahrtkasse.browser.fahrtkasseNr"),
                170));

        TableColumn<Fahrtkasse, String> belegdatumColumn =
                TableColumnBuilder.<Fahrtkasse, String>create("belegdatum",
                        I18n.FAHRTKASSE.getString("fahrtkasse.browser.belegdatum"),
                        170, false, true, true, true);

        belegdatumColumn.setCellValueFactory(
                fahrtkasse -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
                    property.setValue(dateFormat.format(fahrtkasse.getValue().getBelegdatum()));
                    return property;
                });
        columns.add(belegdatumColumn);

        columns.add(TableColumnBuilder.<Fahrtkasse, String>create("adresse",
                I18n.FAHRTKASSE.getString("fahrtkasse.browser.fahrtkassenempfaenger"),
                300));

        columns.add(TableColumnBuilder.<Fahrtkasse, String>create("planung",
                I18n.FAHRTKASSE.getString("fahrtkasse.browser.veranstaltungsNr"),
                200));

        TableColumn<Fahrtkasse, String> betragColumn =
                TableColumnBuilder.<Fahrtkasse, String>create("betrag",
                        I18n.FAHRTKASSE.getString("fahrtkasse.browser.betrag"),
                        150, false, true, true, true);

        betragColumn.setCellValueFactory(
                fahrtkasse -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    NumberFormat numberFormat = new DecimalFormat("#,###,###,##0.00");
                    property.setValue(numberFormat.format(fahrtkasse.getValue().getBetrag()));
                    return property;
                });
        columns.add(betragColumn);

        TableColumn<Fahrtkasse, String> zahlenAmColumn =
                TableColumnBuilder.<Fahrtkasse, String>create("zahlenAm",
                        I18n.FAHRTKASSE.getString("fahrtkasse.browser.zahlenAm"),
                        150, false, true, true, true);

        zahlenAmColumn.setCellValueFactory(
                fahrtkasse -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
                    property.setValue(dateFormat.format(fahrtkasse.getValue().getZahlenAm()));
                    return property;
                });
        columns.add(zahlenAmColumn);

        return columns;
    }

    @Override
    public String getHeaderTitle() {
        return I18n.FAHRTKASSE.getString("fahrtkasse.browser.title");
    }

}
