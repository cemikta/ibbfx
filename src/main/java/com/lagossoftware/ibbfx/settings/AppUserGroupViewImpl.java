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
package com.lagossoftware.ibbfx.settings;

import com.lagossoftware.ibbfx.app.I18n;
import com.lagossoftware.ibbfx.app.Images;
import com.lagossoftware.ibbfx.entity.AppRight;
import com.lagossoftware.ibbfx.entity.AppUserGroup;
import com.lagossoftware.lagosfx.common.TableColumnBuilder;
import com.lagossoftware.lagosfx.common.ViewHelpers;
import com.lagossoftware.lagosfx.control.TitledSeparator;
import com.lagossoftware.lagosfx.mvp.AbstractBrowserFormView;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import org.controlsfx.validation.Validator;

import java.util.ArrayList;
import java.util.List;

/**
 * App user group view implementation
 *
 * @author Cem Ikta
 */
public class AppUserGroupViewImpl extends AbstractBrowserFormView<AppUserGroup>
        implements AppUserGroupView {

    private Button btnCheck;
    private Button btnUncheck;
    private TextField tfName;
    private TableView<AppRightModel> appRightTableView;
    private TableColumn<AppRightModel, String> nameCol;

    public AppUserGroupViewImpl() {
        super();
        buildView();
    }

    private void buildView() {
        btnCheck = ViewHelpers.createIconButton(Images.CHECKED_24);
        btnCheck.setTooltip(new Tooltip(I18n.COMMON.getString("action.check")));
        btnCheck.getStyleClass().add("left-pill");

        btnUncheck = ViewHelpers.createIconButton(Images.UNCHECKED_24);
        btnUncheck.setTooltip(new Tooltip(I18n.COMMON.getString("action.uncheck")));
        btnUncheck.getStyleClass().add("right-pill");
        HBox.setMargin(btnUncheck, new Insets(0, 10, 0, 0));

        getToolBar().getChildren().add(4, btnCheck);
        getToolBar().getChildren().add(5, btnUncheck);
    }

    @Override
    public GridPane getFormPane() {
        GridPane formPane = new GridPane();
        formPane.setHgap(10);
        formPane.setVgap(10);

        tfName = new TextField();
        tfName.setMinWidth(300);
        tfName.setMaxWidth(300);
        tfName.setPrefWidth(300);
        getValidationSupport().registerValidator(tfName,
                Validator.createEmptyValidator(I18n.COMMON.getString("validation.required")));

        appRightTableView = new TableView<>();
        appRightTableView.setPlaceholder(new Label(I18n.COMMON.getString("tableView.dataNotFound")));
        appRightTableView.setTableMenuButtonVisible(false);
        appRightTableView.setEditable(true);
        appRightTableView.getColumns().addAll(getAppRightTableViewColumns());

        formPane.add(new Label(I18n.SETTINGS.getString("appUserGroup.name")), 0, 0);
        formPane.add(tfName, 1, 0, 1, 1);

        formPane.add(new TitledSeparator(I18n.SETTINGS.getString("appUserGroup.appRights")), 0, 1, 3, 1);
        formPane.add(appRightTableView, 0, 2, 3, 1);
        GridPane.setVgrow(appRightTableView, Priority.ALWAYS);
        GridPane.setHgrow(appRightTableView, Priority.ALWAYS);

        return formPane;
    }

    private List<TableColumn<AppRightModel, ?>> getAppRightTableViewColumns() {
        List<TableColumn<AppRightModel, ?>> columns = new ArrayList<>();

        TableColumn<AppRightModel, Long> idCol =
                TableColumnBuilder.<AppRightModel, Long>create("id",
                        I18n.SETTINGS.getString("appUserGroup.id"), 50, true, true, true, false);
        columns.add(idCol);

        TableColumn<AppRightModel, String> moduleNameCol =
                TableColumnBuilder.<AppRightModel, String>create("moduleName",
                        I18n.SETTINGS.getString("appUserGroup.moduleName"), 100);
        columns.add(moduleNameCol);

        TableColumn<AppRightModel, String> objecName1Col =
                TableColumnBuilder.<AppRightModel, String>create("objectName1",
                        I18n.SETTINGS.getString("appUserGroup.objectName"), 50, true, true, true, false);
        columns.add(objecName1Col);

        TableColumn<AppRightModel, String> objecName2Col =
                TableColumnBuilder.<AppRightModel, String>create("objectName2",
                        I18n.SETTINGS.getString("appUserGroup.objectName"), 50, true, true, true, false);
        columns.add(objecName2Col);

        nameCol = TableColumnBuilder.<AppRightModel, String>create("name",
                        I18n.SETTINGS.getString("appUserGroup.menuName"), 200);
        columns.add(nameCol);

        TableColumn<AppRightModel, Boolean> allowReadCol =
                TableColumnBuilder.<AppRightModel, Boolean>create("allowRead",
                        I18n.SETTINGS.getString("appUserGroup.allowRead"), 100);
        allowReadCol.setCellFactory(CheckBoxTableCell.forTableColumn(allowReadCol));
        allowReadCol.setEditable(true);
        columns.add(allowReadCol);

        TableColumn<AppRightModel, Boolean> allowCreateCol =
                TableColumnBuilder.<AppRightModel, Boolean>create("allowCreate",
                        I18n.SETTINGS.getString("appUserGroup.allowCreate"), 100);
        allowCreateCol.setCellFactory(CheckBoxTableCell.forTableColumn(allowReadCol));
        allowCreateCol.setEditable(true);
        columns.add(allowCreateCol);

        TableColumn<AppRightModel, Boolean> allowEditCol =
                TableColumnBuilder.<AppRightModel, Boolean>create("allowEdit",
                        I18n.SETTINGS.getString("appUserGroup.allowEdit"), 100);
        allowEditCol.setCellFactory(CheckBoxTableCell.forTableColumn(allowReadCol));
        allowEditCol.setEditable(true);
        columns.add(allowEditCol);

        TableColumn<AppRightModel, Boolean> allowDeleteCol =
                TableColumnBuilder.<AppRightModel, Boolean>create("allowDelete",
                        I18n.SETTINGS.getString("appUserGroup.allowDelete"), 100);
        allowDeleteCol.setCellFactory(CheckBoxTableCell.forTableColumn(allowReadCol));
        allowDeleteCol.setEditable(true);
        columns.add(allowDeleteCol);

        return columns;
    }

    @Override
    public String getHeaderTitle() {
        return I18n.SETTINGS.getString("appUserGroup.title");
    }

    @Override
    public Button getBtnCheck() {
        return btnCheck;
    }

    @Override
    public Button getBtnUncheck() {
        return btnUncheck;
    }

    @Override
    public TextField getTfName() {
        return tfName;
    }

    @Override
    public TableView<AppRightModel> getAppRightTableView() {
        return appRightTableView;
    }

    @Override
    public TableColumn<AppRightModel, String> getSortColumn() {
        return nameCol;
    }

}
