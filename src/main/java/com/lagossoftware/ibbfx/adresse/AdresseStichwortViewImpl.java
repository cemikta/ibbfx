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
import com.lagossoftware.ibbfx.entity.AdresseStichwort;
import com.lagossoftware.ibbfx.entity.Stichwort;
import com.lagossoftware.lagosfx.common.ViewHelpers;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 * Adresse Stichwort view implementation
 *
 * @author Cem Ikta
 */
public class AdresseStichwortViewImpl extends VBox implements AdresseStichwortView {

    private ComboBox<Stichwort> cbStichwort;
    private Button btnAddNewStichwort;
    private ListView<AdresseStichwort> stichwortListView;

    public AdresseStichwortViewImpl() {
        buildView();
    }

    private void buildView() {
        setPadding(new Insets(2, 10, 10, 50));
        setSpacing(10);
        
        cbStichwort = new ComboBox<>();
        cbStichwort.setPrefWidth(306);
        cbStichwort.setPlaceholder(new Label(
                I18n.ADRESSE.getString("adresse.form.selectStichwort")));
        btnAddNewStichwort = ViewHelpers.createIconButton(Images.PLUS_16);
        btnAddNewStichwort.setTooltip(new Tooltip(
                I18n.ADRESSE.getString("adresse.form.addNewStichwort")));
        HBox searchBar = new HBox(10, cbStichwort, btnAddNewStichwort);
        
        stichwortListView = new ListView<>();
        stichwortListView.setMinWidth(350);
        stichwortListView.setMaxWidth(350);
        stichwortListView.setPrefWidth(350);
        stichwortListView.setPlaceholder(new Label(
                I18n.ADRESSE.getString("adresse.form.keinStichwort")));
        
        getChildren().addAll(searchBar, stichwortListView);
        VBox.setVgrow(searchBar, Priority.NEVER);
        VBox.setVgrow(stichwortListView, Priority.ALWAYS);
        
    }

    @Override
    public String getTitle() {
        return I18n.ADRESSE.getString("adresse.form.tabStichwort");
    }

    @Override
    public String getIconPath() {
        return Images.STICHWORT_26;
    }

    @Override
    public ComboBox<Stichwort> getCbStichwort() {
        return cbStichwort;
    }

    @Override
    public Button getBtnAddNewStichwort() {
        return btnAddNewStichwort;
    }

    @Override
    public ListView<AdresseStichwort> getStichwortListView() {
        return stichwortListView;
    }

    @Override
    public Node asNode() {
        return this;
    }

}
