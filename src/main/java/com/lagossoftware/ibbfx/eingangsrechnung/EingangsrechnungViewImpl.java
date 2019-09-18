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
package com.lagossoftware.ibbfx.eingangsrechnung;

import com.lagossoftware.ibbfx.app.I18n;
import com.lagossoftware.ibbfx.entity.Eingangsrechnung;
import com.lagossoftware.lagosfx.common.TableColumnBuilder;
import com.lagossoftware.lagosfx.mvp.AbstractBrowserView;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TableColumn;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Eingangsrechnung view implementation
 *
 * @author Cem Ikta
 */
public class EingangsrechnungViewImpl extends AbstractBrowserView<Eingangsrechnung> 
        implements EingangsrechnungView {

    public EingangsrechnungViewImpl() {
        super();
        buildView();
    }

    private void buildView() {
    }
    
    @Override
    public List<TableColumn<Eingangsrechnung, ?>> getTableViewColumns() {
        List<TableColumn<Eingangsrechnung, ?>> columns = new ArrayList<>();

        columns.add(TableColumnBuilder.<Eingangsrechnung, String>create("zahlungsstatus",
                I18n.EINGANGSRECHNUNG.getString("eingangsrechnung.browser.zahlungsstatus"),
                100));

        columns.add(TableColumnBuilder.<Eingangsrechnung, Long>create("belegNr", 
                I18n.EINGANGSRECHNUNG.getString("eingangsrechnung.browser.belegNr"), 
                100));

        // TODO search date with "2015-07-31", write in help dialog
        TableColumn<Eingangsrechnung, String> belegdatumColumn =
                TableColumnBuilder.<Eingangsrechnung, String>create("belegdatum", 
                I18n.EINGANGSRECHNUNG.getString("eingangsrechnung.browser.belegdatum"), 
                120, false, true, true, true);

        belegdatumColumn.setCellValueFactory(
            eingangsrechnung -> {
                SimpleStringProperty property = new SimpleStringProperty();
                DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
                property.setValue(dateFormat.format(eingangsrechnung.getValue().getBelegdatum()));
                return property;
        });
        columns.add(belegdatumColumn);
        
        columns.add(TableColumnBuilder.<Eingangsrechnung, String>create("rechnungsNr", 
                I18n.EINGANGSRECHNUNG.getString("eingangsrechnung.browser.rechnungsNr"), 
                150));
        
        TableColumn<Eingangsrechnung, String> rechnungsdatumColumn = 
                TableColumnBuilder.<Eingangsrechnung, String>create("rechnungsdatum", 
                I18n.EINGANGSRECHNUNG.getString("eingangsrechnung.browser.rechnungsdatum"), 
                170, false, true, true, true);
        
        rechnungsdatumColumn.setCellValueFactory(
            eingangsrechnung -> {
                SimpleStringProperty property = new SimpleStringProperty();
                DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
                property.setValue(dateFormat.format(eingangsrechnung.getValue().getRechnungsdatum()));
                return property;
        });
        columns.add(rechnungsdatumColumn);
        
        columns.add(TableColumnBuilder.<Eingangsrechnung, String>create("lieferant", 
                I18n.EINGANGSRECHNUNG.getString("eingangsrechnung.browser.lieferant"), 
                250));
        columns.add(TableColumnBuilder.<Eingangsrechnung, String>create("gegenstand", 
                I18n.EINGANGSRECHNUNG.getString("eingangsrechnung.browser.gegenstand"), 
                200));
        columns.add(TableColumnBuilder.<Eingangsrechnung, String>create("adresse", 
                I18n.EINGANGSRECHNUNG.getString("eingangsrechnung.browser.weitergeleitetAn"), 
                250));
        
        TableColumn<Eingangsrechnung, String> betragColumn = 
                TableColumnBuilder.<Eingangsrechnung, String>create("betrag", 
                I18n.EINGANGSRECHNUNG.getString("eingangsrechnung.browser.betrag"), 
                100, false, true, true, true);
        
        betragColumn.setCellValueFactory(
            eingangsrechnung -> {
                SimpleStringProperty property = new SimpleStringProperty();
                NumberFormat numberFormat = new DecimalFormat("#,###,###,##0.00");
                property.setValue(numberFormat.format(eingangsrechnung.getValue().getBetrag()));
                return property;
        });
        columns.add(betragColumn);
        
        columns.add(TableColumnBuilder.<Eingangsrechnung, String>create("waehrung", 
                I18n.EINGANGSRECHNUNG.getString("eingangsrechnung.browser.waehrung"), 
                100));
        
        return columns;
    }
    
    @Override
    public String getHeaderTitle() {
        return I18n.EINGANGSRECHNUNG.getString("eingangsrechnung.browser.title");
    }

}
