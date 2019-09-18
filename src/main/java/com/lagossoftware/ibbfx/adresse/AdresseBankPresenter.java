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
package com.lagossoftware.ibbfx.adresse;

import com.google.inject.Inject;
import com.lagossoftware.ibbfx.entity.Adresse;
import com.lagossoftware.lagosfx.common.DateHelpers;
import com.lagossoftware.lagosfx.mvp.AbstractPresenter;
import com.lagossoftware.lagosfx.navigation.NavigationManager;
import javafx.scene.layout.BorderPane;

import java.math.BigDecimal;

/**
 * Adresse Bank presenter
 *
 * @author Cem Ikta
 */
public class AdresseBankPresenter extends AbstractPresenter<AdresseBankView> {

    @Inject
    public AdresseBankPresenter(final NavigationManager navigationManager,
            final AdresseBankView view) {
        super(navigationManager, view);
    }

    @Override
    public void start(BorderPane container) {
        bind();
    }

    @Override
    public void bind() {
    }

    public void popFields(Adresse entity) {
        view.getTfKontoInhaber().setText(entity.getKontoInhaber());
        view.getTfIban().setText(entity.getIban());
        view.getTfBic().setText(entity.getBic());
        view.getTfBank().setText(entity.getBank());
        view.getTfKontoNr().setText(entity.getKontoNr());
        view.getTfBlz().setText(entity.getBlz());

        view.getDpLetzteSpendeAm().setValue(DateHelpers.convertDateToLocalDate(
                entity.getLetzteSpendeAm()));
        view.getNfBetrag().setValue(entity.getBetrag() != null ? 
                entity.getBetrag() : BigDecimal.ZERO);
        view.getNfSpendensumme().setValue(entity.getSpendensumme() != null ? 
                entity.getSpendensumme() : BigDecimal.ZERO);
    }
    
    public void pushFields(Adresse entity) {
        entity.setKontoInhaber(view.getTfKontoInhaber().getText());
        entity.setIban(view.getTfIban().getText());
        entity.setBic(view.getTfBic().getText());
        entity.setBank(view.getTfBank().getText());
        entity.setKontoNr(view.getTfKontoNr().getText());
        entity.setBlz(view.getTfBlz().getText());

        entity.setLetzteSpendeAm(DateHelpers.convertLocalDateToDate(
                view.getDpLetzteSpendeAm().getValue()));
        entity.setBetrag(view.getNfBetrag().getValue());
        entity.setSpendensumme(view.getNfSpendensumme().getValue());
    }

}
