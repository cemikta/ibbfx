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
import javafx.collections.ListChangeListener;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.ButtonType;
import org.apache.commons.lang3.StringUtils;
import org.controlsfx.control.Notifications;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

/**
 * Abstract browser form presenter
 *
 * @param <E> base entity
 * @param <V> view
 * @param <S> service
 *
 * @author Cem Ikta
 */
public abstract class AbstractBrowserFormPresenter<E extends BaseEntity, 
        V extends BrowserFormView<E>, S extends AbstractService<E>>
            implements BrowserFormPresenter<E, V, S> {

    protected NavigationManager navigationManager;
    protected final Logger logger;

    protected E entity;
    private Class<E> entityClass;
    protected final V view;
    protected final S service;

    public AbstractBrowserFormPresenter(final NavigationManager navigationManager,
            final Logger logger, final V view, S service) {
        this.navigationManager = navigationManager;
        this.logger = logger;
        this.view = view;
        this.service = service;

        createNewEntity();
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

        view.getBtnRefresh().setOnAction((ActionEvent event) -> {
            onSearch();
        });

        view.getListView().getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends E> observable, E oldValue,
                        E newValue) -> {
                    if (newValue != null) {
                        entity = newValue;
                        popFields();
                    }
                }
        );

        view.getBtnAddNew().setOnAction((ActionEvent event) -> {
            onAddNew();
        });

        view.getBtnSave().setOnAction((ActionEvent event) -> {
            onSave();
        });

        view.getBtnDelete().setOnAction((ActionEvent event) -> {
            onDelete();
        });

        navigationManager.setCurrentPageTitle(view.getHeaderTitle());
    }

    @Override
    public void onSearch() {
        final Task<List<E>> task = new Task<List<E>>() {
            @Override
            protected List<E> call() throws Exception {
                if (StringUtils.isEmpty(view.getSearchBox().getText())) {
                    return service.getListWithNamedQuery(getNamedQuery());
                } else {
                    return service.getListWithNamedQuery(
                            getNamedQueryWithFilter(), getFilterParameters());
                }
            }
        };

        task.stateProperty().addListener(
                (ObservableValue<? extends Worker.State> source,
                        Worker.State oldState, Worker.State newState) -> {
                    if (newState.equals(Worker.State.SUCCEEDED)) {
                        view.setData(task.getValue());
                    }
                });

        new Thread(task).start();
    }

    @Override
    public void onAddNew() {
        if (IbbManagement.get().isCreateAllowed(getPlaceName())) {
            view.getListView().getSelectionModel().clearSelection();
            createNewEntity();
            popFields();
            requestFocusAfterAddNew();
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

    @Override
    public void onSave() {
        if (isFormValid()) {
            pushFields();

            try {
                if (isNewModel()) {
                    service.create(entity);
                } else {
                    service.update(entity);
                }
                Notifications.create()
                        .text(I18n.COMMON.getString("notification.saveOk"))
                        .position(Pos.TOP_RIGHT).showInformation();
                onSearch();
            } catch (Exception ex) {
                MessageBox.get()
                        .contentText(I18n.COMMON.getString("notification.saveException"))
                        .showError(ex);
            }
        }
    }

    @Override
    public void onDelete() {
        if (!isNewModel()) {
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

    @Override
    public boolean isFormValid() {
        if (view.getValidationSupport().isInvalid()) {
            Notifications.create()
                    .text(I18n.COMMON.getString("notification.saveValidationError"))
                    .position(Pos.TOP_RIGHT).showWarning();
        }

        return !view.getValidationSupport().isInvalid();
    }

    @Override
    public boolean isNewModel() {
        return (entity.getId() == null);
    }

}
