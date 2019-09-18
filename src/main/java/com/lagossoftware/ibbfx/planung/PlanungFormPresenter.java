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
import com.lagossoftware.ibbfx.IbbManagement;
import com.lagossoftware.ibbfx.app.AppPlaces;
import com.lagossoftware.ibbfx.app.I18n;
import com.lagossoftware.ibbfx.entity.Params;
import com.lagossoftware.ibbfx.entity.Planung;
import com.lagossoftware.ibbfx.entity.PlanungParams;
import com.lagossoftware.ibbfx.service.ParamsService;
import com.lagossoftware.ibbfx.service.PlanungParamsService;
import com.lagossoftware.ibbfx.service.PlanungService;
import com.lagossoftware.lagosfx.common.DateHelpers;
import com.lagossoftware.lagosfx.common.ReportHelpers;
import com.lagossoftware.lagosfx.control.IntegerField;
import com.lagossoftware.lagosfx.control.NumberField;
import com.lagossoftware.lagosfx.mvp.AbstractMultiPageFormPresenter;
import com.lagossoftware.lagosfx.mvp.HasParameters;
import com.lagossoftware.lagosfx.mvp.Place;
import com.lagossoftware.lagosfx.navigation.NavigationManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.BorderPane;
import org.controlsfx.control.Notifications;
import org.hibernate.internal.SessionImpl;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

/**
 * Planung form presenter
 *
 * @author Cem Ikta
 */
