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
import com.lagossoftware.lagosfx.control.SearchBox;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.*;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.decoration.GraphicValidationDecoration;

import java.util.List;

/**
 * Abstract browser form view.
 * 
 * @param <E> base entity 
 * 
 * @author Cem Ikta
 */
public abstract class AbstractBrowserFormView<E extends BaseEntity>
        extends BorderPane implements BrowserFormView<E> {

    private SearchBox searchBox;
    private Button btnRefresh;
    private ListView<E> listView;

    private HBox toolBar;
    private Button btnAddNew;
    private Button btnSave;
    private Button btnDelete;
    private ValidationSupport validationSupport;

    public AbstractBrowserFormView() {
        super();
        buildView();
    }

    private void buildView() {
        setTop(buildToolBar());

        listView = new ListView<>();
        listView.setPrefWidth(350);
        listView.setPlaceholder(new Label(I18n.COMMON.getString("listView.dataNotFound")));
        setMargin(listView, new Insets(20, 0, 10, 10));
        
        GridPane formPane = getFormPane();
        setMargin(formPane, new Insets(22, 10, 10, 50));

        setLeft(listView);
        setCenter(formPane);
    }
    
    private HBox buildToolBar() {
        btnAddNew = ViewHelpers.createIconButton(Images.ADD_NEW_24,
                I18n.COMMON.getString("action.addNew"));
        btnAddNew.getStyleClass().add("left-pill");

        btnRefresh = ViewHelpers.createIconButton(Images.REFRESH_24, 
                I18n.COMMON.getString("action.refresh"));
        btnRefresh.getStyleClass().add("right-pill");
        HBox.setMargin(btnRefresh, new Insets(0, 10, 0, 0));

        btnDelete = ViewHelpers.createIconButton(Images.DELETE_24,
                I18n.COMMON.getString("action.delete"));
        HBox.setMargin(btnDelete, new Insets(0, 10, 0, 0));

        btnSave = ViewHelpers.createIconButton(Images.SAVE_24,
                I18n.COMMON.getString("action.save"));
        HBox.setMargin(btnSave, new Insets(0, 10, 0, 0));

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        
        searchBox = new SearchBox();
        
        toolBar = new HBox(0);
        toolBar.setPadding(new Insets(10, 10, 10, 10));
        toolBar.getStyleClass().add("page-tool-bar");
        toolBar.getChildren().addAll(btnAddNew, btnRefresh, btnDelete, btnSave, 
                spacer, searchBox);
        
        return toolBar;
    }

    @Override
    public HBox getToolBar() {
        return toolBar;
    }

    @Override
    public SearchBox getSearchBox() {
        return searchBox;
    }

    @Override
    public Button getBtnRefresh() {
        return btnRefresh;
    }

    public abstract GridPane getFormPane();

    @Override
    public Button getBtnAddNew() {
        return btnAddNew;
    }

    @Override
    public Button getBtnSave() {
        return btnSave;
    }

    @Override
    public Button getBtnDelete() {
        return btnDelete;
    }

    @Override
    public ListView<E> getListView() {
        return listView;
    }

    @Override
    public void setData(List<E> data) {
        listView.getItems().clear();
        if (data != null) {
            listView.getItems().addAll(data);
        }
        listView.getSelectionModel().selectFirst();
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
