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
import com.lagossoftware.ibbfx.adresse.StichwortListCell;
import com.lagossoftware.ibbfx.app.I18n;
import com.lagossoftware.ibbfx.entity.*;
import com.lagossoftware.ibbfx.service.AdresseService;
import com.lagossoftware.ibbfx.service.KontoService;
import com.lagossoftware.ibbfx.service.PlanungService;
import com.lagossoftware.ibbfx.service.WaehrungService;
import com.lagossoftware.lagosfx.common.DateHelpers;
import com.lagossoftware.lagosfx.mvp.AbstractPresenter;
import com.lagossoftware.lagosfx.navigation.NavigationManager;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * Fahrtkasse Abrechnen presenter
 *
 * @author Cem Ikta
 */
public class FahrtkasseAbrechnenPresenter extends AbstractPresenter<FahrtkasseAbrechnenView> {

    private Fahrtkasse fahrtkasse;
    private WaehrungService waehrungService;
    private KontoService kontoService;

    private FahrtkasseWaehrungDialog fahrtkasseWaehrungDialog;
    private FahrtkasseDetailDialog fahrtkasseDetailDialog;

    @Inject
    public FahrtkasseAbrechnenPresenter(final NavigationManager navigationManager,
                                        final FahrtkasseAbrechnenView view,
                                        WaehrungService waehrungService,
                                        KontoService kontoService) {
        super(navigationManager, view);

        this.waehrungService = waehrungService;
        this.kontoService = kontoService;
    }

    @Override
    public void start(BorderPane container) {
        bind();
    }

    @Override
    public void bind() {
        view.getBtnAddNewKurs().setOnAction((ActionEvent event) -> {
            onAddNewKurs();
        });

        view.getBtnEditKurs().setOnAction((ActionEvent event) -> {
            onEditKurs();
        });

        view.getWaehrungListView().setCellFactory(
                list -> new FahrtkasseWaehrungListCell((FahrtkasseWaehrung fahrtkasseWaehrung) -> {
                    fahrtkasse.getFahrtkasseWaehrungList().remove(fahrtkasseWaehrung);
                    view.getWaehrungListView().getItems().remove(fahrtkasseWaehrung);
                })
        );

        view.getWaehrungListView().setOnMouseClicked((MouseEvent event) -> {
            if (event.getClickCount() > 1) {
                onEditKurs();
            }
        });

        // fahrtkassedetails
        view.getBtnAddNewDetail().setOnAction((ActionEvent event) -> {
            onAddNewDetail();
        });

        view.getBtnEditDetail().setOnAction((ActionEvent event) -> {
            onEditDetail();
        });

        view.getDetailListView().setCellFactory(
                list -> new FahrtkasseDetailListCell((FahrtkasseDetail fahrtkasseDetail) -> {
                    fahrtkasse.getFahrtkasseDetailList().remove(fahrtkasseDetail);
                    view.getDetailListView().getItems().remove(fahrtkasseDetail);
                    popSummary();
                })
        );

        view.getDetailListView().setOnMouseClicked((MouseEvent event) -> {
            if (event.getClickCount() > 1) {
                onEditDetail();
            }
        });
    }

    private void onAddNewKurs() {
        fahrtkasseWaehrungDialog = new FahrtkasseWaehrungDialog(new FahrtkasseWaehrung(), waehrungService);
        fahrtkasseWaehrungDialog.pop();
        Optional<FahrtkasseWaehrung> result = fahrtkasseWaehrungDialog.showAndWait();

        result.ifPresent(fahrtkasseWaehrung -> {
            fahrtkasseWaehrung.setFahrtkasse(fahrtkasse);
            fahrtkasse.getFahrtkasseWaehrungList().add(fahrtkasseWaehrung);
            view.getWaehrungListView().getItems().add(fahrtkasseWaehrung);
            popSummary();
        });
    }

    private void onEditKurs() {
        if (view.getWaehrungListView().getSelectionModel().getSelectedItem() != null) {
            fahrtkasseWaehrungDialog = new FahrtkasseWaehrungDialog(
                    view.getWaehrungListView().getSelectionModel().getSelectedItem(), waehrungService);
            fahrtkasseWaehrungDialog.pop();
            Optional<FahrtkasseWaehrung> result = fahrtkasseWaehrungDialog.showAndWait();

            result.ifPresent(fahrtkasseWaehrung -> {
                view.getWaehrungListView().getItems().set(
                        view.getWaehrungListView().getSelectionModel().getSelectedIndex(), fahrtkasseWaehrung);
                popSummary();
            });
        }
    }

