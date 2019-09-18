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
import com.lagossoftware.ibbfx.entity.Adresse;
import com.lagossoftware.ibbfx.entity.AdresseStichwort;
import com.lagossoftware.ibbfx.entity.Stichwort;
import com.lagossoftware.ibbfx.service.StichwortService;
import com.lagossoftware.lagosfx.mvp.AbstractPresenter;
import com.lagossoftware.lagosfx.navigation.NavigationManager;
import javafx.event.ActionEvent;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;

import java.util.List;
import java.util.Objects;

/**
 * Adresse Stichwort presenter
 *
 * @author Cem Ikta
 */
public class AdresseStichwortPresenter extends AbstractPresenter<AdresseStichwortView> {

    private Adresse adresse;
    private StichwortService stichwortService;
    
    @Inject
    public AdresseStichwortPresenter(final NavigationManager navigationManager, 
            StichwortService stichwortService, final AdresseStichwortView view) {
        super(navigationManager, view);
    
        this.stichwortService = stichwortService;
    }

    @Override
    public void start(BorderPane container) {
        bind();
    }

    @Override
    public void bind() {
        List<Stichwort> allStichwortList = 
                stichwortService.getListWithNamedQuery(Stichwort.FIND_ALL);
        view.getCbStichwort().getItems().addAll(allStichwortList);
        
        view.getBtnAddNewStichwort().setOnAction((ActionEvent event) -> {
            onAddNewStichwort();
        });

        view.getStichwortListView().setCellFactory(
                list -> new StichwortListCell((AdresseStichwort adresseStichwort) -> {
                    adresse.getAdresseStichwortList().remove(adresseStichwort);
                    view.getStichwortListView().getItems().remove(adresseStichwort);
                })
        );                
    }

    public void popFields(Adresse entity) {
        this.adresse = entity;
        view.getStichwortListView().getItems().clear();
        
        if (entity.getId() != null) {
            List<AdresseStichwort> list = entity.getAdresseStichwortList();
            if (list != null) {
                view.getStichwortListView().getItems().addAll(list);
            }
            view.getStichwortListView().getSelectionModel().selectFirst();
        }
    }
    
    public void pushFields(Adresse entity) {
        // nothing
    }

    private void onAddNewStichwort() {
        if (!isStichwortInListView()) {
            AdresseStichwort adresseStichwort = new AdresseStichwort();
            adresseStichwort.setAdresse(adresse);
            adresseStichwort.setStichwort(view.getCbStichwort().getSelectionModel().getSelectedItem());
            adresse.getAdresseStichwortList().add(adresseStichwort);
            view.getStichwortListView().getItems().add(adresseStichwort);
        }
    }
    
    private boolean isStichwortInListView() {
        for (AdresseStichwort adresseStichwort : view.getStichwortListView().getItems()) {
            if (Objects.equals(adresseStichwort.getStichwort().getId(), 
                    view.getCbStichwort().getSelectionModel().getSelectedItem().getId())) {
                return true;
            }
        }
        
        return false;
    }

}
