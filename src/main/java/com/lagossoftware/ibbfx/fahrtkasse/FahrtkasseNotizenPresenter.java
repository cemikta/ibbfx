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
package com.lagossoftware.ibbfx.fahrtkasse;

import com.google.inject.Inject;
import com.lagossoftware.ibbfx.entity.Fahrtkasse;
import com.lagossoftware.lagosfx.mvp.AbstractPresenter;
import com.lagossoftware.lagosfx.navigation.NavigationManager;
import javafx.scene.layout.BorderPane;

/**
 * Fahrtkasse Notizen presenter
 *
 * @author Cem Ikta
 */
public class FahrtkasseNotizenPresenter extends AbstractPresenter<FahrtkasseNotizenView> {

    @Inject
    public FahrtkasseNotizenPresenter(final NavigationManager navigationManager,
                                      final FahrtkasseNotizenView view) {
        super(navigationManager, view);
    }

    @Override
    public void start(BorderPane container) {
        bind();
    }

    @Override
    public void bind() {
    }

    public void popFields(Fahrtkasse entity) {
        view.getTfNotizen().setText(entity.getNotizen());
    }
    
    public void pushFields(Fahrtkasse entity) {
        entity.setNotizen(view.getTfNotizen().getText());
    }

}
