/*
 * LagosFX application framework
 *
 * Copyright(c) 2014 - 2016, Cem Ikta
 */
package com.lagossoftware.lagosfx.mvp;

import com.lagossoftware.ibbfx.app.AppPlaces;
import com.lagossoftware.ibbfx.entity.BaseEntity;
import com.lagossoftware.lagosfx.service.AbstractService;

import java.util.Map;

/**
 * Browser presenter interface
 *
 * @param <E> base entity
 * @param <V> view 
 * @param <S> service
 * 
 * @author Cem Ikta
 */
public interface BrowserPresenter<E extends BaseEntity, V extends BrowserView<E>, 
        S extends AbstractService<E>> extends Presenter<V>  {

    E getEntity();
    
    @Override
    V getView();
    
    S getService();
    
    void onSearch();
    
    String getNamedQuery();
    
    String getNamedQueryWithFilter();

    Map<String, Object> getFilterParameters();

    void onAddNew();

    void onEdit();

    void onDelete();

    AppPlaces getPlaceName();

}
