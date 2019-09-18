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
package com.lagossoftware.ibbfx.eingangsrechnung;

import com.google.inject.Inject;
import com.lagossoftware.ibbfx.IbbManagement;
import com.lagossoftware.ibbfx.app.AppPlaces;
import com.lagossoftware.ibbfx.app.I18n;
import com.lagossoftware.ibbfx.entity.Adresse;
import com.lagossoftware.ibbfx.entity.Eingangsrechnung;
import com.lagossoftware.ibbfx.entity.Geschaeftsbereich;
import com.lagossoftware.ibbfx.entity.Waehrung;
import com.lagossoftware.ibbfx.service.AdresseService;
import com.lagossoftware.ibbfx.service.EingangsrechnungService;
import com.lagossoftware.ibbfx.service.GeschaeftsbereichService;
import com.lagossoftware.ibbfx.service.WaehrungService;
import com.lagossoftware.lagosfx.common.DateHelpers;
import com.lagossoftware.lagosfx.common.ReportHelpers;
import com.lagossoftware.lagosfx.control.MessageBox;
import com.lagossoftware.lagosfx.mvp.AbstractFormPresenter;
import com.lagossoftware.lagosfx.mvp.HasParameters;
import com.lagossoftware.lagosfx.mvp.Place;
import com.lagossoftware.lagosfx.navigation.NavigationManager;
import javafx.event.ActionEvent;
import javafx.scene.layout.BorderPane;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.internal.SessionImpl;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Eingangsrechnung form presenter
 *
 * @author Cem Ikta
 */
