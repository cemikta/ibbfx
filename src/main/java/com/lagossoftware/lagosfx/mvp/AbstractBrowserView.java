/*
 * LagosFX application framework
 *
 * Copyright(c) 2014 - 2016, Cem Ikta
 */
package com.lagossoftware.lagosfx.mvp;

import com.lagossoftware.ibbfx.app.I18n;
import com.lagossoftware.ibbfx.app.Images;
import com.lagossoftware.ibbfx.entity.BaseEntity;
import com.lagossoftware.lagosfx.common.ViewHelpers;
import com.lagossoftware.lagosfx.control.Pager;
import com.lagossoftware.lagosfx.control.SearchBox;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.*;

import java.util.List;

/**
 * Abstract browser view.
 * 
 * @param <E> base entity 
 * 
 * @author Cem Ikta
 */
public abstract class AbstractBrowserView<E extends BaseEntity>
        extends BorderPane implements BrowserView<E> {

    private HBox toolBar;    
    private SearchBox searchBox;
    private Button btnAddNew;
    private Button btnEdit;
    private Button btnDelete;
    private TableView<E> tableView;
    private Pager pager;

    public AbstractBrowserView() {
        super();
        buildView();
    }

    private void buildView() {
        setTop(buildToolBar());
        
        VBox tableBox = new VBox();
        pager = new Pager();
        tableBox.getChildren().addAll(buildTableView(), pager);

        VBox.setVgrow(tableView, Priority.ALWAYS);
        VBox.setVgrow(pager, Priority.NEVER);
        VBox.setMargin(pager, new Insets(10, 10, 10, 10));
        VBox.setVgrow(tableBox, Priority.ALWAYS);
        
        setCenter(tableBox);
    }

    private HBox buildToolBar() {
        btnAddNew = ViewHelpers.createIconButton(Images.ADD_NEW_24,
                I18n.COMMON.getString("action.addNew"));
        btnAddNew.getStyleClass().add("left-pill");

        btnEdit = ViewHelpers.createIconButton(Images.EDIT_24,
                I18n.COMMON.getString("action.edit"));
        btnEdit.getStyleClass().addAll("right-pill");
        HBox.setMargin(btnEdit, new Insets(0, 10, 0, 0));

        btnDelete = ViewHelpers.createIconButton(Images.DELETE_24,
                I18n.COMMON.getString("action.delete"));
        HBox.setMargin(btnDelete, new Insets(0, 10, 0, 0));

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        
        searchBox = new SearchBox();
        
        toolBar = new HBox(0);
        toolBar.setPadding(new Insets(10, 10, 10, 10));
        toolBar.getStyleClass().add("page-tool-bar");
        toolBar.getChildren().addAll(btnAddNew, btnEdit, btnDelete, spacer, searchBox);
        
        return toolBar;
    }
    
    private TableView buildTableView() {
        tableView = new TableView<>();
        tableView.setPlaceholder(new Label(I18n.COMMON.getString("tableView.dataNotFound")));
        tableView.setEditable(false);
        tableView.setTableMenuButtonVisible(true);
        tableView.getColumns().addAll(getTableViewColumns());
        return tableView;
    }
    
    @Override
    public SearchBox getSearchBox() {
        return searchBox;
    }

    @Override
    public HBox getToolBar() {
        return toolBar;
    }

    @Override
    public Button getBtnAddNew() {
        return btnAddNew;
    }

    @Override
    public Button getBtnEdit() {
        return btnEdit;
    }

    @Override
    public Button getBtnDelete() {
        return btnDelete;
    }

    @Override
    public TableView<E> getTableView() {
        return tableView;
    }
    
    public abstract List<TableColumn<E, ?>> getTableViewColumns();

    @Override
    public void setData(List<E> data) {
        tableView.getItems().clear();
        if (data != null) {
            tableView.getItems().addAll(data);
        }
        tableView.getSelectionModel().selectFirst();
    }

    @Override
    public Pager getPager() {
        return pager;
    }
    
    @Override
    public Node asNode() {
        return this;
    }

}
