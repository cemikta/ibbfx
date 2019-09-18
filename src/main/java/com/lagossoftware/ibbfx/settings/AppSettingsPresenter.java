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
package com.lagossoftware.ibbfx.settings;

import com.google.inject.Inject;
import com.lagossoftware.ibbfx.IbbManagement;
import com.lagossoftware.ibbfx.app.I18n;
import com.lagossoftware.ibbfx.entity.Params;
import com.lagossoftware.ibbfx.service.ParamsService;
import com.lagossoftware.lagosfx.control.MessageBox;
import com.lagossoftware.lagosfx.control.NumberField;
import com.lagossoftware.lagosfx.mvp.AbstractPresenter;
import com.lagossoftware.lagosfx.navigation.NavigationManager;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import org.controlsfx.control.Notifications;

import java.math.BigDecimal;

/**
 * App settings presenter
 *
 * @author Cem Ikta
 */
public class AppSettingsPresenter extends AbstractPresenter<AppSettingsView> {

    private Params entity;
    private ParamsService paramsService;

    @Inject
    public AppSettingsPresenter(final NavigationManager navigationManager, final AppSettingsView view,
                                final ParamsService paramsService) {
        super(navigationManager, view);

        this.paramsService = paramsService;
    }

    @Override
    public void start(BorderPane container) {
        container.setCenter(getView().asNode());
        bind();
        entity = paramsService.find(1L);
        popFields();
        navigationManager.setCurrentPageTitle(view.getHeaderTitle());
    }

    @Override
    public void bind() {
        view.getBtnSave().setOnAction((ActionEvent event) -> {
            onSave();
        });
    }

    public void onSave() {
        if (isFormValid()) {
            pushFields();

            try {
                paramsService.update(entity);
                Notifications.create()
                        .text(I18n.COMMON.getString("notification.saveOk"))
                        .position(Pos.TOP_RIGHT).showInformation();
            } catch (Exception ex) {
                MessageBox.get()
                        .contentText(I18n.COMMON.getString("notification.saveException"))
                        .showError(ex);
            }
        }
    }

    public void popFields() {
        // nummernkreis tab
        view.getNfLastAdresseNr().setValue(entity.getAdresseNr() != null ?
                entity.getAdresseNr().intValue() : 0);
        view.getNfLastEingangsrechnungBelegNr().setValue(entity.getEingangsrechnungBelegNr() != null ?
                entity.getEingangsrechnungBelegNr().intValue() : 0);
        view.getNfLastFahrtkasseNr().setValue(entity.getFahrtkasseNr() != null ?
                entity.getFahrtkasseNr().intValue() : 0);

        // planung tab
        view.getNfHonorareFuerIbbBegleitung().setValue(entity.getHonorareFuerIbbBegleitung() != null ?
                entity.getHonorareFuerIbbBegleitung() : BigDecimal.ZERO);
        view.getNfIbbStrukturkostenProTNProTag().setValue(entity.getIbbStrukturkostenProTNProTag() != null ?
                entity.getIbbStrukturkostenProTNProTag() : BigDecimal.ZERO);
        view.getNfIbbErloes().setValue(entity.getIbbErloes() != null ?
                entity.getIbbErloes() : BigDecimal.ZERO);
        // versicherung
        view.getNfHaftpflichtUnfallVersicherungProPersonTag().setValue(entity.getHaftpflichtUnfallVersicherungProPersonTag() != null ?
                entity.getHaftpflichtUnfallVersicherungProPersonTag() : BigDecimal.ZERO);

        view.getNfAuslandsreisekrankenVersicherungProPersonTag().setValue(entity.getAuslandsreisekrankenVersicherungProPersonTag() != null ?
                entity.getAuslandsreisekrankenVersicherungProPersonTag() : BigDecimal.ZERO);

        view.getNfKrankenversicherungFuerAuslTNProPersonTag().setValue(entity.getKrankenversicherungFuerAuslTNProPersonTag() != null ?
                entity.getKrankenversicherungFuerAuslTNProPersonTag() : BigDecimal.ZERO);

        view.getNfRegressversicherungVaInDeBis8Tage().setValue(entity.getRegressversicherungVaInDeBis8Tage() != null ?
                entity.getRegressversicherungVaInDeBis8Tage() : BigDecimal.ZERO);

        view.getNfRegressversicherungVaInDeBis22Tage().setValue(entity.getRegressversicherungVaInDeBis22Tage() != null ?
                entity.getRegressversicherungVaInDeBis22Tage() : BigDecimal.ZERO);

        view.getNfRegressversicherungVaInDeBis42Tage().setValue(entity.getRegressversicherungVaInDeBis42Tage() != null ?
                entity.getRegressversicherungVaInDeBis42Tage() : BigDecimal.ZERO);

        view.getNfRegressversicherungVaImAuslandBis8Tage().setValue(entity.getRegressversicherungVaImAuslandBis8Tage() != null ?
                entity.getRegressversicherungVaImAuslandBis8Tage() : BigDecimal.ZERO);

        view.getNfRegressversicherungVaImAuslandBis22Tage().setValue(entity.getRegressversicherungVaImAuslandBis22Tage() != null ?
                entity.getRegressversicherungVaImAuslandBis22Tage() : BigDecimal.ZERO);

        view.getNfRegressversicherungVaImAuslandBis42Tage().setValue(entity.getRegressversicherungVaImAuslandBis42Tage() != null ?
                entity.getRegressversicherungVaImAuslandBis42Tage() : BigDecimal.ZERO);

        view.getNfRechtschutzversicherungVaBis8Tage().setValue(entity.getRechtschutzversicherungVaBis8Tage() != null ?
                entity.getRechtschutzversicherungVaBis8Tage() : BigDecimal.ZERO);

        view.getNfRechtschutzversicherungVaBis14Tage().setValue(entity.getRechtschutzversicherungVaBis14Tage() != null ?
                entity.getRechtschutzversicherungVaBis14Tage() : BigDecimal.ZERO);

        view.getNfRechtschutzversicherungVaBis22Tage().setValue(entity.getRechtschutzversicherungVaBis22Tage() != null ?
                entity.getRechtschutzversicherungVaBis22Tage() : BigDecimal.ZERO);

        view.getNfReisepreissicherungProPersonTag().setValue(entity.getReisepreissicherungProPersonTag() != null ?
                entity.getReisepreissicherungProPersonTag() : BigDecimal.ZERO);
    }

