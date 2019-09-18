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
import com.lagossoftware.ibbfx.entity.Adresse;
import com.lagossoftware.ibbfx.entity.Eingangsrechnung;
import com.lagossoftware.lagosfx.common.TableColumnBuilder;
import com.lagossoftware.lagosfx.mvp.AbstractBrowserView;
import javafx.scene.control.TableColumn;

import java.util.ArrayList;
import java.util.List;

/**
 * Adresse view implementation
 *
 * @author Cem Ikta
 */
public class AdresseViewImpl extends AbstractBrowserView<Adresse> implements AdresseView {
    
    private AdressePreviewViewImpl preview;

    public AdresseViewImpl() {
        super();
        buildView();
    }

    private void buildView() {
    }
    
    @Override
    public List<TableColumn<Adresse, ?>> getTableViewColumns() {
        List<TableColumn<Adresse, ?>> columns = new ArrayList<>();

        columns.add(TableColumnBuilder.<Adresse, Long>create("adresseNr",
                I18n.ADRESSE.getString("adresse.browser.adresseNr"),
                100));
        columns.add(TableColumnBuilder.<Adresse, String>create("anrede",
                I18n.ADRESSE.getString("adresse.browser.anrede"), 
                100));
        columns.add(TableColumnBuilder.<Adresse, String>create("titel", 
                I18n.ADRESSE.getString("adresse.browser.titel"), 
                100));
        columns.add(TableColumnBuilder.<Adresse, String>create("vorname", 
                I18n.ADRESSE.getString("adresse.browser.vorname"), 
                200));
        columns.add(TableColumnBuilder.<Adresse, String>create("nachname", 
                I18n.ADRESSE.getString("adresse.browser.nachname"), 
                200));
        columns.add(TableColumnBuilder.<Adresse, String>create("firma1", 
                I18n.ADRESSE.getString("adresse.browser.firma1"), 
                200));
        columns.add(TableColumnBuilder.<Adresse, String>create("strasse", 
                I18n.ADRESSE.getString("adresse.browser.strasse"), 
                200));
        columns.add(TableColumnBuilder.<Adresse, String>create("plz", 
                I18n.ADRESSE.getString("adresse.browser.plz"), 
                70));
        columns.add(TableColumnBuilder.<Adresse, String>create("ort", 
                I18n.ADRESSE.getString("adresse.browser.ort"), 
                200));
        columns.add(TableColumnBuilder.<Adresse, String>create("bundesland", 
                I18n.ADRESSE.getString("adresse.browser.bundesland"), 
                200));
        columns.add(TableColumnBuilder.<Adresse, String>create("land", 
                I18n.ADRESSE.getString("adresse.browser.land"), 
                200));
        columns.add(TableColumnBuilder.<Adresse, String>create("postfachPlz", 
                I18n.ADRESSE.getString("adresse.browser.postfachPlz"), 
                150, true, true, true, false));
        columns.add(TableColumnBuilder.<Adresse, String>create("postfach", 
                I18n.ADRESSE.getString("adresse.browser.postfach"), 
                150, true, true, true, false));
        columns.add(TableColumnBuilder.<Adresse, String>create("mobiltelefon", 
                I18n.ADRESSE.getString("adresse.browser.mobiltelefon"), 
                150));
        columns.add(TableColumnBuilder.<Adresse, String>create("email", 
                I18n.ADRESSE.getString("adresse.browser.email"), 
                200));
        columns.add(TableColumnBuilder.<Adresse, String>create("homepage", 
                I18n.ADRESSE.getString("adresse.browser.homepage"), 
                200, true, true, true, false));
        columns.add(TableColumnBuilder.<Adresse, String>create("telefonPrivat", 
                I18n.ADRESSE.getString("adresse.browser.telefonPrivat"), 
                150, true, true, true, false));
        columns.add(TableColumnBuilder.<Adresse, String>create("telefonDienst", 
                I18n.ADRESSE.getString("adresse.browser.telefonDienst"), 
                150, true, true, true, false));
        columns.add(TableColumnBuilder.<Adresse, String>create("faxPrivat", 
                I18n.ADRESSE.getString("adresse.browser.faxPrivat"), 
                150, true, true, true, false));
        columns.add(TableColumnBuilder.<Adresse, String>create("faxDienst", 
                I18n.ADRESSE.getString("adresse.browser.faxDienst"), 
                150, true, true, true, false));
        
        return columns;
    }
    
    @Override
    public String getHeaderTitle() {
        return I18n.ADRESSE.getString("adresse.browser.title");
    }

}
