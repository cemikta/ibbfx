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
 * Adresse Notizen presenter
 *
 * @author Cem Ikta
 */
public class AdresseNotizenPresenter extends AbstractPresenter<AdresseNotizenView> {

    @Inject
    public AdresseNotizenPresenter(final NavigationManager navigationManager,
            final AdresseNotizenView view) {
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
        view.getTfFreiesFeld1().setText(entity.getFreiesFeld1());
        view.getTfFreiesFeld2().setText(entity.getFreiesFeld2());
        view.getTfFreiesFeld3().setText(entity.getFreiesFeld3());
        view.getTfNotizen().setText(entity.getNotizen());
    }
    
    public void pushFields(Adresse entity) {
        entity.setFreiesFeld1(view.getTfFreiesFeld1().getText());
        entity.setFreiesFeld2(view.getTfFreiesFeld2().getText());
        entity.setFreiesFeld3(view.getTfFreiesFeld3().getText());
        entity.setNotizen(view.getTfNotizen().getText());
    }

}
