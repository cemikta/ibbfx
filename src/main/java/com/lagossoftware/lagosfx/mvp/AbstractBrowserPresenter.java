/*
 * LagosFX application framework
 *
 * Copyright(c) 2014 - 2016, Cem Ikta
 */
package com.lagossoftware.lagosfx.mvp;

import com.lagossoftware.ibbfx.IbbManagement;
import com.lagossoftware.ibbfx.app.I18n;
import com.lagossoftware.ibbfx.entity.BaseEntity;
import com.lagossoftware.lagosfx.control.MessageBox;
import com.lagossoftware.lagosfx.navigation.NavigationManager;
import com.lagossoftware.lagosfx.service.AbstractService;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import org.apache.commons.lang3.StringUtils;
import org.controlsfx.control.Notifications;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

/**
 * Abstract browser presenter
 *
 * @param <E> base entity
 * @param <V> view
 * @param <S> service
 *
 * @author Cem Ikta
 */
public abstract class AbstractBrowserPresenter<E extends BaseEntity, 
        V extends BrowserView<E>, S extends AbstractService<E>>
            implements BrowserPresenter<E, V, S> {

    protected NavigationManager navigationManager;
    protected final Logger logger;

    protected E entity;
    private Class<E> entityClass;
    
    protected V view;
    protected S service;
    
    private int currentPage = 0;
    private int rowCount = 0;
    private int pageCount = 0;
    private final int pageSize = 20;

    public AbstractBrowserPresenter(final NavigationManager navigationManager, 
            final Logger logger, V view, S service) {
        this.navigationManager = navigationManager;
        this.logger = logger;
        this.view = view;
        this.service = service;
    }

    @Override
    public E getEntity() {
        return this.entity;
    }

    @Override
    public V getView() {
        return this.view;
    }

    @Override
    public S getService() {
        return this.service;
    }

    @Override
    public void bind() {
        view.getSearchBox().textProperty().addListener(
                (ObservableValue<? extends String> observable,
                        String oldValue, String newValue) -> {
                    onSearch();
                }
        );

        view.getBtnDelete().setOnAction((ActionEvent event) -> {
            onDelete();
        });
        
        view.getTableView().setOnMouseClicked((MouseEvent event) -> {
            if (event.getClickCount() > 1) {
                onEdit();
            }            
        });
        
        view.getPager().setRefreshCallback(() -> {
            onSearch();
        });
        
        navigationManager.setCurrentPageTitle(view.getHeaderTitle());
    }

    @Override
    public void onSearch() {
        Task<List<E>> task = new Task<List<E>>() {
            @Override
            protected List<E> call() throws Exception {
                if (StringUtils.isEmpty(view.getSearchBox().getText())) {
                    return service.getListWithNamedQuery(getNamedQuery(), 
                            view.getPager().getStartPosition(), 
                            view.getPager().getEndPosition());
                } else {
                    return service.getListWithNamedQuery(
                            getNamedQueryWithFilter(), getFilterParameters(), 
                            view.getPager().getStartPosition(), 
                            view.getPager().getEndPosition());
                }
            }
        };

        task.stateProperty().addListener(
                (ObservableValue<? extends Worker.State> source,
                        Worker.State oldState, Worker.State newState) -> {
                if (newState.equals(Worker.State.SUCCEEDED)) {
                    view.setData(task.getValue());
                    // set row count    
                    if (StringUtils.isEmpty(view.getSearchBox().getText())) {
                        view.getPager().setRowCount(
                                service.getListWithNamedQuery(getNamedQuery()).size());
                    } else {
                        view.getPager().setRowCount(
                                service.getListWithNamedQuery(
                                        getNamedQueryWithFilter(), 
                                        getFilterParameters()).size());
                    }
                }
            }
        );

        new Thread(task).start();
    }

    @Override
    public void onDelete() {
        if (view.getTableView().getSelectionModel().getSelectedItem() != null) {

            if (IbbManagement.get().isDeleteAllowed(getPlaceName())) {
                Optional<ButtonType> result = MessageBox.get()
                        .contentText(I18n.COMMON.getString("dialog.ask.delete"))
                        .showConfirmation();
                if (result.get() == ButtonType.YES){
                    try {
                        service.remove(entity);
                        onSearch();
                        Notifications.create()
                                .text(I18n.COMMON.getString("notification.deleteOk"))
                                .position(Pos.TOP_RIGHT).showInformation();
                    } catch (Exception ex) {
                        MessageBox.get()
                                .contentText(I18n.COMMON.getString("notification.deleteException"))
                                .showError(ex);
                    }
                }
            }
        }
    }
    
    public final void createNewEntity() {
        try {
            entity = getEntityClass().newInstance();
            logger.info("create new entity");
        } catch (InstantiationException | IllegalAccessException ex) {
            logger.severe("create new entity error");
        }
    }

    @SuppressWarnings("unchecked")
    public Class<E> getEntityClass() {
        if (entityClass == null) {
            Type type = getClass().getGenericSuperclass();
            if (type instanceof ParameterizedType) {
                ParameterizedType paramType = (ParameterizedType) type;
                entityClass = (Class<E>) paramType.getActualTypeArguments()[0];
            } else {
                throw new IllegalArgumentException("Entity class not found by reflection");
            }
        }
        return entityClass;
    }

}
