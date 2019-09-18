/*
 * LagosFX application framework
 *
 * Copyright(c) 2014 - 2016, Cem Ikta
 */
package com.lagossoftware.lagosfx.mvp;

import com.lagossoftware.ibbfx.entity.BaseEntity;
import com.lagossoftware.lagosfx.control.ButtonBarPane;
import javafx.scene.layout.GridPane;

/**
 * Multi page form view interface
 *
 * @param <E> base entity
 *
 * @author Cem Ikta
 */
public interface MultiPageFormView<E extends BaseEntity> extends FormView {
    
    ButtonBarPane getButtonBarPane();

    boolean hasOverviewPane();

    GridPane getOverviewPane();

}
