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
import com.lagossoftware.ibbfx.entity.Bundesland;
import com.lagossoftware.ibbfx.entity.Land;
import com.lagossoftware.ibbfx.service.BundeslandService;
import com.lagossoftware.ibbfx.service.LandService;
import com.lagossoftware.lagosfx.mvp.AbstractBrowserFormPresenter;
import com.lagossoftware.lagosfx.navigation.NavigationManager;
import com.lagossoftware.lagosfx.service.QueryParameter;
import javafx.scene.layout.BorderPane;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Bundesland presenter
 *
 * @author Cem Ikta
 */
public class BundeslandPresenter extends AbstractBrowserFormPresenter<Bundesland, 
        BundeslandView, BundeslandService> {

    private LandService landService;
    
    @Inject
    public BundeslandPresenter(final NavigationManager navigationManager, 
            final Logger logger, final BundeslandView view, 
            final BundeslandService service, final LandService landService) {
        super(navigationManager, logger, view, service);
        
        this.landService = landService;
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

        List<Land> landList = landService.getListWithNamedQuery(Land.FIND_ALL);
        view.getCbLand().getItems().addAll(landList);
    }

    @Override
    public String getNamedQuery() {
        return Bundesland.FIND_ALL;
    }

    @Override
    public String getNamedQueryWithFilter() {
        return Bundesland.FIND_BY_NAME_AND_LAND;
    }

    @Override
    public Map<String, Object> getFilterParameters() {
        return QueryParameter.with("name", 
                "%" + view.getSearchBox().getText() + "%").parameters();
    }

    @Override
    public void requestFocusAfterAddNew() {
        view.getTfName().requestFocus();
    }
    
    @Override
    public void popFields() {
        view.getTfName().setText(entity.getName());
        view.getCbLand().getSelectionModel().select(entity.getLand());
    }
    
    @Override
    public void pushFields() {
        entity.setName(view.getTfName().getText());
        entity.setLand(view.getCbLand().getSelectionModel().getSelectedItem());

        if (isNewModel()) {
            entity.setCreatedBy(IbbManagement.get().getLoggedUser().getId());
        } else {
            entity.setUpdatedBy(IbbManagement.get().getLoggedUser().getId());
        }
    }

    @Override
    public AppPlaces getPlaceName() {
        return AppPlaces.BUNDESLAND;
    }

}