public class EingangsrechnungFormPresenter extends AbstractFormPresenter<Eingangsrechnung, 
        EingangsrechnungFormView, EingangsrechnungService> implements HasParameters {
    
    private WaehrungService waehrungService;
    private GeschaeftsbereichService geschaeftsbereichService;
    private AdresseService adresseService;
    private Map<String, Object> parameters;
    
    @Inject
    public EingangsrechnungFormPresenter(final NavigationManager navigationManager, 
            final Logger logger, final EingangsrechnungFormView view, 
            final EingangsrechnungService service, 
            final WaehrungService waehrungService,
            final GeschaeftsbereichService geschaeftsbereichService,
            AdresseService adresseService) {
        super(navigationManager, logger, view, service);
        
        this.waehrungService = waehrungService;
        this.geschaeftsbereichService = geschaeftsbereichService;
        this.adresseService = adresseService;
    }

    @Override
    public void start(BorderPane container) {
        container.setCenter(view.asNode());
        container.setLeft(null);
        container.setRight(null);
        container.setTop(null);
        container.setBottom(null);

        bind();
        entity =  (Eingangsrechnung) parameters.get("selectedEntity");
        popFields();
        setRequestFocus();
    }
    
    @Override
    public void bind() {
        super.bind();

        view.getBtnPrintPreview().setOnAction((ActionEvent event) -> {
            onPrint(true);
        });

        view.getBtnPrint().setOnAction((ActionEvent event) -> {
            onPrint(false);
        });

        List<Waehrung> waehrungList = waehrungService.getListWithNamedQuery(Waehrung.FIND_ALL);
        view.getCbWaehrung().getItems().addAll(waehrungList);
        
        List<Adresse> adresseList = adresseService.getListWithNamedQuery(
                Adresse.FIND_BY_HAUPT_MITARBEITER);
        view.getCbAdresse().getItems().addAll(adresseList);
    }

    @Override
    public void setRequestFocus() {
        view.getDpBelegdatum().requestFocus();
    }
    
    @Override
    public void popFields() {
        view.getTfBelegNr().setText(entity.getBelegNr() != null ? entity.getBelegNr().toString() : "");
        view.getDpBelegdatum().setValue(DateHelpers.convertDateToLocalDate(
                entity.getBelegdatum()));
        view.getTfRechnungsNr().setText(entity.getRechnungsNr());
        view.getDpRechnungsdatum().setValue(DateHelpers.convertDateToLocalDate(
                entity.getRechnungsdatum()));
        view.getTfLieferant().setText(entity.getLieferant());
        view.getTfGegenstand().setText(entity.getGegenstand());
        view.getNfBetrag().setValue(entity.getBetrag() != null ? 
                entity.getBetrag() : BigDecimal.ZERO);
        view.getCbWaehrung().getSelectionModel().select(entity.getWaehrung());
        view.getCbAdresse().getSelectionModel().select(entity.getAdresse());
        view.getCbZahlungsstatus().getSelectionModel().select(entity.getZahlungsstatus());
        view.getTfNotizen().setText(entity.getNotizen());
    }
    
    @Override
    public void pushFields() {
        entity.setBelegdatum(DateHelpers.convertLocalDateToDate(
                view.getDpBelegdatum().getValue()));
        entity.setRechnungsNr(view.getTfRechnungsNr().getText());
        entity.setRechnungsdatum(DateHelpers.convertLocalDateToDate(
                view.getDpRechnungsdatum().getValue()));
        entity.setLieferant(view.getTfLieferant().getText());
        entity.setGegenstand(view.getTfGegenstand().getText());
        entity.setBetrag(view.getNfBetrag().getValue());
        entity.setWaehrung(view.getCbWaehrung().getSelectionModel().getSelectedItem());
        entity.setAdresse(view.getCbAdresse().getSelectionModel().getSelectedItem());
        entity.setZahlungsstatus(view.getCbZahlungsstatus().getSelectionModel().getSelectedItem());
        entity.setNotizen(view.getTfNotizen().getText());

        if (isNewModel()) {
            entity.setCreatedBy(IbbManagement.get().getLoggedUser().getId());
        } else {
            entity.setUpdatedBy(IbbManagement.get().getLoggedUser().getId());
        }
    }

    @Override
    public AppPlaces getPlaceName() {
        return AppPlaces.EINGANGSRECHNUNG_FORM;
    }

    @Override
    public void onBack() {
        navigationManager.goTo(new Place(AppPlaces.EINGANGSRECHNUNG));
    }

    private void onPrint(boolean preview) {
        if (StringUtils.isEmpty(view.getTfBelegNr().getText())) {
            MessageBox.get()
                    .contentText(I18n.EINGANGSRECHNUNG.getString("eingangsrechnung.form.printInfo"))
                    .showWarning();
            return;
        }

        HashMap<String, Object> mapParams = new HashMap<>();
        int i = 0;

        // Hauptberufliche mitarbeiter mit Kostenstelle
        List<Adresse> mitarbeiterList = adresseService.getListWithNamedQuery(
                Adresse.FIND_BY_KOSTENSTELLE);
        for (Adresse adresse : mitarbeiterList) {
            i++;
            if (i < 10) {
                mapParams.put(("mitarbeiterId" + i), adresse.getId().toString());
                mapParams.put(("mitarbeiter" + i), adresse.getNachname());
            }
        }

        // Geshaeftsbereich
        List<Geschaeftsbereich> geschaeftsbereichList = geschaeftsbereichService.getListWithNamedQuery(
                Geschaeftsbereich.FIND_ALL);
        i = 0;
        for (Geschaeftsbereich geschaeftsbereich : geschaeftsbereichList) {
            i++;
            if (i < 10) {
                mapParams.put(("stelleId" + i), geschaeftsbereich.getId().toString());
                mapParams.put(("stelle" + i), geschaeftsbereich.getName());
            }
        }

        // report query
        String  query = "SELECT e.beleg_nr, e.belegdatum, e.rechnungs_nr, e.rechnungsdatum, " +
                "e.lieferant, e.gegenstand, e.betrag, e.adresse_id, a.vorname, a.nachname " +
                "FROM eingangsrechnung e " +
                "LEFT JOIN adresse a " +
                "ON e.adresse_id = a.adresse_id " +
                "WHERE e.beleg_nr = '" +
                Long.valueOf(view.getTfBelegNr().getText()) + "'";

        EntityManager entityManager =  service.getEntityManager();
        entityManager.getTransaction().begin();
        Connection connection = entityManager.unwrap(SessionImpl.class).connection();
        ReportHelpers.report("rptKontierungsbogen.jasper",
                "Kontierungsbogen", query, preview, mapParams, connection);
        entityManager.getTransaction().commit();
    }

    @Override
    public Map<String, Object> getParameters() {
        return parameters;
    }

    @Override
    public void setParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
    }

}