    public void pushFields() {
        // nummernkreis tab
        entity.setAdresseNr(view.getNfLastAdresseNr().getValue().longValue());
        entity.setEingangsrechnungBelegNr(view.getNfLastEingangsrechnungBelegNr().getValue().longValue());
        entity.setFahrtkasseNr(view.getNfLastFahrtkasseNr().getValue().longValue());

        // planung tab
        entity.setHonorareFuerIbbBegleitung(view.getNfHonorareFuerIbbBegleitung().getValue());
        entity.setIbbStrukturkostenProTNProTag(view.getNfIbbStrukturkostenProTNProTag().getValue());
        entity.setIbbErloes(view.getNfIbbErloes().getValue());
        // versicherung
        entity.setHaftpflichtUnfallVersicherungProPersonTag(view.getNfHaftpflichtUnfallVersicherungProPersonTag().getValue());
        entity.setAuslandsreisekrankenVersicherungProPersonTag(view.getNfAuslandsreisekrankenVersicherungProPersonTag().getValue());
        entity.setKrankenversicherungFuerAuslTNProPersonTag(view.getNfKrankenversicherungFuerAuslTNProPersonTag().getValue());
        entity.setRegressversicherungVaInDeBis8Tage(view.getNfRegressversicherungVaInDeBis8Tage().getValue());
        entity.setRegressversicherungVaInDeBis22Tage(view.getNfRegressversicherungVaInDeBis22Tage().getValue());
        entity.setRegressversicherungVaInDeBis42Tage(view.getNfRegressversicherungVaInDeBis42Tage().getValue());
        entity.setRegressversicherungVaImAuslandBis8Tage(view.getNfRegressversicherungVaImAuslandBis8Tage().getValue());
        entity.setRegressversicherungVaImAuslandBis22Tage(view.getNfRegressversicherungVaImAuslandBis22Tage().getValue());
        entity.setRegressversicherungVaImAuslandBis42Tage(view.getNfRegressversicherungVaImAuslandBis42Tage().getValue());
        entity.setRechtschutzversicherungVaBis8Tage(view.getNfRechtschutzversicherungVaBis8Tage().getValue());
        entity.setRechtschutzversicherungVaBis14Tage(view.getNfRechtschutzversicherungVaBis14Tage().getValue());
        entity.setRechtschutzversicherungVaBis22Tage(view.getNfRechtschutzversicherungVaBis22Tage().getValue());
        entity.setReisepreissicherungProPersonTag(view.getNfReisepreissicherungProPersonTag().getValue());

        entity.setUpdatedBy(IbbManagement.get().getLoggedUser().getId());
    }

    public boolean isFormValid() {
        if (view.getValidationSupport().isInvalid()) {
            Notifications.create()
                    .text(I18n.COMMON.getString("notification.saveValidationError"))
                    .position(Pos.TOP_RIGHT).showWarning();
        }

        return !view.getValidationSupport().isInvalid();
    }

}
