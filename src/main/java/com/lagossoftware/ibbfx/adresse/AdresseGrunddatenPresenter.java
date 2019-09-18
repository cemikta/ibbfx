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
import com.lagossoftware.ibbfx.entity.*;
import com.lagossoftware.ibbfx.service.AnredeService;
import com.lagossoftware.ibbfx.service.BundeslandService;
import com.lagossoftware.ibbfx.service.LandService;
import com.lagossoftware.ibbfx.service.TitelService;
import com.lagossoftware.lagosfx.mvp.AbstractPresenter;
import com.lagossoftware.lagosfx.navigation.NavigationManager;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.BorderPane;

import java.util.List;

import static com.lagossoftware.lagosfx.service.QueryParameter.with;

/**
 * Adresse Grunddaten presenter
 *
 * @author Cem Ikta
 */
public class AdresseGrunddatenPresenter extends AbstractPresenter<AdresseGrunddatenView> {

    private AnredeService anredeService;
    private TitelService titelService;
    private LandService landService;
    private BundeslandService bundeslandService;
    
    @Inject
    public AdresseGrunddatenPresenter(final NavigationManager navigationManager,
            final AdresseGrunddatenView view, AnredeService anredeService, 
            TitelService titelService, LandService landService, 
            BundeslandService bundeslandService) {
        super(navigationManager, view);
        
        this.anredeService = anredeService;
        this.titelService = titelService;
        this.landService = landService;
        this.bundeslandService = bundeslandService;
    }

    @Override
    public void start(BorderPane container) {
        bind();
    }

    @Override
    public void bind() {
        List<Anrede> anredeList = anredeService.getListWithNamedQuery(Anrede.FIND_ALL);
        view.getCbAnrede().getItems().addAll(anredeList);

        List<Titel> titelList = titelService.getListWithNamedQuery(Titel.FIND_ALL);
        view.getCbTitel().getItems().addAll(titelList);

        ObservableList<Land> landList = FXCollections.observableArrayList(
                landService.getListWithNamedQuery(Land.FIND_ALL));
        view.getCbLand().getItems().addAll(landList);
        view.getCbLand().getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends Land> observable, Land oldValue, 
                    Land newValue) -> {
            onLandChanged(newValue);        
        });
    }
    
    private void onLandChanged(Land selectedLand) {
        ObservableList<Bundesland> list = FXCollections.observableArrayList(
                bundeslandService.getListWithNamedQuery(Bundesland.FIND_BY_LAND, 
                        with("land", selectedLand).parameters()));
        view.getCbBundesland().getSelectionModel().clearSelection();
        view.getCbBundesland().getItems().clear();
        view.getCbBundesland().getItems().addAll(list);
    }
    
    public void popFields(Adresse entity) {
        view.getTfAdresseNr().setText(entity.getAdresseNr() != null ? entity.getAdresseNr().toString() : "");
        view.getCbAnrede().getSelectionModel().select(entity.getAnrede());
        view.getCbTitel().getSelectionModel().select(entity.getTitel());
        view.getTfVorname().setText(entity.getVorname());
        view.getTfNachname().setText(entity.getNachname());
        view.getTfFirma1().setText(entity.getFirma1());
        view.getTfFirma2().setText(entity.getFirma2());
        view.getTfFirma3().setText(entity.getFirma3());
        view.getTfFirma4().setText(entity.getFirma4());
        view.getTfKennzeichen1().setText(entity.getKennzeichen1());
        view.getTfKennzeichen2().setText(entity.getKennzeichen2());
        
        view.getTfPostfachPlz().setText(entity.getPostfachPlz());
        view.getTfPostfach().setText(entity.getPostfach());
        view.getTfStrasse().setText(entity.getStrasse());
        view.getTfPlz().setText(entity.getPlz());
        view.getTfOrt().setText(entity.getOrt());
        view.getCbLand().getSelectionModel().select(entity.getLand());
        view.getCbBundesland().getSelectionModel().select(entity.getBundesland());
        view.getTfAnredeBrief().setText(entity.getAnredeBrief());
    }
    
    public void pushFields(Adresse entity) {
        entity.setAnrede(view.getCbAnrede().getSelectionModel().getSelectedItem());
        entity.setTitel(view.getCbTitel().getSelectionModel().getSelectedItem());
        entity.setVorname(view.getTfVorname().getText());
        entity.setNachname(view.getTfNachname().getText());
        entity.setFirma1(view.getTfFirma1().getText());
        entity.setFirma2(view.getTfFirma2().getText());
        entity.setFirma3(view.getTfFirma3().getText());
        entity.setFirma4(view.getTfFirma4().getText());
        entity.setKennzeichen1(view.getTfKennzeichen1().getText());
        entity.setKennzeichen2(view.getTfKennzeichen2().getText());
     
        entity.setPostfachPlz(view.getTfPostfachPlz().getText());
        entity.setPostfach(view.getTfPostfach().getText());
        entity.setStrasse(view.getTfStrasse().getText());
        entity.setPlz(view.getTfPlz().getText());
        entity.setOrt(view.getTfOrt().getText());
        entity.setLand(view.getCbLand().getSelectionModel().getSelectedItem());
        entity.setBundesland(view.getCbBundesland().getSelectionModel().getSelectedItem());
        entity.setAnredeBrief(view.getTfAnredeBrief().getText());
    }

}
