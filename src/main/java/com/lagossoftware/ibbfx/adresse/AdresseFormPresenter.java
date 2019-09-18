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
import com.lagossoftware.ibbfx.IbbManagement;
import com.lagossoftware.ibbfx.app.AppPlaces;
import com.lagossoftware.ibbfx.app.I18n;
import com.lagossoftware.ibbfx.entity.Adresse;
import com.lagossoftware.ibbfx.service.AdresseService;
import com.lagossoftware.lagosfx.mvp.AbstractMultiPageFormPresenter;
import com.lagossoftware.lagosfx.mvp.HasParameters;
import com.lagossoftware.lagosfx.mvp.Place;
import com.lagossoftware.lagosfx.navigation.NavigationManager;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import org.controlsfx.control.Notifications;

import java.util.Map;
import java.util.logging.Logger;

/**
 * Adresse form presenter
 *
 * @author Cem Ikta
 */
public class AdresseFormPresenter extends AbstractMultiPageFormPresenter<Adresse, 
        AdresseFormView, AdresseService> implements HasParameters {
    
    private AdresseGrunddatenPresenter adresseGrunddatenPresenter;
    private AdresseKommunikationPresenter adresseKommunikationPresenter;
    private AdresseBankPresenter adresseBankPresenter;
    private AdressePassPresenter adressePassPresenter;
    private AdresseStichwortPresenter adresseStichwortPresenter;
    private AdresseNotizenPresenter adresseNotizenPresenter;
    private AdresseAuditPresenter adresseAuditPresenter;

    private Map<String, Object> parameters;
    
    @Inject
    public AdresseFormPresenter(final NavigationManager navigationManager, 
            final Logger logger, final AdresseFormView view, final AdresseService service, 
            AdresseGrunddatenPresenter adresseGrunddatenPresenter, 
            AdresseKommunikationPresenter adresseKommunikationPresenter, 
            AdresseBankPresenter adresseBankPresenter, 
            AdressePassPresenter adressePassPresenter, 
            AdresseStichwortPresenter adresseStichwortPresenter, 
            AdresseNotizenPresenter adresseNotizenPresenter,
            AdresseAuditPresenter adresseAuditPresenter) {
        super(navigationManager, logger, view, service);
        
        this.adresseGrunddatenPresenter = adresseGrunddatenPresenter;
        this.adresseKommunikationPresenter = adresseKommunikationPresenter;
        this.adresseBankPresenter = adresseBankPresenter;
        this.adressePassPresenter = adressePassPresenter;
        this.adresseStichwortPresenter = adresseStichwortPresenter;
        this.adresseNotizenPresenter = adresseNotizenPresenter;
        this.adresseAuditPresenter = adresseAuditPresenter;
    }

    @Override
    public void start(BorderPane container) {
        container.setCenter(view.asNode());
        container.setLeft(null);
        container.setRight(null);
        container.setTop(null);
        container.setBottom(null);
        bind();

        adresseGrunddatenPresenter.start(container);
        adresseKommunikationPresenter.start(container);
        adresseBankPresenter.start(container);
        adressePassPresenter.start(container);
        adresseStichwortPresenter.start(container);
        adresseNotizenPresenter.start(container);
        adresseAuditPresenter.start(container);

        view.getButtonBarPane().addPage(adresseGrunddatenPresenter.getView().getIconPath(), 
                adresseGrunddatenPresenter.getView().getTitle(), 
                adresseGrunddatenPresenter.getView().asNode());
        
        view.getButtonBarPane().addPage(adresseKommunikationPresenter.getView().getIconPath(), 
                adresseKommunikationPresenter.getView().getTitle(), 
                adresseKommunikationPresenter.getView().asNode());

        view.getButtonBarPane().addPage(adresseBankPresenter.getView().getIconPath(), 
                adresseBankPresenter.getView().getTitle(), 
                adresseBankPresenter.getView().asNode());

        view.getButtonBarPane().addPage(adressePassPresenter.getView().getIconPath(), 
                adressePassPresenter.getView().getTitle(), 
                adressePassPresenter.getView().asNode());

        view.getButtonBarPane().addPage(adresseStichwortPresenter.getView().getIconPath(), 
                adresseStichwortPresenter.getView().getTitle(), 
                adresseStichwortPresenter.getView().asNode());
        
        view.getButtonBarPane().addPage(adresseNotizenPresenter.getView().getIconPath(), 
                adresseNotizenPresenter.getView().getTitle(), 
                adresseNotizenPresenter.getView().asNode());

        view.getButtonBarPane().addPage(adresseAuditPresenter.getView().getIconPath(),
                adresseAuditPresenter.getView().getTitle(),
                adresseAuditPresenter.getView().asNode());

        entity =  (Adresse) parameters.get("selectedEntity");
        popFields();
        setRequestFocus();
    }

    @Override
    public void setRequestFocus() {
        adresseGrunddatenPresenter.getView().getCbAnrede().requestFocus();
    }
    
    @Override
    public void popFields() {
        adresseGrunddatenPresenter.popFields(entity);
        adresseKommunikationPresenter.popFields(entity);
        adresseBankPresenter.popFields(entity);
        adressePassPresenter.popFields(entity);
        adresseStichwortPresenter.popFields(entity);
        adresseNotizenPresenter.popFields(entity);
        adresseAuditPresenter.popFields(entity);
    }
    
    @Override
    public void pushFields() {
        adresseGrunddatenPresenter.pushFields(entity);
        adresseKommunikationPresenter.pushFields(entity);        
        adresseBankPresenter.pushFields(entity);        
        adressePassPresenter.pushFields(entity);        
        adresseStichwortPresenter.pushFields(entity);        
        adresseNotizenPresenter.pushFields(entity);
        adresseAuditPresenter.pushFields(entity);

        if (isNewModel()) {
            entity.setCreatedBy(IbbManagement.get().getLoggedUser().getId());
        } else {
            entity.setUpdatedBy(IbbManagement.get().getLoggedUser().getId());
        }
    }
    
    @Override
    public boolean isFormValid() {
        if (adresseGrunddatenPresenter.getView().getValidationSupport().isInvalid()) {
            Notifications.create()
                    .text(I18n.COMMON.getString("notification.saveValidationError"))
                    .position(Pos.TOP_RIGHT).showWarning();
        }

        return !adresseGrunddatenPresenter.getView().getValidationSupport().isInvalid();
    }

    @Override
    public AppPlaces getPlaceName() {
        return AppPlaces.ADDRESSE_FORM;
    }

    @Override
    public void onBack() {
        navigationManager.goTo(new Place(AppPlaces.ADDRESSE));
    }

    @Override
    public Map<String, Object> getParameters() {
        return parameters;
    }

    @Override
    public void setParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
    }

}