    private void onAddNewDetail() {
        fahrtkasseDetailDialog = new FahrtkasseDetailDialog(new FahrtkasseDetail(),
                fahrtkasse.getFahrtkasseWaehrungList(), waehrungService, kontoService);
        fahrtkasseDetailDialog.pop();
        Optional<FahrtkasseDetail> result = fahrtkasseDetailDialog.showAndWait();

        result.ifPresent(fahrtkasseDetail -> {
            fahrtkasseDetail.setFahrtkasse(fahrtkasse);
            fahrtkasse.getFahrtkasseDetailList().add(fahrtkasseDetail);
            view.getDetailListView().getItems().add(fahrtkasseDetail);
            popSummary();
        });
    }

    private void onEditDetail() {
        if (view.getDetailListView().getSelectionModel().getSelectedItem() != null) {
            fahrtkasseDetailDialog = new FahrtkasseDetailDialog(
                    view.getDetailListView().getSelectionModel().getSelectedItem(),
                    fahrtkasse.getFahrtkasseWaehrungList(), waehrungService, kontoService);
            fahrtkasseDetailDialog.pop();
            Optional<FahrtkasseDetail> result = fahrtkasseDetailDialog.showAndWait();

            result.ifPresent(fahrtkasseDetail -> {
                view.getDetailListView().getItems().set(
                        view.getDetailListView().getSelectionModel().getSelectedIndex(), fahrtkasseDetail);
                popSummary();
            });
        }
    }

    public void popSummary() {
        BigDecimal sumAnfangsBetrag = view.getNfAnfangsBetrag().getValue();
        BigDecimal sumAusgabenGesamt = BigDecimal.ZERO;
        BigDecimal sumEinnahmenGesamt = BigDecimal.ZERO;
        int sumAusgabenBelege = 0;
        int sumEinnahmenBelege = 0;

        for(FahrtkasseDetail fahrtkasseDetail : fahrtkasse.getFahrtkasseDetailList()) {
            if (fahrtkasseDetail.getKonto().getKontoType().equals(KontoType.EINNAHMEN)) {
                sumEinnahmenGesamt = sumEinnahmenGesamt.add(fahrtkasseDetail.getBetragInEur());
                sumEinnahmenBelege++;
            } else {
                sumAusgabenGesamt = sumAusgabenGesamt.add(fahrtkasseDetail.getBetragInEur());
                sumAusgabenBelege++;
            }
        }

        BigDecimal sumSaldo = sumAnfangsBetrag.subtract(sumAusgabenGesamt).add(sumEinnahmenGesamt);

        view.getLblAusgabenBelege().setText(I18n.FAHRTKASSE.getString("fahrtkasse.form.belege",
                sumAusgabenBelege));
        view.getLblEinnahmenBelege().setText(I18n.FAHRTKASSE.getString("fahrtkasse.form.belege",
                sumEinnahmenBelege));
        view.getNfAusgabenGesamt().setValue(sumAusgabenGesamt);
        view.getNfEinnahmenGesamt().setValue(sumEinnahmenGesamt);
        view.getNfSaldo().setValue(sumSaldo);
    }

    public void popFields(Fahrtkasse entity) {
        this.fahrtkasse = entity;

        // waehrungen
        view.getWaehrungListView().getItems().clear();

        if (entity.getId() != null) {
            List<FahrtkasseWaehrung> list = entity.getFahrtkasseWaehrungList();
            if (list != null) {
                view.getWaehrungListView().getItems().addAll(list);
            }
            view.getWaehrungListView().getSelectionModel().selectFirst();
        }

        // details
        view.getDetailListView().getItems().clear();

        if (entity.getId() != null) {
            List<FahrtkasseDetail> list = entity.getFahrtkasseDetailList();
            if (list != null) {
                view.getDetailListView().getItems().addAll(list);
            }
            view.getDetailListView().getSelectionModel().selectFirst();
        }

        popSummary();
        view.getDpAbgerechnetAm().setValue(DateHelpers.convertDateToLocalDate(
                entity.getAbgerechnetAm()));
    }
    
    public void pushFields(Fahrtkasse entity) {
        // waehrungen already in the list
        // details already in the list
        popSummary();
        entity.setAusgabenGesamt(view.getNfAusgabenGesamt().getValue());
        entity.setEinnahmenGesamt(view.getNfEinnahmenGesamt().getValue());
        entity.setSaldo(view.getNfSaldo().getValue());
        entity.setAbgerechnetAm(DateHelpers.convertLocalDateToDate(
                view.getDpAbgerechnetAm().getValue()));

        // TODO if not save clicked, entity lists saved automaticly???
    }

}
