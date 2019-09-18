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
import com.lagossoftware.ibbfx.entity.Planung;
import com.lagossoftware.lagosfx.mvp.AbstractPresenter;
import com.lagossoftware.lagosfx.navigation.NavigationManager;
import javafx.scene.layout.BorderPane;

import java.math.BigDecimal;

/**
 * Planung Reisekosten presenter
 *
 * @author Cem Ikta
 */
public class PlanungReisekostenPresenter extends AbstractPresenter<PlanungReisekostenView> {

    @Inject
    public PlanungReisekostenPresenter(final NavigationManager navigationManager,
                                       final PlanungReisekostenView view) {
        super(navigationManager, view);
    }

    @Override
    public void start(BorderPane container) {
        bind();
    }

    @Override
    public void bind() {
    }

    public void popFields(Planung entity) {
        view.getNfBahnfahrtProPerson().setValue(entity.getBahnfahrtProPerson() != null ?
                entity.getBahnfahrtProPerson() : BigDecimal.ZERO);
        view.getNfFlugkostenProPerson().setValue(entity.getFlugkostenProPerson() != null ?
                entity.getFlugkostenProPerson() : BigDecimal.ZERO);
        view.getNfBustransfersGesamt().setValue(entity.getBustransfersGesamt() != null ?
                entity.getBustransfersGesamt() : BigDecimal.ZERO);
        view.getNfWeitereFahrtenImLandProPerson().setValue(entity.getWeitereFahrtenImLandProPerson() != null ?
                entity.getWeitereFahrtenImLandProPerson() : BigDecimal.ZERO);
        view.getNfFahrtkostenFuerAuslTnProPerson().setValue(entity.getFahrtkostenFuerAuslTnProPerson() != null ?
                entity.getFahrtkostenFuerAuslTnProPerson() : BigDecimal.ZERO);

        view.getNfUebernachtungskostenProNacht().setValue(entity.getUebernachtungskostenProNacht() != null ?
                entity.getUebernachtungskostenProNacht() : BigDecimal.ZERO);
        view.getNfAufpreisEinzelzimmerProNacht().setValue(entity.getAufpreisEinzelzimmerProNacht() != null ?
                entity.getAufpreisEinzelzimmerProNacht() : BigDecimal.ZERO);
        view.getNfAnzahlNaechteProDeTn().setValue(entity.getAnzahlNaechteProDeTn() != null ?
                entity.getAnzahlNaechteProDeTn() : 0);
        view.getNfAnzahlNaechteProAuslTn().setValue(entity.getAnzahlNaechteProAuslTn() != null ?
                entity.getAnzahlNaechteProAuslTn() : 0);
        view.getNfVerpflegungskostenProTag().setValue(entity.getVerpflegungskostenProTag() != null ?
                entity.getVerpflegungskostenProTag() : BigDecimal.ZERO);
        view.getNfVerpflegungstageProDeTn().setValue(entity.getVerpflegungstageProDeTn() != null ?
                entity.getVerpflegungstageProDeTn() : BigDecimal.ZERO);
        view.getNfVerpflegungstageProAuslTn().setValue(entity.getVerpflegungstageProAuslTn() != null ?
                entity.getVerpflegungstageProAuslTn() : BigDecimal.ZERO);
        view.getNfPauschalpreis().setValue(entity.getPauschalpreis() != null ?
                entity.getPauschalpreis() : BigDecimal.ZERO);
    }

    public void pushFields(Planung entity) {
        entity.setBahnfahrtProPerson(view.getNfBahnfahrtProPerson().getValue());
        entity.setFlugkostenProPerson(view.getNfFlugkostenProPerson().getValue());
        entity.setBustransfersGesamt(view.getNfBustransfersGesamt().getValue());
        entity.setWeitereFahrtenImLandProPerson(view.getNfWeitereFahrtenImLandProPerson().getValue());
        entity.setFahrtkostenFuerAuslTnProPerson(view.getNfFahrtkostenFuerAuslTnProPerson().getValue());

        entity.setUebernachtungskostenProNacht(view.getNfUebernachtungskostenProNacht().getValue());
        entity.setAufpreisEinzelzimmerProNacht(view.getNfAufpreisEinzelzimmerProNacht().getValue());
        entity.setAnzahlNaechteProDeTn(view.getNfAnzahlNaechteProDeTn().getValue());
        entity.setAnzahlNaechteProAuslTn(view.getNfAnzahlNaechteProAuslTn().getValue());
        entity.setVerpflegungskostenProTag(view.getNfVerpflegungskostenProTag().getValue());
        entity.setVerpflegungstageProDeTn(view.getNfVerpflegungstageProDeTn().getValue());
        entity.setVerpflegungstageProAuslTn(view.getNfVerpflegungstageProAuslTn().getValue());
        entity.setPauschalpreis(view.getNfPauschalpreis().getValue());
    }

}
