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
package com.lagossoftware.ibbfx.settings;

import com.google.inject.Inject;
import com.lagossoftware.ibbfx.IbbManagement;
import com.lagossoftware.ibbfx.app.AppPlaces;
import com.lagossoftware.ibbfx.entity.*;
import com.lagossoftware.ibbfx.service.AppUserGroupService;
import com.lagossoftware.ibbfx.service.AppUserService;
import com.lagossoftware.ibbfx.service.LandService;
import com.lagossoftware.lagosfx.mvp.AbstractBrowserFormPresenter;
import com.lagossoftware.lagosfx.navigation.NavigationManager;
import com.lagossoftware.lagosfx.service.QueryParameter;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.BorderPane;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * App user presenter
 *
 * @author Cem Ikta
 */
public class AppUserPresenter extends AbstractBrowserFormPresenter<AppUser, 
        AppUserView, AppUserService> {

    private AppUserGroupService appUserGroupService;

    @Inject
    public AppUserPresenter(final NavigationManager navigationManager, 
            final Logger logger, final AppUserView view, AppUserService service,
            AppUserGroupService appUserGroupService) {
        super(navigationManager, logger, view, service);

        this.appUserGroupService = appUserGroupService;
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

        List<AppUserGroup> appUserGroupList = appUserGroupService.getListWithNamedQuery(
                AppUserGroup.FIND_ALL);
        view.getCbAppUserGroup().getItems().addAll(appUserGroupList);

        ObservableList<AppUserRole> appUserRoleList = FXCollections.observableArrayList(
                Arrays.asList(AppUserRole.ADMIN, AppUserRole.USER));
        view.getCbAppUserRole().getItems().addAll(appUserRoleList);

        view.getCbAppUserRole().getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends AppUserRole> observable, AppUserRole oldValue,
                 AppUserRole newValue) -> {
            onAppUserRoleChanged(newValue);
        });

    }

    private void onAppUserRoleChanged(AppUserRole newValue) {
        if (AppUserRole.ADMIN == newValue) {
            // user role admin don't need user group!
            view.getCbAppUserGroup().getSelectionModel().clearSelection();
            view.getCbAppUserGroup().setDisable(true);
        } else {
            view.getCbAppUserGroup().setDisable(false);
            view.getCbAppUserGroup().getSelectionModel().selectFirst();
        }
    }

    @Override
    public String getNamedQuery() {
        return AppUser.FIND_ALL;
    }

    @Override
    public String getNamedQueryWithFilter() {
        return AppUser.FIND_BY_USERNAME_AND_NAME;
    }

    @Override
    public Map<String, Object> getFilterParameters() {
        return QueryParameter.with("name", 
                "%" + view.getSearchBox().getText() + "%").parameters();
    }

    @Override
    public void requestFocusAfterAddNew() {
        view.getTfUsername().requestFocus();
    }
    
    @Override
    public void popFields() {
        view.getTfUsername().setText(entity.getUsername());
        view.getPfPassword().setText(entity.getPassword());
        view.getTfName().setText(entity.getName());
        view.getCbAppUserRole().getSelectionModel().select(entity.getAppUserRole());
        view.getCbAppUserGroup().getSelectionModel().select(entity.getAppUserGroup());
        view.getChbActive().setSelected(entity.isActive());
    }
    
    @Override
    public void pushFields() {
        entity.setUsername(view.getTfUsername().getText());
        entity.setPassword(view.getPfPassword().getText());
        entity.setName(view.getTfName().getText());
        entity.setAppUserRole(view.getCbAppUserRole().getSelectionModel().getSelectedItem());
        if (view.getCbAppUserRole().getSelectionModel().getSelectedItem() == AppUserRole.ADMIN) {
            entity.setAppUserGroup(null);
        } else {
            entity.setAppUserGroup(view.getCbAppUserGroup().getSelectionModel().getSelectedItem());
        }
        entity.setActive(view.getChbActive().isSelected());

        if (isNewModel()) {
            entity.setCreatedBy(IbbManagement.get().getLoggedUser().getId());
        } else {
            entity.setUpdatedBy(IbbManagement.get().getLoggedUser().getId());
        }
    }

    @Override
    public AppPlaces getPlaceName() {
        return AppPlaces.APP_USER;
    }

}
