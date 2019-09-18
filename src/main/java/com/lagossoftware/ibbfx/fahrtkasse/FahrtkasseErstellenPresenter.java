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
import com.lagossoftware.ibbfx.entity.Adresse;
import com.lagossoftware.ibbfx.entity.Fahrtkasse;
import com.lagossoftware.ibbfx.entity.Planung;
import com.lagossoftware.ibbfx.service.AdresseService;
import com.lagossoftware.ibbfx.service.PlanungService;
import com.lagossoftware.lagosfx.common.DateHelpers;
import com.lagossoftware.lagosfx.mvp.AbstractPresenter;
import com.lagossoftware.lagosfx.navigation.NavigationManager;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.BorderPane;

import java.math.BigDecimal;
import java.util.List;

/**
 * Fahrtkasse Erstellen presenter
 *
 * @author Cem Ikta
 */
public class FahrtkasseErstellenPresenter extends AbstractPresenter<FahrtkasseErstellenView> {

    private AdresseService adresseService;
    private PlanungService planungService;

    @Inject
    public FahrtkasseErstellenPresenter(final NavigationManager navigationManager,
                                        final FahrtkasseErstellenView view,
                                        AdresseService adresseService, PlanungService planungService) {
        super(navigationManager, view);

        this.adresseService = adresseService;
        this.planungService = planungService;
    }

    @Override
    public void start(BorderPane container) {
        bind();
    }

    @Override
    public void bind() {
        List<Adresse> adresseList = adresseService.getListWithNamedQuery(
                Adresse.FIND_BY_HAUPT_AND_FREI_MITARBEITER);
        view.getCbAdresse().getItems().addAll(adresseList);

        view.getCbAdresse().getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends Adresse> observable, Adresse oldValue,
                 Adresse newValue) -> {
            onAdresseChanged(newValue);
        });

        List<Planung> planungList = planungService.getListWithNamedQuery(
                Planung.FIND_ALL);
        view.getCbPlanung().getItems().addAll(planungList);

        view.getCbPlanung().getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends Planung> observable, Planung oldValue,
                 Planung newValue) -> {
            onPlanungChanged(newValue);
        });

    }

    public void popFields(Fahrtkasse entity) {
        view.getTfFahrtkasseNr().setText(entity.getFahrtkasseNr() != null ? entity.getFahrtkasseNr().toString() : "");
        view.getDpBelegdatum().setValue(DateHelpers.convertDateToLocalDate(
                entity.getBelegdatum()));
        view.getCbPlanung().getSelectionModel().select(entity.getPlanung());
        view.getCbAdresse().getSelectionModel().select(entity.getAdresse());
        view.getNfBetrag().setValue(entity.getBetrag() != null ?
                entity.getBetrag() : BigDecimal.ZERO);
        view.getCbZahlungsart().getSelectionModel().select(entity.getZahlungsart());
        view.getDpZahlenAm().setValue(DateHelpers.convertDateToLocalDate(
                entity.getZahlenAm()));
        view.getChbAbgerechnet().setSelected(entity.getAbgerechnet() != null ?
                entity.getAbgerechnet() : false);
    }
    
    public void pushFields(Fahrtkasse entity) {
        entity.setBelegdatum(DateHelpers.convertLocalDateToDate(
                view.getDpBelegdatum().getValue()));
        entity.setPlanung(view.getCbPlanung().getSelectionModel().getSelectedItem());
        entity.setAdresse(view.getCbAdresse().getSelectionModel().getSelectedItem());
        entity.setBetrag(view.getNfBetrag().getValue());
        entity.setZahlungsart(view.getCbZahlungsart().getSelectionModel().getSelectedItem());
        entity.setZahlenAm(DateHelpers.convertLocalDateToDate(
                view.getDpZahlenAm().getValue()));
        entity.setAbgerechnet(view.getChbAbgerechnet().isSelected());
    }

    private void onAdresseChanged(Adresse newAdresse) {
        if (newAdresse != null) {
            view.getTfStrasse().setText(newAdresse.getStrasse());
            view.getTfPlz().setText(newAdresse.getPlz());
            view.getTfOrt().setText(newAdresse.getOrt());

            view.getTfIban().setText(newAdresse.getIban());
            view.getTfBic().setText(newAdresse.getBic());
            view.getTfBank().setText(newAdresse.getBank());
            view.getTfKontoNr().setText(newAdresse.getKontoNr());
            view.getTfBlz().setText(newAdresse.getBlz());

        } else {
            view.getTfStrasse().setText("");
            view.getTfPlz().setText("");
            view.getTfOrt().setText("");

            view.getTfIban().setText("");
            view.getTfBic().setText("");
            view.getTfBank().setText("");
            view.getTfKontoNr().setText("");
            view.getTfBlz().setText("");
        }
    }

    private void onPlanungChanged(Planung newPlanung) {
        if (newPlanung != null) {
            view.getLblPlanungTitle().setText(newPlanung.getTitel());
        } else {
            view.getLblPlanungTitle().setText("");
        }
    }

}
