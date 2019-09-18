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
import com.lagossoftware.ibbfx.entity.*;
import com.lagossoftware.ibbfx.service.ArbeitsaufwandService;
import com.lagossoftware.ibbfx.service.FachbereichService;
import com.lagossoftware.ibbfx.service.GeschaeftsbereichService;
import com.lagossoftware.ibbfx.service.ZgfaktorService;
import com.lagossoftware.lagosfx.common.DateHelpers;
import com.lagossoftware.lagosfx.mvp.AbstractPresenter;
import com.lagossoftware.lagosfx.navigation.NavigationManager;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.BorderPane;

import java.math.BigDecimal;
import java.util.List;

/**
 * Planung Rahmendaten presenter
 *
 * @author Cem Ikta
 */
public class PlanungRahmendatenPresenter extends AbstractPresenter<PlanungRahmendatenView> {

    private FachbereichService fachbereichService;
    private GeschaeftsbereichService geschaeftsbereichService;
    private ArbeitsaufwandService arbeitsaufwandService;
    private ZgfaktorService zgfaktorService;

    @Inject
    public PlanungRahmendatenPresenter(final NavigationManager navigationManager,
                                       final PlanungRahmendatenView view, FachbereichService fachbereichService,
                                       GeschaeftsbereichService geschaeftsbereichService,
                                       ArbeitsaufwandService arbeitsaufwandService, ZgfaktorService zgfaktorService) {
        super(navigationManager, view);

        this.fachbereichService = fachbereichService;
        this.geschaeftsbereichService = geschaeftsbereichService;
        this.arbeitsaufwandService = arbeitsaufwandService;
        this.zgfaktorService = zgfaktorService;
    }

    @Override
    public void start(BorderPane container) {
        bind();
    }

