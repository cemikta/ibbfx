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
 * Planung Kosten presenter
 *
 * @author Cem Ikta
 */
public class PlanungKostenPresenter extends AbstractPresenter<PlanungKostenView> {

    @Inject
    public PlanungKostenPresenter(final NavigationManager navigationManager, final PlanungKostenView view) {
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
        if (entity.getVeranstaltungInDe() != null) {
            if (entity.getVeranstaltungInDe()) {
                view.getRbVeranstaltungInDe().setSelected(true);
            } else {
                view.getRbVeranstaltungImAusland().setSelected(true);
            }
        }

        view.getChbRegressVersicherung().setSelected(entity.getRegressVersicherung() != null ?
                entity.getRegressVersicherung() : false);
        view.getChbReiserecht().setSelected(entity.getReiserecht() != null ? entity.getReiserecht() : false);

        view.getNfFuehrungenGesamt().setValue(entity.getFuehrungenGesamt() != null ?
                entity.getFuehrungenGesamt() : BigDecimal.ZERO);
        view.getNfExterneReferentGesamt().setValue(entity.getExterneReferentGesamt() != null ?
                entity.getExterneReferentGesamt() : BigDecimal.ZERO);
        view.getNfExterneDolmetscherGesamt().setValue(entity.getExterneDolmetscherGesamt() != null ?
                entity.getExterneDolmetscherGesamt() : BigDecimal.ZERO);
        view.getNfMaterialkostenGesamt().setValue(entity.getMaterialkostenGesamt() != null ?
                entity.getMaterialkostenGesamt() : BigDecimal.ZERO);
        view.getNfEintrittsgelderProDeTn().setValue(entity.getEintrittsgelderProDeTn() != null ?
                entity.getEintrittsgelderProDeTn() : BigDecimal.ZERO);
        view.getNfEintrittsgelderProAuslTn().setValue(entity.getEintrittsgelderProAuslTn() != null ?
                entity.getEintrittsgelderProAuslTn() : BigDecimal.ZERO);

        view.getNfVisagebuehrProPerson().setValue(entity.getVisagebuehrProPerson() != null ?
                entity.getVisagebuehrProPerson() : BigDecimal.ZERO);
        view.getNfVisaNebenkosten().setValue(entity.getVisaNebenkosten() != null ?
                entity.getVisaNebenkosten() : BigDecimal.ZERO);

        view.getNfSonstigekostenGesamt().setValue(entity.getSonstigekostenGesamt() != null ?
                entity.getSonstigekostenGesamt() : BigDecimal.ZERO);
        view.getNfSonstigekostenProTn().setValue(entity.getSonstigekostenProTn() != null ?
                entity.getSonstigekostenProTn() : BigDecimal.ZERO);
    }

    public void pushFields(Planung entity) {
        entity.setVeranstaltungInDe(view.getRbVeranstaltungInDe().isSelected());
        entity.setRegressVersicherung(view.getChbRegressVersicherung().isSelected());
        entity.setReiserecht(view.getChbReiserecht().isSelected());

        entity.setFuehrungenGesamt(view.getNfFuehrungenGesamt().getValue());
        entity.setExterneReferentGesamt(view.getNfExterneReferentGesamt().getValue());
        entity.setExterneDolmetscherGesamt(view.getNfExterneDolmetscherGesamt().getValue());
        entity.setMaterialkostenGesamt(view.getNfMaterialkostenGesamt().getValue());
        entity.setEintrittsgelderProDeTn(view.getNfEintrittsgelderProDeTn().getValue());
        entity.setEintrittsgelderProAuslTn(view.getNfEintrittsgelderProAuslTn().getValue());

        entity.setVisagebuehrProPerson(view.getNfVisagebuehrProPerson().getValue());
        entity.setVisaNebenkosten(view.getNfVisaNebenkosten().getValue());

        entity.setSonstigekostenGesamt(view.getNfSonstigekostenGesamt().getValue());
        entity.setSonstigekostenProTn(view.getNfSonstigekostenProTn().getValue());
    }

}
