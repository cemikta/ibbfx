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
import com.lagossoftware.ibbfx.entity.Land;
import com.lagossoftware.ibbfx.service.LandService;
import com.lagossoftware.lagosfx.mvp.AbstractBrowserFormPresenter;
import com.lagossoftware.lagosfx.navigation.NavigationManager;
import com.lagossoftware.lagosfx.service.QueryParameter;
import javafx.scene.layout.BorderPane;

import java.util.Map;
import java.util.logging.Logger;

/**
 * Land presenter
 *
 * @author Cem Ikta
 */
public class LandPresenter extends AbstractBrowserFormPresenter<Land, 
        LandView, LandService> {
    
    @Inject
    public LandPresenter(final NavigationManager navigationManager, 
            final Logger logger, final LandView view, final LandService service) {
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
        return Land.FIND_ALL;
    }

    @Override
    public String getNamedQueryWithFilter() {
        return Land.FIND_BY_CODE_AND_NAME;
    }

    @Override
    public Map<String, Object> getFilterParameters() {
        return QueryParameter.with("name", 
                "%" + view.getSearchBox().getText() + "%").parameters();
    }

    @Override
    public void requestFocusAfterAddNew() {
        view.getTfIsocode2().requestFocus();
    }
    
    @Override
    public void popFields() {
        view.getTfIsocode2().setText(entity.getIsocode2());
        view.getTfIsocode3().setText(entity.getIsocode3());
        view.getTfName().setText(entity.getName());
    }
    
    @Override
    public void pushFields() {
        entity.setIsocode2(view.getTfIsocode2().getText());
        entity.setIsocode3(view.getTfIsocode3().getText());
        entity.setName(view.getTfName().getText());

        if (isNewModel()) {
            entity.setCreatedBy(IbbManagement.get().getLoggedUser().getId());
        } else {
            entity.setUpdatedBy(IbbManagement.get().getLoggedUser().getId());
        }
    }

    @Override
    public AppPlaces getPlaceName() {
        return AppPlaces.LAND;
    }

}
