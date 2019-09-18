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

/**
 * Planung Notizen presenter
 *
 * @author Cem Ikta
 */
public class PlanungNotizenPresenter extends AbstractPresenter<PlanungNotizenView> {

    @Inject
    public PlanungNotizenPresenter(final NavigationManager navigationManager,
                                   final PlanungNotizenView view) {
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
        view.getTfNotizen().setText(entity.getNotizen());
    }
    
    public void pushFields(Planung entity) {
        entity.setNotizen(view.getTfNotizen().getText());
    }

}
