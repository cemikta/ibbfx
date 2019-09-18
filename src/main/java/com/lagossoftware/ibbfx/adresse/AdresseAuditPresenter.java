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
import com.lagossoftware.ibbfx.entity.AppUser;
import com.lagossoftware.ibbfx.service.AppUserService;
import com.lagossoftware.lagosfx.common.DateHelpers;
import com.lagossoftware.lagosfx.mvp.AbstractPresenter;
import com.lagossoftware.lagosfx.navigation.NavigationManager;
import javafx.scene.layout.BorderPane;

/**
 * Adresse Audit presenter
 *
 * @author Cem Ikta
 */
public class AdresseAuditPresenter extends AbstractPresenter<AdresseAuditView> {

    private AppUserService appUserService;

    @Inject
    public AdresseAuditPresenter(final NavigationManager navigationManager,
                                 final AdresseAuditView view, AppUserService appUserService) {
        super(navigationManager, view);

        this.appUserService = appUserService;
    }

    @Override
    public void start(BorderPane container) {
        bind();
    }

    @Override
    public void bind() {
    }

    public void popFields(Adresse entity) {
        AppUser createdByUser = null;
        AppUser updatedByUser = null;

        if (entity.getCreatedBy() != null) {
            createdByUser = appUserService.find(entity.getCreatedBy());
        }

        if (entity.getUpdatedBy() != null) {
            updatedByUser = appUserService.find(entity.getUpdatedBy());
        }

        view.getLblCreatedAt().setText(entity.getCreatedAt() != null ?
                DateHelpers.formatDateToTimestamp(entity.getCreatedAt()) : "");
        if (createdByUser == null) {
            view.getLblCreatedBy().setText(entity.getCreatedBy() != null ?
                    entity.getCreatedBy().toString() : "");
        } else {
            view.getLblCreatedBy().setText(createdByUser.getName());
        }

        view.getLblUpdatedAt().setText(entity.getUpdatedAt() != null ?
                DateHelpers.formatDateToTimestamp(entity.getUpdatedAt()) : "");
        if (updatedByUser == null) {
            view.getLblUpdatedBy().setText(entity.getUpdatedBy() != null ?
                    entity.getUpdatedBy().toString() : "");
        } else {
            view.getLblUpdatedBy().setText(updatedByUser.getName());
        }
    }
    
    public void pushFields(Adresse entity) {
    }

}
