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
 * Planung Personen presenter
 *
 * @author Cem Ikta
 */
public class PlanungPersonPresenter extends AbstractPresenter<PlanungPersonView> {

    @Inject
    public PlanungPersonPresenter(final NavigationManager navigationManager,
                                  final PlanungPersonView view) {
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
        view.getNfZahlendeTnDe().setValue(entity.getZahlendeTnDe() != null ?
                entity.getZahlendeTnDe() : 0);
        view.getNfZahlendeTnAusl().setValue(entity.getZahlendeTnAusl() != null ?
                entity.getZahlendeTnAusl() : 0);
        view.getNfNichtZahlendeTnDe().setValue(entity.getNichtZahlendeTnDe() != null ?
                entity.getNichtZahlendeTnDe() : 0);
        view.getNfNichtZahlendeTnAusl().setValue(entity.getNichtZahlendeTnAusl() != null ?
                entity.getNichtZahlendeTnAusl() : 0);
        view.getNfTeilnehmerNachWgbDe().setValue(entity.getTeilnehmerNachWgbDe() != null ?
                entity.getTeilnehmerNachWgbDe() : 0);
        view.getNfTeilnehmerNachWgbAusl().setValue(entity.getTeilnehmerNachWgbAusl() != null ?
                entity.getTeilnehmerNachWgbAusl() : 0);

        view.getNfZusaetzlicheReiseKosten().setValue(entity.getZusaetzlicheReiseKosten() != null ?
                entity.getZusaetzlicheReiseKosten() : BigDecimal.ZERO);
        view.getNfZusaetzlicheVerpflegungskosten().setValue(entity.getZusaetzlicheVerpflegungskosten() != null ?
                entity.getZusaetzlicheVerpflegungskosten() : BigDecimal.ZERO);
        view.getNfWeitereAusgaben().setValue(entity.getWeitereAusgaben() != null ?
                entity.getWeitereAusgaben() : BigDecimal.ZERO);

        view.getNfHauptberuflicheMitarbeiter().setValue(entity.getHauptberuflicheMitarbeiter() != null ?
                entity.getHauptberuflicheMitarbeiter() : 0);
        view.getNfHonorarMitarbeiter().setValue(entity.getHonorarMitarbeiter() != null ?
                entity.getHonorarMitarbeiter() : 0);
        view.getNfUnbezahlteMitarbeiter().setValue(entity.getUnbezahlteMitarbeiter() != null ?
                entity.getUnbezahlteMitarbeiter() : 0);
        view.getNfFremdeMitarbeiter().setValue(entity.getFremdeMitarbeiter() != null ?
                entity.getFremdeMitarbeiter() : 0);

        view.getNfAuslMitarbeiter().setValue(entity.getAuslMitarbeiter() != null ?
                entity.getAuslMitarbeiter() : 0);
        view.getNfUnterkunfstage().setValue(entity.getUnterkunfstage() != null ?
                entity.getUnterkunfstage() : 0);
        view.getNfVerpflegungstage().setValue(entity.getVerpflegungstage() != null ?
                entity.getVerpflegungstage() : BigDecimal.ZERO);
        view.getNfHonorartage().setValue(entity.getHonorartage() != null ?
                entity.getHonorartage() : 0);
        view.getNfHonorarsatz().setValue(entity.getHonorarsatz() != null ?
                entity.getHonorarsatz() : BigDecimal.ZERO);
        view.getNfReisekosten().setValue(entity.getReisekosten() != null ?
                entity.getReisekosten() : BigDecimal.ZERO);
    }

    public void pushFields(Planung entity) {
        entity.setZahlendeTnDe(view.getNfZahlendeTnDe().getValue());
        entity.setZahlendeTnAusl(view.getNfZahlendeTnAusl().getValue());
        entity.setNichtZahlendeTnDe(view.getNfNichtZahlendeTnDe().getValue());
        entity.setNichtZahlendeTnAusl(view.getNfNichtZahlendeTnAusl().getValue());
        entity.setTeilnehmerNachWgbDe(view.getNfTeilnehmerNachWgbDe().getValue());
        entity.setTeilnehmerNachWgbAusl(view.getNfTeilnehmerNachWgbAusl().getValue());

        entity.setZusaetzlicheReiseKosten(view.getNfZusaetzlicheReiseKosten().getValue());
        entity.setZusaetzlicheVerpflegungskosten(view.getNfZusaetzlicheVerpflegungskosten().getValue());
        entity.setWeitereAusgaben(view.getNfWeitereAusgaben().getValue());

        entity.setHauptberuflicheMitarbeiter(view.getNfHauptberuflicheMitarbeiter().getValue());
        entity.setHonorarMitarbeiter(view.getNfHonorarMitarbeiter().getValue());
        entity.setUnbezahlteMitarbeiter(view.getNfUnbezahlteMitarbeiter().getValue());
        entity.setFremdeMitarbeiter(view.getNfFremdeMitarbeiter().getValue());

        entity.setAuslMitarbeiter(view.getNfAuslMitarbeiter().getValue());
        entity.setUnterkunfstage(view.getNfUnterkunfstage().getValue());
        entity.setVerpflegungstage(view.getNfVerpflegungstage().getValue());
        entity.setHonorartage(view.getNfHonorartage().getValue());
        entity.setHonorarsatz(view.getNfHonorarsatz().getValue());
        entity.setReisekosten(view.getNfReisekosten().getValue());
    }

}
