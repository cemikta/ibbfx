/*
 * LagosFX application framework
 *
 * Copyright(c) 2014 - 2016, Cem Ikta
 */
package com.lagossoftware.lagosfx.mvp;

import com.lagossoftware.ibbfx.IbbManagement;
import com.lagossoftware.ibbfx.app.AppPlaces;
import com.lagossoftware.ibbfx.app.I18n;
import com.lagossoftware.ibbfx.entity.BaseEntity;
import com.lagossoftware.lagosfx.control.MessageBox;
import com.lagossoftware.lagosfx.navigation.NavigationManager;
import com.lagossoftware.lagosfx.service.AbstractService;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.ButtonType;
import org.controlsfx.control.Notifications;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Optional;
import java.util.logging.Logger;

/**
 * Abstract form presenter
 *
 * @param <E> base entity
 * @param <V> view
 * @param <S> service
 *
 * @author Cem Ikta
 */
public abstract class AbstractFormPresenter<E extends BaseEntity, 
        V extends FormView<E>, S extends AbstractService<E>>
            implements FormPresenter<E, V, S> {

    protected NavigationManager navigationManager;
    protected final Logger logger;

    protected E entity;
    private Class<E> entityClass;
    protected final V view;
    protected S service;

    public AbstractFormPresenter(final NavigationManager navigationManager,
            final Logger logger, final V view, S service) {
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
        view.getBtnBack().setOnAction((ActionEvent event) -> {
            onBack();
        });

        view.getBtnSave().setOnAction((ActionEvent event) -> {
            onSave();
        });

        view.getBtnAddNew().setOnAction((ActionEvent event) -> {
            onAddNew();
        });

        view.getBtnDelete().setOnAction((ActionEvent event) -> {
            onDelete();
        });

        navigationManager.setCurrentPageTitle(view.getHeaderTitle());
    }
    
    @Override
    public void onSave() {
        if (isFormValid()) {
            pushFields();

            try {
                if (isNewModel()) {
                    entity = service.create(entity);
                    popFields();
                } else {
                    entity = service.update(entity);
                }
                Notifications.create()
                        .text(I18n.COMMON.getString("notification.saveOk"))
                        .position(Pos.TOP_RIGHT).showInformation();
            } catch (Exception ex) {
                MessageBox.get()
                        .contentText(I18n.COMMON.getString("notification.saveException"))
                        .showError(ex);
            }
        }
    }

    @Override
    public void onAddNew() {
        if (IbbManagement.get().isCreateAllowed(getPlaceName())) {
            createNewEntity();
            popFields();
            setRequestFocus();
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
    public void onDelete() {
        if (!isNewModel()) {
            if (IbbManagement.get().isDeleteAllowed(getPlaceName())) {
                Optional<ButtonType> result = MessageBox.get()
                        .contentText(I18n.COMMON.getString("dialog.ask.delete"))
                        .showConfirmation();
                if (result.get() == ButtonType.YES){
                    try {
                        service.remove(entity);
                        Notifications.create()
                                .text(I18n.COMMON.getString("notification.deleteOk"))
                                .position(Pos.TOP_RIGHT).showInformation();
                        navigationManager.goTo(new Place(AppPlaces.ADDRESSE));
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
