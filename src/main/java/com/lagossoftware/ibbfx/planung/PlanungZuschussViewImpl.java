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
import com.lagossoftware.ibbfx.entity.Zuschuss;
import com.lagossoftware.lagosfx.common.ViewHelpers;
import com.lagossoftware.lagosfx.control.TitledSeparator;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

/**
 * Planung Zuschuss view implementation
 *
 * @author Cem Ikta
 */
public class PlanungZuschussViewImpl extends GridPane implements PlanungZuschussView {

    private Button btnAddNewZuschuss;
    private Button btnEditZuschuss;
    private ListView<Zuschuss> zuschussListView;

    public PlanungZuschussViewImpl() {
        buildView();
    }

    private void buildView() {
        setPadding(new Insets(2, 10, 10, 50));
        setHgap(10);
        setVgap(10);

        btnAddNewZuschuss = ViewHelpers.createIconButton(Images.PLUS_16);
        btnAddNewZuschuss.setTooltip(new Tooltip(
                I18n.PLANUNG.getString("planung.form.addNewZuschuss")));
        btnAddNewZuschuss.getStyleClass().add("left-pill");

        btnEditZuschuss = ViewHelpers.createIconButton(Images.EDIT_16);
        btnEditZuschuss.setTooltip(new Tooltip(
                I18n.PLANUNG.getString("planung.form.editZuschuss")));
        btnEditZuschuss.getStyleClass().add("right-pill");

        Region spacer2 = new Region();
        HBox.setHgrow(spacer2, Priority.ALWAYS);
        HBox zuschussBar = new HBox(0, spacer2, btnAddNewZuschuss, btnEditZuschuss);

        zuschussListView = new ListView<>();
        zuschussListView.setMinSize(700, 400);
        zuschussListView.setMaxSize(700, 400);
        zuschussListView.setPrefSize(700, 400);
        zuschussListView.setPlaceholder(new Label(I18n.COMMON.getString("listView.dataNotFound")));

        add(new TitledSeparator(I18n.PLANUNG.getString("planung.form.zuschuesse")), 0, 0, 3, 1);
        add(zuschussBar, 0, 1, 3, 1);
        add(zuschussListView, 0, 2, 3, 1);
    }

    @Override
    public String getTitle() {
        return I18n.PLANUNG.getString("planung.form.tabZuschuss");
    }

    @Override
    public String getIconPath() {
        return Images.ZUSCHUSS_26;
    }

    @Override
    public Button getBtnAddNewZuschuss() {
        return btnAddNewZuschuss;
    }

    @Override
    public Button getBtnEditZuschuss() {
        return btnEditZuschuss;
    }

    @Override
    public ListView<Zuschuss> getZuschussListView() {
        return zuschussListView;
    }

    @Override
    public Node asNode() {
        return this;
    }

}
