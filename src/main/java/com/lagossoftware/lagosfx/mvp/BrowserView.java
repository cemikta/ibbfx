/*
 * LagosFX application framework
 *
 * Copyright(c) 2014 - 2016, Cem Ikta
 */
package com.lagossoftware.lagosfx.mvp;

import com.lagossoftware.ibbfx.entity.BaseEntity;
import com.lagossoftware.lagosfx.control.Pager;
import com.lagossoftware.lagosfx.control.SearchBox;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;

import java.util.List;

/**
 * Browser view interface
 *
 * @param <E> base entity
 *
 * @author Cem Ikta
 */
public interface BrowserView<E extends BaseEntity> extends View {

    String getHeaderTitle();
    
    SearchBox getSearchBox();

    HBox getToolBar();

    Button getBtnAddNew();

    Button getBtnEdit();

    Button getBtnDelete();

    TableView<E> getTableView();
    
    void setData(List<E> data);

    Pager getPager();
    
}
