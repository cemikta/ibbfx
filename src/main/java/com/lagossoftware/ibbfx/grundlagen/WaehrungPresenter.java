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
package com.lagossoftware.ibbfx.grundlagen;

import com.google.inject.Inject;
import com.lagossoftware.ibbfx.IbbManagement;
import com.lagossoftware.ibbfx.app.AppPlaces;
import com.lagossoftware.ibbfx.entity.Waehrung;
import com.lagossoftware.ibbfx.service.WaehrungService;
import com.lagossoftware.lagosfx.mvp.AbstractBrowserFormPresenter;
import com.lagossoftware.lagosfx.navigation.NavigationManager;
import com.lagossoftware.lagosfx.service.QueryParameter;
import javafx.scene.layout.BorderPane;

import java.util.Map;
import java.util.logging.Logger;

/**
 * Waehrung presenter
 *
 * @author Cem Ikta
 */
public class WaehrungPresenter extends AbstractBrowserFormPresenter<Waehrung, 
        WaehrungView, WaehrungService> {
    
    @Inject
    public WaehrungPresenter(final NavigationManager navigationManager, 
            final Logger logger, final WaehrungView view, final WaehrungService service) {
        super(navigationManager, logger, view, service);
    }

    @Override
    public void start(BorderPane container) {
        container.setCenter(getView().asNode());
        bind();
        onSearch();
        view.getSearchBox().requestFocus();
    }

    @Override
    public void bind() {
        super.bind();
    }

    @Override
    public String getNamedQuery() {
        return Waehrung.FIND_ALL;
    }

    @Override
    public String getNamedQueryWithFilter() {
        return Waehrung.FIND_BY_CODE_AND_BEZEICHNUNG;
    }

    @Override
    public Map<String, Object> getFilterParameters() {
        return QueryParameter.with("bezeichnung", 
                "%" + view.getSearchBox().getText() + "%").parameters();
    }

    @Override
    public void requestFocusAfterAddNew() {
        view.getTfCode().requestFocus();
    }
    
    @Override
    public void popFields() {
        view.getTfCode().setText(entity.getCode());
        view.getTfBezeichnung().setText(entity.getBezeichnung());
    }
    
    @Override
    public void pushFields() {
        entity.setCode(view.getTfCode().getText());
        entity.setBezeichnung(view.getTfBezeichnung().getText());

        if (isNewModel()) {
            entity.setCreatedBy(IbbManagement.get().getLoggedUser().getId());
        } else {
            entity.setUpdatedBy(IbbManagement.get().getLoggedUser().getId());
        }
    }

    @Override
    public AppPlaces getPlaceName() {
        return AppPlaces.WAEHRUNG;
    }

}