    @Override
    public void bind() {
        List<Fachbereich> fachbereichList = fachbereichService.getListWithNamedQuery(Fachbereich.FIND_ALL);
        view.getCbFachbereich().getItems().addAll(fachbereichList);

        List<Geschaeftsbereich> geschaeftsbereichList =
                geschaeftsbereichService.getListWithNamedQuery(Geschaeftsbereich.FIND_ALL);
        view.getCbGeschaeftsbereich().getItems().addAll(geschaeftsbereichList);

        List<Arbeitsaufwand> arbeitsaufwandList = arbeitsaufwandService.getListWithNamedQuery(Arbeitsaufwand.FIND_ALL);
        view.getCbArbeitsaufwand().getItems().addAll(arbeitsaufwandList);
        view.getCbArbeitsaufwand().getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends Arbeitsaufwand> observable, Arbeitsaufwand oldValue,
                 Arbeitsaufwand newValue) -> {
                    onArbeitsaufwandChanged(newValue);
                });

        List<Zgfaktor> zgfaktorList = zgfaktorService.getListWithNamedQuery(Zgfaktor.FIND_ALL);
        view.getCbZgfaktor().getItems().addAll(zgfaktorList);
        view.getCbZgfaktor().getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends Zgfaktor> observable, Zgfaktor oldValue,
                 Zgfaktor newValue) -> {
                    onZgfaktorChanged(newValue);
                });
    }

    public void popFields(Planung entity) {
        view.getNfPlanungNr().setValue(entity.getPlanungNr() != null ?
                entity.getPlanungNr().intValue() : 0);
        view.getTfZusatz().setText(entity.getZusatz());
        view.getTfTitel().setText(entity.getTitel());
        view.getTfVeranstaltungOrt().setText(entity.getVeranstaltungOrt());
        view.getDpVeranstaltungBeginn().setValue(DateHelpers.convertDateToLocalDate(
                entity.getVeranstaltungBeginn()));
        view.getDpVeranstaltungEnde().setValue(DateHelpers.convertDateToLocalDate(
                entity.getVeranstaltungEnde()));
        view.getTfVorbereitungstreffenOrt().setText(entity.getVorbereitungstreffenOrt());
        view.getDpVorbereitungstreffenBeginn().setValue(DateHelpers.convertDateToLocalDate(
                entity.getVorbereitungstreffenBeginn()));
        view.getDpVorbereitungstreffenEnde().setValue(DateHelpers.convertDateToLocalDate(
                entity.getVorbereitungstreffenEnde()));
        view.getTfNachbereitungstreffenOrt().setText(entity.getNachbereitungstreffenOrt());
        view.getDpNachbereitungstreffenBeginn().setValue(DateHelpers.convertDateToLocalDate(
                entity.getNachbereitungstreffenBeginn()));
        view.getDpNachbereitungstreffenEnde().setValue(DateHelpers.convertDateToLocalDate(
                entity.getNachbereitungstreffenEnde()));

        view.getCbFachbereich().getSelectionModel().select(entity.getFachbereich());
        view.getCbGeschaeftsbereich().getSelectionModel().select(entity.getGeschaeftsbereich());
        view.getCbArbeitsaufwand().getSelectionModel().select(entity.getArbeitsaufwand());
        view.getNfArbeitsaufwandAufschlag().setValue(entity.getArbeitsaufwandAufschlag() != null ?
                entity.getArbeitsaufwandAufschlag() : BigDecimal.ZERO);
        view.getNfTageNachWbg().setValue(entity.getTageNachWbg() != null ?
                entity.getTageNachWbg() : 0);
        view.getCbZgfaktor().getSelectionModel().select(entity.getZgfaktor());
        view.getNfZgfaktorAufschlag().setValue(entity.getZgfaktorAufschlag() != null ?
                entity.getZgfaktorAufschlag() : BigDecimal.ZERO);
    }

    private void onArbeitsaufwandChanged(Arbeitsaufwand newArbeitsaufwand) {
        if (newArbeitsaufwand != null) {
            view.getNfArbeitsaufwandAufschlag().setValue(newArbeitsaufwand.getAufschlag() != null ?
                    newArbeitsaufwand.getAufschlag() : BigDecimal.ZERO);
        } else {
            view.getNfArbeitsaufwandAufschlag().setValue(BigDecimal.ZERO);
        }
    }

    private void onZgfaktorChanged(Zgfaktor newZgfaktor) {
        if (newZgfaktor != null) {
            view.getNfZgfaktorAufschlag().setValue(newZgfaktor.getAufschlag() != null ?
                    newZgfaktor.getAufschlag() : BigDecimal.ZERO);
        } else {
            view.getNfZgfaktorAufschlag().setValue(BigDecimal.ZERO);
        }
    }

    public void pushFields(Planung entity) {
        entity.setPlanungNr(view.getNfPlanungNr().getValue().longValue());
        entity.setZusatz(view.getTfZusatz().getText());
        entity.setTitel(view.getTfTitel().getText());
        entity.setVeranstaltungOrt(view.getTfVeranstaltungOrt().getText());
        entity.setVeranstaltungBeginn(DateHelpers.convertLocalDateToDate(
                view.getDpVeranstaltungBeginn().getValue()));
        entity.setVeranstaltungEnde(DateHelpers.convertLocalDateToDate(
                view.getDpVeranstaltungEnde().getValue()));
        entity.setVorbereitungstreffenOrt(view.getTfVorbereitungstreffenOrt().getText());
        entity.setVorbereitungstreffenBeginn(DateHelpers.convertLocalDateToDate(
                view.getDpVorbereitungstreffenBeginn().getValue()));
        entity.setVorbereitungstreffenEnde(DateHelpers.convertLocalDateToDate(
                view.getDpVorbereitungstreffenEnde().getValue()));
        entity.setNachbereitungstreffenOrt(view.getTfNachbereitungstreffenOrt().getText());
        entity.setNachbereitungstreffenBeginn(DateHelpers.convertLocalDateToDate(
                view.getDpNachbereitungstreffenBeginn().getValue()));
        entity.setNachbereitungstreffenEnde(DateHelpers.convertLocalDateToDate(
                view.getDpNachbereitungstreffenEnde().getValue()));

        entity.setFachbereich(view.getCbFachbereich().getSelectionModel().getSelectedItem());
        entity.setGeschaeftsbereich(view.getCbGeschaeftsbereich().getSelectionModel().getSelectedItem());
        entity.setArbeitsaufwand(view.getCbArbeitsaufwand().getSelectionModel().getSelectedItem());
        entity.setArbeitsaufwandAufschlag(view.getNfArbeitsaufwandAufschlag().getValue());
        entity.setTageNachWbg(view.getNfTageNachWbg().getValue());
        entity.setZgfaktor(view.getCbZgfaktor().getSelectionModel().getSelectedItem());
        entity.setZgfaktorAufschlag(view.getNfZgfaktorAufschlag().getValue());

        /**
         * Compute FahrtkasseKostenstelle, it used in Fahrtkasse Kontierung report
         * geschaeftsbereichId + last 3 digit of planungNr.
         */
        String strFahrtkasseKostenstelle = entity.getGeschaeftsbereich().getId().toString();
        strFahrtkasseKostenstelle += entity.getPlanungNr().toString().substring(2);
        entity.setFahrtkasseKostenstelle(Integer.valueOf(strFahrtkasseKostenstelle));
    }

}
