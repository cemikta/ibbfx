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
import javafx.scene.layout.BorderPane;

/**
 * Adresse Kommunikation presenter
 *
 * @author Cem Ikta
 */
public class AdresseKommunikationPresenter extends AbstractPresenter<AdresseKommunikationView> {

    @Inject
    public AdresseKommunikationPresenter(final NavigationManager navigationManager,
            final AdresseKommunikationView view) {
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
        view.getTfMobiltelefon().setText(entity.getMobiltelefon());
        view.getTfTelefonPrivat().setText(entity.getTelefonPrivat());
        view.getTfTelefonDienst().setText(entity.getTelefonDienst());
        view.getTfFaxPrivat().setText(entity.getFaxPrivat());
        view.getTfFaxDienst().setText(entity.getFaxDienst());
        view.getTfEmail().setText(entity.getEmail());
        view.getTfHomepage().setText(entity.getHomepage());
        
        view.getTfSkype().setText(entity.getSkype());
        view.getTfFacebook().setText(entity.getFacebook());
        view.getTfTwitter().setText(entity.getTwitter());
        view.getTfXing().setText(entity.getXing());
        view.getTfLinkedIn().setText(entity.getLinkedin());
        view.getTfGooglePlus().setText(entity.getGooglePlus());
    }
    
    public void pushFields(Adresse entity) {
        entity.setMobiltelefon(view.getTfMobiltelefon().getText());
        entity.setTelefonPrivat(view.getTfTelefonPrivat().getText());
        entity.setTelefonDienst(view.getTfTelefonDienst().getText());
        entity.setFaxPrivat(view.getTfFaxPrivat().getText());
        entity.setFaxDienst(view.getTfFaxDienst().getText());
        entity.setEmail(view.getTfEmail().getText());
        entity.setHomepage(view.getTfHomepage().getText());
        
        entity.setSkype(view.getTfSkype().getText());
        entity.setFacebook(view.getTfFacebook().getText());
        entity.setTwitter(view.getTfTwitter().getText());
        entity.setXing(view.getTfXing().getText());
        entity.setLinkedin(view.getTfLinkedIn().getText());
        entity.setGooglePlus(view.getTfGooglePlus().getText());
    }

}
