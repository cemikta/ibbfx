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
import com.lagossoftware.ibbfx.app.I18n;
import com.lagossoftware.ibbfx.entity.Fahrtkasse;
import com.lagossoftware.ibbfx.entity.FahrtkasseWaehrung;
import com.lagossoftware.ibbfx.entity.Waehrung;
import com.lagossoftware.ibbfx.service.FahrtkasseService;
import com.lagossoftware.ibbfx.service.FahrtkasseWaehrungService;
import com.lagossoftware.lagosfx.common.ReportHelpers;
import com.lagossoftware.lagosfx.control.MessageBox;
import com.lagossoftware.lagosfx.mvp.AbstractMultiPageFormPresenter;
import com.lagossoftware.lagosfx.mvp.HasParameters;
import com.lagossoftware.lagosfx.mvp.Place;
import com.lagossoftware.lagosfx.navigation.NavigationManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import org.apache.commons.lang3.StringUtils;
import org.controlsfx.control.Notifications;
import org.hibernate.internal.SessionImpl;

import javax.persistence.EntityManager;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Fahrtkasse form presenter
 *
 * @author Cem Ikta
 */
public class FahrtkasseFormPresenter extends AbstractMultiPageFormPresenter<Fahrtkasse,
        FahrtkasseFormView, FahrtkasseService> implements HasParameters {

    private FahrtkasseWaehrungService fahrtkasseWaehrungService;
    private FahrtkasseErstellenPresenter fahrtkasseErstellenPresenter;
    private FahrtkasseAbrechnenPresenter fahrtkasseAbrechnenPresenter;
    private FahrtkasseNotizenPresenter fahrtkasseNotizenPresenter;
    private Map<String, Object> parameters;

    @Inject
    public FahrtkasseFormPresenter(final NavigationManager navigationManager,
                                   final Logger logger, final FahrtkasseFormView view,
                                   FahrtkasseService service,
                                   FahrtkasseWaehrungService fahrtkasseWaehrungService,
                                   FahrtkasseErstellenPresenter fahrtkasseErstellenPresenter,
                                   FahrtkasseAbrechnenPresenter fahrtkasseAbrechnenPresenter,
                                   FahrtkasseNotizenPresenter fahrtkasseNotizenPresenter) {
        super(navigationManager, logger, view, service);

        this.fahrtkasseWaehrungService = fahrtkasseWaehrungService;
        this.fahrtkasseErstellenPresenter = fahrtkasseErstellenPresenter;
        this.fahrtkasseAbrechnenPresenter = fahrtkasseAbrechnenPresenter;
        this.fahrtkasseNotizenPresenter = fahrtkasseNotizenPresenter;
    }

    @Override
    public void start(BorderPane container) {
        container.setCenter(view.asNode());
        container.setLeft(null);
        container.setRight(null);
        container.setTop(null);
        container.setBottom(null);
        bind();

        fahrtkasseErstellenPresenter.start(container);
        fahrtkasseAbrechnenPresenter.start(container);
        fahrtkasseNotizenPresenter.start(container);

        view.getButtonBarPane().addPage(fahrtkasseErstellenPresenter.getView().getIconPath(),
                fahrtkasseErstellenPresenter.getView().getTitle(),
                fahrtkasseErstellenPresenter.getView().asNode());

        view.getButtonBarPane().addPage(fahrtkasseAbrechnenPresenter.getView().getIconPath(),
                fahrtkasseAbrechnenPresenter.getView().getTitle(),
                fahrtkasseAbrechnenPresenter.getView().asNode());

        view.getButtonBarPane().addPage(fahrtkasseNotizenPresenter.getView().getIconPath(),
                fahrtkasseNotizenPresenter.getView().getTitle(),
                fahrtkasseNotizenPresenter.getView().asNode());

        entity =  (Fahrtkasse) parameters.get("selectedEntity");
        popFields();
        setRequestFocus();
    }

    @Override
    public void bind() {
        super.bind();

        EventHandler<ActionEvent> printPreviewAction = event -> {
            if (event.getSource() == view.getMnuFahrtkasse()) {
                printFahrtkasse(true);
            } else if (event.getSource() == view.getMnuBelegaufstellung()) {
                printBelegaufstellung(true);
            } else if (event.getSource() == view.getMnuKontierung()) {
                printKontierung(true);
            } else if (event.getSource() == view.getMnuBrief()) {
                printBrief(true);
            }
        };

        EventHandler<ActionEvent> printAction = event -> {
            if (event.getSource() == view.getMnuFahrtkassePrint()) {
                printFahrtkasse(false);
            } else if (event.getSource() == view.getMnuBelegaufstellungPrint()) {
                printBelegaufstellung(false);
            } else if (event.getSource() == view.getMnuKontierungPrint()) {
                printKontierung(false);
            } else if (event.getSource() == view.getMnuBriefPrint()) {
                printBrief(false);
            }
        };

        view.getMnuFahrtkasse().setOnAction(printPreviewAction);
        view.getMnuBelegaufstellung().setOnAction(printPreviewAction);
        view.getMnuKontierung().setOnAction(printPreviewAction);
        view.getMnuBrief().setOnAction(printPreviewAction);

        view.getMnuFahrtkassePrint().setOnAction(printAction);
        view.getMnuBelegaufstellungPrint().setOnAction(printAction);
        view.getMnuKontierungPrint().setOnAction(printAction);
        view.getMnuBriefPrint().setOnAction(printAction);

        fahrtkasseErstellenPresenter.getView().getNfBetrag().valueProperty()
                .addListener((observable, oldValue, newValue) -> {
            fahrtkasseAbrechnenPresenter.getView().getNfAnfangsBetrag().setValue(newValue);
        });
    }

    @Override
    public void setRequestFocus() {
        fahrtkasseErstellenPresenter.getView().getDpBelegdatum().requestFocus();
    }
    
    @Override
    public void popFields() {
        fahrtkasseErstellenPresenter.popFields(entity);
        fahrtkasseAbrechnenPresenter.popFields(entity);
        fahrtkasseNotizenPresenter.popFields(entity);
    }
    
    @Override
    public void pushFields() {
        fahrtkasseErstellenPresenter.pushFields(entity);
        fahrtkasseAbrechnenPresenter.pushFields(entity);
        fahrtkasseNotizenPresenter.pushFields(entity);

        if (isNewModel()) {
            entity.setCreatedBy(IbbManagement.get().getLoggedUser().getId());
        } else {
            entity.setUpdatedBy(IbbManagement.get().getLoggedUser().getId());
        }
    }

    private void printFahrtkasse(boolean preview) {
        if (isFahrtkasseSaved()) {
            HashMap<String, Object> mapParams = new HashMap<>();
            String  query = "SELECT f.fahrtkasse_nr, f.belegdatum, f.betrag, f.zahlungsart, f.zahlen_am, " +
                    "a.vorname, a.nachname, a.strasse, a.plz, a.ort, a.iban, a.bic, a.bank, a.konto_nr, " +
                    "a.blz, p.planung_nr, p.zusatz " +
                    "FROM adresse a, fahrtkasse f, planung p " +
                    "WHERE f.fahrtkasse_nr = '" +
                    fahrtkasseErstellenPresenter.getView().getTfFahrtkasseNr().getText() + "' " +
                    "AND f.adresse_id = a.adresse_id " +
                    "AND f.planung_id = p.planung_id";

            EntityManager entityManager =  service.getEntityManager();
            entityManager.getTransaction().begin();
            Connection connection = entityManager.unwrap(SessionImpl.class).connection();
            ReportHelpers.report("rptFahrtkasse.jasper",
                    "Fahrtkasse", query, preview, mapParams, connection);
            entityManager.getTransaction().commit();
        }
    }

    private void printBelegaufstellung(boolean preview) {
        if (isFahrtkasseSaved()) {
            HashMap<String, Object> mapParams = new HashMap<>();

            // fahrtkassewaehrungen as report params
            List<FahrtkasseWaehrung> fahrtkasseWaehrungList = entity.getFahrtkasseWaehrungList();
            int i = 0;
            for (FahrtkasseWaehrung fahrtkassewaehrung : fahrtkasseWaehrungList) {
                i++;
                if (i < 6) {
                    mapParams.put(("multiplyEur" + i) , fahrtkassewaehrung.getMultiplyEur());
                    mapParams.put(("kurs" + i) , fahrtkassewaehrung.getKurs().doubleValue());
                    mapParams.put(("waehrung" + i) , fahrtkassewaehrung.getWaehrung().getCode());
                }
            }

            // report query
            String  query = "SELECT f.fahrtkasse_nr, f.belegdatum, f.abgerechnet_am, f.betrag, f.ausgaben_gesamt, " +
                    "f.einnahmen_gesamt, f.saldo, a.vorname, a.nachname, a.strasse, a.plz, a.ort, a.iban, a.bic, " +
                    "a.bank, a.konto_nr, a.blz, k.konto_id, k.konto_nr, k.name, w.code, p.planung_nr, p.zusatz, " +
                    "fd.nummer, fd.empfaenger, fd.zweck, fd.betrag as detail_betrag, fd.waehrung_id, fd.betrag_in_eur " +
                    "FROM fahrtkasse f " +
                    "LEFT JOIN adresse a ON f.adresse_id = a.adresse_id " +
                    "LEFT JOIN fahrtkasse_detail fd ON f.fahrtkasse_id = fd.fahrtkasse_id " +
                    "LEFT JOIN planung p ON f.planung_id = p.planung_id " +
                    "LEFT JOIN konto k ON fd.konto_id = k.konto_id " +
                    "LEFT JOIN waehrung w ON fd.waehrung_id = w.waehrung_id " +
                    "WHERE f.fahrtkasse_id = '" + entity.getId().toString() + "' " +
                    "ORDER BY k.konto_nr ASC";

            EntityManager entityManager =  service.getEntityManager();
            entityManager.getTransaction().begin();
            Connection connection = entityManager.unwrap(SessionImpl.class).connection();
            ReportHelpers.report("rptFahrtkasseBelegaufstellung.jasper",
                    "Belegaufstellung", query, preview, mapParams, connection);
            entityManager.getTransaction().commit();
        }
    }

    private void printKontierung(boolean preview) {
        if (isFahrtkasseSaved()) {
            HashMap<String, Object> mapParams = new HashMap<>();

            // report query
            String  query = "SELECT f.fahrtkasse_nr, f.saldo, a.vorname, a.nachname, k.konto_id, k.konto_nr, " +
                "p.planung_nr, p.zusatz, p.fahrtkasse_kostenstelle, fd.betrag_in_eur, fd.konto_id " +
                "FROM fahrtkasse f " +
                "LEFT JOIN adresse a ON f.adresse_id = a.adresse_id " +
                "LEFT JOIN fahrtkasse_detail fd ON f.fahrtkasse_id = fd.fahrtkasse_id " +
                "LEFT JOIN planung p ON f.planung_id = p.planung_id " +
                "LEFT JOIN konto k ON fd.konto_id = k.konto_id " +
                "WHERE f.fahrtkasse_id = '" + entity.getId().toString() + "' " +
                "ORDER BY k.konto_nr ASC";

            EntityManager entityManager =  service.getEntityManager();
            entityManager.getTransaction().begin();
            Connection connection = entityManager.unwrap(SessionImpl.class).connection();
            ReportHelpers.report("rptFahrtkasseKontierung.jasper",
                    "Kontrierung", query, preview, mapParams, connection);
            entityManager.getTransaction().commit();
        }
    }

    private void printBrief(boolean preview) {
        if (isFahrtkasseSaved()) {
            HashMap<String, Object> mapParams = new HashMap<>();

            mapParams.put("benutzer", IbbManagement.get().getLoggedUser().getName());

            // if Saldo > 0
            String saldoText1 = "Bitte erstatten Sie zum Abschluss der Fahrtkasse " +
                    "diesen verbliebenen Betrag auf unser unten " +
                    "genanntes Konto unter dem Verwendungszweck „Fahrtkasse " +
                    entity.getFahrtkasseNr().toString() + "”.";


            // if Saldo = 0
            String saldoText2 = "Die Fahrtkasse ist damit abgeschlossen.";

            // if Saldo < 0
            String saldoText3 = "Wir werden diesen Betrag zum Abschluss der Fahrkasse " +
                    "in Kürze unter dem Verwendungszweck „Erstattung Auslagen Fahrtkasse " +
                    entity.getFahrtkasseNr().toString() +
                    "” auf Ihr Konto überweisen.";

            if (entity.getSaldo().doubleValue() > 0) {
                mapParams.put("saldoText", saldoText1);
            } else if (entity.getSaldo().doubleValue() == 0) {
                mapParams.put("saldoText", saldoText2);
            } else if (entity.getSaldo().doubleValue() < 0) {
                mapParams.put("saldoText", saldoText3);
            }

            // report query
            String  query = "SELECT f.fahrtkasse_nr, f.saldo, a.vorname, a.nachname, a.strasse, a.plz, " +
                    "a.ort, a.anrede_brief, p.planung_nr, p.zusatz " +
                    "FROM fahrtkasse f " +
                    "LEFT JOIN adresse a ON f.adresse_id = a.adresse_id " +
                    "LEFT JOIN planung p ON f.planung_id = p.planung_id " +
                    "WHERE f.fahrtkasse_id = '" + entity.getId().toString() + "' ";

            EntityManager entityManager =  service.getEntityManager();
            entityManager.getTransaction().begin();
            Connection connection = entityManager.unwrap(SessionImpl.class).connection();
            ReportHelpers.report("rptFahrtkasseBrief.jasper",
                    "Brief", query, preview, mapParams, connection);
            entityManager.getTransaction().commit();
        }
    }

    @Override
    public boolean isFormValid() {
        if (fahrtkasseErstellenPresenter.getView().getValidationSupport().isInvalid()) {
            Notifications.create()
                    .text(I18n.COMMON.getString("notification.saveValidationError"))
                    .position(Pos.TOP_RIGHT).showWarning();
        }

        return !fahrtkasseErstellenPresenter.getView().getValidationSupport().isInvalid();
    }

    private boolean isFahrtkasseSaved() {
        if (StringUtils.isEmpty(fahrtkasseErstellenPresenter.getView().getTfFahrtkasseNr().getText())) {
            MessageBox.get()
                    .contentText(I18n.FAHRTKASSE.getString("fahrtkasse.form.printInfo"))
                    .showWarning();
            return false;
        }

        return true;
    }

    @Override
    public AppPlaces getPlaceName() {
        return AppPlaces.FAHRTKASSE_FORM;
    }

    @Override
    public void onBack() {
        navigationManager.goTo(new Place(AppPlaces.FAHRTKASSE));
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
