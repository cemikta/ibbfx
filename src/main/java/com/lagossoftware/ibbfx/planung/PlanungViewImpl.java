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
import com.lagossoftware.ibbfx.entity.Planung;
import com.lagossoftware.lagosfx.common.TableColumnBuilder;
import com.lagossoftware.lagosfx.common.ViewHelpers;
import com.lagossoftware.lagosfx.mvp.AbstractBrowserView;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.HBox;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Planung view implementation
 *
 * @author Cem Ikta
 */
public class PlanungViewImpl extends AbstractBrowserView<Planung> implements PlanungView {

    private Button btnDuplicate;

    public PlanungViewImpl() {
        super();
        buildView();
    }

    private void buildView() {
        btnDuplicate = ViewHelpers.createIconButton(Images.DUPLICATE_24,
                I18n.COMMON.getString("action.duplicate"));
        HBox.setMargin(btnDuplicate, new Insets(0, 10, 0, 0));

        getToolBar().getChildren().add(2, btnDuplicate);
    }
    
    @Override
    public List<TableColumn<Planung, ?>> getTableViewColumns() {
        List<TableColumn<Planung, ?>> columns = new ArrayList<>();

        columns.add(TableColumnBuilder.<Planung, Long>create("planungNr",
                I18n.PLANUNG.getString("planung.browser.planungNr"),
                200));

        columns.add(TableColumnBuilder.<Planung, String>create("zusatz",
                I18n.PLANUNG.getString("planung.browser.zusatz"),
                100));

        columns.add(TableColumnBuilder.<Planung, String>create("titel",
                I18n.PLANUNG.getString("planung.browser.planungTitel"),
                300));

        columns.add(TableColumnBuilder.<Planung, String>create("veranstaltungOrt",
                I18n.PLANUNG.getString("planung.browser.ort"),
                300));

        // TODO search date with "2015-07-31", write in help dialog
        TableColumn<Planung, String> veranstaltungsBeginnColumn =
                TableColumnBuilder.<Planung, String>create("veranstaltungsBeginn",
                        I18n.PLANUNG.getString("planung.browser.veranstaltungsBeginn"),
                        200, false, true, true, true);

        veranstaltungsBeginnColumn.setCellValueFactory(
                planung -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
                    property.setValue(dateFormat.format(planung.getValue().getVeranstaltungBeginn()));
                    return property;
                });
        columns.add(veranstaltungsBeginnColumn);

        TableColumn<Planung, String> veranstaltungsEndeColumn =
                TableColumnBuilder.<Planung, String>create("veranstaltungsEnde",
                        I18n.PLANUNG.getString("planung.browser.veranstaltungsEnde"),
                        200, false, true, true, true);

        veranstaltungsEndeColumn.setCellValueFactory(
                planung -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
                    property.setValue(dateFormat.format(planung.getValue().getVeranstaltungEnde()));
                    return property;
                });
        columns.add(veranstaltungsEndeColumn);

        return columns;
    }
    
    @Override
    public String getHeaderTitle() {
        return I18n.PLANUNG.getString("planung.browser.title");
    }

    @Override
    public Button getBtnDuplicate() {
        return btnDuplicate;
    }
}
