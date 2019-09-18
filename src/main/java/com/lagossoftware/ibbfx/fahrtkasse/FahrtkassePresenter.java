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
package com.lagossoftware.ibbfx.fahrtkasse;

import com.google.inject.Inject;
import com.lagossoftware.ibbfx.IbbManagement;
import com.lagossoftware.ibbfx.app.AppPlaces;
import com.lagossoftware.ibbfx.entity.Fahrtkasse;
import com.lagossoftware.ibbfx.service.FahrtkasseService;
import com.lagossoftware.lagosfx.mvp.AbstractBrowserPresenter;
import com.lagossoftware.lagosfx.mvp.PlaceBuilder;
import com.lagossoftware.lagosfx.navigation.NavigationManager;
import com.lagossoftware.lagosfx.service.QueryParameter;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.layout.BorderPane;

import java.util.Map;
import java.util.logging.Logger;

/**
 * Fahrtkasse presenter
 *
 * @author Cem Ikta
 */
public class FahrtkassePresenter extends AbstractBrowserPresenter<Fahrtkasse,
        FahrtkasseView, FahrtkasseService> {

    @Inject
    public FahrtkassePresenter(final NavigationManager navigationManager,
                                     final Logger logger, FahrtkasseView view,
                                     FahrtkasseService service) {
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

        view.getTableView().getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends Fahrtkasse> observable, Fahrtkasse oldValue,
                 Fahrtkasse newValue) -> {
                    this.entity = view.getTableView().getSelectionModel().getSelectedItem();
                }
        );
    }

    @Override
    public void onAddNew() {
        if (IbbManagement.get().isCreateAllowed(AppPlaces.FAHRTKASSE_FORM)) {
            createNewEntity();
            navigationManager.goTo(new PlaceBuilder(AppPlaces.FAHRTKASSE_FORM)
                    .parameter("selectedEntity", entity).build());
        }
    }

    @Override
    public void onEdit() {
        if (view.getTableView().getSelectionModel().getSelectedItem() != null) {
            this.entity = view.getTableView().getSelectionModel().getSelectedItem();
            if (IbbManagement.get().isEditAllowed(AppPlaces.FAHRTKASSE_FORM)) {
                navigationManager.goTo(new PlaceBuilder(AppPlaces.FAHRTKASSE_FORM)
                        .parameter("selectedEntity", entity).build());
            }
        }
    }

    @Override
    public AppPlaces getPlaceName() {
        return AppPlaces.FAHRTKASSE;
    }

    @Override
    public String getNamedQuery() {
        return Fahrtkasse.FIND_ALL;
    }

    @Override
    public String getNamedQueryWithFilter() {
        return Fahrtkasse.FIND_BY_FIELDS;
    }

    @Override
    public Map<String, Object> getFilterParameters() {
        return QueryParameter.with("search",
                "%" + view.getSearchBox().getText() + "%").parameters();
    }

}
