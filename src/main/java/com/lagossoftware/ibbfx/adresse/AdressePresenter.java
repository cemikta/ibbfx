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
package com.lagossoftware.ibbfx.adresse;

import com.google.inject.Inject;
import com.lagossoftware.ibbfx.IbbManagement;
import com.lagossoftware.ibbfx.app.AppPlaces;
import com.lagossoftware.ibbfx.entity.Adresse;
import com.lagossoftware.ibbfx.service.AdresseService;
import com.lagossoftware.lagosfx.mvp.AbstractBrowserPresenter;
import com.lagossoftware.lagosfx.mvp.Place;
import com.lagossoftware.lagosfx.mvp.PlaceBuilder;
import com.lagossoftware.lagosfx.navigation.NavigationManager;
import com.lagossoftware.lagosfx.service.QueryParameter;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.layout.BorderPane;

import java.util.Map;
import java.util.logging.Logger;

/**
 * Adresse presenter
 *
 * @author Cem Ikta
 */
public class AdressePresenter extends AbstractBrowserPresenter<Adresse, AdresseView, AdresseService> {
    
    private AdressePreviewPresenter adressePreviewPresenter;
    
    @Inject
    public AdressePresenter(final NavigationManager navigationManager, 
            final Logger logger, final AdresseView view, final AdresseService service, 
            AdressePreviewPresenter adressePreviewPresenter) {
        super(navigationManager, logger, view, service);
        
        this.adressePreviewPresenter = adressePreviewPresenter;
    }

    @Override
    public void start(BorderPane container) {
        container.setCenter(getView().asNode());
        container.setLeft(null);
        container.setRight(null);
        container.setTop(null);
        container.setBottom(adressePreviewPresenter.getView().asNode());
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
                (ObservableValue<? extends Adresse> observable, Adresse oldValue, 
                        Adresse newValue) -> {
                this.entity = view.getTableView().getSelectionModel().getSelectedItem();   
                adressePreviewPresenter.onModelChanged(entity);
            }
        );
    }

    @Override
    public void onAddNew() {
        if (IbbManagement.get().isCreateAllowed(AppPlaces.ADDRESSE_FORM)) {
            createNewEntity();
            navigationManager.goTo(new PlaceBuilder(AppPlaces.ADDRESSE_FORM)
                    .parameter("selectedEntity", entity).build());
        }
    }

    @Override
    public void onEdit() {
        if (view.getTableView().getSelectionModel().getSelectedItem() != null) {
            this.entity = view.getTableView().getSelectionModel().getSelectedItem();
            if (IbbManagement.get().isEditAllowed(AppPlaces.ADDRESSE_FORM)) {
                navigationManager.goTo(new PlaceBuilder(AppPlaces.ADDRESSE_FORM)
                        .parameter("selectedEntity", entity).build());
            }
        }
    }

    @Override
    public AppPlaces getPlaceName() {
        return AppPlaces.ADDRESSE;
    }

    @Override
    public String getNamedQuery() {
        return Adresse.FIND_ALL;
    }

    @Override
    public String getNamedQueryWithFilter() {
        return Adresse.FIND_BY_FIELDS;
    }

    @Override
    public Map<String, Object> getFilterParameters() {
        return QueryParameter.with("search", 
                "%" + view.getSearchBox().getText() + "%").parameters();
    }

}
