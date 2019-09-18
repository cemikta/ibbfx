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
import com.lagossoftware.ibbfx.entity.Adresse;
import com.lagossoftware.ibbfx.entity.Planung;
import com.lagossoftware.ibbfx.service.AdresseService;
import com.lagossoftware.lagosfx.mvp.AbstractPresenter;
import com.lagossoftware.lagosfx.navigation.NavigationManager;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.BorderPane;

import java.math.BigDecimal;
import java.util.List;

/**
 * Planung Namen und Preise presenter
 *
 * @author Cem Ikta
 */
public class PlanungNamenPresenter extends AbstractPresenter<PlanungNamenView> {

    private AdresseService adresseService;

    @Inject
    public PlanungNamenPresenter(final NavigationManager navigationManager,
                                 PlanungNamenView view, AdresseService adresseService) {
        super(navigationManager, view);

        this.adresseService = adresseService;
    }

    @Override
    public void start(BorderPane container) {
        bind();
    }

    @Override
    public void bind() {
        List<Adresse> adresseList = adresseService.getListWithNamedQuery(
                Adresse.FIND_BY_HAUPT_MITARBEITER);
        view.getCbMitarbeiter1().getItems().addAll(adresseList);
        view.getCbMitarbeiter2().getItems().addAll(adresseList);

        List<Adresse> partnerList = adresseService.getListWithNamedQuery(
                Adresse.FIND_BY_PARTNER_MITARBEITER);
        view.getCbPartner().getItems().addAll(partnerList);

        view.getCbPartner().getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends Adresse> observable, Adresse oldValue,
                 Adresse newValue) -> {
            onPartnerChanged(newValue);
        });

        /**
         * nfPreis1 is always nfFestpreis
         */
        view.getNfFestpreis().valueProperty().addListener((observable, oldValue, newValue) -> {
            view.getNfPreis1().setValue(newValue);
        });

        setKurzbeschreibungEbPlanVisible();

        view.getChbWirdDurchgefuehrt().selectedProperty().addListener(
                (ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
                    setKurzbeschreibungEbPlanVisible();
        });
    }

    private void setKurzbeschreibungEbPlanVisible() {
        view.getKurzbeschreibungEbPlanTitle().setVisible(view.getChbWirdDurchgefuehrt().isSelected());
        view.getTfKurzbeschreibungEbPlan().setVisible(view.getChbWirdDurchgefuehrt().isSelected());
    }

    public void popFields(Planung entity) {
        view.getCbMitarbeiter1().getSelectionModel().select(entity.getMitarbeiter1());
        view.getCbMitarbeiter2().getSelectionModel().select(entity.getMitarbeiter2());
        view.getCbPartner().getSelectionModel().select(entity.getPartner());

        view.getChbAnmeldungIbb().setSelected(entity.getAnmeldungIbb() != null ?
                entity.getAnmeldungIbb() : false);
        view.getChbAnmeldungPartner().setSelected(entity.getAnmeldungPartner() != null ?
                entity.getAnmeldungPartner() : false);

        view.getNfFestpreis().setValue(entity.getFestpreis() != null ?
                entity.getFestpreis() : BigDecimal.ZERO);

        view.getTfBedingung1().setText("Festgesetzter Verkaufspreis");
        view.getTfBedingung2().setText("EZ-Zuschlag gesamt");
        view.getTfBedingung3().setText(entity.getBedingung3());
        view.getTfBedingung4().setText(entity.getBedingung4());
        view.getTfBedingung5().setText(entity.getBedingung5());
        view.getTfBedingung6().setText(entity.getBedingung6());
        view.getNfPreis1().setValue(entity.getPreis1() != null ?
                entity.getPreis1() : BigDecimal.ZERO);
        view.getNfPreis2().setValue(entity.getPreis2() != null ?
                entity.getPreis2() : BigDecimal.ZERO);
        view.getNfPreis3().setValue(entity.getPreis3() != null ?
                entity.getPreis3() : BigDecimal.ZERO);
        view.getNfPreis4().setValue(entity.getPreis4() != null ?
                entity.getPreis4() : BigDecimal.ZERO);
        view.getNfPreis5().setValue(entity.getPreis5() != null ?
                entity.getPreis5() : BigDecimal.ZERO);
        view.getNfPreis6().setValue(entity.getPreis6() != null ?
                entity.getPreis6() : BigDecimal.ZERO);

        view.getChbWirdDurchgefuehrt().setSelected(entity.getWirdDurchgefuehrt() != null ?
                entity.getWirdDurchgefuehrt() : false);
        view.getTfKurzbeschreibungEbPlan().setText(entity.getKurzbeschreibungEbPlan());
    }

    public void onPartnerChanged(Adresse newPartner) {
        if (newPartner != null) {
            view.getTfPlz().setText(newPartner.getPlz());
            view.getTfOrt().setText(newPartner.getOrt());
            view.getTfMobiltelefon().setText(newPartner.getMobiltelefon());
            view.getTfTelefonPrivat().setText(newPartner.getTelefonPrivat());
            view.getTfTelefonDienst().setText(newPartner.getTelefonDienst());
            view.getTfEmail().setText(newPartner.getEmail());
        } else {
            view.getTfPlz().setText("");
            view.getTfOrt().setText("");
            view.getTfMobiltelefon().setText("");
            view.getTfTelefonPrivat().setText("");
            view.getTfTelefonDienst().setText("");
            view.getTfEmail().setText("");
        }
    }

    public void pushFields(Planung entity) {
        entity.setMitarbeiter1(view.getCbMitarbeiter1().getSelectionModel().getSelectedItem());
        entity.setMitarbeiter2(view.getCbMitarbeiter2().getSelectionModel().getSelectedItem());
        entity.setPartner(view.getCbPartner().getSelectionModel().getSelectedItem());

        entity.setAnmeldungIbb(view.getChbAnmeldungIbb().isSelected());
        entity.setAnmeldungPartner(view.getChbAnmeldungPartner().isSelected());

        entity.setFestpreis(view.getNfFestpreis().getValue());
        entity.setBedingung1(view.getTfBedingung1().getText());
        entity.setBedingung2(view.getTfBedingung2().getText());
        entity.setBedingung3(view.getTfBedingung3().getText());
        entity.setBedingung4(view.getTfBedingung4().getText());
        entity.setBedingung5(view.getTfBedingung5().getText());
        entity.setBedingung6(view.getTfBedingung6().getText());
        entity.setPreis1(view.getNfPreis1().getValue());
        entity.setPreis2(view.getNfPreis2().getValue());
        entity.setPreis3(view.getNfPreis3().getValue());
        entity.setPreis4(view.getNfPreis4().getValue());
        entity.setPreis5(view.getNfPreis5().getValue());
        entity.setPreis6(view.getNfPreis6().getValue());
        entity.setWirdDurchgefuehrt(view.getChbWirdDurchgefuehrt().isSelected());
        entity.setKurzbeschreibungEbPlan(view.getTfKurzbeschreibungEbPlan().getText());
    }

}
