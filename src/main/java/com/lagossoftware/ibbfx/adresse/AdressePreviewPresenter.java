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
import com.lagossoftware.lagosfx.mvp.AbstractPresenter;
import com.lagossoftware.lagosfx.navigation.NavigationManager;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import org.apache.commons.lang3.StringUtils;

/**
 * Adresse presenter
 *
 * @author Cem Ikta
 */
public class AdressePreviewPresenter extends AbstractPresenter<AdressePreviewView> {

    @Inject
    public AdressePreviewPresenter(final NavigationManager navigationManager,
            final AdressePreviewView view) {
        super(navigationManager, view);
    }

    @Override
    public void start(BorderPane container) {
        bind();
    }

    @Override
    public void bind() {
    }

    /**
     * Adresse preview event from eventBus.
     *
     * @param adresse adresse entity
     */
    public void onModelChanged(Adresse adresse) {
        if (adresse == null) {
            cleanLabels();
        } else {
            setLabels(adresse);
        }
    }

    private void cleanLabels() {
        view.getLblAnredeTitelName().setText("");
        view.getLblFirma1().setText("");
        view.getLblFirma2().setText("");

        view.getLblPostfachPlz().setText("");
        view.getLblPostfach().setText("");
        view.getLblStrasse().setText("");
        view.getLblPlzOrt().setText("");
        view.getLblLand().setText("");

        setVisibility(view.getLblPostfachPlz(), "");
        setVisibility(view.getLblStrasse(), "");

        view.getLblMobiltelefon().setText("");
        view.getLblTelefonPrivat().setText("");
        view.getLblTelefonDienst().setText("");
        view.getLblFaxPrivat().setText("");
        view.getLblFaxDienst().setText("");
        view.getLblSkype().setText("");

        setVisibility(view.getLblMobiltelefon(), "");
        setVisibility(view.getLblTelefonPrivat(), "");
        setVisibility(view.getLblTelefonDienst(), "");
        setVisibility(view.getLblFaxPrivat(), "");
        setVisibility(view.getLblFaxDienst(), "");
        setVisibility(view.getLblSkype(), "");

        view.getLblEmail().setText("");
        view.getLblHomepage().setText("");
        view.getLblFacebook().setText("");
        view.getLblTwitter().setText("");
        view.getLblXing().setText("");
        view.getLblLinkedin().setText("");
        view.getLblGooglePlus().setText("");

        setVisibility(view.getLblEmail(), "");
        setVisibility(view.getLblHomepage(), "");
        setVisibility(view.getLblFacebook(), "");
        setVisibility(view.getLblTwitter(), "");
        setVisibility(view.getLblXing(), "");
        setVisibility(view.getLblLinkedin(), "");
        setVisibility(view.getLblGooglePlus(), "");
    }

    private void setLabels(Adresse adresse) {
        if (adresse.getTitel() != null) {
            view.getLblAnredeTitelName().setText(
                    adresse.getAnrede().getBezeichnung() + " " +
                            adresse.getTitel().getBezeichnung() + " " +
                            adresse.getVorname() + " " + adresse.getNachname());
        } else {
            view.getLblAnredeTitelName().setText(
                    adresse.getAnrede().getBezeichnung() + " " +
                            adresse.getVorname() + " " + adresse.getNachname());
        }

        view.getLblFirma1().setText(adresse.getFirma1());
        view.getLblFirma2().setText(adresse.getFirma2());

        view.getLblPostfachPlz().setText(adresse.getPostfachPlz());
        view.getLblPostfach().setText(adresse.getPostfach());
        view.getLblStrasse().setText(adresse.getStrasse());
        if (adresse.getOrt() != null) {
            view.getLblPlzOrt().setText(adresse.getPlz() + " " + adresse.getOrt());
        } else {
            view.getLblPlzOrt().setText(adresse.getPlz());
        }
        view.getLblLand().setText(adresse.getLand().getName());

        setVisibility(view.getLblPostfachPlz(), adresse.getPostfachPlz());
        setVisibility(view.getLblStrasse(), adresse.getStrasse());

        view.getLblMobiltelefon().setText(adresse.getMobiltelefon());
        view.getLblTelefonPrivat().setText(adresse.getTelefonPrivat());
        view.getLblTelefonDienst().setText(adresse.getTelefonDienst());
        view.getLblFaxPrivat().setText(adresse.getFaxPrivat());
        view.getLblFaxDienst().setText(adresse.getFaxDienst());
        view.getLblSkype().setText(adresse.getSkype());

        setVisibility(view.getLblMobiltelefon(), adresse.getMobiltelefon());
        setVisibility(view.getLblTelefonPrivat(), adresse.getTelefonPrivat());
        setVisibility(view.getLblTelefonDienst(), adresse.getTelefonDienst());
        setVisibility(view.getLblFaxPrivat(), adresse.getFaxPrivat());
        setVisibility(view.getLblFaxDienst(), adresse.getFaxDienst());
        setVisibility(view.getLblSkype(), adresse.getSkype());

        view.getLblEmail().setText(adresse.getEmail());
        view.getLblHomepage().setText(adresse.getHomepage());
        view.getLblFacebook().setText(adresse.getFacebook());
        view.getLblTwitter().setText(adresse.getTwitter());
        view.getLblXing().setText(adresse.getXing());
        view.getLblLinkedin().setText(adresse.getLinkedin());
        view.getLblGooglePlus().setText(adresse.getGooglePlus());

        setVisibility(view.getLblEmail(), adresse.getEmail());
        setVisibility(view.getLblHomepage(), adresse.getHomepage());
        setVisibility(view.getLblFacebook(), adresse.getFacebook());
        setVisibility(view.getLblTwitter(), adresse.getTwitter());
        setVisibility(view.getLblXing(), adresse.getXing());
        setVisibility(view.getLblLinkedin(), adresse.getLinkedin());
        setVisibility(view.getLblGooglePlus(), adresse.getGooglePlus());
    }

    private void setVisibility(Label label, String value) {
        if (StringUtils.isEmpty(value)) {
            label.setVisible(false);
        } else {
            label.setVisible(true);
        }
    }

}
