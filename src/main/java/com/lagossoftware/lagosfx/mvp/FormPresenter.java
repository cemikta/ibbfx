/*
 * LagosFX application framework
 *
 * Copyright(c) 2014 - 2016, Cem Ikta
 */
package com.lagossoftware.lagosfx.mvp;

import com.lagossoftware.ibbfx.app.AppPlaces;
import com.lagossoftware.ibbfx.entity.BaseEntity;
import com.lagossoftware.lagosfx.service.AbstractService;

/**
 * Form presenter interface
 *
 * @param <E> base entity
 * @param <V> view 
 * @param <S> service
 * 
 * @author Cem Ikta
 */
public interface FormPresenter<E extends BaseEntity, V extends FormView<E>, 
        S extends AbstractService<E>> extends Presenter<V>  {

    E getEntity();
    
    @Override
    V getView();
    
    S getService();
    
    void onBack();

    void onSave();
    
    void onAddNew();

    void onDelete();
    
    void setRequestFocus();

    void popFields();

    void pushFields();

    boolean isFormValid();

    boolean isNewModel();

    AppPlaces getPlaceName();

}
