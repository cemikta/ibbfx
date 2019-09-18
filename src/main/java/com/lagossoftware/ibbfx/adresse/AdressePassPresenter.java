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

/**
 * Adresse Pass presenter
 *
 * @author Cem Ikta
 */
public class AdressePassPresenter extends AbstractPresenter<AdressePassView> {

    @Inject
    public AdressePassPresenter(final NavigationManager navigationManager,
            final AdressePassView view) {
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
        view.getTfBeruf().setText(entity.getBeruf());
        view.getDpGeburtsdatum().setValue(DateHelpers.convertDateToLocalDate(
                entity.getGeburtsdatum()));
        view.getTfGeburtsort().setText(entity.getGeburtsort());
        view.getTfStaatsangehoerigkeit().setText(entity.getStaatsangehoerigkeit());
        view.getTfPassNr().setText(entity.getPassNr());
        view.getDpAusstellungsdatum().setValue(DateHelpers.convertDateToLocalDate(
                entity.getAusstellungsdatum()));
        view.getTfAusstellungsort().setText(entity.getAusstellungsort());
        view.getNfKostenstelle().setValue(entity.getKostenstelle() != null ? 
                entity.getKostenstelle() : 0);
    }
    
    public void pushFields(Adresse entity) {
        entity.setBeruf(view.getTfBeruf().getText());
        entity.setGeburtsdatum(DateHelpers.convertLocalDateToDate(
                view.getDpGeburtsdatum().getValue()));
        entity.setGeburtsort(view.getTfGeburtsort().getText());
        entity.setStaatsangehoerigkeit(view.getTfStaatsangehoerigkeit().getText());
        entity.setPassNr(view.getTfPassNr().getText());
        entity.setAusstellungsdatum(DateHelpers.convertLocalDateToDate(
                view.getDpAusstellungsdatum().getValue()));
        entity.setAusstellungsort(view.getTfAusstellungsort().getText());
        entity.setKostenstelle(view.getNfKostenstelle().getValue());
    }

}
