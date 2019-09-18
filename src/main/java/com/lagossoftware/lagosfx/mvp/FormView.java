/*
 * LagosFX application framework
 *
 * Copyright(c) 2014 - 2016, Cem Ikta
 */
package com.lagossoftware.lagosfx.mvp;

import com.lagossoftware.ibbfx.entity.BaseEntity;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import org.controlsfx.validation.ValidationSupport;

/**
 * Form view interface
 *
 * @param <E> base entity
 *
 * @author Cem Ikta
 */
public interface FormView<E extends BaseEntity> extends View {

    String getHeaderTitle();

    HBox getToolBar();

    Button getBtnBack();
    
    Button getBtnSave();
    
    Button getBtnAddNew();

    Button getBtnDelete();

    ValidationSupport getValidationSupport();
    
}