public class PlanungFormPresenter extends AbstractMultiPageFormPresenter<Planung,
        PlanungFormView, PlanungService> implements HasParameters {

    private static final String REPORT_MASSNAHMEDATENBLATT1 = "rptMassnahmedatenblatt1.jasper";
    private static final String REPORT_MASSNAHMEKALKULATION = "rptMassnahmeKalkulation.jasper";

    private PlanungRahmendatenPresenter planungRahmendatenPresenter;
    private PlanungPersonPresenter planungPersonPresenter;
    private PlanungKostenPresenter planungKostenPresenter;
    private PlanungReisekostenPresenter planungReisekostenPresenter;
    private PlanungZuschussPresenter planungZuschussPresenter;
    private PlanungNamenPresenter planungNamenPresenter;
    private PlanungNotizenPresenter planungNotizenPresenter;
    private Map<String, Object> parameters;
    private PlanungParams planungParams;
    private PlanungParamsService planungParamsService;
    private PlanungParamsDialog planungParamsDialog;
    private Params params;
    private ParamsService paramsService;

    private final NumberFormat numberFormat = new DecimalFormat("#,###,###,##0.00");

    // massnahmedatenblatt 1 fields
    private Integer zahlendeNichtzahlendeTNDe;
    private Integer zahlendeNichtzahlendeTNAusl;
    private Integer begleitpersonen;
    private Integer teilnehmerGesamt;

    // massnahmekalkulation fields
    private Integer sumZahlendeTnDeAusl;
    private Integer sumZahlendeNichtzahlendeTNAusl;
    private Integer sumZahlendeNichtzahlendeTNDe;

    private Integer begleitpersonenDe;
    private Integer begleitpersonenDeOhneFremde;
    private Integer alleBegleitpersonenOhneFremdeMitarbeiter;
    private Integer vaTage;

    // 1. fahrtkosten
    private BigDecimal sumFKZahlendeTeilnehmerGesamtkosten;
    private BigDecimal sumFKZahlendeTeilnehmerTeilnehmerkosten;

    private BigDecimal sumFKNichtzahlendeTeilnehmerGesamtkosten;
    private BigDecimal sumFKNichtzahlendeTeilnehmerTeilnehmerkosten;

    private BigDecimal sumFKAuslaendischeTeilnehmerGesamtkosten;
    private BigDecimal sumFKAuslaendischeTeilnehmerTeilnehmerkosten;

    private BigDecimal sumFKMassnahmeabhaengigeFahrtkostenGesamtkosten;
    private BigDecimal sumFKMassnahmeabhaengigeFahrtkostenTeilnehmerkosten;

    private BigDecimal sumFKBegleitpersonenGesamtkosten;
    private BigDecimal sumFKBegleitpersonenTeilnehmerkosten;

    private BigDecimal sumFKVisakostenTeilnehmerGesamtkosten;
    private BigDecimal sumFKVisakostenTeilnehmerTeilnehmerkosten;

    private BigDecimal sumFKVisakostenBegleitpersonenGesamtkosten;
    private BigDecimal sumFKVisakostenBegleitpersonenTeilnehmerkosten;

    private BigDecimal sumFKFahrtkostenGesamtGesamtkosten;
    private BigDecimal sumFKFahrtkostenGesamtTeilnehmerkosten;
    private BigDecimal sumFKFahrtkostenGesamtProzent;

    // 2. Unterkunft / Verpflegung
    private BigDecimal sumUVZahlendeTeilnehmerGesamtkosten;
    private BigDecimal sumUVZahlendeTeilnehmerTeilnehmerkosten;

    private BigDecimal sumUVNichtzahlendeTeilnehmerGesamtkosten;
    private BigDecimal sumUVNichtzahlendeTeilnehmerTeilnehmerkosten;

    private BigDecimal sumUVAuslaendischeTeilnehmerGesamtkosten;
    private BigDecimal sumUVAuslaendischeTeilnehmerTeilnehmerkosten;

    private BigDecimal sumUVBegleitpersonenGesamtkosten;
    private BigDecimal sumUVBegleitpersonenTeilnehmerkosten;

    private BigDecimal sumUVGesamtGesamtkosten;
    private BigDecimal sumUVGesamtTeilnehmerkosten;
    private BigDecimal sumUVGesamtProzent;

    // 3. Honorarkosten
    private BigDecimal sumHKHonorareFuerIbbBegleitungGesamtkosten;
    private BigDecimal sumHKHonorareFuerIbbBegleitungTeilnehmerkosten;

    private BigDecimal sumHKHonorareFuerAuslaendischeBegleitungGesamtkosten;
    private BigDecimal sumHKHonorareFuerAuslaendischeBegleitungTeilnehmerkosten;

    private BigDecimal sumHKHonorareFuerVisabeschaffungGesamtkosten = BigDecimal.ZERO;
    private BigDecimal sumHKHonorareFuerVisabeschaffungTeilnehmerkosten;

    private BigDecimal sumHKGesamtGesamtkosten;
    private BigDecimal sumHKGesamtTeilnehmerkosten;
    private BigDecimal sumHKGesamtProzent;

    // 4. Programmkosten
    private BigDecimal sumPKEintrittgelderDeutscheTNTeilnehmerkosten;
    private BigDecimal sumPKEintrittgelderDeutscheTNGesamtkosten;

    private BigDecimal sumPKEintrittgelderAuslTNTeilnehmerkosten;
    private BigDecimal sumPKEintrittgelderAuslTNGesamtkosten;

    private BigDecimal sumPKProgrammkostenGesamtkosten;
    private BigDecimal sumPKProgrammkostenTeilnehmerkosten;

    private BigDecimal sumPKGesamtGesamtkosten;
    private BigDecimal sumPKGesamtTeilnehmerkosten;
    private BigDecimal sumPKGesamtProzent;

    // 5. Versicherungen
    private BigDecimal sumVERVersicherungDeutscheTNTeilnehmerkosten;
    private BigDecimal sumVERVersicherungDeutscheTNGesamtkosten;

    private BigDecimal sumVERVersicherungAuslTNTeilnehmerkosten;
    private BigDecimal sumVERVersicherungAuslTNGesamtkosten;

    private BigDecimal sumVERBegleitpersonenDeutscheGesamtkosten;
    private BigDecimal sumVERBegleitpersonenDeutscheTeilnehmerkosten;
    private BigDecimal sumVERBegleitpersonenAuslGesamtkosten;
    private BigDecimal sumVERBegleitpersonenAuslTeilnehmerkosten;
    private BigDecimal sumVERBegleitpersonenGesamtkosten;
    private BigDecimal sumVERBegleitpersonenTeilnehmerkosten;

    private BigDecimal sumVERGesamtGesamtkosten;
    private BigDecimal sumVERGesamtTeilnehmerkosten;
    private BigDecimal sumVERGesamtProzent;

    // 6. Strukturkosten
    private BigDecimal sumSKStrukturkostenGesamtkosten;
    private BigDecimal sumSKStrukturkostenTeilnehmerkosten;
    private BigDecimal sumSKStrukturkostenProzent;

    // Gesamtkosten der Massnahme
    private BigDecimal sumGesamtkostenDerMassnahmeGesamtkosten;
    private BigDecimal sumGesamtkostenDerMassnahmeTeilnehmerkosten;
    private BigDecimal sumGesamtkostenDerMassnahmeProzent;

    // Gesamtpreis der Massnahme
    private BigDecimal sumGesamtpreisDerMassnahmeGesamtkosten;
    private BigDecimal sumGesamtpreisDerMassnahmeTeilnehmerkosten;

    // Verkaufpreis & IBB-Erlös
    private BigDecimal sumVerkaufpreisGesamtkosten;
    private BigDecimal sumVerkaufpreisTeilnehmerkosten;
    private BigDecimal sumIBBErloes;

    @Inject
    public PlanungFormPresenter(final NavigationManager navigationManager,
                                final Logger logger, final PlanungFormView view,
                                PlanungService service,
                                ParamsService paramsService,
                                PlanungParamsService planungParamsService,
                                PlanungRahmendatenPresenter planungRahmendatenPresenter,
                                PlanungPersonPresenter planungPersonPresenter,
                                PlanungKostenPresenter planungKostenPresenter,
                                PlanungReisekostenPresenter planungReisekostenPresenter,
                                PlanungZuschussPresenter planungZuschussPresenter,
                                PlanungNamenPresenter planungNamenPresenter,
                                PlanungNotizenPresenter planungNotizenPresenter) {
        super(navigationManager, logger, view, service);

        this.paramsService = paramsService;
        this.planungParamsService = planungParamsService;
        this.planungRahmendatenPresenter = planungRahmendatenPresenter;
        this.planungPersonPresenter = planungPersonPresenter;
        this.planungKostenPresenter = planungKostenPresenter;
        this.planungReisekostenPresenter = planungReisekostenPresenter;
        this.planungZuschussPresenter = planungZuschussPresenter;
        this.planungNamenPresenter = planungNamenPresenter;
        this.planungNotizenPresenter = planungNotizenPresenter;
    }

    @Override
    public void start(BorderPane container) {
        container.setCenter(view.asNode());
        container.setLeft(null);
        container.setRight(null);
        container.setTop(null);
        container.setBottom(null);
        bind();

        planungRahmendatenPresenter.start(container);
        planungPersonPresenter.start(container);
        planungKostenPresenter.start(container);
        planungReisekostenPresenter.start(container);
        planungZuschussPresenter.start(container);
        planungNamenPresenter.start(container);
        planungNotizenPresenter.start(container);

        view.getButtonBarPane().addPage(planungRahmendatenPresenter.getView().getIconPath(),
                planungRahmendatenPresenter.getView().getTitle(),
                planungRahmendatenPresenter.getView().asNode(),
                ContentDisplay.LEFT);

        view.getButtonBarPane().addPage(planungPersonPresenter.getView().getIconPath(),
                planungPersonPresenter.getView().getTitle(),
                planungPersonPresenter.getView().asNode(),
                ContentDisplay.LEFT);

        view.getButtonBarPane().addPage(planungKostenPresenter.getView().getIconPath(),
                planungKostenPresenter.getView().getTitle(),
                planungKostenPresenter.getView().asNode(),
                ContentDisplay.LEFT);

        view.getButtonBarPane().addPage(planungReisekostenPresenter.getView().getIconPath(),
                planungReisekostenPresenter.getView().getTitle(),
                planungReisekostenPresenter.getView().asNode(),
                ContentDisplay.LEFT);

        view.getButtonBarPane().addPage(planungZuschussPresenter.getView().getIconPath(),
                planungZuschussPresenter.getView().getTitle(),
                planungZuschussPresenter.getView().asNode(),
                ContentDisplay.LEFT);

        view.getButtonBarPane().addPage(planungNamenPresenter.getView().getIconPath(),
                planungNamenPresenter.getView().getTitle(),
                planungNamenPresenter.getView().asNode(),
                ContentDisplay.LEFT);

        view.getButtonBarPane().addPage(planungNotizenPresenter.getView().getIconPath(),
                planungNotizenPresenter.getView().getTitle(),
                planungNotizenPresenter.getView().asNode(),
                ContentDisplay.LEFT);

        params = paramsService.find(1L);
        entity =  (Planung) parameters.get("selectedEntity");
        if (entity.getId() == null) {
            planungParams = new PlanungParams();
            planungParams.setHonorareFuerIbbBegleitung(params.getHonorareFuerIbbBegleitung());
            planungParams.setIbbStrukturkostenProTNProTag(params.getIbbStrukturkostenProTNProTag());
            planungParams.setIbbErloes(params.getIbbErloes());
            planungParams.setHaftpflichtUnfallVersicherungProPersonTag(params.getHaftpflichtUnfallVersicherungProPersonTag());
            planungParams.setAuslandsreisekrankenVersicherungProPersonTag(params.getAuslandsreisekrankenVersicherungProPersonTag());
            planungParams.setKrankenversicherungFuerAuslTNProPersonTag(params.getKrankenversicherungFuerAuslTNProPersonTag());
            planungParams.setRegressversicherungVaInDeBis8Tage(params.getRegressversicherungVaInDeBis8Tage());
            planungParams.setRegressversicherungVaInDeBis22Tage(params.getRegressversicherungVaInDeBis22Tage());
            planungParams.setRegressversicherungVaInDeBis42Tage(params.getRegressversicherungVaInDeBis42Tage());
            planungParams.setRegressversicherungVaImAuslandBis8Tage(params.getRegressversicherungVaImAuslandBis8Tage());
            planungParams.setRegressversicherungVaImAuslandBis22Tage(params.getRegressversicherungVaImAuslandBis22Tage());
            planungParams.setRegressversicherungVaImAuslandBis42Tage(params.getRegressversicherungVaImAuslandBis42Tage());
            planungParams.setRechtschutzversicherungVaBis8Tage(params.getRechtschutzversicherungVaBis8Tage());
            planungParams.setRechtschutzversicherungVaBis14Tage(params.getRechtschutzversicherungVaBis14Tage());
            planungParams.setRechtschutzversicherungVaBis22Tage(params.getRechtschutzversicherungVaBis22Tage());
            planungParams.setReisepreissicherungProPersonTag(params.getReisepreissicherungProPersonTag());
        } else {
          planungParams = entity.getPlanungParams();
        }

        popFields();
        setRequestFocus();
    }

    @Override
    public void bind() {
        super.bind();

        EventHandler<ActionEvent> printPreviewAction = event -> {
            if (event.getSource() == view.getMnuMassnahmedatenblatt1()) {
                printMassnahmedatenblatt1(true);
            } else if (event.getSource() == view.getMnuMassnahmekalkulation()) {
                printMassnahmekalkulation(true);
            }
        };

        EventHandler<ActionEvent> printAction = event -> {
            if (event.getSource() == view.getMnuMassnahmedatenblatt1Print()) {
                printMassnahmedatenblatt1(false);
            } else if (event.getSource() == view.getMnuMassnahmekalkulationPrint()) {
                printMassnahmekalkulation(false);
            }
        };

        view.getMnuMassnahmedatenblatt1().setOnAction(printPreviewAction);
        view.getMnuMassnahmekalkulation().setOnAction(printPreviewAction);
        view.getMnuMassnahmedatenblatt1Print().setOnAction(printAction);
        view.getMnuMassnahmekalkulationPrint().setOnAction(printAction);
        view.getBtnPlanungParams().setOnAction((ActionEvent event) -> {
            if (IbbManagement.get().isLoggedUserAdmin()) {
                onPlanungParams();
            } else {
                Notifications.create()
                        .text(I18n.COMMON.getString("app.readNotAllowed"))
                        .position(Pos.TOP_RIGHT).showInformation();
            }
        });

        addOverviewListeners();
    }

    private void addOverviewListeners() {
        // rahmendaten
        planungRahmendatenPresenter.getView().getNfPlanungNr().valueProperty()
                .addListener((observable, oldValue, newValue) -> {
                    setPlanungNrValue();
                });
        planungRahmendatenPresenter.getView().getTfZusatz().textProperty()
                .addListener((observable, oldValue, newValue) -> {
                    setPlanungNrValue();
                });

        planungRahmendatenPresenter.getView().getTfTitel().textProperty()
                .addListener((observable, oldValue, newValue) -> {
                    view.getLblPlanungTitle().setText(newValue);
                });

        planungRahmendatenPresenter.getView().getDpVeranstaltungBeginn().valueProperty()
                .addListener((observable, oldValue, newValue) -> {
                    computeVATage();
                    computeAll();
                });

        planungRahmendatenPresenter.getView().getDpVeranstaltungEnde().valueProperty()
                .addListener((observable, oldValue, newValue) -> {
                    computeVATage();
                    computeAll();
                });

        addIntegerFieldListener(planungRahmendatenPresenter.getView().getNfTageNachWbg());

        // person
        planungPersonPresenter.getView().getNfZahlendeTnDe().valueProperty()
                .addListener((observable, oldValue, newValue) -> {
                    computeZahlendeTN();
                });

        planungPersonPresenter.getView().getNfZahlendeTnAusl().valueProperty()
                .addListener((observable, oldValue, newValue) -> {
                    computeZahlendeTN();
                    computeAll();
                });

        planungPersonPresenter.getView().getNfNichtZahlendeTnDe().valueProperty()
                .addListener((observable, oldValue, newValue) -> {
                    computeNichtZahlendeTN();
                    computeAll();
                });

        planungPersonPresenter.getView().getNfNichtZahlendeTnAusl().valueProperty()
                .addListener((observable, oldValue, newValue) -> {
                    computeNichtZahlendeTN();
                    computeAll();
                });

        addIntegerFieldListener(planungPersonPresenter.getView().getNfTeilnehmerNachWgbDe());
        addIntegerFieldListener(planungPersonPresenter.getView().getNfTeilnehmerNachWgbAusl());
        addIntegerFieldListener(planungPersonPresenter.getView().getNfHauptberuflicheMitarbeiter());
        addIntegerFieldListener(planungPersonPresenter.getView().getNfHonorarMitarbeiter());
        addIntegerFieldListener(planungPersonPresenter.getView().getNfUnbezahlteMitarbeiter());
        addIntegerFieldListener(planungPersonPresenter.getView().getNfFremdeMitarbeiter());
        addNumberFieldListener(planungPersonPresenter.getView().getNfZusaetzlicheReiseKosten());
        addNumberFieldListener(planungPersonPresenter.getView().getNfZusaetzlicheVerpflegungskosten());
        addNumberFieldListener(planungPersonPresenter.getView().getNfWeitereAusgaben());
        addIntegerFieldListener(planungPersonPresenter.getView().getNfAuslMitarbeiter());
        addIntegerFieldListener(planungPersonPresenter.getView().getNfUnterkunfstage());
        addNumberFieldListener(planungPersonPresenter.getView().getNfVerpflegungstage());
        addIntegerFieldListener(planungPersonPresenter.getView().getNfHonorartage());
        addNumberFieldListener(planungPersonPresenter.getView().getNfHonorarsatz());
        addNumberFieldListener(planungPersonPresenter.getView().getNfReisekosten());

        // kosten
        addRadiButtonListener(planungKostenPresenter.getView().getRbVeranstaltungInDe());
        addRadiButtonListener(planungKostenPresenter.getView().getRbVeranstaltungImAusland());
        addCheckBoxListener(planungKostenPresenter.getView().getChbRegressVersicherung());
        addCheckBoxListener(planungKostenPresenter.getView().getChbReiserecht());
        addNumberFieldListener(planungKostenPresenter.getView().getNfVisagebuehrProPerson());
        addNumberFieldListener(planungKostenPresenter.getView().getNfVisaNebenkosten());
        addNumberFieldListener(planungKostenPresenter.getView().getNfFuehrungenGesamt());
        addNumberFieldListener(planungKostenPresenter.getView().getNfSonstigekostenGesamt());
        addNumberFieldListener(planungKostenPresenter.getView().getNfSonstigekostenProTn());

        // reisekosten
        addNumberFieldListener(planungReisekostenPresenter.getView().getNfBahnfahrtProPerson());
        addNumberFieldListener(planungReisekostenPresenter.getView().getNfFlugkostenProPerson());
        addNumberFieldListener(planungReisekostenPresenter.getView().getNfBustransfersGesamt());
        addNumberFieldListener(planungReisekostenPresenter.getView().getNfWeitereFahrtenImLandProPerson());
        addNumberFieldListener(planungReisekostenPresenter.getView().getNfFahrtkostenFuerAuslTnProPerson());
        addNumberFieldListener(planungReisekostenPresenter.getView().getNfUebernachtungskostenProNacht());
        addNumberFieldListener(planungReisekostenPresenter.getView().getNfAufpreisEinzelzimmerProNacht());
        addIntegerFieldListener(planungReisekostenPresenter.getView().getNfAnzahlNaechteProDeTn());
        addIntegerFieldListener(planungReisekostenPresenter.getView().getNfAnzahlNaechteProAuslTn());
        addNumberFieldListener(planungReisekostenPresenter.getView().getNfVerpflegungskostenProTag());
        addNumberFieldListener(planungReisekostenPresenter.getView().getNfVerpflegungstageProDeTn());
        addNumberFieldListener(planungReisekostenPresenter.getView().getNfVerpflegungstageProAuslTn());
        addNumberFieldListener(planungReisekostenPresenter.getView().getNfPauschalpreis());

        // TODO zuschuesse listeners?

        //  namen
        addNumberFieldListener(planungNamenPresenter.getView().getNfFestpreis());
        addCheckBoxListener(planungNamenPresenter.getView().getChbWirdDurchgefuehrt());
    }

    private void addNumberFieldListener(NumberField field) {
        field.valueProperty().addListener((observable, oldValue, newValue) -> {
            computeAll();
        });
    }

    private void addIntegerFieldListener(IntegerField field) {
        field.valueProperty().addListener((observable, oldValue, newValue) -> {
            computeAll();
        });
    }

    private void addRadiButtonListener(RadioButton btn) {
        btn.selectedProperty().addListener((observable, oldValue, newValue) -> {
            computeAll();
        });
    }

    private void addCheckBoxListener(CheckBox chb) {
        chb.selectedProperty().addListener((observable, oldValue, newValue) -> {
            computeAll();
        });
    }

    private void onPlanungParams() {
        PlanungParamsModel model = new PlanungParamsModel();
        model.setHonorareFuerIbbBegleitung(planungParams.getHonorareFuerIbbBegleitung());
        model.setIbbStrukturkostenProTNProTag(planungParams.getIbbStrukturkostenProTNProTag());
        model.setIbbErloes(planungParams.getIbbErloes());
        // versicherung
        model.setHaftpflichtUnfallVersicherungProPersonTag(planungParams.getHaftpflichtUnfallVersicherungProPersonTag());
        model.setAuslandsreisekrankenVersicherungProPersonTag(planungParams.getAuslandsreisekrankenVersicherungProPersonTag());
        model.setKrankenversicherungFuerAuslTNProPersonTag(planungParams.getKrankenversicherungFuerAuslTNProPersonTag());
        model.setRegressversicherungVaInDeBis8Tage(planungParams.getRegressversicherungVaInDeBis8Tage());
        model.setRegressversicherungVaInDeBis22Tage(planungParams.getRegressversicherungVaInDeBis22Tage());
        model.setRegressversicherungVaInDeBis42Tage(planungParams.getRegressversicherungVaInDeBis42Tage());
        model.setRegressversicherungVaImAuslandBis8Tage(planungParams.getRegressversicherungVaImAuslandBis8Tage());
        model.setRegressversicherungVaImAuslandBis22Tage(planungParams.getRegressversicherungVaImAuslandBis22Tage());
        model.setRegressversicherungVaImAuslandBis42Tage(planungParams.getRegressversicherungVaImAuslandBis42Tage());
        model.setRechtschutzversicherungVaBis8Tage(planungParams.getRechtschutzversicherungVaBis8Tage());
        model.setRechtschutzversicherungVaBis14Tage(planungParams.getRechtschutzversicherungVaBis14Tage());
        model.setRechtschutzversicherungVaBis22Tage(planungParams.getRechtschutzversicherungVaBis22Tage());
        model.setReisepreissicherungProPersonTag(planungParams.getReisepreissicherungProPersonTag());

        planungParamsDialog = new PlanungParamsDialog(model, paramsService);
        planungParamsDialog.pop();
        Optional<PlanungParamsModel> result = planungParamsDialog.showAndWait();

        result.ifPresent(planungParamsModel -> {
            planungParams.setHonorareFuerIbbBegleitung(planungParamsModel.getHonorareFuerIbbBegleitung());
            planungParams.setIbbStrukturkostenProTNProTag(planungParamsModel.getIbbStrukturkostenProTNProTag());
            planungParams.setIbbErloes(planungParamsModel.getIbbErloes());
            // versicherung
            planungParams.setHaftpflichtUnfallVersicherungProPersonTag(planungParamsModel.getHaftpflichtUnfallVersicherungProPersonTag());
            planungParams.setAuslandsreisekrankenVersicherungProPersonTag(planungParamsModel.getAuslandsreisekrankenVersicherungProPersonTag());
            planungParams.setKrankenversicherungFuerAuslTNProPersonTag(planungParamsModel.getKrankenversicherungFuerAuslTNProPersonTag());
            planungParams.setRegressversicherungVaInDeBis8Tage(planungParamsModel.getRegressversicherungVaInDeBis8Tage());
            planungParams.setRegressversicherungVaInDeBis22Tage(planungParamsModel.getRegressversicherungVaInDeBis22Tage());
            planungParams.setRegressversicherungVaInDeBis42Tage(planungParamsModel.getRegressversicherungVaInDeBis42Tage());
            planungParams.setRegressversicherungVaImAuslandBis8Tage(planungParamsModel.getRegressversicherungVaImAuslandBis8Tage());
            planungParams.setRegressversicherungVaImAuslandBis22Tage(planungParamsModel.getRegressversicherungVaImAuslandBis22Tage());
            planungParams.setRegressversicherungVaImAuslandBis42Tage(planungParamsModel.getRegressversicherungVaImAuslandBis42Tage());
            planungParams.setRechtschutzversicherungVaBis8Tage(planungParamsModel.getRechtschutzversicherungVaBis8Tage());
            planungParams.setRechtschutzversicherungVaBis14Tage(planungParamsModel.getRechtschutzversicherungVaBis14Tage());
            planungParams.setRechtschutzversicherungVaBis22Tage(planungParamsModel.getRechtschutzversicherungVaBis22Tage());
            planungParams.setReisepreissicherungProPersonTag(planungParamsModel.getReisepreissicherungProPersonTag());
        });
    }

    // ======================================
    // =   overview panel compute methods   =
    // ======================================

    private void setPlanungNrValue() {
        view.getLblPlanungNr().setText(
                planungRahmendatenPresenter.getView().getNfPlanungNr().getValue().toString() + "-" +
                planungRahmendatenPresenter.getView().getTfZusatz().getText());
    }

    private void computeVATage() {
        LocalDate start = planungRahmendatenPresenter.getView().getDpVeranstaltungBeginn().getValue();
        LocalDate end = planungRahmendatenPresenter.getView().getDpVeranstaltungEnde().getValue();

        if ( start != null && end != null) {
            view.getLblVATage().setText(String.valueOf(DateHelpers.differenceDays(start, end)));
        } else {
            view.getLblVATage().setText("0");
        }
    }

    private void computeZahlendeTN() {
        Integer zahlendeTN = planungPersonPresenter.getView().getNfZahlendeTnDe().getValue() +
                planungPersonPresenter.getView().getNfZahlendeTnAusl().getValue();
        view.getLblZahlendeTN().setText(zahlendeTN.toString());
    }

    private void computeNichtZahlendeTN() {
        Integer nichtZahlendeTN = planungPersonPresenter.getView().getNfNichtZahlendeTnDe().getValue() +
                planungPersonPresenter.getView().getNfNichtZahlendeTnAusl().getValue();
        view.getLblNichtZahlendeMitfahrer().setText(nichtZahlendeTN.toString());
    }

    // ===========================
    // =   compute all methods   =
    // ===========================

    private void computeAll() {
        /** compute massnahmedatenblatt1 */
        computeZahlendeNichtzahlendeTNDe();
        computeZahlendeNichtzahlendeTNAusl();
        computeBegleitpersonen();
        computeTeilnehmerGesamt();

        /** compute massnahmekalkulation; */
        computeZahlendeTNDeAuslAnzahl();
        computeZahlendeNichtzahlendeTNAuslAnzahl();
        computeZahlendeNichtzahlendeTNDeAnzahl();

        computeBegleitpersonenDe();
        computeBegleitpersonenDeOhneFremde();
        computeAlleBegleitpersonenOhneFremdeMitarbeiter();

        // 1. fahrtkosten
        computeZahlendeTeilnehmerTeilnehmerkosten();
        computeZahlendeTeilnehmerGesamtkosten();
        computeNichtzahlendeTeilnehmerGesamtkosten();
        computeAuslaendischeTeilnehmerTeilnehmerkosten();
        computeAuslaendischeTeilnehmerGesamtkosten();
        computeMassnahmeabhaengigeFahrtkostenTeilnehmerkosten();
        computeBegleitpersonenGesamtkosten();
        computeBegleitpersonenTeilnehmerkosten();
        computeVisakostenTeilnehmerGesamtkosten();
        computeVisakostenBegleitpersonenGesamtkosten();
        computeVisakostenBegleitpersonenTeilnehmerkosten();
        computeFahrtkostenGesamt();

        // 2. unterkunft / verpflegung
        computeUVZahlendeTeilnehmerGesamtkosten();
        computeUVZahlendeTeilnehmerTeilnehmerkosten();
        computeUVNichtzahlendeTeilnehmerGesamtkosten();
        computeUVNichtzahlendeTeilnehmerTeilnehmerkosten();
        computeUVAuslaendischeTeilnehmerGesamtkosten();
        computeUVAuslaendischeTeilnehmerTeilnehmerkosten();
        computeUVBegleitpersonenGesamtkosten();
        computeUVBegleitpersonenTeilnehmerkosten();
        computeUVGesamt();

        // 3. honorarkosten
        computeHKHonorareFuerIbbBegleitungGesamtkosten();
        computeHKHonorareFuerIbbBegleitungTeilnehmerkosten();
        computeHKHonorareFuerAuslaendischeBegleitungGesamtkosten();
        computeHKHonorareFuerAuslaendischeBegleitungTeilnehmerkosten();
        computeHKHonorareFuerVisabeschaffungGesamtkosten();
        computeHKHonorareFuerVisabeschaffungTeilnehmerkosten();
        computeHKGesamt();

        // 4. programmkosten
        computePKEintrittgelderDeutscheTNGesamtkosten();
        computePKEintrittgelderAuslaendischeTNGesamtkosten();
        computePKProgrammkostenGesamtkosten();
        computePKProgrammkostenTeilnehmerkosten();
        computePKGesamt();

        // 5. versicherungen
        computeVERVersicherungDeutscheTNTeilnehmerkosten();
        computeVERVersicherungDeutscheTNGesamtkosten();
        computeVERVersicherungAuslaendischeTNTeilnehmerkosten();
        computeVERVersicherungAuslaendischeTNGesamtkosten();
        computeVERBegleitpersonenDeutscheTeilnehmerkosten();
        computeVERBegleitpersonenDeutscheGesamtkosten();
        computeVERBegleitpersonenAuslTeilnehmerkosten();
        computeVERBegleitpersonenAuslGesamtkosten();
        computeVERBegleitpersonenTeilnehmerkosten();
        computeVERBegleitpersonenGesamtkosten();
        computeVERGesamt();

        // 6. ibb-strukturkosten
        computeSKStrukturkostenGesamtkosten();
        computeSKStrukturkostenTeilnehmerkosten();

        // Gesamtkosten der Massnahme
        computeGesamtkostenDerMassnahmeGesamtkosten();
        computeGesamtkostenDerMassnahmeTeilnehmerkosten();

        // Gesamtpreis der Massnahme
        computeGesamtpreisDerMassnahmeGesamtkosten();
        computeGesamtpreisDerMassnahmeTeilnehmerkosten();

        // Verkaufpreis & IBB-Erlös
        computeVerkaufpreisTeilnehmerkosten();
        computeVerkaufpreisGesamtkosten();
        computeIBBErloes();

        // TODO add new compute methods here...

        setOverviewFieldValues();
    }

    private void setOverviewFieldValues() {
        // TODO set values
        //view.getLblTeilnehmerabhaengigeKosten().setText(numberFormat.format(newValue));
        //view.getLblTeilnehmerunabhaengigeKosten().setText(numberFormat.format(newValue));
        //view.getLblKostenFuerAlleAuslaendischeTN().setText(numberFormat.format(newValue));
        view.getLblIBBStrukturkosten().setText(numberFormat.format(sumSKStrukturkostenGesamtkosten));
        view.getLblGesamtkosten().setText(numberFormat.format(sumGesamtkostenDerMassnahmeGesamtkosten));
        //view.getLblAbzZuschuesse().setText(numberFormat.format(newValue));
        //view.getLblPreisKalk().setText(numberFormat.format(newValue));
        view.getLblVerkaufspreis().setText(numberFormat.format(sumVerkaufpreisTeilnehmerkosten));
        view.getLblIBBErloes().setText(numberFormat.format(sumIBBErloes));
    }

    // =============================================
    // =   massnahmedatenblatt 1 compute methods   =
    // =============================================

    private void computeZahlendeNichtzahlendeTNDe() {
        zahlendeNichtzahlendeTNDe =
                planungPersonPresenter.getView().getNfZahlendeTnDe().getValue() +
                planungPersonPresenter.getView().getNfNichtZahlendeTnDe().getValue();
    }

    private void computeZahlendeNichtzahlendeTNAusl() {
        zahlendeNichtzahlendeTNAusl =
                planungPersonPresenter.getView().getNfZahlendeTnAusl().getValue() +
                planungPersonPresenter.getView().getNfNichtZahlendeTnAusl().getValue();
    }

    private void computeBegleitpersonen() {
        begleitpersonen =
                planungPersonPresenter.getView().getNfHauptberuflicheMitarbeiter().getValue() +
                planungPersonPresenter.getView().getNfHonorarMitarbeiter().getValue() +
                planungPersonPresenter.getView().getNfUnbezahlteMitarbeiter().getValue() +
                planungPersonPresenter.getView().getNfFremdeMitarbeiter().getValue() +
                planungPersonPresenter.getView().getNfAuslMitarbeiter().getValue();
    }

    private void computeTeilnehmerGesamt() {
        teilnehmerGesamt = zahlendeNichtzahlendeTNDe + zahlendeNichtzahlendeTNAusl + begleitpersonen;
    }

    // =============================================
    // =   massnahmekalkulation compute methods    =
    // =============================================

    /**
     * Common formula
     *
     * zahlende TN de + zahlende TN ausl
     */
    private void computeZahlendeTNDeAuslAnzahl() {
        sumZahlendeTnDeAusl = planungPersonPresenter.getView().getNfZahlendeTnDe().getValue() +
                planungPersonPresenter.getView().getNfZahlendeTnAusl().getValue();
    }

    /**
     * Common formula
     *
     * zahlende TN ausl + nichtZahlende TN ausl
     */
    private void computeZahlendeNichtzahlendeTNAuslAnzahl() {
        sumZahlendeNichtzahlendeTNAusl = planungPersonPresenter.getView().getNfZahlendeTnAusl().getValue() +
                planungPersonPresenter.getView().getNfNichtZahlendeTnAusl().getValue();
    }

    /**
     * Common formula
     *
     * zahlende TN de + nichtZahlende TN de
     */
    private void computeZahlendeNichtzahlendeTNDeAnzahl() {
        sumZahlendeNichtzahlendeTNDe = planungPersonPresenter.getView().getNfZahlendeTnDe().getValue() +
                planungPersonPresenter.getView().getNfNichtZahlendeTnDe().getValue();
    }

    /**
     * Compute only DE-Begleitpersonen
     */
    private void computeBegleitpersonenDe() {
        begleitpersonenDe =
                planungPersonPresenter.getView().getNfHauptberuflicheMitarbeiter().getValue() +
                planungPersonPresenter.getView().getNfHonorarMitarbeiter().getValue() +
                planungPersonPresenter.getView().getNfUnbezahlteMitarbeiter().getValue() +
                planungPersonPresenter.getView().getNfFremdeMitarbeiter().getValue();
    }

    /**
     * Compute only DE-Begleitpersonen without fremdeMitarbeiter
     */
    private void computeBegleitpersonenDeOhneFremde() {
        begleitpersonenDeOhneFremde =
                planungPersonPresenter.getView().getNfHauptberuflicheMitarbeiter().getValue() +
                planungPersonPresenter.getView().getNfHonorarMitarbeiter().getValue() +
                planungPersonPresenter.getView().getNfUnbezahlteMitarbeiter().getValue();
    }

    /**
     * Compute all Begleitpersonen without fremdeMitarbeiter
     */
    private void computeAlleBegleitpersonenOhneFremdeMitarbeiter() {
        alleBegleitpersonenOhneFremdeMitarbeiter =
                planungPersonPresenter.getView().getNfHauptberuflicheMitarbeiter().getValue() +
                planungPersonPresenter.getView().getNfHonorarMitarbeiter().getValue() +
                planungPersonPresenter.getView().getNfUnbezahlteMitarbeiter().getValue() +
                planungPersonPresenter.getView().getNfAuslMitarbeiter().getValue();
    }

    // =============================================
    // =  1.Fahrtkosten compute methods   -FK-     =
    // =============================================

    private void computeZahlendeTeilnehmerTeilnehmerkosten() {
        sumFKZahlendeTeilnehmerTeilnehmerkosten = BigDecimal.ZERO;

        sumFKZahlendeTeilnehmerTeilnehmerkosten = sumFKZahlendeTeilnehmerTeilnehmerkosten.add(
                planungReisekostenPresenter.getView().getNfBahnfahrtProPerson().getValue());

        sumFKZahlendeTeilnehmerTeilnehmerkosten = sumFKZahlendeTeilnehmerTeilnehmerkosten.add(
                planungReisekostenPresenter.getView().getNfFlugkostenProPerson().getValue());

        sumFKZahlendeTeilnehmerTeilnehmerkosten = sumFKZahlendeTeilnehmerTeilnehmerkosten.add(
                planungReisekostenPresenter.getView().getNfWeitereFahrtenImLandProPerson().getValue());
    }

    private void computeZahlendeTeilnehmerGesamtkosten() {
        sumFKZahlendeTeilnehmerGesamtkosten = BigDecimal.ZERO;

        sumFKZahlendeTeilnehmerGesamtkosten = sumFKZahlendeTeilnehmerTeilnehmerkosten.multiply(new BigDecimal(
                planungPersonPresenter.getView().getNfZahlendeTnDe().getValue()));
    }

    private void computeNichtzahlendeTeilnehmerGesamtkosten() {
        sumFKNichtzahlendeTeilnehmerGesamtkosten = BigDecimal.ZERO;
        // the formula of both is same.
        sumFKNichtzahlendeTeilnehmerTeilnehmerkosten = sumFKZahlendeTeilnehmerTeilnehmerkosten;

        sumFKNichtzahlendeTeilnehmerGesamtkosten = sumFKNichtzahlendeTeilnehmerTeilnehmerkosten.multiply(new BigDecimal(
                planungPersonPresenter.getView().getNfNichtZahlendeTnDe().getValue()));
    }

    private void computeAuslaendischeTeilnehmerTeilnehmerkosten() {
        sumFKAuslaendischeTeilnehmerTeilnehmerkosten = BigDecimal.ZERO;

        sumFKAuslaendischeTeilnehmerTeilnehmerkosten = sumFKAuslaendischeTeilnehmerTeilnehmerkosten.add(
                planungReisekostenPresenter.getView().getNfFahrtkostenFuerAuslTnProPerson().getValue());

        sumFKAuslaendischeTeilnehmerTeilnehmerkosten = sumFKAuslaendischeTeilnehmerTeilnehmerkosten.add(
                planungReisekostenPresenter.getView().getNfWeitereFahrtenImLandProPerson().getValue());
    }

    private void computeAuslaendischeTeilnehmerGesamtkosten() {
        sumFKAuslaendischeTeilnehmerGesamtkosten = BigDecimal.ZERO;
        sumFKAuslaendischeTeilnehmerGesamtkosten = sumFKZahlendeTeilnehmerTeilnehmerkosten.multiply(
                new BigDecimal(sumZahlendeNichtzahlendeTNAusl));
    }

    private void computeMassnahmeabhaengigeFahrtkostenTeilnehmerkosten() {
        sumFKMassnahmeabhaengigeFahrtkostenGesamtkosten = planungReisekostenPresenter.getView().getNfBustransfersGesamt().getValue();
        sumFKMassnahmeabhaengigeFahrtkostenTeilnehmerkosten = BigDecimal.ZERO;

        if (sumZahlendeTnDeAusl > 0) {
            sumFKMassnahmeabhaengigeFahrtkostenTeilnehmerkosten = sumFKMassnahmeabhaengigeFahrtkostenGesamtkosten.divide(
                    new BigDecimal(sumZahlendeTnDeAusl), BigDecimal.ROUND_HALF_UP);
        }
    }

    private void computeBegleitpersonenGesamtkosten() {
        sumFKBegleitpersonenGesamtkosten = BigDecimal.ZERO;

        // the formula of both is same.
        BigDecimal sumFahrt = sumFKZahlendeTeilnehmerTeilnehmerkosten;

        Integer begleitpersonenAnzahl =
                planungPersonPresenter.getView().getNfHauptberuflicheMitarbeiter().getValue() +
                planungPersonPresenter.getView().getNfHonorarMitarbeiter().getValue() +
                planungPersonPresenter.getView().getNfUnbezahlteMitarbeiter().getValue();

        sumFKBegleitpersonenGesamtkosten = sumFahrt.multiply(new BigDecimal(begleitpersonenAnzahl));

        sumFKBegleitpersonenGesamtkosten = sumFKBegleitpersonenGesamtkosten.add(
                planungPersonPresenter.getView().getNfZusaetzlicheReiseKosten().getValue());
    }

    private void computeBegleitpersonenTeilnehmerkosten() {
        sumFKBegleitpersonenTeilnehmerkosten = BigDecimal.ZERO;

        if (sumZahlendeTnDeAusl > 0) {
            sumFKBegleitpersonenTeilnehmerkosten = sumFKBegleitpersonenGesamtkosten.divide(
                    new BigDecimal(sumZahlendeTnDeAusl), BigDecimal.ROUND_HALF_UP);
        }
    }

    private void computeVisakostenTeilnehmerGesamtkosten() {
        sumFKVisakostenTeilnehmerGesamtkosten = BigDecimal.ZERO;
        sumFKVisakostenTeilnehmerTeilnehmerkosten = planungKostenPresenter.getView().getNfVisagebuehrProPerson().getValue();

        sumFKVisakostenTeilnehmerGesamtkosten = sumFKVisakostenTeilnehmerTeilnehmerkosten.multiply(
                new BigDecimal(sumZahlendeNichtzahlendeTNDe));
    }

    private void computeVisakostenBegleitpersonenGesamtkosten() {
        sumFKVisakostenBegleitpersonenGesamtkosten = BigDecimal.ZERO;
        sumFKVisakostenBegleitpersonenGesamtkosten = planungKostenPresenter.getView().getNfVisagebuehrProPerson().getValue().multiply(
                new BigDecimal(begleitpersonenDeOhneFremde));
    }

    private void computeVisakostenBegleitpersonenTeilnehmerkosten() {
        sumFKVisakostenBegleitpersonenTeilnehmerkosten = BigDecimal.ZERO;

        if (sumZahlendeTnDeAusl > 0) {
            sumFKVisakostenBegleitpersonenTeilnehmerkosten = sumFKVisakostenBegleitpersonenGesamtkosten.divide(
                    new BigDecimal(sumZahlendeTnDeAusl), BigDecimal.ROUND_HALF_UP);
        }
    }

    private void computeFahrtkostenGesamt() {
        sumFKFahrtkostenGesamtGesamtkosten = BigDecimal.ZERO;
        sumFKFahrtkostenGesamtTeilnehmerkosten = BigDecimal.ZERO;
        sumFKFahrtkostenGesamtProzent = BigDecimal.ZERO;

        sumFKFahrtkostenGesamtGesamtkosten = sumFKFahrtkostenGesamtGesamtkosten.add(sumFKZahlendeTeilnehmerGesamtkosten)
                .add(sumFKNichtzahlendeTeilnehmerGesamtkosten)
                .add(sumFKAuslaendischeTeilnehmerGesamtkosten)
                .add(sumFKMassnahmeabhaengigeFahrtkostenGesamtkosten)
                .add(sumFKBegleitpersonenGesamtkosten)
                .add(sumFKVisakostenTeilnehmerGesamtkosten)
                .add(sumFKVisakostenBegleitpersonenGesamtkosten);

        sumFKFahrtkostenGesamtTeilnehmerkosten = sumFKFahrtkostenGesamtTeilnehmerkosten.add(sumFKZahlendeTeilnehmerTeilnehmerkosten)
                .add(sumFKNichtzahlendeTeilnehmerTeilnehmerkosten)
                .add(sumFKAuslaendischeTeilnehmerTeilnehmerkosten)
                .add(sumFKMassnahmeabhaengigeFahrtkostenTeilnehmerkosten)
                .add(sumFKBegleitpersonenTeilnehmerkosten)
                .add(sumFKVisakostenTeilnehmerTeilnehmerkosten)
                .add(sumFKVisakostenBegleitpersonenTeilnehmerkosten);
    }

    // =====================================================
    // = 2. Unterkunft / Verpflegung compute methods -UV-  =
    // =====================================================

    private void computeUVZahlendeTeilnehmerGesamtkosten() {
        sumUVZahlendeTeilnehmerGesamtkosten = BigDecimal.ZERO;

        // de
        BigDecimal sumUebernachtungDe = planungReisekostenPresenter.getView().getNfUebernachtungskostenProNacht().getValue().multiply(
                new BigDecimal(planungReisekostenPresenter.getView().getNfAnzahlNaechteProDeTn().getValue()));

        BigDecimal sumVerpflegungDe = planungReisekostenPresenter.getView().getNfVerpflegungskostenProTag().getValue().multiply(
                planungReisekostenPresenter.getView().getNfVerpflegungstageProDeTn().getValue());

        BigDecimal sumUebernachtungDeUndVerpflegungDe = sumUebernachtungDe.add(sumVerpflegungDe);

        BigDecimal sumUVTeilnehmerDe = planungReisekostenPresenter.getView().getNfPauschalpreis().getValue().add(
                        (sumUebernachtungDeUndVerpflegungDe.multiply(
                                new BigDecimal(planungPersonPresenter.getView().getNfZahlendeTnDe().getValue()))));

        // ausl.
        BigDecimal sumUebernachtungAusl = planungReisekostenPresenter.getView().getNfUebernachtungskostenProNacht().getValue().multiply(
                new BigDecimal(planungReisekostenPresenter.getView().getNfAnzahlNaechteProAuslTn().getValue()));

        BigDecimal sumVerpflegungAusl = planungReisekostenPresenter.getView().getNfVerpflegungskostenProTag().getValue().multiply(
                planungReisekostenPresenter.getView().getNfVerpflegungstageProAuslTn().getValue());

        BigDecimal sumUebernachtungAuslUndVerpflegungAusl = sumUebernachtungAusl.add(sumVerpflegungAusl);

        BigDecimal sumUVTeilnehmerAusl = planungReisekostenPresenter.getView().getNfPauschalpreis().getValue().add(
                (sumUebernachtungAuslUndVerpflegungAusl.multiply(
                        new BigDecimal(planungPersonPresenter.getView().getNfZahlendeTnAusl().getValue()))));

        sumUVZahlendeTeilnehmerGesamtkosten = sumUVTeilnehmerDe.add(sumUVTeilnehmerAusl);
    }

    private void computeUVZahlendeTeilnehmerTeilnehmerkosten() {
        sumUVZahlendeTeilnehmerTeilnehmerkosten = BigDecimal.ZERO;

        if (sumZahlendeTnDeAusl > 0) {
            sumUVZahlendeTeilnehmerTeilnehmerkosten = sumUVZahlendeTeilnehmerGesamtkosten.divide(
                    new BigDecimal(sumZahlendeTnDeAusl), BigDecimal.ROUND_HALF_UP);
        }
    }

    private void computeUVNichtzahlendeTeilnehmerGesamtkosten() {
        sumUVNichtzahlendeTeilnehmerGesamtkosten = BigDecimal.ZERO;

        BigDecimal sumUebernachtungDe = planungReisekostenPresenter.getView().getNfUebernachtungskostenProNacht().getValue().multiply(
                new BigDecimal(planungReisekostenPresenter.getView().getNfAnzahlNaechteProDeTn().getValue()));

        BigDecimal sumVerpflegungDe = planungReisekostenPresenter.getView().getNfVerpflegungskostenProTag().getValue().multiply(
                planungReisekostenPresenter.getView().getNfVerpflegungstageProDeTn().getValue());

        sumVerpflegungDe = sumVerpflegungDe.add(planungReisekostenPresenter.getView().getNfPauschalpreis().getValue());

        BigDecimal sumUebernachtungDeAndVerpflegungDe = sumUebernachtungDe.add(sumVerpflegungDe);

        sumUVNichtzahlendeTeilnehmerGesamtkosten = sumUebernachtungDeAndVerpflegungDe.multiply(
                new BigDecimal(planungPersonPresenter.getView().getNfNichtZahlendeTnDe().getValue()));
    }

    private void computeUVNichtzahlendeTeilnehmerTeilnehmerkosten() {
        sumUVNichtzahlendeTeilnehmerTeilnehmerkosten = BigDecimal.ZERO;

        if (sumZahlendeTnDeAusl > 0) {
            sumUVNichtzahlendeTeilnehmerTeilnehmerkosten = sumUVNichtzahlendeTeilnehmerGesamtkosten.divide(
                    new BigDecimal(sumZahlendeTnDeAusl), BigDecimal.ROUND_HALF_UP);
        }
    }

    private void computeUVAuslaendischeTeilnehmerGesamtkosten() {
        sumUVAuslaendischeTeilnehmerGesamtkosten = BigDecimal.ZERO;

        BigDecimal sumUebernachtungAusl = planungReisekostenPresenter.getView().getNfUebernachtungskostenProNacht().getValue().multiply(
                new BigDecimal(planungReisekostenPresenter.getView().getNfAnzahlNaechteProAuslTn().getValue()));

        BigDecimal sumVerpflegungAusl = planungReisekostenPresenter.getView().getNfVerpflegungskostenProTag().getValue().multiply(
                planungReisekostenPresenter.getView().getNfVerpflegungstageProAuslTn().getValue());

        sumVerpflegungAusl = sumVerpflegungAusl.add(planungReisekostenPresenter.getView().getNfPauschalpreis().getValue());

        BigDecimal sumUebernachtungAuslUndVerpflegungAusl = sumUebernachtungAusl.add(sumVerpflegungAusl);

        sumUVAuslaendischeTeilnehmerGesamtkosten = sumUebernachtungAuslUndVerpflegungAusl.multiply(
                new BigDecimal(planungPersonPresenter.getView().getNfNichtZahlendeTnAusl().getValue()));
    }

    private void computeUVAuslaendischeTeilnehmerTeilnehmerkosten() {
        sumUVAuslaendischeTeilnehmerTeilnehmerkosten = BigDecimal.ZERO;

        if (sumZahlendeTnDeAusl > 0) {
            sumUVAuslaendischeTeilnehmerTeilnehmerkosten = sumUVAuslaendischeTeilnehmerGesamtkosten.divide(
                    new BigDecimal(sumZahlendeTnDeAusl), BigDecimal.ROUND_HALF_UP);
        }
    }

    private void computeUVBegleitpersonenGesamtkosten() {
        sumUVBegleitpersonenGesamtkosten = BigDecimal.ZERO;

        BigDecimal sumUnterkunftAusl =  planungReisekostenPresenter.getView().getNfUebernachtungskostenProNacht().getValue().multiply(
                new BigDecimal(planungPersonPresenter.getView().getNfAuslMitarbeiter().getValue() *
                        planungPersonPresenter.getView().getNfUnterkunfstage().getValue()));

        BigDecimal sumVerpflegungAusl = planungPersonPresenter.getView().getNfVerpflegungstage().getValue()
                .multiply(new BigDecimal(planungPersonPresenter.getView().getNfAuslMitarbeiter().getValue()))
                        .multiply(planungReisekostenPresenter.getView().getNfVerpflegungskostenProTag().getValue());

        BigDecimal sumUnterkunftAuslAndVerpflegungAusl = sumUnterkunftAusl.add(sumVerpflegungAusl);

        BigDecimal sumProTag = BigDecimal.ZERO;
        sumProTag = sumProTag.add(planungReisekostenPresenter.getView().getNfUebernachtungskostenProNacht().getValue())
                .add(planungReisekostenPresenter.getView().getNfVerpflegungskostenProTag().getValue())
                .multiply(new BigDecimal(Integer.valueOf(view.getLblVATage().getText())))
                .add(planungReisekostenPresenter.getView().getNfPauschalpreis().getValue());

        sumUVBegleitpersonenGesamtkosten = sumProTag.multiply(new BigDecimal(begleitpersonenDe)
                .add(planungPersonPresenter.getView().getNfZusaetzlicheVerpflegungskosten().getValue()));
    }

    private void computeUVBegleitpersonenTeilnehmerkosten() {
        sumUVBegleitpersonenTeilnehmerkosten = BigDecimal.ZERO;

        if (sumZahlendeTnDeAusl > 0) {
            sumUVBegleitpersonenTeilnehmerkosten = sumUVBegleitpersonenGesamtkosten.divide(
                    new BigDecimal(sumZahlendeTnDeAusl), BigDecimal.ROUND_HALF_UP);
        }
    }

    private void computeUVGesamt() {
        sumUVGesamtGesamtkosten = BigDecimal.ZERO;
        sumUVGesamtTeilnehmerkosten = BigDecimal.ZERO;
        sumUVGesamtProzent = BigDecimal.ZERO;

        sumUVGesamtGesamtkosten = sumUVGesamtGesamtkosten.add(sumUVZahlendeTeilnehmerGesamtkosten)
                .add(sumUVNichtzahlendeTeilnehmerGesamtkosten)
                .add(sumUVAuslaendischeTeilnehmerGesamtkosten)
                .add(sumUVBegleitpersonenGesamtkosten);

        sumUVGesamtTeilnehmerkosten = sumUVGesamtTeilnehmerkosten.add(sumUVZahlendeTeilnehmerTeilnehmerkosten)
                .add(sumUVNichtzahlendeTeilnehmerTeilnehmerkosten)
                .add(sumUVAuslaendischeTeilnehmerTeilnehmerkosten)
                .add(sumUVBegleitpersonenTeilnehmerkosten);
    }

    // ==========================================
    // = 3. Honorarkosten compute methods -HK-  =
    // ==========================================

    private void computeHKHonorareFuerIbbBegleitungGesamtkosten() {
        sumHKHonorareFuerIbbBegleitungGesamtkosten = BigDecimal.ZERO;
        BigDecimal honorareFuerIbbBegleitungVeranstaltung = planungParams.getHonorareFuerIbbBegleitung();

        sumHKHonorareFuerIbbBegleitungGesamtkosten = new BigDecimal(Integer.valueOf(view.getLblVATage().getText()))
                .multiply(honorareFuerIbbBegleitungVeranstaltung)
                .multiply(new BigDecimal(planungPersonPresenter.getView().getNfHonorarMitarbeiter().getValue()));
    }

    private void computeHKHonorareFuerIbbBegleitungTeilnehmerkosten() {
        sumHKHonorareFuerIbbBegleitungTeilnehmerkosten = BigDecimal.ZERO;

        if (sumZahlendeTnDeAusl > 0) {
            sumHKHonorareFuerIbbBegleitungTeilnehmerkosten = sumHKHonorareFuerIbbBegleitungGesamtkosten.divide(
                    new BigDecimal(sumZahlendeTnDeAusl), BigDecimal.ROUND_HALF_UP);
        }
    }

    private void computeHKHonorareFuerAuslaendischeBegleitungGesamtkosten() {
        sumHKHonorareFuerAuslaendischeBegleitungGesamtkosten = BigDecimal.ZERO;

        sumHKHonorareFuerAuslaendischeBegleitungGesamtkosten = new BigDecimal(
                planungPersonPresenter.getView().getNfAuslMitarbeiter().getValue())
                .multiply(new BigDecimal(planungPersonPresenter.getView().getNfHonorartage().getValue()))
                .multiply(planungPersonPresenter.getView().getNfHonorarsatz().getValue());
    }

    private void computeHKHonorareFuerAuslaendischeBegleitungTeilnehmerkosten() {
        sumHKHonorareFuerAuslaendischeBegleitungTeilnehmerkosten = BigDecimal.ZERO;

        if (sumZahlendeTnDeAusl > 0) {
            sumHKHonorareFuerAuslaendischeBegleitungTeilnehmerkosten = sumHKHonorareFuerAuslaendischeBegleitungGesamtkosten.divide(
                    new BigDecimal(sumZahlendeTnDeAusl), BigDecimal.ROUND_HALF_UP);
        }
    }

    private void computeHKHonorareFuerVisabeschaffungGesamtkosten() {
        sumHKHonorareFuerVisabeschaffungGesamtkosten = planungKostenPresenter.getView().getNfVisaNebenkosten().getValue();
    }

    private void computeHKHonorareFuerVisabeschaffungTeilnehmerkosten() {
        sumHKHonorareFuerVisabeschaffungTeilnehmerkosten = BigDecimal.ZERO;

        if (sumZahlendeTnDeAusl > 0) {
            sumHKHonorareFuerVisabeschaffungTeilnehmerkosten = sumHKHonorareFuerVisabeschaffungGesamtkosten.divide(
                    new BigDecimal(sumZahlendeTnDeAusl), BigDecimal.ROUND_HALF_UP);
        }
    }

    private void computeHKGesamt() {
        sumHKGesamtGesamtkosten = BigDecimal.ZERO;
        sumHKGesamtTeilnehmerkosten = BigDecimal.ZERO;
        sumHKGesamtProzent = BigDecimal.ZERO;

        sumHKGesamtGesamtkosten = sumHKGesamtGesamtkosten.add(sumHKHonorareFuerIbbBegleitungGesamtkosten)
                .add(sumHKHonorareFuerAuslaendischeBegleitungGesamtkosten)
                .add(sumHKHonorareFuerVisabeschaffungGesamtkosten);

        sumHKGesamtTeilnehmerkosten = sumHKGesamtTeilnehmerkosten.add(sumHKHonorareFuerIbbBegleitungTeilnehmerkosten)
                .add(sumHKHonorareFuerAuslaendischeBegleitungTeilnehmerkosten)
                .add(sumHKHonorareFuerVisabeschaffungTeilnehmerkosten);
   }

    // ==========================================
    // = 4. Programmkosten compute methods -PK- =
    // ==========================================

    private void computePKEintrittgelderDeutscheTNGesamtkosten() {
        sumPKEintrittgelderDeutscheTNTeilnehmerkosten = planungKostenPresenter.getView().getNfEintrittsgelderProDeTn().getValue();
        sumPKEintrittgelderDeutscheTNGesamtkosten = BigDecimal.ZERO;

        sumPKEintrittgelderDeutscheTNGesamtkosten = sumPKEintrittgelderDeutscheTNTeilnehmerkosten.multiply(
                new BigDecimal(planungPersonPresenter.getView().getNfZahlendeTnDe().getValue()));
    }

    private void computePKEintrittgelderAuslaendischeTNGesamtkosten() {
        sumPKEintrittgelderAuslTNTeilnehmerkosten = planungKostenPresenter.getView().getNfEintrittsgelderProAuslTn().getValue();
        sumPKEintrittgelderAuslTNGesamtkosten = BigDecimal.ZERO;

        sumPKEintrittgelderAuslTNGesamtkosten = sumPKEintrittgelderAuslTNTeilnehmerkosten.multiply(
                new BigDecimal(planungPersonPresenter.getView().getNfZahlendeTnAusl().getValue()).add(
                        new BigDecimal(planungPersonPresenter.getView().getNfNichtZahlendeTnAusl().getValue())));
    }

    private void computePKProgrammkostenGesamtkosten() {
        sumPKProgrammkostenGesamtkosten = BigDecimal.ZERO;

        sumPKProgrammkostenGesamtkosten = sumPKProgrammkostenGesamtkosten.add(
                planungKostenPresenter.getView().getNfFuehrungenGesamt().getValue()).add(
                planungKostenPresenter.getView().getNfExterneReferentGesamt().getValue()).add(
                planungKostenPresenter.getView().getNfExterneDolmetscherGesamt().getValue()).add(
                planungKostenPresenter.getView().getNfMaterialkostenGesamt().getValue());

        sumPKProgrammkostenGesamtkosten = sumPKProgrammkostenGesamtkosten.add(
                planungKostenPresenter.getView().getNfEintrittsgelderProDeTn().getValue().multiply(
                        new BigDecimal(planungPersonPresenter.getView().getNfNichtZahlendeTnDe().getValue())));

    }

    private void computePKProgrammkostenTeilnehmerkosten() {
        sumPKProgrammkostenTeilnehmerkosten = BigDecimal.ZERO;

        if (sumZahlendeTnDeAusl > 0) {
            sumPKProgrammkostenTeilnehmerkosten = sumPKProgrammkostenGesamtkosten.divide(
                    new BigDecimal(sumZahlendeTnDeAusl), BigDecimal.ROUND_HALF_UP);
        }

    }

    private void computePKGesamt() {
        sumPKGesamtGesamtkosten = BigDecimal.ZERO;
        sumPKGesamtTeilnehmerkosten = BigDecimal.ZERO;
        sumPKGesamtProzent = BigDecimal.ZERO;

        sumPKGesamtGesamtkosten = sumPKGesamtGesamtkosten.add(sumPKEintrittgelderDeutscheTNGesamtkosten)
                .add(sumPKEintrittgelderAuslTNGesamtkosten)
                .add(sumPKProgrammkostenGesamtkosten);

        sumPKGesamtTeilnehmerkosten = sumPKGesamtTeilnehmerkosten.add(sumPKEintrittgelderDeutscheTNTeilnehmerkosten)
                .add(sumPKEintrittgelderAuslTNTeilnehmerkosten)
                .add(sumPKProgrammkostenTeilnehmerkosten);
    }

    // ===========================================
    // = 5. Versicherungen compute methods -VER- =
    // ===========================================

    private void computeVERVersicherungDeutscheTNTeilnehmerkosten() {
        sumVERVersicherungDeutscheTNTeilnehmerkosten = BigDecimal.ZERO;
        Integer vaTage = Integer.valueOf(view.getLblVATage().getText());

        BigDecimal sumKombinierteHaftpflichtUndUnfallVersicherung = BigDecimal.ZERO;
        BigDecimal haftpflichtUnfallVersicherungProPersonTag = planungParams.getHaftpflichtUnfallVersicherungProPersonTag();

        BigDecimal sumRegressversicherung = BigDecimal.ZERO;
        BigDecimal sumReisepreissicherung = BigDecimal.ZERO;

        BigDecimal sumAuslandsreisekrankenversicherung = BigDecimal.ZERO;
        BigDecimal auslandsreisekrankenVersicherungProPersonTag = planungParams.getAuslandsreisekrankenVersicherungProPersonTag();

        if (zahlendeNichtzahlendeTNDe < 1) {
            return;
        }

        if (planungKostenPresenter.getView().getRbVeranstaltungInDe().isSelected()) {
            // veranstaltung is in deutschland

            // kombinierte haftpflicht und unfallVersicherung
            sumKombinierteHaftpflichtUndUnfallVersicherung = haftpflichtUnfallVersicherungProPersonTag
                    .multiply(new BigDecimal(vaTage));

            // reisepreissicherung
            if (planungPersonPresenter.getView().getNfZahlendeTnDe().getValue() >= 1) {
                sumReisepreissicherung = planungParams.getReisepreissicherungProPersonTag();
            }

            // regressversicherung
            if (planungKostenPresenter.getView().getChbRegressVersicherung().isSelected()) {
                if (vaTage > 0 || vaTage == 8) {
                    sumRegressversicherung = planungParams.getRegressversicherungVaInDeBis8Tage();
                } else if (vaTage > 8 || vaTage == 22) {
                    sumRegressversicherung = planungParams.getRegressversicherungVaInDeBis22Tage();
                } else if (vaTage > 22 || vaTage == 42) {
                    sumRegressversicherung = planungParams.getRegressversicherungVaInDeBis42Tage();
                }
            }

            sumVERVersicherungDeutscheTNTeilnehmerkosten = sumKombinierteHaftpflichtUndUnfallVersicherung
                    .add(sumReisepreissicherung)
                    .add(sumRegressversicherung);

        } else {
            // veranstaltung is im ausland

            // kombinierte haftpflicht und unfallVersicherung
            sumKombinierteHaftpflichtUndUnfallVersicherung = haftpflichtUnfallVersicherungProPersonTag
                    .multiply(new BigDecimal(vaTage));

            // auslandsreisekrankenversicherung
            sumAuslandsreisekrankenversicherung = auslandsreisekrankenVersicherungProPersonTag
                    .multiply(new BigDecimal(vaTage));

            // reisepreissicherung
            // TODO keine reisepreissicherung im Ausland?

            // regressversicherung
            if (planungKostenPresenter.getView().getChbRegressVersicherung().isSelected()) {
                if (vaTage > 0 || vaTage == 8) {
                    sumRegressversicherung = planungParams.getRegressversicherungVaImAuslandBis8Tage();
                } else if (vaTage > 8 || vaTage == 22) {
                    sumRegressversicherung = planungParams.getRegressversicherungVaImAuslandBis22Tage();
                } else if (vaTage > 22 || vaTage == 42) {
                    sumRegressversicherung = planungParams.getRegressversicherungVaImAuslandBis42Tage();
                }
            }

            sumVERVersicherungDeutscheTNTeilnehmerkosten = sumKombinierteHaftpflichtUndUnfallVersicherung
                    .add(sumAuslandsreisekrankenversicherung)
                    .add(sumRegressversicherung);
        }

    }

    private void computeVERVersicherungDeutscheTNGesamtkosten() {
        sumVERVersicherungDeutscheTNGesamtkosten = BigDecimal.ZERO;

        sumVERVersicherungDeutscheTNGesamtkosten = sumVERVersicherungDeutscheTNTeilnehmerkosten
                .multiply(new BigDecimal(zahlendeNichtzahlendeTNDe));
    }

    private void computeVERVersicherungAuslaendischeTNTeilnehmerkosten() {
        sumVERVersicherungAuslTNTeilnehmerkosten = BigDecimal.ZERO;
        Integer vaTage = Integer.valueOf(view.getLblVATage().getText());

        BigDecimal sumKombinierteHaftpflichtUndUnfallVersicherung = BigDecimal.ZERO;
        BigDecimal haftpflichtUnfallVersicherungProPersonTag = planungParams.getHaftpflichtUnfallVersicherungProPersonTag();

        BigDecimal sumRegressversicherung = BigDecimal.ZERO;
        BigDecimal sumReisepreissicherung = BigDecimal.ZERO;

        BigDecimal sumAuslandsreisekrankenversicherung = BigDecimal.ZERO;
        BigDecimal auslandsreisekrankenVersicherungProPersonTag = planungParams.getAuslandsreisekrankenVersicherungProPersonTag();

        if (zahlendeNichtzahlendeTNAusl < 1) {
            return;
        }

        if (planungKostenPresenter.getView().getRbVeranstaltungInDe().isSelected()) {
            // veranstaltung is in deutschland

            // kombinierte haftpflicht und unfallVersicherung
            sumKombinierteHaftpflichtUndUnfallVersicherung = haftpflichtUnfallVersicherungProPersonTag
                    .multiply(new BigDecimal(vaTage));

            // auslandsreisekrankenversicherung
            sumAuslandsreisekrankenversicherung = auslandsreisekrankenVersicherungProPersonTag
                    .multiply(new BigDecimal(vaTage));

            // reisepreissicherung
            if (planungPersonPresenter.getView().getNfZahlendeTnAusl().getValue() >= 1) {
                sumReisepreissicherung = planungParams.getReisepreissicherungProPersonTag();
            }

            // regressversicherung
            if (planungKostenPresenter.getView().getChbRegressVersicherung().isSelected()) {
                if (vaTage > 0 || vaTage == 8) {
                    sumRegressversicherung = planungParams.getRegressversicherungVaInDeBis8Tage();
                } else if (vaTage > 8 || vaTage == 22) {
                    sumRegressversicherung = planungParams.getRegressversicherungVaInDeBis22Tage();
                } else if (vaTage > 22 || vaTage == 42) {
                    sumRegressversicherung = planungParams.getRegressversicherungVaInDeBis42Tage();
                }
            }

            sumVERVersicherungAuslTNTeilnehmerkosten = sumKombinierteHaftpflichtUndUnfallVersicherung
                    .add(sumAuslandsreisekrankenversicherung)
                    .add(sumReisepreissicherung)
                    .add(sumRegressversicherung);

        } else {
            // veranstaltung is im ausland

            // kombinierte haftpflicht und unfallVersicherung
            sumKombinierteHaftpflichtUndUnfallVersicherung = haftpflichtUnfallVersicherungProPersonTag
                    .multiply(new BigDecimal(vaTage));

            // auslandsreisekrankenversicherung
            sumAuslandsreisekrankenversicherung = auslandsreisekrankenVersicherungProPersonTag
                    .multiply(new BigDecimal(vaTage));

            // reisepreissicherung
            // TODO keine reisepreissicherung im Ausland?

            // regressversicherung
            if (planungKostenPresenter.getView().getChbRegressVersicherung().isSelected()) {
                if (vaTage > 0 || vaTage == 8) {
                    sumRegressversicherung = planungParams.getRegressversicherungVaImAuslandBis8Tage();
                } else if (vaTage > 8 || vaTage == 22) {
                    sumRegressversicherung = planungParams.getRegressversicherungVaImAuslandBis22Tage();
                } else if (vaTage > 22 || vaTage == 42) {
                    sumRegressversicherung = planungParams.getRegressversicherungVaImAuslandBis42Tage();
                }
            }

            sumVERVersicherungAuslTNTeilnehmerkosten = sumKombinierteHaftpflichtUndUnfallVersicherung
                    .add(sumAuslandsreisekrankenversicherung)
                    .add(sumRegressversicherung);
        }

    }

    private void computeVERVersicherungAuslaendischeTNGesamtkosten() {
        sumVERVersicherungAuslTNGesamtkosten = BigDecimal.ZERO;

        sumVERVersicherungAuslTNGesamtkosten = sumVERVersicherungAuslTNTeilnehmerkosten
                .multiply(new BigDecimal(zahlendeNichtzahlendeTNAusl));
    }

    private void computeVERBegleitpersonenDeutscheTeilnehmerkosten() {
        sumVERBegleitpersonenDeutscheTeilnehmerkosten = BigDecimal.ZERO;
        Integer vaTage = Integer.valueOf(view.getLblVATage().getText());

        BigDecimal sumKombinierteHaftpflichtUndUnfallVersicherung = BigDecimal.ZERO;
        BigDecimal haftpflichtUnfallVersicherungProPersonTag = planungParams.getHaftpflichtUnfallVersicherungProPersonTag();

        BigDecimal sumRegressversicherung = BigDecimal.ZERO;
        BigDecimal sumReisepreissicherung = BigDecimal.ZERO;

        BigDecimal sumAuslandsreisekrankenversicherung = BigDecimal.ZERO;
        BigDecimal auslandsreisekrankenVersicherungProPersonTag = planungParams.getAuslandsreisekrankenVersicherungProPersonTag();

        if (begleitpersonenDeOhneFremde < 1) {
            return;
        }

        if (planungKostenPresenter.getView().getRbVeranstaltungInDe().isSelected()) {
            // veranstaltung is in deutschland

            // kombinierte haftpflicht und unfallVersicherung
            sumKombinierteHaftpflichtUndUnfallVersicherung = haftpflichtUnfallVersicherungProPersonTag
                    .multiply(new BigDecimal(vaTage));

            // reisepreissicherung
            if (planungPersonPresenter.getView().getNfZahlendeTnDe().getValue() >= 1) {
                sumReisepreissicherung = planungParams.getReisepreissicherungProPersonTag();
            }

            // regressversicherung
            if (planungKostenPresenter.getView().getChbRegressVersicherung().isSelected()) {
                if (vaTage > 0 || vaTage == 8) {
                    sumRegressversicherung = planungParams.getRegressversicherungVaInDeBis8Tage();
                } else if (vaTage > 8 || vaTage == 22) {
                    sumRegressversicherung = planungParams.getRegressversicherungVaInDeBis22Tage();
                } else if (vaTage > 22 || vaTage == 42) {
                    sumRegressversicherung = planungParams.getRegressversicherungVaInDeBis42Tage();
                }
            }

            sumVERBegleitpersonenDeutscheTeilnehmerkosten = sumKombinierteHaftpflichtUndUnfallVersicherung
                    .add(sumReisepreissicherung)
                    .add(sumRegressversicherung);

        } else {
            // veranstaltung is im ausland

            // kombinierte haftpflicht und unfallVersicherung
            sumKombinierteHaftpflichtUndUnfallVersicherung = haftpflichtUnfallVersicherungProPersonTag
                    .multiply(new BigDecimal(vaTage));

            // auslandsreisekrankenversicherung
            sumAuslandsreisekrankenversicherung = auslandsreisekrankenVersicherungProPersonTag
                    .multiply(new BigDecimal(vaTage));

            // reisepreissicherung
            // TODO keine reisepreissicherung im Ausland?

            // regressversicherung
            if (planungKostenPresenter.getView().getChbRegressVersicherung().isSelected()) {
                if (vaTage > 0 || vaTage == 8) {
                    sumRegressversicherung = planungParams.getRegressversicherungVaImAuslandBis8Tage();
                } else if (vaTage > 8 || vaTage == 22) {
                    sumRegressversicherung = planungParams.getRegressversicherungVaImAuslandBis22Tage();
                } else if (vaTage > 22 || vaTage == 42) {
                    sumRegressversicherung = planungParams.getRegressversicherungVaImAuslandBis42Tage();
                }
            }

            sumVERBegleitpersonenDeutscheTeilnehmerkosten = sumKombinierteHaftpflichtUndUnfallVersicherung
                    .add(sumAuslandsreisekrankenversicherung)
                    .add(sumRegressversicherung);
        }
    }

    private void computeVERBegleitpersonenDeutscheGesamtkosten() {
        sumVERBegleitpersonenDeutscheGesamtkosten = BigDecimal.ZERO;

        sumVERBegleitpersonenDeutscheGesamtkosten = sumVERBegleitpersonenDeutscheTeilnehmerkosten
                .multiply(new BigDecimal(begleitpersonenDeOhneFremde));
    }

    private void computeVERBegleitpersonenAuslTeilnehmerkosten() {
        sumVERBegleitpersonenAuslTeilnehmerkosten = BigDecimal.ZERO;
        Integer vaTage = Integer.valueOf(view.getLblVATage().getText());

        BigDecimal sumKombinierteHaftpflichtUndUnfallVersicherung = BigDecimal.ZERO;
        BigDecimal haftpflichtUnfallVersicherungProPersonTag = planungParams.getHaftpflichtUnfallVersicherungProPersonTag();

        BigDecimal sumRegressversicherung = BigDecimal.ZERO;
        BigDecimal sumReisepreissicherung = BigDecimal.ZERO;

        BigDecimal sumAuslandsreisekrankenversicherung = BigDecimal.ZERO;
        BigDecimal auslandsreisekrankenVersicherungProPersonTag = planungParams.getAuslandsreisekrankenVersicherungProPersonTag();

        Integer auslMitarbeiter = planungPersonPresenter.getView().getNfAuslMitarbeiter().getValue();

        if (auslMitarbeiter < 1) {
            return;
        }

        if (planungKostenPresenter.getView().getRbVeranstaltungInDe().isSelected()) {
            // veranstaltung is in deutschland

            // kombinierte haftpflicht und unfallVersicherung
            sumKombinierteHaftpflichtUndUnfallVersicherung = haftpflichtUnfallVersicherungProPersonTag
                    .multiply(new BigDecimal(vaTage));

            // auslandsreisekrankenversicherung
            sumAuslandsreisekrankenversicherung = auslandsreisekrankenVersicherungProPersonTag
                    .multiply(new BigDecimal(vaTage));

            // reisepreissicherung
            // TODO
            if (planungPersonPresenter.getView().getNfZahlendeTnAusl().getValue() >= 1) {
                sumReisepreissicherung = planungParams.getReisepreissicherungProPersonTag();
            }

            // regressversicherung
            if (planungKostenPresenter.getView().getChbRegressVersicherung().isSelected()) {
                if (vaTage > 0 || vaTage == 8) {
                    sumRegressversicherung = planungParams.getRegressversicherungVaInDeBis8Tage();
                } else if (vaTage > 8 || vaTage == 22) {
                    sumRegressversicherung = planungParams.getRegressversicherungVaInDeBis22Tage();
                } else if (vaTage > 22 || vaTage == 42) {
                    sumRegressversicherung = planungParams.getRegressversicherungVaInDeBis42Tage();
                }
            }

            sumVERBegleitpersonenAuslTeilnehmerkosten = sumKombinierteHaftpflichtUndUnfallVersicherung
                    .add(sumAuslandsreisekrankenversicherung)
                    .add(sumReisepreissicherung)
                    .add(sumRegressversicherung);

        } else {
            // veranstaltung is im ausland

            // kombinierte haftpflicht und unfallVersicherung
            sumKombinierteHaftpflichtUndUnfallVersicherung = haftpflichtUnfallVersicherungProPersonTag
                    .multiply(new BigDecimal(vaTage));

            // auslandsreisekrankenversicherung
            sumAuslandsreisekrankenversicherung = auslandsreisekrankenVersicherungProPersonTag
                    .multiply(new BigDecimal(vaTage));

            // reisepreissicherung
            // TODO keine reisepreissicherung im Ausland?

            // regressversicherung
            if (planungKostenPresenter.getView().getChbRegressVersicherung().isSelected()) {
                if (vaTage > 0 || vaTage == 8) {
                    sumRegressversicherung = planungParams.getRegressversicherungVaImAuslandBis8Tage();
                } else if (vaTage > 8 || vaTage == 22) {
                    sumRegressversicherung = planungParams.getRegressversicherungVaImAuslandBis22Tage();
                } else if (vaTage > 22 || vaTage == 42) {
                    sumRegressversicherung = planungParams.getRegressversicherungVaImAuslandBis42Tage();
                }
            }

            sumVERBegleitpersonenAuslTeilnehmerkosten = sumKombinierteHaftpflichtUndUnfallVersicherung
                    .add(sumAuslandsreisekrankenversicherung)
                    .add(sumRegressversicherung);
        }
    }

    private void computeVERBegleitpersonenAuslGesamtkosten() {
        sumVERBegleitpersonenAuslGesamtkosten = BigDecimal.ZERO;
        Integer auslMitarbeiter = planungPersonPresenter.getView().getNfAuslMitarbeiter().getValue();

        sumVERBegleitpersonenAuslGesamtkosten = sumVERBegleitpersonenAuslTeilnehmerkosten
                .multiply(new BigDecimal(auslMitarbeiter));
    }

    private void computeVERBegleitpersonenTeilnehmerkosten() {
        sumVERBegleitpersonenTeilnehmerkosten = BigDecimal.ZERO;

        sumVERBegleitpersonenTeilnehmerkosten = sumVERBegleitpersonenDeutscheTeilnehmerkosten
                .add(sumVERBegleitpersonenAuslTeilnehmerkosten);
    }

    private void computeVERBegleitpersonenGesamtkosten() {
        sumVERBegleitpersonenGesamtkosten = BigDecimal.ZERO;

        sumVERBegleitpersonenGesamtkosten = sumVERBegleitpersonenDeutscheGesamtkosten
                .add(sumVERBegleitpersonenAuslGesamtkosten);
    }

    private void computeVERGesamt() {
        sumVERGesamtGesamtkosten = BigDecimal.ZERO;
        sumVERGesamtTeilnehmerkosten = BigDecimal.ZERO;
        sumVERGesamtProzent = BigDecimal.ZERO;

        sumVERGesamtGesamtkosten = sumVERGesamtGesamtkosten.add(sumVERVersicherungDeutscheTNGesamtkosten)
                .add(sumVERVersicherungAuslTNGesamtkosten)
                .add(sumVERBegleitpersonenGesamtkosten);

        sumVERGesamtTeilnehmerkosten = sumVERGesamtTeilnehmerkosten.add(sumVERVersicherungDeutscheTNTeilnehmerkosten)
                .add(sumVERVersicherungAuslTNTeilnehmerkosten)
                .add(sumVERBegleitpersonenTeilnehmerkosten);
    }

    // ==============================================
    // = 6. IBB-Strukturkosten compute methods -SK- =
    // ==============================================

    private void computeSKStrukturkostenGesamtkosten() {
        sumSKStrukturkostenGesamtkosten = BigDecimal.ZERO;
        sumSKStrukturkostenProzent = BigDecimal.ZERO;
        BigDecimal ibbStrukturkostenProTNProTag = planungParams.getIbbStrukturkostenProTNProTag();

        sumSKStrukturkostenGesamtkosten = ibbStrukturkostenProTNProTag
                .multiply(new BigDecimal(Integer.valueOf(view.getLblVATage().getText())))
                .multiply(new BigDecimal(sumZahlendeTnDeAusl));
    }

    private void computeSKStrukturkostenTeilnehmerkosten() {
        sumSKStrukturkostenTeilnehmerkosten = BigDecimal.ZERO;
        BigDecimal ibbStrukturkostenProTNProTag = planungParams.getIbbStrukturkostenProTNProTag();

        sumSKStrukturkostenTeilnehmerkosten = ibbStrukturkostenProTNProTag
                .multiply(new BigDecimal(Integer.valueOf(view.getLblVATage().getText())));
    }

    // ==============================================
    // = Gesamtkosten der Massnahme                 =
    // ==============================================

    private void computeGesamtkostenDerMassnahmeGesamtkosten() {
        sumGesamtkostenDerMassnahmeGesamtkosten = BigDecimal.ZERO;
        sumGesamtkostenDerMassnahmeProzent = new BigDecimal(100);

        sumGesamtkostenDerMassnahmeGesamtkosten = sumGesamtkostenDerMassnahmeGesamtkosten
                .add(sumFKFahrtkostenGesamtGesamtkosten)
                .add(sumUVGesamtGesamtkosten)
                .add(sumHKGesamtGesamtkosten)
                .add(sumPKGesamtGesamtkosten)
                .add(sumVERGesamtGesamtkosten)
                .add(sumSKStrukturkostenGesamtkosten);

        // prozent berechnungen
        BigDecimal gesamtkostenDerMassnahmeGesamtkostenDivisor = sumGesamtkostenDerMassnahmeGesamtkosten.divide(
                new BigDecimal(100));

        if (gesamtkostenDerMassnahmeGesamtkostenDivisor.compareTo(BigDecimal.ZERO) == 1) {
            sumFKFahrtkostenGesamtProzent = sumFKFahrtkostenGesamtGesamtkosten.divide(
                    gesamtkostenDerMassnahmeGesamtkostenDivisor, BigDecimal.ROUND_HALF_UP);

            sumUVGesamtProzent = sumUVGesamtGesamtkosten.divide(
                    gesamtkostenDerMassnahmeGesamtkostenDivisor, BigDecimal.ROUND_HALF_UP);

            sumHKGesamtProzent = sumHKGesamtGesamtkosten.divide(
                    gesamtkostenDerMassnahmeGesamtkostenDivisor, BigDecimal.ROUND_HALF_UP);

            sumPKGesamtProzent = sumPKGesamtGesamtkosten.divide(
                    gesamtkostenDerMassnahmeGesamtkostenDivisor, BigDecimal.ROUND_HALF_UP);

            sumVERGesamtProzent = sumVERGesamtGesamtkosten.divide(
                    gesamtkostenDerMassnahmeGesamtkostenDivisor, BigDecimal.ROUND_HALF_UP);

            sumSKStrukturkostenProzent = sumSKStrukturkostenGesamtkosten.divide(
                    gesamtkostenDerMassnahmeGesamtkostenDivisor, BigDecimal.ROUND_HALF_UP);
        }
    }

    private void computeGesamtkostenDerMassnahmeTeilnehmerkosten() {
        sumGesamtkostenDerMassnahmeTeilnehmerkosten = BigDecimal.ZERO;

        sumGesamtkostenDerMassnahmeTeilnehmerkosten = sumGesamtkostenDerMassnahmeTeilnehmerkosten
                .add(sumFKFahrtkostenGesamtTeilnehmerkosten)
                .add(sumUVGesamtTeilnehmerkosten)
                .add(sumHKGesamtTeilnehmerkosten)
                .add(sumPKGesamtTeilnehmerkosten)
                .add(sumVERGesamtTeilnehmerkosten)
                .add(sumSKStrukturkostenTeilnehmerkosten);
    }

    // ==============================================
    // = Gesamtpreis der Massnahme                  =
    // ==============================================

    private void computeGesamtpreisDerMassnahmeGesamtkosten() {
        sumGesamtpreisDerMassnahmeGesamtkosten = BigDecimal.ZERO;
        sumGesamtpreisDerMassnahmeGesamtkosten = sumGesamtkostenDerMassnahmeGesamtkosten;
    }

    private void computeGesamtpreisDerMassnahmeTeilnehmerkosten() {
        sumGesamtpreisDerMassnahmeTeilnehmerkosten = BigDecimal.ZERO;
        sumGesamtpreisDerMassnahmeTeilnehmerkosten = sumGesamtkostenDerMassnahmeTeilnehmerkosten;
    }

    // ==============================================
    // Verkaufpreis & IBB-Erlös                     =
    // ==============================================

    private void computeVerkaufpreisTeilnehmerkosten() {
        sumVerkaufpreisTeilnehmerkosten = planungNamenPresenter.getView().getNfFestpreis().getValue();
    }

    private void computeVerkaufpreisGesamtkosten() {
        sumVerkaufpreisGesamtkosten = BigDecimal.ZERO;
        Integer zahlendeDeAuslAnzahl = planungPersonPresenter.getView().getNfZahlendeTnDe().getValue() +
                planungPersonPresenter.getView().getNfZahlendeTnAusl().getValue();

        sumVerkaufpreisGesamtkosten = sumVerkaufpreisTeilnehmerkosten
                .multiply(new BigDecimal(zahlendeDeAuslAnzahl));
    }

    private void computeIBBErloes() {
        sumIBBErloes = BigDecimal.ZERO;

        BigDecimal sumIBBErloesProzentTeil = sumGesamtpreisDerMassnahmeGesamtkosten
                .divide(new BigDecimal(100), BigDecimal.ROUND_HALF_UP)
                .multiply(planungParams.getIbbErloes());

        sumIBBErloes = sumIBBErloes.add(sumSKStrukturkostenGesamtkosten).add(sumIBBErloesProzentTeil);
    }

    @Override
    public void setRequestFocus() {
        planungRahmendatenPresenter.getView().getNfPlanungNr().requestFocus();
    }
    
    @Override
    public void popFields() {
        planungRahmendatenPresenter.popFields(entity);
        planungPersonPresenter.popFields(entity);
        planungKostenPresenter.popFields(entity);
        planungReisekostenPresenter.popFields(entity);
        planungZuschussPresenter.popFields(entity);
        planungNamenPresenter.popFields(entity);
        planungNotizenPresenter.popFields(entity);
    }
    
    @Override
    public void pushFields() {
        // save the planungparams
        planungParams = planungParamsService.createOrUpdate(planungParams);
        entity.setPlanungParams(planungParams);

        // computed values
        entity.setVaTage(Integer.valueOf(view.getLblVATage().getText()));

        planungRahmendatenPresenter.pushFields(entity);
        planungPersonPresenter.pushFields(entity);
        planungKostenPresenter.pushFields(entity);
        planungReisekostenPresenter.pushFields(entity);
        planungZuschussPresenter.pushFields(entity);
        planungNamenPresenter.pushFields(entity);
        planungNotizenPresenter.pushFields(entity);

        if (isNewModel()) {
            entity.setCreatedBy(IbbManagement.get().getLoggedUser().getId());
        } else {
            entity.setUpdatedBy(IbbManagement.get().getLoggedUser().getId());
        }
    }

    private void printMassnahmedatenblatt1(boolean preview) {
        // TODO checkbox utf-8 symbol not print?
        computeAll();
        HashMap<String, Object> mapParams = new HashMap<>();
        mapParams.put("zahlendeNichtzahlendeTNDe", zahlendeNichtzahlendeTNDe);
        mapParams.put("zahlendeNichtzahlendeTNAusl", zahlendeNichtzahlendeTNAusl);
        mapParams.put("begleitpersonen", begleitpersonen);
        mapParams.put("teilnehmerGesamt", teilnehmerGesamt);

        String  query = "SELECT p.planung_nr, p.zusatz, p.titel, p.veranstaltung_ort, p.veranstaltung_beginn, " +
                "p.veranstaltung_ende, g.abkuerzung as geschaeftsbereich, p.tage_nach_wbg, " +
                "f.abkuerzung as fachbereich, p.anmeldung_ibb, p.anmeldung_partner, p.vorbereitungstreffen_beginn, " +
                "p.vorbereitungstreffen_ende, p.vorbereitungstreffen_ort, p.nachbereitungstreffen_ort, " +
                "p.nachbereitungstreffen_beginn, p.nachbereitungstreffen_ende, " +
                "a1.nachname as mitarbeiter1_nachname, a1.kostenstelle as mitarbeiter1_kostenstelle, " +
                "a2.nachname as mitarbeiter2_nachname, a2.kostenstelle as mitarbeiter2_kostenstelle, " +
                "p.bedingung3, p.bedingung4, p.bedingung5, p.bedingung6, p.preis1, p.preis2, p.preis3, p.preis4, " +
                "p.preis5, p.preis6, kurzbeschreibung_eb_plan " +
                "FROM planung p " +
                "LEFT JOIN geschaeftsbereich g ON p.geschaeftsbereich_id = g.geschaeftsbereich_id " +
                "LEFT JOIN fachbereich f ON p.fachbereich_id = f.fachbereich_id " +
                "LEFT JOIN adresse a1 ON a1.adresse_id = p.mitarbeiter1_id " +
                "LEFT JOIN adresse a2 ON a2.adresse_id = p.mitarbeiter2_id " +
                "WHERE p.planung_id = '" + entity.getId() + "'";

        EntityManager entityManager =  service.getEntityManager();
        entityManager.getTransaction().begin();
        Connection connection = entityManager.unwrap(SessionImpl.class).connection();
        ReportHelpers.report(REPORT_MASSNAHMEDATENBLATT1, "Massnahmedatenblatt 1",
                query, preview, mapParams, connection);
        entityManager.getTransaction().commit();
    }

    private void printMassnahmekalkulation(boolean preview) {
        computeAll();
        HashMap<String, Object> mapParams = new HashMap<>();
        mapParams.put("begleitpersonenDe", begleitpersonenDe);

        // 1. fahrtkosten
        mapParams.put("sumFKZahlendeTeilnehmerGesamtkosten", sumFKZahlendeTeilnehmerGesamtkosten);
        mapParams.put("sumFKZahlendeTeilnehmerTeilnehmerkosten", sumFKZahlendeTeilnehmerTeilnehmerkosten);

        mapParams.put("sumFKNichtzahlendeTeilnehmerGesamtkosten", sumFKNichtzahlendeTeilnehmerGesamtkosten);
        mapParams.put("sumFKNichtzahlendeTeilnehmerTeilnehmerkosten", sumFKNichtzahlendeTeilnehmerTeilnehmerkosten);

        mapParams.put("sumFKAuslaendischeTeilnehmerGesamtkosten", sumFKAuslaendischeTeilnehmerGesamtkosten);
        mapParams.put("sumFKAuslaendischeTeilnehmerTeilnehmerkosten", sumFKAuslaendischeTeilnehmerTeilnehmerkosten);

        mapParams.put("sumFKMassnahmeabhaengigeFahrtkostenGesamtkosten", sumFKMassnahmeabhaengigeFahrtkostenGesamtkosten);
        mapParams.put("sumFKMassnahmeabhaengigeFahrtkostenTeilnehmerkosten", sumFKMassnahmeabhaengigeFahrtkostenTeilnehmerkosten);

        mapParams.put("sumFKBegleitpersonenGesamtkosten", sumFKBegleitpersonenGesamtkosten);
        mapParams.put("sumFKBegleitpersonenTeilnehmerkosten", sumFKBegleitpersonenTeilnehmerkosten);

        mapParams.put("sumFKVisakostenTeilnehmerGesamtkosten", sumFKVisakostenTeilnehmerGesamtkosten);
        mapParams.put("sumFKVisakostenTeilnehmerTeilnehmerkosten", sumFKVisakostenTeilnehmerTeilnehmerkosten);

        mapParams.put("sumFKVisakostenBegleitpersonenGesamtkosten", sumFKVisakostenBegleitpersonenGesamtkosten);
        mapParams.put("sumFKVisakostenBegleitpersonenTeilnehmerkosten", sumFKVisakostenBegleitpersonenTeilnehmerkosten);

        mapParams.put("sumFKFahrtkostenGesamtGesamtkosten", sumFKFahrtkostenGesamtGesamtkosten);
        mapParams.put("sumFKFahrtkostenGesamtTeilnehmerkosten", sumFKFahrtkostenGesamtTeilnehmerkosten);
        mapParams.put("sumFKFahrtkostenGesamtProzent", sumFKFahrtkostenGesamtProzent);

        // 2. unterkunft / verpflegung
        mapParams.put("sumUVZahlendeTeilnehmerGesamtkosten", sumUVZahlendeTeilnehmerGesamtkosten);
        mapParams.put("sumUVZahlendeTeilnehmerTeilnehmerkosten", sumUVZahlendeTeilnehmerTeilnehmerkosten);

        mapParams.put("sumUVNichtzahlendeTeilnehmerGesamtkosten", sumUVNichtzahlendeTeilnehmerGesamtkosten);
        mapParams.put("sumUVNichtzahlendeTeilnehmerTeilnehmerkosten", sumUVNichtzahlendeTeilnehmerTeilnehmerkosten);

        mapParams.put("sumUVAuslaendischeTeilnehmerGesamtkosten", sumUVAuslaendischeTeilnehmerGesamtkosten);
        mapParams.put("sumUVAuslaendischeTeilnehmerTeilnehmerkosten", sumUVAuslaendischeTeilnehmerTeilnehmerkosten);

        mapParams.put("sumUVBegleitpersonenGesamtkosten", sumUVBegleitpersonenGesamtkosten);
        mapParams.put("sumUVBegleitpersonenTeilnehmerkosten", sumUVBegleitpersonenTeilnehmerkosten);

        mapParams.put("sumUVGesamtGesamtkosten", sumUVGesamtGesamtkosten);
        mapParams.put("sumUVGesamtTeilnehmerkosten", sumUVGesamtTeilnehmerkosten);
        mapParams.put("sumUVGesamtProzent", sumUVGesamtProzent);

        // 3. honorarkosten
        mapParams.put("sumHKHonorareFuerIbbBegleitungGesamtkosten", sumHKHonorareFuerIbbBegleitungGesamtkosten);
        mapParams.put("sumHKHonorareFuerIbbBegleitungTeilnehmerkosten", sumHKHonorareFuerIbbBegleitungTeilnehmerkosten);

        mapParams.put("sumHKHonorareFuerAuslaendischeBegleitungGesamtkosten", sumHKHonorareFuerAuslaendischeBegleitungGesamtkosten);
        mapParams.put("sumHKHonorareFuerAuslaendischeBegleitungTeilnehmerkosten", sumHKHonorareFuerAuslaendischeBegleitungTeilnehmerkosten);

        mapParams.put("sumHKHonorareFuerVisabeschaffungGesamtkosten", sumHKHonorareFuerVisabeschaffungGesamtkosten);
        mapParams.put("sumHKHonorareFuerVisabeschaffungTeilnehmerkosten", sumHKHonorareFuerVisabeschaffungTeilnehmerkosten);

        mapParams.put("sumHKGesamtGesamtkosten", sumHKGesamtGesamtkosten);
        mapParams.put("sumHKGesamtTeilnehmerkosten", sumHKGesamtTeilnehmerkosten);
        mapParams.put("sumHKGesamtProzent", sumHKGesamtProzent);

        // 4. programmkosten
        mapParams.put("sumPKEintrittgelderDeutscheTNTeilnehmerkosten", sumPKEintrittgelderDeutscheTNTeilnehmerkosten);
        mapParams.put("sumPKEintrittgelderDeutscheTNGesamtkosten", sumPKEintrittgelderDeutscheTNGesamtkosten);

        mapParams.put("sumPKEintrittgelderAuslTNTeilnehmerkosten", sumPKEintrittgelderAuslTNTeilnehmerkosten);
        mapParams.put("sumPKEintrittgelderAuslTNGesamtkosten", sumPKEintrittgelderAuslTNGesamtkosten);

        mapParams.put("sumPKProgrammkostenGesamtkosten", sumPKProgrammkostenGesamtkosten);
        mapParams.put("sumPKProgrammkostenTeilnehmerkosten", sumPKProgrammkostenTeilnehmerkosten);

        mapParams.put("sumPKGesamtGesamtkosten", sumPKGesamtGesamtkosten);
        mapParams.put("sumPKGesamtTeilnehmerkosten", sumPKGesamtTeilnehmerkosten);
        mapParams.put("sumPKGesamtProzent", sumPKGesamtProzent);

        // 5. versicherungen
        mapParams.put("sumVERVersicherungDeutscheTNGesamtkosten", sumVERVersicherungDeutscheTNGesamtkosten);
        mapParams.put("sumVERVersicherungDeutscheTNTeilnehmerkosten", sumVERVersicherungDeutscheTNTeilnehmerkosten);

        mapParams.put("sumVERVersicherungAuslTNGesamtkosten", sumVERVersicherungAuslTNGesamtkosten);
        mapParams.put("sumVERVersicherungAuslTNTeilnehmerkosten", sumVERVersicherungAuslTNTeilnehmerkosten);

        mapParams.put("sumVERBegleitpersonenGesamtkosten", sumVERBegleitpersonenGesamtkosten);
        mapParams.put("sumVERBegleitpersonenTeilnehmerkosten", sumVERBegleitpersonenTeilnehmerkosten);

        mapParams.put("sumVERGesamtGesamtkosten", sumVERGesamtGesamtkosten);
        mapParams.put("sumVERGesamtTeilnehmerkosten", sumVERGesamtTeilnehmerkosten);
        mapParams.put("sumVERGesamtProzent", sumVERGesamtProzent);

        // 6. ibb-strukturkosten
        mapParams.put("sumSKStrukturkostenGesamtkosten", sumSKStrukturkostenGesamtkosten);
        mapParams.put("sumSKStrukturkostenTeilnehmerkosten", sumSKStrukturkostenTeilnehmerkosten);
        mapParams.put("sumSKStrukturkostenProzent", sumSKStrukturkostenProzent);

        // Gesamtkosten der Massnahme
        mapParams.put("sumGesamtkostenDerMassnahmeGesamtkosten", sumGesamtkostenDerMassnahmeGesamtkosten);
        mapParams.put("sumGesamtkostenDerMassnahmeTeilnehmerkosten", sumGesamtkostenDerMassnahmeTeilnehmerkosten);
        mapParams.put("sumGesamtkostenDerMassnahmeProzent", sumGesamtkostenDerMassnahmeProzent);

        // Gesamtpreis der Massnahme
        mapParams.put("sumGesamtpreisDerMassnahmeGesamtkosten", sumGesamtpreisDerMassnahmeGesamtkosten);
        mapParams.put("sumGesamtpreisDerMassnahmeTeilnehmerkosten", sumGesamtpreisDerMassnahmeTeilnehmerkosten);

        // Verkaufpreis & IBB-Erlös
        mapParams.put("sumVerkaufpreisGesamtkosten", sumVerkaufpreisGesamtkosten);
        mapParams.put("sumVerkaufpreisTeilnehmerkosten", sumVerkaufpreisTeilnehmerkosten);
        mapParams.put("sumIBBErloes", sumIBBErloes);

        String  query = "SELECT p.planung_nr, p.zusatz, p.titel, p.veranstaltung_ort, p.veranstaltung_beginn, " +
                "p.veranstaltung_ende, p.vorbereitungstreffen_ort, p.vorbereitungstreffen_beginn, " +
                "p.vorbereitungstreffen_ende, p.nachbereitungstreffen_ort, p.nachbereitungstreffen_beginn, " +
                "p.nachbereitungstreffen_ende, g.abkuerzung as geschaeftsbereich, p.tage_nach_wbg, p.va_tage, " +
                "p.zahlende_tn_de, p.zahlende_tn_ausl, p.nicht_zahlende_tn_de, p.nicht_zahlende_tn_ausl, " +
                "p.teilnehmer_nach_wgb_de, p.teilnehmer_nach_wgb_ausl, p.ausl_mitarbeiter " +
                "FROM planung p " +
                "LEFT JOIN geschaeftsbereich g ON p.geschaeftsbereich_id = g.geschaeftsbereich_id " +
                "WHERE p.planung_id = '" + entity.getId() + "'";

        EntityManager entityManager =  service.getEntityManager();
        entityManager.getTransaction().begin();
        Connection connection = entityManager.unwrap(SessionImpl.class).connection();
        ReportHelpers.report(REPORT_MASSNAHMEKALKULATION, "IBB-Massnahmekalkulation",
                query, preview, mapParams, connection);
        entityManager.getTransaction().commit();
    }

    @Override
    public boolean isFormValid() {
        if (planungRahmendatenPresenter.getView().getValidationSupport().isInvalid()) {
            Notifications.create()
                    .text(I18n.COMMON.getString("notification.saveValidationError"))
                    .position(Pos.TOP_RIGHT).showWarning();
        }

        return !planungRahmendatenPresenter.getView().getValidationSupport().isInvalid();
    }

    @Override
    public AppPlaces getPlaceName() {
        return AppPlaces.PLANUNG_FORM;
    }

    @Override
    public void onBack() {
        navigationManager.goTo(new Place(AppPlaces.PLANUNG));
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
