/*
 * LagosFX application framework
 *
 * Copyright(c) 2014 - 2016, Cem Ikta
 */
package com.lagossoftware.lagosfx.mvp;

import com.lagossoftware.ibbfx.entity.BaseEntity;
import com.lagossoftware.lagosfx.control.SearchBox;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import org.controlsfx.validation.ValidationSupport;

import java.util.List;

/**
 * Browser form view interface
 *
 * @param <E> base entity
 *
 * @author Cem Ikta
 */
public interface BrowserFormView<E extends BaseEntity> extends View {

    String getHeaderTitle();

    HBox getToolBar();

    Button getBtnAddNew();

    Button getBtnRefresh();

    Button getBtnDelete();

    Button getBtnSave();

    SearchBox getSearchBox();

    ListView<E> getListView();

    void setData(List<E> data);

    ValidationSupport getValidationSupport();

}
