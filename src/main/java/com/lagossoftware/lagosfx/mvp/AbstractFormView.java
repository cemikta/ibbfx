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
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.decoration.GraphicValidationDecoration;

/**
 * Abstract form view.
 * 
 * @param <E> base entity 
 * 
 * @author Cem Ikta
 */
public abstract class AbstractFormView<E extends BaseEntity>
        extends BorderPane implements FormView<E> {

    private HBox toolBar;    
    private Button btnBack;
    private Button btnAddNew;
    private Button btnDelete;
    private Button btnSave;
    private ValidationSupport validationSupport;

    public AbstractFormView() {
        super();
        buildView();
    }

    private void buildView() {
        setTop(buildToolBar());
        
        GridPane formPane = getFormPane();
        setMargin(formPane, new Insets(20, 10, 10, 10));
        setCenter(formPane);
    }
    
    private HBox buildToolBar() {
        btnBack = ViewHelpers.createIconButton(Images.BACK_24,
                I18n.COMMON.getString("action.back"));
        HBox.setMargin(btnBack, new Insets(0, 10, 0, 0));
        
        btnAddNew = ViewHelpers.createIconButton(Images.ADD_NEW_24,
                I18n.COMMON.getString("action.addNew"));
        btnAddNew.getStyleClass().add("left-pill");

        btnDelete = ViewHelpers.createIconButton(Images.DELETE_24,
                I18n.COMMON.getString("action.delete"));
        btnDelete.getStyleClass().add("right-pill");
        HBox.setMargin(btnDelete, new Insets(0, 10, 0, 0));

        btnSave = ViewHelpers.createIconButton(Images.SAVE_24,
                I18n.COMMON.getString("action.save"));
        HBox.setMargin(btnSave, new Insets(0, 10, 0, 0));

        toolBar = new HBox(0);
        toolBar.setPadding(new Insets(10, 10, 10, 10));
        toolBar.getStyleClass().add("page-tool-bar");
        toolBar.getChildren().addAll(btnBack, btnAddNew, btnDelete, btnSave);
        
        return toolBar;
    }

    public abstract GridPane getFormPane();

    @Override
    public HBox getToolBar() {
        return toolBar;
    }

    @Override
    public Button getBtnBack() {
        return btnBack;
    }

    @Override
    public Button getBtnSave() {
        return btnSave;
    }

    @Override
    public Button getBtnAddNew() {
        return btnAddNew;
    }
    
    @Override
    public Button getBtnDelete() {
        return btnDelete;
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
