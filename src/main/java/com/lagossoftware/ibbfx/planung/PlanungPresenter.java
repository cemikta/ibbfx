/*
 * Copyright (C) 2016 IBB Management Project, Cem Ikta
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.lagossoftware.ibbfx.planung;

import com.google.inject.Inject;
import com.lagossoftware.ibbfx.IbbManagement;
import com.lagossoftware.ibbfx.app.AppPlaces;
import com.lagossoftware.ibbfx.app.I18n;
import com.lagossoftware.ibbfx.entity.Planung;
import com.lagossoftware.ibbfx.entity.Zuschuss;
import com.lagossoftware.ibbfx.service.PlanungService;
import com.lagossoftware.lagosfx.control.MessageBox;
import com.lagossoftware.lagosfx.mvp.AbstractBrowserPresenter;
import com.lagossoftware.lagosfx.mvp.PlaceBuilder;
import com.lagossoftware.lagosfx.navigation.NavigationManager;
import com.lagossoftware.lagosfx.service.QueryParameter;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.layout.BorderPane;
import org.apache.commons.beanutils.BeanUtils;

import javax.persistence.Column;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Planung presenter
 *
 * @author Cem Ikta
 */
public class PlanungPresenter extends AbstractBrowserPresenter<Planung, PlanungView, PlanungService> {

    @Inject
    public PlanungPresenter(final NavigationManager navigationManager,
                            final Logger logger, final PlanungView view,
                            PlanungService service) {
        super(navigationManager, logger, view, service);
    }

    @Override
    public void start(BorderPane container) {
        container.setCenter(getView().asNode());
        container.setLeft(null);
        container.setRight(null);
        container.setTop(null);
        container.setBottom(null);
        bind();
        onSearch();
        view.getSearchBox().requestFocus();
    }

    @Override
    public void bind() {
        super.bind();
        
        view.getBtnAddNew().setOnAction((ActionEvent event) -> {
            onAddNew();
        });

        view.getBtnEdit().setOnAction((ActionEvent event) -> {
            onEdit();
        });

        view.getBtnDuplicate().setOnAction((ActionEvent event) -> {
            onDuplicate();
        });

        view.getTableView().getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends Planung> observable, Planung oldValue,
                 Planung newValue) -> {
                this.entity = view.getTableView().getSelectionModel().getSelectedItem();   
            }
        );
    }

    @Override
    public void onAddNew() {
        if (IbbManagement.get().isCreateAllowed(AppPlaces.PLANUNG_FORM)) {
            createNewEntity();
            navigationManager.goTo(new PlaceBuilder(AppPlaces.PLANUNG_FORM)
                    .parameter("selectedEntity", entity).build());
        }

    }

    @Override
    public void onEdit() {
        if (view.getTableView().getSelectionModel().getSelectedItem() != null) {
            this.entity = view.getTableView().getSelectionModel().getSelectedItem();
            if (IbbManagement.get().isEditAllowed(AppPlaces.PLANUNG_FORM)) {
                navigationManager.goTo(new PlaceBuilder(AppPlaces.PLANUNG_FORM)
                        .parameter("selectedEntity", entity).build());
            }
        }
    }

    public void onDuplicate() {
        if (view.getTableView().getSelectionModel().getSelectedItem() != null) {
            Planung old = view.getTableView().getSelectionModel().getSelectedItem();

            try {
                entity = (Planung)BeanUtils.cloneBean(view.getTableView().getSelectionModel().getSelectedItem());
                entity.setId(null);
                entity.setPlanungNr(null);
                entity.setZusatz("");
                entity.setZuschussList(new ArrayList<>());

                if (old.getZuschussList() != null) {
                    for (Zuschuss zuschuss : old.getZuschussList()) {
                        Zuschuss newZuschuss = new Zuschuss();
                        newZuschuss.setId(null);
                        newZuschuss.setPlanung(entity);
                        newZuschuss.setBezeichnung(zuschuss.getBezeichnung());
                        newZuschuss.setProDeTn(zuschuss.getProDeTn());
                        newZuschuss.setProDeTnGesamt(zuschuss.getProDeTnGesamt());
                        newZuschuss.setProAuslTn(zuschuss.getProAuslTn());
                        newZuschuss.setProAuslTnGesamt(zuschuss.getProAuslTnGesamt());
                        newZuschuss.setManuellBerechnet(zuschuss.getManuellBerechnet());
                        newZuschuss.setGesamt(zuschuss.getGesamt());
                        newZuschuss.setBewilligt(zuschuss.getBewilligt());
                        entity.getZuschussList().add(newZuschuss);
                    }
                }

            } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException |
                        InstantiationException e) {
                MessageBox.get()
                        .contentText(I18n.COMMON.getString("notification.duplicateException"))
                        .showError(e);
            }

            if (IbbManagement.get().isCreateAllowed(AppPlaces.PLANUNG_FORM)) {
                navigationManager.goTo(new PlaceBuilder(AppPlaces.PLANUNG_FORM)
                        .parameter("selectedEntity", entity).build());
            }
        }

    }

    @Override
    public AppPlaces getPlaceName() {
        return AppPlaces.PLANUNG;
    }

    @Override
    public String getNamedQuery() {
        return Planung.FIND_ALL;
    }

    @Override
    public String getNamedQueryWithFilter() {
        return Planung.FIND_BY_FIELDS;
    }

    @Override
    public Map<String, Object> getFilterParameters() {
        return QueryParameter.with("search", 
                "%" + view.getSearchBox().getText() + "%").parameters();
    }

}
